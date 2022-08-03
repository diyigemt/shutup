package net.diyigemt.shutup.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object ShutupConfig: AutoSavePluginConfig("shut-up") {

  @ValueDescription("运行的qq")
  val qq: Long by value()

  @ValueDescription("生效的群")
  val group: List<Long> by value()

  @ValueDescription("排除的qq,因为机器人管理也会触发检测机制")
  val exceptQQ: List<Long> by value()

  @ValueDescription("指定触发指令")
  val command: String by value("/封群")

  @ValueDescription("指定的票数")
  val voteTimes: Int by value(5)

  @ValueDescription("夜间指定的票数")
  val voteTimesAtNight: Int by value(3)

  @ValueDescription("夜晚开始时间, 24小时制")
  val nightStart: Int by value(23)

  @ValueDescription("夜晚结束时间, 24小时制")
  val nightEnd: Int by value(7)

  @ValueDescription("消息缓存条数,越大撤回消息的效果就越好")
  val cacheSize: Int by value(100)

  @ValueDescription("撤回几分钟内的消息,单位分钟")
  val cacheTime: Int by value(5)

}