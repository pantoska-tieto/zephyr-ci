//  Common CI parameters

@groovy.transform.Field
def GIT_URL_PROJECT = "https://github.com/pantoska-tieto/zephyr-testing-raspi-app1.git"
@groovy.transform.Field
def GIT_URL_TOOLS = "https://github.com/pantoska-tieto/zephyr-ci.git"
@groovy.transform.Field
def GIT_URL_ZEPH_APP = "https://github.com/pantoska-tieto/zephyr-testing-raspi-app1.git"
@groovy.transform.Field
def BUILD_DIR = "/tmp/zephyr_ci"
@groovy.transform.Field
def TOOLS_DIR = "tools"
@groovy.transform.Field
def FLOWS_DIR = "flows"
@groovy.transform.Field
def DEVOPS_CLONE_CMD = "/usr/bin/git clone ${GIT_URL_TOOLS} ${TOOLS_DIR} --single-branch --depth 1 --no-progress"

// Local test
@groovy.transform.Field
def CI_BUILD_DIR = "/tmp/zephyrproject_ci"

// Applications
@groovy.transform.Field
def PYTHON_PATH = "/usr/bin/python3"

