package defpackage;

import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: Ef1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0261Ef1 implements AbstractC0322Ff1 {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ TemplateUrlService G;

    public C0261Ef1(TemplateUrlService templateUrlService, Runnable runnable) {
        this.G = templateUrlService;
        this.F = runnable;
    }

    @Override // defpackage.AbstractC0322Ff1
    public void f() {
        this.G.k(this);
        this.F.run();
    }
}
