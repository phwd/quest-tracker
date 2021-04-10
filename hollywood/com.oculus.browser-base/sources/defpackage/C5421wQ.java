package defpackage;

import J.N;
import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: wQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5421wQ implements TextWatcher {
    public final /* synthetic */ BQ F;

    public C5421wQ(BQ bq) {
        this.F = bq;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        BQ bq = this.F;
        if (bq.S != null) {
            bq.e0 = false;
            if (!bq.V && bq.P.l() != null) {
                if (charSequence.length() > 0) {
                    BQ bq2 = this.F;
                    bq2.W = false;
                    C3546lQ lQVar = bq2.S;
                    N.MiKuFRTN(lQVar.b, lQVar, charSequence.toString(), true, false);
                } else {
                    this.F.c();
                    C3546lQ lQVar2 = this.F.S;
                    N.MWOuMqhA(lQVar2.b, lQVar2, true);
                    this.F.l(false);
                }
                if (!this.F.i()) {
                    this.F.U = charSequence.toString();
                }
            }
        }
    }
}
