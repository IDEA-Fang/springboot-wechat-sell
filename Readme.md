#readme
##SpringBoot微信端仿美团外卖系统
###只开发后端，跟着github的项目开发

项目设计
#####功能设计
![功能设计](https://github.com/IDEA-Fang/springboot-wechat-sell/blob/master/showimg/Springbootsell-gongneng.png)

#####角色划分：

* 卖家（PC端）   是一个卖家管理的后台
* 买家（手机端） 是一个微信公众账号提供的服务

#####功能分析：

* 包含三大模块，商品、订单、类目
* 针对买家，商品模块有商品列表数据、订单模块有订单的创建、查询、取消等
* 针对卖家，有订单管理、商品管理、类目管理等

###技术框架
1. springboot
2. 持久层框架SpringBoot-Data-JPA
3. 实体类使用了lombok插件，使代码简洁
4. 日志选择Slf4j+Logback
5. java2json序列化工具使用Jackson
6. 后期部署Tomcat+Nginx+Redis+Mysql
7. 配置文件使用.yml

###技术点
1. 使用DTO实现实体类和数据传输对象的转换
2. 使用统一的Enums来规范参数
3. 自定义错误类型
4. 使用VO定义输出对象
5. 实现表单转换成DTO功能

![项目文件路径](https://github.com/IDEA-Fang/springboot-wechat-sell/blob/master/showimg/springbootsell-mulu.png)

后端开发，按照dao->service->controller的api的顺序开发

开发进度：
1. 买家端开发完成，主要是点单，类目（品种），商品，买家。
2. 卖家段部分实现，部分逻辑。
3. 在微信支付这里卡主，没有继续开发
4. 所有的前端页面层没有开发
5. 后续开发
 >> 前端展示
 >> 支付功能
 >> 卖家段管理部分，对于商品，类目，订单等的管理
 >> 产品的部署，tomcat部署，Nginx做负载均衡
 >> 分布式部署项目（没有想好怎么做）

* 底层DateObject
> 存放实体对象
* DAO的Resository
> 进行查询的接口
* Service
> 处理业务的逻辑
* Controller
> 转发输出到页面

2018.9.25整理
---------------

