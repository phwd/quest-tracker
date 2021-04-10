package X;

import android.app.Activity;
import android.content.pm.PackageManager;

/* renamed from: X.032  reason: invalid class name */
public class AnonymousClass032 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.app.ActivityCompat$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ Activity A01;
    public final /* synthetic */ String[] A02;

    public AnonymousClass032(String[] strArr, Activity activity, int i) {
        this.A02 = strArr;
        this.A01 = activity;
        this.A00 = i;
    }

    public final void run() {
        String[] strArr = this.A02;
        int length = strArr.length;
        int[] iArr = new int[length];
        Activity activity = this.A01;
        PackageManager packageManager = activity.getPackageManager();
        String packageName = activity.getPackageName();
        for (int i = 0; i < length; i++) {
            iArr[i] = packageManager.checkPermission(strArr[i], packageName);
        }
        ((AnonymousClass034) activity).onRequestPermissionsResult(this.A00, strArr, iArr);
    }
}
