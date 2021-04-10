package com.oculus.ocms.locationservices.dumper;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LocationServiceDumperAutoProvider extends AbstractProvider<LocationServiceDumper> {
    @Override // javax.inject.Provider
    public LocationServiceDumper get() {
        return new LocationServiceDumper(this);
    }
}
