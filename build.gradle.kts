plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    java
}

group = "net.uun.java"
version = "0.0.1-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_17
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.security:spring-security-core")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql")

    val jpaStreamerVersion = "1.0.2"
    implementation("com.speedment.jpastreamer:jpastreamer-core:$jpaStreamerVersion")
    annotationProcessor("com.speedment.jpastreamer:fieldgenerator-standard:$jpaStreamerVersion")

}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
            srcDir("target/generated-sources/annotations")
        }
    }
}
