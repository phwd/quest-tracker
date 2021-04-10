package com.oculus.auth.receiver;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LoginHandlersRunnerAutoProvider extends AbstractProvider<LoginHandlersRunner> {
    @Override // javax.inject.Provider
    public LoginHandlersRunner get() {
        return new LoginHandlersRunner(this);
    }
}
