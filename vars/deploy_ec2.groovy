#!/usr/bin/env groovy
def call()
{
    def dockerCmd = "docker run -p 8081:8000 -d ${env.IMG_TAG}"
    sshagent(['ec2-shh-key']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.232.157.100 '${dockerCmd}'"
    }
}