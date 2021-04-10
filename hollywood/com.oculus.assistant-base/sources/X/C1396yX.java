package X;

import android.app.Application;
import android.content.pm.PackageManager;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.yX  reason: case insensitive filesystem */
public final class C1396yX {
    public static final int A00() {
        Application A00 = BX.A00();
        try {
            return A00.getPackageManager().getPackageInfo(A00.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            C0139Dd.A0L("AppInfoProvider", "getAppVersion: Problem getting version code.", e);
            return 0;
        }
    }

    public final String A01() {
        Application A00 = BX.A00();
        try {
            return A00.getPackageManager().getPackageInfo(A00.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            C0139Dd.A0L("AppInfoProvider", "getAppVersion: Problem getting version.", e);
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
    }
}
