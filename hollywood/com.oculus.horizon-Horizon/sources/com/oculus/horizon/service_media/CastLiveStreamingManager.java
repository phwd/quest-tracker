package com.oculus.horizon.service_media;

import com.oculus.horizon.cast.VideoSpec;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service_media.AbstractLiveStreamingManager;

public class CastLiveStreamingManager extends AbstractLiveStreamingManager {
    public static final String TAG = "CastLiveStreamingManager";
    public final int mBitrate;
    public final boolean mEnableDataChannel;
    public final String mEndpoint;
    public final String mSessionId;
    public final VideoSpec mVideoSpec;

    public CastLiveStreamingManager(PlatformPluginManager platformPluginManager, String str, String str2, VideoSpec videoSpec, int i, boolean z) {
        super(platformPluginManager, AbstractLiveStreamingManager.StreamingType.CAST);
        this.mEndpoint = str;
        this.mSessionId = str2;
        this.mVideoSpec = videoSpec;
        this.mBitrate = i;
        this.mEnableDataChannel = z;
    }
}
