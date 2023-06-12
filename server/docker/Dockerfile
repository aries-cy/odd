FROM openjdk:8-jdk-alpine

WORKDIR /app

# 复制 JAR 文件到容器中
COPY target/server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 80

# 设置容器启动命令
CMD ["java", "-jar", "app.jar"]
