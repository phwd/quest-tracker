package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: Ql0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1007Ql0 implements LargeIconBridge$LargeIconCallback {
    public final UH0 F;

    public C1007Ql0(UH0 uh0) {
        this.F = uh0;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        UH0 uh0 = this.F;
        if (bitmap != null) {
            uh0.m(AbstractC5977zi1.c, new BitmapDrawable(bitmap));
        }
    }
}
