package com.oculus.content;

public abstract class OculusPublicReadContentProvider extends OculusFbPermissionsContentProvider {
    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public boolean onCheckReadOnlyPermissions() {
        return true;
    }
}
