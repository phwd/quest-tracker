package defpackage;

import android.os.Handler;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: A91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class A91 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C91 f7656a;
    public final Callback b;

    public A91(C91 c91, Callback callback) {
        this.f7656a = c91;
        this.b = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C91 c91 = this.f7656a;
        Callback callback = this.b;
        C2361eV0 ev0 = (C2361eV0) obj;
        Objects.requireNonNull(c91);
        callback.onResult(ev0);
        new Handler().post(new B91(c91, ev0));
    }
}
