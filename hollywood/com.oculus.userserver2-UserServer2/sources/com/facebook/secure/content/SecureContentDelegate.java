package com.facebook.secure.content;

import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class SecureContentDelegate extends DeferredInitAbstractContentProviderDelegate {
    public SecureContentDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }
}
