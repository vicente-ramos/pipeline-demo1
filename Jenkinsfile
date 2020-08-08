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
				def report_file = env.WORKSPACE + '/coverage.xml'
				echo "el reporte ${report_file}"
			}
		}
		stage('Pietto') {
			steps {
		        script
		        {
		        	def report_file = env.WORKSPACE + '/coverage.xml'
					//output = upload_coverage('cddafa13d5fe4dbead4819e1a559c144', 'python', 'coverage.xml')
					output = coverage_upload_with_project('Django-Mail-Template', 'vicente-ramos', 'python', "${report_file}")
					echo "The status code was ${output}"
		        }
			}
		}
	}
}