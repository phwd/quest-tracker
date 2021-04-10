package com.facebook.inject.rootmodule;

import com.facebook.common.process.PrivateProcessName;
import com.facebook.common.process.ProcessName;
import com.facebook.inject.PrivateModule;
import com.facebook.inject.rootmodule.defaultmodule.___DEFAULT___ProcessRootModule;

public class RootModuleProvider {
    private static final String ROOT_MODULE_PREFIX = "generated_rootmodule.";
    private static final String ROOT_MODULE_SUFFIX = "ProcessRootModule";

    public static PrivateModule get(PrivateProcessName privateProcessName) {
        return new ___DEFAULT___ProcessRootModule();
    }

    public static PrivateModule get(ProcessName processName) {
        PrivateProcessName privateProcessName = processName.getPrivateProcessName();
        if (privateProcessName != null) {
            return get(privateProcessName);
        }
        throw new IllegalStateException("processName has null PrivateProcessName - cannot infer root module");
    }
}
