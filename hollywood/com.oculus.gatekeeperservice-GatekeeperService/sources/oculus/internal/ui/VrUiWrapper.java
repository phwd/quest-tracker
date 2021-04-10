package oculus.internal.ui;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import com.oculus.os.DialogContext;
import oculus.internal.ui.VrBase;
import oculus.internal.ui.VrReactDialog;

public class VrUiWrapper implements VrUiInterface {
    public static final int DEFAULT_ANIMATION_INTERVAL = 1;
    private static final String TAG = "VrUiWrapper";
    private VrBase mBase;
    protected boolean mDebug;
    private boolean mIsVrEnabled;
    private NativeKeyEventListener mListener;

    static class BaseNativeKeyEventListener implements NativeKeyEventListener {
        VrUiInterface mInterface;

        BaseNativeKeyEventListener() {
        }

        @Override // oculus.internal.ui.NativeKeyEventListener
        public boolean onNativeKeyEvent(int keyAction, int keyCode, int repeatCount) {
            if (!(keyCode == 96 || keyCode == 24 || keyCode == 25) || repeatCount != 0) {
                return false;
            }
            this.mInterface.onClickAction(keyAction);
            return true;
        }
    }

    public VrUiWrapper() {
        this(null, false);
    }

    public VrUiWrapper(boolean enableHomeButton) {
        this(null, enableHomeButton);
    }

    public VrUiWrapper(NativeKeyEventListener listener) {
        this(listener, false);
    }

    private VrUiWrapper(NativeKeyEventListener listener, boolean enableHomeButton) {
        if (listener == null) {
            listener = new BaseNativeKeyEventListener();
            ((BaseNativeKeyEventListener) listener).mInterface = this;
        }
        this.mBase = new VrBase(listener, enableHomeButton);
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void onCreate(Context context, Window window) {
        onCreate(context, window, 1);
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void recreate(Context context, Window window) {
        recreate(context, window, 1);
    }

    private void commonInit(Context context, Window window, int animationFrameInterval, boolean firstTime) {
        VrBase vrBase = this.mBase;
        this.mIsVrEnabled = VrBase.isVrEnabled();
        Display display = window.getWindowManager().getDefaultDisplay();
        if (display.getDisplayId() != 0) {
            Log.d(TAG, "Started on non-primary display " + display.getDisplayId() + ", running in 2D mode.");
            this.mIsVrEnabled = false;
            return;
        }
        Context dialogContext = new DialogContext(context, window);
        if (!this.mIsVrEnabled) {
            return;
        }
        if (firstTime) {
            this.mBase.onCreate(dialogContext, window, window.getDecorView(), animationFrameInterval);
        } else {
            this.mBase.recreate(dialogContext, window, window.getDecorView(), animationFrameInterval);
        }
    }

    private void onCreate(Context context, Window window, int animationFrameInterval) {
        commonInit(context, window, animationFrameInterval, true);
    }

    private void recreate(Context context, Window window, int animationFrameInterval) {
        commonInit(context, window, animationFrameInterval, false);
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void onPause() {
        if (this.mIsVrEnabled) {
            try {
                this.mBase.onPause();
            } catch (VrBase.LifecycleException e) {
                if (this.mDebug) {
                    Log.d(TAG, "Exception occurred during onPause(): " + e.getMessage());
                }
            }
        }
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void onResume() {
        if (this.mIsVrEnabled) {
            try {
                this.mBase.onResume();
            } catch (VrBase.LifecycleException e) {
                if (this.mDebug) {
                    Log.d(TAG, "Exception occurred during onResume(): " + e.getMessage());
                }
            }
        }
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void onWindowFocusChanged(boolean hasFocus) {
        if (this.mIsVrEnabled) {
            try {
                this.mBase.onWindowFocusChanged(hasFocus);
            } catch (VrBase.LifecycleException e) {
                if (this.mDebug) {
                    Log.d(TAG, "Exception occurred during onWindowFocusChanged(): " + e.getMessage());
                }
            }
        }
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void onDestroy() {
        if (this.mIsVrEnabled) {
            this.mBase.onDestroy();
        }
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void onClickAction(int keyAction) {
        if (this.mIsVrEnabled) {
            this.mBase.onClickAction(keyAction);
        }
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void useReactLibrary(ReactDialogEventHandler reactDialogEventHandler) {
        this.mBase.useReactLibrary(reactDialogEventHandler);
    }

    @Override // oculus.internal.ui.VrUiInterface
    public void setConfiguration(VrReactDialog.DialogConfiguration dialogConfiguration) {
        this.mBase.setReactConfiguration(dialogConfiguration.toJSONParcel());
    }
}
