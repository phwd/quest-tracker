package com.oculus.video.extractor.hls;

import android.util.SparseArray;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.ts.Ac3Reader;
import com.oculus.android.exoplayer2.extractor.ts.AdtsReader;
import com.oculus.android.exoplayer2.extractor.ts.DtsReader;
import com.oculus.android.exoplayer2.extractor.ts.DvbSubtitleReader;
import com.oculus.android.exoplayer2.extractor.ts.H262Reader;
import com.oculus.android.exoplayer2.extractor.ts.Id3Reader;
import com.oculus.android.exoplayer2.extractor.ts.LatmReader;
import com.oculus.android.exoplayer2.extractor.ts.MpegAudioReader;
import com.oculus.android.exoplayer2.extractor.ts.PesReader;
import com.oculus.android.exoplayer2.extractor.ts.SectionReader;
import com.oculus.android.exoplayer2.extractor.ts.SpliceInfoSectionReader;
import com.oculus.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OculusTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List<Format> closedCaptionFormats;
    private final int flags;
    private Format format;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public OculusTsPayloadReaderFactory() {
        this(0);
    }

    public OculusTsPayloadReaderFactory(int i) {
        this(i, Collections.emptyList(), null);
    }

    public OculusTsPayloadReaderFactory(int i, List<Format> list, Format format2) {
        this.flags = i;
        if (!isSet(32) && list.isEmpty()) {
            list = Collections.singletonList(Format.createTextSampleFormat(null, MimeTypes.APPLICATION_CEA608, 0, null));
        }
        this.closedCaptionFormats = list;
        this.format = format2;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public TsPayloadReader createPayloadReader(int i, TsPayloadReader.EsInfo esInfo) {
        if (i == 2) {
            return new PesReader(new H262Reader());
        }
        if (i == 3 || i == 4) {
            return new PesReader(new MpegAudioReader(esInfo.language));
        }
        if (i != 15) {
            if (i != 17) {
                if (i == 21) {
                    return new PesReader(new Id3Reader());
                }
                if (i != 27) {
                    if (i == 36) {
                        return new PesReader(new OculusH265Reader(buildSeiReader(esInfo)));
                    }
                    if (i == 89) {
                        return new PesReader(new DvbSubtitleReader(esInfo.dvbSubtitleInfos));
                    }
                    if (i != 138) {
                        if (i != 129) {
                            if (i != 130) {
                                if (i != 134) {
                                    if (i != 135) {
                                        return null;
                                    }
                                } else if (isSet(16)) {
                                    return null;
                                } else {
                                    return new SectionReader(new SpliceInfoSectionReader());
                                }
                            }
                        }
                        return new PesReader(new Ac3Reader(esInfo.language));
                    }
                    return new PesReader(new DtsReader(esInfo.language));
                } else if (isSet(4)) {
                    return null;
                } else {
                    return new PesReader(new OculusH264Reader(buildSeiReader(esInfo), isSet(1), isSet(8), this.format));
                }
            } else if (isSet(2)) {
                return null;
            } else {
                return new PesReader(new LatmReader(esInfo.language));
            }
        } else if (isSet(2)) {
            return null;
        } else {
            return new PesReader(new AdtsReader(false, esInfo.language));
        }
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        int i;
        String str;
        if (isSet(32)) {
            return new SeiReader(this.closedCaptionFormats);
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> list = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (readUnsignedByte == 134) {
                list = new ArrayList<>();
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i2 = 0; i2 < readUnsignedByte2; i2++) {
                    String readString = parsableByteArray.readString(3);
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    if ((readUnsignedByte3 & 128) != 0) {
                        i = readUnsignedByte3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        i = 1;
                        str = MimeTypes.APPLICATION_CEA608;
                    }
                    list.add(Format.createTextSampleFormat((String) null, str, (String) null, -1, 0, readString, i, (DrmInitData) null));
                    parsableByteArray.skipBytes(2);
                }
            }
            parsableByteArray.setPosition(position);
        }
        return new SeiReader(list);
    }

    private boolean isSet(int i) {
        return (i & this.flags) != 0;
    }
}
