package com.facebook.secure.receiver;

import com.facebook.secure.trustedapp.checker.FbPermissionCallerChecker;

public abstract class FBPermissionSecureBroadcastReceiver extends SecureBroadcastReceiver {
    public abstract String getFbPermission();

    public FBPermissionSecureBroadcastReceiver() {
        addAdditionalPermissionChecker(new FbPermissionCallerChecker(getFbPermission()));
    }
}
