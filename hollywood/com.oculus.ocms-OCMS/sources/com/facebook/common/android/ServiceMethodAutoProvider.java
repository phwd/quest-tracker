package com.facebook.common.android;

import android.app.Service;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ServiceMethodAutoProvider extends AbstractProvider<Service> {
    @Override // javax.inject.Provider
    public Service get() {
        return AndroidModule.provideService(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
