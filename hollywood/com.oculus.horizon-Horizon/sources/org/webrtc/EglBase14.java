package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.os.Build;
import android.view.Surface;
import org.webrtc.EglBase;

@TargetApi(18)
public final class EglBase14 extends EglBase {
    public static final int CURRENT_SDK_VERSION = Build.VERSION.SDK_INT;
    public static final int EGLExt_SDK_VERSION = 18;
    public static final String TAG = "EglBase14";
    public EGLConfig eglConfig;
    public EGLContext eglContext;
    public EGLDisplay eglDisplay;
    public EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;

    public static EGLConfig getEglConfig(EGLDisplay eGLDisplay, int[] iArr) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        throw new RuntimeException("Unable to find any matching EGL config");
    }

    public static EGLDisplay getEglDisplay() {
        String str;
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != EGL14.EGL_NO_DISPLAY) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                return eglGetDisplay;
            }
            str = "Unable to initialize EGL14";
        } else {
            str = "Unable to get EGL14 display";
        }
        throw new RuntimeException(str);
    }

    @Override // org.webrtc.EglBase
    public void createDummyPbufferSurface() {
        createPbufferSurface(1, 1);
    }

    @Override // org.webrtc.EglBase
    public int surfaceHeight() {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, iArr, 0);
        return iArr[0];
    }

    @Override // org.webrtc.EglBase
    public int surfaceWidth() {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, iArr, 0);
        return iArr[0];
    }

    public static class Context extends EglBase.Context {
        public final EGLContext egl14Context;

        public Context(EGLContext eGLContext) {
            this.egl14Context = eGLContext;
        }
    }

    private void checkIsNotReleased() {
        if (this.eglDisplay == EGL14.EGL_NO_DISPLAY || this.eglContext == EGL14.EGL_NO_CONTEXT || this.eglConfig == null) {
            throw new RuntimeException("This object has been released");
        }
    }

    public static EGLContext createEglContext(Context context, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        EGLContext eGLContext;
        String str;
        if (context == null || context.egl14Context != EGL14.EGL_NO_CONTEXT) {
            int[] iArr = {12440, 2, 12344};
            if (context == null) {
                eGLContext = EGL14.EGL_NO_CONTEXT;
            } else {
                eGLContext = context.egl14Context;
            }
            EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr, 0);
            if (eglCreateContext != EGL14.EGL_NO_CONTEXT) {
                return eglCreateContext;
            }
            str = "Failed to create EGL context";
        } else {
            str = "Invalid sharedContext";
        }
        throw new RuntimeException(str);
    }

    private void createSurfaceInternal(Object obj) {
        String str;
        if ((obj instanceof Surface) || (obj instanceof SurfaceTexture)) {
            checkIsNotReleased();
            if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
                EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, obj, new int[]{12344}, 0);
                this.eglSurface = eglCreateWindowSurface;
                if (eglCreateWindowSurface == EGL14.EGL_NO_SURFACE) {
                    str = "Failed to create window surface";
                } else {
                    return;
                }
            } else {
                str = "Already has an EGLSurface";
            }
            throw new RuntimeException(str);
        }
        throw new IllegalStateException("Input must be either a Surface or SurfaceTexture");
    }

    public static boolean isEGL14Supported() {
        StringBuilder sb = new StringBuilder("SDK version: ");
        int i = CURRENT_SDK_VERSION;
        sb.append(i);
        sb.append(". isEGL14Supported: ");
        boolean z = false;
        if (i >= 18) {
            z = true;
        }
        sb.append(z);
        Logging.d(TAG, sb.toString());
        if (i < 18) {
            return false;
        }
        return true;
    }

    @Override // org.webrtc.EglBase
    public void detachCurrent() {
        EGLDisplay eGLDisplay = this.eglDisplay;
        EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    @Override // org.webrtc.EglBase
    public boolean hasSurface() {
        if (this.eglSurface != EGL14.EGL_NO_SURFACE) {
            return true;
        }
        return false;
    }

    @Override // org.webrtc.EglBase
    public void releaseSurface() {
        EGLSurface eGLSurface = this.eglSurface;
        if (eGLSurface != EGL14.EGL_NO_SURFACE) {
            EGL14.eglDestroySurface(this.eglDisplay, eGLSurface);
            this.eglSurface = EGL14.EGL_NO_SURFACE;
        }
    }

    public EglBase14(Context context, int[] iArr) {
        EGLDisplay eglDisplay2 = getEglDisplay();
        this.eglDisplay = eglDisplay2;
        EGLConfig eglConfig2 = getEglConfig(eglDisplay2, iArr);
        this.eglConfig = eglConfig2;
        this.eglContext = createEglContext(context, this.eglDisplay, eglConfig2);
    }

    @Override // org.webrtc.EglBase
    public void createPbufferSurface(int i, int i2) {
        String str;
        checkIsNotReleased();
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) {
            EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(this.eglDisplay, this.eglConfig, new int[]{12375, i, 12374, i2, 12344}, 0);
            this.eglSurface = eglCreatePbufferSurface;
            if (eglCreatePbufferSurface == EGL14.EGL_NO_SURFACE) {
                str = "Failed to create pixel buffer surface";
            } else {
                return;
            }
        } else {
            str = "Already has an EGLSurface";
        }
        throw new RuntimeException(str);
    }

    @Override // org.webrtc.EglBase
    public void makeCurrent() {
        String str;
        checkIsNotReleased();
        EGLSurface eGLSurface = this.eglSurface;
        if (eGLSurface == EGL14.EGL_NO_SURFACE) {
            str = "No EGLSurface - can't make current";
        } else if (!EGL14.eglMakeCurrent(this.eglDisplay, eGLSurface, eGLSurface, this.eglContext)) {
            str = "eglMakeCurrent failed";
        } else {
            return;
        }
        throw new RuntimeException(str);
    }

    @Override // org.webrtc.EglBase
    public void release() {
        checkIsNotReleased();
        releaseSurface();
        detachCurrent();
        EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    @Override // org.webrtc.EglBase
    public void createSurface(SurfaceTexture surfaceTexture) {
        createSurfaceInternal(surfaceTexture);
    }

    @Override // org.webrtc.EglBase
    public void createSurface(Surface surface) {
        createSurfaceInternal(surface);
    }

    @Override // org.webrtc.EglBase
    public Context getEglBaseContext() {
        return new Context(this.eglContext);
    }

    @Override // org.webrtc.EglBase
    public void swapBuffers() {
        checkIsNotReleased();
        EGLSurface eGLSurface = this.eglSurface;
        if (eGLSurface != EGL14.EGL_NO_SURFACE) {
            EGL14.eglSwapBuffers(this.eglDisplay, eGLSurface);
            return;
        }
        throw new RuntimeException("No EGLSurface - can't swap buffers");
    }

    public void swapBuffers(long j) {
        checkIsNotReleased();
        EGLSurface eGLSurface = this.eglSurface;
        if (eGLSurface != EGL14.EGL_NO_SURFACE) {
            EGLExt.eglPresentationTimeANDROID(this.eglDisplay, eGLSurface, j);
            EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface);
            return;
        }
        throw new RuntimeException("No EGLSurface - can't swap buffers");
    }
}
