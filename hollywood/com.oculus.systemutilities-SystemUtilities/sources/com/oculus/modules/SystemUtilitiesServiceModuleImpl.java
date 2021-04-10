package com.oculus.modules;

import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SystemUtilitiesServiceModule;
import com.oculus.systemutilities.UsedByNative;

public class SystemUtilitiesServiceModuleImpl extends SystemUtilitiesServiceModule {
    private static SystemUtilitiesServiceModule.ServiceType sServiceType;
    private static boolean sServiceTypeSet = false;

    @Override // com.oculus.modules.codegen.SystemUtilitiesServiceModule
    public void onAccessTokenImpl(String accessToken, Resolver<Void> resolver) {
        resolver.resolve(null);
    }

    public static void setServiceType(SystemUtilitiesServiceModule.ServiceType type) {
        sServiceTypeSet = true;
        sServiceType = type;
    }

    public static SystemUtilitiesServiceModule.ServiceType getServiceType() {
        if (sServiceTypeSet) {
            return sServiceType;
        }
        throw new RuntimeException("Requesting ServiceType before set.");
    }

    @UsedByNative
    public static String getServiceTypeString() {
        return getServiceType().toString();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemUtilitiesServiceModule
    public SystemUtilitiesServiceModule.ServiceType marshallJSConstantServiceType() {
        return getServiceType();
    }
}
