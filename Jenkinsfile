pipeline {
	agent any
	libraries {
  		lib('Lib-demo-1@docs')
	}
	stages {
		stage('Demo') {
			steps {
				hello 'Vincent'
			}
		}
	}
}