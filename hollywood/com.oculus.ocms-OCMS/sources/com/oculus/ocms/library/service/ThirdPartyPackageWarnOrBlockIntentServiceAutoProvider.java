package com.oculus.ocms.library.service;

import com.facebook.inject.AbstractComponentProvider;
import com.facebook.inject.InjectorLike;

public class ThirdPartyPackageWarnOrBlockIntentServiceAutoProvider extends AbstractComponentProvider<ThirdPartyPackageWarnOrBlockIntentService> {
    public void inject(ThirdPartyPackageWarnOrBlockIntentService thirdPartyPackageWarnOrBlockIntentService) {
        ThirdPartyPackageWarnOrBlockIntentService._UL_staticInjectMe((InjectorLike) this, thirdPartyPackageWarnOrBlockIntentService);
    }

    public boolean equals(Object obj) {
        return obj instanceof ThirdPartyPackageWarnOrBlockIntentServiceAutoProvider;
    }
}
