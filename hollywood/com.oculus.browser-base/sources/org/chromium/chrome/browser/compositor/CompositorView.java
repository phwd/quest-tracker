package org.chromium.chrome.browser.compositor;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.ResourceManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CompositorView extends FrameLayout implements AbstractC0358Fw, Ly1 {
    public final Rect F = new Rect();
    public AbstractC0419Gw G;
    public boolean H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public int f10632J;
    public long K;
    public final F70 L;
    public int M = -1;
    public ResourceManager N;
    public WindowAndroid O;
    public TabContentManager P;
    public View Q;
    public boolean R;
    public List S;
    public boolean T;
    public boolean U;
    public C0784Mw V;

    public CompositorView(Context context, F70 f70) {
        super(context);
        this.L = f70;
        c();
    }

    public void a(E70 e70) {
        float f;
        int[] iArr;
        int[] iArr2;
        TraceEvent.Y("CompositorView:finalizeLayers", null);
        if (((D70) e70).S == null || this.K == 0) {
            TraceEvent.f0("CompositorView:finalizeLayers");
            return;
        }
        boolean z = true;
        if (!this.R) {
            ResourceManager resourceManager = this.N;
            if (DeviceFormFactor.a(getContext())) {
                iArr = I11.f8196a;
            } else {
                iArr = I11.c;
            }
            if (DeviceFormFactor.a(getContext())) {
                iArr2 = I11.b;
            } else {
                iArr2 = I11.c;
            }
            AbstractC4394qM0 qm0 = (AbstractC4394qM0) resourceManager.f11027a.get(0);
            for (int i : iArr2) {
                qm0.c(Integer.valueOf(i).intValue());
            }
            for (int i2 : iArr) {
                qm0.a(Integer.valueOf(i2).intValue());
            }
            this.R = true;
        }
        N.Mjz8vYEz(this.K, this);
        TabContentManager tabContentManager = this.P;
        ResourceManager resourceManager2 = this.N;
        D70 d70 = (D70) e70;
        C1551Zj k = d70.k();
        if (k != null) {
            int i3 = 0;
            while (true) {
                if (i3 >= d70.m0.size()) {
                    z = false;
                    break;
                } else if (((LO0) d70.m0.get(i3)).k()) {
                    break;
                } else {
                    i3++;
                }
            }
            if (z || d70.S.k()) {
                int i4 = d70.X;
                int a2 = k.I.a();
                k.I.c(i4);
                d70.X = a2;
                d70.H.m(Boolean.FALSE);
            } else {
                k.I.c(d70.X);
                d70.H.m(Boolean.TRUE);
            }
        }
        d70.o(d70.c0);
        ((CompositorViewHolder) d70.G).r(d70.d0);
        AbstractC2300e70 e702 = d70.S;
        e702.U(d70.d0, d70.c0, (LayerTitleCache) d70.q0.get(), tabContentManager, resourceManager2, k);
        SceneLayer q = e702.q();
        Object obj = d70.k0.H;
        if (obj == null) {
            f = 0.0f;
        } else {
            f = (float) ((C1551Zj) ((AbstractC2400ek) obj)).R;
        }
        for (int i5 = 0; i5 < d70.m0.size(); i5++) {
            if (((LO0) d70.m0.get(i5)).u()) {
                MO0 q2 = ((LO0) d70.m0.get(i5)).q(d70.d0, d70.c0, resourceManager2, d70.F * f);
                q2.d(q);
                q = q2;
            }
        }
        N.MPdbXv3F(this.K, this, q);
        if (AbstractC5109uc1.f11423a > 0 && AbstractC5109uc1.c) {
            AbstractC5109uc1.b();
            AbstractC5109uc1.a(false);
            AbstractC5109uc1.f11423a = 0;
            AbstractC5109uc1.c = false;
        }
        N.MPzbdzfI(this.K, this);
        TraceEvent.f0("CompositorView:finalizeLayers");
    }

    public final int b() {
        if (this.H || this.I) {
            return -3;
        }
        if (!this.T) {
            return -1;
        }
        if (!this.U) {
            return -3;
        }
        return -1;
    }

    public final void c() {
        if (ThreadUtils.i() || Build.VERSION.SDK_INT >= 26) {
            this.G = new SurfaceHolder$Callback2C0723Lw(this, this);
            if (Build.VERSION.SDK_INT >= 28) {
                this.V = new C0784Mw(this);
            }
            setBackgroundColor(AbstractC2934hr.b(getResources(), false));
            super.setVisibility(0);
            ((SurfaceHolder$Callback2C0723Lw) this.G).f(-1);
        }
    }

    public void d(boolean z) {
        if (this.T && this.U != z) {
            this.U = z;
            if (z) {
                N.Mlw_qgLA(this.K, this);
            }
            ((SurfaceHolder$Callback2C0723Lw) this.G).f(b());
        }
    }

    public final void didSwapBuffers(boolean z) {
        int i = this.f10632J;
        if (i > 1) {
            this.f10632J = i - 1;
            long j = this.K;
            if (j != 0) {
                N.M_Nkznfe(j, this);
            }
        } else if (i == 1) {
            this.f10632J = 0;
            N.MVesqb5U(this.K, this);
            SurfaceHolder$Callback2C0723Lw lw = (SurfaceHolder$Callback2C0723Lw) this.G;
            C0663Kw kw = lw.H;
            if (kw != null) {
                C0663Kw kw2 = lw.F;
                if (kw == kw2) {
                    kw2 = lw.G;
                }
                if (lw.I != kw2) {
                    lw.b(kw2);
                }
            }
        }
        if (z) {
            e();
        }
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.L;
        for (Runnable runnable : compositorViewHolder.w0) {
            runnable.run();
        }
        compositorViewHolder.w0.clear();
    }

    public final void didSwapFrame(int i) {
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.L;
        Objects.requireNonNull(compositorViewHolder);
        TraceEvent.h0("didSwapFrame");
        ViewGroup viewGroup = (ViewGroup) compositorViewHolder.b0;
        if (compositorViewHolder.l0) {
            compositorViewHolder.post(new RunnableC1333Vw(compositorViewHolder, viewGroup));
        }
        compositorViewHolder.l0 = true;
        compositorViewHolder.P = i;
        if (!compositorViewHolder.R || i == 0) {
            compositorViewHolder.a();
        }
        compositorViewHolder.R = !compositorViewHolder.R;
        compositorViewHolder.w0.addAll(compositorViewHolder.v0);
        compositorViewHolder.v0.clear();
    }

    public final void e() {
        List<Runnable> list = this.S;
        this.S = null;
        if (list != null) {
            for (Runnable runnable : list) {
                runnable.run();
            }
        }
    }

    public void f(Surface surface, int i, int i2, int i3) {
        CompositorView compositorView;
        long j = this.K;
        if (j != 0) {
            N.MH1eqy7s(j, this, i, i2, i3, !this.U, surface);
            CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.L;
            ViewGroup d = compositorViewHolder.d();
            WebContents q = compositorViewHolder.q();
            if (d != null && q != null && (compositorView = compositorViewHolder.M) != null) {
                N.MzYzRqF3(compositorView.K, compositorView, q, i2, i3);
            }
        }
    }

    public void g(Surface surface) {
        long j = this.K;
        if (j != 0) {
            N.MGPC4Ktv(j, this);
            this.f10632J = 2;
            CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.L;
            compositorViewHolder.P = 0;
            compositorViewHolder.a();
        }
    }

    public final void notifyWillUseSurfaceControl() {
        this.T = true;
    }

    public final void onCompositorLayout() {
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.L;
        Objects.requireNonNull(compositorViewHolder);
        TraceEvent.Y("CompositorViewHolder:layout", null);
        D70 d70 = compositorViewHolder.K;
        if (d70 != null) {
            TraceEvent.Y("LayoutDriver:onUpdate", null);
            long uptimeMillis = SystemClock.uptimeMillis();
            if (!d70.Y) {
                d70.l0.m(Long.valueOf(uptimeMillis));
            } else {
                d70.Y = false;
                C4316pw pwVar = d70.h0;
                Objects.requireNonNull(pwVar);
                long currentTimeMillis = System.currentTimeMillis();
                pwVar.e = currentTimeMillis;
                boolean a2 = pwVar.a(currentTimeMillis - pwVar.e);
                AbstractC2300e70 e70 = d70.S;
                if (e70 != null) {
                    boolean L2 = e70.L(uptimeMillis, false);
                    if (e70 == ((D70) e70.N).S) {
                        e70.T(uptimeMillis, 16);
                    }
                    if (L2 && a2) {
                        if (e70.Q) {
                            e70.h();
                        } else if (e70.R) {
                            e70.i();
                        }
                    }
                }
                for (int i = 0; i < d70.m0.size(); i++) {
                    ((LO0) d70.m0.get(i)).e(uptimeMillis, 16);
                }
                d70.l0.m(Long.valueOf(uptimeMillis));
            }
            TraceEvent.f0("LayoutDriver:onUpdate");
            compositorViewHolder.M.a(compositorViewHolder.K);
        }
        compositorViewHolder.v0.addAll(compositorViewHolder.u0);
        compositorViewHolder.u0.clear();
        TraceEvent.f0("CompositorViewHolder:layout");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.M = -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r9, int r10) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.CompositorView.onMeasure(int, int):void");
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        WindowAndroid windowAndroid = this.O;
        if (windowAndroid != null) {
            if (i == 8) {
                windowAndroid.y0(false);
            } else if (i == 0) {
                windowAndroid.y0(true);
            }
            X20 a2 = X20.a();
            a2.f = null;
            a2.g = null;
            a2.e = null;
        }
    }

    public final void recreateSurface() {
        SurfaceHolder$Callback2C0723Lw lw = (SurfaceHolder$Callback2C0723Lw) this.G;
        if (lw.H != null) {
            lw.K.post(new RunnableC0480Hw(lw));
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        SurfaceHolder$Callback2C0723Lw lw = (SurfaceHolder$Callback2C0723Lw) this.G;
        lw.F.f8395a.setBackgroundDrawable(drawable);
        lw.G.f8395a.setBackgroundDrawable(drawable);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        SurfaceHolder$Callback2C0723Lw lw = (SurfaceHolder$Callback2C0723Lw) this.G;
        lw.F.f8395a.setVisibility(i);
        lw.G.f8395a.setVisibility(i);
        if (i == 4) {
            e();
        }
    }

    public void setWillNotDraw(boolean z) {
        SurfaceHolder$Callback2C0723Lw lw = (SurfaceHolder$Callback2C0723Lw) this.G;
        lw.F.f8395a.setWillNotDraw(z);
        lw.G.f8395a.setWillNotDraw(z);
    }
}
