package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.content_public.browser.WebContents;

/* renamed from: AL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AL extends OK {
    public int F;
    public final /* synthetic */ DL G;

    public AL(DL dl) {
        this.G = dl;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void f(float f, float f2) {
        NL nl = this.G.P;
        if (nl != null) {
            View findViewById = nl.f.findViewById(R.id.open_in_new_tab);
            if (f > 0.5f) {
                if (findViewById.getVisibility() != 0) {
                    findViewById.setVisibility(0);
                }
                findViewById.setAlpha((f - 0.5f) * 2.0f);
            } else if (findViewById.getVisibility() != 8) {
                findViewById.setVisibility(8);
            }
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void g(int i) {
        DL dl = this.G;
        if (!dl.U) {
            JL jl = dl.L;
            jl.f = true;
            jl.b();
            this.G.U = true;
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void h(AbstractC4277pj pjVar) {
        DL dl = this.G;
        if (pjVar != dl.P) {
            dl.L.c(this.F);
            DL dl2 = this.G;
            dl2.T = false;
            dl2.P = null;
            WebContents webContents = dl2.N;
            if (webContents != null) {
                webContents.destroy();
                dl2.N = null;
                dl2.O = null;
            }
            IL il = dl2.M;
            if (il != null) {
                AbstractC6022zx1 zx1 = il.g;
                if (zx1 != null) {
                    zx1.destroy();
                    il.g = null;
                }
                il.h = null;
                il.e = null;
                il.f = null;
                il.i = null;
            }
            dl2.H.removeOnLayoutChangeListener(dl2);
            OK ok = dl2.Q;
            if (ok != null) {
                ((C5638xj) dl2.K).r(ok);
            }
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void i(int i) {
        DL dl = this.G;
        if (dl.P != null) {
            if (i != 1) {
                if (i == 3 && !dl.V) {
                    JL jl = dl.L;
                    jl.e = true;
                    if (jl.d == 0) {
                        jl.d = System.nanoTime();
                    }
                    jl.b();
                    this.G.V = true;
                }
            } else if (!dl.T) {
                JL jl2 = dl.L;
                jl2.e = true;
                if (jl2.b == 0) {
                    jl2.b = System.nanoTime();
                }
                jl2.a();
                this.G.T = true;
            }
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void k(int i) {
        this.F = i;
    }
}
