package com.oculus.video.trackselection;

import android.util.Size;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.RendererCapabilities;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.oculus.android.exoplayer2.trackselection.FixedTrackSelection;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.video.Settings;
import com.oculus.video.audio.AudioChannelLayout;
import java.util.HashMap;
import java.util.Map;

public class OculusTrackSelector extends DefaultTrackSelector {
    private final AudioChannelLayout audioChannelLayout;
    private Size currentVideoResolution = null;
    private Map<Size, FrameDropRecord> frameDropInfo;
    private final Settings playbackSettings;
    private Size smallestVideoResolution = null;

    /* access modifiers changed from: private */
    public static class FrameDropRecord {
        long decodedFrameCount;
        long elapsedMs;
        long frameDrops;

        FrameDropRecord(long j, long j2, long j3) {
            this.frameDrops = j;
            this.decodedFrameCount = j2;
            this.elapsedMs = j3;
        }
    }

    OculusTrackSelector(TrackSelection.Factory factory, AudioChannelLayout audioChannelLayout2, Settings settings) {
        super(factory);
        this.audioChannelLayout = audioChannelLayout2;
        this.frameDropInfo = new HashMap();
        this.playbackSettings = settings;
    }

    public void onVideoSizeChange(int i, int i2) {
        this.currentVideoResolution = new Size(i, i2);
    }

    public void onDroppedDelayedFrame(long j, long j2, long j3) {
        Size size = this.currentVideoResolution;
        if (size != null) {
            if (!this.frameDropInfo.containsKey(size)) {
                this.frameDropInfo.put(this.currentVideoResolution, new FrameDropRecord(j, j2, j3));
            } else {
                FrameDropRecord frameDropRecord = this.frameDropInfo.get(this.currentVideoResolution);
                frameDropRecord.frameDrops += j;
                frameDropRecord.decodedFrameCount += j2;
                frameDropRecord.elapsedMs += j3;
            }
            FrameDropRecord frameDropRecord2 = this.frameDropInfo.get(this.currentVideoResolution);
            if (this.playbackSettings.useFrameDropBasedTrackSelection(frameDropRecord2.elapsedMs, (((float) frameDropRecord2.frameDrops) * 100.0f) / ((float) frameDropRecord2.decodedFrameCount)) && this.smallestVideoResolution != null) {
                DefaultTrackSelector.ParametersBuilder parametersBuilder = new DefaultTrackSelector.ParametersBuilder();
                DefaultTrackSelector.Parameters parameters = getParameters();
                int min = Math.min(this.currentVideoResolution.getWidth() - 1, parameters.maxVideoWidth);
                int min2 = Math.min(this.currentVideoResolution.getHeight() - 1, parameters.maxVideoHeight);
                int max = Math.max(min, this.smallestVideoResolution.getWidth());
                int max2 = Math.max(min2, this.smallestVideoResolution.getHeight());
                if (max < parameters.maxVideoWidth || max2 < parameters.maxVideoHeight) {
                    parametersBuilder.setMaxVideoSize(max, max2);
                    setParameters(parametersBuilder.build());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.trackselection.MappingTrackSelector, com.oculus.android.exoplayer2.trackselection.DefaultTrackSelector
    public TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException {
        int i;
        TrackGroup trackGroup;
        if (this.smallestVideoResolution == null) {
            long j = Long.MAX_VALUE;
            for (int i2 = 0; i2 < rendererCapabilitiesArr.length; i2++) {
                if (2 == rendererCapabilitiesArr[i2].getTrackType()) {
                    TrackGroupArray trackGroupArray = trackGroupArrayArr[i2];
                    long j2 = j;
                    int i3 = 0;
                    while (i3 < trackGroupArray.length) {
                        TrackGroup trackGroup2 = trackGroupArray.get(i3);
                        long j3 = j2;
                        for (int i4 = 0; i4 < trackGroup2.length; i4++) {
                            Format format = trackGroup2.getFormat(i4);
                            if (format != null) {
                                long j4 = (long) (format.width * format.height);
                                if (j4 > 0 && j3 > j4) {
                                    this.smallestVideoResolution = new Size(format.width, format.height);
                                    j3 = j4;
                                }
                            }
                        }
                        i3++;
                        j2 = j3;
                    }
                    j = j2;
                }
            }
        }
        TrackSelection[] selectTracks = super.selectTracks(rendererCapabilitiesArr, trackGroupArrayArr, iArr);
        if (this.audioChannelLayout == AudioChannelLayout.TBE_4_4_2) {
            int i5 = 0;
            TrackGroupArray trackGroupArray2 = null;
            for (int i6 = 0; i6 < rendererCapabilitiesArr.length; i6++) {
                if (rendererCapabilitiesArr[i6].getTrackType() == 1) {
                    if (trackGroupArray2 == null) {
                        trackGroupArray2 = trackGroupArrayArr[i6];
                    }
                    if (i5 < trackGroupArray2.length) {
                        i = i5 + 1;
                        trackGroup = trackGroupArray2.get(i5);
                    } else {
                        i = i5;
                        trackGroup = null;
                    }
                    if (trackGroup != null) {
                        selectTracks[i6] = new FixedTrackSelection(trackGroup, 0);
                    } else {
                        selectTracks[i6] = null;
                    }
                    i5 = i;
                }
            }
        }
        return selectTracks;
    }
}
