package com.lesfurets.jenkins

import org.junit.Before
import org.junit.Test

import com.lesfurets.jenkins.helpers.cps.BasePipelineTestCPS

class TestSerializationCPS extends BasePipelineTestCPS {



    @Override
    @Before
    void setUp() throws Exception {
        super.setUp()
        def scmBranch = "feature_test"
        binding.setVariable('scm', [
                        $class                           : 'GitSCM',
                        branches                         : [[name: scmBranch]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[
                                                                            credentialsId: 'gitlab_git_ssh',
                                                                            url          : "github.com/lesfurets/pipeline-test-helper.git"
                                                            ]]
        ])
    }

    @Test(expected = Exception.class)
    void testException() throws Exception {
        def script = loadScript('job/serialize.jenkins')
        try {
            script.execute()
        } catch (e) {
            throw e
        } finally {
            printCallStack()
        }
    }

    @Test
    void testSerialization() throws Exception {
        def script = loadScript('job/serializeCPS.jenkins')
        try {
            script.execute()
        } catch (e) {
            throw e
        } finally {
            printCallStack()
        }
    }
}
