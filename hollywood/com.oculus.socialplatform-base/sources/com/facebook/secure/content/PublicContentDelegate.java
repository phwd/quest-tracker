package com.facebook.secure.content;

import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class PublicContentDelegate extends DeferredInitAbstractContentProviderNoDIDelegate {
    @Override // com.facebook.secure.content.DeferredInitAbstractContentProviderNoDIDelegate
    public final boolean A0N() {
        return true;
    }

    public PublicContentDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }
}
