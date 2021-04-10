package org.chromium.media;

import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Size;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VideoCaptureFactory {
    public static VideoCapture createVideoCapture(int i, long j) {
        if (isLegacyOrDeprecatedDevice(i)) {
            return new C5500wt1(i, j);
        }
        return new C5330vt1(i, j);
    }

    public static int getCaptureApiType(int i) {
        if (!isLegacyOrDeprecatedDevice(i)) {
            CameraCharacteristics i2 = C5330vt1.i(i);
            if (i2 == null) {
                return 12;
            }
            int intValue = ((Integer) i2.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
            if (intValue != 0) {
                return intValue != 1 ? 7 : 8;
            }
            return 9;
        } else if (C5500wt1.e(i) == null) {
            return 12;
        } else {
            return 6;
        }
    }

    public static int getCaptureFormatFramerate(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.c;
    }

    public static int getCaptureFormatHeight(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.b;
    }

    public static int getCaptureFormatPixelFormat(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.d;
    }

    public static int getCaptureFormatWidth(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.f10988a;
    }

    public static String getDeviceId(int i) {
        if (isLegacyOrDeprecatedDevice(i)) {
            SparseArray sparseArray = C5500wt1.f;
            return Integer.toString(i);
        }
        String[] strArr = C5330vt1.f;
        try {
            String[] cameraIdList = ((CameraManager) ContextUtils.getApplicationContext().getSystemService("camera")).getCameraIdList();
            if (i < cameraIdList.length) {
                return cameraIdList[i];
            }
            AbstractC1220Ua0.a("VideoCapture", "Invalid camera index: ", Integer.valueOf(i));
            return null;
        } catch (CameraAccessException e) {
            AbstractC1220Ua0.a("VideoCapture", "manager.getCameraIdList: ", e);
            return null;
        }
    }

    public static String getDeviceName(int i) {
        Integer num;
        boolean z = true;
        String str = "front";
        if (isLegacyOrDeprecatedDevice(i)) {
            Camera.CameraInfo e = C5500wt1.e(i);
            if (e == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("camera ");
            sb.append(i);
            sb.append(", facing ");
            if (e.facing != 1) {
                str = "back";
            }
            sb.append(str);
            return sb.toString();
        }
        CameraCharacteristics i2 = C5330vt1.i(i);
        if (i2 == null) {
            return null;
        }
        int intValue = ((Integer) i2.get(CameraCharacteristics.LENS_FACING)).intValue();
        if (Build.VERSION.SDK_INT < 29 || (num = (Integer) i2.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT)) == null || !num.equals(6)) {
            z = false;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("camera2 ");
        sb2.append(i);
        sb2.append(", facing ");
        if (intValue != 0) {
            str = "back";
        }
        sb2.append(str);
        sb2.append(z ? " infrared" : "");
        return sb2.toString();
    }

    public static VideoCaptureFormat[] getDeviceSupportedFormats(int i) {
        List<Integer> list;
        List<int[]> list2;
        boolean z = true;
        if (isLegacyOrDeprecatedDevice(i)) {
            SparseArray sparseArray = C5500wt1.f;
            try {
                Camera open = Camera.open(i);
                Camera.Parameters f = C5500wt1.f(open);
                if (f == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                try {
                    list = f.getSupportedPreviewFormats();
                } catch (NullPointerException e) {
                    AbstractC1220Ua0.a("VideoCapture", "Camera.Parameters.getSupportedPreviewFormats: ", e);
                    list = null;
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                if (list.size() == 0) {
                    list.add(0);
                }
                for (Integer num : list) {
                    int i2 = 842094169;
                    if (num.intValue() != 842094169) {
                        if (num.intValue() != 17) {
                            i2 = 0;
                        }
                    }
                    try {
                        list2 = f.getSupportedPreviewFpsRange();
                    } catch (StringIndexOutOfBoundsException e2) {
                        AbstractC1220Ua0.a("VideoCapture", "Camera.Parameters.getSupportedPreviewFpsRange: ", e2);
                        list2 = null;
                    }
                    if (list2 == null) {
                        list2 = new ArrayList<>();
                    }
                    if (list2.size() == 0) {
                        list2.add(new int[]{0, 0});
                    }
                    for (int[] iArr : list2) {
                        List<Camera.Size> supportedPreviewSizes = f.getSupportedPreviewSizes();
                        if (supportedPreviewSizes == null) {
                            supportedPreviewSizes = new ArrayList<>();
                        }
                        if (supportedPreviewSizes.size() == 0) {
                            open.getClass();
                            supportedPreviewSizes.add(new Camera.Size(open, 0, 0));
                        }
                        for (Camera.Size size : supportedPreviewSizes) {
                            arrayList.add(new VideoCaptureFormat(size.width, size.height, (iArr[1] + 999) / 1000, i2));
                        }
                    }
                }
                open.release();
                return (VideoCaptureFormat[]) arrayList.toArray(new VideoCaptureFormat[arrayList.size()]);
            } catch (RuntimeException e3) {
                AbstractC1220Ua0.a("VideoCapture", "Camera.open: ", e3);
                return null;
            }
        } else {
            CameraCharacteristics i3 = C5330vt1.i(i);
            if (i3 == null) {
                return null;
            }
            int[] iArr2 = (int[]) i3.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            int length = iArr2.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    z = false;
                    break;
                } else if (iArr2[i4] == 1) {
                    break;
                } else {
                    i4++;
                }
            }
            ArrayList arrayList2 = new ArrayList();
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) i3.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            int[] outputFormats = streamConfigurationMap.getOutputFormats();
            for (int i5 : outputFormats) {
                Size[] outputSizes = streamConfigurationMap.getOutputSizes(i5);
                if (outputSizes != null) {
                    for (Size size2 : outputSizes) {
                        double d = 0.0d;
                        if (z) {
                            long outputMinFrameDuration = streamConfigurationMap.getOutputMinFrameDuration(i5, size2);
                            if (outputMinFrameDuration != 0) {
                                d = 1.0E9d / ((double) outputMinFrameDuration);
                            }
                        }
                        arrayList2.add(new VideoCaptureFormat(size2.getWidth(), size2.getHeight(), (int) d, i5));
                    }
                }
            }
            return (VideoCaptureFormat[]) arrayList2.toArray(new VideoCaptureFormat[arrayList2.size()]);
        }
    }

    public static int getFacingMode(int i) {
        if (isLegacyOrDeprecatedDevice(i)) {
            Camera.CameraInfo e = C5500wt1.e(i);
            if (e != null) {
                int i2 = e.facing;
                if (i2 == 0) {
                    return 2;
                }
                if (i2 == 1) {
                    return 1;
                }
            }
            return 0;
        }
        CameraCharacteristics i3 = C5330vt1.i(i);
        if (i3 != null) {
            int intValue = ((Integer) i3.get(CameraCharacteristics.LENS_FACING)).intValue();
            if (intValue == 0) {
                return 1;
            }
            if (intValue == 1) {
                return 2;
            }
        }
        return 0;
    }

    public static int getNumberOfCameras() {
        if (AbstractC5670xt1.f11646a == -1) {
            String[] strArr = C5330vt1.f;
            int i = 0;
            try {
                CameraManager cameraManager = (CameraManager) ContextUtils.getApplicationContext().getSystemService("camera");
                if (cameraManager != null) {
                    try {
                        i = cameraManager.getCameraIdList().length;
                    } catch (CameraAccessException | AssertionError | SecurityException e) {
                        AbstractC1220Ua0.a("VideoCapture", "getNumberOfCameras: getCameraIdList(): ", e);
                    }
                }
            } catch (IllegalArgumentException e2) {
                AbstractC1220Ua0.a("VideoCapture", "getSystemService(Context.CAMERA_SERVICE): ", e2);
            }
            AbstractC5670xt1.f11646a = i;
        }
        return AbstractC5670xt1.f11646a;
    }

    public static boolean isLegacyOrDeprecatedDevice(int i) {
        CameraCharacteristics i2 = C5330vt1.i(i);
        return i2 != null && ((Integer) i2.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2;
    }

    public static boolean isZoomSupported(int i) {
        boolean z = true;
        if (isLegacyOrDeprecatedDevice(i)) {
            SparseArray sparseArray = C5500wt1.f;
            try {
                Camera open = Camera.open(i);
                Camera.Parameters f = C5500wt1.f(open);
                if (f == null) {
                    return false;
                }
                boolean isZoomSupported = f.isZoomSupported();
                open.release();
                return isZoomSupported;
            } catch (RuntimeException e) {
                AbstractC1220Ua0.a("VideoCapture", "Camera.open: ", e);
                return false;
            }
        } else {
            CameraCharacteristics i2 = C5330vt1.i(i);
            if (i2 == null) {
                return false;
            }
            if (((Float) i2.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() <= 1.0f) {
                z = false;
            }
            return z;
        }
    }
}
