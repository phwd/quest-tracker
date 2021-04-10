package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.Objects;

/* renamed from: Vz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Vz1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Rz1 f9121a = new Rz1("AppUpdateService");
    public static final Intent b = new Intent("com.google.android.play.core.install.BIND_UPDATE_SERVICE").setPackage("com.android.vending");
    public final C4531rA1 c;
    public final String d;
    public final Context e;

    public Vz1(Context context) {
        this.d = context.getPackageName();
        this.e = context;
        Context applicationContext = context.getApplicationContext();
        this.c = new C4531rA1(applicationContext != null ? applicationContext : context, f9121a, "AppUpdateService", b, Tz1.f9001a);
    }

    public static Bundle a(Vz1 vz1, String str) {
        Integer num;
        Objects.requireNonNull(vz1);
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("playcore.version.code", 10604);
        bundle.putAll(bundle2);
        bundle.putString("package.name", str);
        try {
            num = Integer.valueOf(vz1.e.getPackageManager().getPackageInfo(vz1.e.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            f9121a.a(6, "The current version of the app could not be retrieved", new Object[0]);
            num = null;
        }
        if (num != null) {
            bundle.putInt("app.version.code", num.intValue());
        }
        return bundle;
    }
}
