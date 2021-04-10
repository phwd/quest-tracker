package com.oculus.mediaupload.api;

import X.AnonymousClass0NO;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.common.graphql.GraphQLParamsHelper;
import com.oculus.http.core.common.Authorization;
import com.oculus.mediaupload.api.FacebookShareMethods;
import com.oculus.mediaupload.api.OculusShareMethods;
import com.oculus.mediaupload.model.MediaMetadata;
import com.oculus.mediaupload.request.FacebookViewerCreateTemporaryPhotoData;
import com.oculus.mediaupload.request.OculusShareToMessengerMessageSendData;
import com.oculus.mediaupload.request.StoryAttachmentParam;
import com.oculus.mediaupload.request.StoryCreateParams;
import com.oculus.mediaupload.request.StoryPrivacyParam;
import retrofit.RetrofitError;

public abstract class Retryable<TResult> {
    public static final int MAX_RETRIES = 5;
    public static final String TAG = "Retryable";

    public final TResult A00() throws UploaderException {
        StoryCreateParams.StoryVRMetadataParam storyVRMetadataParam;
        StoryCreateParams.StoryVRMetadataParam storyVRMetadataParam2;
        StoryCreateParams.StoryVRMetadataParam storyVRMetadataParam3;
        StoryCreateParams.StoryVRMetadataParam storyVRMetadataParam4;
        StoryCreateParams.StoryVRMetadataParam storyVRMetadataParam5;
        StoryCreateParams.StoryVRMetadataParam storyVRMetadataParam6;
        RetrofitError e = null;
        long j = 1000;
        int i = 0;
        while (i < 5) {
            try {
                if (this instanceof OculusShareMethods.AnonymousClass5) {
                    OculusShareMethods.AnonymousClass5 r0 = (OculusShareMethods.AnonymousClass5) this;
                    return (TResult) OculusShareMethods.this.mMethods.videoUploadSubmit(r0.val$targetObjectId, r0.val$videoType, r0.val$uploadSessionId);
                } else if (this instanceof OculusShareMethods.AnonymousClass4) {
                    OculusShareMethods.AnonymousClass4 r02 = (OculusShareMethods.AnonymousClass4) this;
                    return (TResult) OculusShareMethods.this.mMethods.videoUploadTransfer(r02.val$uploadSessionId, r02.val$startOffset, r02.val$chunk);
                } else if (this instanceof OculusShareMethods.AnonymousClass3) {
                    OculusShareMethods.AnonymousClass3 r03 = (OculusShareMethods.AnonymousClass3) this;
                    return (TResult) OculusShareMethods.this.mMethods.videoUploadStart(r03.val$fileName, r03.val$fileSize, r03.val$mimeType, r03.val$chunkSize);
                } else if (this instanceof OculusShareMethods.AnonymousClass2) {
                    OculusShareMethods.AnonymousClass2 r04 = (OculusShareMethods.AnonymousClass2) this;
                    return (TResult) OculusShareMethods.this.mMethods.createVideo(r04.val$videoFile.getName(), r04.val$videoFile.length(), OculusShareMethods.VIDEO_2D_SHARE_TYPE, r04.val$appID, r04.val$captureTimestamp);
                } else if (this instanceof OculusShareMethods.AnonymousClass1) {
                    OculusShareMethods.AnonymousClass1 r05 = (OculusShareMethods.AnonymousClass1) this;
                    ImmutableMap.Builder A01 = ImmutableMap.A01();
                    A01.put("app_id", r05.val$appId);
                    return (TResult) OculusShareMethods.this.mMethods.getFBGamingId(OculusShareMethods.FB_GAMING_ID_QUERY, GraphQLParamsHelper.GSON_CONVERTER.A06(A01.build()));
                } else if (this instanceof FacebookShareMethods.AnonymousClass9) {
                    FacebookShareMethods.AnonymousClass9 r06 = (FacebookShareMethods.AnonymousClass9) this;
                    return (TResult) FacebookShareMethods.this.mMethods.getUserId(Authorization.A00(str), FacebookShareMethods.ME_ID_QUERY, "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass8) {
                    FacebookShareMethods.AnonymousClass8 r07 = (FacebookShareMethods.AnonymousClass8) this;
                    StoryAttachmentParam.FacebookShareAttachmentType facebookShareAttachmentType = StoryAttachmentParam.FacebookShareAttachmentType.PHOTO;
                    String str = r07.val$photoId;
                    String str2 = r07.val$gameId;
                    StoryAttachmentParam storyAttachmentParam = new StoryAttachmentParam(facebookShareAttachmentType, str, str2);
                    String str3 = r07.val$actor_id;
                    StoryCreateParams.StoryAudienceParam storyAudienceParam = new StoryCreateParams.StoryAudienceParam(str3, true);
                    MediaMetadata mediaMetadata = r07.val$mediaMetadata;
                    if (mediaMetadata == null || str2 == null) {
                        storyVRMetadataParam = null;
                    } else {
                        storyVRMetadataParam = new StoryCreateParams.StoryVRMetadataParam(mediaMetadata, StoryCreateParams.StoryVRMetadataParam.SourceType.VR_PHOTO_SHARING);
                    }
                    return (TResult) FacebookShareMethods.this.mMethods.storyCreate(Authorization.A00(r07.val$accessToken), FacebookShareMethods.STORY_CREATE_MUTATION, new StoryCreateParams(str3, storyAttachmentParam, storyAudienceParam, r07.val$text, storyVRMetadataParam).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass7) {
                    FacebookShareMethods.AnonymousClass7 r08 = (FacebookShareMethods.AnonymousClass7) this;
                    StoryAttachmentParam storyAttachmentParam2 = new StoryAttachmentParam(StoryAttachmentParam.FacebookShareAttachmentType.VIDEO, r08.val$videoId, r08.val$gameId);
                    StoryCreateParams.StoryAudienceParam storyAudienceParam2 = new StoryCreateParams.StoryAudienceParam(r08.val$group_id, false);
                    MediaMetadata mediaMetadata2 = r08.val$mediaMetadata;
                    if (mediaMetadata2 == null) {
                        storyVRMetadataParam2 = null;
                    } else {
                        storyVRMetadataParam2 = new StoryCreateParams.StoryVRMetadataParam(mediaMetadata2, StoryCreateParams.StoryVRMetadataParam.SourceType.VR_VIDEO_SHARING);
                    }
                    return (TResult) FacebookShareMethods.this.mMethods.storyCreate(Authorization.A00(r08.val$accessToken), FacebookShareMethods.STORY_CREATE_MUTATION, new StoryCreateParams(r08.val$actor_id, storyAttachmentParam2, storyAudienceParam2, r08.val$text, storyVRMetadataParam2).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass6) {
                    FacebookShareMethods.AnonymousClass6 r09 = (FacebookShareMethods.AnonymousClass6) this;
                    StoryAttachmentParam.FacebookShareAttachmentType facebookShareAttachmentType2 = StoryAttachmentParam.FacebookShareAttachmentType.PHOTO;
                    String str4 = r09.val$photoId;
                    String str5 = r09.val$gameId;
                    StoryAttachmentParam storyAttachmentParam3 = new StoryAttachmentParam(facebookShareAttachmentType2, str4, str5);
                    StoryCreateParams.StoryAudienceParam storyAudienceParam3 = new StoryCreateParams.StoryAudienceParam(r09.val$group_id, false);
                    MediaMetadata mediaMetadata3 = r09.val$mediaMetadata;
                    if (mediaMetadata3 == null || str5 == null) {
                        storyVRMetadataParam3 = null;
                    } else {
                        storyVRMetadataParam3 = new StoryCreateParams.StoryVRMetadataParam(mediaMetadata3, StoryCreateParams.StoryVRMetadataParam.SourceType.VR_PHOTO_SHARING);
                    }
                    return (TResult) FacebookShareMethods.this.mMethods.storyCreate(Authorization.A00(r09.val$accessToken), FacebookShareMethods.STORY_CREATE_MUTATION, new StoryCreateParams(r09.val$actor_id, storyAttachmentParam3, storyAudienceParam3, r09.val$text, storyVRMetadataParam3).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass5) {
                    FacebookShareMethods.AnonymousClass5 r010 = (FacebookShareMethods.AnonymousClass5) this;
                    StoryAttachmentParam storyAttachmentParam4 = new StoryAttachmentParam(StoryAttachmentParam.FacebookShareAttachmentType.VIDEO, r010.val$videoId, r010.val$gameId);
                    String str6 = r010.val$actor_id;
                    StoryCreateParams.StoryAudienceParam storyAudienceParam4 = new StoryCreateParams.StoryAudienceParam(str6, true);
                    MediaMetadata mediaMetadata4 = r010.val$mediaMetadata;
                    if (mediaMetadata4 == null) {
                        storyVRMetadataParam4 = null;
                    } else {
                        storyVRMetadataParam4 = new StoryCreateParams.StoryVRMetadataParam(mediaMetadata4, StoryCreateParams.StoryVRMetadataParam.SourceType.VR_VIDEO_SHARING);
                    }
                    return (TResult) FacebookShareMethods.this.mMethods.storyCreate(Authorization.A00(r010.val$accessToken), FacebookShareMethods.STORY_CREATE_MUTATION, new StoryCreateParams(str6, storyAttachmentParam4, storyAudienceParam4, r010.val$text, storyVRMetadataParam4).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass4) {
                    FacebookShareMethods.AnonymousClass4 r011 = (FacebookShareMethods.AnonymousClass4) this;
                    StoryAttachmentParam storyAttachmentParam5 = new StoryAttachmentParam(StoryAttachmentParam.FacebookShareAttachmentType.VIDEO, r011.val$videoId, r011.val$gameId);
                    StoryCreateParams.StoryAudienceParam storyAudienceParam5 = new StoryCreateParams.StoryAudienceParam(new StoryPrivacyParam(r011.val$privacy));
                    MediaMetadata mediaMetadata5 = r011.val$mediaMetadata;
                    if (mediaMetadata5 == null) {
                        storyVRMetadataParam5 = null;
                    } else {
                        storyVRMetadataParam5 = new StoryCreateParams.StoryVRMetadataParam(mediaMetadata5, StoryCreateParams.StoryVRMetadataParam.SourceType.VR_VIDEO_SHARING);
                    }
                    return (TResult) FacebookShareMethods.this.mMethods.storyCreate(Authorization.A00(r011.val$accessToken), FacebookShareMethods.STORY_CREATE_MUTATION, new StoryCreateParams(r011.val$actor_id, storyAttachmentParam5, storyAudienceParam5, r011.val$text, storyVRMetadataParam5).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass3) {
                    FacebookShareMethods.AnonymousClass3 r012 = (FacebookShareMethods.AnonymousClass3) this;
                    StoryAttachmentParam.FacebookShareAttachmentType facebookShareAttachmentType3 = StoryAttachmentParam.FacebookShareAttachmentType.PHOTO;
                    String str7 = r012.val$photoId;
                    String str8 = r012.val$gameId;
                    StoryAttachmentParam storyAttachmentParam6 = new StoryAttachmentParam(facebookShareAttachmentType3, str7, str8);
                    StoryCreateParams.StoryAudienceParam storyAudienceParam6 = new StoryCreateParams.StoryAudienceParam(new StoryPrivacyParam(r012.val$privacy));
                    MediaMetadata mediaMetadata6 = r012.val$mediaMetadata;
                    if (mediaMetadata6 == null || str8 == null) {
                        storyVRMetadataParam6 = null;
                    } else {
                        storyVRMetadataParam6 = new StoryCreateParams.StoryVRMetadataParam(mediaMetadata6, StoryCreateParams.StoryVRMetadataParam.SourceType.VR_PHOTO_SHARING);
                    }
                    return (TResult) FacebookShareMethods.this.mMethods.storyCreate(Authorization.A00(r012.val$accessToken), FacebookShareMethods.STORY_CREATE_MUTATION, new StoryCreateParams(r012.val$actor_id, storyAttachmentParam6, storyAudienceParam6, r012.val$text, storyVRMetadataParam6).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass2) {
                    FacebookShareMethods.AnonymousClass2 r013 = (FacebookShareMethods.AnonymousClass2) this;
                    return (TResult) FacebookShareMethods.this.mMethods.viewerCreateTemporaryPhoto(Authorization.A00(str2), FacebookShareMethods.VIEWER_CREATE_TEMPORARY_PHOTO_MUTATION, new FacebookViewerCreateTemporaryPhotoData(str).toString(), typedFile);
                } else if (this instanceof FacebookShareMethods.AnonymousClass21) {
                    FacebookShareMethods.AnonymousClass21 r014 = (FacebookShareMethods.AnonymousClass21) this;
                    return (TResult) FacebookShareMethods.this.mMethods.videoUploadTransfer(r014.val$object, r014.val$accessToken, FacebookShareMethods.UploadPhase.TRANSFER.label, r014.val$uploadSessionId, r014.val$startOffset, r014.val$chunk);
                } else if (this instanceof FacebookShareMethods.AnonymousClass20) {
                    FacebookShareMethods.AnonymousClass20 r015 = (FacebookShareMethods.AnonymousClass20) this;
                    return (TResult) FacebookShareMethods.this.mMethods.videoUploadStart(r015.val$object, r015.val$accessToken, FacebookShareMethods.UploadPhase.START.label, r015.val$fileSize);
                } else if (this instanceof FacebookShareMethods.AnonymousClass1) {
                    FacebookShareMethods.AnonymousClass1 r016 = (FacebookShareMethods.AnonymousClass1) this;
                    return (TResult) FacebookShareMethods.this.mMethods.messageSend(Authorization.A00(r016.val$accessToken), FacebookShareMethods.OCULUS_SHARE_TO_MESSENGER_MESSAGE_SEND_MUTATION, new OculusShareToMessengerMessageSendData(r016.val$threadId, r016.val$videoId, r016.val$text).toString(), "");
                } else if (this instanceof FacebookShareMethods.AnonymousClass16) {
                    FacebookShareMethods.AnonymousClass16 r017 = (FacebookShareMethods.AnonymousClass16) this;
                    return r017.val$isGroupThread ? (TResult) FacebookShareMethods.this.mMethods.sendPhotoToMessengerGroupThread(r017.val$accessToken, r017.val$message, r017.val$imageId, r017.val$threadId) : (TResult) FacebookShareMethods.this.mMethods.sendPhotoToMessenger1To1Thread(r017.val$accessToken, r017.val$message, r017.val$imageId, r017.val$threadId);
                } else if (!(this instanceof FacebookShareMethods.AnonymousClass15)) {
                    FacebookShareMethods.AnonymousClass12 r018 = (FacebookShareMethods.AnonymousClass12) this;
                    return (TResult) FacebookShareMethods.this.mMethods.syncPhotoToFacebook(r018.val$typedScreenshotFile, r018.val$accessToken, FacebookShareMethods.SELF_PRIVACY);
                } else {
                    FacebookShareMethods.AnonymousClass15 r019 = (FacebookShareMethods.AnonymousClass15) this;
                    return (TResult) FacebookShareMethods.this.mMethods.uploadPhotoToMessenger(r019.val$typedScreenshotFile, r019.val$accessToken);
                }
            } catch (RetrofitError e2) {
                e = e2;
                i++;
                AnonymousClass0NO.A0K(TAG, e, "Got error, retrying (retry %s of %s) after sleeping for %s ms.", Integer.valueOf(i), 5, Long.valueOf(j));
                try {
                    Thread.sleep(j);
                } catch (InterruptedException e3) {
                    AnonymousClass0NO.A0B(TAG, "InterruptedException", e3);
                }
                j *= 2;
            }
        }
        if (e != null) {
            throw new UploaderException(e, new Object[0]);
        }
        throw new UploaderException();
    }
}
