package defpackage;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

/* renamed from: Ey0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0302Ey0 implements View.OnClickListener {
    public final /* synthetic */ C0363Fy0 F;

    public View$OnClickListenerC0302Ey0(C0363Fy0 fy0) {
        this.F = fy0;
    }

    public void onClick(View view) {
        EditText editText = this.F.f9772a.f9696J;
        if (editText != null) {
            int selectionEnd = editText.getSelectionEnd();
            if (C0363Fy0.d(this.F)) {
                editText.setTransformationMethod(null);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (selectionEnd >= 0) {
                editText.setSelection(selectionEnd);
            }
        }
    }
}
