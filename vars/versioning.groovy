#!/usr/bin/env groovy

def call(){
    echo 'start versioning'
    def filePath = 'version.txt'
    def versionFile = readFile filePath
    // Split the version into major, minor, and patch parts
    def versionParts = versionFile.tokenize('.')
    def major = versionParts[0].toInteger()
    def minor = versionParts[1].toInteger()
    def patch = versionParts[2].toInteger()

    // Increment the patch version
    patch += 1

    // Create the new version
    def newVersion = "${major}.${minor}.${patch}"
    def dockerImage = 'ved1111/django-vkart-practise'
    // Build and tag the new Docker image
    def newImageTag = "${dockerImage}:${newVersion}"

    env.IMG_TAG=newImageTag

    writeFile file: filePath,text: newVersion
    echo "Updated version to: ${newVersion}"
    echo'end versioning'
}