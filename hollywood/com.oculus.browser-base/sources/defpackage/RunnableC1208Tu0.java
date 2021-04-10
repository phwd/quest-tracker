package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* renamed from: Tu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1208Tu0 implements Runnable {
    public final PageInfoController F;

    public RunnableC1208Tu0(PageInfoController pageInfoController) {
        this.F = pageInfoController;
    }

    public void run() {
        PageInfoController pageInfoController = this.F;
        pageInfoController.S = new RunnableC1086Ru0(pageInfoController);
        pageInfoController.M.b(true);
    }
}
