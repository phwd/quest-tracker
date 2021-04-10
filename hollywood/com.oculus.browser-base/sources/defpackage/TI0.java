package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: TI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TI0 extends C2800h3 {
    public final String d;
    public final int e;

    public TI0(Tab tab, String str, int i) {
        super(tab, null);
        this.d = str;
        this.e = i;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid, defpackage.C2800h3
    public int a() {
        return this.e;
    }

    @Override // defpackage.AbstractC0133Cd1
    public String getManifestScope() {
        return this.d;
    }
}
