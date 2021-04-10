package com.oculus.ocms.library.service;

import com.facebook.inject.AbstractComponentProvider;
import com.facebook.inject.InjectorLike;

public class BinaryCheckUpdateIntentServiceAutoProvider extends AbstractComponentProvider<BinaryCheckUpdateIntentService> {
    public void inject(BinaryCheckUpdateIntentService binaryCheckUpdateIntentService) {
        BinaryCheckUpdateIntentService._UL_staticInjectMe((InjectorLike) this, binaryCheckUpdateIntentService);
    }

    public boolean equals(Object obj) {
        return obj instanceof BinaryCheckUpdateIntentServiceAutoProvider;
    }
}
