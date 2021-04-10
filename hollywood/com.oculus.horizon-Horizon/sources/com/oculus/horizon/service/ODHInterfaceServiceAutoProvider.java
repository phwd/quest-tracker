package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class ODHInterfaceServiceAutoProvider extends AnonymousClass0J9<ODHInterfaceService> {
    public boolean equals(Object obj) {
        return obj instanceof ODHInterfaceServiceAutoProvider;
    }

    public void inject(ODHInterfaceService oDHInterfaceService) {
        ODHInterfaceService._UL_staticInjectMe((AbstractC06640p5) this, oDHInterfaceService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        ODHInterfaceService._UL_staticInjectMe((AbstractC06640p5) this, (ODHInterfaceService) obj);
    }
}
