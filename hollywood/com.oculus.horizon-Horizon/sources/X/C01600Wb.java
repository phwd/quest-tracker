package X;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.DeadObjectException;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.squareup.okhttp.internal.DiskLruCache;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Wb  reason: invalid class name and case insensitive filesystem */
public final class C01600Wb {
    public static C01600Wb A03;
    public final String A00;
    public final String A01;
    public final boolean A02;

    public static synchronized C01600Wb A00(Context context) {
        C01600Wb r0;
        synchronized (C01600Wb.class) {
            r0 = A03;
            if (r0 == null) {
                r0 = new C01600Wb(context);
                A03 = r0;
            }
        }
        return r0;
    }

    public C01600Wb(Context context) {
        boolean z = false;
        AnonymousClass0WT A002 = AnonymousClass0WZ.A00(context, context.getPackageName(), 0);
        PackageInfo packageInfo = A002.A01;
        String str = DiskLruCache.VERSION_1;
        String str2 = "unknown";
        if (packageInfo != null) {
            this.A01 = !TextUtils.isEmpty(A002.A01.versionName) ? A002.A01.versionName : str2;
            if (A002.A01.versionCode > 0) {
                str = String.valueOf(AnonymousClass0JF.A01());
            }
        } else {
            this.A01 = str2;
        }
        this.A00 = str;
        try {
            z = C02780bN.A10.contains(AnonymousClass0bU.A03(context, context.getPackageName()));
        } catch (SecurityException e) {
            if (e.getCause() == null || !(e.getCause().getCause() instanceof DeadObjectException)) {
                throw e;
            }
        }
        this.A02 = z;
    }
}
