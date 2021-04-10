package defpackage;

import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: Lg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0689Lg implements LargeIconBridge$LargeIconCallback {
    public final AbstractC0749Mg F;
    public final UH0 G;
    public final Runnable H;

    public C0689Lg(AbstractC0749Mg mg, UH0 uh0, Runnable runnable) {
        this.F = mg;
        this.G = uh0;
        this.H = runnable;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        AbstractC0749Mg mg = this.F;
        UH0 uh0 = this.G;
        Runnable runnable = this.H;
        Objects.requireNonNull(mg);
        if (bitmap != null) {
            uh0.m(AbstractC0871Og.f8641a, C5191v31.b(mg.f8494a, bitmap).a());
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
