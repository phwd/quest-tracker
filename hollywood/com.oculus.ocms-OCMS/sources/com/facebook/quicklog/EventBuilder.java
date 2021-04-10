package com.facebook.quicklog;

public interface EventBuilder {
    EventBuilder annotate(String str, double d);

    EventBuilder annotate(String str, int i);

    EventBuilder annotate(String str, long j);

    EventBuilder annotate(String str, String str2);

    EventBuilder annotate(String str, boolean z);

    EventBuilder annotate(String str, double[] dArr);

    EventBuilder annotate(String str, int[] iArr);

    EventBuilder annotate(String str, long[] jArr);

    EventBuilder annotate(String str, String[] strArr);

    EventBuilder annotate(String str, boolean[] zArr);

    boolean isSampled();

    void report();

    EventBuilder setLevel(@EventLevel int i);
}
