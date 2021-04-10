package com.oculus.common.packagescache;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class PackageManagerUtilsAutoProvider extends AbstractProvider<PackageManagerUtils> {
    @Override // javax.inject.Provider
    public PackageManagerUtils get() {
        return new PackageManagerUtils(this);
    }
}
