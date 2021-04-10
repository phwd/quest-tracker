package org.webrtc;

import X.AnonymousClass006;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.squareup.okhttp.internal.framed.Http2;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;

public class SurfaceViewRenderer extends SurfaceView implements SurfaceHolder.Callback, VideoRenderer.Callbacks {
    public static final String TAG = "SurfaceViewRenderer";
    public Point desiredLayoutSize = new Point();
    public RendererCommon.GlDrawer drawer;
    public EglBase eglBase;
    public long firstFrameTimeNs;
    public int frameHeight;
    public final Object frameLock = new Object();
    public int frameRotation;
    public int frameWidth;
    public int framesDropped;
    public int framesReceived;
    public int framesRendered;
    public final Object handlerLock = new Object();
    public boolean isSurfaceCreated;
    public final Object layoutLock = new Object();
    public final Point layoutSize = new Point();
    public final Runnable makeBlackRunnable = new Runnable() {
        /* class org.webrtc.SurfaceViewRenderer.AnonymousClass2 */

        public void run() {
            SurfaceViewRenderer.this.makeBlack();
        }
    };
    public boolean mirror;
    public VideoRenderer.I420Frame pendingFrame;
    public final Runnable renderFrameRunnable = new Runnable() {
        /* class org.webrtc.SurfaceViewRenderer.AnonymousClass1 */

        public void run() {
            SurfaceViewRenderer.this.renderFrameOnRenderThread();
        }
    };
    public HandlerThread renderThread;
    public Handler renderThreadHandler;
    public long renderTimeNs;
    public RendererCommon.RendererEvents rendererEvents;
    public RendererCommon.ScalingType scalingType = RendererCommon.ScalingType.SCALE_ASPECT_BALANCED;
    public final Object statisticsLock = new Object();
    public final Point surfaceSize = new Point();
    public int[] yuvTextures = null;
    public final RendererCommon.YuvUploader yuvUploader = new RendererCommon.YuvUploader();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        org.webrtc.ThreadUtils.awaitUninterruptibly(r4);
        r5.renderThread.quit();
        r1 = r5.frameLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0 = r5.pendingFrame;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        if (r0 == null) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        org.webrtc.VideoRenderer.renderFrameDone(r0);
        r5.pendingFrame = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        org.webrtc.ThreadUtils.joinUninterruptibly(r5.renderThread);
        r5.renderThread = null;
        r1 = r5.layoutLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r5.frameWidth = 0;
        r5.frameHeight = 0;
        r5.frameRotation = 0;
        r5.rendererEvents = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0052, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        resetStatistics();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
            r5 = this;
            r0 = 1
            java.util.concurrent.CountDownLatch r4 = new java.util.concurrent.CountDownLatch
            r4.<init>(r0)
            java.lang.Object r3 = r5.handlerLock
            monitor-enter(r3)
            android.os.Handler r1 = r5.renderThreadHandler     // Catch:{ all -> 0x005d }
            if (r1 != 0) goto L_0x001e
            java.lang.String r2 = "SurfaceViewRenderer"
            java.lang.String r1 = r5.getResourceName()     // Catch:{ all -> 0x005d }
            java.lang.String r0 = "Already released"
            java.lang.String r0 = X.AnonymousClass006.A05(r1, r0)     // Catch:{ all -> 0x005d }
            org.webrtc.Logging.d(r2, r0)     // Catch:{ all -> 0x005d }
            monitor-exit(r3)     // Catch:{ all -> 0x005d }
            return
        L_0x001e:
            org.webrtc.SurfaceViewRenderer$4 r0 = new org.webrtc.SurfaceViewRenderer$4     // Catch:{ all -> 0x005d }
            r0.<init>(r4)     // Catch:{ all -> 0x005d }
            r1.postAtFrontOfQueue(r0)     // Catch:{ all -> 0x005d }
            r2 = 0
            r5.renderThreadHandler = r2     // Catch:{ all -> 0x005d }
            monitor-exit(r3)     // Catch:{ all -> 0x005d }
            org.webrtc.ThreadUtils.awaitUninterruptibly(r4)
            android.os.HandlerThread r0 = r5.renderThread
            r0.quit()
            java.lang.Object r1 = r5.frameLock
            monitor-enter(r1)
            org.webrtc.VideoRenderer$I420Frame r0 = r5.pendingFrame     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x003e
            org.webrtc.VideoRenderer.renderFrameDone(r0)     // Catch:{ all -> 0x005a }
            r5.pendingFrame = r2     // Catch:{ all -> 0x005a }
        L_0x003e:
            monitor-exit(r1)     // Catch:{ all -> 0x005a }
            android.os.HandlerThread r0 = r5.renderThread
            org.webrtc.ThreadUtils.joinUninterruptibly(r0)
            r5.renderThread = r2
            java.lang.Object r1 = r5.layoutLock
            monitor-enter(r1)
            r0 = 0
            r5.frameWidth = r0     // Catch:{ all -> 0x0057 }
            r5.frameHeight = r0     // Catch:{ all -> 0x0057 }
            r5.frameRotation = r0     // Catch:{ all -> 0x0057 }
            r5.rendererEvents = r2     // Catch:{ all -> 0x0057 }
            monitor-exit(r1)     // Catch:{ all -> 0x0057 }
            r5.resetStatistics()
            return
        L_0x0057:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x005a:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x005d:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.SurfaceViewRenderer.release():void");
    }

    private float frameAspectRatio() {
        float f;
        int i;
        synchronized (this.layoutLock) {
            int i2 = this.frameWidth;
            if (i2 == 0 || (i = this.frameHeight) == 0) {
                f = 0.0f;
            } else {
                float f2 = (float) i;
                if (this.frameRotation % 180 == 0) {
                    f2 = (float) i2;
                    i2 = i;
                }
                f = f2 / ((float) i2);
            }
        }
        return f;
    }

    private Point getDesiredLayoutSize(int i, int i2) {
        Point displaySize;
        synchronized (this.layoutLock) {
            int defaultSize = getDefaultSize(Integer.MAX_VALUE, i);
            int defaultSize2 = getDefaultSize(Integer.MAX_VALUE, i2);
            displaySize = RendererCommon.getDisplaySize(this.scalingType, frameAspectRatio(), defaultSize, defaultSize2);
            if (View.MeasureSpec.getMode(i) == 1073741824) {
                displaySize.x = defaultSize;
            }
            if (View.MeasureSpec.getMode(i2) == 1073741824) {
                displaySize.y = defaultSize2;
            }
        }
        return displaySize;
    }

    private void logStatistics() {
        synchronized (this.statisticsLock) {
            StringBuilder sb = new StringBuilder();
            sb.append(getResourceName());
            sb.append("Frames received: ");
            sb.append(this.framesReceived);
            sb.append(". Dropped: ");
            sb.append(this.framesDropped);
            sb.append(". Rendered: ");
            sb.append(this.framesRendered);
            Logging.d(TAG, sb.toString());
            if (this.framesReceived > 0 && this.framesRendered > 0) {
                long nanoTime = System.nanoTime() - this.firstFrameTimeNs;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(getResourceName());
                sb2.append("Duration: ");
                double d = (double) nanoTime;
                Double.isNaN(d);
                sb2.append((int) (d / 1000000.0d));
                sb2.append(" ms. FPS: ");
                double d2 = (double) this.framesRendered;
                Double.isNaN(d2);
                Double.isNaN(d);
                sb2.append((d2 * 1.0E9d) / d);
                Logging.d(TAG, sb2.toString());
                StringBuilder sb3 = new StringBuilder();
                sb3.append(getResourceName());
                sb3.append("Average render time: ");
                sb3.append((int) (this.renderTimeNs / ((long) (this.framesRendered * 1000))));
                sb3.append(" us.");
                Logging.d(TAG, sb3.toString());
            }
        }
    }

    private void runOnRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            Handler handler = this.renderThreadHandler;
            if (handler != null) {
                handler.post(runnable);
            }
        }
    }

    private void updateFrameDimensionsAndReportEvents(VideoRenderer.I420Frame i420Frame) {
        synchronized (this.layoutLock) {
            if (!(this.frameWidth == i420Frame.width && this.frameHeight == i420Frame.height && this.frameRotation == i420Frame.rotationDegree)) {
                StringBuilder sb = new StringBuilder();
                sb.append(getResourceName());
                sb.append("Reporting frame resolution changed to ");
                sb.append(i420Frame.width);
                sb.append("x");
                sb.append(i420Frame.height);
                sb.append(" with rotation ");
                sb.append(i420Frame.rotationDegree);
                Logging.d(TAG, sb.toString());
                RendererCommon.RendererEvents rendererEvents2 = this.rendererEvents;
                if (rendererEvents2 != null) {
                    rendererEvents2.onFrameResolutionChanged(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                }
                this.frameWidth = i420Frame.width;
                this.frameHeight = i420Frame.height;
                this.frameRotation = i420Frame.rotationDegree;
                post(new Runnable() {
                    /* class org.webrtc.SurfaceViewRenderer.AnonymousClass6 */

                    public void run() {
                        SurfaceViewRenderer.this.requestLayout();
                    }
                });
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        synchronized (this.layoutLock) {
            Point point = this.layoutSize;
            point.x = i3 - i;
            point.y = i4 - i2;
        }
        runOnRenderThread(this.renderFrameRunnable);
    }

    public void onMeasure(int i, int i2) {
        synchronized (this.layoutLock) {
            if (this.frameWidth == 0 || this.frameHeight == 0) {
                super.onMeasure(i, i2);
            } else {
                Point desiredLayoutSize2 = getDesiredLayoutSize(i, i2);
                this.desiredLayoutSize = desiredLayoutSize2;
                if (!(desiredLayoutSize2.x == getMeasuredWidth() && this.desiredLayoutSize.y == getMeasuredHeight())) {
                    synchronized (this.handlerLock) {
                        Handler handler = this.renderThreadHandler;
                        if (handler != null) {
                            handler.postAtFrontOfQueue(this.makeBlackRunnable);
                        }
                    }
                }
                Point point = this.desiredLayoutSize;
                setMeasuredDimension(point.x, point.y);
            }
        }
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        synchronized (this.statisticsLock) {
            this.framesReceived++;
        }
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                Logging.d(TAG, AnonymousClass006.A05(getResourceName(), "Dropping frame - Not initialized or already released."));
                VideoRenderer.renderFrameDone(i420Frame);
            } else {
                synchronized (this.frameLock) {
                    if (this.pendingFrame != null) {
                        synchronized (this.statisticsLock) {
                            this.framesDropped++;
                        }
                        VideoRenderer.renderFrameDone(this.pendingFrame);
                    }
                    this.pendingFrame = i420Frame;
                    updateFrameDimensionsAndReportEvents(i420Frame);
                    this.renderThreadHandler.post(this.renderFrameRunnable);
                }
            }
        }
    }

    public void resetStatistics() {
        synchronized (this.statisticsLock) {
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.firstFrameTimeNs = 0;
            this.renderTimeNs = 0;
        }
    }

    public void setMirror(boolean z) {
        synchronized (this.layoutLock) {
            this.mirror = z;
        }
    }

    public void setScalingType(RendererCommon.ScalingType scalingType2) {
        synchronized (this.layoutLock) {
            this.scalingType = scalingType2;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append(getResourceName());
        sb.append("Surface changed: ");
        sb.append(i2);
        sb.append("x");
        sb.append(i3);
        Logging.d(TAG, sb.toString());
        synchronized (this.layoutLock) {
            Point point = this.surfaceSize;
            point.x = i2;
            point.y = i3;
        }
        runOnRenderThread(this.renderFrameRunnable);
    }

    public void tryCreateEglSurface() {
        runOnRenderThread(new Runnable() {
            /* class org.webrtc.SurfaceViewRenderer.AnonymousClass3 */

            public void run() {
                synchronized (SurfaceViewRenderer.this.layoutLock) {
                    SurfaceViewRenderer surfaceViewRenderer = SurfaceViewRenderer.this;
                    if (surfaceViewRenderer.isSurfaceCreated) {
                        EglBase eglBase = surfaceViewRenderer.eglBase;
                        if (!eglBase.hasSurface()) {
                            eglBase.createSurface(surfaceViewRenderer.getHolder().getSurface());
                            SurfaceViewRenderer.this.eglBase.makeCurrent();
                            GLES20.glPixelStorei(3317, 1);
                        }
                    }
                }
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        if (r3.surfaceSize.equals(r3.layoutSize) == false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkConsistentLayout() {
        /*
            r3 = this;
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            android.os.HandlerThread r0 = r3.renderThread
            if (r1 != r0) goto L_0x0026
            java.lang.Object r2 = r3.layoutLock
            monitor-enter(r2)
            android.graphics.Point r1 = r3.layoutSize     // Catch:{ all -> 0x0023 }
            android.graphics.Point r0 = r3.desiredLayoutSize     // Catch:{ all -> 0x0023 }
            boolean r0 = r1.equals(r0)     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0020
            android.graphics.Point r1 = r3.surfaceSize     // Catch:{ all -> 0x0023 }
            android.graphics.Point r0 = r3.layoutSize     // Catch:{ all -> 0x0023 }
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x0023 }
            r0 = 1
            if (r1 != 0) goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
            return r0
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0023 }
            throw r0
        L_0x0026:
            java.lang.String r1 = r3.getResourceName()
            java.lang.String r0 = "Wrong thread."
            java.lang.String r1 = X.AnonymousClass006.A05(r1, r0)
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.SurfaceViewRenderer.checkConsistentLayout():boolean");
    }

    private String getResourceName() {
        try {
            return AnonymousClass006.A05(getResources().getResourceEntryName(getId()), ": ");
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void makeBlack() {
        if (Thread.currentThread() == this.renderThread) {
            EglBase eglBase2 = this.eglBase;
            if (eglBase2 != null && eglBase2.hasSurface()) {
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                GLES20.glClear(Http2.INITIAL_MAX_FRAME_SIZE);
                this.eglBase.swapBuffers();
                return;
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A05(getResourceName(), "Wrong thread."));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1 = r14.eglBase;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r1 == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        if (r1.hasSurface() == false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        if (checkConsistentLayout() != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        makeBlack();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        org.webrtc.VideoRenderer.renderFrameDone(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        org.webrtc.Logging.d(org.webrtc.SurfaceViewRenderer.TAG, X.AnonymousClass006.A05(getResourceName(), "No surface to draw on"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
        r3 = r14.layoutLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0049, code lost:
        if (r14.eglBase.surfaceWidth() != r14.surfaceSize.x) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r14.eglBase.surfaceHeight() == r14.surfaceSize.y) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        makeBlack();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        r1 = java.lang.System.nanoTime();
        r9 = r14.layoutLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r8 = org.webrtc.RendererCommon.rotateTextureMatrix(r0.samplingMatrix, (float) r0.rotationDegree);
        r7 = r14.mirror;
        r6 = frameAspectRatio();
        r5 = r14.layoutSize;
        r7 = org.webrtc.RendererCommon.multiplyMatrices(r8, org.webrtc.RendererCommon.getLayoutMatrix(r7, r6, ((float) r5.x) / ((float) r5.y)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0082, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0083, code lost:
        android.opengl.GLES20.glClear(com.squareup.okhttp.internal.framed.Http2.INITIAL_MAX_FRAME_SIZE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008a, code lost:
        if (r0.yuvFrame == false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008e, code lost:
        if (r14.yuvTextures != null) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0090, code lost:
        r14.yuvTextures = new int[3];
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0096, code lost:
        r14.yuvTextures[r5] = org.webrtc.GlUtil.generateTexture(3553);
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a2, code lost:
        if (r5 >= 3) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a5, code lost:
        r5 = r14.drawer;
        r6 = r0.textureId;
        r3 = r14.surfaceSize;
        r5.drawOes(r6, r7, 0, 0, r3.x, r3.y);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b5, code lost:
        r14.yuvUploader.uploadYuvData(r14.yuvTextures, r0.width, r0.height, r0.yuvStrides, r0.yuvPlanes);
        r5 = r14.drawer;
        r6 = r14.yuvTextures;
        r3 = r14.surfaceSize;
        r5.drawYuv(r6, r7, 0, 0, r3.x, r3.y);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d3, code lost:
        r14.eglBase.swapBuffers();
        org.webrtc.VideoRenderer.renderFrameDone(r0);
        r7 = r14.statisticsLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00dd, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        if (r14.framesRendered != 0) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e2, code lost:
        r14.firstFrameTimeNs = r1;
        r5 = r14.layoutLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e6, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        org.webrtc.Logging.d(org.webrtc.SurfaceViewRenderer.TAG, X.AnonymousClass006.A05(getResourceName(), "Reporting first rendered frame."));
        r0 = r14.rendererEvents;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f8, code lost:
        if (r0 == null) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fa, code lost:
        r0.onFirstFrameRendered();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fd, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0102, code lost:
        r0 = r14.framesRendered + 1;
        r14.framesRendered = r0;
        r14.renderTimeNs += java.lang.System.nanoTime() - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0114, code lost:
        if ((r0 % com.oculus.horizon.abuse_prevention.AudioCapture.AUDIO_RECORDER_INTERVAL_MS) != 0) goto L_0x0119;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0116, code lost:
        logStatistics();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0119, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderFrameOnRenderThread() {
        /*
        // Method dump skipped, instructions count: 311
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.SurfaceViewRenderer.renderFrameOnRenderThread():void");
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Logging.d(TAG, AnonymousClass006.A05(getResourceName(), "Surface created."));
        synchronized (this.layoutLock) {
            this.isSurfaceCreated = true;
        }
        tryCreateEglSurface();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Logging.d(TAG, AnonymousClass006.A05(getResourceName(), "Surface destroyed."));
        synchronized (this.layoutLock) {
            this.isSurfaceCreated = false;
            Point point = this.surfaceSize;
            point.x = 0;
            point.y = 0;
        }
        runOnRenderThread(new Runnable() {
            /* class org.webrtc.SurfaceViewRenderer.AnonymousClass5 */

            public void run() {
                SurfaceViewRenderer.this.eglBase.releaseSurface();
            }
        });
    }

    public SurfaceViewRenderer(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents2) {
        init(context, rendererEvents2, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents2, int[] iArr, RendererCommon.GlDrawer glDrawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                Logging.d(TAG, AnonymousClass006.A05(getResourceName(), "Initializing."));
                this.rendererEvents = rendererEvents2;
                this.drawer = glDrawer;
                HandlerThread handlerThread = new HandlerThread(TAG);
                this.renderThread = handlerThread;
                handlerThread.start();
                this.eglBase = EglBase.create(context, iArr);
                this.renderThreadHandler = new Handler(this.renderThread.getLooper());
            } else {
                throw new IllegalStateException(AnonymousClass006.A05(getResourceName(), "Already initialized"));
            }
        }
        tryCreateEglSurface();
    }
}
