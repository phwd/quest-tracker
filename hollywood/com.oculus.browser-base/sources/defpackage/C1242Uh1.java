package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: Uh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1242Uh1 implements LargeIconBridge$LargeIconCallback {
    public final SX0 F;
    public final /* synthetic */ C1425Xh1 G;

    public C1242Uh1(C1425Xh1 xh1, SX0 sx0, boolean z, AbstractC1120Sh1 sh1) {
        this.G = xh1;
        this.F = sx0;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        C0815Nh1 b = this.G.b(this.F);
        if (b != null) {
            b.d = i2;
            if (bitmap == null) {
                this.G.d.b(b, i, z);
            } else {
                this.G.d.a(b, bitmap);
            }
            this.G.c.a(b);
        }
    }
}
