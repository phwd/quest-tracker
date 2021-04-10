package com.oculus.statscollector;

import com.android.os.StatsLog;
import java.util.function.Function;

/* renamed from: com.oculus.statscollector.-$$Lambda$StatsdManager$omM_B0D4YUbz9-Ce0i6d23q9t_w  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$StatsdManager$omM_B0D4YUbz9Ce0i6d23q9t_w implements Function {
    public static final /* synthetic */ $$Lambda$StatsdManager$omM_B0D4YUbz9Ce0i6d23q9t_w INSTANCE = new $$Lambda$StatsdManager$omM_B0D4YUbz9Ce0i6d23q9t_w();

    private /* synthetic */ $$Lambda$StatsdManager$omM_B0D4YUbz9Ce0i6d23q9t_w() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((StatsLog.StatsLogReport) obj).getEventMetrics().getDataList().stream();
    }
}
