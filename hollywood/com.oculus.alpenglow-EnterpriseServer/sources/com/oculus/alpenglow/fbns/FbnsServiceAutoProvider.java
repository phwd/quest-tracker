package com.oculus.alpenglow.fbns;

import X.AbstractC01770Lm;

public class FbnsServiceAutoProvider extends AbstractC01770Lm<FbnsService> {
    public boolean equals(Object obj) {
        return obj instanceof FbnsServiceAutoProvider;
    }

    public void inject(FbnsService fbnsService) {
        FbnsService._UL_staticInjectMe(this, fbnsService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        FbnsService._UL_staticInjectMe(this, (FbnsService) obj);
    }
}
