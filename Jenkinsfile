pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   pipeline {
       agent any

       stages {
           stage('Build') {
               steps {
                   script {
                       // TODO: Add build command to assemble the project
                       sh './gradlew assemble'
                   }
               }
           }

           stage('Test') {
               steps {
                   script {
                       // TODO: Add test command to run the tests
                       sh './gradlew test'
                   }
               }
           }
       }



}
