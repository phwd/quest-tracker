package defpackage;

import J.N;
import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: oC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4024oC0 implements TextWatcher {
    public boolean F;
    public String G;

    public void afterTextChanged(Editable editable) {
        if (!this.F) {
            String MDrtwxb3 = N.MDrtwxb3(editable.toString(), this.G);
            this.F = true;
            editable.replace(0, editable.length(), MDrtwxb3, 0, MDrtwxb3.length());
            this.F = false;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
