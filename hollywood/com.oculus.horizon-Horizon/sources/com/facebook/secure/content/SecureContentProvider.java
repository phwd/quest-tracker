package com.facebook.secure.content;

import X.C02870bf;

public abstract class SecureContentProvider extends AbstractContentProvider {
    @Override // X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        return C02870bf.A02(getContext());
    }
}
