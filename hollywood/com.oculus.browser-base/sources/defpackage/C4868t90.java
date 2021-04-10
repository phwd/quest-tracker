package defpackage;

import android.os.Bundle;
import androidx.preference.ListPreference;

/* renamed from: t90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4868t90 extends AbstractDialogInterface$OnClickListenerC1632aF0 {
    public int U0;
    public CharSequence[] V0;
    public CharSequence[] W0;

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.U0);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.V0);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.W0);
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle == null) {
            ListPreference listPreference = (ListPreference) l1();
            if (listPreference.z0 == null || listPreference.A0 == null) {
                throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
            }
            this.U0 = listPreference.a0(listPreference.B0);
            this.V0 = listPreference.z0;
            this.W0 = listPreference.A0;
            return;
        }
        this.U0 = bundle.getInt("ListPreferenceDialogFragment.index", 0);
        this.V0 = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
        this.W0 = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
    }

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void o1(boolean z) {
        int i;
        if (z && (i = this.U0) >= 0) {
            String charSequence = this.W0[i].toString();
            ListPreference listPreference = (ListPreference) l1();
            if (listPreference.f(charSequence)) {
                listPreference.c0(charSequence);
            }
        }
    }

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void p1(C2290e4 e4Var) {
        CharSequence[] charSequenceArr = this.V0;
        int i = this.U0;
        DialogInterface$OnClickListenerC4698s90 s90 = new DialogInterface$OnClickListenerC4698s90(this);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.n = charSequenceArr;
        a4Var.p = s90;
        a4Var.v = i;
        a4Var.u = true;
        e4Var.f(null, null);
    }
}
