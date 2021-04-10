package defpackage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* renamed from: UJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UJ extends AbstractDialogInterface$OnClickListenerC1632aF0 {
    public EditText U0;
    public CharSequence V0;

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.V0);
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle != null) {
            this.V0 = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        } else {
            q1();
            throw null;
        }
    }

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void m1(View view) {
        super.m1(view);
        EditText editText = (EditText) view.findViewById(16908291);
        this.U0 = editText;
        if (editText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText.requestFocus();
        this.U0.setText(this.V0);
        EditText editText2 = this.U0;
        editText2.setSelection(editText2.getText().length());
        q1();
        throw null;
    }

    @Override // defpackage.AbstractDialogInterface$OnClickListenerC1632aF0
    public void o1(boolean z) {
        if (z) {
            this.U0.getText().toString();
            q1();
            throw null;
        }
    }

    public final void q1() {
        C5859z.a(l1());
    }
}
