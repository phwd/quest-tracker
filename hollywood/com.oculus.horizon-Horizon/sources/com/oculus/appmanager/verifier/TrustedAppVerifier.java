package com.oculus.appmanager.verifier;

import X.AbstractC06640p5;
import X.C003809k;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.util.device.DeviceUtils;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID"})
public class TrustedAppVerifier {
    @Inject
    @Eager
    public final DeviceUtils mDeviceUtils;
    @Inject
    @Eager
    public final PackageManager mPackageManager;

    public static boolean A00(@Nullable TrustedAppVerifier trustedAppVerifier, String str, String str2) {
        ApplicationInfo applicationInfo;
        if (!trustedAppVerifier.mDeviceUtils.A04() || !TextUtils.equals(str2, str)) {
            return false;
        }
        try {
            PackageInfo packageInfo = trustedAppVerifier.mPackageManager.getPackageInfo(str, 0);
            if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Inject
    public TrustedAppVerifier(AbstractC06640p5 r2) {
        this.mPackageManager = C003809k.A03(r2);
        this.mDeviceUtils = DeviceUtils.A00(r2);
    }
}
