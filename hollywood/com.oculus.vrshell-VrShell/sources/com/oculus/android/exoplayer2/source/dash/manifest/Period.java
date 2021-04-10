package com.oculus.android.exoplayer2.source.dash.manifest;

import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final List<EventStream> eventStreams;
    @Nullable
    public final String id;
    public final long startMs;

    public Period(@Nullable String str, long j, List<AdaptationSet> list) {
        this(str, j, list, Collections.emptyList());
    }

    public Period(@Nullable String str, long j, List<AdaptationSet> list, List<EventStream> list2) {
        this.id = str;
        this.startMs = j;
        this.adaptationSets = Collections.unmodifiableList(list);
        this.eventStreams = Collections.unmodifiableList(list2);
    }

    public int getAdaptationSetIndex(int i) {
        int size = this.adaptationSets.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.adaptationSets.get(i2).type == i) {
                return i2;
            }
        }
        return -1;
    }
}
