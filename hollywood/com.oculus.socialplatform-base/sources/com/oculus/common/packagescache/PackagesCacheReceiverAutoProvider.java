package com.oculus.common.packagescache;

import X.AnonymousClass0VK;

public class PackagesCacheReceiverAutoProvider extends AnonymousClass0VK<PackagesCacheReceiver> {
    public boolean equals(Object obj) {
        return obj instanceof PackagesCacheReceiverAutoProvider;
    }

    public void inject(PackagesCacheReceiver packagesCacheReceiver) {
        PackagesCacheReceiver._UL_staticInjectMe(this, packagesCacheReceiver);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        PackagesCacheReceiver._UL_staticInjectMe(this, (PackagesCacheReceiver) obj);
    }
}
