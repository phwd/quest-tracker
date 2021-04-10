package X;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.DeadObjectException;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.squareup.okhttp.internal.DiskLruCache;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wY  reason: invalid class name and case insensitive filesystem */
public final class C08090wY {
    public static C08090wY A03;
    public final String A00;
    public final String A01;
    public final boolean A02;

    public static synchronized C08090wY A00(Context context) {
        C08090wY r0;
        synchronized (C08090wY.class) {
            r0 = A03;
            if (r0 == null) {
                r0 = new C08090wY(context);
                A03 = r0;
            }
        }
        return r0;
    }

    public C08090wY(Context context) {
        boolean z = false;
        C08130wc A002 = C08100wZ.A00(context, context.getPackageName(), 0);
        PackageInfo packageInfo = A002.A01;
        String str = DiskLruCache.VERSION_1;
        String str2 = "unknown";
        if (packageInfo != null) {
            this.A01 = !TextUtils.isEmpty(A002.A01.versionName) ? A002.A01.versionName : str2;
            if (A002.A01.versionCode > 0) {
                str = String.valueOf(AnonymousClass0JB.A01());
            }
        } else {
            this.A01 = str2;
        }
        this.A00 = str;
        try {
            z = C05120ig.A10.contains(C05180im.A03(context, context.getPackageName()));
        } catch (SecurityException e) {
            if (e.getCause() == null || !(e.getCause().getCause() instanceof DeadObjectException)) {
                throw e;
            }
        }
        this.A02 = z;
    }
}
