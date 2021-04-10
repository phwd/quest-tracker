package com.oculus.config;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ConfigControllerAutoProvider extends AbstractProvider<ConfigController> {
    @Override // javax.inject.Provider
    public ConfigController get() {
        return new ConfigController(this);
    }
}
