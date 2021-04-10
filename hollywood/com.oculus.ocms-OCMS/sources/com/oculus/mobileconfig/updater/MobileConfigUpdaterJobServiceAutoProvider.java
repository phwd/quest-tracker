package com.oculus.mobileconfig.updater;

import com.facebook.inject.AbstractComponentProvider;

public class MobileConfigUpdaterJobServiceAutoProvider extends AbstractComponentProvider<MobileConfigUpdaterJobService> {
    public void inject(MobileConfigUpdaterJobService mobileConfigUpdaterJobService) {
        MobileConfigUpdaterJobService._UL_staticInjectMe(this, mobileConfigUpdaterJobService);
    }

    public boolean equals(Object obj) {
        return obj instanceof MobileConfigUpdaterJobServiceAutoProvider;
    }
}
