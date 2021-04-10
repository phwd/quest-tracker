package com.oculus.auth.receiver;

import X.AnonymousClass0VK;

public class LoginHandlersServiceAutoProvider extends AnonymousClass0VK<LoginHandlersService> {
    public boolean equals(Object obj) {
        return obj instanceof LoginHandlersServiceAutoProvider;
    }

    public void inject(LoginHandlersService loginHandlersService) {
        LoginHandlersService._UL_staticInjectMe(this, loginHandlersService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        LoginHandlersService._UL_staticInjectMe(this, (LoginHandlersService) obj);
    }
}
