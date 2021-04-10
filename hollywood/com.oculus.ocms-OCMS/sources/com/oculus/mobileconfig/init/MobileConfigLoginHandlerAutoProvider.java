package com.oculus.mobileconfig.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MobileConfigLoginHandlerAutoProvider extends AbstractProvider<MobileConfigLoginHandler> {
    @Override // javax.inject.Provider
    public MobileConfigLoginHandler get() {
        return new MobileConfigLoginHandler(this);
    }
}
