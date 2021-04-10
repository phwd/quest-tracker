package com.android.org.bouncycastle.jcajce.util;

import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Provider;
import java.security.Security;

public class BCJcaJceHelper extends ProviderJcaJceHelper {
    private static volatile Provider bcProvider;

    private static synchronized Provider getBouncyCastleProvider() {
        synchronized (BCJcaJceHelper.class) {
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null) {
                return Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
            } else if (bcProvider != null) {
                return bcProvider;
            } else {
                bcProvider = new BouncyCastleProvider();
                return bcProvider;
            }
        }
    }

    public BCJcaJceHelper() {
        super(getBouncyCastleProvider());
    }
}
