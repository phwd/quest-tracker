package com.oculus.android.exoplayer2.extractor.mkv;

import android.support.v4.media.session.PlaybackStateCompat;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        long readUint;
        int i;
        long length = extractorInput.getLength();
        int i2 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        long j = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        if (i2 != 0 && length <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            j = length;
        }
        int i3 = (int) j;
        extractorInput.peekFully(this.scratch.data, 0, 4);
        long readUnsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (readUnsignedInt != 440786851) {
            int i4 = this.peekLength + 1;
            this.peekLength = i4;
            if (i4 == i3) {
                return false;
            }
            extractorInput.peekFully(this.scratch.data, 0, 1);
            readUnsignedInt = ((long) (this.scratch.data[0] & 255)) | ((readUnsignedInt << 8) & -256);
        }
        long readUint2 = readUint(extractorInput);
        long j2 = (long) this.peekLength;
        if (readUint2 == Long.MIN_VALUE || (i2 != 0 && j2 + readUint2 >= length)) {
            return false;
        }
        while (true) {
            int i5 = this.peekLength;
            long j3 = j2 + readUint2;
            if (((long) i5) < j3) {
                if (readUint(extractorInput) != Long.MIN_VALUE && (readUint = readUint(extractorInput)) >= 0 && readUint <= 2147483647L) {
                    if (i != 0) {
                        extractorInput.advancePeekPosition((int) readUint);
                        this.peekLength = (int) (((long) this.peekLength) + readUint);
                    }
                }
            } else if (((long) i5) == j3) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private long readUint(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        extractorInput.peekFully(this.scratch.data, 0, 1);
        int i2 = this.scratch.data[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        int i5 = i2 & (~i3);
        extractorInput.peekFully(this.scratch.data, 1, i4);
        while (i < i4) {
            i++;
            i5 = (this.scratch.data[i] & 255) + (i5 << 8);
        }
        this.peekLength += i4 + 1;
        return (long) i5;
    }
}
