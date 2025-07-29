// Import common parameters
@Library("commonCI") _

flows = commonCI._DIR + "/" + commonCI.TOOLS_DIR + "/" + commonCI.FLOWS_DIR
build_dir = commonCI.BUILD_DIR
python = commonCI.PYTHON_PATH


def ci_build() {
    def build_script = "${flows}/ci_build.py"
    echo "CI workspace for TOOLS: " + commonCI.BUILD_DIR + "/" + commonCI.TOOLS_DIR
    // Clone CI repo to workspace TOOLS
    def cmd = "${commonCI.DEVOPS_CLONE_CMD}"
    sh "cd ${commonCI.BUILD_DIR} && bash -c \"${cmd}\""
    def cmd2 = "${python} ${build_script}"
    def result = sh "bash -c \"${cmd2}\""

    sh """
    cd ${commonCI.CI_BUILD_DIR}
    . ${commonCI.CI_BUILD_DIR}/.venv/bin/activate
    west init -m ${GIT_URL_ZEPH_APP} --mr main customer-application1
    cd ${commonCI.CI_BUILD_DIR}/customer-application1 && west update
    """

    // sh "bash ${commonCI.CI_BUILD_DIR}/venv-activate.sh"
    // sh "cd ${commonCI.CI_BUILD_DIR} && west init -m ${GIT_URL_ZEPH_APP} --mr main customer-application1"
    // sh "cd ${commonCI.CI_BUILD_DIR}/customer-application1 && west update"
}

node {
    stage("Build") {
        echo "Building Zephyr RTOS application workspace..."
        sh "mkdir ${build_dir}"
        try {
                ci_build()
            } catch(err) {
                echo err.getMessage()
            }
        // sh ". ./bin/activate && cd ${commonCI.CI_BUILD_DIR}"
        
    }
    stage("Test") {
        echo "Testing Zephyr RTOS application..."
    }
    stage("Deploy") {
        echo "Deploying Zephyr RTOS application..."
    }
}