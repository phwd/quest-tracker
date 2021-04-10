package com.facebook.secure.content;

import com.facebook.secure.trustedapp.TrustedApp;

public abstract class SecureContentProviderNoDI extends AbstractContentProviderNoDI {
    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public final boolean onCheckPermissions() {
        return TrustedApp.checkSameSignatureCaller(getContext());
    }
}
