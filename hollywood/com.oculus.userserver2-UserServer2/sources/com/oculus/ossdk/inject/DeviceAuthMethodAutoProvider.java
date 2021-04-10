package com.oculus.ossdk.inject;

import X.AbstractC0029Ba;
import X.IX;
import android.content.Context;
import com.facebook.annotations.Generated;
import com.oculus.os.DeviceAuth;

@Generated({"By: InjectorProcessor"})
public class DeviceAuthMethodAutoProvider extends AbstractC0029Ba<DeviceAuth> {
    public final Object get() {
        return OsSdkModule.A01((Context) IX.A00(1, this));
    }
}
