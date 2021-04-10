package com.oculus.mediaupload.api;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.util.thread.ThreadUtils;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_api_MediaUploaderMetadataHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID"})
public class OculusShareMethods {
    public static final String FB_GAMING_ID_QUERY = "  query ($app_id: ID!){  node(node_id: $app_id) {    ... on Application {      grouping {        facebook_game_id      }    }  }}";
    public static final String IMAGE_2D_SHARE_TYPE = "IMAGE_2D";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_MP4 = "video/mp4";
    public static final String SHARING_AUTO_SYNC_PREFERENCE_QUERY = "  query ($device_id: ID!){  me {    device_preferences(device_id: $device_id)  {      sharing_auto_sync_preference    }  }}";
    public static final String USER_SHAREABLE_VIDEO = "USER_SHAREABLE_VIDEO";
    public static final String VIDEO_2D_SHARE_TYPE = "VIDEO_2D";
    @Inject
    @Eager
    public final MediaUploaderMetadataHelper mMediaUploaderMetadataHelper;
    public final Methods mMethods;
    @Inject
    @Eager
    public final ThreadUtils mThreadUtils;

    public interface Methods {
        @POST("/upload_user_media")
        @Multipart
        MediaUploaderResponse createVideo(@Part("file_name") String str, @Part("file_size") long j, @Part("share_type") String str2, @Part("app_id") String str3, @Part("capture_timestamp") long j2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SharingAutoSyncPreferenceResponse getDeviceSharingAutoSyncPreference(@Query("doc") String str, @Query("variables") String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FacebookGamingIdResponse getFBGamingId(@Query("doc") String str, @Query("variables") String str2);

        @POST("/upload_user_media")
        @Multipart
        MediaUploaderResponse uploadImage(@Part("file") TypedFile typedFile, @Part("file_name") String str, @Part("file_size") long j, @Part("share_type") String str2, @Part("app_id") String str3, @Part("capture_timestamp") long j2);

        @POST("/video_upload_start")
        @FormUrlEncoded
        OculusVideoUploadStartResponse videoUploadStart(@Field("file_name") String str, @Field("file_size") long j, @Field("mime_type") String str2, @Field("chunk_size") int i);

        @POST("/video_upload_submit")
        @FormUrlEncoded
        OculusVideoUploadSubmitResponse videoUploadSubmit(@Field("target_object_id") String str, @Field("video_type") String str2, @Field("upload_session_id") String str3);

        @POST("/video_upload_transfer")
        @Multipart
        OculusVideoUploadTransferResponse videoUploadTransfer(@Part("upload_session_id") String str, @Part("start_offset") long j, @Part("chunk") TypedByteArrayWithFilename typedByteArrayWithFilename);
    }

    @Inject
    public OculusShareMethods(AbstractC06640p5 r2, @OculusRestAdapter RestAdapter restAdapter) {
        this.mMediaUploaderMetadataHelper = (MediaUploaderMetadataHelper) AnonymousClass117.A00(197, r2);
        this.mThreadUtils = ThreadUtils.A01(r2);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
