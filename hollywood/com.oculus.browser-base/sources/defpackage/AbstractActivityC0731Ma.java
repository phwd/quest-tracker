package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: Ma  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractActivityC0731Ma extends AbstractActivityC5319vq implements AbstractC2418eq, AbstractC3083ik, AbstractC2917hl0 {
    public final Handler W = new Handler();
    public final C5478wm0 X = new C5478wm0(this);
    public final M2 Y = new M2();
    public final C3261jm0 Z = new C3261jm0(this);
    public long a0;
    public C2971i3 b0;
    public final C1078Rq0 c0 = new C1078Rq0();
    public Bundle d0;
    public int e0;
    public boolean f0;
    public long g0;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public boolean k0 = true;
    public boolean l0;
    public boolean m0;
    public Runnable n0;

    @Override // defpackage.AbstractC3083ik
    public void B(Exception exc) {
        throw new C2840hG0(4, null);
    }

    @Override // defpackage.AbstractC3083ik
    public void F() {
        u();
    }

    @Override // defpackage.AbstractC3083ik
    public final void H(Runnable runnable) {
        boolean z = false;
        boolean z2 = AbstractC4772sd1.k(this.h0) && !this.i0;
        this.n0 = runnable;
        if (z2) {
            y0();
        }
        if (!this.l0) {
            C5478wm0 wm0 = this.X;
            if (Bw1.a().f != null) {
                z = true;
            }
            wm0.b(true ^ z);
        }
        if (!z2) {
            y0();
        }
    }

    @Override // defpackage.AbstractC3083ik
    public void I() {
        try {
            TraceEvent.Y("maybePreconnect", null);
            Intent intent = getIntent();
            if (intent != null) {
                if ("android.intent.action.VIEW".equals(intent.getAction())) {
                    String m = S20.m(intent);
                    if (m == null) {
                        TraceEvent.f0("maybePreconnect");
                        return;
                    }
                    boolean z = false;
                    if (U20.d(intent, "org.chromium.chrome.browser.incognito_mode", false) || U20.d(intent, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false) || U20.d(intent, "org.chromium.chrome.browser.incognito.invoked_from_launch_new_incognito_tab", false)) {
                        z = true;
                    }
                    if (!z) {
                        Bw1.a().c(Profile.b(), m);
                    }
                    TraceEvent.f0("maybePreconnect");
                }
            }
        } finally {
            TraceEvent.f0("maybePreconnect");
        }
    }

    @Override // defpackage.AbstractC3083ik
    public void J() {
    }

    @Override // defpackage.AbstractC3083ik
    public final void K() {
        u0();
        k0();
        Iterator it = this.Y.f8457a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2112d10) uq0.next()).g();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC3083ik
    public boolean N() {
        return false;
    }

    @Override // defpackage.AbstractC2418eq
    public void R(Intent intent) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f A[RETURN] */
    @Override // defpackage.AbstractC2418eq
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(int r6, int r7, android.content.Intent r8) {
        /*
            r5 = this;
            i3 r0 = r5.b0
            r1 = 0
            if (r0 == 0) goto L_0x0030
            android.util.SparseArray r2 = r0.c0
            java.lang.Object r2 = r2.get(r6)
            Ky1 r2 = (defpackage.Ky1) r2
            android.util.SparseArray r3 = r0.c0
            r3.delete(r6)
            java.util.HashMap r3 = r0.K
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            java.lang.Object r3 = r3.remove(r4)
            java.lang.String r3 = (java.lang.String) r3
            r4 = 1
            if (r2 == 0) goto L_0x0025
            r2.a(r0, r7, r8)
            goto L_0x002a
        L_0x0025:
            if (r3 == 0) goto L_0x002c
            r0.D0(r3)
        L_0x002a:
            r0 = r4
            goto L_0x002d
        L_0x002c:
            r0 = r1
        L_0x002d:
            if (r0 == 0) goto L_0x0030
            return r4
        L_0x0030:
            M2 r0 = r5.Y
            Vq0 r0 = r0.h
            java.util.Iterator r0 = r0.iterator()
        L_0x0038:
            r2 = r0
            Uq0 r2 = (defpackage.C1261Uq0) r2
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x004b
            java.lang.Object r2 = r2.next()
            S2 r2 = (defpackage.S2) r2
            r2.a(r6, r7, r8)
            goto L_0x0038
        L_0x004b:
            super.onActivityResult(r6, r7, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC0731Ma.a(int, int, android.content.Intent):boolean");
    }

    @Override // defpackage.AbstractC2418eq
    public void b() {
        M2 m2 = this.Y;
        m2.k = 3;
        Iterator it = m2.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0850Ny0) uq0.next()).b();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2418eq
    public void c() {
        M2 m2 = this.Y;
        m2.k = 4;
        Iterator it = m2.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0850Ny0) uq0.next()).c();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2418eq
    public void d() {
        M2 m2 = this.Y;
        m2.k = 2;
        Iterator it = m2.d.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1768b01) uq0.next()).d();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2418eq
    public void e() {
        M2 m2 = this.Y;
        m2.k = 5;
        Iterator it = m2.d.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1768b01) uq0.next()).e();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractActivityC5319vq
    public boolean f0(Context context, Configuration configuration) {
        super.f0(context, configuration);
        YF b = YF.b(context);
        Point point = b.d;
        int i = point.x;
        int i2 = point.y;
        if (i >= i2) {
            i = i2;
        }
        configuration.smallestScreenWidthDp = (int) ((((float) i) / b.e) + 0.5f);
        return true;
    }

    public final void g0(int i) {
        super.onCreate(null);
        if (i == 1) {
            finish();
            return;
        }
        finishAndRemoveTask();
        overridePendingTransition(0, R.anim.f580_resource_name_obfuscated_RES_2130772026);
    }

    public final void h0() {
        WindowManager windowManager = getWindowManager();
        if (windowManager != null && windowManager.getDefaultDisplay() != null) {
            int i = this.e0;
            int i2 = getResources().getConfiguration().orientation;
            this.e0 = i2;
            if (i != i2) {
                s0();
            }
        }
    }

    public C2746gl0 i0() {
        return null;
    }

    public abstract C2971i3 j0();

    @Override // defpackage.AbstractC3083ik
    public final void k() {
        v0();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x01a7 A[SYNTHETIC, Splitter:B:31:0x01a7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void k0() {
        /*
        // Method dump skipped, instructions count: 436
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC0731Ma.k0():void");
    }

    public C2746gl0 l0() {
        return (C2746gl0) this.c0.H;
    }

    public long m0() {
        return this.a0;
    }

    public View n0() {
        return findViewById(16908290);
    }

    public void o0() {
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onActivityResult(int i, int i2, Intent intent) {
        C5478wm0 wm0 = this.X;
        if (wm0.i) {
            wm0.f11568a.a(i, i2, intent);
            return;
        }
        if (wm0.e == null) {
            wm0.e = new ArrayList(1);
        }
        wm0.e.add(new C5308vm0(i, i2, intent));
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC5319vq
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        t0(configuration);
        Iterator it = this.Y.i.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3464kx) uq0.next()).onConfigurationChanged(configuration);
            } else {
                return;
            }
        }
    }

    public void onContextMenuClosed(Menu menu) {
        C2971i3 i3Var = this.b0;
        if (i3Var != null) {
            C1261Uq0 uq0 = (C1261Uq0) i3Var.a0.iterator();
            if (uq0.hasNext()) {
                C5859z.a(uq0.next());
                throw null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0239, code lost:
        if (r3 == null) goto L_0x024c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0184 A[LOOP:0: B:72:0x017e->B:74:0x0184, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0210  */
    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC3119iw, defpackage.AbstractActivityC5319vq
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r17) {
        /*
        // Method dump skipped, instructions count: 679
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC0731Ma.onCreate(android.os.Bundle):void");
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC5319vq
    public void onDestroy() {
        this.f0 = true;
        C2971i3 i3Var = this.b0;
        if (i3Var != null) {
            i3Var.destroy();
            this.b0 = null;
        }
        Object obj = this.c0.H;
        if (obj != null) {
            ((C2746gl0) obj).a();
            this.c0.m(null);
        }
        super.onDestroy();
        M2 m2 = this.Y;
        m2.k = 6;
        Iterator it = m2.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4371qE) uq0.next()).destroy();
            } else {
                m2.f8457a.clear();
                m2.c.clear();
                m2.d.clear();
                m2.b.clear();
                m2.f.clear();
                m2.g.clear();
                m2.h.clear();
                m2.i.clear();
                m2.e.clear();
                m2.j.clear();
                return;
            }
        }
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onMultiWindowModeChanged(boolean z) {
        this.K.f11609a.I.r(z);
        Iterator it = this.Z.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3090im0) uq0.next()).g(z);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onNewIntent(Intent intent) {
        if (intent != null) {
            C5478wm0 wm0 = this.X;
            if (wm0.i) {
                wm0.f11568a.R(intent);
            } else {
                if (wm0.d == null) {
                    wm0.d = new ArrayList(1);
                }
                wm0.d.add(intent);
            }
            setIntent(intent);
        }
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onPause() {
        C5478wm0 wm0 = this.X;
        wm0.c = false;
        if (wm0.i) {
            wm0.f11568a.c();
        }
        super.onPause();
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        C2971i3 i3Var = this.b0;
        if (i3Var != null) {
            AbstractC2809h6 h6Var = i3Var.R;
            if (h6Var != null ? h6Var.B(i, strArr, iArr) : false) {
                return;
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onResume() {
        super.onResume();
        this.j0 = !this.k0 || this.i0;
        this.k0 = false;
        C5478wm0 wm0 = this.X;
        if (wm0.i) {
            wm0.f11568a.b();
        } else {
            wm0.c = true;
        }
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC3119iw
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        C2971i3 i3Var = this.b0;
        if (i3Var != null) {
            bundle.putSerializable("window_callback_errors", i3Var.K);
        }
        Iterator it = this.Y.f.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((BO0) uq0.next()).P(bundle);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS
    public void onStart() {
        super.onStart();
        C5478wm0 wm0 = this.X;
        if (wm0.i) {
            wm0.c();
        } else {
            wm0.b = true;
        }
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS
    public void onStop() {
        super.onStop();
        C5478wm0 wm0 = this.X;
        wm0.b = false;
        if (wm0.i) {
            wm0.f11568a.e();
        }
    }

    public void onUserInteraction() {
        this.g0 = SystemClock.elapsedRealtime();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Iterator it = this.Y.g.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1764az1) uq0.next()).onWindowFocusChanged(z);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC3083ik
    public void p() {
    }

    public boolean p0(Intent intent) {
        return true;
    }

    public int q0(Intent intent, Bundle bundle) {
        return 0;
    }

    public void r0() {
        Runnable runnable = this.n0;
        if (runnable != null) {
            runnable.run();
            this.n0 = null;
        }
    }

    public void recreate() {
        super.recreate();
        Iterator it = this.Y.j.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C2066cm0) uq0.next()).P = true;
            } else {
                return;
            }
        }
    }

    public void s0() {
    }

    public void t0(Configuration configuration) {
    }

    @Override // defpackage.AbstractC3083ik
    public void u() {
        h0();
        findViewById(16908290).addOnLayoutChangeListener(new View$OnLayoutChangeListenerC0671La(this));
        C5478wm0 wm0 = this.X;
        wm0.i = true;
        if (wm0.b) {
            wm0.b = false;
            wm0.c();
        }
        if (wm0.c) {
            wm0.c = false;
            if (wm0.i) {
                wm0.f11568a.b();
            } else {
                wm0.c = true;
            }
        }
        C2474f80 f80 = C2474f80.f9900a;
        synchronized (f80.j) {
            if (f80.d) {
                AbstractC3364kK0.k("ChromiumAndroidLinker.BrowserLoadTime", f80.n);
            }
        }
        M2 m2 = this.Y;
        m2.l = true;
        Iterator it = m2.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4968tm0) uq0.next()).s();
            } else {
                return;
            }
        }
    }

    public void u0() {
        NQ.a(n0(), new RunnableC0610Ka(this));
    }

    @Override // defpackage.AbstractC3083ik
    public boolean v() {
        return this.f0 || isFinishing();
    }

    public void v0() {
        this.h0 = DeviceFormFactor.a(this);
        this.i0 = C2474f80.f9900a.f();
        Iterator it = this.Y.f8457a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                GN0 gn0 = (GN0) ((AbstractC2112d10) uq0.next());
                C2687gN0 gn02 = new C2687gN0(gn0);
                C2858hN0 hn0 = new C2858hN0(gn0);
                C5638xj xjVar = new C5638xj(new C3029iN0(gn0), gn02, gn0.F.getWindow(), (L2) gn0.F.b0.G, new C3199jN0(gn0));
                gn0.a0 = xjVar;
                BottomSheet.F = new C3370kN0();
                AbstractC5978zj.f11762a.a(gn0.F.b0.U, xjVar);
                C5638xj xjVar2 = gn0.a0;
                C1595a3 a3Var = gn0.f8088J;
                C1551Zj M0 = gn0.F.M0();
                UT S0 = gn0.F.S0();
                ChromeActivity chromeActivity = gn0.F;
                chromeActivity.getClass();
                gn0.Z = new C0515Ij(xjVar2, a3Var, M0, S0, new C3541lN0(chromeActivity), new C3712mN0(gn0), gn0.I, gn0.h0, hn0, gn0.q0);
            } else {
                return;
            }
        }
    }

    public boolean w0() {
        return true;
    }

    public boolean x0() {
        return false;
    }

    public abstract void y0();
}
