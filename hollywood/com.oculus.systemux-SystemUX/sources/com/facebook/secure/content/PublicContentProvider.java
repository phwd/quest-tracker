package com.facebook.secure.content;

public abstract class PublicContentProvider extends AbstractContentProviderNoDI {
    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckPermissions() {
        return true;
    }
}
