plugins {
	java
	id("org.springframework.boot") version "4.0.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "8.4.0"
	`maven-publish`
}

group = "com.common"
version = "0.0.1-SNAPSHOT"
description = "Product Service for Ecommerce App"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

spotless {
	java {
		googleJavaFormat()
	}
}

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-flyway")
	runtimeOnly("org.postgresql:postgresql")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("com.common:shared-common:1.0.0-SNAPSHOT")
	implementation("com.common:shared-security:1.0.0-SNAPSHOT")

	implementation("org.mapstruct:mapstruct:1.6.3")
	compileOnly("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named("check") {
  dependsOn("spotlessCheck")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-serial", "-Xlint:-processing"))
}

val stopApp by tasks.registering(Exec::class) {
  group = "application"
  description = "Stops any running application processes for this service."
  commandLine(
      "sh",
      "-c",
      "ps aux | grep 'bootRun' | grep '${project.name}' | grep -v grep | awk '{print $2}' | xargs kill -9 || true")
}

tasks.named("build") { dependsOn(stopApp) }
