package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: uQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5081uQ extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BQ f11409a;

    public C5081uQ(BQ bq) {
        this.f11409a = bq;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        this.f11409a.d(true);
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        BQ bq = this.f11409a;
        if (tab == bq.P) {
            bq.d(true);
        }
    }
}
