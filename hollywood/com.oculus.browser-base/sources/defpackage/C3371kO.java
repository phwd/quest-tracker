package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: kO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3371kO implements LargeIconBridge$LargeIconCallback {
    public final C3884nO F;
    public final String G;
    public final Callback H;

    public C3371kO(C3884nO nOVar, String str, Callback callback) {
        this.F = nOVar;
        this.G = str;
        this.H = callback;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        C3884nO nOVar = this.F;
        this.H.onResult(AbstractC4055oO.c(bitmap, this.G, i, nOVar.b, nOVar.f10488a, nOVar.c));
    }
}
