package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: aC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1623aC0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f9418a;
    public final Class b;
    public final String c;

    public C1623aC0(Tab tab, Class cls, String str) {
        this.f9418a = tab;
        this.b = cls;
        this.c = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tab tab = this.f9418a;
        Class cls = this.b;
        String str = this.c;
        AbstractC2145dC0 dc0 = (AbstractC2145dC0) obj;
        AbstractC2145dC0.p(dc0);
        AbstractC2145dC0.m(dc0, tab, cls, str);
    }
}
