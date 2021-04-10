package com.oculus.appmanager.installer.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.facebook.common.android.AndroidModule;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.oculus.appmanager.installer.notification.InstallerNotificationManager;
import com.oculus.appmanager.installer.notification.MC;
import com.oculus.appmanager.installer.notification.contract.InstallerNotificationsContract;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.util.device.DeviceUtils;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class InstallerNotificationService extends Service {
    private static final int FOREGROUND_PROGRESS_NOTIF_ID = -87117812;
    private static final int INVALID_NOTIFICATION_ID = -1;
    private static final int NEW_DOWNLOADS_FIRST_ID = 20000;
    private static final String TAG = "com.oculus.appmanager.installer.notification.InstallerNotificationService";
    private static final String VR_NOTIFICATION_TAG = "vr_notification";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private DeviceUtils mDeviceUtils;
    @Inject
    @Eager
    private InstallerNotificationManager mInstallerNotificationManager;
    private final AtomicInteger mNextNewDownloadsID = new AtomicInteger(20000);
    @Inject
    @Eager
    private NotificationManager mNotificationManager;
    @Inject
    @Eager
    private OVRLibrary mOVRLibrary;
    private final BiMap<String, Integer> mPackageNamesToNotifIDs = HashBiMap.create();

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static final void _UL_injectMe(Context context, InstallerNotificationService installerNotificationService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), installerNotificationService);
        } else {
            FbInjector.injectMe(InstallerNotificationService.class, installerNotificationService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, InstallerNotificationService installerNotificationService) {
        installerNotificationService._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        installerNotificationService.mInstallerNotificationManager = InstallerNotificationManager._UL__ULSEP_com_oculus_appmanager_installer_notification_InstallerNotificationManager_ULSEP_ACCESS_METHOD(injectorLike);
        installerNotificationService.mNotificationManager = AndroidModule._UL__ULSEP_android_app_NotificationManager_ULSEP_ACCESS_METHOD(injectorLike);
        installerNotificationService.mDeviceUtils = DeviceUtils._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_ACCESS_METHOD(injectorLike);
        installerNotificationService.mOVRLibrary = OVRLibraryModule._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public int onStartCommand(Intent intent, int i, int i2) {
        char c;
        InstallerNotificationManager.InstallState installState;
        if (intent == null) {
            return 2;
        }
        String stringExtra = intent.getStringExtra("identifier");
        if (TextUtils.isEmpty(stringExtra)) {
            BLog.e(TAG, "required extra %s is missing", "identifier");
            return 2;
        } else if (!intent.hasExtra("is_update")) {
            BLog.e(TAG, "required extra %s is missing", "is_update");
            return 2;
        } else {
            App app = this.mOVRLibrary.getApp(stringExtra);
            if (app == null) {
                return 2;
            }
            String action = intent.getAction();
            switch (action.hashCode()) {
                case -1709011741:
                    if (action.equals(InstallerNotificationsContract.ACTION_UPDATE_PROGRESS)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1555115111:
                    if (action.equals(InstallerNotificationsContract.ACTION_SET_FAILED_INSTALL)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1402931637:
                    if (action.equals(InstallerNotificationsContract.ACTION_SET_COMPLETED)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -1211129254:
                    if (action.equals(InstallerNotificationsContract.ACTION_SET_DOWNLOADING)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -123173735:
                    if (action.equals(InstallerNotificationsContract.ACTION_SET_CANCELED)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -91235766:
                    if (action.equals(InstallerNotificationsContract.ACTION_SET_FAILED_DOWNLOAD)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 900450407:
                    if (action.equals(InstallerNotificationsContract.ACTION_SET_INSTALLING)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    installState = InstallerNotificationManager.InstallState.DOWNLOADING;
                    break;
                case 1:
                    installState = InstallerNotificationManager.InstallState.DOWNLOADING;
                    this.mInstallerNotificationManager.setDownloading(app.packageName);
                    maybeCancelOldNotification(app.packageName);
                    break;
                case 2:
                    installState = InstallerNotificationManager.InstallState.INSTALLING;
                    break;
                case 3:
                    installState = InstallerNotificationManager.InstallState.DOWNLOAD_FAIL;
                    this.mInstallerNotificationManager.setDownloadFailure(app.packageName);
                    break;
                case 4:
                    installState = InstallerNotificationManager.InstallState.INSTALL_FAIL;
                    this.mInstallerNotificationManager.setInstallFailure(app.packageName);
                    break;
                case 5:
                    installState = InstallerNotificationManager.InstallState.COMPLETED;
                    break;
                case 6:
                    installState = InstallerNotificationManager.InstallState.CANCELED;
                    break;
                default:
                    BLog.e(TAG, "Unknown action: %s", intent.getAction());
                    return 2;
            }
            updateProgressNotification(app.packageName, installState);
            return 1;
        }
    }

    private void updateProgressNotification(String str, InstallerNotificationManager.InstallState installState) {
        int i;
        if (((MobileConfig) FbInjector.lazyInstance(0, MobileConfigFactoryModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean(MC.oculus_mobile_core.downloads_manager) || !this.mDeviceUtils.isStandAloneDevice()) {
            i = getUniqueDownloadID(str, installState);
        } else {
            i = FOREGROUND_PROGRESS_NOTIF_ID;
        }
        if (i != -1) {
            Notification generateProgressNotification = this.mInstallerNotificationManager.generateProgressNotification(str, installState, i);
            if (generateProgressNotification != null) {
                if (this.mDeviceUtils.isStandAloneDevice()) {
                    this.mNotificationManager.notify(null, i, generateProgressNotification);
                } else if (!downloadStatusTerminated(installState)) {
                    startForeground(i, generateProgressNotification);
                } else {
                    stopForeground(true);
                    this.mNotificationManager.notify(null, i, generateProgressNotification);
                }
            }
            if (downloadStatusTerminated(installState)) {
                this.mInstallerNotificationManager.onDownloadTerminated(str);
            }
            if (installState == InstallerNotificationManager.InstallState.COMPLETED) {
                this.mPackageNamesToNotifIDs.remove(str);
            }
        }
    }

    private void maybeCancelOldNotification(String str) {
        if (this.mPackageNamesToNotifIDs.containsKey(str)) {
            this.mNotificationManager.cancel(null, this.mPackageNamesToNotifIDs.get(str).intValue());
            this.mPackageNamesToNotifIDs.remove(str);
        }
    }

    private static boolean downloadStatusTerminated(InstallerNotificationManager.InstallState installState) {
        return installState == InstallerNotificationManager.InstallState.DOWNLOAD_FAIL || installState == InstallerNotificationManager.InstallState.INSTALL_FAIL || installState == InstallerNotificationManager.InstallState.COMPLETED || installState == InstallerNotificationManager.InstallState.CANCELED;
    }

    private int getUniqueDownloadID(String str, InstallerNotificationManager.InstallState installState) {
        Integer num = this.mPackageNamesToNotifIDs.get(str);
        if (num == null) {
            if (downloadStatusTerminated(installState)) {
                return -1;
            }
            num = Integer.valueOf(this.mNextNewDownloadsID.incrementAndGet());
            this.mPackageNamesToNotifIDs.put(str, num);
        }
        return num.intValue();
    }
}
