#!/usr/bin/env groovy
import com.example.Docker

def call(String newImageTag){
    return new Docker(this).finalGitPush(newImageTag)
}
