pipeline {
	agent any
	libraries {
  		lib('Lib-demo-1@master')
	}
	stages {
		stage('Demo') {
			steps {
				Hello 'Vincent'
			}
		}
	}
}