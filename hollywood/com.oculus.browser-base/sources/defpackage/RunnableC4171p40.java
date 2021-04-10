package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: p40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4171p40 implements Runnable {
    public final /* synthetic */ C4000o40 F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C5533x40 H;

    public RunnableC4171p40(C5533x40 x40, C4000o40 o40, int i) {
        this.H = x40;
        this.F = o40;
        this.G = i;
    }

    public void run() {
        RecyclerView recyclerView = this.H.r;
        if (recyclerView != null && recyclerView.isAttachedToWindow()) {
            C4000o40 o40 = this.F;
            if (!o40.P && o40.f10530J.e() != -1) {
                EW0 ew0 = this.H.r.y0;
                if (ew0 == null || !ew0.i(null)) {
                    C5533x40 x40 = this.H;
                    int size = x40.p.size();
                    boolean z = false;
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (!((C4000o40) x40.p.get(i)).Q) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (!z) {
                        this.H.m.n(this.F.f10530J, this.G);
                        return;
                    }
                }
                this.H.r.post(this);
            }
        }
    }
}
