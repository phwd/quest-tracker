package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* renamed from: Nu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0842Nu0 implements Runnable {
    public final PageInfoController F;

    public RunnableC0842Nu0(PageInfoController pageInfoController) {
        this.F = pageInfoController;
    }

    public void run() {
        PageInfoController pageInfoController = this.F;
        pageInfoController.S = new RunnableC1025Qu0(pageInfoController);
        pageInfoController.M.b(true);
    }
}
