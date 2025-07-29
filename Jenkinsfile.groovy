// Import common parameters
@Library("commonCI")
import groovy.json.*

flows = commonCI.BUILD_DIR + "/" + commonCI.TOOLS_DIR + "/" + commonCI.FLOWS_DIR
build_dir = commonCI.BUILD_DIR
python = commonCI.PYTHON_PATH


def ci_build() {
    def build_script = "${flows}/ci_build.py"
    echo "CI workspace for TOOLS: " + commonCI.BUILD_DIR + "/" + commonCI.TOOLS_DIR
    // Clone TOOLS repo to workspace
    def cmd = "${commonCI.DEVOPS_CLONE_CMD}"
    sh "cd ${commonCI.BUILD_DIR} && bash -c \"${cmd}\""
    // Invoke external python script
    def cmd2 = "${python} ${build_script}"
    def result = sh "bash -c \"${cmd2}\""

    // Initiate Zephyr project
    sh """
    cd ${commonCI.CI_BUILD_DIR}
    . ${commonCI.CI_BUILD_DIR}/.venv/bin/activate
    west init -m ${commonCI.GIT_URL_ZEPH_APP} --mr main customer-application1
    cd ${commonCI.CI_BUILD_DIR}/customer-application1 && west update
    """
}

def ci_test() {
    def test_script = "${flows}/ci_test.py"
    // Invoke external python script
    def cmd = "${python} ${test_script}"
    def result = sh "bash -c \"${cmd}\""
}

def ci_deploy() {
    def deploy_script = "${flows}/ci_deploy.py"
    // Invoke external python script
    def cmd = "${python} ${deploy_script}"
    def result = sh "bash -c \"${cmd}\""
}

def ci_report() {
    def report_script = "${flows}/ci_report.py"
    // Invoke external python script
    def cmd = "${python} ${report_script}"
    def result = sh "bash -c \"${cmd}\""
}

node {
    stage("Build") {
        echo "Building Zephyr RTOS application workspace..."
        sh "mkdir -p ${build_dir}"
        try {
                ci_build()
            } catch(err) {
                echo err.getMessage()
            }
    }
    stage("Test") {
        echo "Testing Zephyr RTOS application..."
        try {
                ci_test()
            } catch(err) {
                echo err.getMessage()
            }
    }
    stage("Deploy") {
        echo "Deploying Zephyr RTOS application..."
        try {
                ci_deploy()
            } catch(err) {
                echo err.getMessage()
            }
    }
    stage("Report") {
        echo "Sending a report from CI job..."
        try {
                ci_report()
            } catch(err) {
                echo err.getMessage()
            }
    }

}