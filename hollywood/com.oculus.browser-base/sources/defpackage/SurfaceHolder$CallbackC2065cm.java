package defpackage;

import android.content.Context;
import android.hardware.Camera;
import android.os.HandlerThread;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* renamed from: cm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SurfaceHolder$CallbackC2065cm extends SurfaceView implements SurfaceHolder.Callback {
    public final Context F;
    public final Camera.PreviewCallback G;
    public final Camera.ErrorCallback H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public Camera f9629J;
    public HandlerThread K;

    public SurfaceHolder$CallbackC2065cm(Context context, Camera.PreviewCallback previewCallback, Camera.ErrorCallback errorCallback) {
        super(context);
        this.F = context;
        this.G = previewCallback;
        this.H = errorCallback;
    }

    public final void a() {
        int i;
        getHolder().addCallback(this);
        Camera camera = this.f9629J;
        if (camera != null) {
            try {
                camera.setPreviewDisplay(getHolder());
                Camera camera2 = this.f9629J;
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(this.I, cameraInfo);
                int d = YF.b(this.F).d();
                if (cameraInfo.facing == 0) {
                    i = ((cameraInfo.orientation - d) + 360) % 360;
                } else {
                    i = (360 - ((cameraInfo.orientation + d) % 360)) % 360;
                }
                camera2.setDisplayOrientation(i);
                this.f9629J.setOneShotPreviewCallback(this.G);
                this.f9629J.setErrorCallback(this.H);
                Camera.Parameters parameters = this.f9629J.getParameters();
                parameters.setFocusMode("continuous-picture");
                this.f9629J.setParameters(parameters);
                this.f9629J.startPreview();
            } catch (Exception unused) {
                this.H.onError(1003, this.f9629J);
            }
        }
    }

    public void b() {
        if (this.f9629J != null) {
            c();
            this.f9629J.release();
            this.f9629J = null;
            HandlerThread handlerThread = this.K;
            if (handlerThread != null) {
                handlerThread.quit();
                this.K = null;
            }
        }
    }

    public final void c() {
        getHolder().removeCallback(this);
        Camera camera = this.f9629J;
        if (camera != null) {
            camera.setOneShotPreviewCallback(null);
            this.f9629J.setErrorCallback(null);
            try {
                this.f9629J.stopPreview();
            } catch (RuntimeException unused) {
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        c();
        a();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        a();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        c();
    }
}
