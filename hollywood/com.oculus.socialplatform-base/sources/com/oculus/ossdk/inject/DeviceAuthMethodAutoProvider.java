package com.oculus.ossdk.inject;

import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.os.DeviceAuth;

@Generated({"By: InjectorProcessor"})
public class DeviceAuthMethodAutoProvider extends AnonymousClass0VG<DeviceAuth> {
    public DeviceAuth get() {
        return OsSdkModule.provideDeviceAuth(C00610Hs.A00(this));
    }
}
