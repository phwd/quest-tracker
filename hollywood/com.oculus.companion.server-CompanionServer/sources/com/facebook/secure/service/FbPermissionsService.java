package com.facebook.secure.service;

import com.facebook.secure.trustedapp.checker.FbPermissionCallerChecker;

public abstract class FbPermissionsService extends PublicBaseServiceWithSwitchOff {
    /* access modifiers changed from: protected */
    public abstract String getFbPermission();

    public FbPermissionsService() {
        addAdditionalPermissionChecker(new FbPermissionCallerChecker(getFbPermission()));
    }
}
