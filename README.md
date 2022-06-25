# 商品管理系统

项目简介
本系统是基于Vue+Element+SSM+Mysql实现的商品管理。主要实现的功能有用户登录注册，管理员权限下的用户管理，商品管理

技术栈
编辑器
IntelliJ IDEA 2021.1.1 x64

前端技术
基础：html+css+JavaScript

框架：vue+element+jquery+axios

后端技术
Spring+SpringMVC+mybatis

数据库：mysql 8.0

jdk版本：16.0.2

tomcat版本：8.5.68

数据库连接池：dbcp2

项目管理工具：maven

本地运行
Idea环境准备
1.idea新增jdk
2.idea新增tomcat

3.idea配置maven

SSM_CRUD商品管理系统

使用idea导入项目，配置jdk、tomcat和用maven下载所需jar包。

打开Mysql，创建spring数据库，并运行ssm_crud_goods.sql和ssm_crud_user.sql文件。

4.修改resources\config\db.properties中数据库账号密码。

5.商品管理上传显示图片用到了本地路径（使用tomcat服务器路径重启会导致资源丢失）（需要在tomcat配置中加上<Context path="/upload" docBase="D:\upload" debug="0" reloadable="false" crossContext="true"/>）![image-20220527172826430](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527172826430.png)

6.发布到tomcat中，具体访问链接看tomcat配置，若未修改则http://localhost:8080/SSM_CRUD/为登录页面。

该系统分为3种账号。

管理员初始账号：admin系统管理员初始密码：padmin

有商品的普通用户：qier 密码：123456

•注意修改src\demo.properties中数据库相关的内容。

项目截图

登录：

![image-20220527173027821](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173027821.png)

注册：

![image-20220527173126791](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173126791.png)



主页：

![image-20220527173144030](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173144030.png)



非管理员进入用户管理

![image-20220527173224013](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173224013.png)



管理员进入用户管理：

![image-20220527173416234](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173416234.png)

新增用户dialog：

![image-20220527173536267](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173536267.png)

编辑用户dialog:

![image-20220527173618712](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173618712.png)





侧边菜单：

![image-20220527173256431](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527173256431.png)



商品管理列表：

![image-20220527174835532](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527174835532.png)

新增商品dialog:

![image-20220527174856270](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527174856270.png)



编辑商品dialog：

![image-20220527174924104](D:\Users\雷族\Documents\repos\JAVA\SSM\SSM_CRUD\README.assets\image-20220527174924104.png)









