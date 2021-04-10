package oculus.internal.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.InputQueue;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import junit.framework.Assert;

public class VrBase {
    private static final boolean DEBUG = false;
    private static final double DISPLAY_SCALE = getDensityScale(DISPLAY_SCALE_DEFAULT);
    private static final double DISPLAY_SCALE_DEFAULT = 0.42d;
    private static final String TAG = "VrBase";
    private static Runnable mOnControllerRecenter = null;
    private static final boolean sIsNativeVrLibAvailable;
    private static DisplayMetrics sOriginalDisplayMetrics;
    private String mCachedReactConfig;
    private boolean mEnableControllerHomeButton;
    private NativeKeyEventListener mNativeKeyEventListener;
    private ReactDialogEventHandler mReactDialogEventHandler;
    private boolean mUseReactLibrary;
    private long mVrAppStateHandle;
    private VrInputManager mVrInputManager;
    private VrLibJNI mVrLibJNI;

    private static native void nativeOnClickDown(long j);

    private static native void nativeOnClickUp(long j);

    private static native long nativeOnCreate(String str, Context context, VrLibJNI vrLibJNI, int i, boolean z, boolean z2);

    private static native void nativeOnDestroy(long j);

    /* access modifiers changed from: private */
    public static native void nativeOnInputQueueCreated(long j, long j2);

    /* access modifiers changed from: private */
    public static native void nativeOnInputQueueDestroyed(long j, long j2);

    private static native int nativeOnPause(long j);

    private static native int nativeOnResume(long j);

    /* access modifiers changed from: private */
    public static native void nativeOnSurfaceChanged(long j, Surface surface);

    /* access modifiers changed from: private */
    public static native void nativeOnSurfaceCreated(long j, Surface surface);

    /* access modifiers changed from: private */
    public static native void nativeOnSurfaceDestroyed(long j);

    private static native int nativeOnWindowFocusChanged(long j, boolean z);

    private static native void nativeSetReactConfiguration(long j, String str);

    private static native void nativeUpdateMessageDialog(long j);

    static {
        boolean isNativeVrLibAvailable = DEBUG;
        try {
            onLoad();
            isNativeVrLibAvailable = true;
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "Could not find native library: using non-VR UI", e);
        }
        sIsNativeVrLibAvailable = isNativeVrLibAvailable;
    }

    public class LifecycleException extends Exception {
        public LifecycleException(String message) {
            super(message);
        }
    }

    protected static void onLoad() throws UnsatisfiedLinkError {
        System.loadLibrary("vrui_jni");
    }

    public static boolean isVrEnabled() {
        return sIsNativeVrLibAvailable;
    }

    public static void setRecenterCallback(Runnable recenterRunnable) {
        mOnControllerRecenter = recenterRunnable;
    }

    public VrBase(NativeKeyEventListener nativeKeyEventListener) {
        this(nativeKeyEventListener, DEBUG);
    }

    public VrBase(NativeKeyEventListener nativeKeyEventListener, boolean enableControllerHomeButton) {
        this.mVrAppStateHandle = 0;
        this.mUseReactLibrary = DEBUG;
        this.mCachedReactConfig = null;
        this.mReactDialogEventHandler = null;
        this.mEnableControllerHomeButton = DEBUG;
        this.mNativeKeyEventListener = nativeKeyEventListener;
        this.mEnableControllerHomeButton = enableControllerHomeButton;
    }

    public void onCreate(Context context, Window window, View view) throws Exception {
        onCreate(context, window, view, 0);
    }

    public void onCreate(Context context, Window window, View view, int animationFrameInterval) {
        scaleDensity(context, DISPLAY_SCALE);
        if (!sIsNativeVrLibAvailable) {
            throw new NoClassDefFoundError("Attempted to call VR onCreate() without a valid VR API implementation on the system");
        } else if (context instanceof Activity) {
            throw new ClassCastException("Do not pass activity object as context to onCreate(): Use Application::getApplicationContext()");
        } else if (this.mVrAppStateHandle == 0) {
            this.mVrInputManager = new VrInputManager();
            window.takeInputQueue(this.mVrInputManager);
            window.takeSurface(new VrSurfaceManager());
            view.setWillNotDraw(true);
            view.setWillNotCacheDrawing(true);
            view.setLayerType(1, new Paint(1223));
            window.addFlags(1024);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = -1;
            params.height = -1;
            params.windowAnimations = 1057161216;
            params.privateFlags |= 33554432;
            params.screenBrightness = 1.0f;
            window.setAttributes(params);
            recreateInternal(true, context, window, view, animationFrameInterval);
            String str = this.mCachedReactConfig;
            if (str != null) {
                setReactConfiguration(str);
                this.mCachedReactConfig = null;
            }
        } else {
            throw new IllegalStateException("Duplicate calls to onCreate()");
        }
    }

    public void recreate(Context context, Window window, View view, int animationFrameInterval) {
        recreateInternal(DEBUG, context, window, view, animationFrameInterval);
    }

    private void recreateInternal(boolean firstTime, Context context, Window window, View view, int animationFrameInterval) {
        this.mVrLibJNI = new VrLibJNI(window, view, this.mNativeKeyEventListener, mOnControllerRecenter, this.mReactDialogEventHandler);
        this.mVrAppStateHandle = nativeOnCreate(context.getPackageName(), context, this.mVrLibJNI, animationFrameInterval, this.mUseReactLibrary, this.mEnableControllerHomeButton);
        if (this.mVrAppStateHandle == 0) {
            throw new RuntimeException("failed to initialize native app state");
        } else if (!firstTime) {
            this.mVrInputManager.restart();
        }
    }

    public void onResume() throws LifecycleException {
        Assert.assertTrue(sIsNativeVrLibAvailable);
        long j = this.mVrAppStateHandle;
        if (j != 0 && nativeOnResume(j) < 0) {
            throw new LifecycleException("Failed to complete onResume()");
        }
    }

    public void onPause() throws LifecycleException {
        Assert.assertTrue(sIsNativeVrLibAvailable);
        long j = this.mVrAppStateHandle;
        if (j != 0 && nativeOnPause(j) < 0) {
            throw new LifecycleException("Failed to complete onResume()");
        }
    }

    public boolean onNativeKeyEvent(int keyEvent, int keyCode, int repeatCount) {
        return DEBUG;
    }

    public void onWindowFocusChanged(boolean hasFocus) throws LifecycleException {
        Assert.assertTrue(sIsNativeVrLibAvailable);
        long j = this.mVrAppStateHandle;
        if (j != 0 && nativeOnWindowFocusChanged(j, hasFocus) < 0) {
            throw new LifecycleException("Failed to complete onWindowFocusChanged()");
        }
    }

    public void onDestroy() {
        Assert.assertTrue(sIsNativeVrLibAvailable);
        long j = this.mVrAppStateHandle;
        if (j != 0) {
            nativeOnDestroy(j);
            this.mVrAppStateHandle = 0;
        }
    }

    public void onClickAction(int keyAction) {
        long j = this.mVrAppStateHandle;
        if (j == 0) {
            return;
        }
        if (keyAction == 1) {
            nativeOnClickUp(j);
        } else if (keyAction == 0) {
            nativeOnClickDown(j);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void requestRedraw() {
        if (this.mVrAppStateHandle != 0) {
            this.mVrLibJNI.updateBounds();
            nativeUpdateMessageDialog(this.mVrAppStateHandle);
        }
    }

    public void useReactLibrary(ReactDialogEventHandler reactDialogEventHandler) {
        this.mUseReactLibrary = true;
        this.mReactDialogEventHandler = reactDialogEventHandler;
    }

    public void setReactConfiguration(String configJSONParcel) {
        long j = this.mVrAppStateHandle;
        if (j != 0) {
            nativeSetReactConfiguration(j, configJSONParcel);
        } else {
            this.mCachedReactConfig = configJSONParcel;
        }
    }

    private static double getDensityScale(double defaultScale) {
        try {
            return Double.parseDouble(SystemProperties.get("ro.sf.lcd_density_scale", ""));
        } catch (NumberFormatException e) {
            return defaultScale;
        }
    }

    public static void scaleDensity(Context context) {
        scaleDensity(context, DISPLAY_SCALE);
    }

    private static void scaleDensity(Context context, double scale) {
        if (sOriginalDisplayMetrics == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            sOriginalDisplayMetrics = new DisplayMetrics();
            sOriginalDisplayMetrics.setTo(displayMetrics);
        }
        overrideDisplayDensity(context, (int) (((double) sOriginalDisplayMetrics.widthPixels) * scale), (int) (((double) sOriginalDisplayMetrics.heightPixels) * scale));
    }

    private static void overrideDisplayDensity(Context context, int widthPixels, int heightPixels) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Configuration config = context.getResources().getConfiguration();
        metrics.widthPixels = widthPixels;
        metrics.heightPixels = heightPixels;
        metrics.noncompatWidthPixels = widthPixels;
        metrics.noncompatHeightPixels = heightPixels;
        context.getResources().updateConfiguration(config, metrics);
    }

    /* access modifiers changed from: private */
    public class VrSurfaceManager implements SurfaceHolder.Callback2 {
        public VrSurfaceManager() {
        }

        public void surfaceCreated(SurfaceHolder holder) {
            Assert.assertTrue(VrBase.sIsNativeVrLibAvailable);
            if (VrBase.this.mVrAppStateHandle != 0) {
                VrBase.nativeOnSurfaceCreated(VrBase.this.mVrAppStateHandle, holder.getSurface());
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Assert.assertTrue(VrBase.sIsNativeVrLibAvailable);
            if (VrBase.this.mVrAppStateHandle != 0) {
                VrBase.nativeOnSurfaceChanged(VrBase.this.mVrAppStateHandle, holder.getSurface());
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Assert.assertTrue(VrBase.sIsNativeVrLibAvailable);
            if (VrBase.this.mVrAppStateHandle != 0) {
                VrBase.nativeOnSurfaceDestroyed(VrBase.this.mVrAppStateHandle);
            }
        }

        public void surfaceRedrawNeeded(SurfaceHolder holder) {
            Assert.assertTrue(VrBase.sIsNativeVrLibAvailable);
            VrBase.this.requestRedraw();
        }
    }

    /* access modifiers changed from: private */
    public class VrInputManager implements InputQueue.Callback {
        InputQueue mQueue;

        private VrInputManager() {
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void restart() {
            InputQueue inputQueue = this.mQueue;
            if (inputQueue == null) {
                Log.w(VrBase.TAG, "Cannot restart input queue, cached queue was not initialized or already destroyed");
            } else {
                onInputQueueCreated(inputQueue);
            }
        }

        public void onInputQueueCreated(InputQueue queue) {
            this.mQueue = queue;
            Assert.assertTrue(VrBase.sIsNativeVrLibAvailable);
            if (VrBase.this.mVrAppStateHandle != 0) {
                VrBase.nativeOnInputQueueCreated(VrBase.this.mVrAppStateHandle, queue.getNativePtr());
            }
        }

        public void onInputQueueDestroyed(InputQueue queue) {
            this.mQueue = null;
            Assert.assertTrue(VrBase.sIsNativeVrLibAvailable);
            if (VrBase.this.mVrAppStateHandle != 0) {
                VrBase.nativeOnInputQueueDestroyed(VrBase.this.mVrAppStateHandle, queue.getNativePtr());
            }
        }
    }
}
