package com.facebook.secure.content;

import android.content.Context;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;
import javax.annotation.Nullable;

public abstract class FbPermissionsContentProvider extends AbstractContentProviderNoDI {
    /* access modifiers changed from: protected */
    public abstract String getFbPermission();

    /* access modifiers changed from: protected */
    @Nullable
    public Reporter getReporter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getReadOnlyFbPermission() {
        return getFbPermission();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckPermissions() {
        return onCheckPermissionsHelper(getFbPermission());
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckReadOnlyPermissions() {
        return onCheckPermissionsHelper(getReadOnlyFbPermission());
    }

    private boolean onCheckPermissionsHelper(String str) {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        return TrustedApp.checkCallerHasFbPermissions(str, context, null, getReporter());
    }
}
