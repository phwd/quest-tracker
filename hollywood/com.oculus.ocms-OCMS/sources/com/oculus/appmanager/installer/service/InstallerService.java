package com.oculus.appmanager.installer.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.common.util.StringLocaleUtil;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.google.common.base.Strings;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.installer.contract.InstallerServiceContract;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.debug.DebugMode;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.util.service.ServiceFutures;
import java.util.Locale;
import javax.inject.Inject;

public class InstallerService extends IntentService implements InjectableComponentWithoutContext {
    private static final String TAG = "InstallerService";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private DebugMode mDebugMode;
    @Inject
    @Eager
    private DozeDelayHelper mDozeDelayHelper;
    @Inject
    @Eager
    private InfoUtils mInfoUtils;
    @Inject
    @Eager
    private InstallerCleanUpHelper mInstallerCleanUpHelper;
    @Inject
    @Eager
    private InstallerConsistencyHelper mInstallerConsistencyHelper;
    @Inject
    @Eager
    private InstallerServiceManager mInstallerServiceManager;
    @Inject
    @Eager
    private ServiceFutures mServiceFutures;

    private static final void _UL_injectMe(Context context, InstallerService installerService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), installerService);
        } else {
            FbInjector.injectMe(InstallerService.class, (InjectableComponentWithoutContext) installerService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, InstallerService installerService) {
        installerService._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        installerService.mInstallerServiceManager = InstallerServiceManager._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceManager_ULSEP_ACCESS_METHOD(injectorLike);
        installerService.mInfoUtils = InfoUtils._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_ACCESS_METHOD(injectorLike);
        installerService.mDebugMode = DebugMode._UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_ACCESS_METHOD(injectorLike);
        installerService.mServiceFutures = ServiceFutures._UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_ACCESS_METHOD(injectorLike);
        installerService.mInstallerConsistencyHelper = InstallerConsistencyHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerConsistencyHelper_ULSEP_ACCESS_METHOD(injectorLike);
        installerService.mInstallerCleanUpHelper = InstallerCleanUpHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_ACCESS_METHOD(injectorLike);
        installerService.mDozeDelayHelper = DozeDelayHelper._UL__ULSEP_com_oculus_appmanager_installer_service_DozeDelayHelper_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public InstallerService() {
        super("OculusInstallerService");
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public void onHandleIntent(Intent intent) {
        if (intent == null) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("service_missing_update_id", "Null intent");
            return;
        }
        String action = intent.getAction();
        if (Strings.isNullOrEmpty(action)) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("service_missing_update_id", "Null or empty action");
            return;
        }
        ApkUpdateInfo apkUpdateInfo = null;
        if (!isUpdateIdOptional(action)) {
            long longExtra = intent.getLongExtra("update_id", -1);
            if (longExtra == -1) {
                ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("service_missing_update_id", String.format(Locale.US, "No update id in intent with action %s", action));
                return;
            }
            apkUpdateInfo = this.mInfoUtils.getUpdate(longExtra, false);
            if (apkUpdateInfo == null) {
                ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.SERVICE_UPDATE_NOT_FOUND, "update not found in storage");
                return;
            }
        }
        try {
            handleAction(intent, apkUpdateInfo, action);
        } catch (Exception e) {
            BLog.e(TAG, e, "Unhandled exception during install");
            if (apkUpdateInfo == null || apkUpdateInfo.getState().isFinal()) {
                ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.SERVICE_UNEXPECTED_EXCEPTION, "uncaught exception in installer service", e);
                return;
            }
            String message = e.getMessage();
            if (TextUtils.isEmpty(message)) {
                message = "No message";
            }
            if (this.mDebugMode.isEnabled()) {
                this.mInfoUtils.failHard(apkUpdateInfo, ErrorCategories.SERVICE_UNEXPECTED_EXCEPTION, message, InstallerResultError.UNKNOWN_ERROR, e);
            } else {
                this.mInfoUtils.failSoft(apkUpdateInfo, ErrorCategories.SERVICE_UNEXPECTED_EXCEPTION, message, InstallerResultError.UNKNOWN_ERROR, e);
            }
        }
    }

    private void handleAction(Intent intent, ApkUpdateInfo apkUpdateInfo, String str) {
        if (InstallerServiceContract.Cancel.ACTION_CANCEL.equals(str)) {
            this.mInstallerServiceManager.requestCancel(apkUpdateInfo, this.mServiceFutures.getFuture(intent));
        } else if (!this.mServiceFutures.hasFuture(intent) || handleFuturesMetadata(apkUpdateInfo, intent)) {
            char c = 65535;
            switch (str.hashCode()) {
                case -1853370988:
                    if (str.equals(InstallerServiceContract.Verify.ACTION_VERIFICATION_COMPLETE)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1351509759:
                    if (str.equals(InstallerServiceContract.Consistency.ACTION_CONSISTENCY_FOR_ID)) {
                        c = 11;
                        break;
                    }
                    break;
                case -1034374586:
                    if (str.equals(InstallerServiceContract.Install.ACTION_INSTALL_COMPLETED)) {
                        c = 6;
                        break;
                    }
                    break;
                case -939293082:
                    if (str.equals(InstallerServiceContract.Misc.ACTION_CONTINUE_UPDATE_TICKLE)) {
                        c = '\r';
                        break;
                    }
                    break;
                case -556517049:
                    if (str.equals(InstallerServiceContract.Download.ACTION_DOWNLOAD_COMPLETE)) {
                        c = 2;
                        break;
                    }
                    break;
                case -444900928:
                    if (str.equals(InstallerServiceContract.Misc.ACTION_BOOT_CLEANUP)) {
                        c = '\f';
                        break;
                    }
                    break;
                case 283759717:
                    if (str.equals(InstallerServiceContract.Download.ACTION_DOWNLOAD_AND_INSTALL)) {
                        c = 0;
                        break;
                    }
                    break;
                case 619304066:
                    if (str.equals(InstallerServiceContract.Cancel.ACTION_CANCELED)) {
                        c = '\t';
                        break;
                    }
                    break;
                case 916379161:
                    if (str.equals(InstallerServiceContract.Uninstall.ACTION_UNINSTALL)) {
                        c = 7;
                        break;
                    }
                    break;
                case 926658947:
                    if (str.equals(InstallerServiceContract.Download.ACTION_DOWNLOAD_NOTIFICATION)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1150077542:
                    if (str.equals(InstallerServiceContract.Failure.ACTION_FAILED)) {
                        c = 5;
                        break;
                    }
                    break;
                case 1849426783:
                    if (str.equals(InstallerServiceContract.Retry.ACTION_RETRY)) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1859578198:
                    if (str.equals(InstallerServiceContract.Install.ACTION_INSTALL_SUCCESSFUL)) {
                        c = 4;
                        break;
                    }
                    break;
                case 2089310367:
                    if (str.equals(InstallerServiceContract.Uninstall.ACTION_UNINSTALL_COMPLETED)) {
                        c = '\b';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.mDozeDelayHelper.delayDoze();
                    this.mInstallerServiceManager.requestInstall(apkUpdateInfo);
                    return;
                case 1:
                    this.mInstallerServiceManager.onDownloadCompleteNotification(intent.getLongExtra("download_id", -1));
                    return;
                case 2:
                    this.mInstallerServiceManager.onDownloadComplete(apkUpdateInfo, intent.getBooleanExtra(InstallerServiceContract.RETRY_VERIFYING_ATTEMPT, false));
                    return;
                case 3:
                    this.mInstallerServiceManager.onVerificationComplete(apkUpdateInfo);
                    return;
                case 4:
                    this.mInstallerServiceManager.onUpdateSuccessful(apkUpdateInfo);
                    return;
                case 5:
                    this.mInstallerServiceManager.onUpdateFailed(apkUpdateInfo);
                    return;
                case 6:
                    this.mInstallerServiceManager.onInstallComplete(apkUpdateInfo, intent.getIntExtra("android.content.pm.extra.STATUS", 1), intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE"));
                    return;
                case 7:
                    this.mInstallerServiceManager.requestUninstall(apkUpdateInfo);
                    return;
                case '\b':
                    this.mInstallerServiceManager.onUninstallComplete(apkUpdateInfo, intent.getIntExtra("android.content.pm.extra.STATUS", 1), intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE"));
                    return;
                case '\t':
                    this.mInstallerServiceManager.onUpdateCanceled(apkUpdateInfo);
                    return;
                case '\n':
                    this.mInstallerServiceManager.onUpdateRetried(apkUpdateInfo);
                    return;
                case 11:
                    this.mInstallerConsistencyHelper.performConsistencyCheck(apkUpdateInfo);
                    return;
                case '\f':
                    this.mInstallerCleanUpHelper.performBootCleanUpProcess();
                    return;
                case '\r':
                    this.mInstallerServiceManager.continueUpdate(apkUpdateInfo);
                    return;
                default:
                    String formatStrLocaleSafe = StringLocaleUtil.formatStrLocaleSafe("Unrecognized action for InstallerService: %s.", str);
                    BLog.e(TAG, formatStrLocaleSafe);
                    ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(TAG, formatStrLocaleSafe);
                    return;
            }
        }
    }

    private boolean canAcceptFuture(String str) {
        return InstallerServiceContract.Cancel.ACTION_CANCEL.equals(str) || InstallerServiceContract.Download.ACTION_DOWNLOAD_AND_INSTALL.equals(str) || InstallerServiceContract.Uninstall.ACTION_UNINSTALL.equals(str);
    }

    private boolean isUpdateIdOptional(String str) {
        return InstallerServiceContract.Download.ACTION_DOWNLOAD_NOTIFICATION.equals(str) || InstallerServiceContract.Misc.ACTION_BOOT_CLEANUP.equals(str);
    }

    private boolean handleFuturesMetadata(ApkUpdateInfo apkUpdateInfo, Intent intent) {
        String action = intent.getAction();
        if (!canAcceptFuture(action)) {
            String format = String.format(Locale.US, "illegal future received during action %s on update", action);
            BLog.e(TAG, format);
            this.mInfoUtils.failSoft(apkUpdateInfo, ErrorCategories.SERVICE_ILLEGAL_FUTURE, format, InstallerResultError.INVALID_INSTALL_REQUEST, null);
            return false;
        } else if (apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_FUTURE_ID) || apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_FUTURE_PID)) {
            String format2 = String.format(Locale.US, "attempting to save future to update that already has one during action %s", action);
            BLog.e(TAG, format2);
            this.mInfoUtils.failSoft(apkUpdateInfo, ErrorCategories.SERVICE_FUTURE_OVERRIDE, format2, InstallerResultError.UNKNOWN_ERROR, null);
            return false;
        } else {
            long futureId = this.mServiceFutures.getFutureId(intent);
            apkUpdateInfo.edit().putFutureId(futureId).putFuturePid(this.mServiceFutures.getFuturePid(intent)).save();
            return true;
        }
    }
}
