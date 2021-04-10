package com.facebook.config.application;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Boolean_com_facebook_common_build_IsInternalBuildMethodAutoProvider extends AbstractProvider<Boolean> {
    @Override // javax.inject.Provider
    public Boolean get() {
        return FbAppTypeModule.provideIsInternalBuild();
    }
}
