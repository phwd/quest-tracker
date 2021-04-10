package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CredentialLeakDialogBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10737a;
    public final C3126iy0 b;
    public final WeakReference c;

    public CredentialLeakDialogBridge(WindowAndroid windowAndroid, long j) {
        this.f10737a = j;
        ChromeActivity chromeActivity = (ChromeActivity) windowAndroid.s0().get();
        this.c = new WeakReference(chromeActivity);
        this.b = new C3126iy0(chromeActivity.l0(), chromeActivity.findViewById(16908290), chromeActivity.M0(), chromeActivity.N0());
    }

    public static CredentialLeakDialogBridge create(WindowAndroid windowAndroid, long j) {
        return new CredentialLeakDialogBridge(windowAndroid, j);
    }

    public final void destroy() {
        this.f10737a = 0;
        View$OnLayoutChangeListenerC3639ly0 ly0 = this.b.f10178a;
        ly0.F.b(ly0.f10390J, 4);
        ly0.G.removeOnLayoutChangeListener(ly0);
    }

    public void showDialog(String str, String str2, String str3, String str4) {
        if (this.c.get() != null) {
            C2785gy0 gy0 = new C2785gy0(str, str2, R.drawable.f34450_resource_name_obfuscated_RES_2131231485, str3, str4, new C3165jB(this));
            gy0.f = str4 != null;
            gy0.g = new RunnableC2995iB(this);
            this.b.a((Context) this.c.get(), gy0);
            this.b.b();
        }
    }
}
