package com.oculus.auth.receiver;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.oculus.auth.service.contract.ServiceContract;

public class LoginHandlersService extends IntentService {
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, LoginHandlersService loginHandlersService) {
        loginHandlersService._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
    }

    public LoginHandlersService() {
        super("LoginHandlersService");
    }

    public static final void _UL_injectMe(Context context, LoginHandlersService loginHandlersService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), loginHandlersService);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (action.hashCode() != 698177661 || !action.equals(ServiceContract.BROADCAST_LOGIN)) {
            AnonymousClass0NO.A08("TAG", AnonymousClass006.A05("Unsupported action: ", action));
        } else {
            ((LoginHandlersRunner) AnonymousClass0J2.A03(0, 329, this._UL_mInjectionContext)).runLoginHandlers();
        }
    }
}
