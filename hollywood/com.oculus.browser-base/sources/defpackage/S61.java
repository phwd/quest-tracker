package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: S61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S61 implements TextWatcher {
    public final /* synthetic */ U61 F;

    public S61(U61 u61) {
        this.F = u61;
    }

    public void afterTextChanged(Editable editable) {
        U61 u61 = this.F;
        if (u61.r) {
            u61.s = editable.toString();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
