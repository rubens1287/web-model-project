pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'java'
    }
    stages {
        stage('Code and Dependencies'){
            parallel{
                stage('Checkout Code'){
                steps{
                        git credentialsId: 'f14a9113-76cf-49ea-9aa6-dfe18f79af18', url: 'https://git.gft.com/latam-qa-practice/automation-assets/web-model-project.git'
                    }
                }
                stage('Install Dependencies'){
                    steps{
                        bat 'docker pull elgalu/selenium'
                        bat 'docker pull dosel/zalenium'
                    }
               }
             }
        }
    }
    stage ('Start Zalenium'){
        steps{
            if (isUnix()) {
                sh 'docker run --rm -ti --name zalenium -d -p 4445:4445 -e PULL_SELENIUM_IMAGE=true -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start'
            } else {
                bat 'docker run --rm -ti --name zalenium -d -p 4445:4445 -e PULL_SELENIUM_IMAGE=true -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start'
            }
        }
    }
    stage ('Run Tests'){
        steps{
            script {
                if (isUnix()) {
                    sh 'mvn clean test'
                } else {
                    bat 'mvn clean test'
                }
            }
        }
    }
    stage ('Generate Allure Reports'){
        steps{
            script {
                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
    stage ('Stop Zalenium'){
        steps{
            if (isUnix()) {
                    sh 'docker stop zalenium'
                } else {
                    bat 'docker stop zalenium'
            }
        }
    }
    post {
        always {
            step([$class: 'Publisher', reportFilenamePattern: 'target/xml-junit/*.xml'])
        }
    }
}