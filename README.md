# Re-Novel
基于Java Web技术开发的动漫轻小说网站

## 网站地址
[novel.ccyblog.com](https://novel.ccyblog.com)

## 开发计划
1. 登录注册(已基本成完)
2. 图片存储
3. 小说上传
4. 用户个人信息(例如订阅，已读)
5. 为小说添加图片，声音
6. 待续

以上内容只是初步想法，以后根据实际情况修改。

## 相关技术
#### 后端
* `spring`
* `spring-mvc`
* `mybatis` ORM(Object Releative Mapping),对象关系映射
* `mysql`
* `jstl`
* `lombok` 一个标签自动设置`Getter`, `Setter`, `Log`的小工具
* `log4j` 日志记录
* `captcha` 验证码工具
* `jackson-core` RESTFul(REpresentational State Transfer) 表现层状态转移，即`@ResponseBody`标签需要使用。
* `shiro` 安全认证授权
* `aspectjrt` `aspectjweaver` `1cglib` 开启shiro注解功能，也是使用AOP所需的库

#### 前端
* `jquery`
* `bootstrap`
* `awesome-font`

#### 开发环境
* `JDK1.8`
* `Idea 2016.3.4`

## 功能模块说明
1. [登录注册](https://github.com/isghost/Re-Novel/blob/master/doc/LOGIN_REGISTER.md)

## 更新记录
1. 2017/06/09 开坑
2. 2017/08/09 完成登录注册

## 如何贡献代码
用`idea`打开工程，在`TODO`里面，有一些待完成的功能点，可以优先完成这些功能。当然，有建议，想法，实现，都可以提交过来。

## 历程
#### 1. 2017/08/09
六月九号下决定放弃干了三年的游戏行业，六月十号提出离职，确认九月十号离职(给领导情面，带一波新人再走)。花一个月复习《java编程思想》,边复习`spring`，边写这个应用，断断续续，终于完成第一个模块。算是跨出换行业第一个小半步。接下来一个月整理知识，找到工作后，继续更新后面的内容。
> 路漫漫其修远兮 吾将上下而求索
