package com.oculus.ocms.locationservices;

import com.facebook.inject.AbstractComponentProvider;

public class LocationServiceAutoProvider extends AbstractComponentProvider<LocationService> {
    public void inject(LocationService locationService) {
        LocationService._UL_staticInjectMe(this, locationService);
    }

    public boolean equals(Object obj) {
        return obj instanceof LocationServiceAutoProvider;
    }
}
