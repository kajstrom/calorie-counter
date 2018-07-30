FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/calorie-counter.jar /calorie-counter/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/calorie-counter/app.jar"]
