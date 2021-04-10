package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.library.model.App;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$IeWPUv6OibevvY26RpuIt7XZt1Q  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$IeWPUv6OibevvY26RpuIt7XZt1Q implements Predicate {
    public static final /* synthetic */ $$Lambda$IeWPUv6OibevvY26RpuIt7XZt1Q INSTANCE = new $$Lambda$IeWPUv6OibevvY26RpuIt7XZt1Q();

    private /* synthetic */ $$Lambda$IeWPUv6OibevvY26RpuIt7XZt1Q() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return LibraryUtils.appHasPendingUpdate((App) obj);
    }
}
