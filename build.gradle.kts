plugins {
    kotlin("jvm") version "2.1.10"
    id("com.utopia-rise.godot-kotlin-jvm") version "0.14.3-4.5.1"
    java
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// Đoạn này ép cứng compiler Kotlin xuất ra Java 17 chuẩn Kotlin DSL
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

godot {
    isRegistrationFileGenerationEnabled.set(true)
}

tasks.register<Exec>("runGame") {
    group = "godot"
    description = "Builds the project and runs Godot"
    dependsOn("build")
    workingDir = file(".")
    commandLine("C:\\Users\\ADMIN\\Downloads\\godot-kotlin-jvm_editor_windows_x86_64_debug_0.14.3-4.5.1\\godot.windows.editor.x86_64.jvm.0.14.3.exe")
}
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}