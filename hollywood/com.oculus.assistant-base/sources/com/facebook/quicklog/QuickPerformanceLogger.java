package com.facebook.quicklog;

import X.IY;
import X.Ie;
import java.util.concurrent.TimeUnit;

public interface QuickPerformanceLogger {
    long currentMonotonicTimestamp();

    boolean isMarkerOn(int i);

    boolean isMarkerOn(int i, int i2);

    void markerAnnotate(int i, int i2, String str, double d);

    void markerAnnotate(int i, int i2, String str, int i3);

    void markerAnnotate(int i, int i2, String str, long j);

    void markerAnnotate(int i, int i2, String str, String str2);

    void markerAnnotate(int i, int i2, String str, boolean z);

    void markerAnnotate(int i, int i2, String str, double[] dArr);

    void markerAnnotate(int i, int i2, String str, int[] iArr);

    void markerAnnotate(int i, int i2, String str, long[] jArr);

    void markerAnnotate(int i, int i2, String str, String[] strArr);

    void markerAnnotate(int i, int i2, String str, boolean[] zArr);

    void markerAnnotate(int i, String str, double d);

    void markerAnnotate(int i, String str, int i2);

    void markerAnnotate(int i, String str, long j);

    void markerAnnotate(int i, String str, String str2);

    void markerAnnotate(int i, String str, boolean z);

    void markerAnnotate(int i, String str, double[] dArr);

    void markerAnnotate(int i, String str, int[] iArr);

    void markerAnnotate(int i, String str, long[] jArr);

    void markerAnnotate(int i, String str, String[] strArr);

    void markerAnnotate(int i, String str, boolean[] zArr);

    void markerCancel(int i);

    void markerCancel(int i, int i2);

    void markerCancel(int i, int i2, short s);

    void markerCancel(int i, short s);

    void markerEnd(int i, int i2, short s);

    void markerEnd(int i, int i2, short s, long j);

    void markerEnd(int i, int i2, short s, long j, TimeUnit timeUnit);

    void markerEnd(int i, short s);

    void markerEnd(int i, short s, long j);

    void markerEnd(int i, short s, long j, TimeUnit timeUnit);

    void markerPoint(int i, int i2, int i3, String str, Ie ie, long j, int i4);

    void markerPoint(int i, int i2, int i3, String str, Ie ie, long j, TimeUnit timeUnit, int i4);

    void markerPoint(int i, int i2, String str);

    void markerPoint(int i, int i2, String str, long j);

    void markerPoint(int i, int i2, String str, long j, TimeUnit timeUnit);

    void markerPoint(int i, int i2, String str, String str2);

    void markerPoint(int i, int i2, String str, String str2, long j);

    void markerPoint(int i, int i2, String str, String str2, long j, int i3);

    void markerPoint(int i, int i2, String str, String str2, long j, TimeUnit timeUnit);

    void markerPoint(int i, int i2, String str, String str2, long j, TimeUnit timeUnit, int i3);

    void markerPoint(int i, String str);

    void markerPoint(int i, String str, long j);

    void markerPoint(int i, String str, long j, TimeUnit timeUnit);

    void markerPoint(int i, String str, String str2);

    void markerPoint(int i, String str, String str2, long j);

    void markerPoint(int i, String str, String str2, long j, TimeUnit timeUnit);

    void markerStart(int i);

    void markerStart(int i, int i2);

    void markerStart(int i, int i2, long j);

    void markerStart(int i, int i2, long j, int i3);

    void markerStart(int i, int i2, long j, TimeUnit timeUnit);

    void markerStart(int i, int i2, long j, TimeUnit timeUnit, int i3);

    void markerStart(int i, int i2, String str, String str2);

    void markerStart(int i, int i2, String str, String str2, long j);

    void markerStart(int i, int i2, String str, String str2, long j, TimeUnit timeUnit);

    void markerStart(int i, int i2, boolean z);

    void markerStart(int i, String str, String str2);

    void markerStart(int i, String str, String str2, long j);

    void markerStart(int i, String str, String str2, long j, TimeUnit timeUnit);

    void markerStart(int i, boolean z);

    void markerStartWithCancelPolicy(int i, boolean z, int i2, long j, TimeUnit timeUnit);

    int sampleRateForMarker(int i);

    IY withMarker(int i, int i2);
}
