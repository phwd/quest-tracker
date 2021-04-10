package defpackage;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: Oh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0875Oh0 implements LargeIconBridge$LargeIconCallback {
    public final /* synthetic */ C0936Ph0 F;

    public C0875Oh0(C0936Ph0 ph0) {
        this.F = ph0;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        C0936Ph0 ph0 = this.F;
        if (!ph0.i()) {
            if (bitmap == null) {
                ph0.k.h = R.drawable.f28180_resource_name_obfuscated_RES_2131230858;
                ph0.k();
                return;
            }
            ph0.l(bitmap);
        }
    }
}
