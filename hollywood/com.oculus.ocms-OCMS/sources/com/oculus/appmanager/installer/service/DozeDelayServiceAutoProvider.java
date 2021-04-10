package com.oculus.appmanager.installer.service;

import com.facebook.inject.AbstractComponentProvider;

public class DozeDelayServiceAutoProvider extends AbstractComponentProvider<DozeDelayService> {
    public void inject(DozeDelayService dozeDelayService) {
        DozeDelayService._UL_staticInjectMe(this, dozeDelayService);
    }

    public boolean equals(Object obj) {
        return obj instanceof DozeDelayServiceAutoProvider;
    }
}
