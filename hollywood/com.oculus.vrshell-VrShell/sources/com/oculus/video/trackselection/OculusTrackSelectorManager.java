package com.oculus.video.trackselection;

import android.text.TextUtils;
import android.util.Pair;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.android.exoplayer2.trackselection.FixedTrackSelection;
import com.oculus.android.exoplayer2.trackselection.MappingTrackSelector;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.oculus.android.exoplayer2.util.Clock;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.video.Settings;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.trackselection.OculusAdaptiveTrackSelection;
import java.util.HashMap;
import java.util.Map;

public class OculusTrackSelectorManager implements OculusAdaptiveTrackSelection.Callback {
    private static final int UNDEFINED_IDX = -1;
    private int audioRendererIdx;
    private Map<String, Pair<Integer, Integer>> audioTrackInfo;
    private final TrackSelection.Factory fixedSelectionFactory = new FixedTrackSelection.Factory();
    private volatile boolean isSelectionOverrideActive;
    private String selectedVideoFormatId = "";
    private int subtitleRendererIdx;
    private Map<String, Pair<Integer, Integer>> subtitleTrackInfo;
    private OculusTrackSelector trackSelector;
    private boolean useSmoothTrackSelectionOverride;
    private int videoRendererIdx;
    private Map<String, Integer> videoTrackBitrateInfo;
    private Map<String, Pair<Integer, Integer>> videoTrackInfo;

    public boolean selectAudioTrack(String str) {
        return false;
    }

    public OculusTrackSelectorManager(Settings settings, DefaultBandwidthMeter defaultBandwidthMeter, AudioChannelLayout audioChannelLayout, long j) {
        this.trackSelector = new OculusTrackSelector(new OculusAdaptiveTrackSelection.Factory(defaultBandwidthMeter, settings.getMaxInitialBitrate(j), settings.getMinDurationForQualityIncreaseMs(), settings.getMaxDurationForQualityDecreaseMs(), 25000, settings.getTrackSelectionBandwidthFraction(), 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, settings.getMinTimeBetweenQualityReevaluationMs(), settings.getMinTimeBetweenQualitySwitchMs(), Clock.DEFAULT, this), audioChannelLayout, settings);
        this.subtitleRendererIdx = -1;
        this.audioRendererIdx = -1;
        this.videoRendererIdx = -1;
        this.subtitleTrackInfo = new HashMap();
        this.audioTrackInfo = new HashMap();
        this.videoTrackInfo = new HashMap();
        this.videoTrackBitrateInfo = new HashMap();
        this.isSelectionOverrideActive = false;
        this.useSmoothTrackSelectionOverride = settings.useSmoothTrackSelectionOverride();
    }

    public OculusTrackSelector getTrackSelector() {
        return this.trackSelector;
    }

    public void reset() {
        this.trackSelector = null;
        resetInfo();
    }

    public void resetInfo() {
        this.subtitleRendererIdx = -1;
        this.audioRendererIdx = -1;
        this.videoRendererIdx = -1;
        this.subtitleTrackInfo.clear();
        this.audioTrackInfo.clear();
        this.videoTrackInfo.clear();
        this.videoTrackBitrateInfo.clear();
        this.isSelectionOverrideActive = false;
    }

    public void createTrackInfo() {
        resetInfo();
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        if (currentMappedTrackInfo != null) {
            for (int i = 0; i < currentMappedTrackInfo.length; i++) {
                TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i);
                int i2 = 0;
                while (i2 < trackGroups.length) {
                    TrackGroup trackGroup = trackGroups.get(i2);
                    int i3 = 0;
                    while (true) {
                        if (i3 >= trackGroup.length) {
                            break;
                        }
                        Format format = trackGroup.getFormat(i3);
                        if (MimeTypes.getTrackType(format.sampleMimeType) == 3) {
                            this.subtitleRendererIdx = i;
                            if (format.id != null) {
                                this.subtitleTrackInfo.put(format.id, new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3)));
                            }
                        } else if (MimeTypes.getTrackType(format.sampleMimeType) != 1) {
                            if (MimeTypes.getTrackType(format.sampleMimeType) != 2) {
                                i2 = trackGroups.length;
                                break;
                            }
                            this.videoRendererIdx = i;
                            if (format.id != null) {
                                this.videoTrackInfo.put(format.id, new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3)));
                                this.videoTrackBitrateInfo.put(format.id, Integer.valueOf(format.bitrate));
                            }
                        } else {
                            this.audioRendererIdx = i;
                            if (format.id != null) {
                                this.audioTrackInfo.put(format.id, new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3)));
                            }
                        }
                        i3++;
                    }
                    i2++;
                }
            }
        }
    }

    public boolean selectSubtitleTrack(String str) {
        OculusTrackSelector oculusTrackSelector = this.trackSelector;
        if (oculusTrackSelector == null || oculusTrackSelector.getCurrentMappedTrackInfo() == null || this.subtitleRendererIdx == -1) {
            return false;
        }
        boolean z = str == null;
        if (z || !this.subtitleTrackInfo.containsKey(str)) {
            this.trackSelector.setRendererDisabled(this.subtitleRendererIdx, true);
            return z;
        }
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        Pair<Integer, Integer> pair = this.subtitleTrackInfo.get(str);
        this.trackSelector.setSelectionOverride(this.subtitleRendererIdx, currentMappedTrackInfo.getTrackGroups(this.subtitleRendererIdx), new MappingTrackSelector.SelectionOverride(this.fixedSelectionFactory, ((Integer) pair.first).intValue(), ((Integer) pair.second).intValue()));
        this.trackSelector.setRendererDisabled(this.subtitleRendererIdx, false);
        return true;
    }

    public boolean selectVideoTrack(String str) {
        OculusTrackSelector oculusTrackSelector = this.trackSelector;
        if (oculusTrackSelector == null || oculusTrackSelector.getCurrentMappedTrackInfo() == null || this.videoRendererIdx == -1) {
            return false;
        }
        if (!this.useSmoothTrackSelectionOverride) {
            if (TextUtils.isEmpty(str)) {
                this.trackSelector.clearSelectionOverrides(this.videoRendererIdx);
                return true;
            } else if (!this.videoTrackInfo.containsKey(str)) {
                return false;
            } else {
                MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
                Pair<Integer, Integer> pair = this.videoTrackInfo.get(str);
                this.trackSelector.setSelectionOverride(this.videoRendererIdx, currentMappedTrackInfo.getTrackGroups(this.videoRendererIdx), new MappingTrackSelector.SelectionOverride(this.fixedSelectionFactory, ((Integer) pair.first).intValue(), ((Integer) pair.second).intValue()));
                return true;
            }
        } else if (TextUtils.isEmpty(str)) {
            this.isSelectionOverrideActive = false;
            return true;
        } else if (!this.videoTrackInfo.containsKey(str)) {
            return false;
        } else {
            this.isSelectionOverrideActive = true;
            this.selectedVideoFormatId = str;
            return true;
        }
    }

    @Override // com.oculus.video.trackselection.OculusAdaptiveTrackSelection.Callback
    public boolean isTrackSelectionOverrideActive() {
        return this.isSelectionOverrideActive;
    }

    @Override // com.oculus.video.trackselection.OculusAdaptiveTrackSelection.Callback
    public long getTrackSelectionOverrideBitrate() {
        OculusTrackSelector oculusTrackSelector = this.trackSelector;
        if (oculusTrackSelector == null || oculusTrackSelector.getCurrentMappedTrackInfo() == null || this.videoRendererIdx == -1) {
            return 0;
        }
        return (long) this.videoTrackBitrateInfo.get(this.selectedVideoFormatId).intValue();
    }
}
