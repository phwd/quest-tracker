package com.oculus.video.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.audio.Ac3Util;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.GaplessInfoHolder;
import com.oculus.android.exoplayer2.extractor.mp4.Track;
import com.oculus.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.oculus.android.exoplayer2.extractor.ts.PsExtractor;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.CodecSpecificDataUtil;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.android.exoplayer2.video.AvcConfig;
import com.oculus.android.exoplayer2.video.HevcConfig;
import com.oculus.video.extractor.mp4.Atom;
import com.oculus.video.extractor.mp4.FixedSampleSizeRechunker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public final class AtomParsers {
    private static final int[] SPHERICAL_V1_UUID = {-3374493, -128628077, -2011932550, 38936541};
    private static final String TAG = "AtomParsers";
    private static final int TYPE_cenc = Util.getIntegerCodeForString(C.CENC_TYPE_cenc);
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

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z) throws ParserException {
        long j2;
        Atom.LeafAtom leafAtom2;
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
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z);
        Pair<long[], long[]> parseEdts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
        if (parseStsd.format == null) {
            return null;
        }
        return new Track(parseTkhd.id, parseHdlr, ((Long) parseMdhd.first).longValue(), parseMvhd, j3, parseStsd.format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, (long[]) parseEdts.first, (long[]) parseEdts.second);
    }

    public static String parseMetaXmlAtom(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_xml);
        if (leafAtomOfType == null) {
            return null;
        }
        leafAtomOfType.data.setPosition(12);
        return leafAtomOfType.data.readString(leafAtomOfType.data.bytesLeft());
    }

    public static String parseUuidAtomForSphericalV1(Atom.LeafAtom leafAtom) {
        if (leafAtom == null || leafAtom.data.bytesLeft() <= (SPHERICAL_V1_UUID.length * 4) + 12) {
            return null;
        }
        leafAtom.data.setPosition(8);
        for (int i = 0; i < SPHERICAL_V1_UUID.length; i++) {
            if (leafAtom.data.readInt() != SPHERICAL_V1_UUID[i]) {
                return null;
            }
        }
        return leafAtom.data.readString(leafAtom.data.bytesLeft());
    }

    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        long[] jArr;
        int[] iArr2;
        long[] jArr2;
        long j;
        boolean z2;
        int[] iArr3;
        int i5;
        long[] jArr3;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        int i6;
        int i7;
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
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0]);
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
        int i8 = -1;
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            i2 = parsableByteArray4.readUnsignedIntToInt();
            if (i2 > 0) {
                i8 = parsableByteArray4.readUnsignedIntToInt() - 1;
            } else {
                parsableByteArray4 = null;
            }
        } else {
            i2 = 0;
        }
        long j2 = 0;
        if (!(sampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track2.format.sampleMimeType) && readUnsignedIntToInt == 0 && i == 0 && i2 == 0)) {
            jArr2 = new long[sampleCount];
            iArr = new int[sampleCount];
            jArr = new long[sampleCount];
            iArr2 = new int[sampleCount];
            int i9 = i2;
            int i10 = readUnsignedIntToInt3;
            int i11 = i;
            int i12 = i8;
            long j3 = 0;
            long j4 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = readUnsignedIntToInt2;
            int i18 = readUnsignedIntToInt;
            int i19 = 0;
            while (i19 < sampleCount) {
                long j5 = j3;
                int i20 = i13;
                while (i20 == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    j5 = chunkIterator.offset;
                    i20 = chunkIterator.numSamples;
                    i18 = i18;
                    i10 = i10;
                }
                if (parsableByteArray5 != null) {
                    while (i16 == 0 && i11 > 0) {
                        i16 = parsableByteArray5.readUnsignedIntToInt();
                        i15 = parsableByteArray5.readInt();
                        i11--;
                    }
                    i16--;
                }
                jArr2[i19] = j5;
                iArr[i19] = sampleSizeBox.readNextSampleSize();
                if (iArr[i19] > i14) {
                    i6 = sampleCount;
                    i14 = iArr[i19];
                } else {
                    i6 = sampleCount;
                }
                jArr[i19] = j4 + ((long) i15);
                iArr2[i19] = parsableByteArray4 == null ? 1 : 0;
                if (i19 == i12) {
                    iArr2[i19] = 1;
                    i9--;
                    if (i9 > 0) {
                        i12 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                int i21 = i10;
                j4 += (long) i21;
                i17--;
                if (i17 != 0 || i18 <= 0) {
                    i7 = i18;
                } else {
                    i7 = i18 - 1;
                    i17 = parsableByteArray3.readUnsignedIntToInt();
                    i21 = parsableByteArray3.readUnsignedIntToInt();
                }
                long j6 = j5 + ((long) iArr[i19]);
                i19++;
                i12 = i12;
                sampleCount = i6;
                i13 = i20 - 1;
                i15 = i15;
                i18 = i7;
                j3 = j6;
                i10 = i21;
                i9 = i9;
                sampleSizeBox = sampleSizeBox;
            }
            i4 = sampleCount;
            Assertions.checkArgument(i16 == 0);
            while (i11 > 0) {
                Assertions.checkArgument(parsableByteArray5.readUnsignedIntToInt() == 0);
                parsableByteArray5.readInt();
                i11--;
            }
            if (i9 == 0 && i17 == 0 && i13 == 0 && i18 == 0) {
                track2 = track;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                track2 = track;
                sb.append(track2.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i9);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i17);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i13);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i18);
                Log.w(TAG, sb.toString());
            }
            j = j4;
            i3 = i14;
        } else {
            i4 = sampleCount;
            long[] jArr4 = new long[chunkIterator.length];
            int[] iArr7 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr4[chunkIterator.index] = chunkIterator.offset;
                iArr7[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(sampleSizeBox.readNextSampleSize(), jArr4, iArr7, (long) readUnsignedIntToInt3);
            jArr2 = rechunk.offsets;
            iArr = rechunk.sizes;
            int i22 = rechunk.maximumSize;
            jArr = rechunk.timestamps;
            iArr2 = rechunk.flags;
            i3 = i22;
            j = 0;
        }
        if (track2.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
            return new TrackSampleTable(jArr2, iArr, i3, jArr, iArr2);
        }
        if (track2.editListDurations.length == 1 && track2.type == 1 && jArr.length >= 2) {
            long j7 = track2.editListMediaTimes[0];
            long scaleLargeTimestamp = Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale) + j7;
            if (jArr[0] <= j7 && j7 < jArr[1] && jArr[jArr.length - 1] < scaleLargeTimestamp && scaleLargeTimestamp <= j) {
                long j8 = j - scaleLargeTimestamp;
                long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(j7 - jArr[0], (long) track2.format.sampleRate, track2.timescale);
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j8, (long) track2.format.sampleRate, track2.timescale);
                if (!(scaleLargeTimestamp2 == 0 && scaleLargeTimestamp3 == 0) && scaleLargeTimestamp2 <= 2147483647L && scaleLargeTimestamp3 <= 2147483647L) {
                    gaplessInfoHolder.encoderDelay = (int) scaleLargeTimestamp2;
                    gaplessInfoHolder.encoderPadding = (int) scaleLargeTimestamp3;
                    Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
                    return new TrackSampleTable(jArr2, iArr, i3, jArr, iArr2);
                }
            }
        }
        if (track2.editListDurations.length == 1) {
            char c = 0;
            if (track2.editListDurations[0] == 0) {
                int i23 = 0;
                while (i23 < jArr.length) {
                    jArr[i23] = Util.scaleLargeTimestamp(jArr[i23] - track2.editListMediaTimes[c], C.MICROS_PER_SECOND, track2.timescale);
                    i23++;
                    c = 0;
                }
                return new TrackSampleTable(jArr2, iArr, i3, jArr, iArr2);
            }
        }
        boolean z3 = track2.type == 1;
        int i24 = 0;
        boolean z4 = false;
        int i25 = 0;
        int i26 = 0;
        while (i24 < track2.editListDurations.length) {
            long j9 = track2.editListMediaTimes[i24];
            if (j9 != -1) {
                iArr6 = iArr;
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(track2.editListDurations[i24], track2.timescale, track2.movieTimescale);
                int binarySearchCeil = Util.binarySearchCeil(jArr, j9, true, true);
                int binarySearchCeil2 = Util.binarySearchCeil(jArr, j9 + scaleLargeTimestamp4, z3, false);
                i25 += binarySearchCeil2 - binarySearchCeil;
                z4 |= i26 != binarySearchCeil;
                i26 = binarySearchCeil2;
            } else {
                iArr6 = iArr;
            }
            i24++;
            iArr = iArr6;
        }
        int[] iArr8 = iArr;
        boolean z5 = (i25 != i4) | z4;
        long[] jArr5 = z5 ? new long[i25] : jArr2;
        int[] iArr9 = z5 ? new int[i25] : iArr8;
        if (z5) {
            i3 = 0;
        }
        int[] iArr10 = z5 ? new int[i25] : iArr2;
        long[] jArr6 = new long[i25];
        int i27 = i3;
        int i28 = 0;
        int i29 = 0;
        while (i28 < track2.editListDurations.length) {
            long j10 = track2.editListMediaTimes[i28];
            long j11 = track2.editListDurations[i28];
            if (j10 != -1) {
                i5 = i28;
                int binarySearchCeil3 = Util.binarySearchCeil(jArr, j10, true, true);
                int binarySearchCeil4 = Util.binarySearchCeil(jArr, Util.scaleLargeTimestamp(j11, track2.timescale, track2.movieTimescale) + j10, z3, false);
                if (z5) {
                    int i30 = binarySearchCeil4 - binarySearchCeil3;
                    System.arraycopy(jArr2, binarySearchCeil3, jArr5, i29, i30);
                    iArr4 = iArr8;
                    System.arraycopy(iArr4, binarySearchCeil3, iArr9, i29, i30);
                    z2 = z3;
                    iArr5 = iArr10;
                    System.arraycopy(iArr2, binarySearchCeil3, iArr5, i29, i30);
                } else {
                    iArr4 = iArr8;
                    z2 = z3;
                    iArr5 = iArr10;
                }
                int i31 = i27;
                while (binarySearchCeil3 < binarySearchCeil4) {
                    jArr6[i29] = Util.scaleLargeTimestamp(j2, C.MICROS_PER_SECOND, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr[binarySearchCeil3] - j10, C.MICROS_PER_SECOND, track2.timescale);
                    if (z5 && iArr9[i29] > i31) {
                        i31 = iArr4[binarySearchCeil3];
                    }
                    i29++;
                    binarySearchCeil3++;
                    jArr2 = jArr2;
                    j10 = j10;
                    iArr2 = iArr2;
                }
                jArr3 = jArr2;
                iArr3 = iArr2;
                i27 = i31;
            } else {
                iArr4 = iArr8;
                z2 = z3;
                jArr3 = jArr2;
                iArr3 = iArr2;
                iArr5 = iArr10;
                i5 = i28;
            }
            j2 += j11;
            i28 = i5 + 1;
            iArr10 = iArr5;
            jArr2 = jArr3;
            iArr2 = iArr3;
            z3 = z2;
            iArr8 = iArr4;
        }
        boolean z6 = false;
        for (int i32 = 0; i32 < iArr10.length && !z6; i32++) {
            z6 |= (iArr10[i32] & 1) != 0;
        }
        if (z6) {
            return new TrackSampleTable(jArr5, iArr9, i27, jArr6, iArr10);
        }
        throw new ParserException("The edited sample sequence does not contain a sync sample.");
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
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, drmInitData, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, drmInitData);
            }
            parsableByteArray.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, DrmInitData drmInitData, StsdData stsdData) throws ParserException {
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
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, drmInitData, j, list);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        int i7;
        int i8 = i2;
        parsableByteArray.setPosition(i8 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        if (i == Atom.TYPE_encv) {
            i7 = parseSampleEntryEncryptionData(parsableByteArray, i8, i3, stsdData, i6);
            parsableByteArray.setPosition(position);
        } else {
            i7 = i;
        }
        int i9 = -1;
        String str = null;
        List<byte[]> list = null;
        byte[] bArr = null;
        float f = 1.0f;
        boolean z = false;
        while (position - i8 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (readInt == 0 && parsableByteArray.getPosition() - i8 == i3) {
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
                        i9 = 0;
                    } else if (readUnsignedByte2 == 1) {
                        i9 = 1;
                    } else if (readUnsignedByte2 == 2) {
                        i9 = 2;
                    } else if (readUnsignedByte2 == 3) {
                        i9 = 3;
                    }
                }
            }
            position += readInt;
            i8 = i2;
        }
        if (str != null) {
            stsdData.format = Format.createVideoSampleFormat(Integer.toString(i4), str, null, -1, -1, readUnsignedShort, readUnsignedShort2, -1.0f, list, i5, f, bArr, i9, null, drmInitData);
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

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) {
        int i6;
        int i7;
        int i8;
        String str2;
        List list;
        String str3;
        String str4;
        int i9;
        boolean z2;
        boolean z3;
        int i10;
        int i11;
        StsdData stsdData2;
        int i12;
        String str5;
        int i13;
        int i14;
        int i15 = i3;
        StsdData stsdData3 = stsdData;
        parsableByteArray.setPosition(i2 + 8 + 8);
        boolean z4 = false;
        if (z) {
            i6 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            i6 = 0;
        }
        int i16 = 2;
        boolean z5 = true;
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
        int i17 = i;
        if (i17 == Atom.TYPE_enca) {
            int parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i15, stsdData3, i5);
            parsableByteArray.setPosition(position);
            i17 = parseSampleEntryEncryptionData;
        }
        int i18 = Atom.TYPE_ac_3;
        String str6 = MimeTypes.AUDIO_RAW;
        if (i17 == i18) {
            str2 = MimeTypes.AUDIO_AC3;
        } else if (i17 == Atom.TYPE_ec_3) {
            str2 = MimeTypes.AUDIO_E_AC3;
        } else if (i17 == Atom.TYPE_dtsc) {
            str2 = MimeTypes.AUDIO_DTS;
        } else if (i17 == Atom.TYPE_dtsh || i17 == Atom.TYPE_dtsl) {
            str2 = MimeTypes.AUDIO_DTS_HD;
        } else if (i17 == Atom.TYPE_dtse) {
            str2 = MimeTypes.AUDIO_DTS_EXPRESS;
        } else if (i17 == Atom.TYPE_samr) {
            str2 = MimeTypes.AUDIO_AMR_NB;
        } else {
            str2 = i17 == Atom.TYPE_sawb ? MimeTypes.AUDIO_AMR_WB : (i17 == Atom.TYPE_lpcm || i17 == Atom.TYPE_sowt) ? str6 : i17 == Atom.TYPE__mp3 ? MimeTypes.AUDIO_MPEG : i17 == Atom.TYPE_alac ? MimeTypes.AUDIO_ALAC : null;
        }
        int i19 = i8;
        int i20 = position;
        String str7 = str2;
        int i21 = i7;
        byte[] bArr = null;
        while (i20 - i2 < i15) {
            parsableByteArray.setPosition(i20);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0 ? z5 : z4, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_esds || (z && readInt2 == Atom.TYPE_wave)) {
                i11 = readInt;
                i10 = i20;
                str3 = str6;
                z2 = z5;
                i9 = i16;
                z3 = z4;
                stsdData2 = stsdData3;
                if (readInt2 == Atom.TYPE_esds) {
                    i12 = i10;
                } else {
                    i12 = findEsdsPosition(parsableByteArray, i10, i11);
                }
                if (i12 != -1) {
                    Pair<String, byte[]> parseEsdsFromParent = parseEsdsFromParent(parsableByteArray, i12);
                    str5 = (String) parseEsdsFromParent.first;
                    bArr = (byte[]) parseEsdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str5)) {
                        try {
                            Pair<Integer, Integer> parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                            i19 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                            i21 = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                        } catch (ParserException unused) {
                        }
                    }
                } else {
                    str5 = str7;
                }
                str4 = str5;
            } else {
                if (readInt2 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i20 + 8);
                    stsdData3.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData);
                } else if (readInt2 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i20 + 8);
                    stsdData3.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData);
                } else {
                    if (readInt2 == Atom.TYPE_ddts) {
                        i14 = readInt;
                        str4 = str7;
                        i13 = i20;
                        str3 = str6;
                        z2 = z5;
                        i9 = i16;
                        stsdData2 = stsdData3;
                        stsdData2.format = Format.createAudioSampleFormat(Integer.toString(i4), str7, null, -1, -1, i21, i19, null, drmInitData, 0, str);
                    } else {
                        i14 = readInt;
                        str4 = str7;
                        i13 = i20;
                        str3 = str6;
                        z2 = z5;
                        i9 = i16;
                        stsdData2 = stsdData3;
                        if (readInt2 == Atom.TYPE_alac) {
                            i11 = i14;
                            byte[] bArr2 = new byte[i11];
                            i10 = i13;
                            parsableByteArray.setPosition(i10);
                            z3 = false;
                            parsableByteArray.readBytes(bArr2, 0, i11);
                            bArr = bArr2;
                        }
                    }
                    i11 = i14;
                    i10 = i13;
                    z3 = false;
                }
                i11 = readInt;
                str4 = str7;
                i10 = i20;
                str3 = str6;
                z2 = z5;
                i9 = i16;
                z3 = z4;
                stsdData2 = stsdData3;
            }
            i20 = i10 + i11;
            stsdData3 = stsdData2;
            z4 = z3;
            z5 = z2;
            i16 = i9;
            str7 = str4;
            str6 = str3;
            i15 = i3;
        }
        if (stsdData3.format == null && str7 != null) {
            int i22 = str6.equals(str7) ? i16 : -1;
            String num = Integer.toString(i4);
            if (bArr == null) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            stsdData3.format = Format.createAudioSampleFormat(num, str7, null, -1, -1, i21, i19, i22, list, drmInitData, 0, str);
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

    private static int parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2, StsdData stsdData, int i3) {
        Pair<Integer, TrackEncryptionBox> parseSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (true) {
            boolean z = false;
            if (position - i >= i2) {
                return 0;
            }
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            if (readInt > 0) {
                z = true;
            }
            Assertions.checkArgument(z, "childAtomSize should be positive");
            if (parsableByteArray.readInt() != Atom.TYPE_sinf || (parseSinfFromParent = parseSinfFromParent(parsableByteArray, position, readInt)) == null) {
                position += readInt;
            } else {
                stsdData.trackEncryptionBoxes[i3] = (TrackEncryptionBox) parseSinfFromParent.second;
                return ((Integer) parseSinfFromParent.first).intValue();
            }
        }
    }

    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
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
        if (str == null) {
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

        @Override // com.oculus.video.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.oculus.video.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.oculus.video.extractor.mp4.AtomParsers.SampleSizeBox
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

        @Override // com.oculus.video.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        @Override // com.oculus.video.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.oculus.video.extractor.mp4.AtomParsers.SampleSizeBox
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
