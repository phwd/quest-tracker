package com.oculus.android.exoplayer2.text;

import com.oculus.android.exoplayer2.decoder.OutputBuffer;
import java.util.List;

public abstract class SubtitleOutputBuffer extends OutputBuffer implements Subtitle {
    private long subsampleOffsetUs;
    private Subtitle subtitle;

    @Override // com.oculus.android.exoplayer2.decoder.OutputBuffer
    public abstract void release();

    public void setContent(long j, Subtitle subtitle2, long j2) {
        this.timeUs = j;
        this.subtitle = subtitle2;
        if (j2 == Long.MAX_VALUE) {
            j2 = this.timeUs;
        }
        this.subsampleOffsetUs = j2;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.subtitle.getEventTimeCount();
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public long getEventTime(int i) {
        return this.subtitle.getEventTime(i) + this.subsampleOffsetUs;
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        return this.subtitle.getNextEventTimeIndex(j - this.subsampleOffsetUs);
    }

    @Override // com.oculus.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j) {
        return this.subtitle.getCues(j - this.subsampleOffsetUs);
    }

    @Override // com.oculus.android.exoplayer2.decoder.Buffer
    public void clear() {
        super.clear();
        this.subtitle = null;
    }
}
