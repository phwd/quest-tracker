package com.oculus.horizon.api;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class ApiDispatcherAutoProvider extends AbstractProvider<ApiDispatcher> {
    @Override // javax.inject.Provider
    public ApiDispatcher get() {
        return new ApiDispatcher(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
