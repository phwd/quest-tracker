package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* renamed from: BA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BA extends WK {

    /* renamed from: a  reason: collision with root package name */
    public Tab f7720a;

    public BA(Tab tab) {
        this.f7720a = tab;
        tab.A(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        Objects.requireNonNull((KQ0) tab.M().c(KQ0.class));
        N.M440YM10(gurl);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void l(Tab tab) {
        ((KQ0) ((TabImpl) tab).j0.c(KQ0.class)).e();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }
}
