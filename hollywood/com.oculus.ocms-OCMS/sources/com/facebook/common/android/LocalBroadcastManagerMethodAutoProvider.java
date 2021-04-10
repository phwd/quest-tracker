package com.facebook.common.android;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class LocalBroadcastManagerMethodAutoProvider extends AbstractProvider<LocalBroadcastManager> {
    @Override // javax.inject.Provider
    public LocalBroadcastManager get() {
        return AndroidModule.provideLocalBroadcastManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
