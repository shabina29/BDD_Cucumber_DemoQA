pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
        retry(1)
    }

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Select browser'
        )
        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run in headless mode'
        )
        choice(
            name: 'ENV',
            choices: ['QA', 'STAGING'],
            description: 'Select environment'
        )
    }

    triggers {
        githubPush()          // Auto trigger on GitHub push
        cron('0 23 * * *')    // Nightly regression at 11:00 PM
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

            echo 'Publishing Cucumber HTML Report'

            // Publish Cucumber HTML report
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'cucumber-reports.html',
                reportName: 'Cucumber HTML Report'
            ])

            // Publish TestNG results
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

            // Clean workspace after build
            cleanWs()
        }

        success {
            mail to: 'shahinshabina93@gmail.com',
                 subject: "✅ SUCCESS: Jenkins Build #${env.BUILD_NUMBER}",
                 body: """
Hi Shabina,

Your Jenkins build completed SUCCESSFULLY.

Build URL:
${env.BUILD_URL}

Cucumber HTML Report:
${env.BUILD_URL}Cucumber_20HTML_20Report/

Regards,
Jenkins
"""
        }

        failure {
            mail to: 'shahinshabina93@gmail.com',
                 subject: "❌ FAILURE: Jenkins Build #${env.BUILD_NUMBER}",
                 body: """
Hi Shabina,

Your Jenkins build FAILED.

Build URL:
${env.BUILD_URL}

Please check logs and the HTML report.

Regards,
Jenkins
"""
        }
    }
}

