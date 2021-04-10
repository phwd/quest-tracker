package com.oculus.horizon.provider;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class ProfileContentProviderAutoProvider extends AnonymousClass0J9<ProfileContentProvider> {
    public boolean equals(Object obj) {
        return obj instanceof ProfileContentProviderAutoProvider;
    }

    public void inject(ProfileContentProvider profileContentProvider) {
        ProfileContentProvider._UL_staticInjectMe((AbstractC06640p5) this, profileContentProvider);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        ProfileContentProvider._UL_staticInjectMe((AbstractC06640p5) this, (ProfileContentProvider) obj);
    }
}
