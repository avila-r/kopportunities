import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "com.avila"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    /*
     * Spring DATA JPA (Hibernate)
     */
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    /*
     * Spring Web
     */
    implementation("org.springframework.boot:spring-boot-starter-web")

    /*
     * Docs
     */
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    /*
     * PostgreSQL
     */
    runtimeOnly("org.postgresql:postgresql")

    /*
     * Test Environment (JUnit Jupiter)
     */
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("com.h2database:h2")

    /*
     * Misc
     */
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.arrow-kt:arrow-core:1.0.1")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("com.avila.kopportunities.KopportunitiesApplicationKt")
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "com.avila.kopportunities.KopportunitiesApplicationKt"
    }
}