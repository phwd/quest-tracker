package defpackage;

import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Tp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Tp1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Wp1 f8987a;

    public Tp1(Wp1 wp1) {
        this.f8987a = wp1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tab tab;
        ChromeActivity chromeActivity;
        Tab tab2;
        Wp1 wp1 = this.f8987a;
        Objects.requireNonNull(wp1);
        int i = ((C5321vq1) obj).f11503a;
        if (i == 4) {
            ChromeActivity chromeActivity2 = wp1.G;
            if (chromeActivity2 != null) {
                C1184Ti1.b(chromeActivity2, chromeActivity2.getString(R.string.f53160_resource_name_obfuscated_RES_2131952633), 1).b.show();
            }
        } else if (i == 5) {
            ChromeActivity chromeActivity3 = wp1.G;
            if (chromeActivity3 != null && (tab = chromeActivity3.W0.H) != null) {
                WebContents l = tab.l();
                Up1 up1 = new Up1(wp1);
                ChromeActivity chromeActivity4 = wp1.G;
                SimpleConfirmInfoBarBuilder.a(l, up1, 89, chromeActivity4, R.drawable.f33230_resource_name_obfuscated_RES_2131231363, chromeActivity4.getString(R.string.f53150_resource_name_obfuscated_RES_2131952632), null, null, wp1.G.getString(R.string.f53140_resource_name_obfuscated_RES_2131952631), false);
            }
        } else if (i == 6 && (chromeActivity = wp1.G) != null && (tab2 = chromeActivity.W0.H) != null) {
            WebContents l2 = tab2.l();
            Vp1 vp1 = new Vp1(wp1);
            ChromeActivity chromeActivity5 = wp1.G;
            SimpleConfirmInfoBarBuilder.a(l2, vp1, 90, chromeActivity5, R.drawable.f33230_resource_name_obfuscated_RES_2131231363, chromeActivity5.getString(R.string.f53130_resource_name_obfuscated_RES_2131952630), wp1.G.getString(R.string.f63810_resource_name_obfuscated_RES_2131953698), wp1.G.getString(R.string.f48470_resource_name_obfuscated_RES_2131952164), null, false);
        }
    }
}
