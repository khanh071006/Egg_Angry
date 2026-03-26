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