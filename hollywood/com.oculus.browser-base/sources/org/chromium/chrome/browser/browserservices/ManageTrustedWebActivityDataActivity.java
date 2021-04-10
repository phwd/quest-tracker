package org.chromium.chrome.browser.browserservices;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ManageTrustedWebActivityDataActivity extends I7 {
    @Override // defpackage.AbstractActivityC3892nS, defpackage.I7, defpackage.AbstractActivityC3119iw
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        String uri = getIntent().getData().toString();
        boolean z = false;
        boolean booleanExtra = getIntent().getBooleanExtra("org.chromium.webapk.is_webapk", false);
        if (booleanExtra) {
            str = getCallingPackage();
        } else {
            CustomTabsSessionToken b = CustomTabsSessionToken.b(getIntent());
            if (b == null) {
                str = null;
            } else {
                Objects.requireNonNull(AbstractApplicationC3785mq.g());
                CustomTabsConnection f = CustomTabsConnection.f();
                Objects.requireNonNull(f, "Cannot return null from a non-@Nullable @Provides method");
                str = f.f.c(b);
            }
        }
        if (str == null) {
            AbstractC1220Ua0.a("TwaDataActivity", "Package name for incoming intent couldn't be resolved. Was a CustomTabSession created and added to the intent?", new Object[0]);
            finish();
        } else {
            C1321Vq.b().i(new RunnableC4123oo1());
            if (booleanExtra) {
                Iterator it = AbstractC2612fx1.e(this, uri, str).iterator();
                while (true) {
                    if (it.hasNext()) {
                        ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
                        if (activityInfo != null && AbstractC2612fx1.b(this, activityInfo.packageName)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (z && AbstractC3952no1.a(this, str) != null) {
                    byte[] bArr = AbstractC3797mu.f10456a;
                    byte[] bArr2 = AbstractC3797mu.b;
                    if (AbstractC2612fx1.f9968a == null) {
                        AbstractC2612fx1.f9968a = bArr;
                    }
                    if (AbstractC2612fx1.b == null) {
                        AbstractC2612fx1.b = bArr2;
                    }
                    AbstractC3952no1.c(this, uri);
                }
            } else {
                Integer a2 = AbstractC3952no1.a(this, str);
                if (a2 != null) {
                    C2604fv fvVar = new C2604fv();
                    int intValue = a2.intValue();
                    SharedPreferences sharedPreferences = fvVar.f9964a;
                    Set<String> stringSet = sharedPreferences.getStringSet(intValue + ".domain", Collections.emptySet());
                    int intValue2 = a2.intValue();
                    SharedPreferences sharedPreferences2 = fvVar.f9964a;
                    Set<String> stringSet2 = sharedPreferences2.getStringSet(intValue2 + ".origin", Collections.emptySet());
                    if (!stringSet.isEmpty() && !stringSet2.isEmpty()) {
                        AbstractC3952no1.b(this, stringSet2, stringSet);
                    }
                }
            }
        }
        finish();
    }
}
