package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.auth.receiver.BaseLoginHandler;

@Generated({"By: InjectorProcessor"})
public class BaseLoginHandlerMethodAutoProvider extends AbstractProvider<BaseLoginHandler> {
    @Override // javax.inject.Provider
    public BaseLoginHandler get() {
        return OCMSAppModule.provideBaseLoginHandler(OCMSCredentialsManager._UL__ULSEP_com_oculus_ocms_app_OCMSCredentialsManager_ULSEP_ACCESS_METHOD(this));
    }
}
