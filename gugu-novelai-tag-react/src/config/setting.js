const env = process.env.APP_ENV;
const apiPrefix = "//127.0.0.1:8080";

const apis = {
    localPath: "/", // 与package.json的homepage保持一致
    indexApi: apiPrefix + "/gugu_novel_ai_tag/index",
    categoryApi: apiPrefix + "/gugu_novel_ai_tag/category",
    attributeApi: apiPrefix + "/gugu_novel_ai_tag/attribute",
    configApi: apiPrefix + "/gugu_novel_ai_tag/config",
    env
}

export default apis;