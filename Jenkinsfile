pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Runs the build step
                bat 'gradlew.bat assemble'
            }
        }
        stage('Test') {
            steps {
                // Runs the tests
                bat 'gradlew.bat test'
            }
        }
    }
}
