plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.nexus.publish)
    `java-library`
    `maven-publish`
    signing
}

group = "org.eljabali.sami.javatimefun"
version = "4.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testRuntimeOnly(libs.junit.launcher)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks{
    register<Jar>("dokkaJar") {
        from(dokkaHtml)
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
    }
    withType<Jar> {
        metaInf.with(
            copySpec {
                from("${project.rootDir}/LICENSE")
            }
        )
    }
}

signing {
    useInMemoryPgpKeys(
        System.getProperty("GPG_PRIVATE_KEY"),
        System.getProperty("GPG_PRIVATE_PASSWORD")
    )
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set(project.name)
                description.set("java.time Kotlin extension functions library.")
                url.set("https://github.com/sami-eljabali/java-time-fun")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/sami-eljabali/java-time-fun/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("seljabali")
                        name.set("Sami Eljabali")
                        email.set("sami@eljabali.org")
                        url.set("sami.eljabali.org")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/seljabali/java-time-fun.git")
                    developerConnection.set("scm:git:ssh://github.com/seljabali/java-time-fun.git")
                    url.set("https://github.com/sami-eljabali/java-time-fun/tree/main")
                }
            }
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
            from(components["java"])
            artifacts {
                artifact(tasks["dokkaJar"])
                artifact(tasks.kotlinSourcesJar) {
                    classifier = "sources"
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(System.getProperty("SONATYPE_USERNAME"))
            password.set(System.getProperty("SONATYPE_PASSWORD"))
        }
    }
}
