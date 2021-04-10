package defpackage;

import android.net.Uri;
import android.view.View;
import java.util.List;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.WebContents;

/* renamed from: UB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class UB implements View.OnClickListener {
    public final WB F;

    public UB(WB wb) {
        this.F = wb;
    }

    public void onClick(View view) {
        WebContents l;
        ChromeActivity chromeActivity;
        String str;
        String s;
        WB wb = this.F;
        Tab d = wb.G.d();
        if (d != null && (l = d.l()) != null && (chromeActivity = (ChromeActivity) d.i().s0().get()) != null) {
            CustomTabToolbar customTabToolbar = wb.I;
            Tab d2 = customTabToolbar.f9169J.d();
            if (d2 != null) {
                String j = TrustedCdn.j(d2);
                if (j != null) {
                    s = AbstractC5154ur1.a(j);
                } else if (customTabToolbar.o0 == 1) {
                    s = d2.s();
                    List<String> pathSegments = Uri.parse(s).getPathSegments();
                    if (pathSegments.size() >= 3) {
                        if (pathSegments.get(1).length() > 1) {
                            s = pathSegments.get(1);
                        } else {
                            s = pathSegments.get(2);
                        }
                    }
                }
                str = s;
                PageInfoController.l(chromeActivity, l, str, 2, new C4985ts(chromeActivity, l, new VB(chromeActivity), new C1742as0(d)), new C0411Gs());
            }
            str = null;
            PageInfoController.l(chromeActivity, l, str, 2, new C4985ts(chromeActivity, l, new VB(chromeActivity), new C1742as0(d)), new C0411Gs());
        }
    }
}
