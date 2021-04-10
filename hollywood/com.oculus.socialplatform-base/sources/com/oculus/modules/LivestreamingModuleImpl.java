package com.oculus.modules;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.oculus.modules.codegen.LivestreamingModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.secure.trustedapp.CallerInfoHelper;

public class LivestreamingModuleImpl extends LivestreamingModule {
    public static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    public static final String KEY_LIVESTREAM_AUDIENCE = "audience_selector";
    public static final String KEY_LIVESTREAM_GROUP = "group_selector";
    public static final String KEY_LIVESTREAM_LOCATION = "location_selector";
    public static final String KEY_LIVESTREAM_PAGE = "page_selector";
    public static final String KEY_LIVESTREAM_SHOULD_TAG_GAME = "should_tag_game";
    public static final String KEY_MESSAGE_TYPE = "message_type";
    public static final String KEY_MICROPHONE_STATUS = "microphone_status";
    public static final String KEY_RESULT_RECEIVER = "result_receiver";
    public static final String KEY_STREAMING_RESULT = "streaming_result";
    public static final String KEY_STREAMING_RESULT_STRING = "streaming_result_string";
    public static final String OVR_MEDIA_SERVICE_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    public static final String START_FACEBOOK_STREAMING = "start_streaming";
    public Context mContext = null;

    public enum LiveStreamingAudience {
        PUBLIC(1),
        FRIENDS(2),
        ONLY_ME(3);
        
        public final int mVal;

        /* access modifiers changed from: public */
        LiveStreamingAudience(int i) {
            this.mVal = i;
        }
    }

    public enum LiveStreamingLocation {
        TIMELINE(1),
        GROUP(2),
        PAGE(3);
        
        public final int mVal;

        /* access modifiers changed from: public */
        LiveStreamingLocation(int i) {
            this.mVal = i;
        }
    }

    public enum LiveStreamingMicrophoneStatus {
        MICROPHONE_ON(1),
        MICROPHONE_OFF(2);
        
        public final int mVal;

        /* access modifiers changed from: public */
        LiveStreamingMicrophoneStatus(int i) {
            this.mVal = i;
        }
    }

    public LivestreamingModuleImpl(Context context) {
        this.mContext = context;
    }

    public static ResultReceiver getCrossPackageResultReceiver(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    @Override // com.oculus.modules.codegen.LivestreamingModule
    public void startLivestreamingImpl(String str, String str2, String str3, String str4, boolean z, boolean z2, final Resolver<Void> resolver) {
        int i;
        int i2;
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
            intent.putExtra("message_type", START_FACEBOOK_STREAMING);
            if (str.equals("GROUP")) {
                i = LiveStreamingLocation.GROUP.mVal;
            } else if (str.equals("PAGE")) {
                i = LiveStreamingLocation.PAGE.mVal;
            } else {
                i = LiveStreamingLocation.TIMELINE.mVal;
            }
            intent.putExtra(KEY_LIVESTREAM_LOCATION, i);
            if (str2.equals("PUBLIC")) {
                i2 = LiveStreamingAudience.PUBLIC.mVal;
            } else if (str2.equals("FRIENDS")) {
                i2 = LiveStreamingAudience.FRIENDS.mVal;
            } else {
                i2 = LiveStreamingAudience.ONLY_ME.mVal;
            }
            intent.putExtra(KEY_LIVESTREAM_AUDIENCE, i2);
            intent.putExtra(KEY_LIVESTREAM_GROUP, str3);
            intent.putExtra(KEY_LIVESTREAM_PAGE, str4);
            intent.putExtra(KEY_LIVESTREAM_SHOULD_TAG_GAME, z);
            intent.putExtra("microphone_status", (z2 ? LiveStreamingMicrophoneStatus.MICROPHONE_ON : LiveStreamingMicrophoneStatus.MICROPHONE_OFF).mVal);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.LivestreamingModuleImpl.AnonymousClass1 */

                public void onReceiveResult(int i, Bundle bundle) {
                    if (i != -1 || bundle == null) {
                        resolver.reject(new Error("Unable to receive results from Facebook livestream attempt."));
                        return;
                    }
                    int i2 = bundle.getInt(LivestreamingModuleImpl.KEY_STREAMING_RESULT);
                    String string = bundle.getString(LivestreamingModuleImpl.KEY_STREAMING_RESULT_STRING);
                    if (i2 < 0) {
                        resolver.reject(new Error(string));
                    } else {
                        resolver.resolve(null);
                    }
                }
            }));
            CallerInfoHelper.attachCallerInfo(intent, this.mContext, AnonymousClass006.A07(LivestreamingModule.MODULE_NAME, ":startLivestreaming()"));
            this.mContext.startService(intent);
        } catch (Exception e) {
            AnonymousClass0MD.A0C(LivestreamingModule.MODULE_NAME, e, "Sending intent to OVRMediaService to start Facebook livestream failed.");
            resolver.reject(e);
        }
    }
}
