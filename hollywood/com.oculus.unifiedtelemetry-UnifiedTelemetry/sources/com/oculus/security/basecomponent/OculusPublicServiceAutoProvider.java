package com.oculus.security.basecomponent;

import X.I2;

public class OculusPublicServiceAutoProvider extends I2<OculusPublicService> {
    public final boolean equals(Object obj) {
        return obj instanceof OculusPublicServiceAutoProvider;
    }
}
