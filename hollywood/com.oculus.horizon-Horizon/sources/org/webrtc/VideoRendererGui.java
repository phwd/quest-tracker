package org.webrtc;

import X.AnonymousClass006;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.oculus.horizon.abuse_prevention.AudioCapture;
import com.squareup.okhttp.internal.framed.Http2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;
import org.webrtc.EglBase;
import org.webrtc.EglBase10;
import org.webrtc.EglBase14;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;

public class VideoRendererGui implements GLSurfaceView.Renderer {
    public static final String TAG = "VideoRendererGui";
    public static Thread drawThread;
    public static EglBase.Context eglContext;
    public static Runnable eglContextReady;
    public static VideoRendererGui instance;
    public static Thread renderFrameThread;
    public boolean onSurfaceCreatedCalled;
    public int screenHeight;
    public int screenWidth;
    public GLSurfaceView surface;
    public final ArrayList<YuvImageRenderer> yuvImageRenderers = new ArrayList<>();

    public static class YuvImageRenderer implements VideoRenderer.Callbacks {
        public long copyTimeNs;
        public final Rect displayLayout;
        public long drawTimeNs;
        public final RendererCommon.GlDrawer drawer;
        public int framesDropped;
        public int framesReceived;
        public int framesRendered;
        public int id;
        public final Rect layoutInPercentage;
        public float[] layoutMatrix;
        public boolean mirror;
        public VideoRenderer.I420Frame pendingFrame;
        public final Object pendingFrameLock;
        public RendererCommon.RendererEvents rendererEvents;
        public RendererType rendererType;
        public float[] rotatedSamplingMatrix;
        public int rotationDegree;
        public RendererCommon.ScalingType scalingType;
        public int screenHeight;
        public int screenWidth;
        public boolean seenFrame;
        public long startTimeNs;
        public GLSurfaceView surface;
        public GlTextureFrameBuffer textureCopy;
        public final Object updateLayoutLock;
        public boolean updateLayoutProperties;
        public int videoHeight;
        public int videoWidth;
        public int[] yuvTextures;
        public final RendererCommon.YuvUploader yuvUploader;

        public enum RendererType {
            RENDERER_YUV,
            RENDERER_TEXTURE
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized void release() {
            this.surface = null;
            this.drawer.release();
            synchronized (this.pendingFrameLock) {
                VideoRenderer.I420Frame i420Frame = this.pendingFrame;
                if (i420Frame != null) {
                    VideoRenderer.renderFrameDone(i420Frame);
                    this.pendingFrame = null;
                }
            }
        }

        @Override // org.webrtc.VideoRenderer.Callbacks
        public synchronized void renderFrame(VideoRenderer.I420Frame i420Frame) {
            int[] iArr;
            int i;
            int i2;
            int i3;
            if (this.surface == null) {
                VideoRenderer.renderFrameDone(i420Frame);
            } else {
                if (VideoRendererGui.renderFrameThread == null) {
                    VideoRendererGui.renderFrameThread = Thread.currentThread();
                }
                if (!this.seenFrame && this.rendererEvents != null) {
                    Logging.d(VideoRendererGui.TAG, AnonymousClass006.A02("ID: ", this.id, ". Reporting first rendered frame."));
                    this.rendererEvents.onFirstFrameRendered();
                }
                this.framesReceived++;
                synchronized (this.pendingFrameLock) {
                    if (i420Frame.yuvFrame && ((i = (iArr = i420Frame.yuvStrides)[0]) < (i2 = i420Frame.width) || iArr[1] < (i3 = i2 / 2) || iArr[2] < i3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Incorrect strides ");
                        sb.append(i);
                        sb.append(", ");
                        sb.append(iArr[1]);
                        sb.append(", ");
                        sb.append(iArr[2]);
                        Logging.e(VideoRendererGui.TAG, sb.toString());
                        VideoRenderer.renderFrameDone(i420Frame);
                    } else if (this.pendingFrame != null) {
                        this.framesDropped++;
                        VideoRenderer.renderFrameDone(i420Frame);
                        this.seenFrame = true;
                    } else {
                        this.pendingFrame = i420Frame;
                        setSize(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                        this.seenFrame = true;
                        this.surface.requestRender();
                    }
                }
            }
        }

        public synchronized void reset() {
            this.seenFrame = false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void createTextures() {
            StringBuilder sb = new StringBuilder("  YuvImageRenderer.createTextures ");
            sb.append(this.id);
            sb.append(" on GL thread:");
            sb.append(Thread.currentThread().getId());
            Logging.d(VideoRendererGui.TAG, sb.toString());
            int i = 0;
            do {
                this.yuvTextures[i] = GlUtil.generateTexture(3553);
                i++;
            } while (i < 3);
            this.textureCopy = new GlTextureFrameBuffer(6407);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void draw() {
            boolean z;
            if (this.seenFrame) {
                long nanoTime = System.nanoTime();
                synchronized (this.pendingFrameLock) {
                    VideoRenderer.I420Frame i420Frame = this.pendingFrame;
                    z = false;
                    if (i420Frame != null) {
                        z = true;
                        if (this.startTimeNs == -1) {
                            this.startTimeNs = nanoTime;
                        }
                        this.rotatedSamplingMatrix = RendererCommon.rotateTextureMatrix(i420Frame.samplingMatrix, (float) i420Frame.rotationDegree);
                        VideoRenderer.I420Frame i420Frame2 = this.pendingFrame;
                        if (i420Frame2.yuvFrame) {
                            this.rendererType = RendererType.RENDERER_YUV;
                            this.yuvUploader.uploadYuvData(this.yuvTextures, i420Frame2.width, i420Frame2.height, i420Frame2.yuvStrides, i420Frame2.yuvPlanes);
                        } else {
                            this.rendererType = RendererType.RENDERER_TEXTURE;
                            this.textureCopy.setSize(i420Frame2.rotatedWidth(), i420Frame2.rotatedHeight());
                            GLES20.glBindFramebuffer(36160, this.textureCopy.frameBufferId);
                            GlUtil.checkNoGLES2Error("glBindFramebuffer");
                            RendererCommon.GlDrawer glDrawer = this.drawer;
                            int i = this.pendingFrame.textureId;
                            float[] fArr = this.rotatedSamplingMatrix;
                            GlTextureFrameBuffer glTextureFrameBuffer = this.textureCopy;
                            glDrawer.drawOes(i, fArr, 0, 0, glTextureFrameBuffer.width, glTextureFrameBuffer.height);
                            this.rotatedSamplingMatrix = RendererCommon.identityMatrix();
                            GLES20.glBindFramebuffer(36160, 0);
                            GLES20.glFinish();
                        }
                        this.copyTimeNs += System.nanoTime() - nanoTime;
                        VideoRenderer.renderFrameDone(this.pendingFrame);
                        this.pendingFrame = null;
                    }
                }
                updateLayoutMatrix();
                float[] multiplyMatrices = RendererCommon.multiplyMatrices(this.rotatedSamplingMatrix, this.layoutMatrix);
                int i2 = this.screenHeight;
                Rect rect = this.displayLayout;
                int i3 = i2 - rect.bottom;
                RendererType rendererType2 = this.rendererType;
                RendererType rendererType3 = RendererType.RENDERER_YUV;
                RendererCommon.GlDrawer glDrawer2 = this.drawer;
                if (rendererType2 == rendererType3) {
                    glDrawer2.drawYuv(this.yuvTextures, multiplyMatrices, rect.left, i3, rect.width(), this.displayLayout.height());
                } else {
                    glDrawer2.drawRgb(this.textureCopy.textureId, multiplyMatrices, rect.left, i3, rect.width(), this.displayLayout.height());
                }
                if (z) {
                    int i4 = this.framesRendered + 1;
                    this.framesRendered = i4;
                    this.drawTimeNs += System.nanoTime() - nanoTime;
                    if (i4 % AudioCapture.AUDIO_RECORDER_INTERVAL_MS == 0) {
                        logStatistics();
                    }
                }
            }
        }

        private void setSize(int i, int i2, int i3) {
            if (i != this.videoWidth || i2 != this.videoHeight || i3 != this.rotationDegree) {
                if (this.rendererEvents != null) {
                    StringBuilder sb = new StringBuilder("ID: ");
                    sb.append(this.id);
                    sb.append(". Reporting frame resolution changed to ");
                    sb.append(i);
                    sb.append(" x ");
                    sb.append(i2);
                    Logging.d(VideoRendererGui.TAG, sb.toString());
                    this.rendererEvents.onFrameResolutionChanged(i, i2, i3);
                }
                synchronized (this.updateLayoutLock) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("ID: ");
                    sb2.append(this.id);
                    sb2.append(". YuvImageRenderer.setSize: ");
                    sb2.append(i);
                    sb2.append(" x ");
                    sb2.append(i2);
                    sb2.append(" rotation ");
                    sb2.append(i3);
                    Logging.d(VideoRendererGui.TAG, sb2.toString());
                    this.videoWidth = i;
                    this.videoHeight = i2;
                    this.rotationDegree = i3;
                    this.updateLayoutProperties = true;
                    Logging.d(VideoRendererGui.TAG, "  YuvImageRenderer.setSize done.");
                }
            }
        }

        private void updateLayoutMatrix() {
            float f;
            int i;
            synchronized (this.updateLayoutLock) {
                if (this.updateLayoutProperties) {
                    Rect rect = this.displayLayout;
                    int i2 = this.screenWidth;
                    Rect rect2 = this.layoutInPercentage;
                    int i3 = this.screenHeight;
                    rect.set(((rect2.left * i2) + 99) / 100, ((rect2.top * i3) + 99) / 100, (i2 * rect2.right) / 100, (i3 * rect2.bottom) / 100);
                    StringBuilder sb = new StringBuilder();
                    sb.append("ID: ");
                    sb.append(this.id);
                    sb.append(". AdjustTextureCoords. Allowed display size: ");
                    sb.append(this.displayLayout.width());
                    sb.append(" x ");
                    sb.append(this.displayLayout.height());
                    sb.append(". Video: ");
                    sb.append(this.videoWidth);
                    sb.append(" x ");
                    sb.append(this.videoHeight);
                    sb.append(". Rotation: ");
                    sb.append(this.rotationDegree);
                    sb.append(". Mirror: ");
                    sb.append(this.mirror);
                    Logging.d(VideoRendererGui.TAG, sb.toString());
                    if (this.rotationDegree % 180 == 0) {
                        f = (float) this.videoWidth;
                        i = this.videoHeight;
                    } else {
                        f = (float) this.videoHeight;
                        i = this.videoWidth;
                    }
                    float f2 = f / ((float) i);
                    Point displaySize = RendererCommon.getDisplaySize(this.scalingType, f2, this.displayLayout.width(), this.displayLayout.height());
                    Rect rect3 = this.displayLayout;
                    rect3.inset((rect3.width() - displaySize.x) / 2, (this.displayLayout.height() - displaySize.y) / 2);
                    Logging.d(VideoRendererGui.TAG, AnonymousClass006.A03("  Adjusted display size: ", this.displayLayout.width(), " x ", this.displayLayout.height()));
                    this.layoutMatrix = RendererCommon.getLayoutMatrix(this.mirror, f2, ((float) this.displayLayout.width()) / ((float) this.displayLayout.height()));
                    this.updateLayoutProperties = false;
                    Logging.d(VideoRendererGui.TAG, "  AdjustTextureCoords done");
                }
            }
        }

        public void setPosition(int i, int i2, int i3, int i4, RendererCommon.ScalingType scalingType2, boolean z) {
            Rect rect = new Rect(i, i2, Math.min(100, i + i3), Math.min(100, i2 + i4));
            synchronized (this.updateLayoutLock) {
                if (!(rect.equals(this.layoutInPercentage) && scalingType2 == this.scalingType && z == this.mirror)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ID: ");
                    sb.append(this.id);
                    sb.append(". YuvImageRenderer.setPosition: (");
                    sb.append(i);
                    sb.append(", ");
                    sb.append(i2);
                    sb.append(") ");
                    sb.append(i3);
                    sb.append(" x ");
                    sb.append(i4);
                    sb.append(". Scaling: ");
                    sb.append(scalingType2);
                    sb.append(". Mirror: ");
                    sb.append(z);
                    Logging.d(VideoRendererGui.TAG, sb.toString());
                    this.layoutInPercentage.set(rect);
                    this.scalingType = scalingType2;
                    this.mirror = z;
                    this.updateLayoutProperties = true;
                }
            }
        }

        public void setScreenSize(int i, int i2) {
            synchronized (this.updateLayoutLock) {
                if (!(i == this.screenWidth && i2 == this.screenHeight)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ID: ");
                    sb.append(this.id);
                    sb.append(". YuvImageRenderer.setScreenSize: ");
                    sb.append(i);
                    sb.append(" x ");
                    sb.append(i2);
                    Logging.d(VideoRendererGui.TAG, sb.toString());
                    this.screenWidth = i;
                    this.screenHeight = i2;
                    this.updateLayoutProperties = true;
                }
            }
        }

        private void logStatistics() {
            int i;
            long nanoTime = System.nanoTime() - this.startTimeNs;
            StringBuilder sb = new StringBuilder("ID: ");
            sb.append(this.id);
            sb.append(". Type: ");
            sb.append(this.rendererType);
            sb.append(". Frames received: ");
            sb.append(this.framesReceived);
            sb.append(". Dropped: ");
            sb.append(this.framesDropped);
            sb.append(". Rendered: ");
            sb.append(this.framesRendered);
            Logging.d(VideoRendererGui.TAG, sb.toString());
            if (this.framesReceived > 0 && (i = this.framesRendered) > 0) {
                StringBuilder sb2 = new StringBuilder("Duration: ");
                double d = (double) nanoTime;
                sb2.append((int) (d / 1000000.0d));
                sb2.append(" ms. FPS: ");
                sb2.append((((double) i) * 1.0E9d) / d);
                Logging.d(VideoRendererGui.TAG, sb2.toString());
                StringBuilder sb3 = new StringBuilder("Draw time: ");
                sb3.append((int) (this.drawTimeNs / ((long) (this.framesRendered * 1000))));
                sb3.append(" us. Copy time: ");
                sb3.append((int) (this.copyTimeNs / ((long) (this.framesReceived * 1000))));
                sb3.append(" us");
                Logging.d(VideoRendererGui.TAG, sb3.toString());
            }
        }

        public YuvImageRenderer(GLSurfaceView gLSurfaceView, int i, int i2, int i3, int i4, int i5, RendererCommon.ScalingType scalingType2, boolean z, RendererCommon.GlDrawer glDrawer) {
            this.yuvTextures = new int[]{0, 0, 0};
            this.yuvUploader = new RendererCommon.YuvUploader();
            this.pendingFrameLock = new Object();
            this.startTimeNs = -1;
            this.displayLayout = new Rect();
            this.updateLayoutLock = new Object();
            Logging.d(VideoRendererGui.TAG, AnonymousClass006.A01("YuvImageRenderer.Create id: ", i));
            this.surface = gLSurfaceView;
            this.id = i;
            this.scalingType = scalingType2;
            this.mirror = z;
            this.drawer = glDrawer;
            this.layoutInPercentage = new Rect(i2, i3, Math.min(100, i4 + i2), Math.min(100, i5 + i3));
            this.updateLayoutProperties = false;
            this.rotationDegree = 0;
        }
    }

    public static synchronized void dispose() {
        synchronized (VideoRendererGui.class) {
            if (instance != null) {
                Logging.d(TAG, "VideoRendererGui.dispose");
                synchronized (instance.yuvImageRenderers) {
                    Iterator<YuvImageRenderer> it = instance.yuvImageRenderers.iterator();
                    while (it.hasNext()) {
                        it.next().release();
                    }
                    instance.yuvImageRenderers.clear();
                }
                renderFrameThread = null;
                drawThread = null;
                instance.surface = null;
                eglContext = null;
                eglContextReady = null;
                instance = null;
            }
        }
    }

    public static synchronized EglBase.Context getEglBaseContext() {
        EglBase.Context context;
        synchronized (VideoRendererGui.class) {
            context = eglContext;
        }
        return context;
    }

    public static void printStackTrace(Thread thread, String str) {
        StackTraceElement[] stackTrace;
        int length;
        if (thread != null && (length = (stackTrace = thread.getStackTrace()).length) > 0) {
            Logging.d(TAG, AnonymousClass006.A05(str, " stacks trace:"));
            for (int i = 0; i < length; i++) {
                Logging.d(TAG, stackTrace[i].toString());
            }
        }
    }

    public static synchronized void printStackTraces() {
        synchronized (VideoRendererGui.class) {
            if (instance != null) {
                printStackTrace(renderFrameThread, "Render frame thread");
                printStackTrace(drawThread, "Draw thread");
            }
        }
    }

    public static synchronized void remove(VideoRenderer.Callbacks callbacks) {
        synchronized (VideoRendererGui.class) {
            Logging.d(TAG, "VideoRendererGui.remove");
            VideoRendererGui videoRendererGui = instance;
            if (videoRendererGui != null) {
                synchronized (videoRendererGui.yuvImageRenderers) {
                    int indexOf = instance.yuvImageRenderers.indexOf(callbacks);
                    if (indexOf == -1) {
                        Logging.w(TAG, "Couldn't remove renderer (not present in current list)");
                    } else {
                        instance.yuvImageRenderers.remove(indexOf).release();
                    }
                }
            } else {
                throw new RuntimeException("Attempt to remove renderer before setting GLSurfaceView");
            }
        }
    }

    public static synchronized void reset(VideoRenderer.Callbacks callbacks) {
        synchronized (VideoRendererGui.class) {
            Logging.d(TAG, "VideoRendererGui.reset");
            VideoRendererGui videoRendererGui = instance;
            if (videoRendererGui != null) {
                synchronized (videoRendererGui.yuvImageRenderers) {
                    Iterator<YuvImageRenderer> it = instance.yuvImageRenderers.iterator();
                    while (it.hasNext()) {
                        YuvImageRenderer next = it.next();
                        if (next == callbacks) {
                            next.reset();
                        }
                    }
                }
            } else {
                throw new RuntimeException("Attempt to reset renderer before setting GLSurfaceView");
            }
        }
    }

    public static synchronized void setRendererEvents(VideoRenderer.Callbacks callbacks, RendererCommon.RendererEvents rendererEvents) {
        synchronized (VideoRendererGui.class) {
            Logging.d(TAG, "VideoRendererGui.setRendererEvents");
            VideoRendererGui videoRendererGui = instance;
            if (videoRendererGui != null) {
                synchronized (videoRendererGui.yuvImageRenderers) {
                    Iterator<YuvImageRenderer> it = instance.yuvImageRenderers.iterator();
                    while (it.hasNext()) {
                        YuvImageRenderer next = it.next();
                        if (next == callbacks) {
                            next.rendererEvents = rendererEvents;
                        }
                    }
                }
            } else {
                throw new RuntimeException("Attempt to set renderer events before setting GLSurfaceView");
            }
        }
    }

    public static synchronized void setView(GLSurfaceView gLSurfaceView, Runnable runnable) {
        synchronized (VideoRendererGui.class) {
            Logging.d(TAG, "VideoRendererGui.setView");
            instance = new VideoRendererGui(gLSurfaceView);
            eglContextReady = runnable;
        }
    }

    public static synchronized void update(VideoRenderer.Callbacks callbacks, int i, int i2, int i3, int i4, RendererCommon.ScalingType scalingType, boolean z) {
        synchronized (VideoRendererGui.class) {
            Logging.d(TAG, "VideoRendererGui.update");
            VideoRendererGui videoRendererGui = instance;
            if (videoRendererGui != null) {
                synchronized (videoRendererGui.yuvImageRenderers) {
                    Iterator<YuvImageRenderer> it = instance.yuvImageRenderers.iterator();
                    while (it.hasNext()) {
                        YuvImageRenderer next = it.next();
                        if (next == callbacks) {
                            next.setPosition(i, i2, i3, i4, scalingType, z);
                        }
                    }
                }
            } else {
                throw new RuntimeException("Attempt to update yuv renderer before setting GLSurfaceView");
            }
        }
    }

    public void onDrawFrame(GL10 gl10) {
        if (drawThread == null) {
            drawThread = Thread.currentThread();
        }
        GLES20.glViewport(0, 0, this.screenWidth, this.screenHeight);
        GLES20.glClear(Http2.INITIAL_MAX_FRAME_SIZE);
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                it.next().draw();
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        StringBuilder sb = new StringBuilder("VideoRendererGui.onSurfaceChanged: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        sb.append("  ");
        Logging.d(TAG, sb.toString());
        this.screenWidth = i;
        this.screenHeight = i2;
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                it.next().setScreenSize(this.screenWidth, this.screenHeight);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        EglBase.Context context;
        Logging.d(TAG, "VideoRendererGui.onSurfaceCreated");
        synchronized (VideoRendererGui.class) {
            if (EglBase14.isEGL14Supported()) {
                context = new EglBase14.Context(EGL14.eglGetCurrentContext());
                eglContext = context;
            } else {
                context = new EglBase10.Context(((EGL10) EGLContext.getEGL()).eglGetCurrentContext());
                eglContext = context;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("VideoRendererGui EGL Context: ");
            sb.append(context);
            Logging.d(TAG, sb.toString());
        }
        synchronized (this.yuvImageRenderers) {
            Iterator<YuvImageRenderer> it = this.yuvImageRenderers.iterator();
            while (it.hasNext()) {
                it.next().createTextures();
            }
            this.onSurfaceCreatedCalled = true;
        }
        GlUtil.checkNoGLES2Error("onSurfaceCreated done");
        GLES20.glPixelStorei(3317, 1);
        GLES20.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        synchronized (VideoRendererGui.class) {
            Runnable runnable = eglContextReady;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public VideoRendererGui(GLSurfaceView gLSurfaceView) {
        this.surface = gLSurfaceView;
        gLSurfaceView.setPreserveEGLContextOnPause(true);
        gLSurfaceView.setEGLContextClientVersion(2);
        gLSurfaceView.setRenderer(this);
        gLSurfaceView.setRenderMode(0);
    }

    public static VideoRenderer createGui(int i, int i2, int i3, int i4, RendererCommon.ScalingType scalingType, boolean z) throws Exception {
        return new VideoRenderer(create(i, i2, i3, i4, scalingType, z));
    }

    public static VideoRenderer.Callbacks createGuiRenderer(int i, int i2, int i3, int i4, RendererCommon.ScalingType scalingType, boolean z) {
        return create(i, i2, i3, i4, scalingType, z);
    }

    public static synchronized YuvImageRenderer create(int i, int i2, int i3, int i4, RendererCommon.ScalingType scalingType, boolean z) {
        YuvImageRenderer create;
        synchronized (VideoRendererGui.class) {
            create = create(i, i2, i3, i4, scalingType, z, new GlRectDrawer());
        }
        return create;
    }

    public static synchronized YuvImageRenderer create(int i, int i2, int i3, int i4, RendererCommon.ScalingType scalingType, boolean z, RendererCommon.GlDrawer glDrawer) {
        final YuvImageRenderer yuvImageRenderer;
        synchronized (VideoRendererGui.class) {
            if (i < 0 || i > 100 || i2 < 0 || i2 > 100 || i3 < 0 || i3 > 100 || i4 < 0 || i4 > 100 || i + i3 > 100 || i2 + i4 > 100) {
                throw new RuntimeException("Incorrect window parameters.");
            }
            VideoRendererGui videoRendererGui = instance;
            if (videoRendererGui != null) {
                yuvImageRenderer = new YuvImageRenderer(videoRendererGui.surface, videoRendererGui.yuvImageRenderers.size(), i, i2, i3, i4, scalingType, z, glDrawer);
                synchronized (instance.yuvImageRenderers) {
                    if (instance.onSurfaceCreatedCalled) {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        instance.surface.queueEvent(new Runnable() {
                            /* class org.webrtc.VideoRendererGui.AnonymousClass1 */

                            public void run() {
                                yuvImageRenderer.createTextures();
                                YuvImageRenderer yuvImageRenderer = yuvImageRenderer;
                                VideoRendererGui videoRendererGui = VideoRendererGui.instance;
                                yuvImageRenderer.setScreenSize(videoRendererGui.screenWidth, videoRendererGui.screenHeight);
                                countDownLatch.countDown();
                            }
                        });
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    instance.yuvImageRenderers.add(yuvImageRenderer);
                }
            } else {
                throw new RuntimeException("Attempt to create yuv renderer before setting GLSurfaceView");
            }
        }
        return yuvImageRenderer;
    }
}
