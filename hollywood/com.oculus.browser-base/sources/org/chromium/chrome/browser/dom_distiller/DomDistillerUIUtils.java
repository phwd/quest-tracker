package org.chromium.chrome.browser.dom_distiller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DomDistillerUIUtils {
    public static void openSettings(WebContents webContents) {
        Activity activity;
        WindowAndroid I;
        if (webContents == null || (I = webContents.I()) == null) {
            activity = null;
        } else {
            activity = (Activity) I.s0().get();
        }
        if (webContents != null && activity != null) {
            AbstractC3535lK0.a("DomDistiller_DistilledPagePrefsOpened");
            C2290e4 e4Var = new C2290e4(activity, R.style.f72700_resource_name_obfuscated_RES_2132017843);
            int i = DistilledPagePrefsView.F;
            e4Var.h((DistilledPagePrefsView) LayoutInflater.from(activity).inflate(R.layout.f38000_resource_name_obfuscated_RES_2131624109, (ViewGroup) null));
            e4Var.i();
        }
    }
}
