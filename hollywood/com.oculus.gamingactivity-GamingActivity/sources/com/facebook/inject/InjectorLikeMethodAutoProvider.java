package com.facebook.inject;

import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class InjectorLikeMethodAutoProvider extends AbstractProvider<InjectorLike> {
    @Override // javax.inject.Provider
    public InjectorLike get() {
        return BundledAndroidModule.assertInjectorLike();
    }
}
