pipeline {
    agent any

    stages {
        stage('Setup Node.js with NVM') {
            steps {
                sh '''
                    # Install NVM
                    curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash

                    # Load NVM (ensure it matches your shell environment)
                    export NVM_DIR="$HOME/.nvm"
                    [ -s "$NVM_DIR/nvm.sh" ] && . "$NVM_DIR/nvm.sh"

                    # Install and use Node.js 16
                    nvm install 16
                    nvm use 16

                    # Install Yarn globally
                    npm install -g yarn

                    # Verify installation
                    node -v
                    npm -v
                    yarn --version
                '''
            }
        }

        stage('Build') {
            steps {
                echo 'Starting Build'
                sh '''
                    # Reinitialize NVM
                    export NVM_DIR="$HOME/.nvm"
                    [ -s "$NVM_DIR/nvm.sh" ] && . "$NVM_DIR/nvm.sh"

                    # Use Node.js version installed via NVM
                    nvm use 16

                    # Run Gradle build
                    ./gradlew assemble
                '''
            }
        }

        stage('Unit Test') {
            steps {
                echo 'Starting Tests'
                sh './gradlew test'
            }
        }
    }

    post {
        failure {
            echo 'The pipeline failed'
        }
        success {
            echo 'The pipeline completed successfully'
        }
    }
}