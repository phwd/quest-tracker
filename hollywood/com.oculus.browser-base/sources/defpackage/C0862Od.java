package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.autofill.settings.AutofillLocalCardEditor;

/* renamed from: Od  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0862Od implements TextWatcher {
    public final /* synthetic */ AutofillLocalCardEditor F;

    public C0862Od(AutofillLocalCardEditor autofillLocalCardEditor) {
        this.F = autofillLocalCardEditor;
    }

    public void afterTextChanged(Editable editable) {
        String str;
        this.F.N0 = !editable.toString().matches(".*\\d.*");
        AutofillLocalCardEditor autofillLocalCardEditor = this.F;
        TextInputLayout textInputLayout = autofillLocalCardEditor.H0;
        if (autofillLocalCardEditor.N0) {
            str = "";
        } else {
            str = autofillLocalCardEditor.A0.getResources().getString(R.string.f47320_resource_name_obfuscated_RES_2131952049);
        }
        textInputLayout.w(str);
        this.F.k1();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
