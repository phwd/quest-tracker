package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: U30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U30 extends AbstractC4110ok0 {
    public final C4352q71 c;
    public final Tm1 d;

    public U30(C4352q71 q71) {
        super(2);
        this.c = q71;
        Profile b = Profile.b();
        this.d = Um1.a((Profile) N.MD_ez$kP(b.b, b));
    }

    @Override // defpackage.AbstractC4110ok0
    public void a(AbstractC3939nk0 nk0) {
        this.f10572a.b(nk0);
        this.d.a(new Q30(this));
    }
}
