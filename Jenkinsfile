pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "lms_app"
        CONTAINER_NAME = "lms_app"
        MAVEN_WRAPPER = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\LMS-CICD\\LibraryManagementSystem\\mvnw.cmd'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    bat '''
                        if exist LibraryManagementSystem (rmdir /s /q LibraryManagementSystem)
                        git clone https://github.com/SharmaMania09/LibraryManagementSystem.git
                    '''
                }
            }
        }

        stage('Build Application') {
            steps {
                bat '''
                    echo "Building application..."
                    cd LibraryManagementSystem
                    call mvnw.cmd clean package
                '''
            }
        }

        stage('Run App Container') {
            steps {
                bat '''
                    echo "Starting Spring Boot App using Docker Compose..."
                    cd LibraryManagementSystem
                    docker-compose up -d
                '''
            }
        }
    }
}


/*

pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "lms_app"
        CONTAINER_NAME = "lms_app"
        MYSQL_CONTAINER = "mysql_library"
        MAVEN_WRAPPER = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\LMS-CICD\\LibraryManagementSystem\\mvnw.cmd'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    bat '''
                        if exist LibraryManagementSystem (rmdir /s /q LibraryManagementSystem)
                        git clone https://github.com/SharmaMania09/LibraryManagementSystem.git
                    '''
                }
            }
        }

        stage('Start MySQL Container') {
            steps {
                bat '''
                    echo "Starting MySQL container..."
                    cd LibraryManagementSystem
                    docker-compose up -d mysql
                '''
            }
        }

        stage('Wait for MySQL') {
            steps {
                script {
                    def retries = 10
                    def waitTime = 5
                    def success = false
                    for (int i = 1; i <= retries; i++) {
                        def status = bat(returnStatus: true, script: '''
                            docker exec mysql_library mysqladmin ping -h "127.0.0.1" --silent
                        ''')
                        if (status == 0) {
                            echo "✅ MySQL is ready!"
                            success = true
                            break
                        }
                        echo "⏳ Waiting for MySQL to be ready... ($i/$retries)"
                        sleep waitTime
                    }
                    if (!success) {
                        error("❌ MySQL did not start in time. Failing the build.")
                    }
                }
            }
        }


        stage('Build Application') {
            steps {
                bat '''
                    echo "Building application..."
                    cd LibraryManagementSystem
                    call mvnw.cmd clean package
                '''
            }
        }

        stage('Run App Container') {
            steps {
                bat '''
                    echo "Starting Spring Boot App using Docker Compose..."
                    cd LibraryManagementSystem
                    docker-compose up -d app
                '''
            }
        }
    }
}

*/
