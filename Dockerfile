# ======== Étape 1 : Build ========
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copier les fichiers Maven et installer les dépendances (cache build)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et compiler le projet
COPY src ./src
RUN mvn clean package -DskipTests

# ======== Étape 2 : Runtime ========
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copier le JAR construit depuis l’étape précédente
COPY --from=build /app/target/tennis-stats-api-1.0.0.jar app.jar

# Exposer le port de l’application (8081 selon ton config)
EXPOSE 8081

# Lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
