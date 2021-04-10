package com.oculus.appmanager.installer.boot;

import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.receiver.ActionReceiver;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.installer.contract.InstallerServiceContract;

class BootCompletedAction implements ActionReceiver, InjectableComponentWithoutContext {
    private static final String TAG = "BootCompletedAction";
    @Inject
    @Eager
    private InstallerServiceContract mInstallerServiceContract;

    BootCompletedAction() {
    }

    private static final void _UL_injectMe(Context context, BootCompletedAction bootCompletedAction) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), bootCompletedAction);
        } else {
            FbInjector.injectMe(BootCompletedAction.class, (InjectableComponentWithoutContext) bootCompletedAction, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, BootCompletedAction bootCompletedAction) {
        bootCompletedAction.mInstallerServiceContract = InstallerServiceContract._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.facebook.secure.receiver.ActionReceiver
    public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
        BLog.i(TAG, "Boot receiver triggered");
        _UL_injectMe(context, this);
        this.mInstallerServiceContract.performBootCleanup();
    }
}
