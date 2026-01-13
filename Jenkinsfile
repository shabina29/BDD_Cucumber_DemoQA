pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
        retry(1)
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome','firefox','edge'])
        booleanParam(name: 'HEADLESS', defaultValue: true)
        choice(name: 'ENV', choices: ['QA','STAGING'])
    }

    triggers {
        githubPush()
        cron('H 1 * * *') // Nightly regression
    }

    tools {
        maven 'Maven_3'
        // jdk 'JDK_17'  // Uncomment ONLY if configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test Execution') {
            steps {
                sh "mvn test -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -Denv=${params.ENV}"
            }
        }
    }

    post {
        always {
            script {
                junit 'target/surefire-reports/*.xml'

                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'test-output/SparkReport',
                    reportFiles: 'Index.html',
                    reportName: 'Extent Report'
                ])
            }

            cleanWs()
        }

        // Enable email ONLY after SMTP setup
        /*
        success {
            mail to: 'sshahinece@gmail.com',
                 subject: "SUCCESS: Build #${env.BUILD_NUMBER}",
                 body: "Build passed: ${env.BUILD_URL}"
        }

        failure {
            mail to: 'sshahinece@gmail.com',
                 subject: "FAILURE: Build #${env.BUILD_NUMBER}",
                 body: "Build failed: ${env.BUILD_URL}"
        }
        */
    }
}
