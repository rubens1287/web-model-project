pipeline {
    agent any
    tools {
            maven 'maven'
            jdk 'JDK 1.8.0_221'
        }
    stages {
        stage('Code and Dependencies'){
            parallel{
                stage('Checkout Code'){
                    steps{
                        git 'https://git.gft.com/latam-qa-practice/automation-assets/web-model-project.git'
                    }
                }
                stage('Install Dependencies'){
                    steps{
                        sh 'docker pull elgalu/selenium'
                        sh 'docker pull dosel/zalenium'
                    }
                }
            }
        }
        stage ('Start Zalenium'){
            steps{
                sh 'docker run --rm -ti --name zalenium -d -p 4444:4444 -e PULL_SELENIUM_IMAGE=true -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start'
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
                    if (isUnix()) {
                        sh 'mvn allure:report'
                    } else {
                        bat 'mvn allure:report'
                    }
                }
            }
        }
        stage ('Upload Allure Reports'){
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
                sh 'docker stop zalenium'
            }
        }
    }
    post {
        always {
            step([$class: 'Publisher', reportFilenamePattern: 'target/xml-junit/*.xml'])
        }
    }
}