package com.facebook.config.application;

import com.facebook.annotations.Generated;
import com.facebook.common.build.SignatureType;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class SignatureTypeMethodAutoProvider extends AbstractProvider<SignatureType> {
    @Override // javax.inject.Provider
    public SignatureType get() {
        return FbAppTypeModule.provideSignatureType(FbAppTypeModule._UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_ACCESS_METHOD(this));
    }
}
