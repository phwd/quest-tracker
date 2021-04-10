package com.oculus.android.exoplayer2.extractor;

import com.oculus.android.exoplayer2.extractor.flv.FlvExtractor;
import com.oculus.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.oculus.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.oculus.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.oculus.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.oculus.android.exoplayer2.extractor.ogg.OggExtractor;
import com.oculus.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.oculus.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.oculus.android.exoplayer2.extractor.ts.PsExtractor;
import com.oculus.android.exoplayer2.extractor.ts.TsExtractor;
import com.oculus.android.exoplayer2.extractor.wav.WavExtractor;
import java.lang.reflect.Constructor;

public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final Constructor<? extends Extractor> FLAC_EXTRACTOR_CONSTRUCTOR;
    private int fragmentedMp4Flags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private int tsMode = 1;

    static {
        Constructor<? extends U> constructor;
        try {
            constructor = Class.forName("com.oculus.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[0]);
        } catch (ClassNotFoundException unused) {
            constructor = null;
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating FLAC extension", e);
        }
        FLAC_EXTRACTOR_CONSTRUCTOR = constructor;
    }

    public synchronized DefaultExtractorsFactory setMatroskaExtractorFlags(int i) {
        this.matroskaFlags = i;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp4ExtractorFlags(int i) {
        this.mp4Flags = i;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFragmentedMp4ExtractorFlags(int i) {
        this.fragmentedMp4Flags = i;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp3ExtractorFlags(int i) {
        this.mp3Flags = i;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorMode(int i) {
        this.tsMode = i;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorFlags(int i) {
        this.tsFlags = i;
        return this;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors() {
        Extractor[] extractorArr;
        extractorArr = new Extractor[(FLAC_EXTRACTOR_CONSTRUCTOR == null ? 11 : 12)];
        extractorArr[0] = new MatroskaExtractor(this.matroskaFlags);
        extractorArr[1] = new FragmentedMp4Extractor(this.fragmentedMp4Flags);
        extractorArr[2] = new Mp4Extractor(this.mp4Flags);
        extractorArr[3] = new Mp3Extractor(this.mp3Flags);
        extractorArr[4] = new AdtsExtractor();
        extractorArr[5] = new Ac3Extractor();
        extractorArr[6] = new TsExtractor(this.tsMode, this.tsFlags);
        extractorArr[7] = new FlvExtractor();
        extractorArr[8] = new OggExtractor();
        extractorArr[9] = new PsExtractor();
        extractorArr[10] = new WavExtractor();
        if (FLAC_EXTRACTOR_CONSTRUCTOR != null) {
            try {
                extractorArr[11] = (Extractor) FLAC_EXTRACTOR_CONSTRUCTOR.newInstance(new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating FLAC extractor", e);
            }
        }
        return extractorArr;
    }
}
