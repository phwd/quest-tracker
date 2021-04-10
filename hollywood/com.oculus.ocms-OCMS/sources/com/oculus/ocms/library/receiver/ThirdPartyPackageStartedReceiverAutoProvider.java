package com.oculus.ocms.library.receiver;

import com.facebook.inject.AbstractComponentProvider;
import com.facebook.inject.InjectorLike;

public class ThirdPartyPackageStartedReceiverAutoProvider extends AbstractComponentProvider<ThirdPartyPackageStartedReceiver> {
    public void inject(ThirdPartyPackageStartedReceiver thirdPartyPackageStartedReceiver) {
        ThirdPartyPackageStartedReceiver._UL_staticInjectMe((InjectorLike) this, thirdPartyPackageStartedReceiver);
    }

    public boolean equals(Object obj) {
        return obj instanceof ThirdPartyPackageStartedReceiverAutoProvider;
    }
}
