package com.oculus.modules;

import com.oculus.modules.codegen.SystemPropertiesModule;
import com.oculus.panellib.SystemProperties;

public class SystemPropertiesModuleImpl extends SystemPropertiesModule {
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemPropertiesModule
    public String getStringImpl(String key, String defaultValue) {
        return SystemProperties.getString(key, defaultValue);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemPropertiesModule
    public double getNumberImpl(String key, double defaultValue) {
        return SystemProperties.getDouble(key, defaultValue);
    }
}
