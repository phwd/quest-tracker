package defpackage;

import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* renamed from: QB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QB implements Runnable {
    public final /* synthetic */ CustomTabToolbar F;

    public QB(CustomTabToolbar customTabToolbar) {
        this.F = customTabToolbar;
    }

    public void run() {
        CustomTabToolbar customTabToolbar = this.F;
        ZB zb = customTabToolbar.n0;
        customTabToolbar.getContext();
        if (zb.b) {
            zb.b = false;
            throw null;
        }
    }
}
