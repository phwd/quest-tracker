package com.oculus.vrapi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Surface;
import java.util.Arrays;

class ScreenCaptureReceiver extends BroadcastReceiver {
    private static final String BEGIN_VIDEO_CAPTURE_ACTION = "com.oculus.systemactivities.BEGIN_VIDEO_CAPTURE";
    private static final String BEGIN_VIDEO_CAPTURE_WITH_SURFACE_ACTION = "com.oculus.systemactivities.BEGIN_VIDEO_CAPTURE_WITH_SURFACE";
    private static final float DEFAULT_SCREENSHOT_DELAY = 5.0f;
    private static final String IS_SCREEN_CAPTURE_RUNNING = "com.oculus.systemactivities.IS_SCREEN_CAPTURE_RUNNING";
    private static final String PAUSE_VIDEO_CAPTURE_ACTION = "com.oculus.systemactivities.PAUSE_VIDEO_CAPTURE";
    private static final String RESUME_VIDEO_CAPTURE_ACTION = "com.oculus.systemactivities.RESUME_VIDEO_CAPTURE";
    private static final String SCREENSHOT_ACTION = "com.oculus.systemactivities.SCREENSHOT";
    private static final String STOP_VIDEO_CAPTURE_ACTION = "com.oculus.systemactivities.STOP_VIDEO_CAPTURE";
    private static final String TAG = "ScreenCaptureReceiver";
    private static Context cachedContext = null;
    private static float delayInSeconds = DEFAULT_SCREENSHOT_DELAY;
    private static IntentFilter filter = null;
    private static boolean isReceiverStarted = false;
    private static ScreenCaptureReceiver receiver = null;
    private static Surface screenshotSurface = null;
    private static boolean shouldLiftInhibit = false;
    private static boolean showCaptureIndicator = true;
    private static Surface streamingSurface = null;
    private static int unauthorizedAccessCount = 0;

    /* access modifiers changed from: private */
    public enum ScreenCaptureCmd {
        None,
        Start,
        Stop,
        Photo,
        Cancel
    }

    private static native boolean nativeIsVideoCaptureRunning();

    private static native void nativeLiftInhibitState();

    private static native void nativeRestoreInhibitState();

    public static native void nativeSetPhotoParams(Surface surface, float f, boolean z);

    private static native void nativeSetScreenCaptureCmd(int i);

    private static native void nativeSetStreamingParams(Surface surface, boolean z);

    public static native void nativeSetUnauthorizedAccessCount(int i);

    ScreenCaptureReceiver() {
    }

    public static void advertiseScreenCaptureRunning(boolean isCapturing) {
        if (cachedContext != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.vrshell.home", "com.oculus.vrshell.home.SystemUtilitiesService"));
            intent.putExtra("screen_capture_running", isCapturing);
            cachedContext.startService(intent);
            Log.i(TAG, "Advertising screen capture running state = " + isCapturing);
        }
    }

    public static Surface getStreamingCaptureSurface() {
        return streamingSurface;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrapi.ScreenCaptureReceiver$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd = new int[ScreenCaptureCmd.values().length];

        static {
            try {
                $SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd[ScreenCaptureCmd.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd[ScreenCaptureCmd.Start.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd[ScreenCaptureCmd.Stop.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd[ScreenCaptureCmd.Photo.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd[ScreenCaptureCmd.Cancel.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static int getScreenCaptureCmdNum(ScreenCaptureCmd captureCmd) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrapi$ScreenCaptureReceiver$ScreenCaptureCmd[captureCmd.ordinal()];
        if (i == 1) {
            return -1;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i != 4) {
            return i != 5 ? -1 : 4;
        }
        return 3;
    }

    private static void setScreenCaptureCmd(ScreenCaptureCmd newCaptureCmd) {
        nativeSetScreenCaptureCmd(getScreenCaptureCmdNum(newCaptureCmd));
    }

    public static void startReceiver(Context context) {
        if (!isReceiverStarted) {
            cachedContext = context;
            unauthorizedAccessCount = 0;
            TrustedSignatureVerifier.GenerateSignatureMap();
            Log.d(TAG, "startReceiver " + cachedContext.getApplicationContext().getPackageName());
            if (filter == null) {
                filter = new IntentFilter(BEGIN_VIDEO_CAPTURE_ACTION);
                filter.addAction(SCREENSHOT_ACTION);
                filter.addAction(BEGIN_VIDEO_CAPTURE_WITH_SURFACE_ACTION);
                filter.addAction(PAUSE_VIDEO_CAPTURE_ACTION);
                filter.addAction(RESUME_VIDEO_CAPTURE_ACTION);
                filter.addAction(STOP_VIDEO_CAPTURE_ACTION);
                filter.addAction(IS_SCREEN_CAPTURE_RUNNING);
            }
            if (receiver == null) {
                receiver = new ScreenCaptureReceiver();
            }
            ContextHelper.registerReceiverAsAllUsers(context, receiver, filter, null);
            isReceiverStarted = true;
        }
    }

    public static void stopReceiver() {
        if (isReceiverStarted) {
            Log.d(TAG, "stopReceiver " + cachedContext.getApplicationContext().getPackageName());
            setScreenCaptureCmd(ScreenCaptureCmd.Stop);
            cachedContext.unregisterReceiver(receiver);
            cachedContext = null;
            isReceiverStarted = false;
        }
    }

    public static boolean isReceiverStarted() {
        return isReceiverStarted;
    }

    private boolean isTrustedApp(Context context, String callerPackageName) {
        if (Arrays.asList("com.oculus.AugmentedVR", "com.oculus.screencapture", "com.oculus.bullseye").contains(callerPackageName)) {
            return isUserDevBuild();
        }
        if (!Arrays.asList(TrustedSignatureVerifier.TrustedSignedApps).contains(callerPackageName)) {
            return false;
        }
        if (TrustedSignatureVerifier.isSignatureValid(context, callerPackageName)) {
            return true;
        }
        unauthorizedAccessCount++;
        nativeSetUnauthorizedAccessCount(unauthorizedAccessCount);
        return false;
    }

    private static boolean isUserDevBuild() {
        Log.d(TAG, "Checking build type");
        String propValue = SystemProps.getString("ro.build.fingerprint", null);
        if (propValue == null) {
            return false;
        }
        return !propValue.endsWith("release-keys");
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive " + intent.getAction() + " package=" + context.getApplicationContext().getPackageName());
        if (intent.getPackage() == null) {
            Log.e(TAG, "ScreenCaptureReceiver - intent missing package!");
        } else if (intent.hasExtra("_ci_")) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("_ci_");
            if (pendingIntent == null) {
                Log.w(TAG, "PendingIntent is null");
                return;
            }
            String callerPackageName = pendingIntent.getCreatorPackage();
            if (!isTrustedApp(context, callerPackageName)) {
                Log.w(TAG, "Intent sent from untrustworthy app - " + callerPackageName);
                return;
            }
            Log.d(TAG, "App is trusted");
            if (intent.getAction().equals(SCREENSHOT_ACTION)) {
                delayInSeconds = (float) intent.getDoubleExtra("delayInSeconds", 5.0d);
                float f = delayInSeconds;
                if (((double) f) < 0.0d || ((double) f) > 5.0d) {
                    Log.w(TAG, "Screenshot delay is out of bounds. Setting to default value: 5.0 seconds");
                    delayInSeconds = DEFAULT_SCREENSHOT_DELAY;
                }
                Surface surf = (Surface) intent.getParcelableExtra("surface");
                if (surf != null) {
                    screenshotSurface = surf;
                }
                showCaptureIndicator = intent.getBooleanExtra("show_capture_indicator", true);
                Surface surface = screenshotSurface;
                if (surface != null) {
                    nativeSetPhotoParams(surface, delayInSeconds, showCaptureIndicator);
                    setScreenCaptureCmd(ScreenCaptureCmd.Photo);
                    return;
                }
                Log.w(TAG, "Null surface sent for screenshot.");
            } else if (intent.getAction().equals(BEGIN_VIDEO_CAPTURE_WITH_SURFACE_ACTION)) {
                shouldLiftInhibit = intent.getBooleanExtra("lift_inhibit", false);
                Surface surf2 = (Surface) intent.getParcelableExtra("surface");
                if (!(1 == 0 || !shouldLiftInhibit || surf2 == null)) {
                    Log.d(TAG, "Inhibit lifted");
                    nativeLiftInhibitState();
                }
                streamingSurface = surf2;
                showCaptureIndicator = intent.getBooleanExtra("show_capture_indicator", true);
                nativeSetStreamingParams(streamingSurface, showCaptureIndicator);
                if (surf2 != null) {
                    Log.d(TAG, "Valid surface. Starting streaming");
                    setScreenCaptureCmd(ScreenCaptureCmd.Start);
                    return;
                }
                Log.d(TAG, "Null surface. Stopping streaming");
                setScreenCaptureCmd(ScreenCaptureCmd.Stop);
                Log.d(TAG, "Inhibit restored");
                nativeRestoreInhibitState();
            } else if (intent.getAction().equals(RESUME_VIDEO_CAPTURE_ACTION)) {
                if (streamingSurface != null) {
                    setScreenCaptureCmd(ScreenCaptureCmd.Start);
                }
            } else if (intent.getAction().equals(PAUSE_VIDEO_CAPTURE_ACTION)) {
                if (streamingSurface != null) {
                    setScreenCaptureCmd(ScreenCaptureCmd.Stop);
                }
            } else if (intent.getAction().equals(STOP_VIDEO_CAPTURE_ACTION)) {
                setScreenCaptureCmd(ScreenCaptureCmd.Stop);
                Log.d(TAG, "Inhibit restored");
                nativeRestoreInhibitState();
            } else if (intent.getAction().equals(IS_SCREEN_CAPTURE_RUNNING)) {
                advertiseScreenCaptureRunning(nativeIsVideoCaptureRunning());
            } else {
                Log.e(TAG, "Unknown Action " + intent.getAction());
            }
        } else {
            Log.w(TAG, "No PendingIntent attached");
        }
    }
}
