package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: Kz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Kz1 {

    /* renamed from: a  reason: collision with root package name */
    public final Rz1 f8398a;
    public final IntentFilter b;
    public final Context c;
    public final Set d = new HashSet();
    public Oz1 e = null;
    public volatile boolean f = false;

    public Kz1(Rz1 rz1, IntentFilter intentFilter, Context context) {
        this.f8398a = rz1;
        this.b = intentFilter;
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext != null ? applicationContext : context;
    }

    public final void a() {
        Oz1 oz1;
        if ((this.f || !this.d.isEmpty()) && this.e == null) {
            Oz1 oz12 = new Oz1(this, (byte) 0);
            this.e = oz12;
            this.c.registerReceiver(oz12, this.b);
        }
        if (!this.f && this.d.isEmpty() && (oz1 = this.e) != null) {
            this.c.unregisterReceiver(oz1);
            this.e = null;
        }
    }

    public final synchronized void b(AbstractC5355w11 w11) {
        this.f8398a.a(4, "registerListener", new Object[0]);
        this.d.add(w11);
        a();
    }

    public abstract void c(Context context, Intent intent);

    public final synchronized void d(Object obj) {
        Iterator it = new HashSet(this.d).iterator();
        while (it.hasNext()) {
            ((AbstractC5355w11) it.next()).c(obj);
        }
    }

    public final synchronized void e(AbstractC5355w11 w11) {
        this.f8398a.a(4, "unregisterListener", new Object[0]);
        this.d.remove(w11);
        a();
    }
}
