package org.webrtc.videoengine;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.facebook.acra.AppComponentStats;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;
import org.webrtc.Logging;

public class ViEAndroidGLES20 extends GLSurfaceView implements GLSurfaceView.Renderer {
    public static final boolean DEBUG = false;
    public static final String TAG = "WEBRTC-JR";
    public ReentrantLock nativeFunctionLock = new ReentrantLock();
    public boolean nativeFunctionsRegisted = false;
    public long nativeObject = 0;
    public boolean openGLCreated = false;
    public boolean surfaceCreated = false;
    public int viewHeight = 0;
    public int viewWidth = 0;

    public static class ConfigChooser implements GLSurfaceView.EGLConfigChooser {
        public static final int EGL_OPENGL_ES2_BIT = 4;
        public static int[] s_configAttribs2 = {12324, 4, 12323, 4, 12322, 4, 12352, 4, 12344};
        public int mAlphaSize;
        public int mBlueSize;
        public int mDepthSize;
        public int mGreenSize;
        public int mRedSize;
        public int mStencilSize;
        public int[] mValue = new int[1];

        private void printConfigs(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            int length = eGLConfigArr.length;
            Logging.w(ViEAndroidGLES20.TAG, String.format("%d configurations", Integer.valueOf(length)));
            for (int i = 0; i < length; i++) {
                Logging.w(ViEAndroidGLES20.TAG, String.format("Configuration %d:\n", Integer.valueOf(i)));
                printConfig(egl10, eGLDisplay, eGLConfigArr[i]);
            }
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.mValue)) {
                return this.mValue[0];
            }
            return i2;
        }

        private void printConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {12320, 12321, 12322, 12323, 12324, 12325, 12326, 12327, 12328, 12329, 12330, 12331, 12332, 12333, 12334, 12335, 12336, 12337, 12338, 12339, 12340, 12343, 12342, 12341, 12345, 12346, 12347, 12348, 12349, 12350, 12351, 12352, 12354};
            String[] strArr = {"EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT"};
            int[] iArr2 = new int[1];
            int i = 0;
            do {
                int i2 = iArr[i];
                String str = strArr[i];
                if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, iArr2)) {
                    Logging.w(ViEAndroidGLES20.TAG, String.format("  %s: %d\n", str, Integer.valueOf(iArr2[0])));
                } else {
                    do {
                    } while (egl10.eglGetError() != 12288);
                }
                i++;
            } while (i < 33);
        }

        public ConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            this.mRedSize = i;
            this.mGreenSize = i2;
            this.mBlueSize = i3;
            this.mAlphaSize = i4;
            this.mDepthSize = i5;
            this.mStencilSize = i6;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            egl10.eglChooseConfig(eGLDisplay, s_configAttribs2, null, 0, iArr);
            int i = iArr[0];
            if (i > 0) {
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                egl10.eglChooseConfig(eGLDisplay, s_configAttribs2, eGLConfigArr, i, iArr);
                return chooseConfig(egl10, eGLDisplay, eGLConfigArr);
            }
            throw new IllegalArgumentException("No configs match configSpec");
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int findConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int findConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (findConfigAttrib >= this.mDepthSize && findConfigAttrib2 >= this.mStencilSize) {
                    int findConfigAttrib3 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int findConfigAttrib4 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int findConfigAttrib5 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int findConfigAttrib6 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (findConfigAttrib3 == this.mRedSize && findConfigAttrib4 == this.mGreenSize && findConfigAttrib5 == this.mBlueSize && findConfigAttrib6 == this.mAlphaSize) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    public static class ContextFactory implements GLSurfaceView.EGLContextFactory {
        public static final int EGL_CONTEXT_CLIENT_VERSION = 12440;

        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            Logging.w(ViEAndroidGLES20.TAG, "creating OpenGL ES 2.0 context");
            ViEAndroidGLES20.checkEglError("Before eglCreateContext", egl10);
            EGLContext eglCreateContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            ViEAndroidGLES20.checkEglError("After eglCreateContext", egl10);
            return eglCreateContext;
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }

        public ContextFactory() {
        }

        public /* synthetic */ ContextFactory(AnonymousClass1 r1) {
        }
    }

    private native int CreateOpenGLNative(long j, int i, int i2);

    private native void DrawNative(long j);

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.surfaceCreated = true;
        this.viewWidth = i;
        this.viewHeight = i2;
        this.nativeFunctionLock.lock();
        if (this.nativeFunctionsRegisted && CreateOpenGLNative(this.nativeObject, i, i2) == 0) {
            this.openGLCreated = true;
        }
        this.nativeFunctionLock.unlock();
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
    }

    public static boolean IsSupported(Context context) {
        if (((ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY)).getDeviceConfigurationInfo().reqGlEsVersion >= 131072) {
            return true;
        }
        return false;
    }

    public static boolean UseOpenGL2(Object obj) {
        return ViEAndroidGLES20.class.isInstance(obj);
    }

    private void init(boolean z, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (z) {
            getHolder().setFormat(-3);
        }
        setEGLContextFactory(new ContextFactory());
        if (z) {
            i3 = 8;
            i4 = 8;
            i6 = 8;
            i5 = 8;
        } else {
            i3 = 5;
            i4 = 6;
            i5 = 0;
            i6 = 5;
        }
        setEGLConfigChooser(new ConfigChooser(i3, i4, i6, i5, i, i2));
        setRenderer(this);
        setRenderMode(0);
    }

    public void DeRegisterNativeObject() {
        this.nativeFunctionLock.lock();
        this.nativeFunctionsRegisted = false;
        this.openGLCreated = false;
        this.nativeObject = 0;
        this.nativeFunctionLock.unlock();
    }

    public void ReDraw() {
        if (this.surfaceCreated) {
            requestRender();
        }
    }

    public void RegisterNativeObject(long j) {
        this.nativeFunctionLock.lock();
        this.nativeObject = j;
        this.nativeFunctionsRegisted = true;
        this.nativeFunctionLock.unlock();
    }

    public void onDrawFrame(GL10 gl10) {
        this.nativeFunctionLock.lock();
        if (this.nativeFunctionsRegisted && this.surfaceCreated) {
            if (!this.openGLCreated) {
                if (CreateOpenGLNative(this.nativeObject, this.viewWidth, this.viewHeight) == 0) {
                    this.openGLCreated = true;
                } else {
                    return;
                }
            }
            DrawNative(this.nativeObject);
        }
        this.nativeFunctionLock.unlock();
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public static void checkEglError(String str, EGL10 egl10) {
        while (true) {
            int eglGetError = egl10.eglGetError();
            if (eglGetError != 12288) {
                Logging.e(TAG, String.format("%s: EGL error: 0x%x", str, Integer.valueOf(eglGetError)));
            } else {
                return;
            }
        }
    }

    public ViEAndroidGLES20(Context context) {
        super(context);
        init(false, 0, 0);
    }

    public ViEAndroidGLES20(Context context, boolean z, int i, int i2) {
        super(context);
        init(z, i, i2);
    }
}
