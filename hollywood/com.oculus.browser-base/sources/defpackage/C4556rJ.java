package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

/* renamed from: rJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4556rJ implements TextWatcher {
    public final /* synthetic */ AJ F;

    public C4556rJ(AJ aj) {
        this.F = aj;
    }

    public void afterTextChanged(Editable editable) {
        AJ aj = this.F;
        AutoCompleteTextView d = AJ.d(aj, aj.f9772a.f9696J);
        d.post(new RunnableC4386qJ(this, d));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
