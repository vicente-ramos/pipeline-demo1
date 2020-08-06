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
		stage('Pietto') {
			steps {
		        script
		        {
					upload_coverage('cddafa13d5fe4dbead4819e1a559c144', 'python', 'coverage.xml')
					echo "The status code was "
		        }
			}
		}
	}
}