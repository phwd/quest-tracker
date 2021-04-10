package org.chromium.chrome.browser.password_manager.settings;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordReauthenticationFragment extends AbstractComponentCallbacksC3550lS {
    public KS y0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        bundle.putBoolean("has_been_suspended", true);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void c0(int i, int i2, Intent intent) {
        if (i == 2) {
            if (i2 == -1) {
                long currentTimeMillis = System.currentTimeMillis();
                int i3 = this.K.getInt("scope");
                AbstractC2852hK0.f10064a = Long.valueOf(currentTimeMillis);
                AbstractC2852hK0.b = i3;
            } else {
                AbstractC2852hK0.f10064a = null;
                AbstractC2852hK0.b = 0;
            }
            KS ks = this.y0;
            ks.B(new IS(ks, null, -1, 0), false);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        this.y0 = this.W;
        if (bundle == null) {
            Intent createConfirmDeviceCredentialIntent = ((KeyguardManager) u().getSystemService("keyguard")).createConfirmDeviceCredentialIntent(null, O(this.K.getInt("description", 0)));
            if (createConfirmDeviceCredentialIntent != null) {
                d1(createConfirmDeviceCredentialIntent, 2);
            } else {
                this.y0.c0();
            }
        }
    }
}
