import org.gradle.api.JavaVersion

plugins {
    java
    `java-library`
    idea
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "main.java"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.1")
}

javafx {
    version = "20"
    modules = mutableListOf("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics")
}

java {
    if (JavaVersion.current() <= JavaVersion.VERSION_20){
        toolchain.languageVersion.set(JavaLanguageVersion.of(20))
    }
}

tasks.javadoc {
    destinationDir = file("$projectDir/docs/javadoc")
}

application {
    mainClass.set("photos.Photos")
}
