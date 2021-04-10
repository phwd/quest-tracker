package defpackage;

import android.content.pm.FeatureInfo;
import android.os.Build;
import org.chromium.base.ContextUtils;

/* renamed from: ZN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class ZN0 {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f9341a;

    public static int a(int i) {
        Boolean bool = f9341a;
        if (bool == null && bool == null) {
            if ("SAMSUNG".equalsIgnoreCase(Build.MANUFACTURER)) {
                FeatureInfo[] systemAvailableFeatures = ContextUtils.getApplicationContext().getPackageManager().getSystemAvailableFeatures();
                int length = systemAvailableFeatures.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        f9341a = Boolean.FALSE;
                        break;
                    } else if ("com.sec.feature.spen_usp".equalsIgnoreCase(systemAvailableFeatures[i2].name)) {
                        f9341a = Boolean.TRUE;
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                f9341a = Boolean.FALSE;
            }
        }
        if (!f9341a.booleanValue()) {
            return i;
        }
        switch (i) {
            case 211:
                return 0;
            case 212:
                return 1;
            case 213:
                return 2;
            case 214:
                return 3;
            default:
                return i;
        }
    }
}
