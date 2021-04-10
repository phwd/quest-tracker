package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import org.chromium.base.ContextUtils;

/* renamed from: ku0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3456ku0 {
    public PackageInfo a(String str) {
        try {
            return ContextUtils.getApplicationContext().getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
