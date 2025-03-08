pipeline 
{
    agent any

    environment 
    {
        DOCKER_IMAGE = "lms_app"  // Matches service name in docker-compose.yml
        CONTAINER_NAME = "lms_app"
        MYSQL_CONTAINER = "mysql_library"
    }

    stages 
    {
        stage('Checkout') 
        {
            steps 
            {
                script 
                {
                    sh 'rm -rf LibraryManagementSystem || true'
                    sh 'git clone https://github.com/SharmaMania09/LibraryManagementSystem.git'
                }
            }
        }

        stage('Build Application') 
        {
            steps 
            {
                sh 'chmod +x mvnw'
                sh './mvnw clean package'
            }
        }

        stage('Stop & Remove Old Containers') 
        {
            steps 
            {
                sh 'docker-compose down'
            }
        }

        stage('Build & Run Containers') 
        {
            steps 
            {
                sh 'docker-compose up -d --build'
            }
        }

        stage('Verify Running Containers') 
        {
            steps 
            {
                sh 'docker ps -a'
            }
        }

        stage('Cleanup Docker System') 
        {
            steps 
            {
                sh 'docker system prune -f'
            }
        }
    }
}
