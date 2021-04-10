package com.oculus.nux.ota;

import java.util.function.Predicate;

/* renamed from: com.oculus.nux.ota.-$$Lambda$HighPriAppsUpdateTracker$kvm64OHogaE4Ahtc9q7cuqI-Wyk  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$HighPriAppsUpdateTracker$kvm64OHogaE4Ahtc9q7cuqIWyk implements Predicate {
    public static final /* synthetic */ $$Lambda$HighPriAppsUpdateTracker$kvm64OHogaE4Ahtc9q7cuqIWyk INSTANCE = new $$Lambda$HighPriAppsUpdateTracker$kvm64OHogaE4Ahtc9q7cuqIWyk();

    private /* synthetic */ $$Lambda$HighPriAppsUpdateTracker$kvm64OHogaE4Ahtc9q7cuqIWyk() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((HighPriApp) obj).getIsInstalled();
    }
}
