package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.chromium.chrome.browser.password_check.PasswordCheckEditFragmentView;

/* renamed from: Mx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0787Mx0 implements TextWatcher {
    public final /* synthetic */ PasswordCheckEditFragmentView F;

    public C0787Mx0(PasswordCheckEditFragmentView passwordCheckEditFragmentView) {
        this.F = passwordCheckEditFragmentView;
    }

    public void afterTextChanged(Editable editable) {
        PasswordCheckEditFragmentView passwordCheckEditFragmentView = this.F;
        passwordCheckEditFragmentView.G0 = passwordCheckEditFragmentView.J0.getText().toString();
        PasswordCheckEditFragmentView passwordCheckEditFragmentView2 = this.F;
        passwordCheckEditFragmentView2.k1(TextUtils.isEmpty(passwordCheckEditFragmentView2.G0));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
