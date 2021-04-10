package com.oculus.android.exoplayer2.trackselection;

import com.oculus.android.exoplayer2.RendererConfiguration;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.util.Util;

public final class TrackSelectorResult {
    public final TrackGroupArray groups;
    public final Object info;
    public final RendererConfiguration[] rendererConfigurations;
    public final boolean[] renderersEnabled;
    public final TrackSelectionArray selections;

    public TrackSelectorResult(TrackGroupArray trackGroupArray, boolean[] zArr, TrackSelectionArray trackSelectionArray, Object obj, RendererConfiguration[] rendererConfigurationArr) {
        this.groups = trackGroupArray;
        this.renderersEnabled = zArr;
        this.selections = trackSelectionArray;
        this.info = obj;
        this.rendererConfigurations = rendererConfigurationArr;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult) {
        if (trackSelectorResult == null || trackSelectorResult.selections.length != this.selections.length) {
            return false;
        }
        for (int i = 0; i < this.selections.length; i++) {
            if (!isEquivalent(trackSelectorResult, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult, int i) {
        if (trackSelectorResult != null && this.renderersEnabled[i] == trackSelectorResult.renderersEnabled[i] && Util.areEqual(this.selections.get(i), trackSelectorResult.selections.get(i)) && Util.areEqual(this.rendererConfigurations[i], trackSelectorResult.rendererConfigurations[i])) {
            return true;
        }
        return false;
    }
}
