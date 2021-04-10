package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_OculusApiEndpointMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return OCMSAppModule.provideOculusApiEndpoint();
    }
}
