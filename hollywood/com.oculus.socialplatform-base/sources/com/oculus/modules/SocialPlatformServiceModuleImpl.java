package com.oculus.modules;

import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SocialPlatformServiceModule;

public class SocialPlatformServiceModuleImpl extends SocialPlatformServiceModule {
    public static SocialPlatformServiceModule.ServiceType sServiceType;
    public static boolean sServiceTypeSet;

    public static void setServiceType(SocialPlatformServiceModule.ServiceType serviceType) {
        sServiceTypeSet = true;
        sServiceType = serviceType;
    }

    @Override // com.oculus.modules.codegen.SocialPlatformServiceModule
    public void onAccessTokenImpl(String str, Resolver<Void> resolver) {
        resolver.resolve(null);
    }

    public static SocialPlatformServiceModule.ServiceType getServiceType() {
        if (sServiceTypeSet) {
            return sServiceType;
        }
        throw new RuntimeException("Requesting ServiceType before set.");
    }

    public static String getServiceTypeString() {
        return getServiceType().toString();
    }

    @Override // com.oculus.modules.codegen.SocialPlatformServiceModule
    public SocialPlatformServiceModule.ServiceType marshallJSConstantServiceType() {
        return getServiceType();
    }
}
