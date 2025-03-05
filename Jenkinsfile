pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rmoloney96/selenium-automation-framework.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', fingerprint: true
        }
    }
}
