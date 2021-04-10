package com.oculus.android.exoplayer2.text.dvb;

import com.oculus.android.exoplayer2.text.Cue;
import com.oculus.android.exoplayer2.text.Subtitle;
import java.util.List;

/* access modifiers changed from: package-private */
public final class DvbSubtitle implements Subtitle {
    private final List<Cue> cues;

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public long getEventTime(int i) {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return 1;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        return -1;
    }

    public DvbSubtitle(List<Cue> list) {
        this.cues = list;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j) {
        return this.cues;
    }
}
