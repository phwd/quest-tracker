package com.oculus.modules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.facebook.debug.log.BLog;
import com.oculus.modules.codegen.LivestreamingModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.secure.trustedapp.CallerInfoHelper;

public class LivestreamingModuleImpl extends LivestreamingModule {
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String KEY_LIVESTREAM_AUDIENCE = "audience_selector";
    private static final String KEY_LIVESTREAM_GROUP = "group_selector";
    private static final String KEY_LIVESTREAM_LOCATION = "location_selector";
    private static final String KEY_LIVESTREAM_PAGE = "page_selector";
    private static final String KEY_LIVESTREAM_SHOULD_TAG_GAME = "should_tag_game";
    private static final String KEY_MESSAGE_TYPE = "message_type";
    private static final String KEY_MICROPHONE_STATUS = "microphone_status";
    private static final String KEY_RESULT_RECEIVER = "result_receiver";
    private static final String KEY_STREAMING_RESULT = "streaming_result";
    private static final String KEY_STREAMING_RESULT_STRING = "streaming_result_string";
    private static final String OVR_MEDIA_SERVICE_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String START_FACEBOOK_STREAMING = "start_streaming";
    private Context mContext = null;

    public enum LiveStreamingLocation {
        TIMELINE(1),
        GROUP(2),
        PAGE(3);
        
        public final int mVal;

        private LiveStreamingLocation(int val) {
            this.mVal = val;
        }
    }

    public enum LiveStreamingAudience {
        PUBLIC(1),
        FRIENDS(2),
        ONLY_ME(3);
        
        public final int mVal;

        private LiveStreamingAudience(int val) {
            this.mVal = val;
        }
    }

    public enum LiveStreamingMicrophoneStatus {
        MICROPHONE_ON(1),
        MICROPHONE_OFF(2);
        
        public final int mVal;

        private LiveStreamingMicrophoneStatus(int val) {
            this.mVal = val;
        }
    }

    public LivestreamingModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LivestreamingModule
    public void startLivestreamingImpl(String location, String audience, String group, String page, boolean shouldTagGame, boolean enableMic, final Resolver<Void> resolver) {
        int locationValue;
        int audienceValue;
        int micStatus;
        try {
            BLog.i(MODULE_NAME, "Sending intent to OVRMediaService to start Facebook livestream to [%s]", location);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", OVR_MEDIA_SERVICE_NAME));
            intent.putExtra(KEY_MESSAGE_TYPE, START_FACEBOOK_STREAMING);
            if (location.equals("GROUP")) {
                locationValue = LiveStreamingLocation.GROUP.mVal;
            } else if (location.equals("PAGE")) {
                locationValue = LiveStreamingLocation.PAGE.mVal;
            } else {
                locationValue = LiveStreamingLocation.TIMELINE.mVal;
            }
            intent.putExtra(KEY_LIVESTREAM_LOCATION, locationValue);
            if (audience.equals("PUBLIC")) {
                audienceValue = LiveStreamingAudience.PUBLIC.mVal;
            } else if (audience.equals("FRIENDS")) {
                audienceValue = LiveStreamingAudience.FRIENDS.mVal;
            } else {
                audienceValue = LiveStreamingAudience.ONLY_ME.mVal;
            }
            intent.putExtra(KEY_LIVESTREAM_AUDIENCE, audienceValue);
            intent.putExtra(KEY_LIVESTREAM_GROUP, group);
            intent.putExtra(KEY_LIVESTREAM_PAGE, page);
            intent.putExtra(KEY_LIVESTREAM_SHOULD_TAG_GAME, shouldTagGame);
            if (enableMic) {
                micStatus = LiveStreamingMicrophoneStatus.MICROPHONE_ON.mVal;
            } else {
                micStatus = LiveStreamingMicrophoneStatus.MICROPHONE_OFF.mVal;
            }
            intent.putExtra(KEY_MICROPHONE_STATUS, micStatus);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.LivestreamingModuleImpl.AnonymousClass1 */

                /* access modifiers changed from: protected */
                public void onReceiveResult(int resultCode, Bundle resultData) {
                    if (resultCode != -1 || resultData == null) {
                        resolver.reject(new Error("Unable to receive results from Facebook livestream attempt."));
                        return;
                    }
                    int streamingResult = resultData.getInt(LivestreamingModuleImpl.KEY_STREAMING_RESULT);
                    String streamingResultString = resultData.getString(LivestreamingModuleImpl.KEY_STREAMING_RESULT_STRING);
                    if (streamingResult < 0) {
                        resolver.reject(new Error(streamingResultString));
                    } else {
                        resolver.resolve(null);
                    }
                }
            }));
            CallerInfoHelper.attachCallerInfo(intent, this.mContext, MODULE_NAME + ":startLivestreaming()");
            this.mContext.startService(intent);
        } catch (Exception exception) {
            BLog.e(MODULE_NAME, exception, "Sending intent to OVRMediaService to start Facebook livestream failed.");
            resolver.reject(exception);
        }
    }

    private static ResultReceiver getCrossPackageResultReceiver(ResultReceiver resultReceiver) {
        Parcel parcel = Parcel.obtain();
        resultReceiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver crossPackageResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return crossPackageResultReceiver;
    }
}
