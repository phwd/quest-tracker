package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: BX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BX implements Q31 {
    public final FX F;

    public BX(FX fx) {
        this.F = fx;
    }

    @Override // defpackage.Q31
    public Object get() {
        Tab tab = this.F.Q;
        return Boolean.valueOf(tab != null && tab.isNativePage());
    }
}
