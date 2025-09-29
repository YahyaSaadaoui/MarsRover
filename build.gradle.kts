plugins {
    application
    jacoco
    id("org.barfuin.gradle.jacocolog") version "3.1.0"
}

group = "fr.univ_amu.m1info.mars_rover"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.20.0")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}
application {
    mainClass = "fr.univ_amu.m1info.mars_rover.Main"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "fr.univ_amu.m1info.mars_rover.Main"
    }
}