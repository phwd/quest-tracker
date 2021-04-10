package com.oculus.modules;

import com.oculus.modules.codegen.ExploreServiceModule;
import com.oculus.modules.codegen.Resolver;

public class ExploreServiceModuleImpl extends ExploreServiceModule {
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ExploreServiceModule
    public void onAccessTokenImpl(String accessToken, Resolver<Void> resolver) {
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ExploreServiceModule
    public ExploreServiceModule.ServiceType marshallJSConstantServiceType() {
        return ExploreServiceModule.ServiceType.EXPLORE;
    }
}
