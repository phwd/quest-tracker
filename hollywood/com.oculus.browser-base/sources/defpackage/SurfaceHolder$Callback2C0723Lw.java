package defpackage;

import J.N;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.chromium.chrome.browser.compositor.CompositorView;

/* renamed from: Lw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SurfaceHolder$Callback2C0723Lw implements SurfaceHolder.Callback2, AbstractC0419Gw {
    public final C0663Kw F;
    public final C0663Kw G;
    public C0663Kw H;
    public C0663Kw I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC0358Fw f8449J;
    public final ViewGroup K;

    public SurfaceHolder$Callback2C0723Lw(ViewGroup viewGroup, AbstractC0358Fw fw) {
        this.K = viewGroup;
        this.f8449J = fw;
        this.F = new C0663Kw(viewGroup.getContext(), -3, this);
        this.G = new C0663Kw(viewGroup.getContext(), -1, this);
    }

    public final void a(C0663Kw kw) {
        if (!kw.a() && !kw.c) {
            kw.b = true;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ViewGroup viewGroup = this.K;
            kw.g = viewGroup;
            viewGroup.addView(kw.f8395a, layoutParams);
            this.K.bringChildToFront(kw.f8395a);
            this.K.postInvalidateOnAnimation();
        }
    }

    public final void b(C0663Kw kw) {
        if (kw.a()) {
            kw.c = true;
            this.K.post(new RunnableC0602Jw(this, kw));
        }
    }

    public final void c(C0663Kw kw) {
        if (kw.a()) {
            boolean isValid = kw.b().getSurface().isValid();
            kw.c = isValid;
            StringBuilder i = AbstractC2531fV.i("SurfaceState : detach from parent : ");
            i.append(kw.d);
            AbstractC1220Ua0.a("CompositorSurfaceMgr", i.toString(), new Object[0]);
            ViewGroup viewGroup = kw.g;
            kw.g = null;
            viewGroup.removeView(kw.f8395a);
            if (isValid) {
                return;
            }
        }
        d(kw);
        C0663Kw kw2 = this.I;
        if (kw == kw2) {
            a(kw2);
        }
    }

    public final void d(C0663Kw kw) {
        C0663Kw kw2 = this.H;
        if (kw2 == kw && kw != null) {
            AbstractC0358Fw fw = this.f8449J;
            kw2.b().getSurface();
            CompositorView compositorView = (CompositorView) fw;
            long j = compositorView.K;
            if (j != 0) {
                N.MyANQhkH(j, compositorView);
            }
            this.H = null;
        }
    }

    public final C0663Kw e(SurfaceHolder surfaceHolder) {
        if (this.F.b() == surfaceHolder) {
            return this.F;
        }
        if (this.G.b() == surfaceHolder) {
            return this.G;
        }
        return null;
    }

    public void f(int i) {
        AbstractC1220Ua0.a("CompositorSurfaceMgr", AbstractC2531fV.w("Transitioning to surface with format : ", i), new Object[0]);
        C0663Kw kw = i == -3 ? this.F : this.G;
        this.I = kw;
        if (!kw.c) {
            if (!kw.a()) {
                a(this.I);
            } else if (!this.I.b) {
                d(this.H);
                C0663Kw kw2 = this.I;
                this.H = kw2;
                ((CompositorView) this.f8449J).g(kw2.b().getSurface());
                C0663Kw kw3 = this.H;
                if (kw3.d != 0) {
                    AbstractC0358Fw fw = this.f8449J;
                    Surface surface = kw3.b().getSurface();
                    C0663Kw kw4 = this.H;
                    ((CompositorView) fw).f(surface, kw4.d, kw4.e, kw4.f);
                }
            }
        }
    }

    public void g() {
        this.I = null;
        c(this.G);
        c(this.F);
        this.F.b().removeCallback(this);
        this.G.b().removeCallback(this);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        C0663Kw e = e(surfaceHolder);
        if (e == this.H && e == this.I) {
            e.e = i2;
            e.f = i3;
            e.d = i;
            ((CompositorView) this.f8449J).f(surfaceHolder.getSurface(), i, i2, i3);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        C0663Kw e = e(surfaceHolder);
        StringBuilder i = AbstractC2531fV.i("surfaceCreated format : ");
        i.append(e.d);
        AbstractC1220Ua0.a("CompositorSurfaceMgr", i.toString(), new Object[0]);
        if (e != this.I) {
            b(e);
            return;
        }
        e.b = false;
        e.d = 0;
        d(this.H);
        C0663Kw kw = this.I;
        this.H = kw;
        ((CompositorView) this.f8449J).g(kw.b().getSurface());
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C0663Kw e = e(surfaceHolder);
        StringBuilder i = AbstractC2531fV.i("surfaceDestroyed format : ");
        i.append(e.d);
        AbstractC1220Ua0.a("CompositorSurfaceMgr", i.toString(), new Object[0]);
        if (!e.c) {
            e.b = true;
        } else if (!e.a()) {
            e.c = false;
        }
        e.d = 0;
        C0663Kw kw = this.H;
        if (e == kw) {
            d(kw);
            return;
        }
        CompositorView compositorView = (CompositorView) this.f8449J;
        N.MVesqb5U(compositorView.K, compositorView);
        if (e == this.I && !e.a()) {
            e.b = true;
            this.K.post(new RunnableC0541Iw(this, e));
        } else if (e != this.I && e.a()) {
            b(e);
        }
    }

    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        CompositorView compositorView = (CompositorView) this.f8449J;
        if (compositorView.S == null) {
            compositorView.S = new ArrayList();
        }
        compositorView.S.add(runnable);
        long j = compositorView.K;
        if (j != 0) {
            N.M_Nkznfe(j, compositorView);
        }
    }
}
