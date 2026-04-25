plugins {
    id("org.graalvm.buildtools.native") version "1.1.0"

    java
    alias(libs.plugins.spring.boot)
    
    alias(libs.plugins.spotless)
}

group = "com.app"
version = "0.0.1-SNAPSHOT"
description = "Product Service for Ecommerce App"

dependencies {
    implementation(platform(libs.spring.boot.bom))

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.flyway)
    implementation(libs.flyway.database.postgresql)
    runtimeOnly(libs.postgresql)
    developmentOnly(platform(libs.spring.boot.bom))
    developmentOnly(libs.spring.boot.devtools)

    implementation("com.app:shared-common:1.0.0-SNAPSHOT")
    implementation("com.app:shared-security:1.0.0-SNAPSHOT")
    implementation(libs.springdoc.openapi.webmvc)

    implementation(libs.mapstruct)
    compileOnly(libs.lombok)
    testCompileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstruct.processor)
    annotationProcessor(libs.lombok.mapstruct.binding)
    testAnnotationProcessor(libs.lombok)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.isFork = true
    options.forkOptions.jvmArgs = (options.forkOptions.jvmArgs ?: mutableListOf()).apply {
        addAll(listOf(
            "--add-opens", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
            "--add-opens", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"
        ))
    }
    options.compilerArgs.addAll(listOf(
        "-Xlint:all", "-Xlint:-serial", "-Xlint:-processing", "-Xdoclint:none"
    ))
}

spotless {
    java {
        googleJavaFormat("1.27.0")
    }
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.bouncycastle" && requested.name.startsWith("bcprov")) {
            useVersion("1.84")
            because("Force upgrade to resolve CVE-2026-0636")
        }
    }
}
