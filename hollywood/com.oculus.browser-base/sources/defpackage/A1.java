package defpackage;

import android.content.Intent;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.signin.SigninUtils;

/* renamed from: A1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class A1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final D1 f7649a;
    public final Callback b;

    public A1(D1 d1, Callback callback) {
        this.f7649a = d1;
        this.b = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        D1 d1 = this.f7649a;
        Callback callback = this.b;
        Intent intent = (Intent) obj;
        if (intent != null) {
            d1.f7856a.F0(intent, new C1(d1, callback), null);
            return;
        }
        SigninUtils.a(d1.b);
    }
}
