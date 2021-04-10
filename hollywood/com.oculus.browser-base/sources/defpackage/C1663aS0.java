package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/* renamed from: aS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1663aS0 implements TextWatcher {
    public final /* synthetic */ AbstractView$OnClickListenerC2014cS0 F;

    public C1663aS0(AbstractView$OnClickListenerC2014cS0 cs0) {
        this.F = cs0;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.F.B0.setVisibility(TextUtils.isEmpty(charSequence) ? 4 : 0);
        AbstractView$OnClickListenerC2014cS0 cs0 = this.F;
        if (cs0.x0) {
            ((IC0) cs0.C0).N.t(charSequence.toString());
        }
    }
}
