package com.oculus.horizon.mediaupload.dumper;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass0dM;
import androidx.annotation.RequiresApi;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.mediaupload.model.MediaUploaderCallback;
import com.oculus.mediaupload.model.MediaUploaderResult;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_mediaupload_MediaUploaderSyncServiceInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_io_FacebookGamingProfileTokenManager_ULSEP_BINDING_ID"})
@RequiresApi(api = 21)
public class MediaUploadDumperPlugin implements AnonymousClass0dM {
    public static final String CMD_DELETE_FB_GAMING_PROFILE_ACCESS_TOKEN = "delete_fb_gaming_profile_access_token";
    public static final String CMD_DELETE_MEDIA_METADATA = "delete_media_metadata";
    public static final String CMD_PRINT_FB_GAMING_PROFILE_ACCESS_TOKEN = "print_fb_gaming_profile_access_token";
    public static final String CMD_READ_MEDIA_METADATA = "read_media_metadata";
    public static final String CMD_REQUEST_FB_GAMING_PROFILE_ACCESS_TOKEN = "request_fb_gaming_profile_access_token";
    public static final String CMD_SCHEDULE_SYNC = "schedule_sync";
    public static final String CMD_SEND_FB_SCREENSHOT_UPLOAD_FAILURE_NOTIFICATION = "send_fb_screenshot_upload_failure_notification";
    public static final String CMD_SEND_FB_SCREENSHOT_UPLOAD_SUCCESS_NOTIFICATION = "send_fb_screenshot_upload_success_notification";
    public static final String CMD_SEND_FB_VIDEO_UPLOAD_FAILURE_NOTIFICATION = "send_fb_video_upload_failure_notification";
    public static final String CMD_SEND_FB_VIDEO_UPLOAD_PROGRESS_NOTIFICATION = "send_fb_video_upload_progress_notification";
    public static final String CMD_SEND_FB_VIDEO_UPLOAD_START_NOTIFICATION = "send_fb_video_upload_start_notification";
    public static final String CMD_SEND_FB_VIDEO_UPLOAD_SUCCESS_NOTIFICATION = "send_fb_video_upload_success_notification";
    public static final String CMD_SEND_OCULUS_SCREENSHOT_UPLOAD_FAILURE_NOTIFICATION = "send_oculus_screenshot_upload_failure_notification";
    public static final String CMD_SEND_OCULUS_SCREENSHOT_UPLOAD_SUCCESS_NOTIFICATION = "send_oculus_screenshot_upload_success_notification";
    public static final String CMD_SEND_OCULUS_VIDEO_UPLOAD_FAILURE_NOTIFICATION = "send_oculus_video_upload_failure_notification";
    public static final String CMD_SEND_OCULUS_VIDEO_UPLOAD_PROGRESS_NOTIFICATION = "send_oculus_video_upload_progress_notification";
    public static final String CMD_SEND_OCULUS_VIDEO_UPLOAD_START_NOTIFICATION = "send_oculus_video_upload_start_notification";
    public static final String CMD_SEND_OCULUS_VIDEO_UPLOAD_SUCCESS_NOTIFICATION = "send_oculus_video_upload_success_notification";
    public static final String CMD_SEND_REQUEST_FB_GAMING_LOGIN_NOTIFICATION = "send_request_fb_gaming_login_notification";
    public static final String CMD_START_MEDIA_OBSERVER = "start_media_observer";
    public static final String CMD_START_SYNC = "start_sync";
    public static final String CMD_STOP_MEDIA_OBSERVER = "stop_media_observer";
    public static final String CMD_STORE_FB_GAMING_PROFILE_ACCESS_TOKEN = "store_fb_gaming_profile_access_token";
    public static final String CMD_STORE_FB_GAMING_PROFILE_ACCESS_TOKEN_THROUGH_SERVICE = "store_fb_gaming_profile_access_token_through_service";
    public static final String CMD_SYNC_SCREENSHOT_TO_FACEBOOK = "sync_screenshot_to_facebook";
    public static final String CMD_SYNC_VIDEOSHOT_TO_FACEBOOK = "sync_videoshot_to_facebook";
    public static final String CMD_UPLOADED_FILES = "uploaded_files";
    public static final String CMD_UPLOAD_SCREENSHOT = "upload_screenshot";
    public static final String CMD_UPLOAD_SCREENSHOT_TO_FACEBOOK_GROUP = "upload_screenshot_to_facebook_group";
    public static final String CMD_UPLOAD_SCREENSHOT_TO_FACEBOOK_TIMELINE = "upload_screenshot_to_facebook_timeline";
    public static final String CMD_UPLOAD_VIDEOSHOT = "upload_videoshot";
    public static final String CMD_UPLOAD_VIDEOSHOT_TO_FACEBOOK_GROUP = "upload_videoshot_to_facebook_group";
    public static final String CMD_UPLOAD_VIDEOSHOT_TO_FACEBOOK_GROUP_WITH_GAME_ID = "upload_videoshot_to_facebook_group_with_game_id";
    public static final String CMD_UPLOAD_VIDEOSHOT_TO_FACEBOOK_TIMELINE = "upload_videoshot_to_facebook_timeline";
    public static final String CMD_UPLOAD_VIDEOSHOT_TO_FACEBOOK_TIMELINE_WITH_GAME_ID = "upload_videoshot_to_facebook_timeline_with_game_id";
    public static final String CMD_WRITE_MEDIA_METADATA = "write_media_metadata";
    public static final String NAME = "mediaupload";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public MediaUploadDumperPlugin(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }

    public static class MediaUploaderCallbackPrinter implements MediaUploaderCallback {
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.oculus.ipc.common.ParcelableCallbackReceiver.Callback
        public final void onResult(MediaUploaderResult mediaUploaderResult) {
            mediaUploaderResult.toString();
        }
    }
}
