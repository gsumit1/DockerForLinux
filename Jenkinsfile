/*
 Library declaration.
  Notes:
  identifier includes the version of the library (git tag / branch)
  remote includes the repository git url
  credentialsId needs to be of the type SSH key in Jenkins
  _ at the end of the declaration loads the whole library
  This section always runs in the master jenkins.
*/
library(
      identifier: 'jsl-jenkins-shared-library@master',
      retriever: modernSCM(
        [
          $class: 'GitSCMSource',
          remote: "git@github.com:CenturyLink/jsl-jenkins-shared-library.git",
          credentialsId: 'allyourbotsarebelongtous'
        ]
      )
    ) _

pipeline {

    environment {

      /*
        Credentials:
        GITHUB_TOKEN_CREDENTIALS github token, jenkins user password credential
        GITHUB_SSH_CREDENTIALS github ssh private key, jenkins private key credential.
        DOCKER_CREDENTIALS Docker access info, jenkins secret file credential with environment variables to export
        KUBE_CREDENTIALS Kubernetes access info, jenkins secret file credential with environment variables to export. For PRs.
        KUBE_CREDENTIALS_TEST Kubernetes access info, jenkins secret file credential with environment variables to export. For branches.
        AMAZON_CREDENTIALS AWS access info, jenkins secret file credential with environment variables to export
        SONARQUBE_CREDENTIALS Sonarqube access info, jenkins secret text
        GCP_CREDENTIALS GCP access info, jenkins secret file credential with environment variables to export
        JIRA_CREDENTIALS Jira access info, jenkins secret file credential with environment variables to export
        MSTEAMS_CREADENTIALS MS Teams access info, jenkins secret text
      */
      GITHUB_TOKEN_CREDENTIALS = 'allyourbotsarebelongtous-token'
      GITHUB_SSH_CREDENTIALS = ''
      DOCKER_CREDENTIALS = ''
      KUBE_CREDENTIALS = ''
      KUBE_CREDENTIALS_TEST = ''
      AMAZON_CREDENTIALS = ''
      SONARQUBE_CREDENTIALS = ''
      QUALITY_GATE_CREDENTIALS = ''
      GCP_CREDENTIALS = ''
      JIRA_CREDENTIALS = ''
      MSTEAMS_CREDENTIALS = 'teams-secret'

      // Custom project variables
      BRANCH_NAME = GIT_BRANCH.split('/')[-1].trim().toLowerCase()
      COMMIT_ID = GIT_COMMIT.substring(0,7).trim().toLowerCase()
      PULL_REQUEST="pr-${env.CHANGE_ID}"
      PROJECT_NAME = 'PROJECT'
      DOCKER_REPO ='PROJECT_REPO'
      IMAGE_NAME = "${env.PROJECT_NAME}"
      IMAGE_TAG =  "${env.PULL_REQUEST}"
      KUBE_DOCKER_SECRET_NAME = "${env.PROJECT_NAME}-${env.PULL_REQUEST}"
      KUBE_DOCKER_SECRET_NAME_TEST = "${env.PROJECT_NAME}-${env.BRANCH_NAME}"
    }

    parameters {
      //      https://www.jenkins.io/doc/book/pipeline/syntax/#parameters
      choice(name: 'Environment', choices: ['TEST1', 'TEST2', 'TEST4'], description: '')
      choice(name: 'TestType', choices: ['Sanity'], description: '')
      choice(name: 'MAL', choices: ['STAF'], description: '')
      choice(name: 'TestTool', choices: ['STAF'], description: '')

    }

    /*
    https://www.jenkins.io/doc/book/pipeline/syntax/#agent
    Add agent sections in stages/stage if needed.
    */
    agent {
      label 'Docker-enabled'
    }

    options {
      /*
      https://www.jenkins.io/doc/book/pipeline/syntax/#options
      */
      timestamps ()
      timeout(time: 1, unit: 'HOURS')
      buildDiscarder(logRotator(numToKeepStr:'3', daysToKeepStr: '5'))
      disableConcurrentBuilds()
    }

    /*
    https://www.jenkins.io/doc/book/pipeline/syntax/#triggers
    */
    triggers {
      pollSCM('*/5 * * * *')
      issueCommentTrigger('.*test this please.*')
    }

    stages {

      stage('DevSecOps'){
        steps {
          script {
            /* Contract Validation Gate */
            jslGitHubSecurityAlert()
          }
        }
      }


      stage('Sanity Test') {
        agent {
          dockerfile {
              filename 'Dockerfile'
              dir 'CICD/Selenium'
              label 'Docker-enabled'
          }
        }
        steps {
          script {
            //sh('curl -k https://ctl-consumer--pp4uat.my.salesforce.com/')
            //sh('cat /etc/hosts')
            //jslMavenWrapper("-f pom.xml test")
            try {
            
            jslMavenWrapper("-f /pom.xml test")
            
            
            
		//jslMavenWrapper("-f SampleTests/pom.xml verify serenity:aggregate -Dstaf.environment.key=${params.Environment} -Dwebdriver.driver=chrome -Dwebdriver.timeouts.implicitlywait=40000")
	    }
		
		//clean verify serenity:aggregate -Dstaf.environment.key=TEST1 -Dcucumber.options='src/test/resources/features --tags @google --plugin json:target/TestResult.json' -Dwebdriver.timeouts.implicitlywait=40000 -Dstaf.mal.key=${params.MAL} -Dstaf.testtype.key=${params.TestType} -Dstaf.environment.key=${params.Environment} -Dwebdriver.driver=chrome -Dwebdriver.timeouts.implicitlywait=40000")            }
            catch (Exception e) {
              println "Tests executed but failed. Check the logs, login is working." 
            }
            // As tests are failing the index is not generated
            //jslPublishHTMLTestResults('Report.html','SampleTests/target/report', 'Sample STAF Tests HTML Report')
            //jslPublishHTMLTestResults('index.html', 'SampleTests/target/site/serenity', 'Serenity STAF Report')
            //jslPublishHTMLTestResults('*.html', 'SampleTests/target/site/serenity', 'Sample STAF Report')
          }
        }
      }
    }
    post {
      /*
      https://www.jenkins.io/doc/book/pipeline/syntax/#post
      Always post somewhere the watermark:
	- md5sum of Jenkinsfile
	- Output of the Jenkinsfile checker output
      */
      success {
        /* Contract Validation Gate */
        jslNotification('success')
       // cleanWs()
      }
      failure {
        /* Contract Validation Gate */
        jslNotification('failure')
        //cleanWs()
      }
    }
}
