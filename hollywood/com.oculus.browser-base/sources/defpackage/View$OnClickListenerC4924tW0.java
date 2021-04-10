package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.signin.SigninFragment;

/* renamed from: tW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4924tW0 implements View.OnClickListener {
    public final C5264vW0 F;
    public final Context G;

    public View$OnClickListenerC4924tW0(C5264vW0 vw0, Context context) {
        this.F = vw0;
        this.G = context;
    }

    public void onClick(View view) {
        C5264vW0 vw0 = this.F;
        Context context = this.G;
        vw0.c();
        AbstractC3535lK0.a(vw0.j);
        BV0 bv0 = vw0.q;
        int i = vw0.d;
        String str = vw0.f11482a.f10337a;
        Objects.requireNonNull(bv0);
        int i2 = SigninFragment.U0;
        Bundle bundle = new Bundle();
        bundle.putInt("SigninFragmentBase.SigninFlowType", 2);
        bundle.putString("SigninFragmentBase.AccountName", str);
        bundle.putInt("SigninFragment.AccessPoint", i);
        bundle.putInt("SigninFragment.PersonalizedPromoAction", 2);
        bv0.c(context, bundle);
    }
}
