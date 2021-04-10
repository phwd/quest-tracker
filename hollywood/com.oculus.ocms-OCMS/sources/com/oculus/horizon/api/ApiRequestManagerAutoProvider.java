package com.oculus.horizon.api;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ApiRequestManagerAutoProvider extends AbstractProvider<ApiRequestManager> {
    @Override // javax.inject.Provider
    public ApiRequestManager get() {
        return new ApiRequestManager(ApiDispatcher._UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_ACCESS_METHOD(this));
    }
}
