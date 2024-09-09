pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
    agent any
       stages {
               stage('Build') {
                   steps {
                       // Run the Gradle build
                       sh './gradlew clean assemble'
                   }
                   post {
                       success {
                           echo 'Build completed successfully.'
                       }
                       failure {
                           echo 'Build failed!'
                       }
                   }
               }

               stage('Test') {
                   steps {
                       // Run the Gradle tests
                       sh './gradlew test'
                   }
                   post {
                       success {
                           echo 'Tests passed.'
                       }
                       failure {
                           echo 'Tests failed!'
                       }
                   }
               }
           }

           post {
               always {
                   // Archive the build/test results or send notifications here
                   echo 'Pipeline completed. Check for success or failure.'
               }
           }
}
