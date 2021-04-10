package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: uf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5118uf1 implements TextWatcher {
    public final /* synthetic */ C5628xf1 F;

    public C5118uf1(C5628xf1 xf1) {
        this.F = xf1;
    }

    public void afterTextChanged(Editable editable) {
        if (editable.length() != 0) {
            AbstractC1834bO bOVar = this.F.F;
            ((View$OnKeyListenerC0001Aa0) bOVar).F.n(true, editable.toString(), 7);
            AbstractC3535lK0.a("TasksSurface.FakeBox.LongPressed");
            editable.clear();
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
