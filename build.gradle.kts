plugins {
    kotlin("jvm") version "1.9.10"
    id("org.sonarqube") version "4.4.0.3356"
    jacoco
}

group = "de.djetzen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.24.2")
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    implementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
    }
}


sonarqube {
    properties {
        property("sonar.projectKey", "djetzen_adventofcode2021")
        property("sonar.organization", "djetzen")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}