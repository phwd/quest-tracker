package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayDeque;
import java.util.Queue;

/* renamed from: eo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2412eo {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f9880a;
    public final Queue b = new ArrayDeque();
    public final Handler c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public C1900bo j = new C1900bo(null);

    public AbstractC2412eo(Handler handler, Runnable runnable, String str, String str2, String str3, boolean z, boolean z2, boolean z3, C1559Zn zn) {
        this.c = handler;
        this.f9880a = runnable;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = z;
        this.h = z2;
        this.i = z3;
    }

    public static void a(Context context, String str, String str2) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getServiceInfo(new ComponentName(str, str2 + "0"), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new RuntimeException("Illegal meta data value: the child service doesn't exist");
        }
    }

    public static AbstractC2412eo b(Context context, Handler handler, Runnable runnable, String str, String str2, String str3, boolean z, boolean z2, boolean z3) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(str, 128).metaData;
            int i2 = bundle != null ? bundle.getInt(str3, -1) : -1;
            if (i2 >= 0) {
                a(context, str, str2);
                return new C2071co(handler, runnable, str, str2, z, z2, z3, i2, null);
            }
            throw new RuntimeException("Illegal meta data value for number of child services");
        } catch (PackageManager.NameNotFoundException unused) {
            throw new RuntimeException("Could not get application info.");
        }
    }

    public abstract C5653xo c(Context context, Bundle bundle, AbstractC5483wo woVar);

    public abstract void d(C5653xo xoVar);

    public abstract int e();
}
