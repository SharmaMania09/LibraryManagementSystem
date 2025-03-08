pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "lms_app"  // Matches service name in docker-compose.yml
        CONTAINER_NAME = "lms_app"
        MYSQL_CONTAINER = "mysql_library"
        JENKINS_CONTAINER = "jenkins_container"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/SharmaMania09/LibraryManagementSystem.git'  // Update with your actual repo
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Stop & Remove Old Containers') {
            steps {
                sh 'docker-compose down'
            }
        }

        stage('Build & Run Containers') {
            steps {
                sh 'docker-compose up -d --build'
            }
        }

        stage('Verify Running Containers') {
            steps {
                sh 'docker ps -a'
            }
        }

        stage('Cleanup Docker System') {
            steps {
                sh 'docker system prune -f'
            }
        }
    }
}
