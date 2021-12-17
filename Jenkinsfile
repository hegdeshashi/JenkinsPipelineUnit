#!groovy

def deployBranches = [ "master" ]
def phase = "verify"

stage ('Build') {
    node {
        checkout scm
        def branch = scm.branches[0].name
        if (deployBranches.contains(branch)) {
            phase = "deploy"
        }
        echo "Running mvn $phase on branch $branch"
        
     {
       
                    archiveArtifacts 'build/libs/*.jar'
                    archiveArtifacts 'build/libs/*.asc'
                    if (phase == 'deploy') archiveArtifacts 'build/poms/*.xml'
                    if (phase == 'deploy') archiveArtifacts 'build/poms/*.asc'
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                
        }
        step([$class: 'WsCleanup'])
    }
}
