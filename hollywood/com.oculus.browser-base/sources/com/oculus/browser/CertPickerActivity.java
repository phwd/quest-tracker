package com.oculus.browser;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.security.KeyChain;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CertPickerActivity extends Activity {
    public C2518fO0 F = new C2518fO0();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("nativePtr");
        String string2 = extras.getString("hostName");
        String string3 = extras.getString("port");
        long parseLong = Long.parseLong(string);
        int parseInt = Integer.parseInt(string3);
        Objects.requireNonNull(this.F);
        Object obj = ThreadUtils.f10596a;
        String[] strArr = new String[0];
        C2347eO0 eo0 = new C2347eO0(getApplicationContext(), parseLong, this);
        try {
            KeyChain.choosePrivateKeyAlias(this, eo0, strArr, null, string2, parseInt, null);
        } catch (ActivityNotFoundException unused) {
            eo0.alias(null);
            C2246dp1 dp1 = new C2246dp1(this, R.style.f72700_resource_name_obfuscated_RES_2132017843);
            dp1.g(R.string.f49130_resource_name_obfuscated_RES_2131952230);
            dp1.c(R.string.f49120_resource_name_obfuscated_RES_2131952229);
            dp1.d(R.string.f49160_resource_name_obfuscated_RES_2131952233, new DialogInterface$OnClickListenerC1835bO0());
            dp1.i();
        }
    }
}
