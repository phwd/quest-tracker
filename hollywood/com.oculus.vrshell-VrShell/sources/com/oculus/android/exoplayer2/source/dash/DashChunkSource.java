package com.oculus.android.exoplayer2.source.dash;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.source.chunk.ChunkSource;
import com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.LoaderErrorThrower;

public interface DashChunkSource extends ChunkSource {

    public interface Factory {
        DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, long j, boolean z, boolean z2, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler);
    }

    void updateManifest(DashManifest dashManifest, int i);
}
