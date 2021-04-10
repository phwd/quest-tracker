package com.oculus.library.utils;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AppSharingUtilsAutoProvider extends AbstractProvider<AppSharingUtils> {
    @Override // javax.inject.Provider
    public AppSharingUtils get() {
        return new AppSharingUtils(this);
    }
}
