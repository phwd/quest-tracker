package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Pair;
import com.google.android.gms.common.api.Status;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BasePendingResult extends DB0 {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal f9656a = new WB1();
    public final Object b = new Object();
    public final HandlerC5459wg c;
    public final WeakReference d;
    public final CountDownLatch e = new CountDownLatch(1);
    public final ArrayList f = new ArrayList();
    public BM0 g;
    public final AtomicReference h = new AtomicReference();
    public AM0 i;
    public Status j;
    public volatile boolean k;
    public boolean l;
    public boolean m;
    public boolean n = false;

    public BasePendingResult(YV yv) {
        this.c = new HandlerC5459wg(yv != null ? yv.h() : Looper.getMainLooper());
        this.d = new WeakReference(yv);
    }

    @Override // defpackage.DB0
    public void a() {
        synchronized (this.b) {
            if (!this.l) {
                if (!this.k) {
                    this.l = true;
                    g(c(Status.I));
                }
            }
        }
    }

    @Override // defpackage.DB0
    public final void b(BM0 bm0) {
        boolean z;
        synchronized (this.b) {
            if (bm0 == null) {
                this.g = null;
                return;
            }
            SE0.k(!this.k, "Result has already been consumed.");
            SE0.k(true, "Cannot set callbacks if then() has been called.");
            synchronized (this.b) {
                z = this.l;
            }
            if (!z) {
                if (e()) {
                    HandlerC5459wg wgVar = this.c;
                    AM0 d2 = d();
                    Objects.requireNonNull(wgVar);
                    wgVar.sendMessage(wgVar.obtainMessage(1, new Pair(bm0, d2)));
                } else {
                    this.g = bm0;
                }
            }
        }
    }

    public abstract AM0 c(Status status);

    public final AM0 d() {
        AM0 am0;
        synchronized (this.b) {
            SE0.k(!this.k, "Result has already been consumed.");
            SE0.k(e(), "Result is not ready.");
            am0 = this.i;
            this.i = null;
            this.g = null;
            this.k = true;
        }
        FB1 fb1 = (FB1) this.h.getAndSet(null);
        if (fb1 != null) {
            fb1.a(this);
        }
        return am0;
    }

    public final boolean e() {
        return this.e.getCount() == 0;
    }

    public final void f(AM0 am0) {
        synchronized (this.b) {
            if (!this.m && !this.l) {
                e();
                boolean z = true;
                SE0.k(!e(), "Results have already been set");
                if (this.k) {
                    z = false;
                }
                SE0.k(z, "Result has already been consumed");
                g(am0);
            }
        }
    }

    public final void g(AM0 am0) {
        this.i = am0;
        this.e.countDown();
        this.j = this.i.b();
        if (this.l) {
            this.g = null;
        } else if (this.g != null) {
            this.c.removeMessages(2);
            HandlerC5459wg wgVar = this.c;
            BM0 bm0 = this.g;
            AM0 d2 = d();
            Objects.requireNonNull(wgVar);
            wgVar.sendMessage(wgVar.obtainMessage(1, new Pair(bm0, d2)));
        }
        ArrayList arrayList = this.f;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            FA1 fa1 = (FA1) obj;
            fa1.b.f9839a.remove(fa1.f7998a);
        }
        this.f.clear();
    }

    public final void h(Status status) {
        synchronized (this.b) {
            if (!e()) {
                f(c(status));
                this.m = true;
            }
        }
    }

    public final void i() {
        this.n = this.n || ((Boolean) f9656a.get()).booleanValue();
    }
}
