package defpackage;

import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: Df1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0200Df1 implements Runnable {
    public final TemplateUrlService F;
    public final AbstractC0322Ff1 G;

    public RunnableC0200Df1(TemplateUrlService templateUrlService, AbstractC0322Ff1 ff1) {
        this.F = templateUrlService;
        this.G = ff1;
    }

    public void run() {
        TemplateUrlService templateUrlService = this.F;
        AbstractC0322Ff1 ff1 = this.G;
        if (templateUrlService.f10887a.F.contains(ff1)) {
            ff1.f();
        }
    }
}
