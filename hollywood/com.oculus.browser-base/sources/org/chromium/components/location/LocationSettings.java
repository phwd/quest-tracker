package org.chromium.components.location;

import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocationSettings {
    public static boolean canPromptForAndroidLocationPermission(WindowAndroid windowAndroid) {
        return windowAndroid.canRequestPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    public static boolean canPromptToEnableSystemLocationSetting() {
        Objects.requireNonNull(C1159Ta0.a());
        return false;
    }

    public static boolean hasAndroidLocationPermission() {
        return C1159Ta0.a().c();
    }

    public static boolean isSystemLocationSettingEnabled() {
        return C1159Ta0.a().e();
    }

    public static void promptToEnableSystemLocationSetting(int i, WindowAndroid windowAndroid, long j) {
        C1159Ta0 a2 = C1159Ta0.a();
        C1098Sa0 sa0 = new C1098Sa0(j);
        Objects.requireNonNull(a2);
        sa0.onResult(3);
    }
}
