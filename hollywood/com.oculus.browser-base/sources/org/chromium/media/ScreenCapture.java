package org.chromium.media;

import J.N;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ScreenCapture extends Fragment {
    public static final /* synthetic */ int F = 0;
    public final long G;
    public final Object H = new Object();
    public int I = 4;

    /* renamed from: J  reason: collision with root package name */
    public MediaProjection f10986J;
    public MediaProjectionManager K;
    public VirtualDisplay L;
    public Surface M;
    public ImageReader N;
    public HandlerThread O;
    public Handler P;
    public Display Q;
    public int R;
    public Intent S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;

    public ScreenCapture(long j) {
        this.G = j;
    }

    public static ScreenCapture createScreenCaptureMachine(long j) {
        return new ScreenCapture(j);
    }

    public final void a(int i) {
        synchronized (this.H) {
            this.I = i;
            this.H.notifyAll();
        }
    }

    public boolean allocate(int i, int i2) {
        this.U = i;
        this.V = i2;
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) ContextUtils.getApplicationContext().getSystemService("media_projection");
        this.K = mediaProjectionManager;
        if (mediaProjectionManager == null) {
            AbstractC1220Ua0.a("ScreenCapture", "mMediaProjectionManager is null", new Object[0]);
            return false;
        }
        this.Q = ((DisplayManager) ContextUtils.getApplicationContext().getSystemService("display")).getDisplay(0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.Q.getMetrics(displayMetrics);
        this.T = displayMetrics.densityDpi;
        return true;
    }

    public final void b() {
        ImageReader imageReader = this.N;
        if (imageReader != null) {
            imageReader.close();
        }
        ImageReader newInstance = ImageReader.newInstance(this.U, this.V, this.W, 2);
        this.N = newInstance;
        this.M = newInstance.getSurface();
        this.N.setOnImageAvailableListener(new YO0(this, null), this.P);
    }

    public final void c() {
        VirtualDisplay virtualDisplay = this.L;
        if (virtualDisplay != null) {
            virtualDisplay.release();
        }
        this.L = this.f10986J.createVirtualDisplay("ScreenCapture", this.U, this.V, this.T, 16, this.M, null, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean d() {
        /*
            r6 = this;
            android.view.Display r0 = r6.Q
            int r0 = r0.getRotation()
            r1 = 180(0xb4, float:2.52E-43)
            r2 = 90
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L_0x001e
            if (r0 == r4) goto L_0x001c
            r5 = 2
            if (r0 == r5) goto L_0x001a
            r5 = 3
            if (r0 == r5) goto L_0x0017
            goto L_0x001e
        L_0x0017:
            r0 = 270(0x10e, float:3.78E-43)
            goto L_0x001f
        L_0x001a:
            r0 = r1
            goto L_0x001f
        L_0x001c:
            r0 = r2
            goto L_0x001f
        L_0x001e:
            r0 = r3
        L_0x001f:
            if (r0 == 0) goto L_0x0027
            if (r0 == r2) goto L_0x0025
            if (r0 == r1) goto L_0x0027
        L_0x0025:
            r1 = r4
            goto L_0x0028
        L_0x0027:
            r1 = r3
        L_0x0028:
            int r2 = r6.R
            if (r1 != r2) goto L_0x002d
            return r3
        L_0x002d:
            r6.R = r1
            if (r1 != r4) goto L_0x0037
            int r2 = r6.U
            int r3 = r6.V
            if (r2 < r3) goto L_0x003f
        L_0x0037:
            if (r1 != 0) goto L_0x0049
            int r1 = r6.V
            int r2 = r6.U
            if (r1 >= r2) goto L_0x0049
        L_0x003f:
            int r1 = r6.U
            int r2 = r6.V
            r6.V = r1
            int r2 = r2 - r1
            int r2 = r2 + r1
            r6.U = r2
        L_0x0049:
            long r1 = r6.G
            J.N.Ml8UDaFX(r1, r6, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.ScreenCapture.d():boolean");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = true;
        if (i == 1) {
            if (i2 == -1) {
                this.X = i2;
                this.S = intent;
                a(1);
            }
            long j = this.G;
            if (i2 != -1) {
                z = false;
            }
            N.M2auslLM(j, this, z);
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        a(0);
    }

    public void onDetach() {
        super.onDetach();
        stopCapture();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        defpackage.AbstractC1220Ua0.a("ScreenCapture", "mMediaProjection is null", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r0.registerCallback(new defpackage.ZO0(r5, null), null);
        r0 = new android.os.HandlerThread("ScreenCapture");
        r5.O = r0;
        r0.start();
        r5.P = new android.os.Handler(r5.O.getLooper());
        r5.W = 1;
        d();
        b();
        c();
        a(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        r0 = r5.K.getMediaProjection(r5.X, r5.S);
        r5.f10986J = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean startCapture() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.H
            monitor-enter(r0)
            int r1 = r5.I     // Catch:{ all -> 0x005f }
            r2 = 1
            r3 = 0
            if (r1 == r2) goto L_0x0014
            java.lang.String r1 = "ScreenCapture"
            java.lang.String r2 = "startCapture() invoked without user permission."
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x005f }
            defpackage.AbstractC1220Ua0.a(r1, r2, r4)     // Catch:{ all -> 0x005f }
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            return r3
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            android.media.projection.MediaProjectionManager r0 = r5.K
            int r1 = r5.X
            android.content.Intent r4 = r5.S
            android.media.projection.MediaProjection r0 = r0.getMediaProjection(r1, r4)
            r5.f10986J = r0
            if (r0 != 0) goto L_0x002d
            java.lang.String r0 = "ScreenCapture"
            java.lang.String r1 = "mMediaProjection is null"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            defpackage.AbstractC1220Ua0.a(r0, r1, r2)
            return r3
        L_0x002d:
            ZO0 r1 = new ZO0
            r3 = 0
            r1.<init>(r5, r3)
            r0.registerCallback(r1, r3)
            android.os.HandlerThread r0 = new android.os.HandlerThread
            java.lang.String r1 = "ScreenCapture"
            r0.<init>(r1)
            r5.O = r0
            r0.start()
            android.os.Handler r0 = new android.os.Handler
            android.os.HandlerThread r1 = r5.O
            android.os.Looper r1 = r1.getLooper()
            r0.<init>(r1)
            r5.P = r0
            r5.W = r2
            r5.d()
            r5.b()
            r5.c()
            r0 = 2
            r5.a(r0)
            return r2
        L_0x005f:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.ScreenCapture.startCapture():boolean");
    }

    public boolean startPrompt() {
        Activity activity = ApplicationStatus.e;
        if (activity == null) {
            AbstractC1220Ua0.a("ScreenCapture", "activity is null", new Object[0]);
            return false;
        }
        FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
        beginTransaction.add(this, "screencapture");
        try {
            beginTransaction.commit();
            synchronized (this.H) {
                while (this.I != 0) {
                    try {
                        this.H.wait();
                    } catch (InterruptedException e) {
                        AbstractC1220Ua0.a("ScreenCapture", "ScreenCaptureException: " + e, new Object[0]);
                    }
                }
            }
            try {
                startActivityForResult(this.K.createScreenCaptureIntent(), 1);
                return true;
            } catch (ActivityNotFoundException e2) {
                AbstractC1220Ua0.a("ScreenCapture", "ScreenCaptureException " + e2, new Object[0]);
                return false;
            }
        } catch (RuntimeException e3) {
            AbstractC1220Ua0.a("ScreenCapture", "ScreenCaptureExcaption " + e3, new Object[0]);
            return false;
        }
    }

    public void stopCapture() {
        synchronized (this.H) {
            MediaProjection mediaProjection = this.f10986J;
            if (mediaProjection == null || this.I != 2) {
                a(4);
            } else {
                mediaProjection.stop();
                a(3);
                while (this.I != 4) {
                    try {
                        this.H.wait();
                    } catch (InterruptedException e) {
                        AbstractC1220Ua0.a("ScreenCapture", "ScreenCaptureEvent: " + e, new Object[0]);
                    }
                }
            }
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a(0);
    }
}
