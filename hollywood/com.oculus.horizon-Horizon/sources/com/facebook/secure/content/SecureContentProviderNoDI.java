package com.facebook.secure.content;

import X.AbstractC09361bk;
import X.C02870bf;

public abstract class SecureContentProviderNoDI extends AbstractC09361bk {
    @Override // X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        return C02870bf.A02(getContext());
    }
}
