package com.oculus.video.extractor;

import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorsFactory;
import com.oculus.android.exoplayer2.extractor.flv.FlvExtractor;
import com.oculus.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.oculus.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.oculus.android.exoplayer2.extractor.ogg.OggExtractor;
import com.oculus.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.oculus.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.oculus.android.exoplayer2.extractor.ts.PsExtractor;
import com.oculus.android.exoplayer2.extractor.ts.TsExtractor;
import com.oculus.android.exoplayer2.extractor.wav.WavExtractor;
import com.oculus.video.extractor.mkv.OculusMatroskaExtractor;
import com.oculus.video.extractor.mp4.OculusMp4Extractor;
import com.oculus.video.extractor.source.OculusFrameworkSourceExtractor;
import java.lang.reflect.Constructor;

public final class OculusExtractorsFactory implements ExtractorsFactory {
    private static final Constructor<? extends Extractor> FLAC_EXTRACTOR_CONSTRUCTOR;
    private EventListener extractorEventListener;

    public interface EventListener {
        void onFindVideoSeekTimestamp(long j);

        void onMovieMetadataXml(String str);

        void onSphericalV1Xml(String str);
    }

    static {
        Constructor<? extends U> constructor;
        try {
            constructor = Class.forName("com.oculus.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[0]);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            constructor = null;
        }
        FLAC_EXTRACTOR_CONSTRUCTOR = constructor;
    }

    public OculusExtractorsFactory setExtractorEventListener(EventListener eventListener) {
        this.extractorEventListener = eventListener;
        return this;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors() {
        Extractor[] extractorArr;
        extractorArr = new Extractor[(FLAC_EXTRACTOR_CONSTRUCTOR == null ? 12 : 13)];
        extractorArr[0] = new OculusMatroskaExtractor().setEventListener(this.extractorEventListener);
        extractorArr[1] = new FragmentedMp4Extractor();
        extractorArr[2] = new OculusMp4Extractor(true).setEventListener(this.extractorEventListener);
        extractorArr[3] = new Mp3Extractor();
        extractorArr[4] = new AdtsExtractor();
        extractorArr[5] = new Ac3Extractor();
        extractorArr[6] = new TsExtractor();
        extractorArr[7] = new FlvExtractor();
        extractorArr[8] = new OggExtractor();
        extractorArr[9] = new PsExtractor();
        extractorArr[10] = new WavExtractor();
        extractorArr[11] = new OculusFrameworkSourceExtractor();
        if (FLAC_EXTRACTOR_CONSTRUCTOR != null) {
            try {
                extractorArr[12] = (Extractor) FLAC_EXTRACTOR_CONSTRUCTOR.newInstance(new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating FLAC extractor", e);
            }
        }
        return extractorArr;
    }
}
