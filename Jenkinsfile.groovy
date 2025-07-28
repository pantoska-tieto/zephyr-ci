// Import common parameters
@Library("commonCI") _

node {
    stage("Build") {
        echo "Building...."
        echo commonCI.GIT_URL_TOOLS
        // Clone CI repo to workspace
        def cmd = "${commonCI.DEVOPS_CLONE_CMD}"
        sh "cd ${commonCI.BUILD_DIR} && bash -c \"${cmd}\""
    }
    stage("Test") {
        echo "Testing...."
    }
    stage("Deploy") {
        echo "Deploying...."
    }
}