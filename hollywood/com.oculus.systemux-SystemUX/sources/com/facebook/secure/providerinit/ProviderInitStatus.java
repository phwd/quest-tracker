package com.facebook.secure.providerinit;

import android.os.ConditionVariable;

public class ProviderInitStatus {
    private static final ConditionVariable providerVar = new ConditionVariable(true);

    public static void blockProviderInits() {
        providerVar.close();
    }

    public static void unblockProviderInits() {
        providerVar.open();
    }

    public static void waitUntilSafeForInit() {
        providerVar.block();
    }

    public static boolean isSafeForInit() {
        return providerVar.block(-1);
    }
}
