package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: sx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4830sx implements LargeIconBridge$LargeIconCallback {
    public final /* synthetic */ C5170ux F;
    public final /* synthetic */ String G;
    public final /* synthetic */ C5000tx H;

    public C4830sx(C5000tx txVar, C5170ux uxVar, String str) {
        this.H = txVar;
        this.F = uxVar;
        this.G = str;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        if (this == this.F.c) {
            String str = this.G;
            C5000tx txVar = this.H;
            this.F.b.setImageDrawable(AbstractC4055oO.c(bitmap, str, i, txVar.H, txVar.I.I(), this.H.G));
        }
    }
}
