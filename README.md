# 整体思路

利用Springboot 提供的AbstractRoutingDataSource类提供的自动路由功能，即通过实现接口

```
@Override
protected Object determineCurrentLookupKey() ;
```
来实现动态切换数据源

# 具体步骤

## 关闭DataSourceAutoConfiguration, 在application中添加如下配置

```
spring:
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

## 配置多数据源

```aidl
multitenant:
  datasources:
    - url:: jdbc:mysql://localhost:3308/fly1?useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: root
      name: fly1
    - url: jdbc:mysql://localhost:3308/fly2?useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: root
      name: fly2
```

## 通过类DynamicDataSourceProperties获取多数据源配置信息

## 继承AbstractRoutingDataSource实现动态数据源切换，见类DynamicDataSource

## 通过DataSourceHolder 来维护当前线程数据源

## 主要流程
> * 后端接收到请求
> * 根据请求的参数或者当前用户应该访问哪个数据库，在DataSourceHolder中设置当前数据源的名称
> * Spring boot会根据 determineCurrentLookupKey里返回的当前数据源的值（即上一步在DataSourceHolder中设置当前数据源的名称）来路由当前访问的数据源

## demo
user包下的代码为测试demo代码

# 遗留问题

目前用threadlocal的方式管理当前上下文的数据源信息，在多线程的场景下会出现找不到当前上下文的数据源的情况（子线程没有父线程的threadlocal变量），可以通过 [Transmittable Thread Local](https://github.com/alibaba/transmittable-thread-local) 来解决此问题


