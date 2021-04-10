package com.oculus.content;

import android.os.Binder;

public abstract class AdbContentProvider extends OculusPublicContentProvider {
    public static final int SHELL_UID = 2000;

    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC0195ed
    public final boolean onCheckPermissions() {
        if (Binder.getCallingUid() == 2000) {
            return true;
        }
        return false;
    }
}
