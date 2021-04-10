package com.oculus.app.standalone;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.base.app.ApplicationLike;

@Generated({"By: InjectorProcessor"})
public class ApplicationLikeMethodAutoProvider extends AbstractProvider<ApplicationLike> {
    @Override // javax.inject.Provider
    public ApplicationLike get() {
        return StandaloneApplicationLikeModule.provideApplicationLike(StandaloneApplicationLike._UL__ULSEP_com_oculus_app_standalone_StandaloneApplicationLike_ULSEP_ACCESS_METHOD(this));
    }
}
