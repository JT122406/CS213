plugins {
    java
    `java-library`
    idea
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "main.java"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.0")
}

javafx {
    version = "19.0.2.1"
    modules = mutableListOf("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

application {
    mainClass.set("SongLib")
}