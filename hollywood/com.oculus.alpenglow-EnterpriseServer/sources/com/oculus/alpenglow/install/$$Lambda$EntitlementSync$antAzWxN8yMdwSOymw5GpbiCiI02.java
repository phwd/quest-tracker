package com.oculus.alpenglow.install;

import com.oculus.alpenglow.database.AppSource;
import com.oculus.alpenglow.database.Application;
import java.util.function.Predicate;

/* renamed from: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$antAzWxN8yMdwSOymw5GpbiCiI02  reason: invalid class name */
public final /* synthetic */ class $$Lambda$EntitlementSync$antAzWxN8yMdwSOymw5GpbiCiI02 implements Predicate {
    public static final /* synthetic */ $$Lambda$EntitlementSync$antAzWxN8yMdwSOymw5GpbiCiI02 INSTANCE = new $$Lambda$EntitlementSync$antAzWxN8yMdwSOymw5GpbiCiI02();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        if (((Application) obj).appSource != AppSource.ENTITLEMENT_BASED) {
            return true;
        }
        return false;
    }
}
