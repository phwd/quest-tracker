package com.oculus.content;

public abstract class OculusPublicReadContentProvider extends OculusFbPermissionsContentProvider {
    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.FbPermissionsContentProvider, com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckReadOnlyPermissions() {
        return true;
    }
}
