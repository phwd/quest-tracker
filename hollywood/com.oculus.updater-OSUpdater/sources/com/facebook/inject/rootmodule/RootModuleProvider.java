package com.facebook.inject.rootmodule;

import com.facebook.common.process.PrivateProcessName;
import com.facebook.inject.PrivateModule;
import com.facebook.inject.rootmodule.defaultmodule.___DEFAULT___ProcessRootModule;

public class RootModuleProvider {
    public static PrivateModule get(PrivateProcessName privateProcessName) {
        return new ___DEFAULT___ProcessRootModule();
    }
}
