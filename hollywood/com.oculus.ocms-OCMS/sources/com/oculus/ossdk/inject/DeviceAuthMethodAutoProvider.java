package com.oculus.ossdk.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.os.DeviceAuth;

@Generated({"By: InjectorProcessor"})
public class DeviceAuthMethodAutoProvider extends AbstractProvider<DeviceAuth> {
    @Override // javax.inject.Provider
    public DeviceAuth get() {
        return OsSdkModule.provideDeviceAuth(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
