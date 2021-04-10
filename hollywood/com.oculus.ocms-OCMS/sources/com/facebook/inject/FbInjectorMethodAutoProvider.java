package com.facebook.inject;

import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class FbInjectorMethodAutoProvider extends AbstractProvider<FbInjector> {
    @Override // javax.inject.Provider
    public FbInjector get() {
        return BundledAndroidModule.assertFbInjector();
    }
}
