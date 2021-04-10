package com.oculus.horizon.api;

import X.AnonymousClass0J3;
import X.C003108z;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class ApiDispatcherAutoProvider extends AnonymousClass0J3<ApiDispatcher> {
    public ApiDispatcher get() {
        return new ApiDispatcher(C003108z.A00(this), ApiModule.A0C(this));
    }
}
