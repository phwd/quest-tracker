package com.oculus.app.standalone;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class StandaloneApplicationLikeAutoProvider extends AbstractProvider<StandaloneApplicationLike> {
    @Override // javax.inject.Provider
    public StandaloneApplicationLike get() {
        return new StandaloneApplicationLike(this);
    }
}
