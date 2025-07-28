// Import common parameters
@Library("commonCI") _

node {
    stage("Build") {
        echo "Building Zephyr RTOS application workspace..."
        echo "CI workspace for TOOLS: " + commonCI.GIT_URL_TOOLS
        // Clone CI repo to workspace TOOLS
        def cmd = "${commonCI.DEVOPS_CLONE_CMD}"
        sh "cd ${commonCI.BUILD_DIR} && bash -c \"${cmd}\""
        // Install Application into Zephyr basic installation
        sh "sudo cd ${commonCI.CI_BUILD_DIR}"
        sh "sudo cd .venv && sudo source bin/activate && cd .."
        sh "west init -m ${GIT_URL_ZEPH_APP} --mr main customer-application1"
        sh "sudo cd customer-application1 && west update"
    }
    stage("Test") {
        echo "Testing Zephyr RTOS application..."
    }
    stage("Deploy") {
        echo "Deploying Zephyr RTOS application..."
    }
}