package org.chromium.chrome.browser.sync.settings;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearDataProgressDialog extends OE {
    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        i1(false);
        ProgressDialog progressDialog = new ProgressDialog(u());
        progressDialog.setTitle(O(R.string.f65770_resource_name_obfuscated_RES_2131953894));
        progressDialog.setMessage(O(R.string.f65760_resource_name_obfuscated_RES_2131953893));
        progressDialog.setIndeterminate(true);
        return progressDialog;
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle != null) {
            f1(false, false);
        }
    }
}
