package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: By0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0119By0 implements TextWatcher {
    public final /* synthetic */ C0363Fy0 F;

    public C0119By0(C0363Fy0 fy0) {
        this.F = fy0;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        C0363Fy0 fy0 = this.F;
        fy0.c.setChecked(!C0363Fy0.d(fy0));
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
