package defpackage;

import android.graphics.BitmapFactory;
import org.chromium.base.Callback;

/* renamed from: dx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2268dx implements Runnable {
    public final C2952hx F;
    public final Callback G;

    public RunnableC2268dx(C2952hx hxVar, Callback callback) {
        this.F = hxVar;
        this.G = callback;
    }

    public void run() {
        byte[] bArr;
        C2952hx hxVar = this.F;
        Callback callback = this.G;
        if (hxVar.f10112a == null && (bArr = hxVar.b) != null) {
            hxVar.f10112a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        if (callback != null) {
            callback.onResult(hxVar);
        }
    }
}
