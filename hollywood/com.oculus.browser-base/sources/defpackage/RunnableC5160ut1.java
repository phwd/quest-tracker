package defpackage;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.util.Size;
import java.util.ArrayList;
import org.chromium.base.TraceEvent;

/* renamed from: ut1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5160ut1 implements Runnable {
    public final long F;
    public final /* synthetic */ C5330vt1 G;

    public RunnableC5160ut1(C5330vt1 vt1, long j) {
        this.G = vt1;
        this.F = j;
    }

    public void run() {
        int i;
        int i2;
        TraceEvent.i0("VideoCaptureCamera2.java", "TakePhotoTask.run");
        C5330vt1 vt1 = this.G;
        if (vt1.i == null || vt1.q != 2) {
            AbstractC1220Ua0.a("VideoCapture", "TakePhoto failed because mCameraDevice == null || mCameraState != CameraState.STARTED", new Object[0]);
            this.G.d(this.F);
            return;
        }
        Size[] outputSizes = ((StreamConfigurationMap) C5330vt1.i(vt1.d).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(256);
        C5330vt1 vt12 = this.G;
        Size h = C5330vt1.h(outputSizes, vt12.t, vt12.u);
        C5330vt1 vt13 = this.G;
        int i3 = vt13.t;
        int i4 = vt13.u;
        if (h != null) {
            h.getWidth();
            h.getHeight();
        }
        TraceEvent.i0("VideoCaptureCamera2.java", "TakePhotoTask.run creating ImageReader");
        if (h != null) {
            i = h.getWidth();
        } else {
            i = this.G.c.f10988a;
        }
        if (h != null) {
            i2 = h.getHeight();
        } else {
            i2 = this.G.c.b;
        }
        ImageReader newInstance = ImageReader.newInstance(i, i2, 256, 1);
        C5330vt1 vt14 = this.G;
        newInstance.setOnImageAvailableListener(new C3454kt1(vt14, this.F), vt14.n);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(newInstance.getSurface());
        try {
            CaptureRequest.Builder createCaptureRequest = this.G.i.createCaptureRequest(2);
            if (createCaptureRequest == null) {
                AbstractC1220Ua0.a("VideoCapture", "photoRequestBuilder error", new Object[0]);
                this.G.d(this.F);
                return;
            }
            createCaptureRequest.addTarget(newInstance.getSurface());
            createCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(this.G.a()));
            TraceEvent.i0("VideoCaptureCamera2.java", "TakePhotoTask.run calling configureCommonCaptureSettings");
            this.G.g(createCaptureRequest);
            TraceEvent.i0("VideoCaptureCamera2.java", "TakePhotoTask.run calling photoRequestBuilder.build()");
            C3625lt1 lt1 = new C3625lt1(this.G, newInstance, createCaptureRequest.build(), this.F);
            try {
                TraceEvent.i0("VideoCaptureCamera2.java", "TakePhotoTask.run calling mCameraDevice.createCaptureSession()");
                C5330vt1 vt15 = this.G;
                vt15.i.createCaptureSession(arrayList, lt1, vt15.n);
            } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
                AbstractC1220Ua0.a("VideoCapture", "createCaptureSession: " + e, new Object[0]);
                this.G.d(this.F);
            }
        } catch (CameraAccessException e2) {
            AbstractC1220Ua0.a("VideoCapture", "createCaptureRequest() error ", e2);
            this.G.d(this.F);
        }
    }
}
