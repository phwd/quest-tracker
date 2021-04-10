package com.facebook.secure.content;

import X.Ok;
import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class DeferredInitAbstractContentProviderDelegate extends DeferredInitAbstractContentProviderNoDIDelegate implements Ok {
    public DeferredInitAbstractContentProviderDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }
}
