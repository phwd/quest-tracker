package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* renamed from: Pu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0964Pu0 implements Runnable {
    public final PageInfoController F;

    public RunnableC0964Pu0(PageInfoController pageInfoController) {
        this.F = pageInfoController;
    }

    public void run() {
        PageInfoController pageInfoController = this.F;
        AbstractC0174Cv0 cv0 = pageInfoController.W;
        if (cv0 != null) {
            cv0.d();
            pageInfoController.W = null;
        }
    }
}
