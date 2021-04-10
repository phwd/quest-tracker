package com.oculus.auth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.auth.service.contract.ServiceContract;

public class AuthBroadcastReceiver extends BroadcastReceiver implements InjectableComponentWithoutContext {
    @Inject
    @Eager
    private BaseLoginHandler mHandler;

    private static final void _UL_injectMe(Context context, AuthBroadcastReceiver authBroadcastReceiver) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), authBroadcastReceiver);
        } else {
            FbInjector.injectMe(AuthBroadcastReceiver.class, (InjectableComponentWithoutContext) authBroadcastReceiver, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, AuthBroadcastReceiver authBroadcastReceiver) {
        authBroadcastReceiver.mHandler = ReceiverModule._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onReceive(Context context, Intent intent) {
        _UL_injectMe(context, this);
        String action = intent.getAction();
        if (action != null) {
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != 168677078) {
                if (hashCode == 698177661 && action.equals(ServiceContract.BROADCAST_LOGIN)) {
                    c = 0;
                }
            } else if (action.equals(ServiceContract.BROADCAST_LOGOUT)) {
                c = 1;
            }
            if (c != 0) {
                if (c == 1) {
                    this.mHandler.onLogout();
                }
                BLog.e("TAG", "Unsupported action: " + action);
            } else if (intent.getExtras().getBoolean(ServiceContract.EXTRA_IS_RELOGIN, false)) {
                this.mHandler.onRelogin();
            } else {
                this.mHandler.onLogin();
            }
        }
    }
}
