package com.oculus.content;

import android.os.Binder;

public abstract class AdbContentProvider extends OculusPublicContentProvider {
    private static final int SHELL_UID = 2000;

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.PublicContentProvider, com.facebook.secure.content.AbstractContentProviderNoDI
    public final boolean onCheckPermissions() {
        return Binder.getCallingUid() == 2000;
    }
}
