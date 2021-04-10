package defpackage;

import android.content.Context;

/* renamed from: FE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class FE {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f8002a;
    public static Boolean b;
    public static Boolean c;

    public static boolean a(Context context) {
        if (f8002a == null) {
            f8002a = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return f8002a.booleanValue();
    }

    public static boolean b(Context context) {
        if (!a(context)) {
            return false;
        }
        if (b == null) {
            b = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return b.booleanValue() && !AbstractC4539rD0.a();
    }
}
