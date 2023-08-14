val project_version: String by project
val jdk_version: String by project
val kafka_version: String by project

plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "studio.hcmc"
version = project_version

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

kotlin {
    jvmToolchain(jdk_version.toInt())
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "studio.hcmc"
            artifactId = project.name
            version = project_version
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:$kafka_version")
    implementation("org.apache.kafka:kafka-streams:$kafka_version")
}