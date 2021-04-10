package defpackage;

import android.content.Context;
import android.content.ServiceConnection;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* renamed from: Jq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0591Jq0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8315a = new Object();
    public static C0591Jq0 b;
    public ServiceConnection c;
    public AbstractC0347Fq0 d;
    public HashSet e = new HashSet();
    public String f;

    public static void a(AbstractC0530Iq0 iq0) {
        C0591Jq0 b2 = b();
        synchronized (b2.e) {
            b2.e.add(iq0);
        }
        String str = b2.f;
        if (str != null) {
            iq0.c(str);
        }
    }

    public static C0591Jq0 b() {
        C0591Jq0 jq0;
        synchronized (f8315a) {
            if (b == null) {
                b = new C0591Jq0();
            }
            jq0 = b;
        }
        return jq0;
    }

    public static void e(AbstractC0530Iq0 iq0) {
        C0591Jq0 b2 = b();
        synchronized (b2.e) {
            b2.e.remove(iq0);
        }
    }

    public void c() {
        HashSet hashSet;
        synchronized (this.e) {
            hashSet = (HashSet) this.e.clone();
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((AbstractC0530Iq0) it.next()).a();
        }
    }

    public void d(String str) {
        HashSet hashSet;
        Log.i("OVRServiceManager", "Token Received");
        synchronized (this.e) {
            hashSet = (HashSet) this.e.clone();
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((AbstractC0530Iq0) it.next()).c(str);
        }
    }

    public void f(Context context) {
        String str = this.f;
        if (str == null) {
            C0469Hq0 hq0 = new C0469Hq0(this, context);
            Executor executor = AbstractC2032cb.f9616a;
            hq0.f();
            ((ExecutorC1463Ya) executor).execute(hq0.e);
            return;
        }
        d(str);
    }
}
