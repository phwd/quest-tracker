package com.oculus.coreapps;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class CoreAppManagerAutoProvider extends AbstractProvider<CoreAppManager> {
    @Override // javax.inject.Provider
    public CoreAppManager get() {
        return new CoreAppManager(this);
    }
}
