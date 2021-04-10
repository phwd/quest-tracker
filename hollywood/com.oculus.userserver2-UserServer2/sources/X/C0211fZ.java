package X;

import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting(otherwise = 2)
@TargetApi(28)
/* renamed from: X.fZ  reason: case insensitive filesystem */
public class C0211fZ {
    public static long A00(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }
}
