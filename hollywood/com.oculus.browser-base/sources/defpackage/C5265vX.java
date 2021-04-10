package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: vX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5265vX implements Q31 {
    public final FX F;

    public C5265vX(FX fx) {
        this.F = fx;
    }

    @Override // defpackage.Q31
    public Object get() {
        Tab tab = this.F.Q;
        return Boolean.valueOf(tab != null && tab.isNativePage());
    }
}
