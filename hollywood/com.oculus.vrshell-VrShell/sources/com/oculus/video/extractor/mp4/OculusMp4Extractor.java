package com.oculus.video.extractor.mp4;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.GaplessInfoHolder;
import com.oculus.android.exoplayer2.extractor.PositionHolder;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.SeekPoint;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.extractor.mp4.Track;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.NalUnitUtil;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.extractor.OculusExtractorsFactory;
import com.oculus.video.extractor.mp4.Atom;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public final class OculusMp4Extractor implements Extractor, SeekMap {
    private static final int BRAND_QUICKTIME = Util.getIntegerCodeForString("qt  ");
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader = new ParsableByteArray(16);
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final Stack<Atom.ContainerAtom> containerAtoms = new Stack<>();
    private int currentTrackIndex;
    private long durationUs;
    private OculusExtractorsFactory.EventListener eventListener;
    private ExtractorOutput extractorOutput;
    private boolean isInterleaved;
    private boolean isQuickTime;
    private final ParsableByteArray nalLength = new ParsableByteArray(4);
    private final ParsableByteArray nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
    private int parserState;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private Mp4Track[] tracks;

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    public OculusMp4Extractor(boolean z) {
        this.isInterleaved = z;
    }

    public OculusMp4Extractor setEventListener(OculusExtractorsFactory.EventListener eventListener2) {
        this.eventListener = eventListener2;
        return this;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return Sniffer.sniffUnfragmented(extractorInput);
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        if (j == 0) {
            enterReadingAtomHeaderState();
        } else if (this.tracks != null) {
            updateSampleIndices(j2);
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        while (true) {
            int i = this.parserState;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        return readSample(extractorInput, positionHolder);
                    }
                    throw new IllegalStateException();
                } else if (readAtomPayload(extractorInput, positionHolder)) {
                    return 1;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        long j2 = Long.MAX_VALUE;
        for (Mp4Track mp4Track : this.tracks) {
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
            if (indexOfEarlierOrEqualSynchronizationSample == -1) {
                indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
            }
            long j3 = trackSampleTable.offsets[indexOfEarlierOrEqualSynchronizationSample];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return new SeekMap.SeekPoints(new SeekPoint(j, j2));
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.data, 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        if (this.atomSize == 1) {
            extractorInput.readFully(this.atomHeader.data, 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        }
        if (this.atomType == Atom.TYPE_meta) {
            byte[] bArr = new byte[4];
            if (!extractorInput.peekFully(bArr, 0, 4, true)) {
                return false;
            }
            if ((bArr[3] | bArr[0] | bArr[1] | bArr[2]) == 0) {
                extractorInput.skipFully(4);
                this.atomHeaderBytesRead += 4;
            }
        }
        if (shouldParseContainerAtom(this.atomType)) {
            long position = (extractorInput.getPosition() + this.atomSize) - ((long) this.atomHeaderBytesRead);
            this.containerAtoms.add(new Atom.ContainerAtom(this.atomType, position));
            if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                processAtomEnded(position);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            Assertions.checkState(this.atomHeaderBytesRead == 8);
            Assertions.checkState(this.atomSize <= 2147483647L);
            this.atomData = new ParsableByteArray((int) this.atomSize);
            System.arraycopy(this.atomHeader.data, 0, this.atomData.data, 0, 8);
            this.parserState = 1;
        } else {
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private boolean readAtomPayload(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        boolean z;
        long j = this.atomSize - ((long) this.atomHeaderBytesRead);
        long position = extractorInput.getPosition() + j;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, this.atomHeaderBytesRead, (int) j);
            if (this.atomType == Atom.TYPE_ftyp) {
                this.isQuickTime = processFtypAtom(this.atomData);
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(new Atom.LeafAtom(this.atomType, this.atomData));
            }
        } else if (j < 262144) {
            extractorInput.skipFully((int) j);
        } else {
            positionHolder.position = extractorInput.getPosition() + j;
            z = true;
            processAtomEnded(position);
            return !z && this.parserState != 2;
        }
        z = false;
        processAtomEnded(position);
        if (!z) {
        }
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j) {
            Atom.ContainerAtom pop = this.containerAtoms.pop();
            if (pop.type == Atom.TYPE_moov) {
                processMoovAtom(pop);
                this.containerAtoms.clear();
                this.parserState = 2;
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(pop);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private static boolean processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
            return true;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
                return true;
            }
        }
        return false;
    }

    private void processMoovAtom(Atom.ContainerAtom containerAtom) throws ParserException {
        Metadata metadata;
        Track parseTrak;
        long j;
        OculusExtractorsFactory.EventListener eventListener2;
        ArrayList arrayList = new ArrayList();
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_udta);
        if (leafAtomOfType != null) {
            metadata = AtomParsers.parseUdta(leafAtomOfType, this.isQuickTime);
            if (metadata != null) {
                gaplessInfoHolder.setFromMetadata(metadata);
            }
        } else {
            metadata = null;
        }
        long j2 = -9223372036854775807L;
        int i = 0;
        long j3 = Long.MAX_VALUE;
        while (true) {
            int i2 = 1;
            if (i >= containerAtom.containerChildren.size()) {
                break;
            }
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i);
            if (containerAtom2.type == Atom.TYPE_meta) {
                String parseMetaXmlAtom = AtomParsers.parseMetaXmlAtom(containerAtom2);
                if (!(parseMetaXmlAtom == null || parseMetaXmlAtom.length() <= 0 || (eventListener2 = this.eventListener) == null)) {
                    eventListener2.onMovieMetadataXml(parseMetaXmlAtom);
                }
            } else if (containerAtom2.type == Atom.TYPE_trak && (parseTrak = AtomParsers.parseTrak(containerAtom2, containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), C.TIME_UNSET, null, this.isQuickTime)) != null) {
                TrackSampleTable parseStbl = AtomParsers.parseStbl(parseTrak, containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia).getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl), gaplessInfoHolder);
                if (parseStbl.sampleCount != 0) {
                    if (this.eventListener != null && parseTrak.type == 2) {
                        int i3 = 0;
                        while (i3 < parseStbl.sampleCount) {
                            if ((parseStbl.flags[i3] & i2) != 0) {
                                j = j3;
                                this.eventListener.onFindVideoSeekTimestamp(parseStbl.timestampsUs[i3]);
                            } else {
                                j = j3;
                            }
                            i3++;
                            j3 = j;
                            i2 = 1;
                        }
                    }
                    if (this.eventListener != null) {
                        Iterator<Atom.LeafAtom> it = containerAtom2.getAllLeafAtomsOfType(Atom.TYPE_uuid).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String parseUuidAtomForSphericalV1 = AtomParsers.parseUuidAtomForSphericalV1(it.next());
                            if (parseUuidAtomForSphericalV1 != null) {
                                this.eventListener.onSphericalV1Xml(parseUuidAtomForSphericalV1);
                                break;
                            }
                        }
                    }
                    Mp4Track mp4Track = new Mp4Track(parseTrak, parseStbl, this.extractorOutput.track(i, parseTrak.type));
                    Format copyWithMaxInputSize = parseTrak.format.copyWithMaxInputSize(parseStbl.maximumSize + 30);
                    if (parseTrak.type == 1) {
                        if (gaplessInfoHolder.hasGaplessInfo()) {
                            copyWithMaxInputSize = copyWithMaxInputSize.copyWithGaplessInfo(gaplessInfoHolder.encoderDelay, gaplessInfoHolder.encoderPadding);
                        }
                        if (metadata != null) {
                            copyWithMaxInputSize = copyWithMaxInputSize.copyWithMetadata(metadata);
                        }
                    }
                    mp4Track.trackOutput.format(copyWithMaxInputSize);
                    j2 = Math.max(j2, parseTrak.durationUs);
                    arrayList.add(mp4Track);
                    j3 = parseStbl.offsets[0];
                    if (j3 >= j3) {
                        j3 = j3;
                    }
                }
            }
            i++;
        }
        boolean z = true;
        this.durationUs = j2;
        this.tracks = (Mp4Track[]) arrayList.toArray(new Mp4Track[arrayList.size()]);
        Mp4Track[] mp4TrackArr = this.tracks;
        long j4 = Long.MIN_VALUE;
        long j5 = Long.MAX_VALUE;
        for (Mp4Track mp4Track2 : mp4TrackArr) {
            if (mp4Track2.sampleTable.offsets.length != 0) {
                long j6 = mp4Track2.sampleTable.offsets[0];
                if (j6 < j5) {
                    j5 = j6;
                }
                if (j6 > j4) {
                    j4 = j6;
                }
            }
        }
        if (((double) Math.max(0L, j4 - j5)) >= 1.0E8d) {
            z = false;
        }
        this.isInterleaved = z;
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        int i;
        if (this.isInterleaved) {
            i = getTrackIndexOfEarliestCurrentSample();
        } else {
            i = getTrackIndexOfSampleNonInterleaved();
        }
        if (i == -1) {
            return -1;
        }
        Mp4Track mp4Track = this.tracks[i];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i2 = mp4Track.sampleIndex;
        long j = mp4Track.sampleTable.offsets[i2];
        int i3 = mp4Track.sampleTable.sizes[i2];
        if (mp4Track.track.sampleTransformation == 1) {
            j += 8;
            i3 -= 8;
        }
        long position = (j - extractorInput.getPosition()) + ((long) this.sampleBytesWritten);
        if (position < 0 || position >= 262144) {
            positionHolder.position = j;
            return 1;
        }
        extractorInput.skipFully((int) position);
        if (mp4Track.track.nalUnitLengthFieldLength == 0) {
            while (true) {
                int i4 = this.sampleBytesWritten;
                if (i4 >= i3) {
                    break;
                }
                int sampleData = trackOutput.sampleData(extractorInput, i3 - i4, false);
                this.sampleBytesWritten += sampleData;
                this.sampleCurrentNalBytesRemaining -= sampleData;
            }
        } else {
            byte[] bArr = this.nalLength.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i5 = mp4Track.track.nalUnitLengthFieldLength;
            int i6 = 4 - mp4Track.track.nalUnitLengthFieldLength;
            while (this.sampleBytesWritten < i3) {
                int i7 = this.sampleCurrentNalBytesRemaining;
                if (i7 == 0) {
                    extractorInput.readFully(this.nalLength.data, i6, i5);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    i3 += i6;
                } else {
                    int sampleData2 = trackOutput.sampleData(extractorInput, i7, false);
                    this.sampleBytesWritten += sampleData2;
                    this.sampleCurrentNalBytesRemaining -= sampleData2;
                }
            }
        }
        trackOutput.sampleMetadata(mp4Track.sampleTable.timestampsUs[i2], mp4Track.sampleTable.flags[i2], i3, 0, null);
        mp4Track.sampleIndex++;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        return 0;
    }

    private int getTrackIndexOfEarliestCurrentSample() {
        int i = -1;
        long j = Long.MAX_VALUE;
        int i2 = 0;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i2 >= mp4TrackArr.length) {
                return i;
            }
            Mp4Track mp4Track = mp4TrackArr[i2];
            int i3 = mp4Track.sampleIndex;
            if (i3 != mp4Track.sampleTable.sampleCount) {
                long j2 = mp4Track.sampleTable.offsets[i3];
                if (j2 < j) {
                    i = i2;
                    j = j2;
                }
            }
            i2++;
        }
    }

    private void updateSampleIndices(long j) {
        Mp4Track[] mp4TrackArr = this.tracks;
        for (Mp4Track mp4Track : mp4TrackArr) {
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
            if (indexOfEarlierOrEqualSynchronizationSample == -1) {
                indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
            }
            mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
        }
    }

    private int getTrackIndexOfSampleNonInterleaved() {
        Mp4Track[] mp4TrackArr = this.tracks;
        int i = 0;
        if (mp4TrackArr.length == 1) {
            Mp4Track mp4Track = mp4TrackArr[0];
            return mp4Track.sampleIndex == mp4Track.sampleTable.sampleCount ? -1 : 0;
        }
        long j = Long.MAX_VALUE;
        long j2 = Long.MAX_VALUE;
        int i2 = -1;
        while (true) {
            Mp4Track[] mp4TrackArr2 = this.tracks;
            if (i >= mp4TrackArr2.length) {
                break;
            }
            Mp4Track mp4Track2 = mp4TrackArr2[i];
            int i3 = mp4Track2.sampleIndex;
            if (i3 != mp4Track2.sampleTable.sampleCount) {
                long j3 = mp4Track2.sampleTable.timestampsUs[i3];
                if (j3 < j2) {
                    i2 = i;
                    j2 = j3;
                }
                if (i == this.currentTrackIndex) {
                    j = j3;
                }
            } else if (i == this.currentTrackIndex) {
                this.currentTrackIndex = -1;
            }
            i++;
        }
        if (this.currentTrackIndex == -1) {
            this.currentTrackIndex = i2;
        } else if (j - j2 > 3000000) {
            this.currentTrackIndex = i2;
        }
        return this.currentTrackIndex;
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == Atom.TYPE_mdhd || i == Atom.TYPE_mvhd || i == Atom.TYPE_hdlr || i == Atom.TYPE_stsd || i == Atom.TYPE_stts || i == Atom.TYPE_stss || i == Atom.TYPE_ctts || i == Atom.TYPE_elst || i == Atom.TYPE_stsc || i == Atom.TYPE_stsz || i == Atom.TYPE_stz2 || i == Atom.TYPE_stco || i == Atom.TYPE_co64 || i == Atom.TYPE_tkhd || i == Atom.TYPE_ftyp || i == Atom.TYPE_udta || i == Atom.TYPE_xml || i == Atom.TYPE_uuid;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == Atom.TYPE_moov || i == Atom.TYPE_trak || i == Atom.TYPE_mdia || i == Atom.TYPE_minf || i == Atom.TYPE_stbl || i == Atom.TYPE_edts || i == Atom.TYPE_meta;
    }

    /* access modifiers changed from: private */
    public static final class Mp4Track {
        int sampleIndex;
        final TrackSampleTable sampleTable;
        final Track track;
        final TrackOutput trackOutput;

        Mp4Track(Track track2, TrackSampleTable trackSampleTable, TrackOutput trackOutput2) {
            this.track = track2;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput2;
        }
    }
}
