# 运行方法
## 开发环境安装
1. git clone https://github.com/isghost/Re-Novel.git
2. IntelliJ IDEA
	1. 下载安装
	2. 破解 Help --> Register --> License server --> 输入 http://idea.iteblog.com/key.php --> Active
	3. 安装lombok插件 File --> Settings --> Plugins --> Browse repositories... --> 搜索lombok --> 安装重启
	4. eclipse同理安装lombok插件
3. 在IDE导入已经存在的maven工程(具体步骤百度)
4. jdk 1.8环境 略
5. 查看配置，web.xml的spring.profiles.default修改成prod，这样直接连接服务器数据库。如果后端开发修改数据库结构，需要自行配置mysql，执行sql文件。
6. 安装tomcat，并在IDEA中配置
7. 打开工程，选择用tomcat运行