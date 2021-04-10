package defpackage;

import J.N;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: Uu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1269Uu0 implements Runnable {
    public final PageInfoController F;

    public RunnableC1269Uu0(PageInfoController pageInfoController) {
        this.F = pageInfoController;
    }

    public void run() {
        CookieControlsBridge cookieControlsBridge = this.F.a0;
        if (cookieControlsBridge != null) {
            long j = cookieControlsBridge.f10833a;
            if (j != 0) {
                N.MGYjAHK4(j);
            }
        }
    }
}
