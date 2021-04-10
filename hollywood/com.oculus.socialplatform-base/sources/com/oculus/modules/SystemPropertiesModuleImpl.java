package com.oculus.modules;

import com.oculus.modules.codegen.SystemPropertiesModule;
import com.oculus.panellib.SystemProperties;

public class SystemPropertiesModuleImpl extends SystemPropertiesModule {
    @Override // com.oculus.modules.codegen.SystemPropertiesModule
    public double getNumberImpl(String str, double d) {
        return SystemProperties.getDouble(str, d);
    }

    @Override // com.oculus.modules.codegen.SystemPropertiesModule
    public String getStringImpl(String str, String str2) {
        return SystemProperties.getString(str, str2);
    }
}
