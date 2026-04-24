pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/CCogolloH/maxtime_back.git'
        REPO_BRANCH = 'master'
        REMOTE_HOST = '141.148.5.105'
        REMOTE_USER = 'ubuntu'
        REMOTE_PATH = '/home/ubuntu/springboot-docker'
        APP_SERVICE = 'app'
        APP_CONTAINER = 'maxtime_app'
    }

    options {
        disableConcurrentBuilds()
        timeout(time: 20, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Limpiar workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Clonar repositorio') {
            steps {
                git branch: "${env.REPO_BRANCH}",
                        credentialsId: 'github-https',
                        url: "${env.REPO_URL}"
            }
        }

        stage('Verificar archivos') {
            steps {
                sh 'pwd'
                sh 'ls -la'
            }
        }

        stage('Dar permisos al wrapper') {
            steps {
                sh 'chmod +x gradlew || true'
            }
        }

        stage('Compilar con Gradle') {
            steps {
                script {
                    if (fileExists('gradlew')) {
                        sh './gradlew clean build -x test'
                    } else {
                        sh 'gradle clean build -x test'
                    }
                }
            }
        }

        stage('Preparar artefacto') {
            steps {
                sh '''
                    JAR_FILE=$(find build/libs -maxdepth 1 -type f -name "*.jar" ! -name "*-plain.jar" | head -n 1)

                    if [ -z "$JAR_FILE" ]; then
                        echo "No se encontró el jar ejecutable en build/libs"
                        ls -la build/libs || true
                        exit 1
                    fi

                    echo "Jar encontrado: $JAR_FILE"
                    cp "$JAR_FILE" app.jar
                    ls -l app.jar
                '''
                archiveArtifacts artifacts: 'app.jar', fingerprint: true
            }
        }

        stage('Copiar app.jar a servidor') {
            steps {
                sshagent(credentials: ['oracle-vm-deploy']) {
                    sh '''
                        scp -o StrictHostKeyChecking=no app.jar ${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_PATH}/app.jar
                    '''
                }
            }
        }

        stage('Redeploy solo app') {
            steps {
                sshagent(credentials: ['oracle-vm-deploy']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} "
                            cd ${REMOTE_PATH} &&
                            docker compose up -d --no-deps --build ${APP_SERVICE}
                        "
                    '''
                }
            }
        }

        stage('Validar despliegue') {
            steps {
                sshagent(credentials: ['oracle-vm-deploy']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} "
                            docker ps --filter name=${APP_CONTAINER} &&
                            docker logs --tail=50 ${APP_CONTAINER}
                        "
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Despliegue completado correctamente.'
        }
        failure {
            echo 'El pipeline falló. Revisar Console Output.'
        }
    }

}