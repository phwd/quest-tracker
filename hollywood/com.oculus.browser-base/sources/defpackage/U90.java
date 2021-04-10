package defpackage;

import android.content.Context;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;

@Deprecated
/* renamed from: U90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class U90 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f9009a = new Object();
    public static U90 b;
    public final Context c;
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public final ArrayList f = new ArrayList();
    public final Handler g;

    public U90(Context context) {
        this.c = context;
        this.g = new R90(this, context.getMainLooper());
    }

    public static U90 a(Context context) {
        U90 u90;
        synchronized (f9009a) {
            if (b == null) {
                b = new U90(context.getApplicationContext());
            }
            u90 = b;
        }
        return u90;
    }
}
