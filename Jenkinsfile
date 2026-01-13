pipeline {
    agent any

    // 1. PARAMETERS: Build start karne se pehle choice milegi
    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Testing browser select karein')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run in Headless mode (Invisible)?')
        choice(name: 'ENV', choices: ['QA', 'STAGING', 'PROD'], description: 'Environment select karein')
    }

    triggers {
        // 2. AUTO TRIGGER: GitHub push hote hi job chalegi
        githubPush()
    }

    environment {
        // Global variables for the pipeline
        MAVEN_HOME = tool 'Maven_Home' // Make sure this matches your Jenkins Global Tool Config
    }

    stages {
        stage('Checkout Source') {
            steps {
                script {
                    echo "Checking out code from GitHub..."
                    checkout scm
                }
            }
        }

        stage('Clean & Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Automated Tests') {
            steps {
                // Passing Jenkins parameters directly to Maven
                sh "mvn test -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -Denv=${params.ENV}"
            }
        }
    }

    post {
        always {
            // 3. PROPER REPORTING: Extent Report publish karna
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/SparkReport',
                reportFiles: 'Index.html',
                reportName: 'Extent Spark Report'
            ])
            
            // Old data delete karna taaki space bache
            cleanWs()
        }

        success {
            mail to: 'your-email@example.com',
                 subject: "SUCCESS: DemoQA Build #${env.BUILD_NUMBER} Passed",
                 body: "Test execution was successful. Check reports here: ${env.BUILD_URL}"
        }

        failure {
            mail to: 'your-email@example.com',
                 subject: "FAILURE: DemoQA Build #${env.BUILD_NUMBER} Failed",
                 body: "Tests failed! Please check the logs and screenshots: ${env.BUILD_URL}"
        }
    }
}