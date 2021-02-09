# HMS Ads Kit Java Severdemo
中文 | [English](https://github.com/HMS-Core/hms-ads-severdemo/edit/master/README.md)
## 目录

 * [简介](#简介)
 * [安装](#安装)
 * [配置](#配置)
 * [环境要求](#环境要求)
 * [示例代码](#示例代码)
 * [授权许可](#授权许可)
 
 
## 简介
华为广告服务（HUAWEI Ads Kit）服务端示例代码向您介绍如何通过Java代码调用RESTful接口。

## 安装
确认Java环境准备就绪。

## 配置
为使用华为广告服务RESTful接口提供的功能，您需要在请求消息中设置相关参数。
   
**access token**: 请求消息的令牌。获取过程请参见华为开发者联盟上的开发指南。

**requestUrl**: URL，用来接入华为广告服务的流量变现服务报表API。详情请参见API参考。

## 环境要求
JDK 1.7.0及以上版本。

## 示例代码
使用在华为开发者联盟上获得的client ID以及对应密钥，发送HTTPS POST请求，获取查询access token，发送HTTPS POST查询报表数据。

1). Send an message to create a dataCollector.
You need to assign a value to the request body based on the corresponding format and use the POST method to send the message body to the corresponding URL.

## 技术支持
如果您对HMS Core还处于评估阶段，可在[Reddit社区](https://www.reddit.com/r/HuaweiDevelopers/)获取关于HMS Core的最新讯息，并与其他开发者交流见解。

如果您对使用HMS示例代码有疑问，请尝试：
- 开发过程遇到问题上[Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services)，在`huawei-mobile-services`标签下提问，有华为研发专家在线一对一解决您的问题。
- 到[华为开发者论坛](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core板块与其他开发者进行交流。

如果您在尝试示例代码中遇到问题，请向仓库提交[issue](https://github.com/HMS-Core/hms-ads-severdemo/issues)，也欢迎您提交[Pull Request](https://github.com/HMS-Core/hms-ads-severdemo/pulls)。

##  授权许可
华为广告服务Java服务端示例代码经过[Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0)授权许可。

