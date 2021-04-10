package com.facebook.secure.trustedapp.checker;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;

public class FbPermissionCallerChecker extends CheckerBase {
    private String mFbPermission;

    public FbPermissionCallerChecker(String str) {
        this.mFbPermission = str;
    }

    @Override // com.facebook.secure.trustedapp.checker.Checker
    public boolean shouldAllowIntent(Context context, Object obj, Intent intent, Reporter reporter) {
        return TrustedApp.checkCallerHasFbPermissions(this.mFbPermission, context, intent, reporter);
    }
}
