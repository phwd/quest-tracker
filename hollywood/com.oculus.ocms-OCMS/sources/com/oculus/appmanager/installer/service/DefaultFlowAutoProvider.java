package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DefaultFlowAutoProvider extends AbstractProvider<DefaultFlow> {
    @Override // javax.inject.Provider
    public DefaultFlow get() {
        return new DefaultFlow(this);
    }
}
