package com.facebook.secure.content;

import X.AbstractC09361bk;
import X.AnonymousClass0b1;
import X.C02870bf;
import android.content.Context;
import javax.annotation.Nullable;

public abstract class FbPermissionsContentProvider extends AbstractC09361bk {
    public abstract String getFbPermission();

    @Nullable
    public AnonymousClass0b1 getReporter() {
        return null;
    }

    private boolean onCheckPermissionsHelper(String str) {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        return C02870bf.A05(str, context, null, null);
    }

    public String getReadOnlyFbPermission() {
        return getFbPermission();
    }

    @Override // X.AbstractC09361bk
    public boolean onCheckPermissions() {
        return onCheckPermissionsHelper(getFbPermission());
    }

    @Override // X.AbstractC09361bk
    public boolean onCheckReadOnlyPermissions() {
        return onCheckPermissionsHelper(getFbPermission());
    }
}
