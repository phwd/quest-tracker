package defpackage;

import J.N;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.media.ImageReader;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.media.VideoCapture;

/* renamed from: vt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5330vt1 extends VideoCapture {
    public static final String[] f = {"Pixel 3", "Pixel 3 XL"};
    public static final SparseIntArray g;
    public int A;
    public int B = 4;
    public int C = -1;
    public int D;
    public boolean E;
    public int F = 1;
    public boolean G;
    public boolean H;
    public final Object h = new Object();
    public CameraDevice i;
    public CameraCaptureSession j;
    public CaptureRequest k;
    public CaptureRequest.Builder l;
    public ImageReader m;
    public Handler n;
    public ConditionVariable o = new ConditionVariable();
    public Range p;
    public int q = 3;
    public float r = 1.0f;
    public Rect s = new Rect();
    public int t;
    public int u;
    public int v = 4;
    public float w = 1.0f;
    public int x = 4;
    public long y;
    public MeteringRectangle z;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        g = sparseIntArray;
        sparseIntArray.append(2850, 2);
        sparseIntArray.append(2950, 4);
        sparseIntArray.append(4250, 3);
        sparseIntArray.append(4600, 7);
        sparseIntArray.append(5000, 5);
        sparseIntArray.append(6000, 6);
        sparseIntArray.append(7000, 8);
    }

    public C5330vt1(int i2, long j2) {
        super(i2, j2);
        N.Mqw5545M(j2, this);
        HandlerThread handlerThread = new HandlerThread("VideoCaptureCamera2_CameraThread");
        handlerThread.start();
        this.n = new Handler(handlerThread.getLooper());
        CameraCharacteristics i3 = i(i2);
        if (i3 != null) {
            this.r = ((Float) i3.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e4, code lost:
        if (r1 == null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f5, code lost:
        if (r1 == null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f7, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        if (r1 != null) goto L_0x00f7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void e(defpackage.C5330vt1 r10, int r11) {
        /*
        // Method dump skipped, instructions count: 279
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5330vt1.e(vt1, int):void");
    }

    public static Size h(Size[] sizeArr, int i2, int i3) {
        if (sizeArr == null) {
            return null;
        }
        Size size = null;
        int i4 = Integer.MAX_VALUE;
        for (Size size2 : sizeArr) {
            int abs = (i2 > 0 ? Math.abs(size2.getWidth() - i2) : 0) + (i3 > 0 ? Math.abs(size2.getHeight() - i3) : 0);
            if (abs < i4) {
                size = size2;
                i4 = abs;
            }
        }
        if (i4 != Integer.MAX_VALUE) {
            return size;
        }
        AbstractC1220Ua0.a("VideoCapture", "Couldn't find resolution close to (%dx%d)", Integer.valueOf(i2), Integer.valueOf(i3));
        return null;
    }

    public static CameraCharacteristics i(int i2) {
        CameraManager cameraManager = (CameraManager) ContextUtils.getApplicationContext().getSystemService("camera");
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (i2 < cameraIdList.length) {
                return cameraManager.getCameraCharacteristics(cameraIdList[i2]);
            }
            AbstractC1220Ua0.a("VideoCapture", "Invalid camera index: ", Integer.valueOf(i2));
            return null;
        } catch (CameraAccessException | AssertionError | IllegalArgumentException e) {
            AbstractC1220Ua0.a("VideoCapture", "getCameraCharacteristics: ", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        if (r4 == 0) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        if (r4 != 180) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        r12 = r11;
        r11 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        r11 = h(r1.getOutputSizes(35), r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r11 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        defpackage.AbstractC1220Ua0.a("VideoCapture", "No supported resolutions.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        r12 = java.util.Arrays.asList((android.util.Range[]) r0.get(android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        if (r12.isEmpty() == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        defpackage.AbstractC1220Ua0.a("VideoCapture", "No supported framerate ranges.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        r1 = new java.util.ArrayList(r12.size());
        r6 = 1000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0085, code lost:
        if (((java.lang.Integer) ((android.util.Range) r12.get(0)).getUpper()).intValue() <= 1000) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        r12 = r12.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0090, code lost:
        if (r12.hasNext() == false) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        r5 = (android.util.Range) r12.next();
        r1.add(new defpackage.C2429et1(((java.lang.Integer) r5.getLower()).intValue() * r6, ((java.lang.Integer) r5.getUpper()).intValue() * r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b7, code lost:
        r12 = (defpackage.C2429et1) java.util.Collections.min(r1, new defpackage.C2258dt1(r13 * 1000));
        r10.p = new android.util.Range(java.lang.Integer.valueOf(r12.f9889a / r6), java.lang.Integer.valueOf(r12.b / r6));
        r11.getWidth();
        r11.getHeight();
        r10.p.getLower();
        r10.p.getUpper();
        r10.c = new org.chromium.media.VideoCaptureFormat(r11.getWidth(), r11.getHeight(), r13, 35);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0104, code lost:
        if (((java.lang.Integer) r0.get(android.hardware.camera2.CameraCharacteristics.LENS_FACING)).intValue() != 1) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0106, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0107, code lost:
        r10.b = r2;
        r10.H = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x010b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r0 = i(r10.d);
        r1 = (android.hardware.camera2.params.StreamConfigurationMap) r0.get(android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        r4 = ((java.lang.Integer) r0.get(android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        r10.f10987a = r4;
     */
    @Override // org.chromium.media.VideoCapture
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean allocate(int r11, int r12, int r13, boolean r14) {
        /*
        // Method dump skipped, instructions count: 282
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5330vt1.allocate(int, int, int, boolean):boolean");
    }

    @Override // org.chromium.media.VideoCapture
    public void deallocate() {
    }

    public final void f(int i2) {
        synchronized (this.h) {
            this.q = i2;
            this.h.notifyAll();
        }
    }

    public void finalize() {
        this.n.getLooper().quit();
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e0 A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00fa A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0149 A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x015a A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x017a A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01ca A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01e8 A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01f3 A[Catch:{ all -> 0x0202, all -> 0x020a }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01fe  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void g(android.hardware.camera2.CaptureRequest.Builder r20) {
        /*
        // Method dump skipped, instructions count: 530
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5330vt1.g(android.hardware.camera2.CaptureRequest$Builder):void");
    }

    @Override // org.chromium.media.VideoCapture
    public void getPhotoCapabilitiesAsync(long j2) {
        N.Mqw5545M(this.e, this);
        this.n.post(new RunnableC4480qt1(this, j2));
    }

    @Override // org.chromium.media.VideoCapture
    public void setPhotoOptions(double d, int i2, double d2, int i3, double d3, double d4, double[] dArr, boolean z2, double d5, double d6, int i4, double d7, boolean z3, boolean z4, int i5, boolean z5, boolean z6, double d8) {
        N.Mqw5545M(this.e, this);
        this.n.post(new RunnableC4820st1(this, new C4650rt1(this, d, i2, d2, i3, d3, d4, dArr, z2, d5, d6, i4, d7, z3, z4, i5, z5, z6, d8)));
    }

    @Override // org.chromium.media.VideoCapture
    public boolean startCaptureMaybeAsync() {
        N.Mqw5545M(this.e, this);
        f(0);
        CameraManager cameraManager = (CameraManager) ContextUtils.getApplicationContext().getSystemService("camera");
        C4309pt1 pt1 = new C4309pt1(this, null);
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            int i2 = this.d;
            if (i2 >= cameraIdList.length) {
                AbstractC1220Ua0.a("VideoCapture", "Invalid camera Id: ", Integer.valueOf(i2));
                return false;
            }
            TraceEvent.i0("VideoCaptureCamera2.java", "VideoCaptureCamera2.startCaptureMaybeAsync calling manager.openCamera");
            cameraManager.openCamera(cameraIdList[this.d], pt1, this.n);
            return true;
        } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
            AbstractC1220Ua0.a("VideoCapture", "allocate: manager.openCamera: ", e);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        if (r0 == null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        r7.n.post(new defpackage.RunnableC4990tt1(r7, null));
        r7.o.block();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r0 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        return true;
     */
    @Override // org.chromium.media.VideoCapture
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean stopCaptureAndBlockUntilStopped() {
        /*
            r7 = this;
            long r0 = r7.e
            J.N.Mqw5545M(r0, r7)
            java.lang.String r0 = "VideoCaptureCamera2.stopCaptureAndBlockUntilStopped"
            org.chromium.base.TraceEvent r0 = org.chromium.base.TraceEvent.j0(r0)
            java.lang.Object r1 = r7.h     // Catch:{ all -> 0x004e }
            monitor-enter(r1)     // Catch:{ all -> 0x004e }
        L_0x000e:
            int r2 = r7.q     // Catch:{ all -> 0x004b }
            r3 = 2
            r4 = 3
            r5 = 1
            if (r2 == r3) goto L_0x002b
            if (r2 == r4) goto L_0x002b
            java.lang.Object r2 = r7.h     // Catch:{ InterruptedException -> 0x001d }
            r2.wait()     // Catch:{ InterruptedException -> 0x001d }
            goto L_0x000e
        L_0x001d:
            r2 = move-exception
            java.lang.String r3 = "VideoCapture"
            java.lang.String r4 = "CaptureStartedEvent: "
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r2
            defpackage.AbstractC1220Ua0.a(r3, r4, r5)
            goto L_0x000e
        L_0x002b:
            if (r2 != r4) goto L_0x0034
            monitor-exit(r1)
            if (r0 == 0) goto L_0x0033
            r0.close()
        L_0x0033:
            return r5
        L_0x0034:
            monitor-exit(r1)
            android.os.Handler r1 = r7.n
            tt1 r2 = new tt1
            r3 = 0
            r2.<init>(r7, r3)
            r1.post(r2)
            android.os.ConditionVariable r1 = r7.o
            r1.block()
            if (r0 == 0) goto L_0x004a
            r0.close()
        L_0x004a:
            return r5
        L_0x004b:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x004e:
            r1 = move-exception
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ all -> 0x0055 }
            goto L_0x005b
        L_0x0055:
            r0 = move-exception
            Gh1 r2 = defpackage.AbstractC0754Mh1.f8495a
            r2.a(r1, r0)
        L_0x005b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5330vt1.stopCaptureAndBlockUntilStopped():boolean");
    }

    @Override // org.chromium.media.VideoCapture
    public void takePhotoAsync(long j2) {
        N.Mqw5545M(this.e, this);
        TraceEvent.i0("VideoCaptureCamera2.java", "takePhotoAsync");
        this.n.post(new RunnableC5160ut1(this, j2));
    }
}
