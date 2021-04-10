package com.oculus.updater.net.methods;

public class OSReleasesParams {
    public final String accessToken;
    public final String channelAppId;
    public final String deviceAccessToken;
    public final int deviceManagedMode;
    public final String graphQl;

    public OSReleasesParams(String str, String str2, String str3, String str4, int i) {
        this.graphQl = str;
        this.channelAppId = str2;
        this.accessToken = str3;
        this.deviceAccessToken = str4;
        this.deviceManagedMode = i;
    }
}
