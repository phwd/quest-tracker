package com.oculus.horizon.platform;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import X.C003108z;
import X.C01010Iv;
import com.facebook.annotations.Generated;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.http.core.ApiModule;
import com.oculus.libraryapi.OVRLibraryModule;

@Generated({"By: InjectorProcessor"})
public class PresenceManagerAutoProvider extends AnonymousClass0J3<PresenceManager> {
    public PresenceManager get() {
        return new PresenceManager(this, C003108z.A00(this), ApiModule.A0C(this), PlatformContext._UL__ULSEP_com_oculus_horizon_platform_PlatformContext_ULSEP_ACCESS_METHOD(this), (PlatformPluginManager) AnonymousClass117.A00(160, this), (UserProfileHelper) AnonymousClass117.A00(68, this), OVRLibraryModule.A00(this), new C01010Iv(8, this));
    }
}
