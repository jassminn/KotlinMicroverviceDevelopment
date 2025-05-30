FROM bellsoft/liberica-openjdk-rocky:17

ADD target/*.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]