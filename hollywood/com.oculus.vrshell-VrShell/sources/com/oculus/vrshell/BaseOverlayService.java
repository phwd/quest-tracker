package com.oculus.vrshell;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.PreferencesManager;
import com.oculus.os.WindowTokenContext;
import com.oculus.vrshell.config.BootConfig;
import com.oculus.vrshell.util.CallerInfoHelper;
import com.oculus.vrshell.util.DeviceHelper;

public abstract class BaseOverlayService extends Service {
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String KEY_MESSAGE_TYPE = "message_type";
    private static final String OVERLAY_INPUT_FOCUS_CHANGE = "overlay_input_focus_change";
    private static final String OVERLAY_INPUT_FOCUS_STATE = "InputFocusState";
    private static final String TAG = LoggingUtil.tag(BaseOverlayService.class);
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private WindowTokenContext mOverlayContext;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private View mServiceView;
    private WindowManager.LayoutParams mServiceViewWindowLayoutParamsActive;
    private WindowManager mWindowManager;

    /* access modifiers changed from: package-private */
    public abstract FrameLayout createEventInterceptLayout();

    public void onCreate() {
        super.onCreate();
        this.mWindowManager = (WindowManager) getSystemService("window");
        createServiceWindowView();
        this.mOverlayContext = new WindowTokenContext(this, this.mServiceView);
    }

    public Context getInputFocusTokenViewContext() {
        if (this.mOverlayContext == null) {
            Log.e(TAG, "getInputFocusTokenViewContext called with mOverlayContext == null");
        }
        return this.mOverlayContext;
    }

    public boolean isDeviceLocked() {
        return DeviceHelper.isDeviceLocked(this.mOverlayContext);
    }

    public void setOverlayInputFocus(final boolean z, final boolean z2) {
        String str = TAG;
        Log.d(str, "setOverlayInputFocus : entrypoint - inputFocus=" + z + " shouldSupportIME=" + z2);
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.BaseOverlayService.AnonymousClass1 */

            public void run() {
                boolean z;
                try {
                    if (z) {
                        z = BaseOverlayService.this.attachServiceWindowView(z2);
                    } else {
                        z = BaseOverlayService.this.detachServiceWindowView();
                    }
                    if (z) {
                        Intent intent = new Intent();
                        intent.putExtra(BaseOverlayService.KEY_MESSAGE_TYPE, BaseOverlayService.OVERLAY_INPUT_FOCUS_CHANGE);
                        intent.setComponent(new ComponentName("com.oculus.horizon", BaseOverlayService.HORIZON_COMPONENT_NAME));
                        CallerInfoHelper.attachCallerInfo(intent, BaseOverlayService.this, null);
                        intent.putExtra(BaseOverlayService.OVERLAY_INPUT_FOCUS_STATE, z);
                        BaseOverlayService.this.getApplication().startService(intent);
                        Application application = BaseOverlayService.this.getApplication();
                        if (application == null) {
                            Log.d(BaseOverlayService.TAG, "onPrimaryPackageChanged - shellApplication null");
                            return;
                        } else if (application instanceof ShellApplication) {
                            ((ShellApplication) application).getNavigationRouter().setCurrentAppFocus(!z);
                        }
                    }
                    String str = BaseOverlayService.TAG;
                    Log.d(str, "setOverlayInputFocus : completed - inputFocus=" + z + " shouldSupportIME=" + z2 + " actionTaken:" + z);
                } catch (Throwable th) {
                    String str2 = BaseOverlayService.TAG;
                    Log.e(str2, "setOverlayInputFocus(1) e = " + th);
                }
            }
        });
    }

    private void createServiceWindowView() {
        Log.d(TAG, "createServiceWindowView");
        if (((LayoutInflater) getSystemService("layout_inflater")) != null) {
            FrameLayout createEventInterceptLayout = createEventInterceptLayout();
            createEventInterceptLayout.setLayoutParams(new FrameLayout.LayoutParams(1, 1));
            this.mServiceView = createEventInterceptLayout;
            this.mServiceView.setClickable(true);
            this.mServiceView.setFocusable(true);
            this.mServiceViewWindowLayoutParamsActive = new WindowManager.LayoutParams(1, 1, Build.VERSION.SDK_INT >= 26 ? 2038 : 2002, 0, -2);
            WindowManager.LayoutParams layoutParams = this.mServiceViewWindowLayoutParamsActive;
            layoutParams.gravity = 8388627;
            layoutParams.x = 0;
            layoutParams.y = 0;
            Log.d(TAG, "createServiceWindowView service view available");
            return;
        }
        Log.e(TAG, "Layout Inflater Service is null.");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean attachServiceWindowView(boolean z) {
        String str = TAG;
        Log.d(str, "attachServiceWindowView shouldSupportIME:" + z);
        try {
            if (this.mServiceView.getWindowId() == null) {
                attachServiceWindowInternal(z);
                return true;
            }
            if (((this.mServiceViewWindowLayoutParamsActive.flags & 131072) == 131072) != z) {
                Log.d(TAG, "attachServiceWindowView shouldSupportIME change");
                detachServiceWindowView();
                attachServiceWindowInternal(z);
                return true;
            }
            return false;
        } catch (Throwable th) {
            String str2 = TAG;
            Log.e(str2, "attachServiceWindowView e = " + th, th);
        }
    }

    private void attachServiceWindowInternal(boolean z) {
        if (z) {
            this.mServiceViewWindowLayoutParamsActive.flags = 131072;
        }
        this.mWindowManager.addView(this.mServiceView, this.mServiceViewWindowLayoutParamsActive);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean detachServiceWindowView() {
        Log.d(TAG, "detachServiceWindowView");
        try {
            if (this.mServiceView.getWindowId() != null) {
                this.mServiceViewWindowLayoutParamsActive.flags = 0;
                this.mWindowManager.removeViewImmediate(this.mServiceView);
                return true;
            }
        } catch (Throwable th) {
            String str = TAG;
            Log.e(str, "detachServiceWindowView e = " + th, th);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ShellApplication getShellApplication() {
        if (getApplication() == null) {
            return null;
        }
        return (ShellApplication) getApplication();
    }

    /* access modifiers changed from: protected */
    public void updateExperimentValues() {
        ShellApplication shellApplication = getShellApplication();
        if (shellApplication != null) {
            ExperimentUtil.updateForceApplicationFocusAwarenessPreferences(this.mPreferencesManager, BootConfig.getBootConfig(shellApplication).isForceApplicationFocusAwarenessEnabled);
        }
    }
}
