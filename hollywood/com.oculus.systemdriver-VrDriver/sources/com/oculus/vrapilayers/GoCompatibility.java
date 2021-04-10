package com.oculus.vrapilayers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GoCompatibility {
    private static final int DISABLE_ANDROID_INPUT_EVENTS = 32;
    private static final int DISABLE_EXTRA_LATENCY = 64;
    private static final int DISABLE_SUBMITFRAME_MODIFICATION = 256;
    private static final int FORCE_HMD_3DOF = 512;
    private static final int INPUT_SOURCE_HEADSET = 8194;
    private static final int JOYSTICK_AS_SIDECLICK = 1;
    private static final int JOYSTICK_AS_SLOW_SIPE = 8;
    private static final int JOYSTICK_AS_SWIPE = 4;
    private static final int JOYSTICK_AS_TOUCHPAD = 2;
    private static final int META_STATE_REMOTE = 1698897920;
    private static final int REPORT_TRUE_FOV = 128;
    private static final String TAG = "GoCompatibility";
    private static final int UPDATE_CONTROLLER_HANDEDNESS = 16;
    static long downTime = 0;

    private static native void nativeStartLayer(long j, long j2, ClassLoader classLoader);

    public static void injectTouch(Activity activity, boolean down) {
        if (down) {
            downTime = SystemClock.uptimeMillis();
        }
        MotionEvent motionEvent = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), !down ? 1 : 0, 160.0f, 160.0f, META_STATE_REMOTE);
        motionEvent.setSource(INPUT_SOURCE_HEADSET);
        try {
            activity.getWindow().injectInputEvent(motionEvent);
        } catch (Exception ex) {
            Log.e(TAG, "injectTouch exception: " + ex);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public static void injectBack(Activity activity, boolean down) {
        if (down != 0) {
            downTime = SystemClock.uptimeMillis();
        }
        KeyEvent keyEvent = new KeyEvent(downTime, SystemClock.uptimeMillis(), !down ? 1 : 0, JOYSTICK_AS_SWIPE, 0, -707860259);
        keyEvent.setSource(1698898178);
        Log.d(TAG, "Injecting back " + down);
        try {
            activity.getWindow().injectInputEvent(keyEvent);
        } catch (Exception ex) {
            Log.e(TAG, "injectBack exception: " + ex);
        }
        KeyEvent keyEvent2 = new KeyEvent(downTime, SystemClock.uptimeMillis(), !down, 111, 0, -707860259);
        keyEvent2.setSource(1698898178);
        Log.d(TAG, "Injecting escape " + down);
        try {
            activity.getWindow().injectInputEvent(keyEvent2);
        } catch (Exception ex2) {
            Log.e(TAG, "injectEscape exception: " + ex2);
        }
    }

    public static boolean hasHeadtrackingFeature(Context context) {
        if (!(context instanceof Activity)) {
            return true;
        }
        String name = context.getPackageName();
        Log.d(TAG, "PackageName: " + name);
        if (name.length() == 0) {
            return true;
        }
        try {
            FeatureInfo[] f = context.getPackageManager().getPackageInfo(name, 16384).reqFeatures;
            if (f == null) {
                return true;
            }
            for (int i = 0; i < f.length; i++) {
                if (f[i].name != null) {
                    if (f[i].name.equals("android.hardware.vr.headtracking")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "hasHeadtrackingFeature() threw ", e);
        }
    }

    public static int getGoEmulationManifestFeatures(Context context) {
        int returnValue = 0;
        if (!(context instanceof Activity)) {
            return 0;
        }
        String name = context.getPackageName();
        if (name.length() <= 0) {
            return 0;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(name, REPORT_TRUE_FOV).metaData;
            if (bundle == null) {
                return 0;
            }
            String joystickString = bundle.getString("oculus.go_emulation.joystick");
            boolean handednessBool = bundle.getBoolean("oculus.go_emulation.update_controller_handedness", false);
            boolean androidInputBool = bundle.getBoolean("oculus.go_emulation.disable_android_input_events", false);
            boolean latencyBool = bundle.getBoolean("oculus.go_emulation.disable_extra_latency", false);
            boolean fovBool = bundle.getBoolean("oculus.go_emulation.report_true_fov", false);
            boolean submitframeBool = bundle.getBoolean("oculus.go_emulation.disable_submitframe_modification", false);
            boolean hmd3dofBool = bundle.getBoolean("oculus.go_emulation.force_hmd_3dof", false);
            if (joystickString != null) {
                if (joystickString.equals("sideclick")) {
                    returnValue = 0 | 1;
                } else if (joystickString.equals("touchpad")) {
                    returnValue = 0 | 2;
                } else if (joystickString.equals("swipe")) {
                    returnValue = 0 | JOYSTICK_AS_SWIPE;
                } else if (joystickString.equals("slowswipe")) {
                    returnValue = 0 | JOYSTICK_AS_SLOW_SIPE;
                }
            }
            if (handednessBool) {
                returnValue |= UPDATE_CONTROLLER_HANDEDNESS;
            }
            if (androidInputBool) {
                returnValue |= DISABLE_ANDROID_INPUT_EVENTS;
            }
            if (latencyBool) {
                returnValue |= DISABLE_EXTRA_LATENCY;
            }
            if (fovBool) {
                returnValue |= REPORT_TRUE_FOV;
            }
            if (submitframeBool) {
                returnValue |= DISABLE_SUBMITFRAME_MODIFICATION;
            }
            if (hmd3dofBool) {
                return returnValue | FORCE_HMD_3DOF;
            }
            return returnValue;
        } catch (Exception e) {
            Log.e(TAG, "getGoEmulationManifestFeatures() threw ", e);
            return 0;
        }
    }

    public static void StartLayer(long vrapi_fnptr, long vrapi_priv_fnptr, Context appContext) {
        Log.d(TAG, "GoCompatibility StartLayer: Loading libgocompatibility.so");
        System.loadLibrary("gocompatibility");
        nativeStartLayer(vrapi_fnptr, vrapi_priv_fnptr, GoCompatibility.class.getClassLoader());
    }
}
