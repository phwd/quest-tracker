package com.facebook.secure.content;

import com.facebook.secure.providerinit.DeferredInitContentProvider;
import com.facebook.secure.trustedapp.TrustedApp;

public abstract class SecureContentDelegate extends DeferredInitAbstractContentProviderDelegate {
    public SecureContentDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.DeferredInitAbstractContentProviderNoDIDelegate
    public final boolean onCheckPermissions() {
        return TrustedApp.checkSameSignatureCaller(getContext());
    }
}
