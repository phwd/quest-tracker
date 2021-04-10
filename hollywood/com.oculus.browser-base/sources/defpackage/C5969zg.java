package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;

/* renamed from: zg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5969zg extends GL0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0018Ag f11759a;

    public C5969zg(AbstractC0018Ag ag, AbstractC5629xg xgVar) {
        this.f11759a = ag;
    }

    @Override // defpackage.GL0
    public void a() {
        for (AbstractC5799yg ygVar : this.f11759a.e) {
            AbstractC5289vg vgVar = (AbstractC5289vg) ygVar;
            if (vgVar.F != null) {
                vgVar.h();
                ChromeMediaRouterClient chromeMediaRouterClient = ChromeMediaRouterClient.f10694a;
                C0074Be0 a2 = vgVar.F.a();
                Objects.requireNonNull(chromeMediaRouterClient);
                AbstractC1384Wr.a(a2);
            }
        }
    }

    @Override // defpackage.GL0
    public void b() {
        this.f11759a.l();
    }
}
