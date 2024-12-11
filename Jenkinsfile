pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Cloning the repository
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Build the application using Gradle
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                // Run tests using Gradle
                sh './gradlew test'
            }
        }
    }

    post {
        success {
            echo 'Build and test stages succeeded!'
        }
        failure {
            echo 'Build or test stages failed. Please check the logs.'
        }
    }
}
pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
}
