pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application...'
                // TODO: Add build command
                sh './gradlew assemble'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // TODO: Add test command
                sh './gradlew test'
            }
        }
    }

    post {
        success {
            echo '✅ Build and Tests passed!'
        }
        failure {
            echo '❌ Build or Tests failed!'
        }
    }
}
