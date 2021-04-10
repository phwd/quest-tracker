package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class PermissionManagerAutoProvider extends AbstractProvider<PermissionManager> {
    @Override // javax.inject.Provider
    public PermissionManager get() {
        return new PermissionManager(AndroidModule._UL__ULSEP_android_content_pm_PackageManager_ULSEP_ACCESS_METHOD(this));
    }
}
