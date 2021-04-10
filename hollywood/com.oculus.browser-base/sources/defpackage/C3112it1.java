package defpackage;

import J.N;
import android.hardware.Camera;

/* renamed from: it1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3112it1 implements Camera.PictureCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5500wt1 f10169a;

    public C3112it1(C5500wt1 wt1, C2600ft1 ft1) {
        this.f10169a = wt1;
    }

    public void onPictureTaken(byte[] bArr, Camera camera) {
        try {
            this.f10169a.m.flatten();
            camera.setParameters(this.f10169a.m);
        } catch (RuntimeException e) {
            AbstractC1220Ua0.a("VideoCapture", "onPictureTaken, setParameters() " + e, new Object[0]);
        }
        try {
            camera.startPreview();
        } catch (RuntimeException e2) {
            AbstractC1220Ua0.a("VideoCapture", "onPictureTaken, startPreview() " + e2, new Object[0]);
        }
        synchronized (this.f10169a.h) {
            C5500wt1 wt1 = this.f10169a;
            long j = wt1.i;
            if (j != 0) {
                N.MdZBZ$ST(wt1.e, wt1, j, bArr);
            }
            this.f10169a.i = 0;
        }
    }
}
