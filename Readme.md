## 声明

<h3>一切开发旨在学习，请勿用于非法用途</h3>

- shutup 是一款免费且开放源代码的软件，仅供学习和娱乐用途使用。
- shutup 不会通过任何方式强制收取费用，或对使用者提出物质条件。
- shutup 由整个开源社区维护，并不是属于某个个体的作品，所有贡献者都享有其作品的著作权。

## 许可证

详见 https://github.com/diyigemt/shutup/blob/master/LICENSE

shutup 继承 [Mirai](https://github.com/mamoe/mirai) 使用 AGPLv3 协议开源。为了整个社区的良性发展，我们强烈建议您做到以下几点：

- 间接接触到 arona 的软件使用 AGPLv3 开源
- **不鼓励，不支持一切商业使用**

请注意，由于种种原因，开发者可能在任何时间**停止更新**或**删除项目**。

### 衍生软件需声明引用

- 若引用 shutup 发布的软件包而不修改 shutup ，则衍生项目需在描述的任意部位提及使用 shutup 。
- 若修改 shutup 源代码再发布，或参考 shutup 内部实现发布另一个项目，则衍生项目必须在文章首部或 'miraiboot' 相关内容首次出现的位置明确声明来源于本仓库 ([shutup](https://github.com/diyigemt/shutup))。
- 不得扭曲或隐藏免费且开源的事实。

## Statement

<h3>All development is for learning, please do not use it for illegal purposes</h3>

- arona is a free and open source software for learning and entertainment purposes only.
- arona will not compulsorily charge fees or impose material conditions on users in any way.
- arona is maintained by the entire open source community and is not a work belonging to an individual. All contributors enjoy the copyright of their work.

## License

See https://github.com/diyigemt/shutup/blob/master/LICENSE for details

shutup inherits [Mirai](https://github.com/mamoe/mirai) Open source using AGPLv3 protocol. For the healthy development of the entire community, we strongly recommend that you do the following:

- Software indirectly exposed to shutup uses AGPLv3 open source
- **Does not encourage and does not support all commercial use**

Please note that for various reasons, developers may **stop updating** or **deleting** projects at any time.

### Derivative software needs to declare and quote

- If you quote the package released by shutup without modifying shutup , the derivative project needs to mention miraiboot in any part of the description.
- If the shutup source code is modified and then released, or another project is released by referring to shutup's internal implementation, the derivative project must be clearly stated in the first part of the article or at the location where 'shutup'-related content first appears from this repository ([shutup](https://github.com/diyigemt/shutup)).
- The fact that it is free and open source must not be distorted or hidden.

## 介绍

shutup是基于mirai-console的插件

**本插件依赖的mirai-console版本为2.11.1**

插件的作用是将全员禁言的权利下放给群员，当有足够数量的群员执行指定指令时，会自动执行全体禁言，并撤回从现在开始往前指定时间（不保证100%准确）至执行指令前最后一刻内的群消息。

使用本插件的目的是期望管理员不在的情况下，群里讨论上头时，群内仅有的几个有理智的群员能够为群的存在贡献自己一份力，因此插件的效果并不能保证。

插件同时会记录参与投票的群员，并在管理员解除禁言时发送信息，以略微防止滥用行为。

**请注意**，该插件十分依赖群员自觉性，过分依赖本插件的后果请自行承担。

## 配置文件详解

| 键               | 属性         | 作用                                        |
| ---------------- | ------------ | ------------------------------------------- |
| qq               | Long         | 指定运行在哪个机器人上                      |
| groups           | List\<Long\> | 指定生效的群                                |
| exceptQQ         | List\<Long\> | 指定排除的qq,因为机器人管理也会触发检测机制 |
| command          | String       | 指定触发指令                                |
| voteTimes        | Int          | 指定的票数                                  |
| voteTimesAtNight | Int          | 夜间指定的票数                              |
| nightStart       | Int          | 夜晚开始时间, 24小时制                      |
| nightEnd         | Int          | 夜晚结束时间, 24小时制                      |
| cacheSize        | Int          | 消息缓存条数,越大撤回消息的效果就越好       |
| cacheTime        | String       | 撤回几分钟内的消息,单位分钟                 |