package com.facebook.inject;

import android.content.Context;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class Context_com_facebook_inject_UnsafeContextInjectionMethodAutoProvider extends AbstractProvider<Context> {
    @Override // javax.inject.Provider
    public Context get() {
        return BundledAndroidModule.provideUnsafeCurrentContext(BundledAndroidModule._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_ACCESS_METHOD(this));
    }
}
