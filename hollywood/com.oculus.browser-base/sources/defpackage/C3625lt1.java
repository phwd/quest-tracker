package defpackage;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import org.chromium.base.TraceEvent;

/* renamed from: lt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3625lt1 extends CameraCaptureSession.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final ImageReader f10383a;
    public final CaptureRequest b;
    public final long c;
    public final /* synthetic */ C5330vt1 d;

    public C3625lt1(C5330vt1 vt1, ImageReader imageReader, CaptureRequest captureRequest, long j) {
        this.d = vt1;
        this.f10383a = imageReader;
        this.b = captureRequest;
        this.c = j;
    }

    public void onClosed(CameraCaptureSession cameraCaptureSession) {
        this.f10383a.close();
    }

    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        AbstractC1220Ua0.a("VideoCapture", "failed configuring capture session", new Object[0]);
        this.d.d(this.c);
    }

    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        TraceEvent.i0("VideoCaptureCamera2.java", "CrPhotoSessionListener.onConfigured");
        try {
            TraceEvent.i0("VideoCaptureCamera2.java", "Calling CameraCaptureSession.capture()");
            cameraCaptureSession.capture(this.b, null, null);
        } catch (CameraAccessException e) {
            AbstractC1220Ua0.a("VideoCapture", "capture() CameraAccessException", e);
            this.d.d(this.c);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("VideoCapture", "capture() IllegalStateException", e2);
            this.d.d(this.c);
        }
    }
}
