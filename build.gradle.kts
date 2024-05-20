plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(8)
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
