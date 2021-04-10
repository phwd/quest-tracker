package X;

import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
@TargetApi(28)
/* renamed from: X.0k9  reason: invalid class name */
public class AnonymousClass0k9 {
    public static long A00(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }
}
