package defpackage;

import android.media.ImageReader;
import org.chromium.media.ScreenCapture;

/* renamed from: YO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YO0 implements ImageReader.OnImageAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ScreenCapture f9271a;

    public YO0(ScreenCapture screenCapture, XO0 xo0) {
        this.f9271a = screenCapture;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r0.d() == false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r23.f9271a.b();
        r23.f9271a.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r6 = r24.acquireLatestImage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r6 != null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r6 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        if (r24.getWidth() != r6.getWidth()) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        if (r24.getHeight() != r6.getHeight()) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        r0 = r6.getFormat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        if (r0 == 1) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0055, code lost:
        if (r0 != 35) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
        if (r6.getPlanes().length != 3) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005f, code lost:
        r10 = r23.f9271a;
        J.N.MgLL$kUF(r10.G, r10, r6.getPlanes()[0].getBuffer(), r6.getPlanes()[0].getRowStride(), r6.getPlanes()[1].getBuffer(), r6.getPlanes()[2].getBuffer(), r6.getPlanes()[1].getRowStride(), r6.getPlanes()[1].getPixelStride(), r6.getCropRect().left, r6.getCropRect().top, r6.getCropRect().width(), r6.getCropRect().height(), r6.getTimestamp());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c8, code lost:
        defpackage.AbstractC1220Ua0.a("ScreenCapture", "Unexpected image planes for YUV_420_888 format: " + r6.getPlanes().length, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ea, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00eb, code lost:
        defpackage.AbstractC1220Ua0.a("ScreenCapture", "Unexpected image format: " + r6.getFormat(), new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x010c, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0112, code lost:
        if (r6.getPlanes().length != 1) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0114, code lost:
        r9 = r23.f9271a;
        J.N.MjDBp6op(r9.G, r9, r6.getPlanes()[0].getBuffer(), r6.getPlanes()[0].getRowStride(), r6.getCropRect().left, r6.getCropRect().top, r6.getCropRect().width(), r6.getCropRect().height(), r6.getTimestamp());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x014f, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0154, code lost:
        defpackage.AbstractC1220Ua0.a("ScreenCapture", "Unexpected image planes for RGBA_8888 format: " + r6.getPlanes().length, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0176, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0177, code lost:
        defpackage.AbstractC1220Ua0.a("ScreenCapture", "ImageReader size (" + r24.getWidth() + "x" + r24.getHeight() + ") did not match Image size (" + r6.getWidth() + "x" + r6.getHeight() + ")", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01c1, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01c2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01c9, code lost:
        defpackage.AbstractC0754Mh1.f8495a.a(r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01d0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01d1, code lost:
        defpackage.AbstractC1220Ua0.d("ScreenCapture", "acquireLatestImage():" + r0, new java.lang.Object[0]);
        r0 = r23.f9271a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01ed, code lost:
        if (r0.W == 35) goto L_0x01ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01ef, code lost:
        r0.W = 1;
        r0.b();
        r23.f9271a.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01fa, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01fb, code lost:
        defpackage.AbstractC1220Ua0.a("ScreenCapture", "acquireLatestImage():" + r0, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onImageAvailable(android.media.ImageReader r24) {
        /*
        // Method dump skipped, instructions count: 535
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.YO0.onImageAvailable(android.media.ImageReader):void");
    }
}
