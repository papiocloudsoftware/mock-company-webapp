pipeline {

       agent any
       stages {
           stage('Build') {
               steps {
                   ./gradlew assemble
               },
               stage('Test') {
                  steps {
                      ./gradlew test
                  }
           }
       }
}
