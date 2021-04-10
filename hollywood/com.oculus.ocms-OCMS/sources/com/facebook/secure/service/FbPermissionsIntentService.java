package com.facebook.secure.service;

import com.facebook.secure.trustedapp.checker.FbPermissionCallerChecker;

public abstract class FbPermissionsIntentService extends PublicBaseIntentServiceWithSwitchOff {
    /* access modifiers changed from: protected */
    public abstract String getFbPermission();

    public FbPermissionsIntentService(String str) {
        super(str);
        addAdditionalPermissionChecker(new FbPermissionCallerChecker(getFbPermission()));
    }
}
