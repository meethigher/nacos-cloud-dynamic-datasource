模块说明

* ftpclientpool：通过nacos配置中心实现ftpclientpool的动态切换，通过接口/list测试。
* jdbctemplate：通过nacos配置中心实现jdbctemplate的动态切换，通过定时任务Runner测试。
* jpa：通过nacos配置中心实现jpa底层数据源的动态切换，通过定时任务Runner测试。测试jdbctemplate时，可以通过jpa来快速生成表。
* spring-cloud-gateway-demo：记录gateway的基础配置。


注意：

springboot与springcloud与springcloudalibaba之间的对应关系，建议直接使用[阿里云脚手架](https://start.aliyun.com/)

![image-20240320151732317](README/image-20240320151732317.png)

![image-20240320151828945](README/image-20240320151828945.png)