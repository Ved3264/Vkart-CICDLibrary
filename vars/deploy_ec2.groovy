#!/usr/bin/env groovy
def call()
{
    def dockerCmd = "docker run -p 8081:8000 -d ${env.IMG_TAG}"
    sshagent(['ec2-shh-key']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@44.202.81.47 '${dockerCmd}'"
    }
}