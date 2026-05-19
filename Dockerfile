# Etapa 1: Construir el proyecto usando solo el JDK puro de Java 25
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY . .
# Le damos permisos de ejecución al archivo mvnw
RUN chmod +x ./mvnw
# Compilamos usando tu propio Wrapper en lugar del Maven global
RUN ./mvnw clean package -DskipTests

# Etapa 2: Correr el proyecto en el entorno de ejecución
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/Back-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
