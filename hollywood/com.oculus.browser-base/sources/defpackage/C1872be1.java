package defpackage;

import J.N;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewPropertyAnimator;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;

/* renamed from: be1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1872be1 extends Pr1 {
    public Tab F;
    public AbstractC1404Xa1 G;
    public AbstractC0072Bd1 H;
    public PaintPreviewTabService I;

    /* renamed from: J  reason: collision with root package name */
    public C3859nE0 f9550J;
    public C0090Bk K;
    public Runnable L;
    public Callback M;
    public boolean N;
    public boolean O;
    public int P = -1;

    public C1872be1(Tab tab) {
        this.F = tab;
        this.H = new C1692ae1(this, null);
        this.I = (PaintPreviewTabService) N.M$ZOxizP();
        this.G = new C1474Yd1(this);
    }

    public static void c(C1872be1 be1, boolean z) {
        Callback callback = be1.M;
        if (callback != null) {
            callback.onResult(Boolean.valueOf(z));
        }
    }

    public static C1872be1 e(Tab tab) {
        if (tab.M().c(C1872be1.class) == null) {
            tab.M().e(C1872be1.class, new C1872be1(tab));
        }
        return (C1872be1) tab.M().c(C1872be1.class);
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        this.F.I(this.G);
        this.F = null;
    }

    public boolean h() {
        Tab tab = this.F;
        if (tab == null) {
            return false;
        }
        return ((C0011Ad1) tab.C()).b(this.H);
    }

    public boolean j(AbstractC3688mE0 me0) {
        boolean z;
        boolean z2;
        if (this.N) {
            return true;
        }
        PaintPreviewTabService paintPreviewTabService = this.I;
        int id = this.F.getId();
        long j = paintPreviewTabService.c;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i == 0) {
            z = false;
        } else {
            if (paintPreviewTabService.d != null) {
                if (i == 0) {
                    z2 = false;
                } else {
                    z2 = N.MWP3QaBv(j);
                }
                if (!z2) {
                    z = paintPreviewTabService.d.contains(Integer.valueOf(id));
                } else {
                    paintPreviewTabService.d = null;
                }
            }
            z = N.MYa8QxsY(paintPreviewTabService.c, id);
        }
        if (!z) {
            return false;
        }
        this.F.A(this.G);
        N.MJ3oAy5s();
        this.f9550J = new C3859nE0(this.F.getUrl(), this.F.getContext(), this.I, String.valueOf(this.F.getId()), me0, AbstractC2934hr.b(this.F.getContext().getResources(), false), false);
        ((C0011Ad1) this.F.C()).a(this.H);
        this.N = true;
        return true;
    }

    public void k(boolean z) {
        C3859nE0 ne0;
        Point point;
        N.M9gwtxem();
        if (this.F != null && (ne0 = this.f9550J) != null && !this.O) {
            this.O = true;
            PD0 pd0 = ne0.d;
            if (pd0 != null) {
                pd0.b(false);
            }
            this.F.I(this.G);
            PD0 pd02 = this.f9550J.d;
            if (pd02 == null) {
                point = null;
            } else {
                Rect a2 = pd02.f8676a.i.a();
                float c = pd02.f8676a.i.c();
                if (c == 0.0f) {
                    c = 1.0f;
                }
                point = new Point((int) (((float) a2.left) / c), (int) (((float) a2.top) / c));
            }
            C3859nE0 ne02 = this.f9550J;
            AbstractC5900zD0 zd0 = ne02.c;
            long j = 0;
            if (zd0 != null) {
                PlayerCompositorDelegateImpl playerCompositorDelegateImpl = (PlayerCompositorDelegateImpl) zd0;
                long j2 = playerCompositorDelegateImpl.b;
                if (j2 != 0) {
                    N.MIRVkfIx(j2);
                    playerCompositorDelegateImpl.b = 0;
                }
                ne02.c = null;
            }
            PD0 pd03 = ne02.d;
            if (pd03 != null) {
                pd03.a();
                ne02.d = null;
            }
            if (!(this.F.l() == null || point == null)) {
                this.F.l().n0().f((float) point.x, (float) point.y);
            }
            ViewPropertyAnimator alpha = this.H.b().animate().alpha(0.0f);
            if (z) {
                j = 500;
            }
            alpha.setDuration(j).setListener(new C1535Zd1(this));
            Runnable runnable = this.L;
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
