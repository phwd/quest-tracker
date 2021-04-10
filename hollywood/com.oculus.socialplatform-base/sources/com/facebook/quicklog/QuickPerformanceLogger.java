package com.facebook.quicklog;

import X.AnonymousClass0V4;
import com.facebook.privacy.datacollection.DisallowSensitive;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface QuickPerformanceLogger {

    @Retention(RetentionPolicy.SOURCE)
    public @interface MarkerFlags {
    }

    long currentMonotonicTimestamp();

    boolean isMarkerOn(int i);

    boolean isMarkerOn(int i, int i2);

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

    void markerEnd(int i, int i2, short s);

    @Deprecated
    void markerEnd(int i, int i2, short s, long j);

    void markerEnd(int i, int i2, short s, long j, TimeUnit timeUnit);

    void markerEnd(int i, short s);

    @Deprecated
    void markerEnd(int i, short s, long j);

    void markerEnd(int i, short s, long j, TimeUnit timeUnit);

    @Deprecated
    void markerPoint(int i, int i2, @EventLevel int i3, String str, @Nullable AnonymousClass0V4 v, long j, int i4);

    void markerPoint(int i, int i2, @EventLevel int i3, String str, @Nullable AnonymousClass0V4 v, long j, TimeUnit timeUnit, int i4);

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

    void markerStartWithCancelPolicy(int i, boolean z, int i2, long j, TimeUnit timeUnit);

    int sampleRateForMarker(int i);
}
