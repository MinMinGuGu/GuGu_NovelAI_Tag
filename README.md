# GuGu_NovelAI_Tag
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FMinMinGuGu%2FGuGu_NovelAI_Tag.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FMinMinGuGu%2FGuGu_NovelAI_Tag?ref=badge_shield)

Web项目，用于生成NovelAI、stable-diffusion-webui的参数(Tag|元素)，并支持参数管理。

<s>[在线体验(不支持参数管理)](https://www.gugu.dev/GuGu_NovelAI_Tag), 因服务器到期服务失效</s>

![07614c75a4a0e9f915c09f26ab3f7941.gif](https://img.mjj.today/2023/02/10/07614c75a4a0e9f915c09f26ab3f7941.gif)


## 特性

1. 项目可以完全靠前端支撑，但这样会无法对数据进行管理(CRUD)。
2. 后端一键启动，无须关心数据库的配置，启动时将会在当前用户文件夹下生成`GuGu_NovelAI_Tag/h2database.mv.db`数据库文件。

## 启动项目

### Docker

1. 执行`docker run -it --name=gugu_novelai_tag -p 8080:8080 -d minminfromdocker/gugu_novelai_tag`启动
2. 访问项目`http://127.0.0.1:8080`

### 普通方式

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


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FMinMinGuGu%2FGuGu_NovelAI_Tag.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FMinMinGuGu%2FGuGu_NovelAI_Tag?ref=badge_large)
