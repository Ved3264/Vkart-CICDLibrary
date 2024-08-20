#!/usr/bin/env groovy
def call()
{
    withCredentials([usernamePassword(credentialsId: 'vedgit', passwordVariable: 'TOKEN', usernameVariable: 'USER')]) {
        // git config here for the first time run
        sh 'git config --global user.email "devlopment@gmail.com"'
        sh 'git config --global user.name "devlopement"'

        // Using the credentials securely
        sh "git remote set-url origin https://${USER}:${TOKEN}@github.com/Ved3264/VKart.ecom.git"
        sh 'git add .'
        sh 'git commit -m "ci: version bump"'
        sh 'git push origin HEAD:jenkins'
    }
}
