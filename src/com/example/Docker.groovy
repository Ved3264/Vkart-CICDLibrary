#!/usr/bin/env groovy
package com.example

class Docker implements  Serializable
{
    def script

    Docker(script){
        this.script = script
    }

    def buildDockerImage(String newImageTag) {
        script.echo "Building Docker image: ${newImageTag}"
        script.sh "docker build -t ${newImageTag} ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'Ved-DockerHub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            // Pass the credentials to the Docker login command safely
            script.sh """
                echo \$PASS | docker login -u \$USER --password-stdin
            """
            script.echo 'Login successful'
        }
    }

    def dockerPush(String newImageTag) {
        script.echo "Pushing Docker image: ${newImageTag}"
        script.sh "docker push ${newImageTag}"
    }

}
