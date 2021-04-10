package com.oculus.modules;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.ipc.common.ParcelableCallbackReceiver;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.mediaupload.model.MediaUploaderCallback;
import com.oculus.mediaupload.model.MediaUploaderRequest;
import com.oculus.mediaupload.model.MediaUploaderResult;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.ShareToFacebookModule;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareToFacebookModuleImpl extends ShareToFacebookModule {
    public static final String HORIZON_MEDIA_UPLOADER_SERVICE_NAME = "com.oculus.horizon.mediaupload.MediaUploaderService";
    public static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    public static final String MEDIA_UPLOAD_REQUEST_KEY = "request";
    public static final String MEDIA_UPLOAD_REQUEST_TYPE_KEY = "request_type";
    public static final String MEDIA_UPLOAD_RESULT_RECEIVER_KEY = "result_receiver";
    public static final String MEDIA_UPLOAD_SYNC_TO_FB_USER_INITIATED = "SYNC_TO_FB_USER_INITIATED";
    public static final String OVR_MEDIA_SERVICE_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    public static final String RESULT_RECEIVER_KEY = "result_receiver";
    public static final String TAG = "ShareToFacebookModuleImpl";
    public Context mContext = null;

    public class MediaUploaderCallbackReceiver implements MediaUploaderCallback {
        public MediaUploaderCallbackReceiver() {
        }

        @Override // com.oculus.mediaupload.model.MediaUploaderCallback
        public void onResult(MediaUploaderResult mediaUploaderResult) {
            mediaUploaderResult.toString();
        }
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void shutdownModule() {
    }

    private Intent getMediaUploaderIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_MEDIA_UPLOADER_SERVICE_NAME));
        intent.putExtra("result_receiver", new ParcelableCallbackReceiver(new MediaUploaderCallbackReceiver()).getReceiverForIPC());
        return intent;
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void getMediaMetadataImpl(String str, final Resolver<ShareToFacebookModule.MediaMetadata> resolver) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
            intent.putExtra("message_type", "com.oculus.horizon.GET_MEDIA_METADATA");
            intent.putExtra(OCMSLibraryContract.ASSETS_PATH_BY_FILENAME, str);
            intent.putExtra("result_receiver", getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.ShareToFacebookModuleImpl.AnonymousClass1 */

                public void onReceiveResult(int i, Bundle bundle) {
                    if (i != -1 || bundle == null) {
                        resolver.reject(new Error("Unable to receive results from OVRMediaService"));
                    }
                    try {
                        resolver.resolve(ShareToFacebookModule.MediaMetadata.makeFromJSONObject(new JSONObject(bundle.getString("media_metadata_json"))));
                    } catch (JSONException e) {
                        AnonymousClass0MD.A0C(ShareToFacebookModule.MODULE_NAME, e, "Exception while parsing media metadata");
                        resolver.reject(e);
                    }
                }
            }));
            CallerInfoHelper.attachCallerInfo(intent, this.mContext, AnonymousClass006.A07(ShareToFacebookModule.MODULE_NAME, ":getMediaMetadata()"));
            this.mContext.startService(intent);
        } catch (Exception e) {
            AnonymousClass0MD.A0C(ShareToFacebookModule.MODULE_NAME, e, "Failed to send intent to get media metadeata from OVRMediaService");
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToFacebookTimelineImpl(String str, String str2, String str3, String str4, String str5, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest forShareToTimeline;
        FacebookShareRequest.FacebookSharePrivacy facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.SELF;
        try {
            facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.valueOf(str4);
        } catch (IllegalArgumentException unused) {
            AnonymousClass0MD.A09(ShareToFacebookModule.MODULE_NAME, "%s is not a valid privacy", str4);
        }
        if (str5.length() == 0 || str5.equals("0")) {
            forShareToTimeline = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy);
        } else {
            forShareToTimeline = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy, str5);
        }
        MediaUploaderRequest forUploadScreenshotToFacebook = MediaUploaderRequest.forUploadScreenshotToFacebook(str3, forShareToTimeline, str, str2, null);
        forUploadScreenshotToFacebook.toString();
        postToMediaUploader(forUploadScreenshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToFacebookTimelineImpl(String str, String str2, String str3, String str4, String str5, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest forShareToTimeline;
        FacebookShareRequest.FacebookSharePrivacy facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.SELF;
        try {
            facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.valueOf(str4);
        } catch (IllegalArgumentException unused) {
            AnonymousClass0MD.A09(ShareToFacebookModule.MODULE_NAME, "%s is not a valid privacy", str4);
        }
        if (str5.length() == 0 || str5.equals("0")) {
            forShareToTimeline = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy);
        } else {
            forShareToTimeline = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy, str5);
        }
        MediaUploaderRequest forUploadVideoshotToFacebook = MediaUploaderRequest.forUploadVideoshotToFacebook(str3, forShareToTimeline, str, str2, null);
        forUploadVideoshotToFacebook.toString();
        postToMediaUploader(forUploadVideoshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void returnShareResultImpl(boolean z, String str, String str2, String str3) {
        try {
            Intent intent = new Intent("com.oculus.share_to_facebook_result");
            intent.setPackage(str3);
            try {
                intent.putExtra(AbuseReportModule.KEY_PLATFORM_REQUEST_ID, Long.parseLong(str2));
            } catch (NumberFormatException e) {
                Log.e(ShareToFacebookModule.MODULE_NAME, AnonymousClass006.A09("Cannot parse platformRequestId ", str2, ", returning Share result without it."), e);
            }
            intent.putExtra("share_result_json", str);
            intent.putExtra("share_was_successful", z);
            this.mContext.sendBroadcast(intent);
        } catch (Exception e2) {
            Log.e(ShareToFacebookModule.MODULE_NAME, "Failed to return Share result to the calling app.", e2);
        }
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void uploadToOculusImpl(boolean z, String str, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        MediaUploaderRequest forUploadScreenshotToOculus;
        if (z) {
            forUploadScreenshotToOculus = MediaUploaderRequest.forUploadVideoshotToOculus(str);
        } else {
            forUploadScreenshotToOculus = MediaUploaderRequest.forUploadScreenshotToOculus(str);
        }
        postToMediaUploader(forUploadScreenshotToOculus, resolver);
    }

    public ShareToFacebookModuleImpl(Context context) {
        this.mContext = context;
    }

    public static /* synthetic */ String access$000() {
        return ShareToFacebookModule.MODULE_NAME;
    }

    public static /* synthetic */ String access$100() {
        return ShareToFacebookModule.MODULE_NAME;
    }

    public static ResultReceiver getCrossPackageResultReceiver(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    private void postToMediaUploader(MediaUploaderRequest mediaUploaderRequest, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        try {
            Intent mediaUploaderIntent = getMediaUploaderIntent();
            mediaUploaderIntent.putExtra("request", mediaUploaderRequest);
            CallerInfoHelper.attachCallerInfo(mediaUploaderIntent, this.mContext, AnonymousClass006.A07(getClass().getSimpleName(), ":startUploadMedia()"));
            this.mContext.startService(mediaUploaderIntent);
        } catch (Exception e) {
            resolver.reject(e);
            AnonymousClass0MD.A0C(ShareToFacebookModule.MODULE_NAME, e, "Sending intent to upload media failed.");
        }
        resolver.resolve(ShareToFacebookModule.MediaUploadResponse.makeFromJSONObject(new JSONObject()));
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToFacebookGroupImpl(String str, String str2, String str3, String str4, String str5, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest forShareToGroup;
        if (str5.length() == 0 || str5.equals("0")) {
            forShareToGroup = FacebookShareRequest.forShareToGroup(str4);
        } else {
            forShareToGroup = FacebookShareRequest.forShareToGroup(str4, str5);
        }
        MediaUploaderRequest forUploadScreenshotToFacebook = MediaUploaderRequest.forUploadScreenshotToFacebook(str3, forShareToGroup, str, str2, null);
        forUploadScreenshotToFacebook.toString();
        postToMediaUploader(forUploadScreenshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToFacebookStoriesImpl(String str, String str2, String str3, String str4, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest forShareToStories;
        if (str4.length() == 0 || str4.equals("0")) {
            forShareToStories = FacebookShareRequest.forShareToStories();
        } else {
            forShareToStories = FacebookShareRequest.forShareToStories(str4);
        }
        MediaUploaderRequest forUploadScreenshotToFacebook = MediaUploaderRequest.forUploadScreenshotToFacebook(str3, forShareToStories, str, str2, null);
        forUploadScreenshotToFacebook.toString();
        postToMediaUploader(forUploadScreenshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToMessengerThreadImpl(String str, String str2, String str3, String str4, boolean z, String str5, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        MediaUploaderRequest forUploadScreenshotToFacebook = MediaUploaderRequest.forUploadScreenshotToFacebook(str3, FacebookShareRequest.forShareToMessengerThread(str4, z), str, str2, str5);
        forUploadScreenshotToFacebook.toString();
        postToMediaUploader(forUploadScreenshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToFacebookGroupImpl(String str, String str2, String str3, String str4, String str5, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest forShareToGroup;
        if (str5.length() == 0 || str5.equals("0")) {
            forShareToGroup = FacebookShareRequest.forShareToGroup(str4);
        } else {
            forShareToGroup = FacebookShareRequest.forShareToGroup(str4, str5);
        }
        MediaUploaderRequest forUploadVideoshotToFacebook = MediaUploaderRequest.forUploadVideoshotToFacebook(str3, forShareToGroup, str, str2, null);
        forUploadVideoshotToFacebook.toString();
        postToMediaUploader(forUploadVideoshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToFacebookStoriesImpl(String str, String str2, String str3, String str4, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest forShareToStories;
        if (str4.length() == 0 || str4.equals("0")) {
            forShareToStories = FacebookShareRequest.forShareToStories();
        } else {
            forShareToStories = FacebookShareRequest.forShareToStories(str4);
        }
        MediaUploaderRequest forUploadVideoshotToFacebook = MediaUploaderRequest.forUploadVideoshotToFacebook(str3, forShareToStories, str, str2, null);
        forUploadVideoshotToFacebook.toString();
        postToMediaUploader(forUploadVideoshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToMessengerThreadImpl(String str, String str2, String str3, String str4, boolean z, String str5, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        MediaUploaderRequest forUploadVideoshotToFacebook = MediaUploaderRequest.forUploadVideoshotToFacebook(str3, FacebookShareRequest.forShareToMessengerThread(str4, z), str, str2, str5);
        forUploadVideoshotToFacebook.toString();
        postToMediaUploader(forUploadVideoshotToFacebook, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void startSyncImpl() {
        Intent mediaUploaderIntent = getMediaUploaderIntent();
        mediaUploaderIntent.putExtra(MEDIA_UPLOAD_REQUEST_TYPE_KEY, MEDIA_UPLOAD_SYNC_TO_FB_USER_INITIATED);
        CallerInfoHelper.attachCallerInfo(mediaUploaderIntent, this.mContext, AnonymousClass006.A07(getClass().getSimpleName(), ":startMediaSync()"));
        this.mContext.startService(mediaUploaderIntent);
    }
}
