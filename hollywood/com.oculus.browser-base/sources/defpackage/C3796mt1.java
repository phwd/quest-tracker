package defpackage;

import J.N;
import android.media.Image;
import android.media.ImageReader;

/* renamed from: mt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3796mt1 implements ImageReader.OnImageAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5330vt1 f10455a;

    public C3796mt1(C5330vt1 vt1, AbstractC3283jt1 jt1) {
        this.f10455a = vt1;
    }

    public void onImageAvailable(ImageReader imageReader) {
        try {
            Image acquireLatestImage = imageReader.acquireLatestImage();
            if (acquireLatestImage == null) {
                try {
                    C5330vt1 vt1 = this.f10455a;
                    N.M651cEC1(vt1.e, vt1, 9);
                    if (acquireLatestImage != null) {
                        acquireLatestImage.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } else if (acquireLatestImage.getFormat() != 35 || acquireLatestImage.getPlanes().length != 3) {
                C5330vt1 vt12 = this.f10455a;
                long j = vt12.e;
                N.MhmwjISE(j, vt12, 71, "Unexpected image format: " + acquireLatestImage.getFormat() + " or #planes: " + acquireLatestImage.getPlanes().length);
                throw new IllegalStateException();
            } else if (imageReader.getWidth() == acquireLatestImage.getWidth() && imageReader.getHeight() == acquireLatestImage.getHeight()) {
                C5330vt1 vt13 = this.f10455a;
                N.MlTacwJQ(vt13.e, vt13, acquireLatestImage.getPlanes()[0].getBuffer(), acquireLatestImage.getPlanes()[0].getRowStride(), acquireLatestImage.getPlanes()[1].getBuffer(), acquireLatestImage.getPlanes()[2].getBuffer(), acquireLatestImage.getPlanes()[1].getRowStride(), acquireLatestImage.getPlanes()[1].getPixelStride(), acquireLatestImage.getWidth(), acquireLatestImage.getHeight(), this.f10455a.a(), acquireLatestImage.getTimestamp());
                acquireLatestImage.close();
                return;
            } else {
                C5330vt1 vt14 = this.f10455a;
                long j2 = vt14.e;
                N.MhmwjISE(j2, vt14, 72, "ImageReader size (" + imageReader.getWidth() + "x" + imageReader.getHeight() + ") did not match Image size (" + acquireLatestImage.getWidth() + "x" + acquireLatestImage.getHeight() + ")");
                throw new IllegalStateException();
            }
            throw th;
        } catch (IllegalStateException e) {
            AbstractC1220Ua0.a("VideoCapture", "acquireLatestImage():", e);
        }
    }
}
