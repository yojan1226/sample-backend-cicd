pipeline {
    agent any

    stages {

        stage('Validate Parameters') {
            steps {
                script {

                    def repositories = [
                        'sample-backend-cicd': 'https://github.com/yojan1226/sample-backend-cicd.git'
                    ]

                    if (!repositories.containsKey(params.REPOSITORY)) {
                        error "Invalid repository selected: ${params.REPOSITORY}"
                    }

                    if (!params.BRANCH?.trim()) {
                        error "Branch must be selected."
                    }

                    if (!params.CHANGE_DESCRIPTION?.trim()) {
                        error "Change description cannot be empty."
                    }

                    env.REPOSITORY_URL = repositories[params.REPOSITORY]

                    currentBuild.description =
                        "${params.REPOSITORY} | ${params.BRANCH} | ${params.CHANGE_DESCRIPTION}"

                    echo "Repository       : ${params.REPOSITORY}"
                    echo "Repository URL   : ${env.REPOSITORY_URL}"
                    echo "Branch           : ${params.BRANCH}"
                    echo "Change Description: ${params.CHANGE_DESCRIPTION}"
                }
            }
        }

        stage('Checkout') {
            steps {
                deleteDir()

                checkout scmGit(
                    branches: [[name: "refs/heads/${params.BRANCH}"]],
                    userRemoteConfigs: [[
                        url: env.REPOSITORY_URL
                    ]]
                )
            }
        }

        stage('Maven Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:5.8.0.7211:sonar \
                        -Dsonar.projectKey=sample-backend-cicd \
                        -Dsonar.projectName=sample-backend-cicd
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Trivy Security Scan') {
            steps {
                sh 'trivy fs .'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
    }
}