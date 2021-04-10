package X;

import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
@TargetApi(28)
/* renamed from: X.0ij  reason: invalid class name and case insensitive filesystem */
public class C05150ij {
    public static long A00(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }
}
