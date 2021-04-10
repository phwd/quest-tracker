package com.oculus.auth.receiver;

import com.facebook.inject.AbstractComponentProvider;

public class LoginHandlersServiceAutoProvider extends AbstractComponentProvider<LoginHandlersService> {
    public void inject(LoginHandlersService loginHandlersService) {
        LoginHandlersService._UL_staticInjectMe(this, loginHandlersService);
    }

    public boolean equals(Object obj) {
        return obj instanceof LoginHandlersServiceAutoProvider;
    }
}
