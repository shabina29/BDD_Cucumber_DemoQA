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
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test Execution') {
            steps {
                bat "mvn test -Dbrowser=%BROWSER% -Dheadless=%HEADLESS% -Denv=%ENV%"
            }
        }
    }

    post {
        always {
            script {
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

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
    }
}
