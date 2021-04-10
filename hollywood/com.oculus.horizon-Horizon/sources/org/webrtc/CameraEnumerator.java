package org.webrtc;

import X.AnonymousClass006;
import android.hardware.Camera;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;

public class CameraEnumerator implements CameraEnumerationAndroid.Enumerator {
    public static final String TAG = "CameraEnumerator";
    public List<List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats;

    @Override // org.webrtc.CameraEnumerationAndroid.Enumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int i) {
        synchronized (this) {
            if (this.cachedSupportedFormats == null) {
                this.cachedSupportedFormats = new ArrayList();
                for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
                    this.cachedSupportedFormats.add(enumerateFormats(i2));
                }
            }
        }
        return this.cachedSupportedFormats.get(i);
    }

    private List<CameraEnumerationAndroid.CaptureFormat> enumerateFormats(int i) {
        int i2;
        Logging.d(TAG, AnonymousClass006.A02("Get supported formats for camera index ", i, "."));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Camera camera = null;
        try {
            Logging.d(TAG, AnonymousClass006.A01("Opening camera with index ", i));
            camera = Camera.open(i);
            Camera.Parameters parameters = camera.getParameters();
            camera.release();
            ArrayList arrayList = new ArrayList();
            try {
                List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
                int i3 = 0;
                if (supportedPreviewFpsRange != null) {
                    int[] iArr = supportedPreviewFpsRange.get(supportedPreviewFpsRange.size() - 1);
                    i3 = iArr[0];
                    i2 = iArr[1];
                } else {
                    i2 = 0;
                }
                for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                    arrayList.add(new CameraEnumerationAndroid.CaptureFormat(size.width, size.height, i3, i2));
                }
            } catch (Exception e) {
                Logging.e(TAG, AnonymousClass006.A01("getSupportedFormats() failed on camera index ", i), e);
            }
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append("Get supported formats for camera index ");
            sb.append(i);
            sb.append(" done. Time spent: ");
            sb.append(elapsedRealtime2 - elapsedRealtime);
            sb.append(" ms.");
            Logging.d(TAG, sb.toString());
            return arrayList;
        } catch (RuntimeException e2) {
            Logging.e(TAG, AnonymousClass006.A01("Open camera failed on camera index ", i), e2);
            ArrayList arrayList2 = new ArrayList();
            if (camera != null) {
                camera.release();
            }
            return arrayList2;
        } catch (Throwable th) {
            if (camera != null) {
                camera.release();
            }
            throw th;
        }
    }
}
