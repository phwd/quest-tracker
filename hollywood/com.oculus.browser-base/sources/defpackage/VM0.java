package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: VM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class VM0 implements LargeIconBridge$LargeIconCallback {
    public final WM0 F;

    public VM0(WM0 wm0) {
        this.F = wm0;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        WM0 wm0 = this.F;
        if (bitmap == null) {
            Resources resources = wm0.G.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f24740_resource_name_obfuscated_RES_2131166093);
            bitmap = new KN0(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, i, (float) resources.getDimensionPixelSize(R.dimen.f24750_resource_name_obfuscated_RES_2131166094)).b(wm0.H.h());
            if (bitmap == null) {
                return;
            }
        }
        int dimensionPixelSize2 = wm0.G.getResources().getDimensionPixelSize(R.dimen.f24740_resource_name_obfuscated_RES_2131166093);
        wm0.a(Bitmap.createScaledBitmap(bitmap, dimensionPixelSize2, dimensionPixelSize2, true), false);
    }
}
