package defpackage;

import android.os.Handler;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Jz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0609Jz0 extends AbstractC6022zx1 {
    public final UH0 G;
    public Handler H;
    public final WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC0548Iz0 f8331J;

    public C0609Jz0(UH0 uh0, WebContents webContents, AbstractC0548Iz0 iz0) {
        super(webContents);
        this.I = webContents;
        this.G = uh0;
        this.f8331J = iz0;
    }

    public final void b(int i) {
        this.G.l(AbstractC0670Kz0.e, MR0.b(i, ((C0426Gz0) this.f8331J).e, false));
        this.G.m(AbstractC0670Kz0.f, ((C0426Gz0) this.f8331J).d.getResources().getString(MR0.a(i)));
    }

    @Override // defpackage.AbstractC6022zx1
    public void didChangeVisibleSecurityState() {
        b(LR0.a(((C0426Gz0) this.f8331J).c));
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFailLoad(boolean z, int i, GURL gurl) {
        this.G.j(AbstractC0670Kz0.d, false);
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishLoad(long j, GURL gurl, boolean z, boolean z2) {
        Handler handler = new Handler();
        this.H = handler;
        handler.postDelayed(new RunnableC0487Hz0(this), 64);
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f && navigationHandle.f10940a) {
            this.G.m(AbstractC0670Kz0.f8397a, this.I.u());
            this.G.j(AbstractC0670Kz0.d, false);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !navigationHandle.c) {
            b(0);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void loadProgressChanged(float f) {
        if (((double) f) != 1.0d) {
            Handler handler = this.H;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                this.H = null;
            }
            this.G.j(AbstractC0670Kz0.d, true);
            this.G.k(AbstractC0670Kz0.c, Math.max(f, 0.05f));
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void titleWasSet(String str) {
        this.G.m(AbstractC0670Kz0.b, str);
    }
}
