package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* renamed from: e8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2302e8 {

    /* renamed from: a  reason: collision with root package name */
    public BroadcastReceiver f9834a;
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 b;

    public AbstractC2302e8(LayoutInflater$Factory2C3156j8 j8Var) {
        this.b = j8Var;
    }

    public void a() {
        BroadcastReceiver broadcastReceiver = this.f9834a;
        if (broadcastReceiver != null) {
            try {
                this.b.N.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
            }
            this.f9834a = null;
        }
    }

    public abstract IntentFilter b();

    public abstract int c();

    public abstract void d();

    public void e() {
        a();
        IntentFilter b2 = b();
        if (b2 != null && b2.countActions() != 0) {
            if (this.f9834a == null) {
                this.f9834a = new C2132d8(this);
            }
            this.b.N.registerReceiver(this.f9834a, b2);
        }
    }
}
