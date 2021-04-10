package com.oculus.executors;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusThreadExecutorMethodAutoProvider extends AbstractProvider<OculusThreadExecutor> {
    @Override // javax.inject.Provider
    public OculusThreadExecutor get() {
        return ExecutorsModule.provideOculusThreadExecutor();
    }
}
