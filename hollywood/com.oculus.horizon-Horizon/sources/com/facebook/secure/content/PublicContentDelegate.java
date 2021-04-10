package com.facebook.secure.content;

import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class PublicContentDelegate extends DeferredInitAbstractContentProviderNoDIDelegate {
    public PublicContentDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }
}
