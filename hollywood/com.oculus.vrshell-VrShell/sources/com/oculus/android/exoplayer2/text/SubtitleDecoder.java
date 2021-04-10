package com.oculus.android.exoplayer2.text;

import com.oculus.android.exoplayer2.decoder.Decoder;

public interface SubtitleDecoder extends Decoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> {
    void setPositionUs(long j);
}
