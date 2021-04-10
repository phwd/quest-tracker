package defpackage;

import android.hardware.fingerprint.FingerprintManager;
import com.oculus.browser.R;

/* renamed from: Tk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1188Tk0 extends FingerprintManager.AuthenticationCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View$OnClickListenerC1249Uk0 f8979a;

    public C1188Tk0(View$OnClickListenerC1249Uk0 uk0) {
        this.f8979a = uk0;
    }

    public void onAuthenticationError(int i, CharSequence charSequence) {
        this.f8979a.d(new C1127Sk0(this), charSequence, null);
    }

    public void onAuthenticationFailed() {
        View$OnClickListenerC1249Uk0.a(this.f8979a, null, Integer.valueOf((int) R.string.f58220_resource_name_obfuscated_RES_2131953139));
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
        View$OnClickListenerC1249Uk0.a(this.f8979a, charSequence, null);
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        this.f8979a.e();
    }
}
