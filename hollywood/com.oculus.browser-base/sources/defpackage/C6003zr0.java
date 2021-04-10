package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import org.chromium.base.Callback;

/* renamed from: zr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6003zr0 {

    /* renamed from: a  reason: collision with root package name */
    public Context f11774a;
    public Z11 b;
    public Handler c;
    public C4302pr0 d;
    public AbstractC0956Pq0 e;
    public Q31 f;
    public Callback g;
    public Runnable h;
    public Runnable i;
    public Runnable j;
    public Runnable k;
    public Runnable l;
    public long m;
    public boolean n;
    public long o;

    public C6003zr0(Context context, Z11 z11, AbstractC0956Pq0 pq0, Q31 q31) {
        if (!AbstractC1575Zv.e().g("force-online-connection-state-for-indicator")) {
            this.f11774a = context;
            this.b = z11;
            this.c = new Handler();
            this.m = SystemClock.elapsedRealtime() - 5000;
            this.d = new C4302pr0(new C4813sr0(this));
            this.h = new RunnableC4983tr0(this);
            this.j = new RunnableC5153ur0(this);
            this.i = new RunnableC5323vr0(this);
            this.e = pq0;
            this.f = q31;
            C5493wr0 wr0 = new C5493wr0(this);
            this.g = wr0;
            ((C1078Rq0) pq0).l(wr0);
            this.l = new RunnableC5663xr0(this);
        }
    }

    public final void a(boolean z) {
        int i2;
        this.n = z;
        if (!((Boolean) ((C1078Rq0) this.e).H).booleanValue()) {
            (z ? this.h : this.i).run();
            i2 = ((Boolean) this.f.get()).booleanValue() ? 0 : 2;
        } else if ((z || this.k != this.h) && (!z || this.k != this.i)) {
            this.k = z ? this.h : this.i;
            i2 = ((Boolean) this.f.get()).booleanValue() ? 1 : 3;
        } else {
            this.k = null;
            return;
        }
        StringBuilder i3 = AbstractC2531fV.i("OfflineIndicator.ConnectivityChanged.DeviceState.");
        i3.append(z ? "Offline" : "Online");
        AbstractC3364kK0.g(i3.toString(), i2, 4);
    }
}
