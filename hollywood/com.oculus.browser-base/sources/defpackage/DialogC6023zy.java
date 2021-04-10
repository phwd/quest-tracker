package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.content.browser.ContactsDialogHost;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: zy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC6023zy extends DialogC2461f4 implements AbstractC0118By {
    public IC0 I;

    public DialogC6023zy(WindowAndroid windowAndroid, C0472Hs hs, ContactsDialogHost contactsDialogHost, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str) {
        super((Context) windowAndroid.f11022J.get(), R.style.f72740_resource_name_obfuscated_RES_2132017847);
        IC0 ic0 = new IC0(windowAndroid, hs, z, z2, z3, z4, z5, z6, str, this);
        this.I = ic0;
        ic0.G = this;
        ic0.f8206J = contactsDialogHost;
        setOnCancelListener(new DC0(ic0));
        ic0.N.F.b();
        IC0 ic02 = this.I;
        C2120d4 d4Var = this.H;
        d4Var.h = ic02;
        d4Var.i = 0;
        d4Var.n = false;
    }
}
