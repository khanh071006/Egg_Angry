plugins {
    kotlin("jvm") version "2.1.10"
    id("com.utopia-rise.godot-kotlin-jvm") version "0.14.3-4.5.1"
    java
}

repositories {
    mavenCentral()
}

kotlin { jvmToolchain(21) }
java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

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