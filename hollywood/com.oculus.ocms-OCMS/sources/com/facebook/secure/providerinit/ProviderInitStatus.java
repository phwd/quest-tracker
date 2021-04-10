package com.facebook.secure.providerinit;

import android.os.ConditionVariable;
import java.util.HashMap;
import java.util.Map;

public class ProviderInitStatus {
    public static final String FIREBASE_INIT_OPERATION_NAME = "firebase_init";
    private static final Map<String, ConditionVariable> namedOperations = new HashMap();
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

    public static void addNamedOperation(String str) {
        synchronized (namedOperations) {
            if (!namedOperations.containsKey(str)) {
                namedOperations.put(str, new ConditionVariable());
            }
        }
    }

    public static void completeNamedOperation(String str) {
        ConditionVariable conditionVariable;
        synchronized (namedOperations) {
            conditionVariable = namedOperations.get(str);
        }
        if (conditionVariable != null) {
            conditionVariable.open();
        }
    }

    public static boolean waitForNamedOperation(String str) {
        ConditionVariable conditionVariable;
        synchronized (namedOperations) {
            conditionVariable = namedOperations.get(str);
        }
        if (conditionVariable == null) {
            return false;
        }
        conditionVariable.block();
        return true;
    }
}
