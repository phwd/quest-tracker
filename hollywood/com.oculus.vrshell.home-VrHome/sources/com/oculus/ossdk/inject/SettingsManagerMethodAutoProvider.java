package com.oculus.ossdk.inject;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.os.SettingsManager;

public class SettingsManagerMethodAutoProvider extends AbstractProvider<SettingsManager> {
    @Override // javax.inject.Provider
    public SettingsManager get() {
        return OsSdkModule.provideSettingsManager(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
