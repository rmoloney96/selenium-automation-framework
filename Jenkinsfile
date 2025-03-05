pipeline {
    agent any

    environment {
        CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver"  // Path inside the container
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rmoloney96/selenium-automation-framework.git'
            }
        }

        stage('Setup Java & Dependencies') {
            steps {
                sh 'java -version'  // Check Java version
                sh 'mvn --version'  // Check Maven version
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test -Dwebdriver.chrome.driver=$CHROME_DRIVER_PATH'
            }
        }

        stage('Post-Test Actions') {
            steps {
                junit '**/target/surefire-reports/*.xml'  // Archive test results
            }
        }
    }
}
