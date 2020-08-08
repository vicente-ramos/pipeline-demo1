// def report_file = WORKSPACE + '/coverage.xml'

pipeline {
	agent any
	libraries {
  		lib('Lib-demo-1@docs')
	}
    options {
        // Keep the 10 most recent builds
        buildDiscarder(logRotator(numToKeepStr: '5'))
        timestamps()
    }
	stages {
		stage('Demo') {
			steps {
				hello 'Vincent'
				echo "el reporte ${env.WORKSPACE}"
			}
		}
		stage('Pietto') {
			steps {
		        script
		        {
					//output = upload_coverage('cddafa13d5fe4dbead4819e1a559c144', 'python', 'coverage.xml')
					output = coverage_upload_with_project('Django-Mail-Template', 'vicente-ramos', 'python', "${report_file}")
					echo "The status code was ${output}"
		        }
			}
		}
	}
}