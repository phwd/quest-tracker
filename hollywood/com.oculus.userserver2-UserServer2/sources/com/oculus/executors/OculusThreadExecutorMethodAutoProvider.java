package com.oculus.executors;

import X.AbstractC0029Ba;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class OculusThreadExecutorMethodAutoProvider extends AbstractC0029Ba<OculusThreadExecutor> {
    public final Object get() {
        OculusThreadExecutor oculusThreadExecutor = OculusThreadExecutor.sInstance;
        if (oculusThreadExecutor != null) {
            return oculusThreadExecutor;
        }
        OculusThreadExecutor oculusThreadExecutor2 = new OculusThreadExecutor();
        OculusThreadExecutor.sInstance = oculusThreadExecutor2;
        return oculusThreadExecutor2;
    }
}
