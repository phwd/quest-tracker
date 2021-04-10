package com.oculus.modules;

import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.ServiceModule;
import com.oculus.vrshell.home.HomeApplication;
import com.oculus.vrshell.home.UsedByNative;

public class ServiceModuleImpl extends ServiceModule {
    private static ServiceModule.ServiceType sServiceType;
    private static boolean sServiceTypeSet = false;

    @Override // com.oculus.modules.codegen.ServiceModule
    public void onAccessTokenImpl(String accessToken, Resolver<Void> resolver) {
        HomeApplication.instance.onAccessToken(accessToken);
        resolver.resolve(null);
    }

    public static void setServiceType(ServiceModule.ServiceType type) {
        sServiceTypeSet = true;
        sServiceType = type;
    }

    public static ServiceModule.ServiceType getServiceType() {
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
    @Override // com.oculus.modules.codegen.ServiceModule
    public ServiceModule.ServiceType marshallJSConstantServiceType() {
        return getServiceType();
    }
}
