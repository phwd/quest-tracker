package com.oculus.content;

public abstract class OculusPublicReadContentProvider extends OculusFbPermissionsContentProvider {
    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AbstractC0195ed
    public boolean onCheckReadOnlyPermissions() {
        return true;
    }
}
