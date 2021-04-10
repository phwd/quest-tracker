package org.webrtc;

import X.AnonymousClass006;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraEnumerationAndroid {
    public static final String TAG = "CameraEnumerationAndroid";
    public static Enumerator enumerator = new CameraEnumerator();

    public static class CaptureFormat {
        public final int height;
        public final int imageFormat = 17;
        public final int maxFramerate;
        public final int minFramerate;
        public final int width;

        public boolean isSameFormat(CaptureFormat captureFormat) {
            return captureFormat != null && this.width == captureFormat.width && this.height == captureFormat.height && this.maxFramerate == captureFormat.maxFramerate && this.minFramerate == captureFormat.minFramerate;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.width);
            sb.append("x");
            sb.append(this.height);
            sb.append("@[");
            sb.append(this.minFramerate);
            sb.append(":");
            sb.append(this.maxFramerate);
            sb.append("]");
            return sb.toString();
        }

        public CaptureFormat(int i, int i2, int i3, int i4) {
            this.width = i;
            this.height = i2;
            this.minFramerate = i3;
            this.maxFramerate = i4;
        }

        public static int frameSize(int i, int i2, int i3) {
            if (i3 == 17) {
                return ((i * i2) * ImageFormat.getBitsPerPixel(i3)) >> 3;
            }
            throw new UnsupportedOperationException("Don't know how to calculate the frame size of non-NV21 image formats.");
        }

        public int frameSize() {
            return frameSize(this.width, this.height, 17);
        }
    }

    public interface Enumerator {
        List<CaptureFormat> getSupportedFormats(int i);
    }

    public static String getNameOfBackFacingDevice() {
        return getNameOfDevice(0);
    }

    public static String getNameOfFrontFacingDevice() {
        return getNameOfDevice(1);
    }

    public static abstract class ClosestComparator<T> implements Comparator<T> {
        public abstract int diff(T t);

        @Override // java.util.Comparator
        public int compare(T t, T t2) {
            return diff(t) - diff(t2);
        }

        public ClosestComparator() {
        }

        public /* synthetic */ ClosestComparator(AnonymousClass1 r1) {
        }
    }

    public static Camera.Size getClosestSupportedSize(List<Camera.Size> list, final int i, final int i2) {
        return (Camera.Size) Collections.min(list, new ClosestComparator<Camera.Size>() {
            /* class org.webrtc.CameraEnumerationAndroid.AnonymousClass2 */

            public int diff(Camera.Size size) {
                return Math.abs(i - size.width) + Math.abs(i2 - size.height);
            }
        });
    }

    public static String getDeviceName(int i) {
        String str;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                str = "front";
            } else {
                str = "back";
            }
            StringBuilder sb = new StringBuilder("Camera ");
            sb.append(i);
            sb.append(", Facing ");
            sb.append(str);
            sb.append(", Orientation ");
            sb.append(cameraInfo.orientation);
            return sb.toString();
        } catch (Exception e) {
            Logging.e(TAG, AnonymousClass006.A01("getCameraInfo failed on index ", i), e);
            return null;
        }
    }

    public static String getNameOfDevice(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            try {
                Camera.getCameraInfo(i2, cameraInfo);
                if (cameraInfo.facing == i) {
                    return getDeviceName(i2);
                }
            } catch (Exception e) {
                Logging.e(TAG, AnonymousClass006.A01("getCameraInfo() failed on index ", i2), e);
            }
        }
        return null;
    }

    public static synchronized List<CaptureFormat> getSupportedFormats(int i) {
        List<CaptureFormat> supportedFormats;
        synchronized (CameraEnumerationAndroid.class) {
            supportedFormats = enumerator.getSupportedFormats(i);
            StringBuilder sb = new StringBuilder();
            sb.append("Supported formats for camera ");
            sb.append(i);
            sb.append(": ");
            sb.append(supportedFormats);
            Logging.d(TAG, sb.toString());
        }
        return supportedFormats;
    }

    public static synchronized void setEnumerator(Enumerator enumerator2) {
        synchronized (CameraEnumerationAndroid.class) {
            enumerator = enumerator2;
        }
    }

    public static int getDeviceCount() {
        return Camera.getNumberOfCameras();
    }

    public static String[] getDeviceNames() {
        String[] strArr = new String[Camera.getNumberOfCameras()];
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            strArr[i] = getDeviceName(i);
        }
        return strArr;
    }

    public static int[] getFramerateRange(Camera.Parameters parameters, final int i) {
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (!supportedPreviewFpsRange.isEmpty()) {
            return (int[]) Collections.min(supportedPreviewFpsRange, new ClosestComparator<int[]>() {
                /* class org.webrtc.CameraEnumerationAndroid.AnonymousClass1 */

                public int diff(int[] iArr) {
                    return iArr[0] + (Math.abs(i - iArr[1]) * 10);
                }
            });
        }
        Logging.w(TAG, "No supported preview fps range");
        return new int[]{0, 0};
    }
}
