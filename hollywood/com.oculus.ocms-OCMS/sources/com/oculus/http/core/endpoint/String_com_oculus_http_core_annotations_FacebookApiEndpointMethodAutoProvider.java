package com.oculus.http.core.endpoint;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_core_annotations_FacebookApiEndpointMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return EndpointModule.provideFacebookApiEndpoint();
    }
}
