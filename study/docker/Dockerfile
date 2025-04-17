# 使用官方的Java运行时作为父镜像
FROM openjdk:8-jdk-alpine

# 设置工作目录
WORKDIR /app

# 将当前目录的内容复制到容器的工作目录
COPY Test2.jar /app/app.jar

# 运行Java应用
#ENTRYPOINT ["java", "-jar", "app.jar"]