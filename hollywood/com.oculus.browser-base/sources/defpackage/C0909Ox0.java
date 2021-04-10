package defpackage;

import J.N;
import android.content.Context;
import android.os.Bundle;
import org.chromium.chrome.browser.password_check.PasswordCheckBridge;
import org.chromium.chrome.browser.password_check.PasswordCheckFragmentView;

/* renamed from: Ox0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0909Ox0 {

    /* renamed from: a  reason: collision with root package name */
    public final PasswordCheckBridge f8657a = new PasswordCheckBridge(this);
    public final C1322Vq0 b = new C1322Vq0();
    public final C2528fT0 c;

    public C0909Ox0(C2528fT0 ft0) {
        this.c = ft0;
    }

    public void a(Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("password-check-referrer", i);
        this.c.b(context, PasswordCheckFragmentView.class, bundle);
        N.M9QKlyGA(this.f8657a.f10731a);
    }
}
