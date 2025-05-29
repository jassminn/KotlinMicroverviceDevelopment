FROM bellsoft/liberica-openjdk-rocky:17

#메이븐으로 생성된 패키지를 도커 이미지에 포함
#ADD target/*.jar microservice.jar

#도커와 메이븐 통합
ADD maven/*.jar microservice.jar

#도커 실행시 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "microservice.jar"]

#도커 실행시 java 버전 확인
#ENTRYPOINT ["java", "-version"]