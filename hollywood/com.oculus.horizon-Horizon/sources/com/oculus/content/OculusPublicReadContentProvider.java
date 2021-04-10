package com.oculus.content;

public abstract class OculusPublicReadContentProvider extends OculusFbPermissionsContentProvider {
    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC09361bk
    public boolean onCheckReadOnlyPermissions() {
        return true;
    }
}
