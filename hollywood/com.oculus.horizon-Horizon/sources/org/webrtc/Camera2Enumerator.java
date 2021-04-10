package org.webrtc;

import X.AnonymousClass006;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.SystemClock;
import android.util.Range;
import android.util.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.webrtc.CameraEnumerationAndroid;

@TargetApi(21)
public class Camera2Enumerator implements CameraEnumerationAndroid.Enumerator {
    public static final double NANO_SECONDS_PER_SECOND = 1.0E9d;
    public static final String TAG = "Camera2Enumerator";
    public final Map<Integer, List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats = new HashMap();
    public final CameraManager cameraManager;

    @Override // org.webrtc.CameraEnumerationAndroid.Enumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int i) {
        List<CameraEnumerationAndroid.CaptureFormat> arrayList;
        long j;
        int i2;
        synchronized (this.cachedSupportedFormats) {
            if (this.cachedSupportedFormats.containsKey(Integer.valueOf(i))) {
                arrayList = this.cachedSupportedFormats.get(Integer.valueOf(i));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Get supported formats for camera index ");
                sb.append(i);
                sb.append(".");
                Logging.d(TAG, AnonymousClass006.A02("Get supported formats for camera index ", i, "."));
                long elapsedRealtime = SystemClock.elapsedRealtime();
                try {
                    CameraCharacteristics cameraCharacteristics = this.cameraManager.getCameraCharacteristics(Integer.toString(i));
                    int i3 = 0;
                    for (Range range : (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES)) {
                        i3 = Math.max(i3, ((Integer) range.getUpper()).intValue());
                    }
                    StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    int i4 = 35;
                    Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
                    if (outputSizes != null) {
                        ArrayList arrayList2 = new ArrayList();
                        int length = outputSizes.length;
                        int i5 = 0;
                        while (i5 < length) {
                            Size size = outputSizes[i5];
                            try {
                                j = streamConfigurationMap.getOutputMinFrameDuration(i4, size);
                            } catch (Exception unused) {
                                j = 0;
                            }
                            if (j == 0) {
                                i2 = i3;
                            } else {
                                double d = (double) j;
                                Double.isNaN(d);
                                i2 = (int) Math.round(1.0E9d / d);
                            }
                            arrayList2.add(new CameraEnumerationAndroid.CaptureFormat(size.getWidth(), size.getHeight(), 0, i2 * 1000));
                            i5++;
                            i4 = 35;
                        }
                        this.cachedSupportedFormats.put(Integer.valueOf(i), arrayList2);
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Get supported formats for camera index ");
                        sb2.append(i);
                        sb2.append(" done. Time spent: ");
                        sb2.append(elapsedRealtime2 - elapsedRealtime);
                        sb2.append(" ms.");
                        Logging.d(TAG, sb2.toString());
                        return arrayList2;
                    }
                    throw new RuntimeException("ImageFormat.YUV_420_888 not supported.");
                } catch (Exception e) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("getCameraCharacteristics(): ");
                    sb3.append(e);
                    Logging.e(TAG, sb3.toString());
                    arrayList = new ArrayList<>();
                }
            }
            return arrayList;
        }
    }

    public Camera2Enumerator(Context context) {
        this.cameraManager = (CameraManager) context.getSystemService("camera");
    }

    public static boolean isSupported() {
        return true;
    }
}
