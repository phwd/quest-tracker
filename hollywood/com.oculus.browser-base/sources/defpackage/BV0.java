package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninFragment;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.chrome.browser.signin.services.SigninManager;

/* renamed from: BV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BV0 {

    /* renamed from: a  reason: collision with root package name */
    public static BV0 f7742a;

    public static BV0 a() {
        if (f7742a == null) {
            f7742a = new BV0();
        }
        return f7742a;
    }

    public boolean b(Context context, int i) {
        SigninManager d = C5949zZ.a().d(Profile.b());
        if (d.i()) {
            int i2 = SigninFragment.U0;
            Bundle f1 = SigninFragmentBase.f1(null);
            f1.putInt("SigninFragment.AccessPoint", i);
            c(context, f1);
            return true;
        } else if (!d.A()) {
            return false;
        } else {
            AbstractC1865bc0.e(context);
            return false;
        }
    }

    public final void c(Context context, Bundle bundle) {
        int i = AV0.W;
        Intent intent = new Intent(context, AV0.class);
        intent.putExtra("SigninActivity.FragmentArgs", bundle);
        context.startActivity(intent);
    }
}
