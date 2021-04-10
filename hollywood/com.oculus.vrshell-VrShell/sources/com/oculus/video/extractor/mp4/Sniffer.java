package com.oculus.video.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;

final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};
    private static final int SEARCH_LENGTH = 4096;

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z) throws IOException, InterruptedException {
        boolean z2;
        boolean z3;
        long length = extractorInput.getLength();
        if (length == -1 || length > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            length = 4096;
        }
        int i = (int) length;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i2 = 0;
        boolean z4 = false;
        while (true) {
            if (i2 >= i) {
                break;
            }
            parsableByteArray.reset(8);
            extractorInput.peekFully(parsableByteArray.data, 0, 8);
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            int i3 = 16;
            if (readUnsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.data, 8, 8);
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readUnsignedLongToLong();
            } else {
                i3 = 8;
            }
            long j = (long) i3;
            if (readUnsignedInt < j) {
                return false;
            }
            i2 += i3;
            if (readInt != Atom.TYPE_moov) {
                if (readInt == Atom.TYPE_moof || readInt == Atom.TYPE_mvex) {
                    z2 = true;
                    z3 = true;
                } else if ((((long) i2) + readUnsignedInt) - j >= ((long) i)) {
                    break;
                } else {
                    int i4 = (int) (readUnsignedInt - j);
                    i2 += i4;
                    if (readInt == Atom.TYPE_ftyp) {
                        if (i4 < 8) {
                            return false;
                        }
                        parsableByteArray.reset(i4);
                        extractorInput.peekFully(parsableByteArray.data, 0, i4);
                        int i5 = i4 / 4;
                        int i6 = 0;
                        while (true) {
                            if (i6 >= i5) {
                                break;
                            }
                            if (i6 == 1) {
                                parsableByteArray.skipBytes(4);
                            } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                                z4 = true;
                                break;
                            }
                            i6++;
                        }
                        if (!z4) {
                            return false;
                        }
                    } else if (i4 != 0) {
                        extractorInput.advancePeekPosition(i4);
                    }
                }
            }
        }
        z2 = true;
        z3 = false;
        if (!z4 || z != z3) {
            return false;
        }
        return z2;
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
