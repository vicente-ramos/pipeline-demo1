#!/usr/bin/env groovy

@Library('jenkins-shared-lib') _

pipeline {
    agent any

    environment {
        PROJECT_NAME = get_project_name()
        PR_BRANCH = ""
    }

    stages{
        stage("git checkout"){
            steps{
                git 'https://github.com/mavenishere/codacy'
            }
           
        }
        stage('Build') {
            steps {
                sh label: 'Debug: Print env vars', script: '''printenv | sort'''
            }
        }        
        stage("info"){
            steps{
                writeFile file: 'info.json', text: create_info()
            }          
        }
        stage("Compress Files"){
            steps{
                script {
                    def statusCode = compress_files("time.txt", "README.md", "tests.py", "awesome/*")
                    // if ( statusCode == 200 ) {
                    //     echo "Passed! Status Code is : ${statusCode}"
                    // } else {
                    //     throw new hudson.AbortException("[FAILURE] Status Code is not value : ${statusCode}")
                    // }   
                    echo statusCode                 
                }
            }          
        }    
        stage("Upload Code coverage to codacy.") {
            steps {
                codacy_coverage("codacy", "guleriagishere", "python", "codacy.json", "d999b59e462847fe8e2caf63f21e57d0")
            }
        }
        stage('codacy') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'token', variable: 'TOKEN')]) {
                        def grade = codacy_report(TOKEN)
                        if ( grade == "B" ) {
                            echo "Grade is of acceptable value : ${grade}"
                        }else {
                            throw new hudson.AbortException("[FAILURE] Grade is below acceptable value : ${grade}")
                        }            
                    }
                }
            }
        }                
    }
    post {
        always{
            slacknotification(currentBuild.currentResult,'vascocapital','jenkins','#jenkins','slack-tokenId-credential',getTestResultsForSlack())
        }
    }    
}