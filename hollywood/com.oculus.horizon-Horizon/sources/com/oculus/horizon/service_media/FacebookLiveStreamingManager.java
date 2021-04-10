package com.oculus.horizon.service_media;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import android.text.TextUtils;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service_media.AbstractLiveStreamingManager;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.mediaupload.api.MediaUploaderMetadataHelper;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookLiveStreamingManager extends AbstractLiveStreamingManager {
    public static final String KEY_LIVESTREAM_GROUP = "group_selector";
    public static final String KEY_LIVESTREAM_PAGE = "page_selector";
    public static final String KEY_LIVESTREAM_SHOULD_TAG_GAME = "should_tag_game";
    public static final String TAG = "FacebookLiveStreamingManager";
    public static final String url_authority = "graph.facebook.com";
    public static final String url_path = "live_videos";
    public static final String url_scheme = "https";
    public String mAccessToken;
    public final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    public final FBConnectHelper mFBConnectHelper;
    public final MediaUploaderMetadataHelper mMediaUploaderMetadataHelper;
    public boolean mShouldTagGame;
    public String mStreamID;

    public FacebookLiveStreamingManager(MediaUploaderMetadataHelper mediaUploaderMetadataHelper, FBConnectHelper fBConnectHelper, PlatformPluginManager platformPluginManager) {
        super(platformPluginManager, AbstractLiveStreamingManager.StreamingType.FACEBOOK);
        this.mFBConnectHelper = fBConnectHelper;
        this.mMediaUploaderMetadataHelper = mediaUploaderMetadataHelper;
    }

    public static JSONObject A00(HttpUriRequest httpUriRequest, String str) throws IOException, JSONException {
        httpUriRequest.addHeader("Authorization", AnonymousClass006.A05("OAuth ", str));
        return new JSONObject(EntityUtils.toString(new DefaultHttpClient().execute(httpUriRequest).getEntity()));
    }

    public static void A01(FacebookLiveStreamingManager facebookLiveStreamingManager) {
        if (!TextUtils.isEmpty(facebookLiveStreamingManager.mStreamID) && !TextUtils.isEmpty(facebookLiveStreamingManager.mAccessToken)) {
            final String A05 = AnonymousClass006.A05(AnonymousClass006.A05(EndpointModule.GRAPH_ENDPOINT_FACEBOOK, facebookLiveStreamingManager.mStreamID), "?og_action_type_id=0");
            try {
                facebookLiveStreamingManager.mExecutorService.execute(new Runnable() {
                    /* class com.oculus.horizon.service_media.FacebookLiveStreamingManager.AnonymousClass2 */

                    public final void run() {
                        try {
                            FacebookLiveStreamingManager.A00(new HttpPost(A05), FacebookLiveStreamingManager.this.mAccessToken);
                        } catch (IOException | JSONException e) {
                            AnonymousClass0NO.A0B(FacebookLiveStreamingManager.TAG, "", e);
                        }
                    }
                });
            } catch (RejectedExecutionException e) {
                AnonymousClass0NO.A0B(TAG, "failed to clear live video minutiae, error:", e);
            }
        }
    }
}
