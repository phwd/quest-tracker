package oculus.internal.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.Window;

/* access modifiers changed from: package-private */
public class VrLibJNI {
    private static final boolean DEBUG = false;
    private static final String TAG = "VrLibJNI";
    private Rect mBounds;
    private Object mBoundsMonitor = new Object();
    private final Context mContext;
    private final Runnable mControllerRecenterRunnable;
    private final NativeKeyEventListener mKeyEventListener;
    private final ReactDialogEventHandler mReactDialogEventHandler;
    private final View mRootView;
    private Surface mSurface;
    private Object mSurfaceMonitor = new Object();
    private final Window mWindow;

    private static native void nativeSetBounds(long j, int i, int i2, int i3, int i4);

    public VrLibJNI(Window window, View rootView, NativeKeyEventListener keyEventListener, Runnable controllerRecenterRunnable, ReactDialogEventHandler reactDialogEventHandler) {
        this.mRootView = rootView;
        this.mWindow = window;
        this.mContext = window.getContext();
        this.mKeyEventListener = keyEventListener;
        this.mControllerRecenterRunnable = controllerRecenterRunnable;
        this.mReactDialogEventHandler = reactDialogEventHandler;
    }

    /* access modifiers changed from: package-private */
    public void updateBounds() {
        synchronized (this.mBoundsMonitor) {
            this.mBounds = RenderView.getBoundsForView(this.mRootView, this.mContext);
        }
    }

    public void loadBounds(long handle) {
        boolean isBoundsNull;
        synchronized (this.mBoundsMonitor) {
            isBoundsNull = this.mBounds == null ? true : DEBUG;
        }
        if (isBoundsNull) {
            updateBounds();
        }
        synchronized (this.mBoundsMonitor) {
            nativeSetBounds(handle, this.mBounds.left, this.mBounds.top, this.mBounds.right, this.mBounds.bottom);
        }
    }

    public int bindViewToSurface(final int left, final int top, final int right, final int bottom, boolean needsNewTexture) {
        Handler handler;
        View view = this.mRootView;
        if (view == null) {
            return 0;
        }
        if ((!view.isDirty() && !needsNewTexture) || (handler = this.mRootView.getHandler()) == null) {
            return 0;
        }
        handler.postAtFrontOfQueue(new Runnable() {
            /* class oculus.internal.ui.VrLibJNI.AnonymousClass1 */

            public void run() {
                VrLibJNI.this.updateSurface(left, top, right, bottom);
            }
        });
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateSurface(int left, int top, int right, int bottom) {
        synchronized (this.mSurfaceMonitor) {
            if (this.mSurface != null) {
                Canvas canvas = this.mSurface.lockCanvas(null);
                this.mRootView.measure(View.MeasureSpec.makeMeasureSpec(right - left, 1073741824), View.MeasureSpec.makeMeasureSpec(bottom - top, 1073741824));
                this.mRootView.layout(left, top, right, bottom);
                updateBounds();
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                this.mRootView.draw(canvas);
                this.mSurface.unlockCanvasAndPost(canvas);
            }
        }
    }

    public boolean handleNativeKeyEvent(int keyAction, int keyCode, int repeatCount) {
        return this.mKeyEventListener.onNativeKeyEvent(keyAction, keyCode, repeatCount);
    }

    public void sendMotionEventToWindow(final float xInWindowCoords, final float yInWindowCoords, final int action) {
        this.mRootView.post(new Runnable() {
            /* class oculus.internal.ui.VrLibJNI.AnonymousClass2 */

            public void run() {
                VrLibJNI.this.mWindow.superDispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), action, xInWindowCoords, yInWindowCoords, 0));
            }
        });
    }

    public void setSurface(Surface surface) {
        synchronized (this.mSurfaceMonitor) {
            this.mSurface = surface;
        }
    }

    public void sendControllerRecenterToWindow() {
        Log.d(TAG, "Sent controller recenter event to window");
        this.mRootView.post(this.mControllerRecenterRunnable);
    }

    public void onPrimaryButtonClicked() {
        ReactDialogEventHandler reactDialogEventHandler = this.mReactDialogEventHandler;
        if (reactDialogEventHandler != null) {
            reactDialogEventHandler.onPrimaryButtonClicked();
        }
    }

    public void onSecondaryButtonClicked() {
        ReactDialogEventHandler reactDialogEventHandler = this.mReactDialogEventHandler;
        if (reactDialogEventHandler != null) {
            reactDialogEventHandler.onSecondaryButtonClicked();
        }
    }

    public void onTertiaryButtonClicked() {
        ReactDialogEventHandler reactDialogEventHandler = this.mReactDialogEventHandler;
        if (reactDialogEventHandler != null) {
            reactDialogEventHandler.onTertiaryButtonClicked();
        }
    }

    public void onIconButtonClicked() {
        ReactDialogEventHandler reactDialogEventHandler = this.mReactDialogEventHandler;
        if (reactDialogEventHandler != null) {
            reactDialogEventHandler.onIconButtonClicked();
        }
    }
}
