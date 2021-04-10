package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* renamed from: Cu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0172Cu0 implements Runnable {
    public final C0233Du0 F;

    public RunnableC0172Cu0(C0233Du0 du0) {
        this.F = du0;
    }

    public void run() {
        C0233Du0 du0 = this.F;
        ((PageInfoController) du0.F).k(10);
        ((PageInfoController) du0.F).j(du0);
    }
}
