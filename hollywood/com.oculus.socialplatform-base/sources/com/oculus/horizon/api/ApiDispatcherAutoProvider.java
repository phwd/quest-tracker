package com.oculus.horizon.api;

import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class ApiDispatcherAutoProvider extends AnonymousClass0VG<ApiDispatcher> {
    public ApiDispatcher get() {
        return new ApiDispatcher(C00610Hs.A00(this), ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
