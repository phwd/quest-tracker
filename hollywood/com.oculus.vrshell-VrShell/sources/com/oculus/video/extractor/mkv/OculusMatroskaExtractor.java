package com.oculus.video.extractor.mkv;

import android.util.Log;
import android.util.SparseArray;
import androidx.core.view.MotionEventCompat;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.ChunkIndex;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.ExtractorsFactory;
import com.oculus.android.exoplayer2.extractor.PositionHolder;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.util.LongArray;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.NalUnitUtil;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.android.exoplayer2.video.AvcConfig;
import com.oculus.android.exoplayer2.video.ColorInfo;
import com.oculus.android.exoplayer2.video.HevcConfig;
import com.oculus.video.extractor.OculusExtractorsFactory;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class OculusMatroskaExtractor implements Extractor {
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        /* class com.oculus.video.extractor.mkv.OculusMatroskaExtractor.AnonymousClass1 */

        @Override // com.oculus.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new OculusMatroskaExtractor()};
        }
    };
    private static final String FB360_METADATA_TAG = "FB360";
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_SIMPLE_TAG = 26568;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TAG = 29555;
    private static final int ID_TAGS = 307544935;
    private static final int ID_TAG_NAME = 17827;
    private static final int ID_TAG_STRING = 17543;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final String SPHERICAL_V1_TAG = "SPHERICAL-VIDEO";
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final byte[] SSA_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "OculusMatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private OculusExtractorsFactory.EventListener eventListener;
    private ExtractorOutput extractorOutput;
    private String lastTagName;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* access modifiers changed from: package-private */
    public int getElementType(int i) {
        switch (i) {
            case ID_TRACK_TYPE /*{ENCODED_INT: 131}*/:
            case ID_FLAG_DEFAULT /*{ENCODED_INT: 136}*/:
            case ID_BLOCK_DURATION /*{ENCODED_INT: 155}*/:
            case ID_CHANNELS /*{ENCODED_INT: 159}*/:
            case ID_PIXEL_WIDTH /*{ENCODED_INT: 176}*/:
            case ID_CUE_TIME /*{ENCODED_INT: 179}*/:
            case ID_PIXEL_HEIGHT /*{ENCODED_INT: 186}*/:
            case ID_TRACK_NUMBER /*{ENCODED_INT: 215}*/:
            case ID_TIME_CODE /*{ENCODED_INT: 231}*/:
            case ID_CUE_CLUSTER_POSITION /*{ENCODED_INT: 241}*/:
            case ID_REFERENCE_BLOCK /*{ENCODED_INT: 251}*/:
            case ID_CONTENT_COMPRESSION_ALGORITHM /*{ENCODED_INT: 16980}*/:
            case ID_DOC_TYPE_READ_VERSION /*{ENCODED_INT: 17029}*/:
            case ID_EBML_READ_VERSION /*{ENCODED_INT: 17143}*/:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*{ENCODED_INT: 18401}*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*{ENCODED_INT: 18408}*/:
            case ID_CONTENT_ENCODING_ORDER /*{ENCODED_INT: 20529}*/:
            case ID_CONTENT_ENCODING_SCOPE /*{ENCODED_INT: 20530}*/:
            case ID_SEEK_POSITION /*{ENCODED_INT: 21420}*/:
            case ID_STEREO_MODE /*{ENCODED_INT: 21432}*/:
            case ID_DISPLAY_WIDTH /*{ENCODED_INT: 21680}*/:
            case ID_DISPLAY_UNIT /*{ENCODED_INT: 21682}*/:
            case ID_DISPLAY_HEIGHT /*{ENCODED_INT: 21690}*/:
            case ID_FLAG_FORCED /*{ENCODED_INT: 21930}*/:
            case ID_COLOUR_RANGE /*{ENCODED_INT: 21945}*/:
            case ID_COLOUR_TRANSFER /*{ENCODED_INT: 21946}*/:
            case ID_COLOUR_PRIMARIES /*{ENCODED_INT: 21947}*/:
            case ID_MAX_CLL /*{ENCODED_INT: 21948}*/:
            case ID_MAX_FALL /*{ENCODED_INT: 21949}*/:
            case ID_CODEC_DELAY /*{ENCODED_INT: 22186}*/:
            case ID_SEEK_PRE_ROLL /*{ENCODED_INT: 22203}*/:
            case ID_AUDIO_BIT_DEPTH /*{ENCODED_INT: 25188}*/:
            case ID_DEFAULT_DURATION /*{ENCODED_INT: 2352003}*/:
            case ID_TIMECODE_SCALE /*{ENCODED_INT: 2807729}*/:
                return 2;
            case 134:
            case ID_DOC_TYPE /*{ENCODED_INT: 17026}*/:
            case ID_LANGUAGE /*{ENCODED_INT: 2274716}*/:
                return 3;
            case ID_BLOCK_GROUP /*{ENCODED_INT: 160}*/:
            case ID_TRACK_ENTRY /*{ENCODED_INT: 174}*/:
            case ID_CUE_TRACK_POSITIONS /*{ENCODED_INT: 183}*/:
            case ID_CUE_POINT /*{ENCODED_INT: 187}*/:
            case 224:
            case ID_AUDIO /*{ENCODED_INT: 225}*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /*{ENCODED_INT: 18407}*/:
            case ID_SEEK /*{ENCODED_INT: 19899}*/:
            case ID_CONTENT_COMPRESSION /*{ENCODED_INT: 20532}*/:
            case ID_CONTENT_ENCRYPTION /*{ENCODED_INT: 20533}*/:
            case ID_COLOUR /*{ENCODED_INT: 21936}*/:
            case ID_MASTERING_METADATA /*{ENCODED_INT: 21968}*/:
            case ID_CONTENT_ENCODING /*{ENCODED_INT: 25152}*/:
            case ID_SIMPLE_TAG /*{ENCODED_INT: 26568}*/:
            case ID_CONTENT_ENCODINGS /*{ENCODED_INT: 28032}*/:
            case ID_TAG /*{ENCODED_INT: 29555}*/:
            case ID_PROJECTION /*{ENCODED_INT: 30320}*/:
            case ID_SEEK_HEAD /*{ENCODED_INT: 290298740}*/:
            case ID_TAGS /*{ENCODED_INT: 307544935}*/:
            case 357149030:
            case ID_TRACKS /*{ENCODED_INT: 374648427}*/:
            case ID_SEGMENT /*{ENCODED_INT: 408125543}*/:
            case ID_EBML /*{ENCODED_INT: 440786851}*/:
            case ID_CUES /*{ENCODED_INT: 475249515}*/:
            case ID_CLUSTER /*{ENCODED_INT: 524531317}*/:
                return 1;
            case ID_BLOCK /*{ENCODED_INT: 161}*/:
            case ID_SIMPLE_BLOCK /*{ENCODED_INT: 163}*/:
            case ID_CONTENT_COMPRESSION_SETTINGS /*{ENCODED_INT: 16981}*/:
            case ID_CONTENT_ENCRYPTION_KEY_ID /*{ENCODED_INT: 18402}*/:
            case ID_SEEK_ID /*{ENCODED_INT: 21419}*/:
            case ID_CODEC_PRIVATE /*{ENCODED_INT: 25506}*/:
            case ID_PROJECTION_PRIVATE /*{ENCODED_INT: 30322}*/:
                return 4;
            case ID_SAMPLING_FREQUENCY /*{ENCODED_INT: 181}*/:
            case ID_DURATION /*{ENCODED_INT: 17545}*/:
            case ID_PRIMARY_R_CHROMATICITY_X /*{ENCODED_INT: 21969}*/:
            case ID_PRIMARY_R_CHROMATICITY_Y /*{ENCODED_INT: 21970}*/:
            case ID_PRIMARY_G_CHROMATICITY_X /*{ENCODED_INT: 21971}*/:
            case ID_PRIMARY_G_CHROMATICITY_Y /*{ENCODED_INT: 21972}*/:
            case ID_PRIMARY_B_CHROMATICITY_X /*{ENCODED_INT: 21973}*/:
            case ID_PRIMARY_B_CHROMATICITY_Y /*{ENCODED_INT: 21974}*/:
            case ID_WHITE_POINT_CHROMATICITY_X /*{ENCODED_INT: 21975}*/:
            case ID_WHITE_POINT_CHROMATICITY_Y /*{ENCODED_INT: 21976}*/:
            case ID_LUMNINANCE_MAX /*{ENCODED_INT: 21977}*/:
            case ID_LUMNINANCE_MIN /*{ENCODED_INT: 21978}*/:
                return 5;
            case ID_TAG_STRING /*{ENCODED_INT: 17543}*/:
            case ID_TAG_NAME /*{ENCODED_INT: 17827}*/:
                return 6;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    public OculusMatroskaExtractor() {
        this(0);
    }

    public OculusMatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    OculusMatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        this.reader.init(new InnerEbmlReaderOutput());
        this.seekForCuesEnabled = (i & 1) != 0 ? false : true;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public OculusMatroskaExtractor setEventListener(OculusExtractorsFactory.EventListener eventListener2) {
        if (this.eventListener == eventListener2) {
            return this;
        }
        this.eventListener = eventListener2;
        return this;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
    }

    private static int indexOf(byte[] bArr, byte[] bArr2) {
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= (bArr.length - bArr2.length) + 1) {
                return -1;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= bArr2.length) {
                    break;
                } else if (bArr[i + i2] != bArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                return i;
            }
            i++;
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == ID_BLOCK_GROUP) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (i == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == ID_CONTENT_ENCODING) {
        } else {
            if (i == ID_SEGMENT) {
                long j3 = this.segmentContentPosition;
                if (j3 == -1 || j3 == j) {
                    this.segmentContentPosition = j;
                    this.segmentContentSize = j2;
                    return;
                }
                throw new ParserException("Multiple Segment elements not supported");
            } else if (i == ID_CUES) {
                this.cueTimesUs = new LongArray();
                this.cueClusterPositions = new LongArray();
            } else if (i != ID_CLUSTER || this.sentSeekMap) {
            } else {
                if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                    this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                    this.sentSeekMap = true;
                    return;
                }
                this.seekForCues = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void endMasterElement(int i) throws ParserException {
        if (i != ID_BLOCK_GROUP) {
            if (i == ID_TRACK_ENTRY) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    Track track = this.currentTrack;
                    track.initializeOutput(this.extractorOutput, track.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i == ID_SEEK) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == ID_CUES) {
                            this.cuesContentPosition = j;
                            return;
                        }
                        return;
                    }
                }
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (i != ID_CONTENT_ENCODING) {
                if (i != ID_CONTENT_ENCODINGS) {
                    if (i == 357149030) {
                        if (this.timecodeScale == C.TIME_UNSET) {
                            this.timecodeScale = C.MICROS_PER_SECOND;
                        }
                        long j2 = this.durationTimecode;
                        if (j2 != C.TIME_UNSET) {
                            this.durationUs = scaleTimecodeToUs(j2);
                        }
                    } else if (i != ID_TRACKS) {
                        if (i == ID_CUES && !this.sentSeekMap) {
                            this.extractorOutput.seekMap(buildSeekMap());
                            this.sentSeekMap = true;
                        }
                    } else if (this.tracks.size() != 0) {
                        this.extractorOutput.endTracks();
                    } else {
                        throw new ParserException("No valid tracks were found");
                    }
                } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
            } else if (!this.currentTrack.hasContentEncryption) {
            } else {
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void integerElement(int i, long j) throws ParserException {
        if (i != ID_CONTENT_ENCODING_ORDER) {
            if (i != ID_CONTENT_ENCODING_SCOPE) {
                boolean z = false;
                switch (i) {
                    case ID_TRACK_TYPE /*{ENCODED_INT: 131}*/:
                        this.currentTrack.type = (int) j;
                        return;
                    case ID_FLAG_DEFAULT /*{ENCODED_INT: 136}*/:
                        Track track = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track.flagForced = z;
                        return;
                    case ID_BLOCK_DURATION /*{ENCODED_INT: 155}*/:
                        this.blockDurationUs = scaleTimecodeToUs(j);
                        return;
                    case ID_CHANNELS /*{ENCODED_INT: 159}*/:
                        this.currentTrack.channelCount = (int) j;
                        return;
                    case ID_PIXEL_WIDTH /*{ENCODED_INT: 176}*/:
                        this.currentTrack.width = (int) j;
                        return;
                    case ID_CUE_TIME /*{ENCODED_INT: 179}*/:
                        this.cueTimesUs.add(scaleTimecodeToUs(j));
                        return;
                    case ID_PIXEL_HEIGHT /*{ENCODED_INT: 186}*/:
                        this.currentTrack.height = (int) j;
                        return;
                    case ID_TRACK_NUMBER /*{ENCODED_INT: 215}*/:
                        this.currentTrack.number = (int) j;
                        return;
                    case ID_TIME_CODE /*{ENCODED_INT: 231}*/:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j);
                        return;
                    case ID_CUE_CLUSTER_POSITION /*{ENCODED_INT: 241}*/:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            this.cueClusterPositions.add(j);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case ID_REFERENCE_BLOCK /*{ENCODED_INT: 251}*/:
                        this.sampleSeenReferenceBlock = true;
                        return;
                    case ID_CONTENT_COMPRESSION_ALGORITHM /*{ENCODED_INT: 16980}*/:
                        if (j != 3) {
                            throw new ParserException("ContentCompAlgo " + j + " not supported");
                        }
                        return;
                    case ID_DOC_TYPE_READ_VERSION /*{ENCODED_INT: 17029}*/:
                        if (j < 1 || j > 2) {
                            throw new ParserException("DocTypeReadVersion " + j + " not supported");
                        }
                        return;
                    case ID_EBML_READ_VERSION /*{ENCODED_INT: 17143}*/:
                        if (j != 1) {
                            throw new ParserException("EBMLReadVersion " + j + " not supported");
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_ALGORITHM /*{ENCODED_INT: 18401}*/:
                        if (j != 5) {
                            throw new ParserException("ContentEncAlgo " + j + " not supported");
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*{ENCODED_INT: 18408}*/:
                        if (j != 1) {
                            throw new ParserException("AESSettingsCipherMode " + j + " not supported");
                        }
                        return;
                    case ID_SEEK_POSITION /*{ENCODED_INT: 21420}*/:
                        this.seekEntryPosition = j + this.segmentContentPosition;
                        return;
                    case ID_STEREO_MODE /*{ENCODED_INT: 21432}*/:
                        int i2 = (int) j;
                        if (i2 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i2 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i2 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i2 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DISPLAY_WIDTH /*{ENCODED_INT: 21680}*/:
                        this.currentTrack.displayWidth = (int) j;
                        return;
                    case ID_DISPLAY_UNIT /*{ENCODED_INT: 21682}*/:
                        this.currentTrack.displayUnit = (int) j;
                        return;
                    case ID_DISPLAY_HEIGHT /*{ENCODED_INT: 21690}*/:
                        this.currentTrack.displayHeight = (int) j;
                        return;
                    case ID_FLAG_FORCED /*{ENCODED_INT: 21930}*/:
                        Track track2 = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track2.flagDefault = z;
                        return;
                    case ID_CODEC_DELAY /*{ENCODED_INT: 22186}*/:
                        this.currentTrack.codecDelayNs = j;
                        return;
                    case ID_SEEK_PRE_ROLL /*{ENCODED_INT: 22203}*/:
                        this.currentTrack.seekPreRollNs = j;
                        return;
                    case ID_AUDIO_BIT_DEPTH /*{ENCODED_INT: 25188}*/:
                        this.currentTrack.audioBitDepth = (int) j;
                        return;
                    case ID_DEFAULT_DURATION /*{ENCODED_INT: 2352003}*/:
                        this.currentTrack.defaultSampleDurationNs = (int) j;
                        return;
                    case ID_TIMECODE_SCALE /*{ENCODED_INT: 2807729}*/:
                        this.timecodeScale = j;
                        return;
                    default:
                        switch (i) {
                            case ID_COLOUR_RANGE /*{ENCODED_INT: 21945}*/:
                                int i3 = (int) j;
                                if (i3 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i3 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_COLOUR_TRANSFER /*{ENCODED_INT: 21946}*/:
                                int i4 = (int) j;
                                if (i4 != 1) {
                                    if (i4 == 16) {
                                        this.currentTrack.colorTransfer = 6;
                                        return;
                                    } else if (i4 == 18) {
                                        this.currentTrack.colorTransfer = 7;
                                        return;
                                    } else if (!(i4 == 6 || i4 == 7)) {
                                        return;
                                    }
                                }
                                this.currentTrack.colorTransfer = 3;
                                return;
                            case ID_COLOUR_PRIMARIES /*{ENCODED_INT: 21947}*/:
                                Track track3 = this.currentTrack;
                                track3.hasColorInfo = true;
                                int i5 = (int) j;
                                if (i5 == 1) {
                                    track3.colorSpace = 1;
                                    return;
                                } else if (i5 == 9) {
                                    track3.colorSpace = 6;
                                    return;
                                } else if (i5 == 4 || i5 == 5 || i5 == 6 || i5 == 7) {
                                    this.currentTrack.colorSpace = 2;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_MAX_CLL /*{ENCODED_INT: 21948}*/:
                                this.currentTrack.maxContentLuminance = (int) j;
                                return;
                            case ID_MAX_FALL /*{ENCODED_INT: 21949}*/:
                                this.currentTrack.maxFrameAverageLuminance = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                throw new ParserException("ContentEncodingScope " + j + " not supported");
            }
        } else if (j != 0) {
            throw new ParserException("ContentEncodingOrder " + j + " not supported");
        }
    }

    /* access modifiers changed from: package-private */
    public void floatElement(int i, double d) {
        if (i == ID_SAMPLING_FREQUENCY) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*{ENCODED_INT: 21969}*/:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*{ENCODED_INT: 21970}*/:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*{ENCODED_INT: 21971}*/:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*{ENCODED_INT: 21972}*/:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*{ENCODED_INT: 21973}*/:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*{ENCODED_INT: 21974}*/:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*{ENCODED_INT: 21975}*/:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*{ENCODED_INT: 21976}*/:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*{ENCODED_INT: 21977}*/:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*{ENCODED_INT: 21978}*/:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    return;
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: package-private */
    public void stringElement(int i, String str) throws ParserException {
        OculusExtractorsFactory.EventListener eventListener2;
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != ID_DOC_TYPE) {
            if (i == ID_TAG_STRING) {
                String str2 = this.lastTagName;
                char c = 65535;
                int hashCode = str2.hashCode();
                if (hashCode != 66663409) {
                    if (hashCode == 1836554443 && str2.equals(SPHERICAL_V1_TAG)) {
                        c = 1;
                    }
                } else if (str2.equals(FB360_METADATA_TAG)) {
                    c = 0;
                }
                if (c == 0) {
                    OculusExtractorsFactory.EventListener eventListener3 = this.eventListener;
                    if (eventListener3 != null) {
                        eventListener3.onMovieMetadataXml(str);
                    }
                } else if (c == 1 && (eventListener2 = this.eventListener) != null) {
                    eventListener2.onSphericalV1Xml(str);
                }
            } else if (i == ID_TAG_NAME) {
                this.lastTagName = str;
            } else if (i == ID_LANGUAGE) {
                this.currentTrack.language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw new ParserException("DocType " + str + " not supported");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01fe, code lost:
        throw new com.oculus.android.exoplayer2.ParserException("EBML lacing sample size out of range.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r20, int r21, com.oculus.android.exoplayer2.extractor.ExtractorInput r22) throws java.io.IOException, java.lang.InterruptedException {
        /*
        // Method dump skipped, instructions count: 695
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.extractor.mkv.OculusMatroskaExtractor.binaryElement(int, int, com.oculus.android.exoplayer2.extractor.ExtractorInput):void");
    }

    private void commitSampleToOutput(Track track, long j) {
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            commitSubtitleSample(track, SUBRIP_TIMECODE_FORMAT, 19, SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR, SUBRIP_TIMECODE_EMPTY);
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            commitSubtitleSample(track, SSA_TIMECODE_FORMAT, 21, SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR, SSA_TIMECODE_EMPTY);
        }
        track.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track.cryptoData);
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.reset(Arrays.copyOf(parsableByteArray.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
        } else {
            TrackOutput trackOutput = track.output;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.data[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z) {
                                i3 = 0;
                            }
                            bArr[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.data, 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            int limit = i + this.sampleStrippedBytes.limit();
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit) {
                        break;
                    }
                    readToOutput(extractorInput, trackOutput, limit - i9);
                }
            } else {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        readToTarget(extractorInput, bArr2, i11, i10);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        this.sampleCurrentNalBytesRemaining = i12 - readToOutput(extractorInput, trackOutput, i12);
                    }
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    private void commitSubtitleSample(Track track, String str, int i, long j, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i, j, bArr);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subtitleSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static void setSampleDuration(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        byte[] bArr3;
        if (j == C.TIME_UNSET) {
            bArr3 = bArr2;
        } else {
            int i2 = (int) (j / 3600000000L);
            long j3 = j - (((long) (i2 * 3600)) * C.MICROS_PER_SECOND);
            int i3 = (int) (j3 / 60000000);
            long j4 = j3 - (((long) (i3 * 60)) * C.MICROS_PER_SECOND);
            int i4 = (int) (j4 / C.MICROS_PER_SECOND);
            bArr3 = Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf((int) ((j4 - (((long) i4) * C.MICROS_PER_SECOND)) / j2))));
        }
        System.arraycopy(bArr3, 0, bArr, i, bArr2.length);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int i2;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            i2 = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, i2);
        } else {
            i2 = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += i2;
        this.sampleBytesWritten += i2;
        return i2;
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == C.TIME_UNSET || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            OculusExtractorsFactory.EventListener eventListener2 = this.eventListener;
            if (eventListener2 != null) {
                eventListener2.onFindVideoSeekTimestamp(jArr3[i2]);
            }
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != C.TIME_UNSET) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_THEORA.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP2.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_ASS.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str) || CODEC_ID_DVBSUB.equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public int getElementType(int i) {
            return OculusMatroskaExtractor.this.getElementType(i);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public boolean isLevel1Element(int i) {
            return OculusMatroskaExtractor.this.isLevel1Element(i);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public void startMasterElement(int i, long j, long j2) throws ParserException {
            OculusMatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public void endMasterElement(int i) throws ParserException {
            OculusMatroskaExtractor.this.endMasterElement(i);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public void integerElement(int i, long j) throws ParserException {
            OculusMatroskaExtractor.this.integerElement(i, j);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public void floatElement(int i, double d) throws ParserException {
            OculusMatroskaExtractor.this.floatElement(i, d);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public void stringElement(int i, String str) throws ParserException {
            OculusMatroskaExtractor.this.stringElement(i, str);
        }

        @Override // com.oculus.video.extractor.mkv.EbmlReaderOutput
        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            OculusMatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    /* access modifiers changed from: private */
    public static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public void initializeOutput(ExtractorOutput extractorOutput, int i) throws ParserException {
            char c;
            int i2;
            int i3;
            List<byte[]> list;
            String str;
            Format format;
            int i4;
            List<byte[]> list2;
            String str2;
            int pcmEncoding;
            String str3 = this.codecId;
            int i5 = 0;
            int i6 = 3;
            switch (str3.hashCode()) {
                case -2095576542:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_MPEG4_AP)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -2095575984:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_MPEG4_SP)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1985379776:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_ACM)) {
                        c = 22;
                        break;
                    }
                    c = 65535;
                    break;
                case -1784763192:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_TRUEHD)) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case -1730367663:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_VORBIS)) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case -1482641358:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_MP2)) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case -1482641357:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_MP3)) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case -1373388978:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_FOURCC)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -933872740:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_DVBSUB)) {
                        c = 28;
                        break;
                    }
                    c = 65535;
                    break;
                case -538363189:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_MPEG4_ASP)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -538363109:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_H264)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -425012669:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_VOBSUB)) {
                        c = 26;
                        break;
                    }
                    c = 65535;
                    break;
                case -356037306:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_DTS_LOSSLESS)) {
                        c = 20;
                        break;
                    }
                    c = 65535;
                    break;
                case 62923557:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_AAC)) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 62923603:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_AC3)) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case 62927045:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_DTS)) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case 82338133:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_VP8)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 82338134:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_VP9)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 99146302:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_PGS)) {
                        c = 27;
                        break;
                    }
                    c = 65535;
                    break;
                case 444813526:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_THEORA)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 542569478:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_DTS_EXPRESS)) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case 725957860:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_PCM_INT_LIT)) {
                        c = 23;
                        break;
                    }
                    c = 65535;
                    break;
                case 738597099:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_ASS)) {
                        c = 25;
                        break;
                    }
                    c = 65535;
                    break;
                case 855502857:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_H265)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1422270023:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_SUBRIP)) {
                        c = 24;
                        break;
                    }
                    c = 65535;
                    break;
                case 1809237540:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_MPEG2)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1950749482:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_E_AC3)) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case 1950789798:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_FLAC)) {
                        c = 21;
                        break;
                    }
                    c = 65535;
                    break;
                case 1951062397:
                    if (str3.equals(OculusMatroskaExtractor.CODEC_ID_OPUS)) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            ColorInfo colorInfo = null;
            switch (c) {
                case 0:
                    str = MimeTypes.VIDEO_VP8;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 1:
                    str = MimeTypes.VIDEO_VP9;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 2:
                    str = MimeTypes.VIDEO_MPEG2;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 3:
                case 4:
                case 5:
                    byte[] bArr = this.codecPrivate;
                    if (bArr == null) {
                        list2 = null;
                    } else {
                        list2 = Collections.singletonList(bArr);
                    }
                    str2 = MimeTypes.VIDEO_MP4V;
                    i3 = -1;
                    i2 = -1;
                    list = list2;
                    str = str2;
                    break;
                case 6:
                    AvcConfig parse = AvcConfig.parse(new ParsableByteArray(this.codecPrivate));
                    list = parse.initializationData;
                    this.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                    str = MimeTypes.VIDEO_H264;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 7:
                    HevcConfig parse2 = HevcConfig.parse(new ParsableByteArray(this.codecPrivate));
                    list = parse2.initializationData;
                    this.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                    str = MimeTypes.VIDEO_H265;
                    i3 = -1;
                    i2 = -1;
                    break;
                case '\b':
                    list2 = parseFourCcVc1Private(new ParsableByteArray(this.codecPrivate));
                    if (list2 == null) {
                        Log.w(OculusMatroskaExtractor.TAG, "Unsupported FourCC. Setting mimeType to video/x-unknown");
                        list = list2;
                        i3 = -1;
                        i2 = -1;
                        str = MimeTypes.VIDEO_UNKNOWN;
                        break;
                    } else {
                        str2 = MimeTypes.VIDEO_VC1;
                        i3 = -1;
                        i2 = -1;
                        list = list2;
                        str = str2;
                        break;
                    }
                case '\t':
                    i3 = -1;
                    i2 = -1;
                    str = MimeTypes.VIDEO_UNKNOWN;
                    list = null;
                    break;
                case '\n':
                    list = parseVorbisCodecPrivate(this.codecPrivate);
                    i3 = 8192;
                    i2 = -1;
                    str = MimeTypes.AUDIO_VORBIS;
                    break;
                case 11:
                    ArrayList arrayList = new ArrayList(3);
                    arrayList.add(this.codecPrivate);
                    arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                    arrayList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                    i3 = OculusMatroskaExtractor.OPUS_MAX_INPUT_SIZE;
                    str = MimeTypes.AUDIO_OPUS;
                    i2 = -1;
                    list = arrayList;
                    break;
                case '\f':
                    list2 = Collections.singletonList(this.codecPrivate);
                    str2 = MimeTypes.AUDIO_AAC;
                    i3 = -1;
                    i2 = -1;
                    list = list2;
                    str = str2;
                    break;
                case '\r':
                    str = MimeTypes.AUDIO_MPEG_L2;
                    i2 = -1;
                    list = null;
                    i3 = 4096;
                    break;
                case 14:
                    str = MimeTypes.AUDIO_MPEG;
                    i2 = -1;
                    list = null;
                    i3 = 4096;
                    break;
                case 15:
                    str = MimeTypes.AUDIO_AC3;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 16:
                    str = MimeTypes.AUDIO_E_AC3;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 17:
                    str = MimeTypes.AUDIO_TRUEHD;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 18:
                case 19:
                    str = MimeTypes.AUDIO_DTS;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 20:
                    str = MimeTypes.AUDIO_DTS_HD;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case 21:
                    list2 = Collections.singletonList(this.codecPrivate);
                    str2 = MimeTypes.AUDIO_FLAC;
                    i3 = -1;
                    i2 = -1;
                    list = list2;
                    str = str2;
                    break;
                case 22:
                    if (parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                        pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                        if (pcmEncoding == 0) {
                            Log.w(OculusMatroskaExtractor.TAG, "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        }
                        i2 = pcmEncoding;
                        i3 = -1;
                        str = MimeTypes.AUDIO_RAW;
                        list = null;
                        break;
                    } else {
                        Log.w(OculusMatroskaExtractor.TAG, "Non-PCM MS/ACM is unsupported. Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                    }
                    i3 = -1;
                    i2 = -1;
                    str = MimeTypes.AUDIO_UNKNOWN;
                    list = null;
                case 23:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        Log.w(OculusMatroskaExtractor.TAG, "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to " + MimeTypes.AUDIO_UNKNOWN);
                        i3 = -1;
                        i2 = -1;
                        str = MimeTypes.AUDIO_UNKNOWN;
                        list = null;
                        break;
                    }
                    i2 = pcmEncoding;
                    i3 = -1;
                    str = MimeTypes.AUDIO_RAW;
                    list = null;
                case 24:
                    i3 = -1;
                    i2 = -1;
                    str = MimeTypes.APPLICATION_SUBRIP;
                    list = null;
                    break;
                case 25:
                    i3 = -1;
                    i2 = -1;
                    str = MimeTypes.TEXT_SSA;
                    list = null;
                    break;
                case 26:
                    list = Collections.singletonList(this.codecPrivate);
                    str = MimeTypes.APPLICATION_VOBSUB;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 27:
                    str = MimeTypes.APPLICATION_PGS;
                    i3 = -1;
                    i2 = -1;
                    list = null;
                    break;
                case MotionEventCompat.AXIS_RELATIVE_Y:
                    byte[] bArr2 = this.codecPrivate;
                    list2 = Collections.singletonList(new byte[]{bArr2[0], bArr2[1], bArr2[2], bArr2[3]});
                    str2 = MimeTypes.APPLICATION_DVBSUBS;
                    i3 = -1;
                    i2 = -1;
                    list = list2;
                    str = str2;
                    break;
                default:
                    throw new ParserException("Unrecognized codec identifier.");
            }
            int i7 = (this.flagDefault ? 1 : 0) | 0;
            if (this.flagForced) {
                i5 = 2;
            }
            int i8 = i5 | i7;
            if (MimeTypes.isAudio(str)) {
                format = Format.createAudioSampleFormat(Integer.toString(i), str, null, -1, i3, this.channelCount, this.sampleRate, i2, list, this.drmInitData, i8, this.language);
                i6 = 1;
            } else if (MimeTypes.isVideo(str)) {
                if (this.displayUnit == 0) {
                    int i9 = this.displayWidth;
                    if (i9 == -1) {
                        i9 = this.width;
                    }
                    this.displayWidth = i9;
                    int i10 = this.displayHeight;
                    if (i10 == -1) {
                        i10 = this.height;
                    }
                    this.displayHeight = i10;
                }
                float f = -1.0f;
                int i11 = this.displayWidth;
                if (!(i11 == -1 || (i4 = this.displayHeight) == -1)) {
                    f = ((float) (this.height * i11)) / ((float) (this.width * i4));
                }
                if (this.hasColorInfo) {
                    colorInfo = new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, getHdrStaticInfo());
                }
                format = Format.createVideoSampleFormat(Integer.toString(i), str, null, -1, i3, this.width, this.height, -1.0f, list, -1, f, this.projectionData, this.stereoMode, colorInfo, this.drmInitData);
                i6 = 2;
            } else if (MimeTypes.APPLICATION_SUBRIP.equals(str)) {
                format = Format.createTextSampleFormat(Integer.toString(i), str, i8, this.language, this.drmInitData);
            } else if (MimeTypes.TEXT_SSA.equals(str)) {
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(OculusMatroskaExtractor.SSA_DIALOGUE_FORMAT);
                arrayList2.add(this.codecPrivate);
                format = Format.createTextSampleFormat(Integer.toString(i), str, null, -1, i8, this.language, -1, this.drmInitData, Long.MAX_VALUE, arrayList2);
            } else if (MimeTypes.APPLICATION_VOBSUB.equals(str) || MimeTypes.APPLICATION_PGS.equals(str) || MimeTypes.APPLICATION_DVBSUBS.equals(str)) {
                format = Format.createImageSampleFormat(Integer.toString(i), str, null, -1, 1, list, this.language, this.drmInitData);
            } else {
                throw new ParserException("Unexpected MIME type.");
            }
            this.output = extractorOutput.track(this.number, i6);
            this.output.format(format);
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                if (parsableByteArray.readLittleEndianUnsignedInt() != 826496599) {
                    return null;
                }
                byte[] bArr = parsableByteArray.data;
                for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                    if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] == 2) {
                    int i = 0;
                    int i2 = 1;
                    while (bArr[i2] == -1) {
                        i += 255;
                        i2++;
                    }
                    int i3 = i2 + 1;
                    int i4 = i + bArr[i2];
                    int i5 = 0;
                    while (bArr[i3] == -1) {
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + bArr[i3];
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != OculusMatroskaExtractor.WAVE_FORMAT_EXTENSIBLE) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                return parsableByteArray.readLong() == OculusMatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == OculusMatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits();
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}
