package com.facebook.common.android;

import android.location.Geocoder;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class GeocoderMethodAutoProvider extends AbstractProvider<Geocoder> {
    @Override // javax.inject.Provider
    public Geocoder get() {
        return AndroidModule.provideGeocoder(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
