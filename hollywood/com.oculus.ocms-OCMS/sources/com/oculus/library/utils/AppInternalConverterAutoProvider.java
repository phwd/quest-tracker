package com.oculus.library.utils;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AppInternalConverterAutoProvider extends AbstractProvider<AppInternalConverter> {
    @Override // javax.inject.Provider
    public AppInternalConverter get() {
        return new AppInternalConverter(this);
    }
}
