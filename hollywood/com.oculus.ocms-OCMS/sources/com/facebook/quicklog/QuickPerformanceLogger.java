package com.facebook.quicklog;

import com.facebook.base.lwperf.perfstats.PerfStats;
import com.facebook.privacy.datacollection.DisallowSensitive;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface QuickPerformanceLogger {
    public static final long AUTO_SET_TIMESTAMP = -1;
    public static final int COLLECT_METADATA = 1;
    public static final int DEFAULT_LEVEL = 7;
    public static final int DUMMY_INSTANCE_KEY = 0;
    public static final int FLAG_CHECK_FOR_FOREGROUND_LAUNCH = 1;
    public static final int FLAG_DEFAULT = 0;
    public static final int FLAG_SKIP_METADATA = 8;
    public static final int FLAG_USER_FLOW = 2;
    public static final long IGNORE_TIMESTAMP = -1;
    public static final int NO_METADATA = 0;

    public interface Builder {
        QuickPerformanceLogger build();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MarkerFlags {
    }

    void clearMarkerAllowListTags(int i);

    void clearMarkerAllowListTags(int i, int i2);

    @Deprecated
    void clearModuleTags(short s);

    long currentMonotonicTimestamp();

    long currentMonotonicTimestampNanos();

    void dropAllInstancesOfMarker(int i);

    void dropAllInstancesOfMarker(int i, int i2);

    void endAllInstancesOfMarker(int i, short s);

    void endAllInstancesOfMarker(int i, short s, int i2);

    void endAllMarkers(short s, boolean z);

    boolean isMarkerOn(int i);

    boolean isMarkerOn(int i, int i2);

    void markEvent(int i, String str, @EventLevel int i2);

    EventBuilder markEventBuilder(int i, String str);

    void markJoinRequestForE2E(int i, int i2, String str);

    void markJoinRequestForE2E(int i, int i2, String str, long j, TimeUnit timeUnit);

    void markJoinResponseForE2E(int i, int i2, String str);

    void markJoinResponseForE2E(int i, int i2, String str, long j, TimeUnit timeUnit);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, double d);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, int i3);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, long j);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, String str2);

    void markerAnnotate(int i, int i2, String str, boolean z);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, double[] dArr);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, int[] iArr);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, long[] jArr);

    @DisallowSensitive
    void markerAnnotate(int i, int i2, String str, String[] strArr);

    void markerAnnotate(int i, int i2, String str, boolean[] zArr);

    @DisallowSensitive
    void markerAnnotate(int i, String str, double d);

    @DisallowSensitive
    void markerAnnotate(int i, String str, int i2);

    @DisallowSensitive
    void markerAnnotate(int i, String str, long j);

    @DisallowSensitive
    void markerAnnotate(int i, String str, String str2);

    void markerAnnotate(int i, String str, boolean z);

    @DisallowSensitive
    void markerAnnotate(int i, String str, double[] dArr);

    @DisallowSensitive
    void markerAnnotate(int i, String str, int[] iArr);

    @DisallowSensitive
    void markerAnnotate(int i, String str, long[] jArr);

    @DisallowSensitive
    void markerAnnotate(int i, String str, String[] strArr);

    void markerAnnotate(int i, String str, boolean[] zArr);

    @Deprecated
    void markerCancel(int i);

    @Deprecated
    void markerCancel(int i, int i2);

    @Deprecated
    void markerCancel(int i, int i2, short s);

    @Deprecated
    void markerCancel(int i, short s);

    void markerDrop(int i);

    void markerDrop(int i, int i2);

    void markerDropForUserFlow(int i, int i2);

    void markerEnd(int i, int i2, short s);

    @Deprecated
    void markerEnd(int i, int i2, short s, long j);

    void markerEnd(int i, int i2, short s, long j, TimeUnit timeUnit);

    void markerEnd(int i, short s);

    @Deprecated
    void markerEnd(int i, short s, long j);

    void markerEnd(int i, short s, long j, TimeUnit timeUnit);

    void markerEndAtPoint(int i, int i2, short s, String str);

    void markerEndAtPoint(int i, short s, String str);

    void markerEndAtPointForUserFlow(int i, int i2, short s, String str);

    void markerEndForUserFlow(int i, int i2, short s);

    void markerGenerate(int i, short s, int i2);

    void markerGenerateWithAnnotations(int i, short s, int i2, Map<String, String> map);

    @Deprecated
    void markerPoint(int i, int i2, @EventLevel int i3, String str, @Nullable PointData pointData, long j, int i4);

    void markerPoint(int i, int i2, @EventLevel int i3, String str, @Nullable PointData pointData, long j, TimeUnit timeUnit, int i4);

    void markerPoint(int i, int i2, String str);

    @Deprecated
    void markerPoint(int i, int i2, String str, long j);

    void markerPoint(int i, int i2, String str, long j, TimeUnit timeUnit);

    @DisallowSensitive
    void markerPoint(int i, int i2, String str, @Nullable String str2);

    @Deprecated
    void markerPoint(int i, int i2, String str, @Nullable String str2, long j);

    @Deprecated
    void markerPoint(int i, int i2, String str, @Nullable String str2, long j, int i3);

    void markerPoint(int i, int i2, String str, @Nullable String str2, long j, TimeUnit timeUnit);

    void markerPoint(int i, int i2, String str, @Nullable String str2, long j, TimeUnit timeUnit, int i3);

    void markerPoint(int i, String str);

    @Deprecated
    void markerPoint(int i, String str, long j);

    void markerPoint(int i, String str, long j, TimeUnit timeUnit);

    @DisallowSensitive
    void markerPoint(int i, String str, @Nullable String str2);

    @DisallowSensitive
    @Deprecated
    void markerPoint(int i, String str, @Nullable String str2, long j);

    void markerPoint(int i, String str, @Nullable String str2, long j, TimeUnit timeUnit);

    void markerStart(int i);

    void markerStart(int i, int i2);

    @Deprecated
    void markerStart(int i, int i2, long j);

    @Deprecated
    void markerStart(int i, int i2, long j, int i3);

    void markerStart(int i, int i2, long j, TimeUnit timeUnit);

    void markerStart(int i, int i2, long j, TimeUnit timeUnit, int i3);

    void markerStart(int i, int i2, String str, String str2);

    @Deprecated
    void markerStart(int i, int i2, String str, String str2, long j);

    void markerStart(int i, int i2, String str, String str2, long j, TimeUnit timeUnit);

    void markerStart(int i, int i2, boolean z);

    void markerStart(int i, String str, String str2);

    @Deprecated
    void markerStart(int i, String str, String str2, long j);

    void markerStart(int i, String str, String str2, long j, TimeUnit timeUnit);

    void markerStart(int i, boolean z);

    void markerStartForE2E(int i, int i2, String str);

    void markerStartForLegacy(int i, int i2, long j, TimeUnit timeUnit, PerfStats perfStats);

    @Deprecated
    void markerStartForLegacy(int i, long j, PerfStats perfStats);

    void markerStartForLegacy(int i, long j, TimeUnit timeUnit, PerfStats perfStats);

    void markerStartForUserFlow(int i, int i2, long j, TimeUnit timeUnit, boolean z);

    void markerStartForUserFlow(int i, int i2, boolean z, long j);

    void markerStartWithCancelPolicy(int i, boolean z, int i2, long j, TimeUnit timeUnit);

    @DisallowSensitive
    @Deprecated
    void markerTag(int i, int i2, String str);

    @DisallowSensitive
    @Deprecated
    void markerTag(int i, String str);

    void markerTagForLegacy(int i, int i2, String str, String str2, boolean z);

    void markersEndAllOnTag(Object obj, short s);

    @Deprecated
    void moduleTag(short s, String str);

    int sampleRateForMarker(int i);

    void setAlwaysOnSampleRate(int i, int i2);

    void setMarkerAllowListTags(int i, int i2, Collection<? extends Object> collection);

    void setMarkerAllowListTags(int i, Collection<? extends Object> collection);

    void updateListenerMarkers();

    MarkerEditor withMarker(int i);

    MarkerEditor withMarker(int i, int i2);
}
