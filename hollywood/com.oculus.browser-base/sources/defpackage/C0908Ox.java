package defpackage;

import org.chromium.content_public.browser.WebContents;

/* renamed from: Ox  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0908Ox extends AbstractC6022zx1 {
    public final /* synthetic */ C0969Px G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0908Ox(C0969Px px, WebContents webContents) {
        super(webContents);
        this.G = px;
    }

    @Override // defpackage.AbstractC6022zx1
    public void destroy() {
        super.destroy();
        this.G.a(0);
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        this.G.a(0);
    }
}
