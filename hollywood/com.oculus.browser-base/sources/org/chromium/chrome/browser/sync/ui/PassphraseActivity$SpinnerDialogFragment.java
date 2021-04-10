package org.chromium.chrome.browser.sync.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PassphraseActivity$SpinnerDialogFragment extends OE {
    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        ProgressDialog progressDialog = new ProgressDialog(u());
        progressDialog.setMessage(I().getString(R.string.f62830_resource_name_obfuscated_RES_2131953600));
        return progressDialog;
    }
}
