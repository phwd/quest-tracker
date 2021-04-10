package defpackage;

import J.N;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

/* renamed from: ot1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4138ot1 extends CameraCaptureSession.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final CaptureRequest f11034a;
    public final /* synthetic */ C5330vt1 b;

    public C4138ot1(C5330vt1 vt1, CaptureRequest captureRequest) {
        this.b = vt1;
        this.f11034a = captureRequest;
    }

    public void onClosed(CameraCaptureSession cameraCaptureSession) {
        this.b.j = null;
    }

    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        this.b.f(3);
        C5330vt1 vt1 = this.b;
        vt1.j = null;
        N.MhmwjISE(vt1.e, vt1, 70, "Camera session configuration error");
    }

    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        this.b.j = cameraCaptureSession;
        try {
            cameraCaptureSession.setRepeatingRequest(this.f11034a, new C3967nt1(this), null);
            this.b.f(2);
            C5330vt1 vt1 = this.b;
            N.MPaf3s5k(vt1.e, vt1);
        } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            AbstractC1220Ua0.a("VideoCapture", "setRepeatingRequest: ", e);
        }
    }
}
