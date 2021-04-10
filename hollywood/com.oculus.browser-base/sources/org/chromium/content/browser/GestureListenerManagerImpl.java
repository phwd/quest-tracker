package org.chromium.content.browser;

import J.N;
import android.view.View;
import com.oculus.os.Version;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GestureListenerManagerImpl extends Vy1 implements AbstractC4751sV, Qr1 {
    public final WebContentsImpl F;
    public final C1322Vq0 G;
    public final C1261Uq0 H;
    public ViewAndroidDelegate I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC2432eu1 f10912J;
    public long K;
    public boolean L;
    public boolean M;

    public GestureListenerManagerImpl(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.F = webContentsImpl;
        C1322Vq0 vq0 = new C1322Vq0();
        this.G = vq0;
        this.H = vq0.e();
        this.I = webContentsImpl.F();
        Zy1.t0(webContentsImpl).r0(this);
        this.K = N.MefCIE9S(this, webContentsImpl);
    }

    public static GestureListenerManagerImpl s0(WebContents webContents) {
        return (GestureListenerManagerImpl) ((WebContentsImpl) webContents).v0(GestureListenerManagerImpl.class, AbstractC5091uV.f11414a);
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    public final boolean filterTapOrPressEvent(int i, int i2, int i3) {
        return i == 5 && this.I.getContainerView().performLongClick();
    }

    @Override // defpackage.AbstractC4751sV
    public boolean isScrollInProgress() {
        return this.L;
    }

    public final void onEventAck(int i, boolean z) {
        if (i == 16) {
            this.H.b();
            while (this.H.hasNext()) {
                Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
            }
        } else if (i == 17) {
            this.H.b();
            while (this.H.hasNext()) {
                Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
            }
        } else if (i == 21) {
            SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(this.F);
            if (r != null) {
                r.o();
            }
            this.H.b();
            while (this.H.hasNext()) {
                Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
            }
        } else if (i != 23) {
            switch (i) {
                case Version.VERSION_11:
                    v0(true);
                    this.H.b();
                    while (this.H.hasNext()) {
                        ((AbstractC5601xV) this.H.next()).a(z0(), y0());
                    }
                    return;
                case Version.VERSION_12:
                    x0();
                    return;
                case Version.VERSION_13:
                    if (z) {
                        SelectionPopupControllerImpl r2 = SelectionPopupControllerImpl.r(this.F);
                        if (r2 != null) {
                            r2.o();
                        }
                        this.H.b();
                        while (this.H.hasNext()) {
                            ((AbstractC5601xV) this.H.next()).d();
                        }
                        return;
                    }
                    return;
                case Version.VERSION_14:
                    if (z) {
                        this.M = true;
                        this.H.b();
                        while (this.H.hasNext()) {
                            ((AbstractC5601xV) this.H.next()).b(z0(), y0());
                        }
                        return;
                    }
                    x0();
                    return;
                default:
                    return;
            }
        } else if (z) {
            this.I.getContainerView().performHapticFeedback(0);
            this.H.b();
            while (this.H.hasNext()) {
                Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
            }
        }
    }

    public final void onFlingEnd() {
        this.M = false;
        this.H.b();
        while (this.H.hasNext()) {
            ((AbstractC5601xV) this.H.next()).f(z0(), y0());
        }
    }

    public final void onNativeDestroyed() {
        this.H.b();
        while (this.H.hasNext()) {
            Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
        }
        this.G.clear();
        this.K = 0;
    }

    public final void onRootScrollOffsetChanged(float f, float f2) {
        t0(this.F.M.g, f, f2);
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onWindowFocusChanged(boolean z) {
        if (!z) {
            long j = this.K;
            if (j != 0) {
                N.MMR0DKoy(j, this);
            }
        }
        this.H.b();
        while (this.H.hasNext()) {
            Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
        }
    }

    public void r0(AbstractC5601xV xVVar) {
        boolean b = this.G.b(xVVar);
        long j = this.K;
        if (j != 0 && b && (xVVar instanceof AbstractC5771yV)) {
            N.M9FEGIKH(j, true);
        }
    }

    public final void resetPopupsAndInput(boolean z) {
        ImeAdapterImpl s0;
        WebContentsImpl webContentsImpl = this.F;
        if (webContentsImpl != null) {
            SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(webContentsImpl);
            if (r != null) {
                r.Y = true;
                r.c();
            }
            EE0 c = EE0.c(webContentsImpl);
            if (c != null) {
                c.e();
            }
        }
        if (isScrollInProgress()) {
            boolean z2 = this.L;
            v0(false);
            if (z2) {
                x0();
            }
            if (this.M) {
                onFlingEnd();
                this.M = false;
            }
        }
        if (z && (s0 = ImeAdapterImpl.s0(this.F)) != null) {
            s0.y0();
        }
    }

    public final void t0(float f, float f2, float f3) {
        TraceEvent.Y("GestureListenerManagerImpl:onRootScrollOffsetChanged", null);
        TL0 tl0 = this.F.M;
        AbstractC2432eu1 eu1 = this.f10912J;
        float f4 = tl0.g;
        float f5 = tl0.j;
        ((AbstractViewGroup$OnHierarchyChangeListenerC1520Yy) eu1).onScrollChanged((int) (f2 * f4 * f5), (int) (f4 * f3 * f5), (int) tl0.c(), (int) tl0.d());
        TL0 tl02 = this.F.M;
        tl02.g = f;
        tl02.f8952a = f2;
        tl02.b = f3;
        w0(z0(), y0());
        TraceEvent.f0("GestureListenerManagerImpl:onRootScrollOffsetChanged");
    }

    public void u0(AbstractC5601xV xVVar) {
        boolean z;
        boolean c = this.G.c(xVVar);
        if (this.K != 0 && c && (xVVar instanceof AbstractC5771yV)) {
            Iterator it = this.G.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    if (((AbstractC5601xV) uq0.next()) instanceof AbstractC5771yV) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                N.M9FEGIKH(this.K, false);
            }
        }
    }

    public final void updateOnTouchDown() {
        this.H.b();
        while (this.H.hasNext()) {
            ((AbstractC5601xV) this.H.next()).c();
        }
    }

    public final void updateScrollInfo(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z) {
        TraceEvent.Y("GestureListenerManagerImpl:updateScrollInfo", null);
        TL0 tl0 = this.F.M;
        float f11 = tl0.j;
        View containerView = this.I.getContainerView();
        float f12 = f11 * f3;
        float max = Math.max(f6, ((float) containerView.getWidth()) / f12);
        float max2 = Math.max(f7, ((float) containerView.getHeight()) / f12);
        boolean z2 = false;
        boolean z3 = (f4 == tl0.h && f5 == tl0.i) ? false : true;
        if (!(!(f3 != tl0.g) && f == tl0.f8952a && f2 == tl0.b)) {
            z2 = true;
        }
        if (z2) {
            t0(f3, f, f2);
        }
        tl0.h = f4;
        tl0.i = f5;
        tl0.k = f10;
        tl0.c = max;
        tl0.d = max2;
        tl0.e = f8;
        tl0.f = f9;
        if (!z2 && z) {
            w0(z0(), y0());
        }
        if (z3) {
            this.H.b();
            while (this.H.hasNext()) {
                Objects.requireNonNull((AbstractC5431wV) ((AbstractC5601xV) this.H.next()));
            }
        }
        TraceEvent.f0("GestureListenerManagerImpl:updateScrollInfo");
    }

    public final void v0(boolean z) {
        this.L = z;
        SelectionPopupControllerImpl.r(this.F).v(isScrollInProgress());
    }

    public void w0(int i, int i2) {
        this.H.b();
        while (this.H.hasNext()) {
            AbstractC5601xV xVVar = (AbstractC5601xV) this.H.next();
            if (xVVar instanceof AbstractC5771yV) {
                C4343q41 q41 = (C4343q41) ((AbstractC5771yV) xVVar);
                q41.f11114a = i;
                Objects.requireNonNull(q41.d);
                AbstractC4854t41 t41 = q41.d;
                if (t41.I != 0 && AbstractC4854t41.a(t41)) {
                    Objects.requireNonNull(q41.d);
                    q41.h(i, i2);
                }
            }
        }
    }

    public void x0() {
        v0(false);
        this.H.b();
        while (this.H.hasNext()) {
            ((AbstractC5601xV) this.H.next()).e(z0(), y0());
        }
    }

    public final int y0() {
        return this.F.M.b();
    }

    public final int z0() {
        return this.F.M.e();
    }
}
