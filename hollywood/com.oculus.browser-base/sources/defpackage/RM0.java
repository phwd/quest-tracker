package defpackage;

import org.chromium.content_public.browser.WebContents;

/* renamed from: RM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RM0 extends AbstractC6022zx1 {
    public final /* synthetic */ SM0 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RM0(SM0 sm0, WebContents webContents) {
        super(webContents);
        this.G = sm0;
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        this.G.b();
    }
}
