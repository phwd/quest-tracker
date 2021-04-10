package defpackage;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

/* renamed from: ex  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2439ex implements Runnable {
    public final C2952hx F;
    public final boolean G;

    public RunnableC2439ex(C2952hx hxVar, boolean z) {
        this.F = hxVar;
        this.G = z;
    }

    public void run() {
        C2952hx hxVar = this.F;
        boolean z = this.G;
        if (hxVar.f10112a != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (hxVar.f10112a.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)) {
                hxVar.b = byteArrayOutputStream.toByteArray();
            }
        }
        if (!z) {
            hxVar.c();
        }
    }
}
