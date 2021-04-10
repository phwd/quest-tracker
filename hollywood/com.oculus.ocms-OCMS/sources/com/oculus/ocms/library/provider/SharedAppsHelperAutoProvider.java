package com.oculus.ocms.library.provider;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class SharedAppsHelperAutoProvider extends AbstractProvider<SharedAppsHelper> {
    @Override // javax.inject.Provider
    public SharedAppsHelper get() {
        return new SharedAppsHelper(this);
    }
}
