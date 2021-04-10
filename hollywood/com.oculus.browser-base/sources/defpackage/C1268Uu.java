package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: Uu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1268Uu implements TextWatcher {
    public final /* synthetic */ C2092cv F;

    public C1268Uu(C2092cv cvVar) {
        this.F = cvVar;
    }

    public void afterTextChanged(Editable editable) {
        C2092cv cvVar = this.F;
        if (cvVar.f9772a.e0 == null) {
            cvVar.d(editable.length() > 0);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
