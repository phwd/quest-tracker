package com.oculus.modules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.facebook.debug.log.BLog;
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
    private static final String HORIZON_MEDIA_UPLOADER_SERVICE_NAME = "com.oculus.horizon.mediaupload.MediaUploaderService";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String MEDIA_UPLOAD_REQUEST_KEY = "request";
    private static final String MEDIA_UPLOAD_REQUEST_TYPE_KEY = "request_type";
    private static final String MEDIA_UPLOAD_RESULT_RECEIVER_KEY = "result_receiver";
    private static final String MEDIA_UPLOAD_SYNC_TO_FB_USER_INITIATED = "SYNC_TO_FB_USER_INITIATED";
    private static final String OVR_MEDIA_SERVICE_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String RESULT_RECEIVER_KEY = "result_receiver";
    private static final String TAG = ShareToFacebookModuleImpl.class.getSimpleName();
    private boolean mCanShareToBugReporter = false;
    private boolean mCanShareToFbStories = false;
    private Context mContext = null;
    private boolean mGksInitialized = false;
    private boolean mIsEmployee = false;
    private boolean mIsRecentsEnabled = false;

    public ShareToFacebookModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void shutdownModule() {
    }

    public void SetGks(boolean isEmployee, boolean isRecentsEnabled, boolean canShareToFbStories, boolean canShareToBugReporter) {
        if (!this.mGksInitialized) {
            this.mIsEmployee = isEmployee;
            this.mIsRecentsEnabled = isRecentsEnabled;
            this.mCanShareToFbStories = canShareToFbStories;
            this.mCanShareToBugReporter = canShareToBugReporter;
            this.mGksInitialized = true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public boolean canShareToFbStoriesImpl() {
        return this.mCanShareToFbStories;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public boolean canShareToBugReporterImpl() {
        return this.mCanShareToBugReporter;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public boolean isEmployeeImpl() {
        return this.mIsEmployee;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public boolean isRecentsEnabledImpl() {
        return this.mIsRecentsEnabled;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void uploadToOculusImpl(boolean isVideo, String fileName, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        if (isVideo) {
            postToMediaUploader(MediaUploaderRequest.forUploadVideoshotToOculus(fileName), resolver);
        } else {
            postToMediaUploader(MediaUploaderRequest.forUploadScreenshotToOculus(fileName), resolver);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void returnShareResultImpl(boolean success, String response, String platformRequestId, String fromPkg) {
        try {
            Intent intent = new Intent("com.oculus.share_to_facebook_result");
            intent.setPackage(fromPkg);
            try {
                intent.putExtra("platform_request_id", Long.parseLong(platformRequestId));
            } catch (NumberFormatException e) {
                Log.e(MODULE_NAME, "Cannot parse platformRequestId " + platformRequestId + ", returning Share result without it.", e);
            }
            intent.putExtra("share_result_json", response);
            intent.putExtra("share_was_successful", success);
            this.mContext.sendBroadcast(intent);
        } catch (Exception e2) {
            Log.e(MODULE_NAME, "Failed to return Share result to the calling app.", e2);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToMessengerThreadImpl(String accessToken, String description, String photoName, String threadId, boolean isGroupThread, String notificationErrorMessage, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        MediaUploaderRequest request = MediaUploaderRequest.forUploadScreenshotToFacebook(photoName, FacebookShareRequest.forShareToMessengerThread(threadId, isGroupThread), accessToken, description, notificationErrorMessage);
        BLog.v(MODULE_NAME, "Sending photo to Messenger Thread with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToMessengerThreadImpl(String accessToken, String description, String videoName, String threadId, boolean isGroupThread, String notificationErrorMessage, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        MediaUploaderRequest request = MediaUploaderRequest.forUploadVideoshotToFacebook(videoName, FacebookShareRequest.forShareToMessengerThread(threadId, isGroupThread), accessToken, description, notificationErrorMessage);
        BLog.v(MODULE_NAME, "Sending video to Messenger Thread with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToFacebookTimelineImpl(String accessToken, String description, String videoName, String privacy, String gameID, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest fbRequest;
        FacebookShareRequest.FacebookSharePrivacy facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.SELF;
        try {
            facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.valueOf(privacy);
        } catch (IllegalArgumentException e) {
            BLog.e(MODULE_NAME, "%s is not a valid privacy", privacy);
        }
        if (gameID.length() == 0 || gameID.equals("0")) {
            fbRequest = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy);
        } else {
            fbRequest = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy, gameID);
        }
        MediaUploaderRequest request = MediaUploaderRequest.forUploadVideoshotToFacebook(videoName, fbRequest, accessToken, description, null);
        BLog.v(MODULE_NAME, "Start posting video to Facebook timeline with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToFacebookStoriesImpl(String accessToken, String description, String videoName, String gameID, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest fbRequest;
        if (gameID.length() == 0 || gameID.equals("0")) {
            fbRequest = FacebookShareRequest.forShareToStories();
        } else {
            fbRequest = FacebookShareRequest.forShareToStories(gameID);
        }
        MediaUploaderRequest request = MediaUploaderRequest.forUploadVideoshotToFacebook(videoName, fbRequest, accessToken, description, null);
        BLog.v(MODULE_NAME, "Start posting video to Facebook stories with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToFacebookTimelineImpl(String accessToken, String description, String photoName, String privacy, String gameID, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest fbRequest;
        FacebookShareRequest.FacebookSharePrivacy facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.SELF;
        try {
            facebookSharePrivacy = FacebookShareRequest.FacebookSharePrivacy.valueOf(privacy);
        } catch (IllegalArgumentException e) {
            BLog.e(MODULE_NAME, "%s is not a valid privacy", privacy);
        }
        if (gameID.length() == 0 || gameID.equals("0")) {
            fbRequest = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy);
        } else {
            fbRequest = FacebookShareRequest.forShareToTimeline(facebookSharePrivacy, gameID);
        }
        MediaUploaderRequest request = MediaUploaderRequest.forUploadScreenshotToFacebook(photoName, fbRequest, accessToken, description, null);
        BLog.v(MODULE_NAME, "Start posting photo to Facebook timeline with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToFacebookStoriesImpl(String accessToken, String description, String photoName, String gameID, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest fbRequest;
        if (gameID.length() == 0 || gameID.equals("0")) {
            fbRequest = FacebookShareRequest.forShareToStories();
        } else {
            fbRequest = FacebookShareRequest.forShareToStories(gameID);
        }
        MediaUploaderRequest request = MediaUploaderRequest.forUploadScreenshotToFacebook(photoName, fbRequest, accessToken, description, null);
        BLog.v(MODULE_NAME, "Start posting photo to Facebook stories with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    private Intent getMediaUploaderIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_MEDIA_UPLOADER_SERVICE_NAME));
        intent.putExtra(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, new ParcelableCallbackReceiver(new MediaUploaderCallbackReceiver()).getReceiverForIPC());
        return intent;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void startSyncImpl() {
        BLog.v(MODULE_NAME, "Start sync");
        Intent intent = getMediaUploaderIntent();
        intent.putExtra(MEDIA_UPLOAD_REQUEST_TYPE_KEY, MEDIA_UPLOAD_SYNC_TO_FB_USER_INITIATED);
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, getClass().getSimpleName() + ":startMediaSync()");
        this.mContext.startService(intent);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postVideoToFacebookGroupImpl(String accessToken, String description, String videoName, String groupId, String gameID, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest fbRequest;
        if (gameID.length() == 0 || gameID.equals("0")) {
            fbRequest = FacebookShareRequest.forShareToGroup(groupId);
        } else {
            fbRequest = FacebookShareRequest.forShareToGroup(groupId, gameID);
        }
        MediaUploaderRequest request = MediaUploaderRequest.forUploadVideoshotToFacebook(videoName, fbRequest, accessToken, description, null);
        BLog.v(MODULE_NAME, "Start posting video to Facebook group with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void postPhotoToFacebookGroupImpl(String accessToken, String description, String photoName, String groupId, String gameID, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        FacebookShareRequest fbRequest;
        if (gameID.length() == 0 || gameID.equals("0")) {
            fbRequest = FacebookShareRequest.forShareToGroup(groupId);
        } else {
            fbRequest = FacebookShareRequest.forShareToGroup(groupId, gameID);
        }
        MediaUploaderRequest request = MediaUploaderRequest.forUploadScreenshotToFacebook(photoName, fbRequest, accessToken, description, null);
        BLog.v(MODULE_NAME, "Start posting photo to Facebook group with request: %s", request.toString());
        postToMediaUploader(request, resolver);
    }

    private void postToMediaUploader(MediaUploaderRequest request, Resolver<ShareToFacebookModule.MediaUploadResponse> resolver) {
        try {
            Intent intent = getMediaUploaderIntent();
            intent.putExtra(MEDIA_UPLOAD_REQUEST_KEY, request);
            CallerInfoHelper.attachCallerInfo(intent, this.mContext, getClass().getSimpleName() + ":startUploadMedia()");
            this.mContext.startService(intent);
        } catch (Exception exception) {
            resolver.reject(exception);
            BLog.e(MODULE_NAME, exception, "Sending intent to upload media failed.");
        }
        resolver.resolve(ShareToFacebookModule.MediaUploadResponse.makeFromJSONObject(new JSONObject()));
    }

    /* access modifiers changed from: private */
    public class MediaUploaderCallbackReceiver implements MediaUploaderCallback {
        public MediaUploaderCallbackReceiver() {
        }

        @Override // com.oculus.mediaupload.model.MediaUploaderCallback
        public void onResult(MediaUploaderResult result) {
            BLog.v(ShareToFacebookModuleImpl.MODULE_NAME, "MediaUploaderResult dumper result = %s", result.toString());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ShareToFacebookModule
    public void getMediaMetadataImpl(String filename, final Resolver<ShareToFacebookModule.MediaMetadata> resolver) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.horizon", OVR_MEDIA_SERVICE_NAME));
            intent.putExtra("message_type", "com.oculus.horizon.GET_MEDIA_METADATA");
            intent.putExtra(OCMSLibraryContract.ASSETS_PATH_BY_FILENAME, filename);
            intent.putExtra(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, getCrossPackageResultReceiver(new ResultReceiver(null) {
                /* class com.oculus.modules.ShareToFacebookModuleImpl.AnonymousClass1 */

                /* access modifiers changed from: protected */
                public void onReceiveResult(int resultCode, Bundle resultData) {
                    if (resultCode != -1 || resultData == null) {
                        resolver.reject(new Error("Unable to receive results from OVRMediaService"));
                    }
                    try {
                        resolver.resolve(ShareToFacebookModule.MediaMetadata.makeFromJSONObject(new JSONObject(resultData.getString("media_metadata_json"))));
                    } catch (JSONException e) {
                        BLog.e(ShareToFacebookModuleImpl.MODULE_NAME, e, "Exception while parsing media metadata");
                        resolver.reject(e);
                    }
                }
            }));
            CallerInfoHelper.attachCallerInfo(intent, this.mContext, MODULE_NAME + ":getMediaMetadata()");
            this.mContext.startService(intent);
        } catch (Exception e) {
            BLog.e(MODULE_NAME, e, "Failed to send intent to get media metadeata from OVRMediaService");
            resolver.reject(e);
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
