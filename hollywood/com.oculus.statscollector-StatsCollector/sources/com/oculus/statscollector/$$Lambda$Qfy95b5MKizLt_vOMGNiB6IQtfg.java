package com.oculus.statscollector;

import com.android.os.StatsLog;
import java.util.function.Function;

/* renamed from: com.oculus.statscollector.-$$Lambda$Qfy95b5MKizLt_vOMGNiB6IQtfg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$Qfy95b5MKizLt_vOMGNiB6IQtfg implements Function {
    public static final /* synthetic */ $$Lambda$Qfy95b5MKizLt_vOMGNiB6IQtfg INSTANCE = new $$Lambda$Qfy95b5MKizLt_vOMGNiB6IQtfg();

    private /* synthetic */ $$Lambda$Qfy95b5MKizLt_vOMGNiB6IQtfg() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Long.valueOf(((StatsLog.EventMetricData) obj).getElapsedTimestampNanos());
    }
}
