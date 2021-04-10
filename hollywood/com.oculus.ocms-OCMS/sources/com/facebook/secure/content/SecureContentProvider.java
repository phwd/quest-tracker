package com.facebook.secure.content;

import com.facebook.secure.trustedapp.TrustedApp;

public abstract class SecureContentProvider extends AbstractContentProvider {
    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public final boolean onCheckPermissions() {
        return TrustedApp.checkSameSignatureCaller(getContext());
    }
}
