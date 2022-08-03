package net.diyigemt.shutup

import net.diyigemt.shutup.config.ShutupConfig
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.isOperator
import net.mamoe.mirai.contact.isOwner
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.BotOnlineEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.GroupMuteAllEvent
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageSource.Key.recall
import net.mamoe.mirai.message.data.time
import java.util.*

object Shutup : KotlinPlugin(
  JvmPluginDescription.loadFromResource()
) {
  private lateinit var instance: Bot
  private val record: MutableList<Pair<Long, Long>> = mutableListOf()
  private var actionFlag: Boolean = false
  private var count: Int = 0
  private lateinit var messageCache: MutableList<MessageChain>
  override fun onEnable() {
    ShutupConfig.reload()
    messageCache = mutableListOf()
    GlobalEventChannel.filter {
      it is BotOnlineEvent && it.bot.id == ShutupConfig.qq
    }.subscribeOnce<BotOnlineEvent> {
      instance = it.bot
    }
    GlobalEventChannel.subscribeAlways<GroupMuteAllEvent> {
      if (!ShutupConfig.group.contains(groupId)) return@subscribeAlways
      if (it.new || !actionFlag) return@subscribeAlways
      actionFlag = false
      var ss = "参与投票的群员:\n"
      record
        .filter { record -> record.first == it.groupId }
        .map { record -> "${it.group[record.second]?.nameCardOrNick}(${record.second})" }
        .forEachIndexed { index, s -> ss += "${index + 1}. $s\n" }
      it.group.sendMessage(ss)
      record.clear()
    }
    GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
      if (!ShutupConfig.group.contains(it.group.id)) return@subscribeAlways
      if (!it.sender.isOperator()) {
        messageCache.add(it.message)
        if (messageCache.size > ShutupConfig.cacheSize) {
          messageCache.removeLastOrNull()
        }
      }
      if (ShutupConfig.qq == it.sender.id || ShutupConfig.exceptQQ.contains(it.sender.id)) return@subscribeAlways
      handle(it)
      if (it.sender.isOperator()) {
        count = 0
      }
    }
  }

  private suspend fun handle(ev: GroupMessageEvent) {
    if (!ev.group[ev.bot.id]!!.isOperator()) return
    val subject = ev.subject
    val groupId = subject.id
    if (ev.message.contentToString() != ShutupConfig.command) return
    val now = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val night = now >= ShutupConfig.nightStart || now <= ShutupConfig.nightEnd
    val target = if (night) ShutupConfig.voteTimesAtNight else ShutupConfig.voteTimes
    count += 1
    val userId = ev.sender.id
    record.add(groupId to userId)
    if (count >= target) {
      subject.settings.isMuteAll = true
      actionFlag = true
      count = 0
      val now0 = Calendar.getInstance().time.time
      messageCache.forEach {
        kotlin.runCatching {
          val time = "${it.time}000".toLong()
          if ((now0 - time) / 1000 / 60 <= ShutupConfig.cacheTime) {
            it.recall()
          }
        }
        Thread.sleep(10)
      }
      subject.sendMessage("达到指定人数,开启全员禁言")
    }
  }

  override fun onDisable() {
    record.clear()
  }
}