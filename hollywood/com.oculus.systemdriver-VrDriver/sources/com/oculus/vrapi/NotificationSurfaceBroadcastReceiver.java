package com.oculus.vrapi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.util.Log;
import android.view.Surface;
import com.oculus.platform.OVRServiceSynchronous;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;

class NotificationSurfaceBroadcastReceiver extends BroadcastReceiver implements SurfaceTexture.OnFrameAvailableListener {
    private static String BACK_INPUT_BLOCK = "back_input_blocking";
    private static final String BLOCK_INPUT_FOR_OVERLAY_ACTION = "com.oculus.vrdriver.block_input_for_overlay";
    private static String BUTTON_INPUT_BLOCKING_TYPE = "button_input_blocking";
    private static final int DEFAULT_LAYER_FLAGS = 2;
    private static final String DELETE_NOTIFICATION_ACTION = "com.oculus.vrdriver.teardown_notification";
    private static final String DISTANCE = "distance";
    private static final String DURATION_MILLISECONDS = "duration_ms";
    private static String INPUT_BLOCKING_TYPE_KEY = "input_blocking_type";
    private static final String LAYER_FLAGS = "layer_flags";
    private static final String LEFT_MATRIX = "left_matrix";
    private static final String LEFT_RECT = "left_rect";
    private static final String NEW_NOTIFICATION_ACTION = "com.oculus.vrdriver.new_notification_to_show";
    private static String NO_INPUT_BLOCKING_TYPE = "no_input_blocking";
    private static final String PITCH = "pitch";
    private static final String POSITION = "position";
    private static final String POSITION_USER_YAW = "user_yaw";
    private static final String RIGHT_MATRIX = "right_matrix";
    private static final String RIGHT_RECT = "right_rect";
    private static final String SUGGESTED_HEIGHT_PIXELS = "height";
    private static final String SUGGESTED_WIDTH_PIXELS = "width";
    private static final String TAG = "NotificationSurfaceBroadcastReceiver";
    private static final String YAW = "yaw";
    private Context mCachedContext;
    long mSurfaceTextureNotifierPointer;
    private UserHandle mUserHandle = null;

    private native void nativeClearSurfaceTextureStatus(long j);

    private native void nativeFlagValidSurface(long j);

    private native void nativeHandleBroadcast(long j, Bundle bundle, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, int i, int i2, int i3, int i4);

    private native void nativeHandleBroadcastViewCentered(long j, Bundle bundle, int i, int i2, double d, int i3, int i4);

    private native void nativeSetInputRoutingTypeForOverlay(long j, int i);

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        nativeFlagValidSurface(this.mSurfaceTextureNotifierPointer);
    }

    public NotificationSurfaceBroadcastReceiver(Context context, long surfaceTextureNotifierPointer) {
        this.mCachedContext = context;
        this.mSurfaceTextureNotifierPointer = surfaceTextureNotifierPointer;
        Log.i(TAG, "Registering com.oculus.vrdriver.new_notification_to_show");
        Log.i(TAG, "Registering com.oculus.vrdriver.teardown_notification");
        Log.i(TAG, "Registering com.oculus.vrdriver.block_input_for_overlay");
        IntentFilter intentFilter = new IntentFilter(NEW_NOTIFICATION_ACTION);
        intentFilter.addAction(DELETE_NOTIFICATION_ACTION);
        intentFilter.addAction(BLOCK_INPUT_FOR_OVERLAY_ACTION);
        ContextHelper.registerReceiverAsAllUsers(this.mCachedContext, this, intentFilter, "oculus.render_notification");
    }

    public void Destroy() {
        this.mCachedContext.unregisterReceiver(this);
    }

    /* access modifiers changed from: package-private */
    public void sendSurfaceToPanelService(final String servicePackageName, final String serviceClassName, final Bundle panelExtras, final Surface surface, final int width, final int height, final int sharedMemoryFd) {
        Log.i(TAG, "Broadcasting surface " + surface + " service package " + servicePackageName + " service class " + serviceClassName);
        new Thread(new Runnable() {
            /* class com.oculus.vrapi.NotificationSurfaceBroadcastReceiver.AnonymousClass1 */

            public void run() {
                try {
                    Message msgCreation = Message.obtain(null, 2, 0, 0);
                    Bundle panelCreationBundle = new Bundle();
                    ParcelFileDescriptor pfd = null;
                    try {
                        pfd = ParcelFileDescriptor.fromFd(sharedMemoryFd);
                    } catch (IOException e) {
                        Log.e(NotificationSurfaceBroadcastReceiver.TAG, "Failed to get ParcelFileDescriptor for shared mememory fd", e);
                    }
                    panelCreationBundle.putInt("panelWidth", width);
                    panelCreationBundle.putInt("panelHeight", height);
                    panelCreationBundle.putParcelable("sharedMemoryFd", pfd);
                    panelCreationBundle.putParcelable("panelSurface", surface);
                    panelCreationBundle.putParcelable("panelBundle", panelExtras);
                    msgCreation.setData(panelCreationBundle);
                    OVRServiceSynchronous.ServiceBindResult result = new OVRServiceSynchronous(NotificationSurfaceBroadcastReceiver.this.mCachedContext, servicePackageName, serviceClassName, NotificationSurfaceBroadcastReceiver.this.mUserHandle, Executors.newSingleThreadExecutor()).waitForConnect();
                    if (result.mBindError == 0) {
                        result.mMessenger.send(msgCreation);
                    } else {
                        Log.e(NotificationSurfaceBroadcastReceiver.TAG, "couldn't find the address");
                    }
                } catch (Exception e2) {
                    Log.e(NotificationSurfaceBroadcastReceiver.TAG, "", e2);
                }
            }
        }).start();
    }

    private boolean isTrustedApp(Context context, String callerPackageName) {
        if (Arrays.asList("com.oculus.AugmentedVR").contains(callerPackageName)) {
            return isUserDevBuild();
        }
        return Arrays.asList(TrustedSignatureVerifier.TrustedSignedApps).contains(callerPackageName) && TrustedSignatureVerifier.isSignatureValid(context, callerPackageName);
    }

    private static boolean isUserDevBuild() {
        Log.d(TAG, "Checking build type");
        String propValue = SystemProps.getString("ro.build.fingerprint", null);
        if (propValue == null) {
            return false;
        }
        return !propValue.endsWith("release-keys");
    }

    /* JADX INFO: Multiple debug info for r6v2 float[]: [D('leftRect' float[]), D('rightRect' float[])] */
    /* JADX INFO: Multiple debug info for r2v17 double: [D('sinPitch' double), D('xz' double)] */
    public void onReceive(Context context, Intent intent) {
        int height;
        String action;
        float[] leftRect;
        float[] rightRect;
        int width;
        float[] rightMatrix;
        float[] leftMatrix;
        String action2 = intent.getAction();
        Log.d(TAG, "Received " + action2 + " broadcast");
        if (intent.hasExtra("_ci_")) {
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
            if (NEW_NOTIFICATION_ACTION.equals(action2)) {
                this.mUserHandle = pendingIntent.getCreatorUserHandle();
            }
            if (NEW_NOTIFICATION_ACTION.equals(action2)) {
                int width2 = intent.getIntExtra(SUGGESTED_WIDTH_PIXELS, -1);
                int height2 = intent.getIntExtra(SUGGESTED_HEIGHT_PIXELS, -1);
                int layerFlags = intent.getIntExtra(LAYER_FLAGS, 2);
                int duration = intent.getIntExtra(DURATION_MILLISECONDS, -1);
                if (!POSITION_USER_YAW.equals(intent.getStringExtra(POSITION))) {
                    height = height2;
                } else if (!intent.hasExtra(DISTANCE)) {
                    height = height2;
                } else if (!intent.hasExtra(SUGGESTED_WIDTH_PIXELS)) {
                    height = height2;
                } else if (intent.hasExtra(SUGGESTED_HEIGHT_PIXELS)) {
                    nativeHandleBroadcastViewCentered(this.mSurfaceTextureNotifierPointer, intent.getExtras(), width2, height2, (double) intent.getFloatExtra(DISTANCE, -1.0f), layerFlags, duration);
                    return;
                } else {
                    height = height2;
                }
                if (!intent.hasExtra(LEFT_RECT) || !intent.hasExtra(RIGHT_RECT) || !intent.hasExtra(LEFT_MATRIX) || !intent.hasExtra(RIGHT_MATRIX) || !intent.hasExtra(SUGGESTED_WIDTH_PIXELS) || !intent.hasExtra(SUGGESTED_HEIGHT_PIXELS)) {
                    if (intent.hasExtra(PITCH)) {
                        if (intent.hasExtra(YAW)) {
                            if (intent.hasExtra(DISTANCE)) {
                                if (intent.hasExtra(SUGGESTED_WIDTH_PIXELS)) {
                                    if (intent.hasExtra(SUGGESTED_HEIGHT_PIXELS)) {
                                        Log.d(TAG, "Using backwards compatibility mode.");
                                        double distance = (double) intent.getFloatExtra(DISTANCE, -1.0f);
                                        float[] rightRect2 = {0.0f, 0.0f, 1.0f, 1.0f};
                                        double yawRad = Math.toRadians((double) intent.getFloatExtra(YAW, -1.0f));
                                        double pitchRad = Math.toRadians((double) intent.getFloatExtra(PITCH, -1.0f));
                                        double pitch = (double) width2;
                                        Double.isNaN(pitch);
                                        double scaleX = pitch / 512.0d;
                                        width = width2;
                                        double d = (double) height;
                                        Double.isNaN(d);
                                        double scaleY = d / 512.0d;
                                        double sinPitch = Math.sin(pitchRad);
                                        double cosPitch = Math.cos(pitchRad);
                                        leftRect = rightRect2;
                                        rightRect = rightRect2;
                                        double sinYaw = Math.sin(yawRad);
                                        double cosYaw = Math.cos(yawRad);
                                        double zx = cosPitch * sinYaw;
                                        action = action2;
                                        double zy = -sinPitch;
                                        double zz = cosPitch * cosYaw;
                                        double xz = -sinYaw;
                                        Double.isNaN(distance);
                                        Double.isNaN(distance);
                                        Double.isNaN(distance);
                                        float[] worldMatrix = {(float) (cosYaw * scaleX), (float) (((zy * xz) - (zz * 0.0d)) * scaleY), (float) (zx * 1.0d), (float) ((-distance) * zx), (float) (0.0d * scaleX), (float) (((zz * cosYaw) - (zx * xz)) * scaleY), (float) (zy * 1.0d), (float) ((-distance) * zy), (float) (xz * scaleX), (float) (((zx * 0.0d) - (zy * cosYaw)) * scaleY), (float) (zz * 1.0d), (float) ((-distance) * zz), 0.0f, 0.0f, 0.0f, 1.0f};
                                        leftMatrix = worldMatrix;
                                        rightMatrix = worldMatrix;
                                    }
                                }
                            }
                        }
                    }
                    Log.w(TAG, "Missing required parameters. Ignoring broadcast");
                    return;
                }
                float[] leftRect2 = intent.getFloatArrayExtra(LEFT_RECT);
                float[] rightRect3 = intent.getFloatArrayExtra(RIGHT_RECT);
                float[] leftMatrix2 = intent.getFloatArrayExtra(LEFT_MATRIX);
                float[] rightMatrix2 = intent.getFloatArrayExtra(RIGHT_MATRIX);
                if (leftRect2 == null || leftRect2.length != 4 || rightRect3 == null || rightRect3.length != 4 || leftMatrix2 == null || leftMatrix2.length != 16 || rightMatrix2 == null || rightMatrix2.length != 16) {
                    Log.w(TAG, "Invalid Rect or Matrix parameters. Ignoring broadcast");
                    return;
                }
                leftRect = leftRect2;
                rightRect = rightRect3;
                leftMatrix = leftMatrix2;
                rightMatrix = rightMatrix2;
                width = width2;
                action = action2;
                nativeHandleBroadcast(this.mSurfaceTextureNotifierPointer, intent.getExtras(), leftRect, rightRect, leftMatrix, rightMatrix, width, height, layerFlags, duration);
            } else if (DELETE_NOTIFICATION_ACTION.equals(action2)) {
                nativeClearSurfaceTextureStatus(this.mSurfaceTextureNotifierPointer);
            } else if (!BLOCK_INPUT_FOR_OVERLAY_ACTION.equals(action2)) {
                Log.e(TAG, "Error logging unhandled broadcast action:" + action2);
            } else if (intent.hasExtra(INPUT_BLOCKING_TYPE_KEY)) {
                String blockingType = intent.getStringExtra(INPUT_BLOCKING_TYPE_KEY);
                if (NO_INPUT_BLOCKING_TYPE.equals(blockingType)) {
                    nativeSetInputRoutingTypeForOverlay(this.mSurfaceTextureNotifierPointer, 0);
                } else if (BUTTON_INPUT_BLOCKING_TYPE.equals(blockingType)) {
                    nativeSetInputRoutingTypeForOverlay(this.mSurfaceTextureNotifierPointer, 1);
                } else if (BACK_INPUT_BLOCK.equals(blockingType)) {
                    nativeSetInputRoutingTypeForOverlay(this.mSurfaceTextureNotifierPointer, 2);
                } else {
                    Log.w(TAG, "Unknown blocking type: " + blockingType);
                }
            } else {
                Log.w(TAG, "Missing required parameters. Ignoring broadcast");
            }
        } else {
            Log.w(TAG, "No PendingIntent attached");
        }
    }
}
