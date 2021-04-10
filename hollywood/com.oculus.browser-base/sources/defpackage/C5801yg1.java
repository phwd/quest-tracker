package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: yg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5801yg1 implements TextWatcher {
    public final /* synthetic */ TextInputLayout F;

    public C5801yg1(TextInputLayout textInputLayout) {
        this.F = textInputLayout;
    }

    public void afterTextChanged(Editable editable) {
        TextInputLayout textInputLayout = this.F;
        textInputLayout.O(!textInputLayout.k1, false);
        TextInputLayout textInputLayout2 = this.F;
        if (textInputLayout2.M) {
            textInputLayout2.I(editable.length());
        }
        TextInputLayout textInputLayout3 = this.F;
        if (textInputLayout3.T) {
            textInputLayout3.P(editable.length());
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
