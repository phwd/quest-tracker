package org.chromium.chrome.browser.webauth.authenticator;

import J.N;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbAccessory;
import android.os.Bundle;
import android.util.Base64;
import com.oculus.browser.R;
import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CableAuthenticatorActivity extends AbstractActivityC5319vq {
    @Override // defpackage.AbstractActivityC3892nS, defpackage.I7, defpackage.AbstractActivityC3119iw, defpackage.AbstractActivityC5319vq
    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        setTitle("Phone as a Security Key");
        C1321Vq.b().e();
        super.onCreate(bundle);
        if (N.M09VlOh_("WebAuthenticationPhoneSupport")) {
            Intent intent = getIntent();
            if (intent.getAction() != null && intent.getAction().equals("android.hardware.usb.action.USB_ACCESSORY_ATTACHED")) {
                bundle2 = new Bundle();
                bundle2.putParcelable("accessory", (UsbAccessory) intent.getParcelableExtra("accessory"));
            } else if (intent.hasExtra("org.chromium.chrome.browser.webauth.authenticator.ServerLink")) {
                String stringExtra = intent.getStringExtra("org.chromium.chrome.browser.webauth.authenticator.ServerLink");
                bundle2 = new Bundle();
                try {
                    bundle2.putByteArray("org.chromium.chrome.browser.webauth.authenticator.ServerLink", Base64.decode(stringExtra, 0));
                } catch (IllegalArgumentException unused) {
                    AbstractC1220Ua0.d("CableAuthenticatorActivity", "Invalid base64 in ServerLink argument", new Object[0]);
                }
            } else {
                bundle2 = intent.getBundleExtra("show_fragment_args");
            }
            CableAuthenticatorModuleProvider cableAuthenticatorModuleProvider = new CableAuthenticatorModuleProvider();
            cableAuthenticatorModuleProvider.U0(bundle2);
            C0317Fe fe = new C0317Fe(Y());
            fe.q(16908290, cableAuthenticatorModuleProvider);
            fe.e();
            Resources resources = getResources();
            setTaskDescription(new ActivityManager.TaskDescription(resources.getString(R.string.f46950_resource_name_obfuscated_RES_2131952012), BitmapFactory.decodeResource(resources, R.mipmap.app_icon), resources.getColor(R.color.f11400_resource_name_obfuscated_RES_2131099830)));
        }
    }
}
