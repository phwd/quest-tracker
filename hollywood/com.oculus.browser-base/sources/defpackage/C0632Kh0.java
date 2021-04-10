package defpackage;

import J.N;
import org.chromium.content.browser.MediaSessionImpl;

/* renamed from: Kh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0632Kh0 implements AbstractC0135Ce0 {
    public final /* synthetic */ C0936Ph0 F;

    public C0632Kh0(C0936Ph0 ph0) {
        this.F = ph0;
    }

    @Override // defpackage.AbstractC0135Ce0
    public void a(int i) {
        if (!this.F.i()) {
            Integer d = C0936Ph0.d(i);
            if (d != null) {
                AbstractC3364kK0.g("Media.Session.Pause", d.intValue(), 3);
            }
            MediaSessionImpl mediaSessionImpl = this.F.d.f8975a;
            if (mediaSessionImpl != null) {
                N.M5LC9gX$(mediaSessionImpl.f10915a, mediaSessionImpl);
            }
        }
    }

    @Override // defpackage.AbstractC0135Ce0
    public void b(long j) {
        AbstractC1180Th0 th0 = this.F.d;
        if (th0 != null) {
            MediaSessionImpl mediaSessionImpl = th0.f8975a;
            N.MK7btVfc(mediaSessionImpl.f10915a, mediaSessionImpl, j);
        }
    }

    @Override // defpackage.AbstractC0135Ce0
    public void c(int i) {
        if (!this.F.i()) {
            Integer d = C0936Ph0.d(i);
            if (d != null) {
                AbstractC3364kK0.g("Media.Session.Stop", d.intValue(), 3);
            }
            MediaSessionImpl mediaSessionImpl = this.F.d.f8975a;
            if (mediaSessionImpl != null) {
                N.MW5s36cs(mediaSessionImpl.f10915a, mediaSessionImpl);
            }
        }
    }

    @Override // defpackage.AbstractC0135Ce0
    public void d(int i) {
        AbstractC1180Th0 th0;
        if ((i >= 0 && i <= 12) && (th0 = this.F.d) != null) {
            MediaSessionImpl mediaSessionImpl = th0.f8975a;
            N.MAqRqyJa(mediaSessionImpl.f10915a, mediaSessionImpl, i);
        }
    }

    @Override // defpackage.AbstractC0135Ce0
    public void e(int i) {
        if (!this.F.i()) {
            Integer d = C0936Ph0.d(i);
            if (d != null) {
                AbstractC3364kK0.g("Media.Session.Play", d.intValue(), 3);
            }
            MediaSessionImpl mediaSessionImpl = this.F.d.f8975a;
            if (mediaSessionImpl != null) {
                N.MlezJYnz(mediaSessionImpl.f10915a, mediaSessionImpl);
            }
        }
    }
}
