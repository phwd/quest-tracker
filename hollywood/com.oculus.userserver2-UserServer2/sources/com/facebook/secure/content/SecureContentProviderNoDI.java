package com.facebook.secure.content;

import X.AbstractC0195ed;
import X.C0221fn;

public abstract class SecureContentProviderNoDI extends AbstractC0195ed {
    @Override // X.AbstractC0195ed
    public final boolean onCheckPermissions() {
        return C0221fn.A01(getContext());
    }
}
