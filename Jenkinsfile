pipeline {
    agent any

    environment {
        CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver"  // Path inside the container
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Get the current commit hash
                    def currentCommit = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()

                    // Get the last successful commit hash (from git history or previous build)
                    def lastCommit = sh(script: 'git rev-parse HEAD~1', returnStdout: true).trim()

                    // If current commit is the same as the last commit, skip the build
                    if (currentCommit == lastCommit) {
                        currentBuild.result = 'SUCCESS'
                        echo "No changes detected, skipping the build."
                        return // Skip remaining stages
                    }
                }

                // Checkout the code if changes are detected
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
