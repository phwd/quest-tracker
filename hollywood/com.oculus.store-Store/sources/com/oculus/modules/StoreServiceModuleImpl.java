package com.oculus.modules;

import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.StoreServiceModule;

public class StoreServiceModuleImpl extends StoreServiceModule {
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.StoreServiceModule
    public void onAccessTokenImpl(String accessToken, Resolver<Void> resolver) {
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.StoreServiceModule
    public StoreServiceModule.ServiceType marshallJSConstantServiceType() {
        return StoreServiceModule.ServiceType.STORE;
    }
}
