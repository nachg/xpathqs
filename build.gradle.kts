/*
 * Copyright (c) 2021 Nikita A. Chegodaev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.xpathqs"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.5.0"
    `java-library`
    jacoco
    `maven-publish`
    signing
}

java {
    withJavadocJar()
    withSourcesJar()
}

jacoco {
    toolVersion = "0.8.7"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.0")

    implementation("org.yaml:snakeyaml:1.28")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")

    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23.1")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.xpathqs"
            artifactId = "xpathqs-core"
            version = "0.0.1"

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = true
    }
}