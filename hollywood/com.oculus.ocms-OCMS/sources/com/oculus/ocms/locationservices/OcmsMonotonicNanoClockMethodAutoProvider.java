package com.oculus.ocms.locationservices;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.ocms.locationservices.LocationServiceModule;

@Generated({"By: InjectorProcessor"})
public class OcmsMonotonicNanoClockMethodAutoProvider extends AbstractProvider<LocationServiceModule.OcmsMonotonicNanoClock> {
    @Override // javax.inject.Provider
    public LocationServiceModule.OcmsMonotonicNanoClock get() {
        return LocationServiceModule.provideMonotonicNanoClock();
    }
}
