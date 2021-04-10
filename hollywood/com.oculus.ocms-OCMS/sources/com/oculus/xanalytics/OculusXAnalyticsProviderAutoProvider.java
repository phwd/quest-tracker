package com.oculus.xanalytics;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusXAnalyticsProviderAutoProvider extends AbstractProvider<OculusXAnalyticsProvider> {
    @Override // javax.inject.Provider
    public OculusXAnalyticsProvider get() {
        return new OculusXAnalyticsProvider(this);
    }
}
