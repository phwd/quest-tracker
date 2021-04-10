package defpackage;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.media.FaceDetector;
import java.util.Objects;

/* renamed from: qN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4395qN implements Runnable {
    public final C4565rN F;
    public final int G;
    public final int H;
    public final Bitmap I;

    /* renamed from: J  reason: collision with root package name */
    public final FN f11137J;

    public RunnableC4395qN(C4565rN rNVar, int i, int i2, Bitmap bitmap, FN fn) {
        this.F = rNVar;
        this.G = i;
        this.H = i2;
        this.I = bitmap;
        this.f11137J = fn;
    }

    public void run() {
        C4565rN rNVar = this.F;
        int i = this.G;
        int i2 = this.H;
        Bitmap bitmap = this.I;
        FN fn = this.f11137J;
        Objects.requireNonNull(rNVar);
        FaceDetector faceDetector = new FaceDetector(i, i2, rNVar.F);
        FaceDetector.Face[] faceArr = new FaceDetector.Face[rNVar.F];
        int findFaces = faceDetector.findFaces(bitmap, faceArr);
        AN[] anArr = new AN[findFaces];
        for (int i3 = 0; i3 < findFaces; i3++) {
            anArr[i3] = new AN();
            FaceDetector.Face face = faceArr[i3];
            PointF pointF = new PointF();
            face.getMidPoint(pointF);
            float eyesDistance = face.eyesDistance();
            anArr[i3].d = new C4048oK0();
            anArr[i3].d.d = pointF.x - eyesDistance;
            anArr[i3].d.e = pointF.y - eyesDistance;
            float f = eyesDistance * 2.0f;
            anArr[i3].d.f = f;
            anArr[i3].d.g = f;
            anArr[i3].e = new C4689s60[0];
        }
        fn.a(anArr);
    }
}
