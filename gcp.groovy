pipeline {
  agent any
  environment {
    CLOUDSDK_CORE_PROJECT='burner-noohauqu'
  }
  stages {
    stage('test') {
      steps {
        withCredentials([file(credentialsId: 'burner-noohauqu', variable: 'burner-noohauqu')]) {
          sh '''
            gcloud version
            gcloud auth activate-service-account --key-file="/secure/burner-noohauqu-8b6401def62d.json"
            gcloud compute instance-groups managed create-instance test-node-frontend --zone=us-central1-a --instance=test-vm1
          '''
        }
      }
    }
  }
}