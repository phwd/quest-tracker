package com.oculus.horizon.service_media;

import com.oculus.horizon.cast.VideoSpec;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service_media.AbstractLiveStreamingManager;
import com.oculus.horizon.service_media.vrcast.VrCastManager;

public class CastNativeReceiverManager extends AbstractLiveStreamingManager {
    public final String mEndpoint;
    public final VideoSpec mVideoSpec;
    public final VrCastManager mVrCastManager;

    public CastNativeReceiverManager(PlatformPluginManager platformPluginManager, String str, VideoSpec videoSpec, VrCastManager vrCastManager) {
        super(platformPluginManager, AbstractLiveStreamingManager.StreamingType.CAST);
        this.mEndpoint = str;
        this.mVideoSpec = videoSpec;
        this.mVrCastManager = vrCastManager;
    }
}
