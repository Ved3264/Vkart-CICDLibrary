package com.example

class Docker implements Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "Start building app"
        script.sh "docker build -t $imageName ."
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

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
        script.echo 'Push successful'
    }
}
