package defpackage;

import J.N;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.os.Build;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.media.PhotoCapabilities;
import org.chromium.media.VideoCapture;
import org.chromium.media.VideoCaptureFormat;

/* renamed from: wt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5500wt1 extends VideoCapture implements Camera.PreviewCallback {
    public static final SparseArray f;
    public int g;
    public final Object h = new Object();
    public long i;
    public int j;
    public int k;
    public Camera.Area l;
    public Camera.Parameters m;
    public Camera n;
    public ReentrantLock o = new ReentrantLock();
    public boolean p;
    public int[] q;
    public SurfaceTexture r;

    static {
        SparseArray sparseArray = new SparseArray();
        f = sparseArray;
        sparseArray.append(2850, "incandescent");
        sparseArray.append(2950, "warm-fluorescent");
        sparseArray.append(4250, "fluorescent");
        sparseArray.append(4600, "twilight");
        sparseArray.append(5500, "daylight");
        sparseArray.append(6000, "cloudy-daylight");
        sparseArray.append(7000, "shade");
    }

    public C5500wt1(int i2, long j2) {
        super(i2, j2);
    }

    public static Camera.CameraInfo e(int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i2, cameraInfo);
            return cameraInfo;
        } catch (RuntimeException e) {
            AbstractC1220Ua0.a("VideoCapture", "getCameraInfo: Camera.getCameraInfo: " + e, new Object[0]);
            return null;
        }
    }

    public static Camera.Parameters f(Camera camera) {
        try {
            return camera.getParameters();
        } catch (RuntimeException e) {
            AbstractC1220Ua0.a("VideoCapture", "getCameraParameters: android.hardware.Camera.getParameters: " + e, new Object[0]);
            if (camera == null) {
                return null;
            }
            camera.release();
            return null;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean allocate(int i2, int i3, int i4, boolean z) {
        int i5;
        try {
            this.n = Camera.open(this.d);
            Camera.CameraInfo e = e(this.d);
            if (e == null) {
                this.n.release();
                this.n = null;
                return false;
            }
            this.f10987a = e.orientation;
            this.b = e.facing == 0;
            b();
            Camera.Parameters f2 = f(this.n);
            if (f2 == null) {
                this.n = null;
                return false;
            }
            List<int[]> supportedPreviewFpsRange = f2.getSupportedPreviewFpsRange();
            if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.size() == 0) {
                AbstractC1220Ua0.a("VideoCapture", "allocate: no fps range found", new Object[0]);
                return false;
            }
            ArrayList arrayList = new ArrayList(supportedPreviewFpsRange.size());
            for (int[] iArr : supportedPreviewFpsRange) {
                arrayList.add(new C2429et1(iArr[0], iArr[1]));
            }
            C2429et1 et1 = (C2429et1) Collections.min(arrayList, new C2258dt1(i4 * 1000));
            int[] iArr2 = {et1.f9889a, et1.b};
            int i6 = iArr2[0];
            int i7 = iArr2[1];
            int i8 = i2;
            int i9 = i3;
            int i10 = Integer.MAX_VALUE;
            for (Camera.Size size : f2.getSupportedPreviewSizes()) {
                int abs = Math.abs(size.height - i3) + Math.abs(size.width - i2);
                if (abs < i10) {
                    int i11 = size.width;
                    if (i11 % 32 == 0) {
                        i9 = size.height;
                        i8 = i11;
                        i10 = abs;
                    }
                }
            }
            if (i10 == Integer.MAX_VALUE) {
                AbstractC1220Ua0.a("VideoCapture", "allocate: can not find a multiple-of-32 resolution", new Object[0]);
                return false;
            }
            if (f2.isVideoStabilizationSupported()) {
                f2.getVideoStabilization();
                f2.setVideoStabilization(true);
            }
            if (f2.getSupportedFocusModes().contains("continuous-video")) {
                f2.setFocusMode("continuous-video");
            }
            int i12 = iArr2[1] / 1000;
            String[] strArr = AbstractC2771gt1.f10030a;
            int length = strArr.length;
            int i13 = 0;
            while (true) {
                if (i13 >= length) {
                    i5 = 842094169;
                    break;
                } else if (strArr[i13].contentEquals(Build.MODEL)) {
                    i5 = 17;
                    break;
                } else {
                    i13++;
                }
            }
            this.c = new VideoCaptureFormat(i8, i9, i12, i5);
            f2.setPictureSize(i8, i9);
            f2.setPreviewSize(i8, i9);
            f2.setPreviewFpsRange(iArr2[0], iArr2[1]);
            f2.setPreviewFormat(this.c.d);
            try {
                this.n.setParameters(f2);
                int[] iArr3 = new int[1];
                this.q = iArr3;
                GLES20.glGenTextures(1, iArr3, 0);
                GLES20.glBindTexture(36197, this.q[0]);
                GLES20.glTexParameterf(36197, 10241, 9729.0f);
                GLES20.glTexParameterf(36197, 10240, 9729.0f);
                GLES20.glTexParameteri(36197, 10242, 33071);
                GLES20.glTexParameteri(36197, 10243, 33071);
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.q[0]);
                this.r = surfaceTexture;
                surfaceTexture.setOnFrameAvailableListener(null);
                try {
                    this.n.setPreviewTexture(this.r);
                    this.n.setErrorCallback(new C2942ht1(this, null));
                    VideoCaptureFormat videoCaptureFormat = this.c;
                    this.g = (ImageFormat.getBitsPerPixel(videoCaptureFormat.d) * (videoCaptureFormat.f10988a * videoCaptureFormat.b)) / 8;
                    for (int i14 = 0; i14 < 3; i14++) {
                        this.n.addCallbackBuffer(new byte[this.g]);
                    }
                    return true;
                } catch (IOException e2) {
                    AbstractC1220Ua0.a("VideoCapture", "allocate: " + e2, new Object[0]);
                    return false;
                }
            } catch (RuntimeException e3) {
                AbstractC1220Ua0.a("VideoCapture", "setParameters: " + e3, new Object[0]);
                return false;
            }
        } catch (RuntimeException e4) {
            AbstractC1220Ua0.a("VideoCapture", "allocate: Camera.open: " + e4, new Object[0]);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void deallocate() {
        Camera camera = this.n;
        if (camera != null) {
            if (camera == null) {
                AbstractC1220Ua0.a("VideoCapture", "stopCaptureAndBlockUntilStopped: mCamera is null", new Object[0]);
            } else {
                this.o.lock();
                try {
                    if (this.p) {
                        this.p = false;
                        this.o.unlock();
                        this.n.stopPreview();
                        this.n.setPreviewCallbackWithBuffer(null);
                    }
                } finally {
                    this.o.unlock();
                }
            }
            try {
                this.n.setPreviewTexture(null);
                int[] iArr = this.q;
                if (iArr != null) {
                    GLES20.glDeleteTextures(1, iArr, 0);
                }
                this.c = null;
                this.n.release();
                this.n = null;
            } catch (IOException e) {
                AbstractC1220Ua0.a("VideoCapture", "deallocate: failed to deallocate camera, " + e, new Object[0]);
            }
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void getPhotoCapabilitiesAsync(long j2) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr;
        C5500wt1 wt1;
        int indexOfValue;
        Camera.Parameters f2 = f(this.n);
        if (f2 == null) {
            this.n = null;
            N.MumkJZ3e(this.e, this, j2, null);
            return;
        }
        boolean[] zArr = new boolean[3];
        double[] dArr = new double[16];
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[3];
        int[][] iArr4 = new int[3][];
        AbstractC1220Ua0.d("VideoCapture", " CAM params: %s", f2.flatten());
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i7 = Integer.MAX_VALUE;
        int i8 = 0;
        int i9 = 0;
        int i10 = Integer.MAX_VALUE;
        for (Camera.Size size : f2.getSupportedPictureSizes()) {
            int i11 = size.width;
            if (i11 < i10) {
                i10 = i11;
            }
            int i12 = size.height;
            if (i12 < i7) {
                i7 = i12;
            }
            if (i11 > i8) {
                i8 = i11;
            }
            if (i12 > i9) {
                i9 = i12;
            }
        }
        Camera.Size previewSize = f2.getPreviewSize();
        iArr2[4] = i7;
        iArr2[5] = i9;
        iArr2[7] = 1;
        iArr2[6] = previewSize.height;
        iArr2[8] = i10;
        iArr2[9] = i8;
        iArr2[11] = 1;
        iArr2[10] = previewSize.width;
        if (f2.isZoomSupported()) {
            i5 = f2.getZoomRatios().get(f2.getMaxZoom()).intValue();
            i3 = f2.getZoomRatios().get(f2.getZoom()).intValue();
            i2 = f2.getZoomRatios().get(0).intValue();
            i4 = f2.getZoomRatios().size() > 1 ? f2.getZoomRatios().get(1).intValue() - f2.getZoomRatios().get(0).intValue() : 0;
        } else {
            i5 = 0;
            i4 = 0;
            i3 = 0;
            i2 = 0;
        }
        dArr[0] = (double) i2;
        dArr[1] = (double) i5;
        dArr[2] = (double) i3;
        dArr[3] = (double) i4;
        List<String> supportedFocusModes = f2.getSupportedFocusModes();
        ArrayList arrayList = new ArrayList(3);
        if (supportedFocusModes.contains("continuous-video") || supportedFocusModes.contains("continuous-picture") || supportedFocusModes.contains("edof")) {
            arrayList.add(4);
        }
        if (supportedFocusModes.contains("auto") || supportedFocusModes.contains("macro")) {
            arrayList.add(3);
        }
        if (supportedFocusModes.contains("infinity") || supportedFocusModes.contains("fixed")) {
            arrayList.add(2);
        }
        iArr4[0] = (int[]) VideoCapture.c(arrayList).clone();
        String focusMode = f2.getFocusMode();
        iArr3[0] = (focusMode.equals("continuous-video") || focusMode.equals("continuous-picture") || focusMode.equals("edof")) ? 4 : (focusMode.equals("auto") || focusMode.equals("macro")) ? 3 : (focusMode.equals("infinity") || focusMode.equals("fixed")) ? 2 : 1;
        ArrayList arrayList2 = new ArrayList(2);
        arrayList2.add(4);
        if (f2.isAutoExposureLockSupported()) {
            arrayList2.add(2);
        }
        iArr4[1] = (int[]) VideoCapture.c(arrayList2).clone();
        iArr3[1] = (!f2.isAutoExposureLockSupported() || !f2.getAutoExposureLock()) ? 4 : 2;
        float exposureCompensationStep = f2.getExposureCompensationStep();
        dArr[11] = (double) exposureCompensationStep;
        dArr[8] = (double) (((float) f2.getMinExposureCompensation()) * exposureCompensationStep);
        dArr[9] = (double) (((float) f2.getMaxExposureCompensation()) * exposureCompensationStep);
        dArr[10] = (double) (((float) f2.getExposureCompensation()) * exposureCompensationStep);
        ArrayList arrayList3 = new ArrayList(2);
        List<String> supportedWhiteBalance = f2.getSupportedWhiteBalance();
        if (supportedWhiteBalance != null) {
            if (!supportedWhiteBalance.isEmpty()) {
                i6 = 4;
                arrayList3.add(4);
            } else {
                i6 = 4;
            }
            if (f2.isAutoWhiteBalanceLockSupported()) {
                arrayList3.add(2);
            }
        } else {
            i6 = 4;
        }
        iArr4[2] = (int[]) VideoCapture.c(arrayList3).clone();
        if (f2.isAutoWhiteBalanceLockSupported() && f2.getAutoWhiteBalanceLock()) {
            i6 = 2;
        }
        iArr3[2] = i6;
        SparseArray sparseArray = f;
        iArr2[12] = sparseArray.keyAt(0);
        iArr2[13] = sparseArray.keyAt(sparseArray.size() - 1);
        iArr2[15] = 50;
        if (i6 == 2 && (indexOfValue = sparseArray.indexOfValue(f2.getWhiteBalance())) >= 0) {
            iArr2[14] = sparseArray.keyAt(indexOfValue);
        }
        List<String> supportedFlashModes = f2.getSupportedFlashModes();
        if (supportedFlashModes != null) {
            zArr[0] = supportedFlashModes.contains("torch");
            zArr[1] = "torch".equals(f2.getFlashMode());
            zArr[2] = supportedFlashModes.contains("red-eye");
            ArrayList arrayList4 = new ArrayList(0);
            if (supportedFlashModes.contains("off")) {
                arrayList4.add(1);
            }
            if (supportedFlashModes.contains("auto")) {
                arrayList4.add(2);
            }
            if (supportedFlashModes.contains("on")) {
                arrayList4.add(3);
            }
            wt1 = this;
            iArr = (int[]) VideoCapture.c(arrayList4).clone();
        } else {
            iArr = null;
            wt1 = this;
        }
        N.MumkJZ3e(wt1.e, this, j2, new PhotoCapabilities(zArr, dArr, iArr2, iArr, iArr3, iArr4));
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.o.lock();
        try {
            if (this.p) {
                int length = bArr.length;
                int i2 = this.g;
                if (length == i2) {
                    N.Mq5gIPzl(this.e, this, bArr, i2, a());
                } else {
                    N.M651cEC1(this.e, this, 8);
                }
                this.o.unlock();
                if (camera != null) {
                    camera.addCallbackBuffer(bArr);
                }
            }
        } finally {
            this.o.unlock();
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void setPhotoOptions(double d, int i2, double d2, int i3, double d3, double d4, double[] dArr, boolean z, double d5, double d6, int i4, double d7, boolean z2, boolean z3, int i5, boolean z4, boolean z5, double d8) {
        Camera.Area area;
        int abs;
        Camera.Parameters f2 = f(this.n);
        if (f2 == null) {
            this.n = null;
            return;
        }
        if (f2.isZoomSupported() && d > 0.0d) {
            List<Integer> zoomRatios = f2.getZoomRatios();
            int i6 = 1;
            while (i6 < zoomRatios.size() && d >= ((double) zoomRatios.get(i6).intValue())) {
                i6++;
                zoomRatios = zoomRatios;
            }
            f2.setZoom(i6 - 1);
        }
        String str = "auto";
        if (i2 == 2) {
            f2.setFocusMode("fixed");
        } else if (i2 == 3) {
            f2.setFocusMode(str);
        } else if (i2 == 4) {
            f2.setFocusMode("continuous-picture");
        }
        if (f2.isAutoExposureLockSupported()) {
            if (i3 == 2) {
                f2.setAutoExposureLock(true);
            } else if (i3 != 1) {
                f2.setAutoExposureLock(false);
            }
        }
        if (d3 > 0.0d) {
            this.j = (int) Math.round(d3);
        }
        if (d4 > 0.0d) {
            this.k = (int) Math.round(d4);
        }
        Camera.Area area2 = this.l;
        if (area2 == null || area2.rect.isEmpty() || d <= 0.0d) {
            area = null;
        } else {
            area = null;
            this.l = null;
        }
        if (i2 == 1 || i3 == 1) {
            this.l = area;
        }
        if ((f2.getMaxNumMeteringAreas() > 0 || f2.getMaxNumFocusAreas() > 0) && dArr.length > 0) {
            int round = (int) (Math.round(dArr[0] * 2000.0d) - 1000);
            int round2 = (int) (Math.round(dArr[1] * 2000.0d) - 1000);
            Camera.Area area3 = new Camera.Area(new Rect(Math.max(-1000, round - 125), Math.max(-1000, round2 - 125), Math.min(1000, round + 125), Math.min(1000, round2 + 125)), 1000);
            this.l = area3;
            area3.rect.toString();
        }
        Camera.Area area4 = this.l;
        if (area4 != null) {
            f2.setFocusAreas(Arrays.asList(area4));
            f2.setMeteringAreas(Arrays.asList(this.l));
        }
        if (z) {
            f2.setExposureCompensation((int) Math.round(d5 / ((double) f2.getExposureCompensationStep())));
        }
        if (i4 == 4 && f2.getSupportedWhiteBalance() != null) {
            f2.setWhiteBalance(str);
        } else if (i4 == 2 && f2.isAutoWhiteBalanceLockSupported()) {
            f2.setAutoWhiteBalanceLock(true);
        }
        if (d8 > 0.0d) {
            int i7 = (int) d8;
            List<String> supportedWhiteBalance = f2.getSupportedWhiteBalance();
            int i8 = Integer.MAX_VALUE;
            String str2 = null;
            int i9 = 0;
            while (true) {
                SparseArray sparseArray = f;
                if (i9 >= sparseArray.size()) {
                    break;
                }
                if (supportedWhiteBalance.contains(sparseArray.valueAt(i9)) && (abs = Math.abs(i7 - sparseArray.keyAt(i9))) < i8) {
                    str2 = (String) sparseArray.valueAt(i9);
                    i8 = abs;
                }
                i9++;
            }
            if (str2 != null) {
                f2.setWhiteBalance(str2);
            }
        }
        if (f2.getSupportedFlashModes() != null) {
            if (z4 && z5) {
                f2.setFlashMode("torch");
            } else if (i5 != 0) {
                if (i5 == 1) {
                    f2.setFlashMode("off");
                } else if (i5 == 2) {
                    if (z2 && z3) {
                        str = "red-eye";
                    }
                    f2.setFlashMode(str);
                } else if (i5 == 3) {
                    f2.setFlashMode("on");
                }
            }
        }
        try {
            this.n.setParameters(f2);
            if (i2 == 3) {
                this.n.autoFocus(new C2600ft1(this));
            }
        } catch (RuntimeException e) {
            AbstractC1220Ua0.a("VideoCapture", "setParameters: ", e);
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean startCaptureMaybeAsync() {
        if (this.n == null) {
            AbstractC1220Ua0.a("VideoCapture", "startCaptureAsync: mCamera is null", new Object[0]);
            return false;
        }
        this.o.lock();
        try {
            if (this.p) {
                return true;
            }
            this.o.unlock();
            this.n.setPreviewCallbackWithBuffer(this);
            try {
                this.n.startPreview();
                this.o.lock();
                try {
                    N.MPaf3s5k(this.e, this);
                    this.p = true;
                    return true;
                } finally {
                    this.o.unlock();
                }
            } catch (RuntimeException e) {
                AbstractC1220Ua0.a("VideoCapture", "startCaptureAsync: Camera.startPreview: " + e, new Object[0]);
                return false;
            }
        } finally {
            this.o.unlock();
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean stopCaptureAndBlockUntilStopped() {
        if (this.n == null) {
            AbstractC1220Ua0.a("VideoCapture", "stopCaptureAndBlockUntilStopped: mCamera is null", new Object[0]);
            return true;
        }
        this.o.lock();
        try {
            if (!this.p) {
                return true;
            }
            this.p = false;
            this.o.unlock();
            this.n.stopPreview();
            this.n.setPreviewCallbackWithBuffer(null);
            return true;
        } finally {
            this.o.unlock();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        r0 = f(r11.n);
        r11.m = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        if (r0 != null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        r11.n = null;
        J.N.MdZBZ$ST(r11.e, r11, r12, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        r0 = f(r11.n);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        if (r0 != null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        r11.n = null;
        J.N.MdZBZ$ST(r11.e, r11, r12, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        r0.setRotation(a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        if (r11.j > 0) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        if (r11.k <= 0) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        r3 = r0.getSupportedPictureSizes().iterator();
        r6 = null;
        r5 = Integer.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        if (r3.hasNext() == false) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
        r7 = r3.next();
        r8 = r11.j;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0076, code lost:
        if (r8 <= 0) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0078, code lost:
        r8 = java.lang.Math.abs(r7.width - r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0080, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        r9 = r11.k;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0083, code lost:
        if (r9 <= 0) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0085, code lost:
        r9 = java.lang.Math.abs(r7.height - r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008d, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        r8 = r8 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008f, code lost:
        if (r8 >= r5) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0091, code lost:
        r6 = r7;
        r5 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0094, code lost:
        if (r5 == Integer.MAX_VALUE) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0096, code lost:
        r3 = r6.width;
        r0.setPictureSize(r6.width, r6.height);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r0.flatten();
        r11.n.setParameters(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a7, code lost:
        r11.n.takePicture(null, null, null, new defpackage.C3112it1(r11, null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b1, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b3, code lost:
        defpackage.AbstractC1220Ua0.a("VideoCapture", "setParameters " + r0, new java.lang.Object[0]);
        J.N.MdZBZ$ST(r11.e, r11, r12, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d3, code lost:
        return;
     */
    @Override // org.chromium.media.VideoCapture
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void takePhotoAsync(long r12) {
        /*
        // Method dump skipped, instructions count: 233
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5500wt1.takePhotoAsync(long):void");
    }
}
