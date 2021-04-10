package com.oculus.appmanager.uninstaller.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.uninstaller.events.EventsModule;
import com.oculus.appmanager.uninstaller.events.UninstallCompleteEvent;
import com.oculus.appmanager.uninstaller.events.UninstallCompleteEventProvider;
import com.oculus.appmanager.uninstaller.events.UninstallResponse;
import com.oculus.appmanager.uninstaller.events.UninstallerEventBus;
import com.oculus.library.model.InstallerCallbackReceiver;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;

public class UninstallBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_PROXY_UNINSTALL_CALLBACK = "com.oculus.action.PROXY_UNINSTALL_COMPLETE";
    public static final String ACTION_UNINSTALL_CALLBACK = "com.oculus.action.UNINSTALL_COMPLETE";
    public static final String EXTRA_CALLING_PACKAGE = "extra_calling_package";
    public static final String EXTRA_RESULT_RECEIVER = "result_receiver";
    private static final Class<?> TAG = UninstallBroadcastReceiver.class;
    private InjectionContext _UL_mInjectionContext;

    private static final void _UL_injectMe(Context context, UninstallBroadcastReceiver uninstallBroadcastReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), uninstallBroadcastReceiver);
        } else {
            FbInjector.injectMe(UninstallBroadcastReceiver.class, uninstallBroadcastReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, UninstallBroadcastReceiver uninstallBroadcastReceiver) {
        uninstallBroadcastReceiver._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    public void onReceive(Context context, Intent intent) {
        _UL_injectMe(context, this);
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            BLog.e(TAG, "Empty or missing action.");
        } else if (ACTION_UNINSTALL_CALLBACK.equals(action) || ACTION_PROXY_UNINSTALL_CALLBACK.equals(action)) {
            onCallbackPackageRemoved(intent);
        } else {
            BLog.e(TAG, "Unsupported action: %s", action);
        }
    }

    private void onCallbackPackageRemoved(Intent intent) {
        UninstallResponse uninstallResponse;
        InstallerResult installerResult;
        String stringExtra = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
        String stringExtra2 = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        if (TextUtils.isEmpty(stringExtra)) {
            BLog.e(TAG, "No package name specified.");
            return;
        }
        String stringExtra3 = intent.getStringExtra(EXTRA_CALLING_PACKAGE);
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
        if (intExtra == 0) {
            BLog.d(TAG, "Successfully uninstalled %s", stringExtra);
            ((UninstallCompleteEventProvider) FbInjector.lazyInstance(0, EventsModule.UL_id._UL__ULSEP_com_oculus_appmanager_uninstaller_events_UninstallCompleteEventProvider_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(stringExtra, UninstallCompleteEvent.API_NAME_PROVIDER, Integer.valueOf(intExtra), null, true, stringExtra3).report();
            uninstallResponse = UninstallResponse.success(stringExtra, resultReceiver);
        } else {
            BLog.d(TAG, "Failed to uninstall %s. Status:%d. Message: %s.", stringExtra, Integer.valueOf(intExtra), stringExtra2);
            ((UninstallCompleteEventProvider) FbInjector.lazyInstance(0, EventsModule.UL_id._UL__ULSEP_com_oculus_appmanager_uninstaller_events_UninstallCompleteEventProvider_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(stringExtra, UninstallCompleteEvent.API_NAME_PROVIDER, Integer.valueOf(intExtra), null, false, stringExtra3).report();
            uninstallResponse = UninstallResponse.failure(stringExtra, InstallerResultError.UNKNOWN_ERROR, resultReceiver);
        }
        UninstallerEventBus.getInstance().post(uninstallResponse);
        if (uninstallResponse.resultReceiver != null) {
            if (uninstallResponse.isSuccess) {
                installerResult = InstallerResult.createForSuccess(stringExtra, stringExtra, ((Clock) FbInjector.lazyInstance(1, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now());
            } else {
                installerResult = InstallerResult.createForError(stringExtra, stringExtra, ((Clock) FbInjector.lazyInstance(1, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), uninstallResponse.installerResultError);
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(InstallerCallbackReceiver.EXTRA_RESULT, installerResult);
            uninstallResponse.resultReceiver.send(0, bundle);
        }
    }
}
