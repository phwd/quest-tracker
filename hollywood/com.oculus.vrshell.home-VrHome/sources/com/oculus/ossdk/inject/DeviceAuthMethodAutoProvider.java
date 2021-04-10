package com.oculus.ossdk.inject;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.os.DeviceAuth;

public class DeviceAuthMethodAutoProvider extends AbstractProvider<DeviceAuth> {
    @Override // javax.inject.Provider
    public DeviceAuth get() {
        return OsSdkModule.provideDeviceAuth(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
