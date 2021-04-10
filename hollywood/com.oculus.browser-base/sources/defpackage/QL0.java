package defpackage;

import J.N;
import com.google.android.gms.cast.MediaStatus;
import java.lang.ref.WeakReference;
import org.chromium.components.media_router.FlingingControllerBridge;
import org.chromium.components.media_router.MediaStatusBridge;

/* renamed from: QL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QL0 extends AbstractC0018Ag {
    public C3377kR f;

    public QL0(AbstractC5474wl wlVar) {
        super(wlVar);
        new OL0(this);
        new WeakReference(this);
    }

    @Override // defpackage.AbstractC0018Ag
    public void a(C2922hn hnVar) {
        super.a(hnVar);
        try {
            this.f7686a.i("urn:x-cast:com.google.cast.media", new PL0(this));
        } catch (Exception e) {
            AbstractC1220Ua0.a("RmtSessionCtrl", "Failed to register namespace listener for %s", "urn:x-cast:com.google.cast.media", e);
        }
    }

    @Override // defpackage.AbstractC0018Ag
    public void k() {
        super.k();
        C2653gB gBVar = this.c;
        this.f = new C3377kR(this, ((NL0) (gBVar != null ? gBVar.f9983a : null)).c);
    }

    @Override // defpackage.AbstractC0018Ag
    public void l() {
        C3377kR kRVar = this.f;
        if (kRVar.d != null) {
            ML0 e = kRVar.b.e();
            MediaStatus d = e.d();
            if (d != null) {
                kRVar.f = true;
                if (d.f9645J == 1 && d.K == 1) {
                    kRVar.e = false;
                    N21 n21 = kRVar.f10277a;
                    n21.d = false;
                    n21.b = n21.f8523a;
                    n21.c = System.currentTimeMillis();
                } else {
                    N21 n212 = kRVar.f10277a;
                    long f2 = e.f();
                    long b = e.b();
                    boolean l = e.l();
                    double d2 = d.I;
                    n212.f8523a = f2;
                    n212.b = b;
                    n212.d = l;
                    n212.e = d2;
                    n212.c = System.currentTimeMillis();
                }
                FlingingControllerBridge flingingControllerBridge = kRVar.d;
                MediaStatusBridge mediaStatusBridge = new MediaStatusBridge(d);
                long j = flingingControllerBridge.b;
                if (j != 0) {
                    N.MR1NlI2Y(j, flingingControllerBridge, mediaStatusBridge);
                }
            } else if (kRVar.f) {
                kRVar.e = false;
                N21 n213 = kRVar.f10277a;
                n213.f8523a = 0;
                n213.b = 0;
                n213.c = 0;
                n213.d = false;
                n213.e = 1.0d;
            }
        }
        super.l();
    }
}
