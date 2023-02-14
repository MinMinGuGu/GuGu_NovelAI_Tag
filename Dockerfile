FROM openjdk:8

LABEL version="1.0.0"
LABEL author="minmin"

RUN mkdir -p /myapp/GuGu_NovelAI_Tag
COPY ./gugu-novelai-tag-admin/target/gugu-novelai-tag-admin-1.0.0.jar /myapp/GuGu_NovelAI_Tag
WORKDIR /myapp/GuGu_NovelAI_Tag

CMD ["java", "-jar", "gugu-novelai-tag-admin-1.0.0.jar"]