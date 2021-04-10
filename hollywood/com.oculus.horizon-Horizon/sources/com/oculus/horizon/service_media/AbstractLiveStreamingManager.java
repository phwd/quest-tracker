package com.oculus.horizon.service_media;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.http.core.endpoint.EndpointModule;
import javax.annotation.Nullable;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

public abstract class AbstractLiveStreamingManager {
    public boolean mCommentsVisible;
    public boolean mIsInitialized;
    public boolean mIsLivestreaming;
    public boolean mIsPaused;
    public final PlatformPluginManager mPlatformPluginManager;
    public final StreamingType mStreamingType;

    public enum StreamingType {
        FACEBOOK(1),
        CAST(2);
        
        public final int mVal;

        /* access modifiers changed from: public */
        StreamingType(int i) {
            this.mVal = i;
        }
    }

    public static Bundle A02(LivestreamStartStatus livestreamStartStatus) {
        Bundle bundle = new Bundle();
        bundle.putInt("streaming_result", livestreamStartStatus.getValue());
        bundle.putString("streaming_result_string", livestreamStartStatus.toString());
        return bundle;
    }

    @Nullable
    public final Bundle A03(Context context) {
        if (!(this instanceof FacebookLiveStreamingManager)) {
            if (!(this instanceof CastNativeReceiverManager)) {
                PlatformPluginManager.nativeStopLiveStreaming();
                this.mIsLivestreaming = false;
            } else {
                CastNativeReceiverManager castNativeReceiverManager = (CastNativeReceiverManager) this;
                castNativeReceiverManager.mVrCastManager.stopCast(null);
                PlatformPluginManager.nativeStopLiveStreaming();
                castNativeReceiverManager.mIsLivestreaming = false;
            }
            return new Bundle();
        }
        FacebookLiveStreamingManager facebookLiveStreamingManager = (FacebookLiveStreamingManager) this;
        Bundle bundle = new Bundle();
        if (facebookLiveStreamingManager.mIsLivestreaming) {
            bundle = new Bundle();
            try {
                JSONObject A00 = FacebookLiveStreamingManager.A00(new HttpGet(AnonymousClass006.A05(AnonymousClass006.A05(EndpointModule.GRAPH_ENDPOINT_FACEBOOK, facebookLiveStreamingManager.mStreamID), "?fields=total_views,comments.limit(0).summary(total_count),reactions.limit(0).summary(total_count)")), facebookLiveStreamingManager.mAccessToken);
                bundle.putString("total_views", A00.getString("total_views"));
                bundle.putInt("comment_count", A00.getJSONObject("comments").getJSONObject("summary").getInt("total_count"));
                bundle.putInt("reaction_count", A00.getJSONObject("reactions").getJSONObject("summary").getInt("total_count"));
            } catch (Exception e) {
                AnonymousClass0NO.A0B(FacebookLiveStreamingManager.TAG, "", e);
                bundle = new Bundle();
            }
        }
        PlatformPluginManager.nativeStopLiveStreaming();
        FacebookLiveStreamingManager.A01(facebookLiveStreamingManager);
        facebookLiveStreamingManager.mExecutorService.shutdownNow();
        context.sendBroadcast(new Intent(OverlayUtils.STOP_RENDERING_OVERLAY));
        return bundle;
    }

    public AbstractLiveStreamingManager(PlatformPluginManager platformPluginManager, StreamingType streamingType) {
        this.mStreamingType = streamingType;
        this.mPlatformPluginManager = platformPluginManager;
    }
}
