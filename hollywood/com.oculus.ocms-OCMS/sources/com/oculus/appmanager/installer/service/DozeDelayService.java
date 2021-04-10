package com.oculus.appmanager.installer.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IDeviceIdleController;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.secure.pendingintent.SecurePendingIntent;
import com.facebook.ultralight.UL;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.installer.service.MC;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class DozeDelayService extends IntentService {
    private static final String ACTION_DELAY_DOZE = "delay_doze";
    private static final String ACTION_DELAY_DOZE_EXTEND = "delay_doze_extend";
    private static final String DEVICE_IDLE_CONTROLLER_SERVICE = "deviceidle";
    private static final String DOZE_DELAY_SERVICE_CLASS = "com.oculus.appmanager.installer.service.DozeDelayService";
    private static final String EVENT_DOZE_DELAY_SERVICE = "oculus_mobile_doze_delay_service";
    private static final String EXTRA_DOZE_DELAY_REQUEST_TYPE = "doze_delay_request_type";
    private static final int MAX_RETRIES = 5;
    private static final String TAG = "DozeDelayService";
    private InjectionContext _UL_mInjectionContext;
    private AlarmManager mAlarmManager;
    @Nullable
    private IDeviceIdleController mDeviceIdleController;
    private int mDozeDelayInitialPeriod;
    private int mDozeDelayRetriggerPeriod;

    private static final void _UL_injectMe(Context context, DozeDelayService dozeDelayService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), dozeDelayService);
        } else {
            FbInjector.injectMe(DozeDelayService.class, dozeDelayService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, DozeDelayService dozeDelayService) {
        dozeDelayService._UL_mInjectionContext = new InjectionContext(3, injectorLike);
    }

    public DozeDelayService() {
        super("OculusDozeDelayService");
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
        initDozeDelayFramework();
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (this.mDeviceIdleController == null || this.mAlarmManager == null) {
            BLog.e(TAG, "Service init failed: Unable to handle intent");
        } else if (intent == null) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("service_missing_update_id", "Null intent");
        } else {
            String action = intent.getAction();
            if (Strings.isNullOrEmpty(action)) {
                ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("service_missing_update_id", "Null or empty action");
                return;
            }
            BLog.i(TAG, "Received action: %s", action);
            ((EventManager) FbInjector.lazyInstance(1, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(EVENT_DOZE_DELAY_SERVICE).addExtra(EXTRA_DOZE_DELAY_REQUEST_TYPE, action).logAndRelease();
            if (TextUtils.equals(action, ACTION_DELAY_DOZE)) {
                processDelayDoze(true);
            } else if (TextUtils.equals(action, ACTION_DELAY_DOZE_EXTEND)) {
                processDelayDoze(false);
            } else {
                BLog.e(TAG, "Received unrecognized action: %s", action);
            }
        }
    }

    private void initDozeDelayFramework() {
        int i = 5;
        while (this.mDeviceIdleController == null && i > 0) {
            i--;
            try {
                this.mDeviceIdleController = IDeviceIdleController.Stub.asInterface((IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, DEVICE_IDLE_CONTROLLER_SERVICE));
            } catch (Exception unused) {
                this.mDeviceIdleController = null;
                ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(TAG, "DeviceIdleController initialization failed." + i + " more retries left");
            }
        }
        if (this.mDeviceIdleController == null) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(TAG, "Failed to init Doze Framework; Installs will be subject to Doze");
            return;
        }
        this.mAlarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (this.mAlarmManager == null) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(TAG, "Failed to init AlarmManager; Installs will be subject to Doze");
        }
        this.mDozeDelayInitialPeriod = (int) TimeUnit.MINUTES.toMillis(((MobileConfig) FbInjector.lazyInstance(2, MobileConfigFactoryModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong(MC.android_vrosservices_delay_doze.doze_delay_initial_period_minutes));
        this.mDozeDelayRetriggerPeriod = (int) TimeUnit.MINUTES.toMillis(((MobileConfig) FbInjector.lazyInstance(2, MobileConfigFactoryModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong(MC.android_vrosservices_delay_doze.doze_delay_retrigger_period_minutes));
    }

    private void processDelayDoze(boolean z) {
        if (this.mDeviceIdleController != null) {
            try {
                BLog.i(TAG, "Calling delayDoze with period %d", Integer.valueOf(this.mDozeDelayInitialPeriod));
                this.mDeviceIdleController.delayDoze(this.mDozeDelayInitialPeriod);
            } catch (RemoteException e) {
                BLog.e(TAG, "Couldn't set active", e);
            }
        } else {
            BLog.w(TAG, "Not Calling delayDoze since mDeviceIdleController is null");
        }
        if (z || isAnyUpdateTransient()) {
            BLog.i(TAG, "Scheduling an alarm to check back in the future");
            scheduleAlarm();
            return;
        }
        BLog.w(TAG, "No work remaining, not re-scheduling an alarm");
    }

    private boolean isAnyUpdateTransient() {
        return ((InfoUtils) FbInjector.localInstance(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).findFirstUpdate(new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.DozeDelayService.AnonymousClass1 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                return apkUpdateInfo != null && apkUpdateInfo.getState().isTransient();
            }
        }) != null;
    }

    private void scheduleAlarm() {
        Intent intent = new Intent(ACTION_DELAY_DOZE_EXTEND);
        intent.setComponent(new ComponentName("com.oculus.ocms", DOZE_DELAY_SERVICE_CLASS));
        PendingIntent buildForService = SecurePendingIntent.builder().fromIntentWithExtras(intent, getApplicationContext().getClassLoader()).buildForService(getApplicationContext(), 0, 536870912);
        if (buildForService != null) {
            BLog.i(TAG, "Cancelling old alarm");
            this.mAlarmManager.cancel(buildForService);
        } else {
            BLog.i(TAG, "Creating pending intent for DozeDelayService");
            buildForService = SecurePendingIntent.builder().fromIntentWithExtras(intent, getApplicationContext().getClassLoader()).buildForService(getApplicationContext(), 0, 0);
        }
        BLog.i(TAG, "Scheduling new alarm");
        this.mAlarmManager.set(2, SystemClock.elapsedRealtime() + ((long) this.mDozeDelayRetriggerPeriod), buildForService);
    }
}
