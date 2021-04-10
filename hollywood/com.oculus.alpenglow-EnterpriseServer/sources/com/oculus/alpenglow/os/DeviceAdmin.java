package com.oculus.alpenglow.os;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0R7;
import android.content.ComponentName;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.constants.Constants;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class DeviceAdmin {
    public static final ComponentName AIRWATCH = new ComponentName("com.airwatch.androidagent", "com.airwatch.agent.DeviceAdministratorReceiver");
    public static final ComponentName MOBILE_IRON = new ComponentName("com.mobileiron.anyware.android", "com.mobileiron.polaris.manager.device.AndroidDeviceAdminReceiver");
    public static final String TAG = AnonymousClass006.A05(Constants.TAG_PREFIX, "DeviceAdmin");
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Inject
    public DeviceAdmin(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
