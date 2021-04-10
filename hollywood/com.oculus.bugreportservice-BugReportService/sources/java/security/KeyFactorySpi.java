package java.security;

import java.security.spec.KeySpec;

public abstract class KeyFactorySpi {
    /* access modifiers changed from: protected */
    public abstract PublicKey engineGeneratePublic(KeySpec keySpec);
}
