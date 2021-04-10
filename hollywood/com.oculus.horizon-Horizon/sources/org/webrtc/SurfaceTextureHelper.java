package org.webrtc;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;

public class SurfaceTextureHelper {
    public static final String TAG = "SurfaceTextureHelper";
    public final EglBase eglBase;
    public final Handler handler;
    public boolean hasPendingTexture;
    public boolean isOwningThread;
    public boolean isQuitting;
    public volatile boolean isTextureInUse;
    public OnTextureFrameAvailableListener listener;
    public final int oesTextureId;
    public final SurfaceTexture surfaceTexture;
    public YuvConverter yuvConverter;

    public interface OnTextureFrameAvailableListener {
        void onTextureFrameAvailable(int i, float[] fArr, long j);
    }

    public static class YuvConverter {
        public static final FloatBuffer DEVICE_RECTANGLE = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
        public static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oesTex;\nuniform vec2 xUnit;\nuniform vec4 coeffs;\n\nvoid main() {\n  gl_FragColor.r = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc - 1.5 * xUnit).rgb);\n  gl_FragColor.g = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc - 0.5 * xUnit).rgb);\n  gl_FragColor.b = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc + 0.5 * xUnit).rgb);\n  gl_FragColor.a = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc + 1.5 * xUnit).rgb);\n}\n";
        public static final FloatBuffer TEXTURE_RECTANGLE = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
        public static final String VERTEX_SHADER = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
        public int coeffsLoc;
        public final EglBase eglBase;
        public boolean released = false;
        public final GlShader shader;
        public int texMatrixLoc;
        public int xUnitLoc;

        public synchronized void release() {
            this.released = true;
            this.eglBase.makeCurrent();
            this.shader.release();
            this.eglBase.release();
        }

        public synchronized void convert(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, float[] fArr) {
            int i5;
            if (this.released) {
                throw new IllegalStateException("YuvConverter.convert called on released object");
            } else if (i3 % 8 != 0) {
                throw new IllegalArgumentException("Invalid stride, must be a multiple of 8");
            } else if (i3 >= i) {
                int i6 = (i + 3) / 4;
                int i7 = (i + 7) / 8;
                int i8 = (i2 + 1) / 2;
                int i9 = i2 + i8;
                if (byteBuffer.capacity() >= i3 * i9) {
                    float[] multiplyMatrices = RendererCommon.multiplyMatrices(fArr, RendererCommon.verticalFlipMatrix());
                    EglBase eglBase2 = this.eglBase;
                    if (eglBase2.hasSurface()) {
                        i5 = i3 / 4;
                        if (!(eglBase2.surfaceWidth() == i5 && this.eglBase.surfaceHeight() == i9)) {
                            this.eglBase.releaseSurface();
                            this.eglBase.createPbufferSurface(i5, i9);
                        }
                    } else {
                        i5 = i3 / 4;
                        eglBase2.createPbufferSurface(i5, i9);
                    }
                    this.eglBase.makeCurrent();
                    GLES20.glActiveTexture(33984);
                    GLES20.glBindTexture(36197, i4);
                    GLES20.glUniformMatrix4fv(this.texMatrixLoc, 1, false, multiplyMatrices, 0);
                    GLES20.glViewport(0, 0, i6, i2);
                    float f = (float) i;
                    GLES20.glUniform2f(this.xUnitLoc, multiplyMatrices[0] / f, multiplyMatrices[1] / f);
                    GLES20.glUniform4f(this.coeffsLoc, 0.299f, 0.587f, 0.114f, 0.0f);
                    GLES20.glDrawArrays(5, 0, 4);
                    GLES20.glViewport(0, i2, i7, i8);
                    float f2 = f * 2.0f;
                    GLES20.glUniform2f(this.xUnitLoc, multiplyMatrices[0] / f2, multiplyMatrices[1] / f2);
                    GLES20.glUniform4f(this.coeffsLoc, -0.169f, -0.331f, 0.499f, 0.5f);
                    GLES20.glDrawArrays(5, 0, 4);
                    GLES20.glViewport(i3 / 8, i2, i7, i8);
                    GLES20.glUniform4f(this.coeffsLoc, 0.499f, -0.418f, -0.0813f, 0.5f);
                    GLES20.glDrawArrays(5, 0, 4);
                    GLES20.glReadPixels(0, 0, i5, i9, 6408, 5121, byteBuffer);
                    GlUtil.checkNoGLES2Error("YuvConverter.convert");
                    GLES20.glBindTexture(36197, 0);
                    this.eglBase.detachCurrent();
                } else {
                    throw new IllegalArgumentException("YuvConverter.convert called with too small buffer");
                }
            } else {
                throw new IllegalArgumentException("Invalid stride, must >= width");
            }
        }

        public YuvConverter(EglBase.Context context) {
            EglBase create = EglBase.create(context, EglBase.CONFIG_PIXEL_RGBA_BUFFER);
            this.eglBase = create;
            create.createDummyPbufferSurface();
            this.eglBase.makeCurrent();
            GlShader glShader = new GlShader("varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n", FRAGMENT_SHADER);
            this.shader = glShader;
            glShader.useProgram();
            this.texMatrixLoc = this.shader.getUniformLocation("texMatrix");
            this.xUnitLoc = this.shader.getUniformLocation("xUnit");
            this.coeffsLoc = this.shader.getUniformLocation("coeffs");
            GLES20.glUniform1i(this.shader.getUniformLocation("oesTex"), 0);
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
            this.shader.setVertexAttribArray("in_pos", 2, DEVICE_RECTANGLE);
            this.shader.setVertexAttribArray("in_tc", 2, TEXTURE_RECTANGLE);
            this.eglBase.detachCurrent();
        }
    }

    private YuvConverter getYuvConverter() {
        YuvConverter yuvConverter2;
        YuvConverter yuvConverter3 = this.yuvConverter;
        if (yuvConverter3 != null) {
            return yuvConverter3;
        }
        synchronized (this) {
            yuvConverter2 = this.yuvConverter;
            if (yuvConverter2 == null) {
                yuvConverter2 = new YuvConverter(this.eglBase.getEglBaseContext());
                this.yuvConverter = yuvConverter2;
            }
        }
        return yuvConverter2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void release() {
        String str;
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            str = "Wrong thread.";
        } else if (this.isTextureInUse || !this.isQuitting) {
            str = "Unexpected release.";
        } else {
            synchronized (this) {
                YuvConverter yuvConverter2 = this.yuvConverter;
                if (yuvConverter2 != null) {
                    yuvConverter2.release();
                }
            }
            this.eglBase.makeCurrent();
            GLES20.glDeleteTextures(1, new int[]{this.oesTextureId}, 0);
            this.surfaceTexture.release();
            this.eglBase.release();
            this.handler.getLooper().quit();
            return;
        }
        throw new IllegalStateException(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void tryDeliverTextureFrame() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (!this.isQuitting && this.hasPendingTexture && !this.isTextureInUse) {
            this.isTextureInUse = true;
            this.hasPendingTexture = false;
            this.eglBase.makeCurrent();
            this.surfaceTexture.updateTexImage();
            float[] fArr = new float[16];
            this.surfaceTexture.getTransformMatrix(fArr);
            this.listener.onTextureFrameAvailable(this.oesTextureId, fArr, this.surfaceTexture.getTimestamp());
        }
    }

    public void returnTextureFrame() {
        this.handler.post(new Runnable() {
            /* class org.webrtc.SurfaceTextureHelper.AnonymousClass3 */

            public void run() {
                SurfaceTextureHelper.this.isTextureInUse = false;
                SurfaceTextureHelper surfaceTextureHelper = SurfaceTextureHelper.this;
                if (surfaceTextureHelper.isQuitting) {
                    surfaceTextureHelper.release();
                } else {
                    surfaceTextureHelper.tryDeliverTextureFrame();
                }
            }
        });
    }

    public void setListener(OnTextureFrameAvailableListener onTextureFrameAvailableListener) {
        if (this.listener == null) {
            this.listener = onTextureFrameAvailableListener;
            this.surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                /* class org.webrtc.SurfaceTextureHelper.AnonymousClass2 */

                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    SurfaceTextureHelper surfaceTextureHelper = SurfaceTextureHelper.this;
                    surfaceTextureHelper.hasPendingTexture = true;
                    surfaceTextureHelper.tryDeliverTextureFrame();
                }
            });
            return;
        }
        throw new IllegalStateException("SurfaceTextureHelper listener has already been set.");
    }

    public void textureToYUV(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, float[] fArr) {
        if (i4 == this.oesTextureId) {
            getYuvConverter().convert(byteBuffer, i, i2, i3, i4, fArr);
            return;
        }
        throw new IllegalStateException("textureToByteBuffer called with unexpected textureId");
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    public SurfaceTextureHelper(EglBase.Context context, Handler handler2, boolean z) {
        this.hasPendingTexture = false;
        this.isTextureInUse = false;
        this.isQuitting = false;
        if (handler2.getLooper().getThread() == Thread.currentThread()) {
            this.handler = handler2;
            this.isOwningThread = z;
            EglBase create = EglBase.create(context, EglBase.CONFIG_PIXEL_BUFFER);
            this.eglBase = create;
            create.createDummyPbufferSurface();
            this.eglBase.makeCurrent();
            int generateTexture = GlUtil.generateTexture(36197);
            this.oesTextureId = generateTexture;
            this.surfaceTexture = new SurfaceTexture(generateTexture);
            return;
        }
        throw new IllegalStateException("SurfaceTextureHelper must be created on the handler thread");
    }

    public static SurfaceTextureHelper create(EglBase.Context context) {
        return create(context, null);
    }

    public static SurfaceTextureHelper create(final EglBase.Context context, final Handler handler2) {
        final Handler handler3;
        if (handler2 != null) {
            handler3 = handler2;
        } else {
            HandlerThread handlerThread = new HandlerThread(TAG);
            handlerThread.start();
            handler3 = new Handler(handlerThread.getLooper());
        }
        return (SurfaceTextureHelper) ThreadUtils.invokeUninterruptibly(handler3, new Callable<SurfaceTextureHelper>() {
            /* class org.webrtc.SurfaceTextureHelper.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public SurfaceTextureHelper call() {
                EglBase.Context context = context;
                Handler handler = handler3;
                boolean z = false;
                if (handler2 == null) {
                    z = true;
                }
                return new SurfaceTextureHelper(context, handler, z);
            }
        });
    }

    public void disconnect() {
        if (!this.isOwningThread) {
            throw new IllegalStateException("Must call disconnect(handler).");
        } else if (this.handler.getLooper().getThread() == Thread.currentThread()) {
            this.isQuitting = true;
            if (!this.isTextureInUse) {
                release();
            }
        } else {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            this.handler.postAtFrontOfQueue(new Runnable() {
                /* class org.webrtc.SurfaceTextureHelper.AnonymousClass4 */

                public void run() {
                    SurfaceTextureHelper.this.isQuitting = true;
                    countDownLatch.countDown();
                    if (!SurfaceTextureHelper.this.isTextureInUse) {
                        SurfaceTextureHelper.this.release();
                    }
                }
            });
            ThreadUtils.awaitUninterruptibly(countDownLatch);
        }
    }

    public void disconnect(Handler handler2) {
        if (this.handler == handler2) {
            this.isOwningThread = true;
            disconnect();
            return;
        }
        throw new IllegalStateException("Wrong handler.");
    }
}
