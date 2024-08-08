#!/usr/bin/env groovy

def call()
{
    sh 'pip install -r requirements.txt'
    echo 'build requirement successfully'
}
