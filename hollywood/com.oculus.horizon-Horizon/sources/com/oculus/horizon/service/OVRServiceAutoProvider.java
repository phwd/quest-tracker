package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class OVRServiceAutoProvider extends AnonymousClass0J9<OVRService> {
    public boolean equals(Object obj) {
        return obj instanceof OVRServiceAutoProvider;
    }

    public void inject(OVRService oVRService) {
        OVRService._UL_staticInjectMe((AbstractC06640p5) this, oVRService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        OVRService._UL_staticInjectMe((AbstractC06640p5) this, (OVRService) obj);
    }
}
