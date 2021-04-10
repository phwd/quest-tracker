package com.oculus.ossdk.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.os.Controllers;

@Generated({"By: InjectorProcessor"})
public class ControllersMethodAutoProvider extends AbstractProvider<Controllers> {
    @Override // javax.inject.Provider
    public Controllers get() {
        return OsSdkModule.provideControllers();
    }
}
