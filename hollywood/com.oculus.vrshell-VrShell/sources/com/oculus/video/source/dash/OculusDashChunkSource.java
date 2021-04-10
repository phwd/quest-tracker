package com.oculus.video.source.dash;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.chunk.Chunk;
import com.oculus.android.exoplayer2.source.chunk.ChunkHolder;
import com.oculus.android.exoplayer2.source.chunk.InitializationChunk;
import com.oculus.android.exoplayer2.source.chunk.MediaChunk;
import com.oculus.android.exoplayer2.source.dash.DashChunkSource;
import com.oculus.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.LoaderErrorThrower;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.video.trackselection.OculusAdaptiveTrackSelection;

public class OculusDashChunkSource extends DefaultDashChunkSource {
    private final Callback callback;

    public interface Callback {
        void onVideoSegmentIndexLoaded();

        boolean shouldUseAbr4Vbr();
    }

    public static final class Factory implements DashChunkSource.Factory {
        private final Callback callback;
        private final DataSource.Factory dataSourceFactory;

        public Factory(DataSource.Factory factory, Callback callback2) {
            this.dataSourceFactory = factory;
            this.callback = callback2;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, long j, boolean z, boolean z2, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
            return new OculusDashChunkSource(loaderErrorThrower, dashManifest, i, iArr, trackSelection, i2, this.dataSourceFactory.createDataSource(), j, 1, z, z2, this.callback, playerTrackEmsgHandler);
        }
    }

    private OculusDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, DataSource dataSource, long j, int i3, boolean z, boolean z2, Callback callback2, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
        super(loaderErrorThrower, dashManifest, i, iArr, trackSelection, i2, dataSource, j, i3, z, z2, playerTrackEmsgHandler);
        this.callback = callback2;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DefaultDashChunkSource, com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public void getNextChunk(MediaChunk mediaChunk, long j, long j2, ChunkHolder chunkHolder) {
        Callback callback2 = this.callback;
        if (callback2 == null || !callback2.shouldUseAbr4Vbr() || !(this.trackSelection instanceof OculusAdaptiveTrackSelection)) {
            super.getNextChunk(mediaChunk, j, j2, chunkHolder);
            return;
        }
        OculusAdaptiveTrackSelection oculusAdaptiveTrackSelection = (OculusAdaptiveTrackSelection) this.trackSelection;
        oculusAdaptiveTrackSelection.clearSelectedIndexOffset();
        do {
            super.getNextChunk(mediaChunk, j, j2, chunkHolder);
        } while (oculusAdaptiveTrackSelection.updateSelectedIndexOffset(chunkHolder.chunk));
        oculusAdaptiveTrackSelection.clearSelectedIndexOffset();
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DefaultDashChunkSource, com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        super.onChunkLoadCompleted(chunk);
        if (this.callback != null && (chunk instanceof InitializationChunk)) {
            Format format = chunk.trackFormat;
            if (MimeTypes.isVideo(format.containerMimeType) && this.representationHolders[this.trackSelection.indexOf(format)].segmentIndex != null) {
                this.callback.onVideoSegmentIndexLoaded();
            }
        }
    }
}
