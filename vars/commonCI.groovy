//  Common CI parameters

@groovy.transform.Field
def GIT_URL_PROJECT = "https://github.com/pantoska-tieto/zephyr-testing-raspi-app1.git"
@groovy.transform.Field
def GIT_URL_TOOLS = "https://github.com/pantoska-tieto/zephyr-ci.git"
@groovy.transform.Field
def GIT_URL_ZEPH_APP = "https://github.com/pantoska-tieto/zephyr-testing-raspi-app1.git"
@groovy.transform.Field
def BUILD_DIR = "/tmp"
@groovy.transform.Field
def DEVOPS_CLONE_CMD = "/usr/bin/git clone ${GIT_URL_TOOLS} tools --single-branch --depth 1 --no-progress"

// Test with local Zephyr development workspace installation
@groovy.transform.Field
def CI_BUILD_DIR = "/home/peter/zephyrproject-ci"
