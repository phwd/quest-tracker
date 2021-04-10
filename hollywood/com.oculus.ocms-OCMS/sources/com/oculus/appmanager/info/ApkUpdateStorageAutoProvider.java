package com.oculus.appmanager.info;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateStorageAutoProvider extends AbstractProvider<ApkUpdateStorage> {
    @Override // javax.inject.Provider
    public ApkUpdateStorage get() {
        return new ApkUpdateStorage(this);
    }
}
