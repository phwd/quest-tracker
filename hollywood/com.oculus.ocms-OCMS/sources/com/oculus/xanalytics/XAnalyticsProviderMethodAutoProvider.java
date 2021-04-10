package com.oculus.xanalytics;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.xanalytics.XAnalyticsProvider;

@Generated({"By: InjectorProcessor"})
public class XAnalyticsProviderMethodAutoProvider extends AbstractProvider<XAnalyticsProvider> {
    @Override // javax.inject.Provider
    public XAnalyticsProvider get() {
        return OculusXAnalyticsModule.provideXAnalyticsProvider(OculusXAnalyticsProvider._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_ACCESS_METHOD(this));
    }
}
