pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
    agent any
        stages {
            stage('Example') {
                steps {
                    ./gradlew assemble
                    ./gradlew test
                }
            }
        }

}
