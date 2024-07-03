FROM openjdk:17-oraclelinux7
WORKDIR /usr/src/myapp
COPY . /usr/src/myapp
RUN javac src/main/java/org/example/*.java
EXPOSE 8080
CMD ["sh", "-c", "java -cp src/main/java org.example.TowerDefenseGame && exec bash"]
