package com.oculus.license;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class LicenseMethodsAutoProvider extends AbstractProvider<LicenseMethods> {
    @Override // javax.inject.Provider
    public LicenseMethods get() {
        return new LicenseMethods(ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
