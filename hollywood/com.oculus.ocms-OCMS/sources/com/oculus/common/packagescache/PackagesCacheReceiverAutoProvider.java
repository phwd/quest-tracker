package com.oculus.common.packagescache;

import com.facebook.inject.AbstractComponentProvider;

public class PackagesCacheReceiverAutoProvider extends AbstractComponentProvider<PackagesCacheReceiver> {
    public void inject(PackagesCacheReceiver packagesCacheReceiver) {
        PackagesCacheReceiver._UL_staticInjectMe(this, packagesCacheReceiver);
    }

    public boolean equals(Object obj) {
        return obj instanceof PackagesCacheReceiverAutoProvider;
    }
}
