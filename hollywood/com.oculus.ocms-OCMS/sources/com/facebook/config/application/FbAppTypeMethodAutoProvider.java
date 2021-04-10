package com.facebook.config.application;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class FbAppTypeMethodAutoProvider extends AbstractProvider<FbAppType> {
    @Override // javax.inject.Provider
    public FbAppType get() {
        return FbAppTypeModule.provideFbAppType();
    }
}
