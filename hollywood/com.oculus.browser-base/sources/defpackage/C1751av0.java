package defpackage;

import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: av0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1751av0 extends AbstractC6022zx1 {
    public final /* synthetic */ PageInfoController G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1751av0(PageInfoController pageInfoController, WebContents webContents) {
        super(webContents);
        this.G = pageInfoController;
    }

    @Override // defpackage.AbstractC6022zx1
    public void a(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            PageInfoController.b(this.G);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void destroy() {
        super.destroy();
        PageInfoController.b(this.G);
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        this.G.M.b(true);
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasHidden() {
        this.G.M.b(true);
    }
}
