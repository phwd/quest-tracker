package com.oculus.android.exoplayer2.trackselection;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.RendererCapabilities;
import com.oculus.android.exoplayer2.RendererConfiguration;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;
    private final SparseBooleanArray rendererDisabledFlags = new SparseBooleanArray();
    private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides = new SparseArray<>();
    private int tunnelingAudioSessionId = 0;

    /* access modifiers changed from: protected */
    public abstract TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException;

    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int[][][] formatSupport;
        public final int length;
        private final int[] mixedMimeTypeAdaptiveSupport;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray[] trackGroups;
        private final TrackGroupArray unassociatedTrackGroups;

        MappedTrackInfo(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererTrackTypes = iArr;
            this.trackGroups = trackGroupArrayArr;
            this.formatSupport = iArr3;
            this.mixedMimeTypeAdaptiveSupport = iArr2;
            this.unassociatedTrackGroups = trackGroupArray;
            this.length = trackGroupArrayArr.length;
        }

        public TrackGroupArray getTrackGroups(int i) {
            return this.trackGroups[i];
        }

        public int getRendererSupport(int i) {
            int i2;
            int[][] iArr = this.formatSupport[i];
            int i3 = 0;
            int i4 = 0;
            while (i3 < iArr.length) {
                int i5 = i4;
                for (int i6 = 0; i6 < iArr[i3].length; i6++) {
                    int i7 = iArr[i3][i6] & 7;
                    if (i7 == 3) {
                        i2 = 2;
                    } else if (i7 == 4) {
                        return 3;
                    } else {
                        i2 = 1;
                    }
                    i5 = Math.max(i5, i2);
                }
                i3++;
                i4 = i5;
            }
            return i4;
        }

        public int getTrackTypeRendererSupport(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.length; i3++) {
                if (this.rendererTrackTypes[i3] == i) {
                    i2 = Math.max(i2, getRendererSupport(i3));
                }
            }
            return i2;
        }

        public int getTrackFormatSupport(int i, int i2, int i3) {
            return this.formatSupport[i][i2][i3] & 7;
        }

        public int getAdaptiveSupport(int i, int i2, boolean z) {
            int i3 = this.trackGroups[i].get(i2).length;
            int[] iArr = new int[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int trackFormatSupport = getTrackFormatSupport(i, i2, i5);
                if (trackFormatSupport == 4 || (z && trackFormatSupport == 3)) {
                    iArr[i4] = i5;
                    i4++;
                }
            }
            return getAdaptiveSupport(i, i2, Arrays.copyOf(iArr, i4));
        }

        public int getAdaptiveSupport(int i, int i2, int[] iArr) {
            int i3 = 0;
            int i4 = 16;
            String str = null;
            boolean z = false;
            int i5 = 0;
            while (i3 < iArr.length) {
                String str2 = this.trackGroups[i].get(i2).getFormat(iArr[i3]).sampleMimeType;
                int i6 = i5 + 1;
                if (i5 == 0) {
                    str = str2;
                } else {
                    z |= !Util.areEqual(str, str2);
                }
                i4 = Math.min(i4, this.formatSupport[i][i2][i3] & 24);
                i3++;
                i5 = i6;
            }
            return z ? Math.min(i4, this.mixedMimeTypeAdaptiveSupport[i]) : i4;
        }

        public TrackGroupArray getUnassociatedTrackGroups() {
            return this.unassociatedTrackGroups;
        }
    }

    public static final class SelectionOverride {
        public final TrackSelection.Factory factory;
        public final int groupIndex;
        public final int length;
        public final int[] tracks;

        public SelectionOverride(TrackSelection.Factory factory2, int i, int... iArr) {
            this.factory = factory2;
            this.groupIndex = i;
            this.tracks = iArr;
            this.length = iArr.length;
        }

        public TrackSelection createTrackSelection(TrackGroupArray trackGroupArray) {
            return this.factory.createTrackSelection(trackGroupArray.get(this.groupIndex), this.tracks);
        }

        public boolean containsTrack(int i) {
            for (int i2 : this.tracks) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    public final void setRendererDisabled(int i, boolean z) {
        if (this.rendererDisabledFlags.get(i) != z) {
            this.rendererDisabledFlags.put(i, z);
            invalidate();
        }
    }

    public final boolean getRendererDisabled(int i) {
        return this.rendererDisabledFlags.get(i);
    }

    public final void setSelectionOverride(int i, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
        if (map == null) {
            map = new HashMap<>();
            this.selectionOverrides.put(i, map);
        }
        if (!map.containsKey(trackGroupArray) || !Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
            map.put(trackGroupArray, selectionOverride);
            invalidate();
        }
    }

    public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
        return map != null && map.containsKey(trackGroupArray);
    }

    public final SelectionOverride getSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
        if (map != null) {
            return map.get(trackGroupArray);
        }
        return null;
    }

    public final void clearSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
        if (map != null && map.containsKey(trackGroupArray)) {
            map.remove(trackGroupArray);
            if (map.isEmpty()) {
                this.selectionOverrides.remove(i);
            }
            invalidate();
        }
    }

    public final void clearSelectionOverrides(int i) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
        if (map != null && !map.isEmpty()) {
            this.selectionOverrides.remove(i);
            invalidate();
        }
    }

    public final void clearSelectionOverrides() {
        if (this.selectionOverrides.size() != 0) {
            this.selectionOverrides.clear();
            invalidate();
        }
    }

    public void setTunnelingAudioSessionId(int i) {
        if (this.tunnelingAudioSessionId != i) {
            this.tunnelingAudioSessionId = i;
            invalidate();
        }
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelector
    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray) throws ExoPlaybackException {
        int[] iArr;
        int[] iArr2 = new int[(rendererCapabilitiesArr.length + 1)];
        TrackGroup[][] trackGroupArr = new TrackGroup[(rendererCapabilitiesArr.length + 1)][];
        int[][][] iArr3 = new int[(rendererCapabilitiesArr.length + 1)][][];
        for (int i = 0; i < trackGroupArr.length; i++) {
            trackGroupArr[i] = new TrackGroup[trackGroupArray.length];
            iArr3[i] = new int[trackGroupArray.length][];
        }
        int[] mixedMimeTypeAdaptationSupport = getMixedMimeTypeAdaptationSupport(rendererCapabilitiesArr);
        for (int i2 = 0; i2 < trackGroupArray.length; i2++) {
            TrackGroup trackGroup = trackGroupArray.get(i2);
            int findRenderer = findRenderer(rendererCapabilitiesArr, trackGroup);
            if (findRenderer == rendererCapabilitiesArr.length) {
                iArr = new int[trackGroup.length];
            } else {
                iArr = getFormatSupport(rendererCapabilitiesArr[findRenderer], trackGroup);
            }
            int i3 = iArr2[findRenderer];
            trackGroupArr[findRenderer][i3] = trackGroup;
            iArr3[findRenderer][i3] = iArr;
            iArr2[findRenderer] = iArr2[findRenderer] + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        int[] iArr4 = new int[rendererCapabilitiesArr.length];
        for (int i4 = 0; i4 < rendererCapabilitiesArr.length; i4++) {
            int i5 = iArr2[i4];
            trackGroupArrayArr[i4] = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[i4], i5));
            iArr3[i4] = (int[][]) Arrays.copyOf(iArr3[i4], i5);
            iArr4[i4] = rendererCapabilitiesArr[i4].getTrackType();
        }
        TrackGroupArray trackGroupArray2 = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[rendererCapabilitiesArr.length], iArr2[rendererCapabilitiesArr.length]));
        TrackSelection[] selectTracks = selectTracks(rendererCapabilitiesArr, trackGroupArrayArr, iArr3);
        int i6 = 0;
        while (true) {
            TrackSelection trackSelection = null;
            if (i6 >= rendererCapabilitiesArr.length) {
                break;
            }
            if (this.rendererDisabledFlags.get(i6)) {
                selectTracks[i6] = null;
            } else {
                TrackGroupArray trackGroupArray3 = trackGroupArrayArr[i6];
                if (hasSelectionOverride(i6, trackGroupArray3)) {
                    SelectionOverride selectionOverride = this.selectionOverrides.get(i6).get(trackGroupArray3);
                    if (selectionOverride != null) {
                        trackSelection = selectionOverride.createTrackSelection(trackGroupArray3);
                    }
                    selectTracks[i6] = trackSelection;
                }
            }
            i6++;
        }
        boolean[] determineEnabledRenderers = determineEnabledRenderers(rendererCapabilitiesArr, selectTracks);
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(iArr4, trackGroupArrayArr, mixedMimeTypeAdaptationSupport, iArr3, trackGroupArray2);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCapabilitiesArr.length];
        for (int i7 = 0; i7 < rendererCapabilitiesArr.length; i7++) {
            rendererConfigurationArr[i7] = determineEnabledRenderers[i7] ? RendererConfiguration.DEFAULT : null;
        }
        maybeConfigureRenderersForTunneling(rendererCapabilitiesArr, trackGroupArrayArr, iArr3, rendererConfigurationArr, selectTracks, this.tunnelingAudioSessionId);
        return new TrackSelectorResult(trackGroupArray, determineEnabledRenderers, new TrackSelectionArray(selectTracks), mappedTrackInfo, rendererConfigurationArr);
    }

    private boolean[] determineEnabledRenderers(RendererCapabilities[] rendererCapabilitiesArr, TrackSelection[] trackSelectionArr) {
        boolean[] zArr = new boolean[trackSelectionArr.length];
        for (int i = 0; i < zArr.length; i++) {
            zArr[i] = !this.rendererDisabledFlags.get(i) && (rendererCapabilitiesArr[i].getTrackType() == 5 || trackSelectionArr[i] != null);
        }
        return zArr;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelector
    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int i = 0;
        int i2 = 0;
        while (i < rendererCapabilitiesArr.length) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i];
            int i3 = i2;
            int i4 = length;
            for (int i5 = 0; i5 < trackGroup.length; i5++) {
                int supportsFormat = rendererCapabilities.supportsFormat(trackGroup.getFormat(i5)) & 7;
                if (supportsFormat > i3) {
                    if (supportsFormat == 4) {
                        return i;
                    }
                    i4 = i;
                    i3 = supportsFormat;
                }
            }
            i++;
            length = i4;
            i2 = i3;
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i = 0; i < trackGroup.length; i++) {
            iArr[i] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupport(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int[] iArr = new int[rendererCapabilitiesArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = rendererCapabilitiesArr[i].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    private static void maybeConfigureRenderersForTunneling(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i) {
        boolean z;
        if (i != 0) {
            boolean z2 = false;
            int i2 = 0;
            int i3 = -1;
            int i4 = -1;
            while (true) {
                if (i2 >= rendererCapabilitiesArr.length) {
                    z = true;
                    break;
                }
                int trackType = rendererCapabilitiesArr[i2].getTrackType();
                TrackSelection trackSelection = trackSelectionArr[i2];
                if ((trackType == 1 || trackType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i2], trackGroupArrayArr[i2], trackSelection)) {
                    if (trackType == 1) {
                        if (i4 != -1) {
                            break;
                        }
                        i4 = i2;
                    } else if (i3 != -1) {
                        break;
                    } else {
                        i3 = i2;
                    }
                }
                i2++;
            }
            z = false;
            if (!(i4 == -1 || i3 == -1)) {
                z2 = true;
            }
            if (z && z2) {
                RendererConfiguration rendererConfiguration = new RendererConfiguration(i);
                rendererConfigurationArr[i4] = rendererConfiguration;
                rendererConfigurationArr[i3] = rendererConfiguration;
            }
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i = 0; i < trackSelection.length(); i++) {
            if ((iArr[indexOf][trackSelection.getIndexInTrackGroup(i)] & 32) != 32) {
                return false;
            }
        }
        return true;
    }
}
