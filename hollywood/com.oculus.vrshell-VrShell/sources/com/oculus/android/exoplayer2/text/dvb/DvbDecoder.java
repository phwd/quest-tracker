package com.oculus.android.exoplayer2.text.dvb;

import com.oculus.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

public final class DvbDecoder extends SimpleSubtitleDecoder {
    private final DvbParser parser;

    public DvbDecoder(List<byte[]> list) {
        super("DvbDecoder");
        ParsableByteArray parsableByteArray = new ParsableByteArray(list.get(0));
        this.parser = new DvbParser(parsableByteArray.readUnsignedShort(), parsableByteArray.readUnsignedShort());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.text.SimpleSubtitleDecoder
    public DvbSubtitle decode(byte[] bArr, int i, boolean z) {
        if (z) {
            this.parser.reset();
        }
        return new DvbSubtitle(this.parser.decode(bArr, i));
    }
}
