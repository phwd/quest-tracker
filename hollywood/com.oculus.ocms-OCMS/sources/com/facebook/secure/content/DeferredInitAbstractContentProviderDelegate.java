package com.facebook.secure.content;

import android.content.Context;
import com.facebook.inject.InjectableComponentWithContext;
import com.facebook.secure.providerinit.DeferredInitContentProvider;

public abstract class DeferredInitAbstractContentProviderDelegate extends DeferredInitAbstractContentProviderNoDIDelegate implements InjectableComponentWithContext {
    public DeferredInitAbstractContentProviderDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }

    @Override // com.facebook.inject.InjectableComponentWithContext, com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
    public Context getContext() {
        return super.getContext();
    }
}
