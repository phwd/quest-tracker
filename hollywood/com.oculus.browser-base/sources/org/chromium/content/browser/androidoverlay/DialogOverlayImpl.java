package org.chromium.content.browser.androidoverlay;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.os.IBinder;
import android.view.WindowManager;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.gfx.mojom.Rect;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogOverlayImpl implements AbstractC3660m5 {
    public AbstractC3831n5 F;
    public Runnable G;
    public UE H = new UE();
    public long I;

    /* renamed from: J  reason: collision with root package name */
    public int f10923J;
    public boolean K;

    public DialogOverlayImpl(AbstractC3831n5 n5Var, C5875z5 z5Var, Runnable runnable, boolean z) {
        Object obj = ThreadUtils.f10596a;
        this.F = n5Var;
        this.G = runnable;
        C5318vp1 vp1 = z5Var.d;
        long MqPi0d6D = N.MqPi0d6D(this, vp1.d, vp1.e, z5Var.g);
        this.I = MqPi0d6D;
        if (MqPi0d6D == 0) {
            g0();
            f0();
            return;
        }
        UE ue = this.H;
        Context applicationContext = ContextUtils.getApplicationContext();
        N.MAd6qeVr(this.I, this, z5Var.e);
        ue.f9014a = this;
        ue.e = z;
        Dialog dialog = new Dialog(applicationContext, 16973909);
        ue.b = dialog;
        dialog.requestWindowFeature(1);
        ue.b.setCancelable(false);
        boolean z2 = z5Var.f;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 51;
        layoutParams.type = ue.e ? 1000 : 1001;
        layoutParams.flags = 568;
        if (z2) {
            layoutParams.flags = 8760;
        }
        try {
            layoutParams.getClass().getField("privateFlags").set(layoutParams, Integer.valueOf(((Integer) layoutParams.getClass().getField("privateFlags").get(layoutParams)).intValue() | 64));
        } catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NullPointerException | SecurityException unused) {
        }
        ue.d = layoutParams;
        ue.a(z5Var.e);
        N.MQAm7B7f(this.I, this);
    }

    public static void receiveCompositorOffset(Rect rect, int i, int i2) {
        rect.d += i;
        rect.e += i2;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        Object obj = ThreadUtils.f10596a;
        close();
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        Object obj = ThreadUtils.f10596a;
        if (!this.K) {
            this.K = true;
            UE ue = this.H;
            if (ue != null) {
                ue.b();
                ue.d.token = null;
                ue.f9014a = null;
                f0();
            }
            this.G.run();
        }
    }

    @Override // defpackage.AbstractC3660m5
    public void d(Rect rect) {
        Object obj = ThreadUtils.f10596a;
        if (this.H != null) {
            N.MAd6qeVr(this.I, this, rect);
            UE ue = this.H;
            if (ue.b != null && ue.d.token != null && ue.a(rect)) {
                ue.b.getWindow().setAttributes(ue.d);
            }
        }
    }

    public final void f0() {
        Object obj = ThreadUtils.f10596a;
        int i = this.f10923J;
        if (i != 0) {
            N.M1e4GdYZ(i);
            this.f10923J = 0;
        }
        long j = this.I;
        if (j != 0) {
            N.MJj9v_ba(j, this);
            this.I = 0;
        }
        this.H = null;
        AbstractC3831n5 n5Var = this.F;
        if (n5Var != null) {
            ((AbstractC2459f30) n5Var).close();
        }
        this.F = null;
    }

    public final void g0() {
        AbstractC3831n5 n5Var = this.F;
        if (n5Var != null) {
            this.F = null;
            if (this.f10923J == 0) {
                ((C5365w5) n5Var).f0();
            } else {
                N.MFq0hOYg(((AbstractC1552Zj0) ((RW) ((C5365w5) n5Var).F.G).x()).h());
            }
        }
    }

    public void onDismissed() {
        Object obj = ThreadUtils.f10596a;
        g0();
        UE ue = this.H;
        if (ue != null) {
            ue.c(null);
        }
        f0();
    }

    public final void onPowerEfficientState(boolean z) {
        AbstractC3831n5 n5Var;
        Object obj = ThreadUtils.f10596a;
        if (this.H != null && (n5Var = this.F) != null) {
            ((C5365w5) n5Var).g0(z);
        }
    }

    public void onWindowToken(IBinder iBinder) {
        Object obj = ThreadUtils.f10596a;
        UE ue = this.H;
        if (ue != null) {
            ue.c(iBinder);
        }
    }
}
