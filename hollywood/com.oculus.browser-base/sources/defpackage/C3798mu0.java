package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;

/* renamed from: mu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3798mu0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10457a;

    public C3798mu0(Context context) {
        this.f10457a = context;
    }

    public boolean a(String str) {
        PackageManager packageManager = this.f10457a.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            if (packageManager.checkPermission(str, this.f10457a.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }
}
