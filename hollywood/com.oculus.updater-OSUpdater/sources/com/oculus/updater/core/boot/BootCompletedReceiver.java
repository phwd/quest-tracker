package com.oculus.updater.core.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.multiuser.MultiuserModule;
import com.oculus.multiuser.UserClassifier;
import com.oculus.updater.core.os.contract.OSUpdateServiceContract;

public class BootCompletedReceiver extends BroadcastReceiver implements InjectableComponentWithoutContext {
    private static final String TAG = "com.oculus.updater.core.boot.BootCompletedReceiver";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private OSUpdateServiceContract mOSUpdateServiceContract;

    private static final void _UL_injectMe(Context context, BootCompletedReceiver bootCompletedReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), bootCompletedReceiver);
        } else {
            FbInjector.injectMe(BootCompletedReceiver.class, (InjectableComponentWithoutContext) bootCompletedReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, BootCompletedReceiver bootCompletedReceiver) {
        bootCompletedReceiver._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        bootCompletedReceiver.mOSUpdateServiceContract = OSUpdateServiceContract._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onReceive(Context context, Intent intent) {
        BLog.i(TAG, "Boot receiver triggered");
        _UL_injectMe(context, this);
        if (((UserClassifier) FbInjector.lazyInstance(0, MultiuserModule.UL_id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isSystem()) {
            this.mOSUpdateServiceContract.notifySystemEvent("system_rebooted");
        }
    }
}
