package com.oculus.auth.receiver;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.auth.receiver.ReceiverModule;
import com.oculus.auth.service.contract.ServiceContract;

public class LoginHandlersService extends IntentService {
    private InjectionContext _UL_mInjectionContext;

    private static final void _UL_injectMe(Context context, LoginHandlersService loginHandlersService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), loginHandlersService);
        } else {
            FbInjector.injectMe(LoginHandlersService.class, loginHandlersService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, LoginHandlersService loginHandlersService) {
        loginHandlersService._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    public LoginHandlersService() {
        super("LoginHandlersService");
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (((action.hashCode() == 698177661 && action.equals(ServiceContract.BROADCAST_LOGIN)) ? (char) 0 : 65535) != 0) {
            BLog.e("TAG", "Unsupported action: " + action);
            return;
        }
        ((LoginHandlersRunner) FbInjector.lazyInstance(0, ReceiverModule.UL_id._UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_BINDING_ID, this._UL_mInjectionContext)).runLoginHandlers();
    }
}
