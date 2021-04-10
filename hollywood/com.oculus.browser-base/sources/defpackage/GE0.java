package defpackage;

import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* renamed from: GE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GE0 extends AbstractC6022zx1 {
    public boolean G;
    public final /* synthetic */ WebContents H;
    public final /* synthetic */ IE0 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GE0(IE0 ie0, WebContents webContents, WebContents webContents2) {
        super(webContents);
        this.I = ie0;
        this.H = webContents2;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (!this.G || !navigationHandle.f || !navigationHandle.f10940a || navigationHandle.c || this.I.d == null) {
            this.G = true;
            return;
        }
        this.H.Q(this);
        this.I.b();
    }

    @Override // defpackage.AbstractC6022zx1
    public void documentLoadedInFrame(long j, boolean z) {
        if (z) {
            IE0 ie0 = this.I;
            if (ie0.d == null) {
                ie0.c(this.H);
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderProcessGone(boolean z) {
        this.I.b();
    }
}
