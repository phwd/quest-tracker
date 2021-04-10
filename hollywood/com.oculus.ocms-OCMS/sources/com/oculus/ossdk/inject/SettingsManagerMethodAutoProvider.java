package com.oculus.ossdk.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.os.SettingsManager;

@Generated({"By: InjectorProcessor"})
public class SettingsManagerMethodAutoProvider extends AbstractProvider<SettingsManager> {
    @Override // javax.inject.Provider
    public SettingsManager get() {
        return OsSdkModule.provideSettingsManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
