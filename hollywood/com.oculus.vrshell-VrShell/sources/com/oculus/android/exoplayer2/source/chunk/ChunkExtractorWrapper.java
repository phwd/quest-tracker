package com.oculus.android.exoplayer2.source.chunk;

import android.util.SparseArray;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.extractor.DummyTrackOutput;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

public final class ChunkExtractorWrapper implements ExtractorOutput {
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs = new SparseArray<>();
    public final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private TrackOutputProvider trackOutputProvider;

    public interface TrackOutputProvider {
        TrackOutput track(int i, int i2);
    }

    public ChunkExtractorWrapper(Extractor extractor2, int i, Format format) {
        this.extractor = extractor2;
        this.primaryTrackType = i;
        this.primaryTrackManifestFormat = format;
    }

    public SeekMap getSeekMap() {
        return this.seekMap;
    }

    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    public void init(TrackOutputProvider trackOutputProvider2) {
        this.trackOutputProvider = trackOutputProvider2;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            this.extractorInitialized = true;
            return;
        }
        this.extractor.seek(0, 0);
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            this.bindingTrackOutputs.valueAt(i).bind(trackOutputProvider2);
        }
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(i);
        if (bindingTrackOutput == null) {
            Assertions.checkState(this.sampleFormats == null);
            bindingTrackOutput = new BindingTrackOutput(i, i2, i2 == this.primaryTrackType ? this.primaryTrackManifestFormat : null);
            bindingTrackOutput.bind(this.trackOutputProvider);
            this.bindingTrackOutputs.put(i, bindingTrackOutput);
        }
        return bindingTrackOutput;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            formatArr[i] = this.bindingTrackOutputs.valueAt(i).sampleFormat;
        }
        this.sampleFormats = formatArr;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
    }

    private static final class BindingTrackOutput implements TrackOutput {
        private final int id;
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int i, int i2, Format format) {
            this.id = i;
            this.type = i2;
            this.manifestFormat = format;
        }

        public void bind(TrackOutputProvider trackOutputProvider) {
            if (trackOutputProvider == null) {
                this.trackOutput = new DummyTrackOutput();
                return;
            }
            this.trackOutput = trackOutputProvider.track(this.id, this.type);
            Format format = this.sampleFormat;
            if (format != null) {
                this.trackOutput.format(format);
            }
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public void format(Format format) {
            Format format2 = this.manifestFormat;
            if (format2 != null) {
                format = format.copyWithManifestFormatInfo(format2);
            }
            this.sampleFormat = format;
            this.trackOutput.format(this.sampleFormat);
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws IOException, InterruptedException {
            return this.trackOutput.sampleData(extractorInput, i, z);
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i) {
            this.trackOutput.sampleData(parsableByteArray, i);
        }

        @Override // com.oculus.android.exoplayer2.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            this.trackOutput.sampleMetadata(j, i, i2, i3, cryptoData);
        }
    }
}
