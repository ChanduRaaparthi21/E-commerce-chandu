pipeline 
{
    agent any

    stages {

        stage('clone repository') {

            steps {

                checkout scm
            }
        }

stage('Build') {

            steps {

                sh 'mvn -B -DskipTests clean package'
            }
        }

stage('unit test'){
steps{
           sh 'mvn test'
}


        post{
                      always{

    junit '**/target/surefire-reports/TEST-*.xml'     

}
}
                       }

stage('Deploy') {

            steps {

                   sh 'aws s3 sync ./build s3://my.bucket --only-show-errors'
            }
        }

    stage ('end to end testing'){
                      steps{
                          sh(script: 'NO_COLOR=1 node_modules/.bin/cypress run || true')
}                          
                  }

stage('prod deployment'){
              steps{
                              sh './deploy production'
}  
}

stage ('email'){
    steps{

always {
        emailext body: 'pipeline', subject: 'mailindication', to: 'vamsikrishnachintada99@gmail.com'
} 
}
}
}
}
