package com.facebook.common.android;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class FbLocalBroadcastManagerMethodAutoProvider extends AbstractProvider<FbLocalBroadcastManager> {
    @Override // javax.inject.Provider
    public FbLocalBroadcastManager get() {
        return AndroidModule.provideFbLocalBroadcastManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
