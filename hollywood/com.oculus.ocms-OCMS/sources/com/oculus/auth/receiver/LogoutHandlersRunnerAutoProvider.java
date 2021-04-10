package com.oculus.auth.receiver;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LogoutHandlersRunnerAutoProvider extends AbstractProvider<LogoutHandlersRunner> {
    @Override // javax.inject.Provider
    public LogoutHandlersRunner get() {
        return new LogoutHandlersRunner(this);
    }
}
