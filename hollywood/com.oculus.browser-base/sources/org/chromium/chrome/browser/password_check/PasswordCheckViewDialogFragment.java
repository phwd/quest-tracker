package org.chromium.chrome.browser.password_check;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordCheckViewDialogFragment extends PasswordCheckDialogFragment {
    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        if (!AbstractC2852hK0.a(0)) {
            f1(false, false);
        }
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        TextView textView = (TextView) u().getLayoutInflater().inflate(R.layout.f40320_resource_name_obfuscated_RES_2131624341, (ViewGroup) null).findViewById(R.id.view_dialog_compromised_password);
        throw null;
    }
}
