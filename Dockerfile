FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app/WorkSync
RUN mkdir -p /app/WorkSync/db

# Copiar todo el código fuente del proyecto
COPY autenticar ./ 

# Construir proyecto
RUN mvn -B -DskipTests=true clean package

FROM eclipse-temurin:21-jre
WORKDIR /app/WorkSync
RUN mkdir -p /app/WorkSync/db

# Copiar el archivo jar compilado
COPY --from=build /app/WorkSync/target/*.jar app.jar

# Copiar archivo de base de datos SQLite al contenedor
COPY autenticar/db/usuarios.db /app/WorkSync/db/usuarios.db

CMD ["sh","-c","mkdir -p /app/autenticar/db && java -Dserver.port=${PORT:-4002} -jar /app/WorkSync/app.jar"]

