package defpackage;

import android.content.Context;
import java.util.Map;

/* renamed from: VU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VU {

    /* renamed from: a  reason: collision with root package name */
    public static VU f9086a;
    public final Context b;
    public final Map c = new C4931ta();

    public VU(Context context) {
        this.b = context;
    }

    public static VU a(Context context) {
        VU vu;
        synchronized (VU.class) {
            if (f9086a == null) {
                f9086a = new VU(context.getApplicationContext());
            }
            vu = f9086a;
        }
        return vu;
    }

    public final synchronized void b(String str, String str2) {
        Map map = (Map) this.c.get(str2);
        if (map != null) {
            if ((map.remove(str) != null) && map.isEmpty()) {
                this.c.remove(str2);
            }
        }
    }

    public final synchronized boolean c(String str) {
        return this.c.containsKey(str);
    }

    public final synchronized boolean d(String str, String str2) {
        Map map = (Map) this.c.get(str2);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }
}
