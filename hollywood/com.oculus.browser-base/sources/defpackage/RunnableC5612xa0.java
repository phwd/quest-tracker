package defpackage;

import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: xa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5612xa0 implements Runnable {
    public final View$OnKeyListenerC0001Aa0 F;

    public RunnableC5612xa0(View$OnKeyListenerC0001Aa0 aa0) {
        this.F = aa0;
    }

    public void run() {
        View$OnKeyListenerC0001Aa0 aa0 = this.F;
        ((TemplateUrlService) aa0.U.get()).b.b(aa0);
        aa0.N();
    }
}
