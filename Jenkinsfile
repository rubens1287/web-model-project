pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'java'
    }
    stages {
        stage('Install Dependencies'){
            steps{
                if (isUnix()) {
                    sh 'docker pull elgalu/selenium'
                    sh 'docker pull dosel/zalenium'
                } else {
                     bat 'docker pull elgalu/selenium'
                     bat 'docker pull dosel/zalenium'
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