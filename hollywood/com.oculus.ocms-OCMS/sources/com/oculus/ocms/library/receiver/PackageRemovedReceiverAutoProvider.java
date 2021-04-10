package com.oculus.ocms.library.receiver;

import com.facebook.inject.AbstractComponentProvider;
import com.facebook.inject.InjectorLike;

public class PackageRemovedReceiverAutoProvider extends AbstractComponentProvider<PackageRemovedReceiver> {
    public void inject(PackageRemovedReceiver packageRemovedReceiver) {
        PackageRemovedReceiver._UL_staticInjectMe((InjectorLike) this, packageRemovedReceiver);
    }

    public boolean equals(Object obj) {
        return obj instanceof PackageRemovedReceiverAutoProvider;
    }
}
