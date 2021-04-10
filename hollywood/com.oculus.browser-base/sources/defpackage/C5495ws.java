package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* renamed from: ws  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5495ws extends AbstractC0228Ds {

    /* renamed from: a  reason: collision with root package name */
    public final Ao1 f11576a = new Ao1();
    public final RenderFrameHost b;

    public C5495ws(RenderFrameHost renderFrameHost, AbstractC5325vs vsVar) {
        this.b = renderFrameHost;
    }

    public String b() {
        WebContents a2 = FA0.a(this.b);
        if (!(a2 == null || ChromeActivity.J0(a2) == null)) {
            Objects.requireNonNull(this.f11576a);
        }
        return null;
    }

    public boolean c() {
        Profile a2;
        WebContents a3 = FA0.a(this.b);
        if (a3 == null || (a2 = Profile.a(a3)) == null) {
            return true;
        }
        return a2.g();
    }

    public boolean d() {
        WebContents a2 = FA0.a(this.b);
        return a2 != null && N.MzIXnlkD(Wr1.a(Profile.a(a2)).f10883a, "payments.can_make_payment_enabled");
    }
}
