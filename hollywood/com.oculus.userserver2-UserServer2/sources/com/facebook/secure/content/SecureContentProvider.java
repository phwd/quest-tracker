package com.facebook.secure.content;

import X.C0221fn;

public abstract class SecureContentProvider extends AbstractContentProvider {
    @Override // X.AbstractC0195ed
    public final boolean onCheckPermissions() {
        return C0221fn.A01(getContext());
    }
}
