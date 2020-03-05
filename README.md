# springdata-jpa-demo
# 实践一对多 多对多的jpa操作

## SpringData + JPA ##
1.SpringData：Spring 的一个子项目。用于简化数据库访问，支持NoSQL 和 关系数据存储。其主要目标是使数据库的访问变得方便快捷。  
2.JPA Spring Data：致力于减少数据访问层 (DAO) 的开发量. 开发者唯一要做的，就只是声明持久层的接口，其他都交给 Spring Data JPA 来完成！  

## 一对多 ##
一端多Department 多端为Employee  
在JPA规范中，一对多的双向关系由多端(Employee)来维护，负责关系的增删改查。一端(department)则为关系被维护端，不能维护关系。
关系护端负责外键记录的更新，一方为被维护端即没有权力更新外键记录。

## 多对多 ##
User用户 Authority权限
User为关系维护端:删除user 中间表对应的数据也删除
Authority为关系被维护端:不能操作中间表

## 参考 ##
JPA + SpringData 操作数据库 ---- 深入了解 SpringData  
https://www.cnblogs.com/crawl/p/7735616.html  

Spring Data JPA 之 一对一，一对多，多对多 关系映射  
https://blog.csdn.net/johnf_nash/article/details/80642581  

使用Hibernate、JPA、Lombok遇到的有趣问题  
https://juejin.im/post/5b3ca5386fb9a04fd34370d2  

记JPA一次生成数据库表没有外键原因  
https://blog.csdn.net/w2298119289j/article/details/93309320  



