package com.facebook.secure.content;

import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class PublicContentDelegate extends DeferredInitAbstractContentProviderNoDIDelegate {
    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.DeferredInitAbstractContentProviderNoDIDelegate
    public boolean onCheckPermissions() {
        return true;
    }

    public PublicContentDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }
}
