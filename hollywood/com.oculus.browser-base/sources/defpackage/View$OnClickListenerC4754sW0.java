package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.signin.SigninFragment;
import org.chromium.chrome.browser.signin.SigninFragmentBase;

/* renamed from: sW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4754sW0 implements View.OnClickListener {
    public final C5264vW0 F;
    public final Context G;

    public View$OnClickListenerC4754sW0(C5264vW0 vw0, Context context) {
        this.F = vw0;
        this.G = context;
    }

    public void onClick(View view) {
        C5264vW0 vw0 = this.F;
        Context context = this.G;
        vw0.c();
        AbstractC3535lK0.a(vw0.i);
        BV0 bv0 = vw0.q;
        int i = vw0.d;
        String str = vw0.f11482a.f10337a;
        Objects.requireNonNull(bv0);
        int i2 = SigninFragment.U0;
        Bundle f1 = SigninFragmentBase.f1(str);
        f1.putInt("SigninFragment.AccessPoint", i);
        f1.putInt("SigninFragment.PersonalizedPromoAction", 1);
        bv0.c(context, f1);
    }
}
