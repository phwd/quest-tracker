package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* renamed from: o61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4007o61 implements Runnable {
    public final C4349q61 F;
    public final GURL G;
    public final String H;
    public final C0695Li I;

    public RunnableC4007o61(C4349q61 q61, GURL gurl, String str, C0695Li li) {
        this.F = q61;
        this.G = gurl;
        this.H = str;
        this.I = li;
    }

    public void run() {
        C4349q61 q61 = this.F;
        GURL gurl = this.G;
        String str = this.H;
        C0695Li li = this.I;
        Objects.requireNonNull(q61);
        AbstractC1243Ui.b(gurl.h(), str, (View$OnClickListenerC5098uY0) q61.f.get(), li, q61.f11116a.getContext());
        Um1.a(Profile.b()).notifyEvent("read_later_context_menu_tapped");
        li.a();
    }
}
