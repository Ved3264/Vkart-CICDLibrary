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

    def finalGitPush() {
            script.withCredentials([script.usernamePassword(credentialsId: 'Ved-git', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                // Git config here for the first time run
                script.sh 'git config --global user.email "jenkins@example.com"'
                script.sh 'git config --global user.name "jenkins"'

                script.sh """ git remote set-url origin https://\${USER}:\${PASS}@github.com/Ved3264/VKart.ecom.git """
                script.sh 'git add .'
                script.sh 'git commit -m "ci: version bump"'
                script.sh 'git push origin HEAD:jenkins'
            }
    }
}
