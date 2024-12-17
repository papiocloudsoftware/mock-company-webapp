pipeline {
    agent any

    environment {
        GRADLE_USER_HOME = "${WORKSPACE}/.gradle" // Caches Gradle dependencies
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }

        stage('Setup Java and Gradle') {
            steps {
                echo 'Setting up Java and Gradle environment...'
                sh 'java -version'
                sh './gradlew --version'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application...'
                sh './gradlew assemble' // Build the application
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh './gradlew test' // Run the tests
            }
        }
    }

    post {
        success {
            echo 'Build and tests passed successfully!'
        }
        failure {
            echo 'Build or tests failed. Check logs for details.'
        }
    }
}
