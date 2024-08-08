#!/usr/bin/env groovy
package com.example

class Docker implements  Serializable
{
    def script

    Docker(script){
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo "start build app"
        script.sh "docker build -t $imageName ."
    }

    def dockerLogin(){
        withCredentials([usernamePassword(credentialsId: 'Ved-DockerHub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.echo 'login successfull'
        }
    }

    def dockerPush(String imageName)
    {
        script.sh "docker push $imageName"
        script.echo 'build successfully'
    }
}
