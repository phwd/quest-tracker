package com.oculus.horizon.remotewipe;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class DeviceManifestWipeStatusResponse {
    @SerializedName("all_device_manifests")
    public final AllDeviceManifests deviceManifests;

    public static class AllDeviceManifests {
        @SerializedName("nodes")
        public final List<Node> nodes;
    }

    public static class Node {
        @SerializedName("device_wipe_status")
        public final String deviceWipeStatus;
    }
}
