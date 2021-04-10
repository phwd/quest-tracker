package defpackage;

import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: Hk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Hk1 implements AbstractC0383Gf1 {
    public TemplateUrl F;
    public final /* synthetic */ TemplateUrlService G;
    public final /* synthetic */ Uk1 H;

    public Hk1(Uk1 uk1, TemplateUrlService templateUrlService) {
        this.H = uk1;
        this.G = templateUrlService;
        this.F = templateUrlService.a();
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        TemplateUrl a2 = this.G.a();
        TemplateUrl templateUrl = this.F;
        if (templateUrl != null || a2 != null) {
            if (templateUrl == null || !templateUrl.equals(a2)) {
                this.F = a2;
                this.H.K.f9104a.t();
            }
        }
    }
}
