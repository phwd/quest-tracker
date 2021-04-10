package com.oculus.android.exoplayer2.text.pgs;

import android.graphics.Bitmap;
import com.oculus.android.exoplayer2.text.Cue;
import com.oculus.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.oculus.android.exoplayer2.text.Subtitle;
import com.oculus.android.exoplayer2.text.SubtitleDecoderException;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class PgsDecoder extends SimpleSubtitleDecoder {
    private static final int SECTION_TYPE_BITMAP_PICTURE = 21;
    private static final int SECTION_TYPE_END = 128;
    private static final int SECTION_TYPE_IDENTIFIER = 22;
    private static final int SECTION_TYPE_PALETTE = 20;
    private final ParsableByteArray buffer = new ParsableByteArray();
    private final CueBuilder cueBuilder = new CueBuilder();

    public PgsDecoder() {
        super("PgsDecoder");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.buffer.reset(bArr, i);
        this.cueBuilder.reset();
        ArrayList arrayList = new ArrayList();
        while (this.buffer.bytesLeft() >= 3) {
            Cue readNextSection = readNextSection(this.buffer, this.cueBuilder);
            if (readNextSection != null) {
                arrayList.add(readNextSection);
            }
        }
        return new PgsSubtitle(Collections.unmodifiableList(arrayList));
    }

    private static Cue readNextSection(ParsableByteArray parsableByteArray, CueBuilder cueBuilder2) {
        int limit = parsableByteArray.limit();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition() + readUnsignedShort;
        Cue cue = null;
        if (position > limit) {
            parsableByteArray.setPosition(limit);
            return null;
        }
        if (readUnsignedByte != 128) {
            switch (readUnsignedByte) {
                case 20:
                    cueBuilder2.parsePaletteSection(parsableByteArray, readUnsignedShort);
                    break;
                case 21:
                    cueBuilder2.parseBitmapSection(parsableByteArray, readUnsignedShort);
                    break;
                case 22:
                    cueBuilder2.parseIdentifierSection(parsableByteArray, readUnsignedShort);
                    break;
            }
        } else {
            cue = cueBuilder2.build();
            cueBuilder2.reset();
        }
        parsableByteArray.setPosition(position);
        return cue;
    }

    /* access modifiers changed from: private */
    public static final class CueBuilder {
        private final ParsableByteArray bitmapData = new ParsableByteArray();
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private final int[] colors = new int[256];
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void parsePaletteSection(ParsableByteArray parsableByteArray, int i) {
            if (i % 5 == 2) {
                parsableByteArray.skipBytes(2);
                Arrays.fill(this.colors, 0);
                int i2 = i / 5;
                for (int i3 = 0; i3 < i2; i3++) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte5 = parsableByteArray.readUnsignedByte();
                    double d = (double) readUnsignedByte2;
                    double d2 = (double) (readUnsignedByte3 - 128);
                    double d3 = (double) (readUnsignedByte4 - 128);
                    this.colors[readUnsignedByte] = Util.constrainValue((int) (d + (d3 * 1.772d)), 0, 255) | (Util.constrainValue((int) ((d - (0.34414d * d3)) - (d2 * 0.71414d)), 0, 255) << 8) | (readUnsignedByte5 << 24) | (Util.constrainValue((int) ((1.402d * d2) + d), 0, 255) << 16);
                }
                this.colorsSet = true;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void parseBitmapSection(ParsableByteArray parsableByteArray, int i) {
            int readUnsignedInt24;
            if (i >= 4) {
                parsableByteArray.skipBytes(3);
                int i2 = i - 4;
                if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                    if (i2 >= 7 && (readUnsignedInt24 = parsableByteArray.readUnsignedInt24()) >= 4) {
                        this.bitmapWidth = parsableByteArray.readUnsignedShort();
                        this.bitmapHeight = parsableByteArray.readUnsignedShort();
                        this.bitmapData.reset(readUnsignedInt24 - 4);
                        i2 -= 7;
                    } else {
                        return;
                    }
                }
                int position = this.bitmapData.getPosition();
                int limit = this.bitmapData.limit();
                if (position < limit && i2 > 0) {
                    int min = Math.min(i2, limit - position);
                    parsableByteArray.readBytes(this.bitmapData.data, position, min);
                    this.bitmapData.setPosition(position + min);
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void parseIdentifierSection(ParsableByteArray parsableByteArray, int i) {
            if (i >= 19) {
                this.planeWidth = parsableByteArray.readUnsignedShort();
                this.planeHeight = parsableByteArray.readUnsignedShort();
                parsableByteArray.skipBytes(11);
                this.bitmapX = parsableByteArray.readUnsignedShort();
                this.bitmapY = parsableByteArray.readUnsignedShort();
            }
        }

        public Cue build() {
            int i;
            int i2;
            int i3;
            if (this.planeWidth == 0 || this.planeHeight == 0 || this.bitmapWidth == 0 || this.bitmapHeight == 0 || this.bitmapData.limit() == 0 || this.bitmapData.getPosition() != this.bitmapData.limit() || !this.colorsSet) {
                return null;
            }
            this.bitmapData.setPosition(0);
            int[] iArr = new int[(this.bitmapWidth * this.bitmapHeight)];
            int i4 = 0;
            while (i4 < iArr.length) {
                int readUnsignedByte = this.bitmapData.readUnsignedByte();
                if (readUnsignedByte != 0) {
                    i3 = i4 + 1;
                    iArr[i4] = this.colors[readUnsignedByte];
                } else {
                    int readUnsignedByte2 = this.bitmapData.readUnsignedByte();
                    if (readUnsignedByte2 != 0) {
                        if ((readUnsignedByte2 & 64) == 0) {
                            i = readUnsignedByte2 & 63;
                        } else {
                            i = ((readUnsignedByte2 & 63) << 8) | this.bitmapData.readUnsignedByte();
                        }
                        if ((readUnsignedByte2 & 128) == 0) {
                            i2 = 0;
                        } else {
                            i2 = this.colors[this.bitmapData.readUnsignedByte()];
                        }
                        i3 = i + i4;
                        Arrays.fill(iArr, i4, i3, i2);
                    }
                }
                i4 = i3;
            }
            Bitmap createBitmap = Bitmap.createBitmap(iArr, this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
            int i5 = this.planeWidth;
            float f = ((float) this.bitmapX) / ((float) i5);
            int i6 = this.planeHeight;
            return new Cue(createBitmap, f, 0, ((float) this.bitmapY) / ((float) i6), 0, ((float) this.bitmapWidth) / ((float) i5), ((float) this.bitmapHeight) / ((float) i6));
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }
}
