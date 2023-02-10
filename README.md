# GuGu_NovelAI_Tag

Web项目，用于生成NovelAI、stable-diffusion-webui的参数(Tag|元素)，并支持管理参数。

![07614c75a4a0e9f915c09f26ab3f7941.gif](README.assets/07614c75a4a0e9f915c09f26ab3f7941-16760300496593.gif)

## 特性

1. 项目可以完全靠前端支撑，但这样会无法对数据进行管理(CRUD)。
2. 后端一键启动，无须关心数据库的配置，启动时将会在当前用户文件夹下生成`GuGu_NovelAI_Tag/h2database.mv.db`数据库文件。

## 启动项目

> 环境依赖
>
> > 必要
> >
> > > nodejs version v14.17.5
> >
> > 非必要
> >
> > > java version 8

1. `gugu-novelai-tag-react`文件夹下运行`npm install&&npm start`启动前端
2. (可选)启动Java项目来支持数据管理(CRUD)
3. 访问项目`http://127.0.0.1:3000/`

## 后端接口

### 数据库管理

默认情况下访问`http://127.0.0.1:8080/h2-console`

相关信息可以从`gugu-novelai-tag-admin/src/main/resources/application.yml`获取。

### API接口文档

默认情况下访问`http://127.0.0.1:8080/swagger-ui.html`

## 部署项目

### 配合其他Web服务器

1. `gugu-novelai-tag-react`文件夹下运行`npm i&npm build`打包前端

2. 将打包好的前端`build`文件夹移动到Web服务器的映射目录中
3. 如果已启动后端请修改相关配置将前端请求映射到后端去

### 不需要其他Web服务器

> 环境依赖
>
> > Java version 8及以上
> >
> > maven
>

1. `gugu-novelai-tag-react`文件夹下运行`npm i&npm build`打包前端

2. 将打包好的前端`build`文件夹移动到`gugu-novelai-tag-admin/src/main/resources`下
3. 在项目根目录执行`mvn clean package`命令
4. 构建后生成`gugu-novelai-tag-admin/target/gugu-novelai-tag-admin-1.0.0.jar`运行该jar即可启动

## 主要目录结构

```txt
├─gugu-novelai-tag-admin	后端目录
│  └─src
│      ├─main
│      │  ├─java
│      │  └─resources
│      │      ├─data	初始数据
│      │      └─sql		初始sql
└─gugu-novelai-tag-react	前端目录
    ├─public
    └─src
        ├─config	配置文件
```

## 项目相关技术栈

前端：React、Ant Design

后端：SpringBoot、Mybatis-plus
