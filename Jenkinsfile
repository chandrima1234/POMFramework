pipeline {
    
    agent any 
    
        stages{
            
            stage("build"){
                steps{
                echo("build")
            }
            }
            
            stage("deploy to dev") {
                steps{
                echo ("deploy to dev")
            }
            }
            
             stage("deploy to QA") {
                 steps{
                echo ("deploy to dev")
                 }
            }
            
            stage("Run regression to automation"){
                steps{
                echo ("Run regression to automation")
                }
            }
            
            stage("deploy to stage") {
                steps{
                echo ("deploy to dev")
                }
            }
            
             stage("Run sanity to automation"){
                 steps{
                echo ("Run regression to automation")
                 }
            }
            
              stage("deploy to prod") {
                  steps{
                echo ("deploy to UAT")
                  }
            }
            
            
            
        }
}