package org.chromium.chrome.browser.password_check;

import android.content.DialogInterface;
import android.os.Bundle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordCheckDialogFragment extends OE {
    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle != null) {
            f1(false, false);
        }
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
    }
}
