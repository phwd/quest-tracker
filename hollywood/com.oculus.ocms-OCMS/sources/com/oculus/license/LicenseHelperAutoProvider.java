package com.oculus.license;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LicenseHelperAutoProvider extends AbstractProvider<LicenseHelper> {
    @Override // javax.inject.Provider
    public LicenseHelper get() {
        return new LicenseHelper(this);
    }
}
