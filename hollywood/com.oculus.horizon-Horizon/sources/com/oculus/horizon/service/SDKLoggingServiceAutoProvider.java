package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class SDKLoggingServiceAutoProvider extends AnonymousClass0J9<SDKLoggingService> {
    public boolean equals(Object obj) {
        return obj instanceof SDKLoggingServiceAutoProvider;
    }

    public void inject(SDKLoggingService sDKLoggingService) {
        SDKLoggingService._UL_staticInjectMe((AbstractC06640p5) this, sDKLoggingService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        SDKLoggingService._UL_staticInjectMe((AbstractC06640p5) this, (SDKLoggingService) obj);
    }
}
