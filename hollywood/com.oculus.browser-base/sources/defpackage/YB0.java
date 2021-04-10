package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: YB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class YB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9259a;
    public final Tab b;
    public final Class c;
    public final String d;
    public final AbstractC3340kC0 e;
    public final EnumC3169jC0 f;

    public YB0(Callback callback, Tab tab, Class cls, String str, AbstractC3340kC0 kc0, EnumC3169jC0 jc0) {
        this.f9259a = callback;
        this.b = tab;
        this.c = cls;
        this.d = str;
        this.e = kc0;
        this.f = jc0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f9259a;
        Tab tab = this.b;
        Class cls = this.c;
        String str = this.d;
        AbstractC3340kC0 kc0 = this.e;
        EnumC3169jC0 jc0 = this.f;
        byte[] bArr = (byte[]) obj;
        if (bArr == null) {
            callback.onResult(new C1623aC0(tab, cls, str));
            return;
        }
        AbstractC2145dC0 a2 = kc0.a(bArr, jc0.b(), jc0.R);
        if (a2.l()) {
            callback.onResult(new C1803bC0(tab, cls, str));
        } else {
            AbstractC2145dC0.m(a2, tab, cls, str);
        }
    }
}
