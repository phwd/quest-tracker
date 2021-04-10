package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: x3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5529x3 implements TextWatcher {
    public final /* synthetic */ View$OnClickListenerC5699y3 F;

    public C5529x3(View$OnClickListenerC5699y3 y3Var) {
        this.F = y3Var;
    }

    public void afterTextChanged(Editable editable) {
        this.F.a();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
