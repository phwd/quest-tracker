package com.oculus.ocms.defaultapps;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class DefaultAppsMethodsAutoProvider extends AbstractProvider<DefaultAppsMethods> {
    @Override // javax.inject.Provider
    public DefaultAppsMethods get() {
        return new DefaultAppsMethods(this, ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
