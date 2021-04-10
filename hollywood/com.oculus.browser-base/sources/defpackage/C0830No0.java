package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: No0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0830No0 implements AbstractC3087il0 {
    public C2746gl0 F;
    public WindowAndroid G;
    public Runnable H;

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 1) {
            this.F.b(uh0, 2);
            return;
        }
        try {
            this.G.F0(new Intent(Build.VERSION.SDK_INT >= 29 ? "android.settings.panel.action.NFC" : "android.settings.NFC_SETTINGS"), new C0769Mo0(this, uh0), null);
        } catch (ActivityNotFoundException unused) {
            this.F.b(uh0, 1);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        Runnable runnable = this.H;
        if (runnable != null) {
            runnable.run();
        }
        this.H = null;
    }
}
