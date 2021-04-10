package org.chromium.ui.base;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DeviceFormFactor {
    public static boolean a(Context context) {
        return context.getResources().getInteger(R.integer.f35930_resource_name_obfuscated_RES_2131492890) >= 2;
    }

    public static boolean b(WindowAndroid windowAndroid) {
        int i;
        Object obj = ThreadUtils.f10596a;
        Context context = (Context) windowAndroid.f11022J.get();
        if (context == null) {
            i = 0;
        } else {
            i = context.getResources().getInteger(R.integer.f35930_resource_name_obfuscated_RES_2131492890);
        }
        if (i >= 2) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean isTablet() {
        return ContextUtils.getApplicationContext().getResources().getInteger(R.integer.f35930_resource_name_obfuscated_RES_2131492890) >= 2;
    }
}
