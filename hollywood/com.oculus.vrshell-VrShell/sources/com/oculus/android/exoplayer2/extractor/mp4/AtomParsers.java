package com.oculus.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.audio.Ac3Util;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.GaplessInfoHolder;
import com.oculus.android.exoplayer2.extractor.mp4.Atom;
import com.oculus.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker;
import com.oculus.android.exoplayer2.extractor.ts.PsExtractor;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.CodecSpecificDataUtil;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.android.exoplayer2.video.AvcConfig;
import com.oculus.android.exoplayer2.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public final class AtomParsers {
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");

    /* access modifiers changed from: private */
    public interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        Atom.LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int parseHdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (parseHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j3 = C.TIME_UNSET;
        if (j == C.TIME_UNSET) {
            j2 = parseTkhd.duration;
            leafAtom2 = leafAtom;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 != C.TIME_UNSET) {
            j3 = Util.scaleLargeTimestamp(j2, C.MICROS_PER_SECOND, parseMvhd);
        }
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (!z) {
            Pair<long[], long[]> parseEdts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        } else {
            jArr2 = null;
            jArr = null;
        }
        if (parseStsd.format == null) {
            return null;
        }
        return new Track(parseTkhd.id, parseHdlr, ((Long) parseMdhd.first).longValue(), parseMvhd, j3, parseStsd.format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr2, jArr);
    }

    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        long j;
        int[] iArr;
        int[] iArr2;
        long[] jArr;
        long[] jArr2;
        int i4;
        String str;
        long[] jArr3;
        String str2;
        boolean z2;
        long[] jArr4;
        long[] jArr5;
        int i5;
        int[] iArr3;
        long[] jArr6;
        int[] iArr4;
        String str3;
        int i6;
        int i7;
        int i8;
        Track track2 = track;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            sampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 != null) {
                sampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
            } else {
                throw new ParserException("Track has no sample table size information");
            }
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0], C.TIME_UNSET);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray5 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray3.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray3.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i = parsableByteArray5.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        int i9 = -1;
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            i2 = parsableByteArray4.readUnsignedIntToInt();
            if (i2 > 0) {
                i9 = parsableByteArray4.readUnsignedIntToInt() - 1;
            } else {
                parsableByteArray4 = null;
            }
        } else {
            i2 = 0;
        }
        long j2 = 0;
        if (!(sampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track2.format.sampleMimeType) && readUnsignedIntToInt == 0 && i == 0 && i2 == 0)) {
            jArr2 = new long[sampleCount];
            iArr2 = new int[sampleCount];
            jArr = new long[sampleCount];
            iArr = new int[sampleCount];
            int i10 = i2;
            int i11 = readUnsignedIntToInt2;
            int i12 = readUnsignedIntToInt3;
            int i13 = i;
            int i14 = i9;
            long j3 = 0;
            int i15 = readUnsignedIntToInt;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            long j4 = 0;
            while (i17 < sampleCount) {
                while (i19 == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    j4 = chunkIterator.offset;
                    i19 = chunkIterator.numSamples;
                    sampleCount = sampleCount;
                    i10 = i10;
                }
                if (parsableByteArray5 != null) {
                    while (i18 == 0 && i13 > 0) {
                        i18 = parsableByteArray5.readUnsignedIntToInt();
                        i20 = parsableByteArray5.readInt();
                        i13--;
                    }
                    i18--;
                }
                jArr2[i17] = j4;
                iArr2[i17] = sampleSizeBox.readNextSampleSize();
                if (iArr2[i17] > i16) {
                    i16 = iArr2[i17];
                }
                jArr[i17] = ((long) i20) + j3;
                iArr[i17] = parsableByteArray4 == null ? 1 : 0;
                if (i17 == i14) {
                    iArr[i17] = 1;
                    i7 = i10 - 1;
                    if (i7 > 0) {
                        i14 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                    i6 = i20;
                } else {
                    i6 = i20;
                    i7 = i10;
                }
                j3 += (long) i12;
                i11--;
                if (i11 == 0) {
                    i8 = i15;
                    if (i8 > 0) {
                        int readUnsignedIntToInt4 = parsableByteArray3.readUnsignedIntToInt();
                        i12 = parsableByteArray3.readInt();
                        i15 = i8 - 1;
                        i11 = readUnsignedIntToInt4;
                        j4 += (long) iArr2[i17];
                        i19--;
                        i17++;
                        i10 = i7;
                        sampleSizeBox = sampleSizeBox;
                        sampleCount = sampleCount;
                        i20 = i6;
                    }
                } else {
                    i8 = i15;
                }
                i15 = i8;
                j4 += (long) iArr2[i17];
                i19--;
                i17++;
                i10 = i7;
                sampleSizeBox = sampleSizeBox;
                sampleCount = sampleCount;
                i20 = i6;
            }
            i3 = sampleCount;
            j = j3 + ((long) i20);
            Assertions.checkArgument(i18 == 0);
            while (i13 > 0) {
                Assertions.checkArgument(parsableByteArray5.readUnsignedIntToInt() == 0);
                parsableByteArray5.readInt();
                i13--;
            }
            if (i10 == 0 && i11 == 0 && i19 == 0 && i15 == 0) {
                str = TAG;
                i4 = i16;
                track2 = track;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                i4 = i16;
                track2 = track;
                sb.append(track2.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i10);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i11);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i19);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i15);
                String sb2 = sb.toString();
                str = TAG;
                Log.w(str, sb2);
            }
        } else {
            i3 = sampleCount;
            str = TAG;
            long[] jArr7 = new long[chunkIterator.length];
            int[] iArr5 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr7[chunkIterator.index] = chunkIterator.offset;
                iArr5[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(sampleSizeBox.readNextSampleSize(), jArr7, iArr5, (long) readUnsignedIntToInt3);
            jArr2 = rechunk.offsets;
            iArr2 = rechunk.sizes;
            i4 = rechunk.maximumSize;
            jArr = rechunk.timestamps;
            iArr = rechunk.flags;
            j = rechunk.duration;
        }
        int[] iArr6 = iArr2;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j, C.MICROS_PER_SECOND, track2.timescale);
        if (track2.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
            return new TrackSampleTable(jArr2, iArr6, i4, jArr, iArr, scaleLargeTimestamp);
        }
        if (track2.editListDurations.length == 1 && track2.type == 1 && jArr.length >= 2) {
            long j5 = track2.editListMediaTimes[0];
            jArr3 = jArr2;
            long scaleLargeTimestamp2 = j5 + Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale);
            if (jArr[0] > j5 || j5 >= jArr[1] || jArr[jArr.length - 1] >= scaleLargeTimestamp2 || scaleLargeTimestamp2 > j) {
                str2 = str;
            } else {
                long j6 = j - scaleLargeTimestamp2;
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j5 - jArr[0], (long) track2.format.sampleRate, track2.timescale);
                str2 = str;
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(j6, (long) track2.format.sampleRate, track2.timescale);
                if (!(scaleLargeTimestamp3 == 0 && scaleLargeTimestamp4 == 0) && scaleLargeTimestamp3 <= 2147483647L && scaleLargeTimestamp4 <= 2147483647L) {
                    gaplessInfoHolder.encoderDelay = (int) scaleLargeTimestamp3;
                    gaplessInfoHolder.encoderPadding = (int) scaleLargeTimestamp4;
                    Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
                    return new TrackSampleTable(jArr3, iArr6, i4, jArr, iArr, scaleLargeTimestamp);
                }
            }
        } else {
            str2 = str;
            jArr3 = jArr2;
        }
        if (track2.editListDurations.length == 1 && track2.editListDurations[0] == 0) {
            long j7 = track2.editListMediaTimes[0];
            for (int i21 = 0; i21 < jArr.length; i21++) {
                jArr[i21] = Util.scaleLargeTimestamp(jArr[i21] - j7, C.MICROS_PER_SECOND, track2.timescale);
            }
            return new TrackSampleTable(jArr3, iArr6, i4, jArr, iArr, Util.scaleLargeTimestamp(j - j7, C.MICROS_PER_SECOND, track2.timescale));
        }
        boolean z3 = track2.type == 1;
        int i22 = 0;
        boolean z4 = false;
        int i23 = 0;
        int i24 = 0;
        while (i22 < track2.editListDurations.length) {
            long j8 = track2.editListMediaTimes[i22];
            if (j8 != -1) {
                str3 = str2;
                long scaleLargeTimestamp5 = Util.scaleLargeTimestamp(track2.editListDurations[i22], track2.timescale, track2.movieTimescale);
                int binarySearchCeil = Util.binarySearchCeil(jArr, j8, true, true);
                int binarySearchCeil2 = Util.binarySearchCeil(jArr, j8 + scaleLargeTimestamp5, z3, false);
                i23 += binarySearchCeil2 - binarySearchCeil;
                z4 |= i24 != binarySearchCeil;
                i24 = binarySearchCeil2;
            } else {
                str3 = str2;
            }
            i22++;
            str2 = str3;
            scaleLargeTimestamp = scaleLargeTimestamp;
        }
        boolean z5 = (i23 != i3) | z4;
        long[] jArr8 = z5 ? new long[i23] : jArr3;
        int[] iArr7 = z5 ? new int[i23] : iArr6;
        int i25 = z5 ? 0 : i4;
        int[] iArr8 = z5 ? new int[i23] : iArr;
        long[] jArr9 = new long[i23];
        int i26 = i25;
        int i27 = 0;
        int i28 = 0;
        while (i27 < track2.editListDurations.length) {
            long j9 = track2.editListMediaTimes[i27];
            long j10 = track2.editListDurations[i27];
            if (j9 != -1) {
                i5 = i27;
                iArr3 = iArr8;
                jArr4 = jArr9;
                int binarySearchCeil3 = Util.binarySearchCeil(jArr, j9, true, true);
                int binarySearchCeil4 = Util.binarySearchCeil(jArr, Util.scaleLargeTimestamp(j10, track2.timescale, track2.movieTimescale) + j9, z3, false);
                if (z5) {
                    int i29 = binarySearchCeil4 - binarySearchCeil3;
                    jArr6 = jArr3;
                    System.arraycopy(jArr6, binarySearchCeil3, jArr8, i28, i29);
                    z2 = z3;
                    iArr4 = iArr6;
                    System.arraycopy(iArr4, binarySearchCeil3, iArr7, i28, i29);
                    System.arraycopy(iArr, binarySearchCeil3, iArr3, i28, i29);
                } else {
                    jArr6 = jArr3;
                    z2 = z3;
                    iArr4 = iArr6;
                }
                int i30 = i26;
                while (binarySearchCeil3 < binarySearchCeil4) {
                    jArr4[i28] = Util.scaleLargeTimestamp(j2, C.MICROS_PER_SECOND, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr[binarySearchCeil3] - j9, C.MICROS_PER_SECOND, track2.timescale);
                    if (z5 && iArr7[i28] > i30) {
                        i30 = iArr4[binarySearchCeil3];
                    }
                    i28++;
                    binarySearchCeil3++;
                    binarySearchCeil4 = binarySearchCeil4;
                    j9 = j9;
                    jArr8 = jArr8;
                }
                jArr5 = jArr8;
                i26 = i30;
            } else {
                z2 = z3;
                jArr5 = jArr8;
                jArr4 = jArr9;
                iArr4 = iArr6;
                i5 = i27;
                iArr3 = iArr8;
                jArr6 = jArr3;
            }
            j2 += j10;
            iArr6 = iArr4;
            jArr3 = jArr6;
            iArr8 = iArr3;
            jArr9 = jArr4;
            z3 = z2;
            i27 = i5 + 1;
            jArr8 = jArr5;
        }
        long scaleLargeTimestamp6 = Util.scaleLargeTimestamp(j2, C.MICROS_PER_SECOND, track2.timescale);
        boolean z6 = false;
        for (int i31 = 0; i31 < iArr8.length && !z6; i31++) {
            z6 |= (iArr8[i31] & 1) != 0;
        }
        if (z6) {
            return new TrackSampleTable(jArr8, iArr7, i26, jArr9, iArr8, scaleLargeTimestamp6);
        }
        Log.w(str2, "Ignoring edit list: Edited sample sequence does not contain a sync sample.");
        Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
        return new TrackSampleTable(jArr3, iArr6, i4, jArr, iArr, scaleLargeTimestamp);
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = C.TIME_UNSET;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int readInt = parsableByteArray.readInt();
        if (readInt == TYPE_soun) {
            return 1;
        }
        if (readInt == TYPE_vide) {
            return 2;
        }
        if (readInt == TYPE_text || readInt == TYPE_sbtl || readInt == TYPE_subt || readInt == TYPE_clcp) {
            return 3;
        }
        return readInt == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(readUnsignedInt), "" + ((char) (((readUnsignedShort >> 10) & 31) + 96)) + ((char) (((readUnsignedShort >> 5) & 31) + 96)) + ((char) ((readUnsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263 || readInt3 == Atom.TYPE_vp08 || readInt3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3 || readInt3 == Atom.TYPE_ec_3 || readInt3 == Atom.TYPE_dtsc || readInt3 == Atom.TYPE_dtse || readInt3 == Atom.TYPE_dtsh || readInt3 == Atom.TYPE_dtsl || readInt3 == Atom.TYPE_samr || readInt3 == Atom.TYPE_sawb || readInt3 == Atom.TYPE_lpcm || readInt3 == Atom.TYPE_sowt || readInt3 == Atom.TYPE__mp3 || readInt3 == Atom.TYPE_alac) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_TTML || readInt3 == Atom.TYPE_tx3g || readInt3 == Atom.TYPE_wvtt || readInt3 == Atom.TYPE_stpp || readInt3 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
            }
            parsableByteArray.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        parsableByteArray.setPosition(i2 + 8 + 8);
        int i5 = Atom.TYPE_TTML;
        String str2 = MimeTypes.APPLICATION_TTML;
        List list = null;
        long j = Long.MAX_VALUE;
        if (i != i5) {
            if (i == Atom.TYPE_tx3g) {
                int i6 = (i3 - 8) - 8;
                byte[] bArr = new byte[i6];
                parsableByteArray.readBytes(bArr, 0, i6);
                list = Collections.singletonList(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i == Atom.TYPE_wvtt) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i == Atom.TYPE_stpp) {
                j = 0;
            } else if (i == Atom.TYPE_c608) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, null, j, list);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        DrmInitData drmInitData2 = drmInitData;
        parsableByteArray.setPosition(i2 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        String str = null;
        int i7 = i;
        if (i7 == Atom.TYPE_encv) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i3);
            if (parseSampleEntryEncryptionData != null) {
                i7 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData2 == null) {
                    drmInitData2 = null;
                } else {
                    drmInitData2 = drmInitData2.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData.trackEncryptionBoxes[i6] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        int i8 = -1;
        List<byte[]> list = null;
        byte[] bArr = null;
        float f = 1.0f;
        boolean z = false;
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0 && parsableByteArray.getPosition() - i2 == i3) {
                break;
            }
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray);
                list = parse.initializationData;
                stsdData.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z) {
                    f = parse.pixelWidthAspectRatio;
                }
                str = MimeTypes.VIDEO_H264;
            } else if (readInt2 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                HevcConfig parse2 = HevcConfig.parse(parsableByteArray);
                list = parse2.initializationData;
                stsdData.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                str = MimeTypes.VIDEO_H265;
            } else if (readInt2 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = i7 == Atom.TYPE_vp08 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
            } else if (readInt2 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = MimeTypes.VIDEO_H263;
            } else if (readInt2 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) parseEsdsFromParent.first;
                list = Collections.singletonList(parseEsdsFromParent.second);
            } else if (readInt2 == Atom.TYPE_pasp) {
                f = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (readInt2 == Atom.TYPE_sv3d) {
                bArr = parseProjFromParent(parsableByteArray, position2, readInt);
            } else if (readInt2 == Atom.TYPE_st3d) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (readUnsignedByte == 0) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedByte2 == 0) {
                        i8 = 0;
                    } else if (readUnsignedByte2 == 1) {
                        i8 = 1;
                    } else if (readUnsignedByte2 == 2) {
                        i8 = 2;
                    } else if (readUnsignedByte2 == 3) {
                        i8 = 3;
                    }
                }
            }
            position += readInt;
        }
        if (str != null) {
            stsdData.format = Format.createVideoSampleFormat(Integer.toString(i4), str, null, -1, -1, readUnsignedShort, readUnsignedShort2, -1.0f, list, i5, f, bArr, i8, null, drmInitData2);
        }
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        for (int i = 0; i < readUnsignedIntToInt; i++) {
            jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v6, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r3v24, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int i6;
        int i7;
        int i8;
        String str2;
        List list;
        String str3;
        int i9;
        boolean z2;
        DrmInitData drmInitData2;
        int i10;
        int i11;
        int i12;
        String str4;
        int i13;
        int i14;
        int i15;
        int i16 = i2;
        DrmInitData drmInitData3 = drmInitData;
        parsableByteArray.setPosition(i16 + 8 + 8);
        int i17 = 0;
        if (z) {
            i6 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            i6 = 0;
        }
        int i18 = 2;
        boolean z3 = true;
        if (i6 == 0 || i6 == 1) {
            i7 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            i8 = parsableByteArray.readUnsignedFixedPoint1616();
            if (i6 == 1) {
                parsableByteArray.skipBytes(16);
            }
        } else if (i6 == 2) {
            parsableByteArray.skipBytes(16);
            i8 = (int) Math.round(parsableByteArray.readDouble());
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
            i7 = readUnsignedIntToInt;
        } else {
            return;
        }
        int position = parsableByteArray.getPosition();
        int i19 = i;
        if (i19 == Atom.TYPE_enca) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i16, i3);
            if (parseSampleEntryEncryptionData != null) {
                i19 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData3 == null) {
                    drmInitData3 = null;
                } else {
                    drmInitData3 = drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData.trackEncryptionBoxes[i5] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData4 = drmInitData3;
        int i20 = Atom.TYPE_ac_3;
        String str5 = MimeTypes.AUDIO_RAW;
        if (i19 == i20) {
            str2 = MimeTypes.AUDIO_AC3;
        } else if (i19 == Atom.TYPE_ec_3) {
            str2 = MimeTypes.AUDIO_E_AC3;
        } else if (i19 == Atom.TYPE_dtsc) {
            str2 = MimeTypes.AUDIO_DTS;
        } else if (i19 == Atom.TYPE_dtsh || i19 == Atom.TYPE_dtsl) {
            str2 = MimeTypes.AUDIO_DTS_HD;
        } else if (i19 == Atom.TYPE_dtse) {
            str2 = MimeTypes.AUDIO_DTS_EXPRESS;
        } else if (i19 == Atom.TYPE_samr) {
            str2 = MimeTypes.AUDIO_AMR_NB;
        } else {
            str2 = i19 == Atom.TYPE_sawb ? MimeTypes.AUDIO_AMR_WB : (i19 == Atom.TYPE_lpcm || i19 == Atom.TYPE_sowt) ? str5 : i19 == Atom.TYPE__mp3 ? MimeTypes.AUDIO_MPEG : i19 == Atom.TYPE_alac ? MimeTypes.AUDIO_ALAC : null;
        }
        int i21 = i8;
        int i22 = position;
        int i23 = i7;
        byte[] bArr = null;
        String str6 = str2;
        while (i22 - i16 < i3) {
            parsableByteArray.setPosition(i22);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0 ? z3 : i17, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_esds || (z && readInt2 == Atom.TYPE_wave)) {
                i11 = readInt;
                str4 = str6;
                i10 = i22;
                str3 = str5;
                drmInitData2 = drmInitData4;
                z2 = z3;
                i9 = i18;
                i12 = i17;
                if (readInt2 == Atom.TYPE_esds) {
                    i13 = i10;
                } else {
                    i13 = findEsdsPosition(parsableByteArray, i10, i11);
                }
                if (i13 != -1) {
                    Pair<String, byte[]> parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, i13);
                    str6 = (String) parseEsdsFromParent.first;
                    bArr = (byte[]) parseEsdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str6)) {
                        Pair<Integer, Integer> parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        i21 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                        i23 = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                    }
                    i22 = i10 + i11;
                    i17 = i12;
                    drmInitData4 = drmInitData2;
                    z3 = z2;
                    i18 = i9;
                    str5 = str3;
                    i16 = i2;
                }
            } else {
                if (readInt2 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i22 + 8);
                    stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData4);
                } else if (readInt2 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i22 + 8);
                    stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData4);
                } else {
                    if (readInt2 == Atom.TYPE_ddts) {
                        i15 = readInt;
                        str4 = str6;
                        i14 = i22;
                        str3 = str5;
                        drmInitData2 = drmInitData4;
                        z2 = z3;
                        i9 = i18;
                        i12 = i17;
                        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str6, null, -1, -1, i23, i21, null, drmInitData2, 0, str);
                    } else {
                        i15 = readInt;
                        str4 = str6;
                        i14 = i22;
                        str3 = str5;
                        drmInitData2 = drmInitData4;
                        z2 = z3;
                        i9 = i18;
                        i12 = i17;
                        if (readInt2 == Atom.TYPE_alac) {
                            i11 = i15;
                            byte[] bArr2 = new byte[i11];
                            i10 = i14;
                            parsableByteArray.setPosition(i10);
                            parsableByteArray.readBytes(bArr2, i12, i11);
                            bArr = bArr2;
                        }
                    }
                    i11 = i15;
                    i10 = i14;
                }
                i11 = readInt;
                str4 = str6;
                i10 = i22;
                str3 = str5;
                drmInitData2 = drmInitData4;
                z2 = z3;
                i9 = i18;
                i12 = i17;
            }
            str6 = str4;
            i22 = i10 + i11;
            i17 = i12;
            drmInitData4 = drmInitData2;
            z3 = z2;
            i18 = i9;
            str5 = str3;
            i16 = i2;
        }
        int i24 = i18;
        if (stsdData.format == null && str6 != null) {
            if (!str5.equals(str6)) {
                i24 = -1;
            }
            String num = Integer.toString(i4);
            if (bArr == null) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            stsdData.format = Format.createAudioSampleFormat(num, str6, null, -1, -1, i23, i21, i24, list, drmInitData4, 0, str);
        }
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        int i4 = -1;
        String str = null;
        Integer num = null;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(str) && !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str) && !C.CENC_TYPE_cbcs.equals(str)) {
            return null;
        }
        boolean z = true;
        Assertions.checkArgument(num != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent == null) {
            z = false;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(num, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, bArr2.length);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private AtomParsers() {
    }

    /* access modifiers changed from: private */
    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    /* access modifiers changed from: private */
    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        @Override // com.oculus.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.oculus.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.oculus.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        @Override // com.oculus.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        @Override // com.oculus.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.oculus.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }
}
