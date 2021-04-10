package com.oculus.android.exoplayer2.source.smoothstreaming;

import com.oculus.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.oculus.android.exoplayer2.source.chunk.ChunkSource;
import com.oculus.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.LoaderErrorThrower;

public interface SsChunkSource extends ChunkSource {

    public interface Factory {
        SsChunkSource createChunkSource(LoaderErrorThrower loaderErrorThrower, SsManifest ssManifest, int i, TrackSelection trackSelection, TrackEncryptionBox[] trackEncryptionBoxArr);
    }

    void updateManifest(SsManifest ssManifest);
}
