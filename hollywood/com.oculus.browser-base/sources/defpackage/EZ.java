package defpackage;

import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: EZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EZ extends AbstractC6022zx1 {
    public final /* synthetic */ FZ G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EZ(FZ fz, WebContents webContents) {
        super(webContents);
        this.G = fz;
    }

    @Override // defpackage.AbstractC6022zx1
    public void a(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            this.G.I.destroy();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void destroy() {
        super.destroy();
        FZ fz = this.G;
        fz.G.b(fz.H, 2);
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        this.G.I.destroy();
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasHidden() {
        this.G.I.destroy();
    }
}
