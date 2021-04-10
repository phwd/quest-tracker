package com.oculus.android.exoplayer2.text.webvtt;

import com.oculus.android.exoplayer2.text.Cue;
import com.oculus.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.oculus.android.exoplayer2.text.SubtitleDecoderException;
import com.oculus.android.exoplayer2.text.webvtt.WebvttCue;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;

public final class Mp4WebvttDecoder extends SimpleSubtitleDecoder {
    private static final int BOX_HEADER_SIZE = 8;
    private static final int TYPE_payl = Util.getIntegerCodeForString("payl");
    private static final int TYPE_sttg = Util.getIntegerCodeForString("sttg");
    private static final int TYPE_vttc = Util.getIntegerCodeForString("vttc");
    private final WebvttCue.Builder builder = new WebvttCue.Builder();
    private final ParsableByteArray sampleData = new ParsableByteArray();

    public Mp4WebvttDecoder() {
        super("Mp4WebvttDecoder");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.text.SimpleSubtitleDecoder
    public Mp4WebvttSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.sampleData.reset(bArr, i);
        ArrayList arrayList = new ArrayList();
        while (this.sampleData.bytesLeft() > 0) {
            if (this.sampleData.bytesLeft() >= 8) {
                int readInt = this.sampleData.readInt();
                if (this.sampleData.readInt() == TYPE_vttc) {
                    arrayList.add(parseVttCueBox(this.sampleData, this.builder, readInt - 8));
                } else {
                    this.sampleData.skipBytes(readInt - 8);
                }
            } else {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
        }
        return new Mp4WebvttSubtitle(arrayList);
    }

    private static Cue parseVttCueBox(ParsableByteArray parsableByteArray, WebvttCue.Builder builder2, int i) throws SubtitleDecoderException {
        builder2.reset();
        while (i > 0) {
            if (i >= 8) {
                int readInt = parsableByteArray.readInt();
                int readInt2 = parsableByteArray.readInt();
                int i2 = readInt - 8;
                String str = new String(parsableByteArray.data, parsableByteArray.getPosition(), i2);
                parsableByteArray.skipBytes(i2);
                i = (i - 8) - i2;
                if (readInt2 == TYPE_sttg) {
                    WebvttCueParser.parseCueSettingsList(str, builder2);
                } else if (readInt2 == TYPE_payl) {
                    WebvttCueParser.parseCueText(null, str.trim(), builder2, Collections.emptyList());
                }
            } else {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
        }
        return builder2.build();
    }
}
