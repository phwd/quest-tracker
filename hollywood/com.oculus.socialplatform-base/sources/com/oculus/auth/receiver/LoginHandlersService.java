package com.oculus.auth.receiver;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class LoginHandlersService extends IntentService {
    public AnonymousClass0RE _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, LoginHandlersService loginHandlersService) {
        loginHandlersService._UL_mInjectionContext = new AnonymousClass0RE(1, r2);
    }

    public LoginHandlersService() {
        super("LoginHandlersService");
    }

    public static final void _UL_injectMe(Context context, LoginHandlersService loginHandlersService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), loginHandlersService);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (action.hashCode() != 698177661 || !action.equals("com.oculus.auth.BROADCAST_LOGIN")) {
            AnonymousClass0MD.A04("TAG", AnonymousClass006.A07("Unsupported action: ", action));
        } else {
            ((LoginHandlersRunner) AnonymousClass0VF.A03(0, 38, this._UL_mInjectionContext)).runLoginHandlers();
        }
    }
}
