package com.oculus.android.exoplayer2.text.ttml;

import com.oculus.android.exoplayer2.text.Cue;
import com.oculus.android.exoplayer2.text.Subtitle;
import com.oculus.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class TtmlSubtitle implements Subtitle {
    private final long[] eventTimesUs;
    private final Map<String, TtmlStyle> globalStyles;
    private final Map<String, TtmlRegion> regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode ttmlNode, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) {
        this.root = ttmlNode;
        this.regionMap = map2;
        this.globalStyles = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.eventTimesUs = ttmlNode.getEventTimesUs();
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        int binarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        if (binarySearchCeil < this.eventTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public long getEventTime(int i) {
        return this.eventTimesUs[i];
    }

    /* access modifiers changed from: package-private */
    public TtmlNode getRoot() {
        return this.root;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j) {
        return this.root.getCues(j, this.globalStyles, this.regionMap);
    }

    /* access modifiers changed from: package-private */
    public Map<String, TtmlStyle> getGlobalStyles() {
        return this.globalStyles;
    }
}
