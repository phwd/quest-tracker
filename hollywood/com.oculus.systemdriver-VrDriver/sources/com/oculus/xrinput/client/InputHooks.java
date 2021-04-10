package com.oculus.xrinput.client;

import android.app.Activity;
import android.app.NativeActivity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.oculus.os.DialogContext;
import java.util.List;

public class InputHooks implements Window.Callback {
    private static final int INPUT_SOURCE_HEADSET = 8194;
    private static final int INPUT_SOURCE_REMOTE = 1698898178;
    private static final int META_STATE_REMOTE = 1698897920;
    public static final int OVERLAY_INPUT_ROUTE_BACK_STATE_TO_OVERLAY = 2;
    public static final int OVERLAY_INPUT_ROUTE_TO_APP = 0;
    public static final int OVERLAY_INPUT_ROUTE_TO_OVERLAY = 1;
    private static final String TAG = "InputHooksJava";
    private static final int TOUCH_STATE_DOWN = 1;
    private static final int TOUCH_STATE_NO_CHANGE = 0;
    private static final int TOUCH_STATE_UP = 2;
    private static final boolean VERBOSE = false;
    private static Activity mActivity = null;
    private static InputHooks mHooks = null;
    private static int mOverlayInputRoutingType = 0;
    private static Window.Callback mPreviousCallback = null;
    private static Window mWindow = null;
    static long touchDownTime = 0;

    public static native void nativeSetGamepadButtonDown(long j, int i);

    public static native void nativeSetGamepadButtonUp(long j, int i);

    public static native void nativeSetGamepadJoystickState(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8);

    public static native void nativeSetHomeButtonDown(int i, int i2);

    public static native void nativeSetHomeButtonUp(int i);

    public static native void nativeSetVolumeButtonDownForAbusePrevention();

    public static native void nativeSetVolumeButtonUpForAbusePrevention();

    private InputHooks(Context context) {
        if (context instanceof Activity) {
            mActivity = (Activity) context;
            mWindow = mActivity.getWindow();
        } else if (context instanceof DialogContext) {
            mWindow = ((DialogContext) context).getWindow();
        } else {
            Log.w(TAG, "Unable to enable input hooks on a non-Activity Context");
            return;
        }
        mPreviousCallback = mWindow.getCallback();
        if (mPreviousCallback == this) {
            mPreviousCallback = null;
            Log.w(TAG, "Window already has this as a callback, setting it to null for fallback.");
        }
        if (mPreviousCallback == null) {
            Log.w(TAG, "Window has no previous callback set. Falling back to directly accessing Activity");
        }
    }

    public static void SetOverlayInputRoutingType(int overlayRoutingType) {
        mOverlayInputRoutingType = overlayRoutingType;
    }

    public static boolean IsEnabled() {
        return mHooks != null;
    }

    public static boolean IsNativeActivity(Context context) {
        return NativeActivity.class.isInstance(context);
    }

    public static void EnableInputHooks(Context context) {
        Log.d(TAG, "Enabling Java Input Hooks");
        mHooks = new InputHooks(context);
        InputHooks inputHooks = mHooks;
        Window window = mWindow;
        if (window != null) {
            window.setCallback(inputHooks);
        }
    }

    public static void DisableInputHooks(Context context) {
        Window window;
        Log.d(TAG, "Disabling Java Input Hooks");
        InputHooks inputHooks = mHooks;
        if (inputHooks != null && (window = mWindow) != null) {
            if (inputHooks == window.getCallback()) {
                InputHooks inputHooks2 = mHooks;
                mWindow.setCallback(mPreviousCallback);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("DisableInputHooks: Window callback was not set to InputHooks! ");
                InputHooks inputHooks3 = mHooks;
                sb.append(mWindow.getCallback());
                Log.e(TAG, sb.toString());
            }
            mHooks = null;
        }
    }

    public static void SendHMTEmulationTouchEvent(Context context, int action, float x, float y) {
        long updateTime = SystemClock.uptimeMillis();
        if (action == 0) {
            touchDownTime = updateTime;
        }
        if (context instanceof Activity) {
            mActivity = (Activity) context;
            mWindow = mActivity.getWindow();
            if (mWindow != null) {
                MotionEvent motionEvent = MotionEvent.obtain(touchDownTime, updateTime, action, x, y, META_STATE_REMOTE);
                motionEvent.setSource(INPUT_SOURCE_HEADSET);
                mWindow.injectInputEvent(motionEvent);
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keycode = event.getKeyCode();
        int action = event.getAction();
        int source = event.getSource();
        event.getMetaState();
        event.getRepeatCount();
        if (source == INPUT_SOURCE_REMOTE) {
            return true;
        }
        long hardwareID = (long) event.getDeviceId();
        if (keycode == 25 || keycode == 24) {
            if (action == 0) {
                nativeSetVolumeButtonDownForAbusePrevention();
            } else if (action == 1) {
                nativeSetVolumeButtonUpForAbusePrevention();
            }
        }
        if (keycode == 125) {
            Log.d(TAG, "InputHooks HOME BUTTON " + action + " sending android event to native");
            if (action == 0) {
                nativeSetHomeButtonDown(source, event.getRepeatCount());
            } else if (action == 1) {
                nativeSetHomeButtonUp(source);
            }
        } else if ((source & 1025) > 0) {
            if (action == 0) {
                nativeSetGamepadButtonDown(hardwareID, keycode);
            } else if (action == 1) {
                nativeSetGamepadButtonUp(hardwareID, keycode);
            }
        }
        int i = mOverlayInputRoutingType;
        if (i == 1) {
            Log.d(TAG, "Consuming input due to overlay");
            return true;
        } else if (i == 2 && (keycode == 4 || keycode == 109 || keycode == 111)) {
            Log.d(TAG, "Consuming back button due to overlay");
            return true;
        } else {
            Window.Callback callback = mPreviousCallback;
            if (callback != null) {
                return callback.dispatchKeyEvent(event);
            }
            Activity activity = mActivity;
            if (activity != null) {
                return activity.dispatchKeyEvent(event);
            }
            return true;
        }
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.dispatchKeyShortcutEvent(event);
        }
        Activity activity = mActivity;
        if (activity != null) {
            return activity.dispatchKeyShortcutEvent(event);
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        event.getActionIndex();
        event.getSource();
        event.getMetaState();
        event.getRawX();
        event.getRawY();
        boolean up = false;
        boolean down = action == 0;
        if (action == 1) {
            up = true;
        }
        if (down) {
        }
        if (up) {
        }
        if (mOverlayInputRoutingType == 1) {
            Log.d(TAG, "Consuming touch due to overlay");
            return true;
        }
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.dispatchTouchEvent(event);
        }
        Activity activity = mActivity;
        if (activity != null) {
            return activity.dispatchTouchEvent(event);
        }
        return true;
    }

    public boolean dispatchTrackballEvent(MotionEvent event) {
        if (mOverlayInputRoutingType != 0) {
            return true;
        }
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.dispatchTrackballEvent(event);
        }
        Activity activity = mActivity;
        if (activity != null) {
            return activity.dispatchTrackballEvent(event);
        }
        return true;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        if (mOverlayInputRoutingType != 0) {
            return true;
        }
        if ((16777232 & event.getSource()) > 0) {
            nativeSetGamepadJoystickState((long) event.getDeviceId(), event.getAxisValue(0), event.getAxisValue(1), event.getAxisValue(11) + event.getAxisValue(12), event.getAxisValue(14) + event.getAxisValue(13), event.getAxisValue(23), event.getAxisValue(22), event.getAxisValue(15), event.getAxisValue(16));
        }
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.dispatchGenericMotionEvent(event);
        }
        Activity activity = mActivity;
        if (activity != null) {
            return activity.dispatchGenericMotionEvent(event);
        }
        return true;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.dispatchPopulateAccessibilityEvent(event);
        }
        return false;
    }

    public View onCreatePanelView(int featureId) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onCreatePanelView(featureId);
        }
        return null;
    }

    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onCreatePanelMenu(featureId, menu);
        }
        return false;
    }

    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onPreparePanel(featureId, view, menu);
        }
        return false;
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onMenuOpened(featureId, menu);
        }
        return false;
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onMenuItemSelected(featureId, item);
        }
        return false;
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onWindowAttributesChanged(attrs);
        }
    }

    public void onContentChanged() {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onContentChanged();
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onWindowFocusChanged(hasFocus);
        }
    }

    public void onAttachedToWindow() {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onAttachedToWindow();
        }
    }

    public void onDetachedFromWindow() {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onDetachedFromWindow();
        }
    }

    public void onPanelClosed(int featureId, Menu menu) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onPanelClosed(featureId, menu);
        }
    }

    public boolean onSearchRequested() {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onSearchRequested();
        }
        return false;
    }

    public boolean onSearchRequested(SearchEvent searchEvent) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            return callback.onSearchRequested(searchEvent);
        }
        return false;
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Window.Callback callback2 = mPreviousCallback;
        if (callback2 != null) {
            return callback2.onWindowStartingActionMode(callback);
        }
        return null;
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        Window.Callback callback2 = mPreviousCallback;
        if (callback2 != null) {
            return callback2.onWindowStartingActionMode(callback, type);
        }
        return null;
    }

    public void onActionModeStarted(ActionMode mode) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onActionModeStarted(mode);
        }
    }

    public void onActionModeFinished(ActionMode mode) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onActionModeFinished(mode);
        }
    }

    @Override // android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        Window.Callback callback = mPreviousCallback;
        if (callback != null) {
            callback.onProvideKeyboardShortcuts(data, menu, deviceId);
        }
    }
}
