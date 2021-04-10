package defpackage;

import org.chromium.content_public.browser.WebContents;

/* renamed from: xc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5618xc0 extends AbstractC6022zx1 {
    public final /* synthetic */ C5958zc0 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5618xc0(C5958zc0 zc0, WebContents webContents) {
        super(webContents);
        this.G = zc0;
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasHidden() {
        this.G.e = false;
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasShown() {
        C5958zc0 zc0 = this.G;
        zc0.e = true;
        C4112ol olVar = zc0.d;
        if (olVar != null) {
            olVar.b(olVar.I);
        }
        for (int i : C5958zc0.f11755a) {
            C4112ol olVar2 = this.G.b(i).f11691a;
            if (olVar2 != null) {
                olVar2.b(olVar2.I);
            }
        }
    }
}
