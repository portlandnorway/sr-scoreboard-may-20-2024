import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
  kotlin("jvm") version "1.9.0"
  application
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation(kotlin("test"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events("passed", "skipped", "failed")
    exceptionFormat = TestExceptionFormat.FULL
    showStandardStreams = true
  }
}

kotlin {
  jvmToolchain(17)
}

application {
  mainClass.set("MainKt")
}

sourceSets {
  main {
    kotlin {
      srcDirs("src/main/kotlin")
    }
  }
  test {
    kotlin {
      srcDirs("src/test/kotlin")
    }
  }
}

