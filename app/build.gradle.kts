plugins {
  kotlin("jvm") version "1.9.20"
  kotlin("plugin.spring") version "1.9.20"
  id("java-test-fixtures")
}

apply(plugin = "project-report")

dependencies {
  implementation(project(":domain"))
  implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("io.r2dbc:r2dbc-postgresql:0.8.2.RELEASE")
  implementation("commons-beanutils:commons-beanutils:1.9.4")
  implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")

  // #### RESILIENCE
  implementation("io.github.resilience4j:resilience4j-spring-boot2:2.0.0")
  implementation("io.github.resilience4j:resilience4j-kotlin:2.0.0")
  runtimeOnly("org.aspectj:aspectjweaver:1.9.19")
  // ##

  implementation("org.apache.commons:commons-text:1.10.0")
  testRuntimeOnly("com.h2database:h2")
  testRuntimeOnly("io.r2dbc:r2dbc-h2")

  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:2.2.5.RELEASE")

  testImplementation(testFixtures(project(":domain")))
  testFixturesImplementation(testFixtures(project(":domain")))
  testImplementation(testFixtures(project(":app")))
}

tasks.test {
  environment("APPLICATION", "project-luna-backend")
}

tasks {
  bootJar {
    destinationDirectory.set(file("${rootProject.buildDir}/libs"))
  }
  bootRun {
    environment.putIfAbsent("APPLICATION", "project-luna-backend")
  }
}