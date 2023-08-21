#   ---------Dockerfile para aplicacao SpringBoot---------

FROM maven:3.8.3-openjdk-17 as maven

# Create a directory where our app will be placed
RUN mkdir -p /usr/src/app

# Change directory so that our commands run inside this new directory
WORKDIR /usr/src/app
 
# Get all the code needed to run the app
COPY . .

#Substitui o arquivo 'application.properties' por 'application-docker.properties' para ser utilizado no docker
COPY src/main/resources/application-docker.properties src/main/resources/application.properties 
 
# Run Maven build
RUN mvn clean package -DskipTests=true

# Just using the build artifact and then removing the build-container
FROM openjdk:17.0.2-jdk

# Create a directory where our app will be placed
RUN mkdir -p /usr/src/app

# Add Spring Boot app.jar to Container
COPY --from=maven "/usr/src/app/target/game-manager-*.jar" /usr/src/app/game-manager.jar

# Make port 8081 available to the world outside this container
EXPOSE 8081

# Fire up our Spring Boot app by default
CMD [ "sh", "-c", "java -jar /usr/src/app/game-manager.jar"]