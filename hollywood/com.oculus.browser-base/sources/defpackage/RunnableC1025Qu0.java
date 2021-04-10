package defpackage;

import android.content.Context;
import org.chromium.components.page_info.ConnectionInfoView;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Qu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1025Qu0 implements Runnable {
    public final PageInfoController F;

    public RunnableC1025Qu0(PageInfoController pageInfoController) {
        this.F = pageInfoController;
    }

    public void run() {
        PageInfoController pageInfoController = this.F;
        if (!pageInfoController.H.g()) {
            pageInfoController.k(10);
            Context context = pageInfoController.F;
            WebContents webContents = pageInfoController.H;
            new ConnectionInfoView(context, webContents, new C0969Px((C2746gl0) ((C4985ts) pageInfoController.I).j.get(), webContents), pageInfoController.I.b);
        }
    }
}
