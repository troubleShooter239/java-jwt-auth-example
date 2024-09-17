plugins {
	java
	pmd
	checkstyle
	id("io.spring.dependency-management") version "1.1.6"
	id("com.github.spotbugs") version "6.0.22"
	id("org.springframework.boot") version "3.3.3"
}

group = "com.rest"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.yaml:snakeyaml:2.1")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	implementation("org.mapstruct:mapstruct:1.6.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("io.swagger.core.v3:swagger-models-jakarta:2.2.15")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta:2.2.15")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.2")
    compileOnly("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

pmd {
    toolVersion = "7.0.0"
    ruleSets = listOf("config/pmd/rulesets.xml")
}

spotbugs {
    ignoreFailures = true
}

checkstyle {
    toolVersion = "10.11.0"
    configFile = file("config/checkstyle/checkstyle.xml")
}

springBoot {
    buildInfo()
}