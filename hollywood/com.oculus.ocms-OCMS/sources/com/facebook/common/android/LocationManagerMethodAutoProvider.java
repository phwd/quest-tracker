package com.facebook.common.android;

import android.location.LocationManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class LocationManagerMethodAutoProvider extends AbstractProvider<LocationManager> {
    @Override // javax.inject.Provider
    public LocationManager get() {
        return AndroidModule.provideLocationManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
