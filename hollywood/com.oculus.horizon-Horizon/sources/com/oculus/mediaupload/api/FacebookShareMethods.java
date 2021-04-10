package com.oculus.mediaupload.api;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.util.thread.ThreadUtils;
import java.io.File;
import javax.annotation.Nullable;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_mediaupload_api_FacebookGraphRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID"})
public class FacebookShareMethods {
    public static final String ME_ID_QUERY = "query {   me {     id   }}";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String OCULUS_SHARE_TO_MESSENGER_MESSAGE_SEND_MUTATION = "mutation($data: OculusShareToMessengerMessageSendData!) {   oculus_share_to_messenger_message_send(data: $data) {     job_id   }}";
    public static final String SELF_PRIVACY = "{\"value\":\"SELF\"}";
    public static final String STORY_CREATE_MUTATION = "Mutation StoryCreate : StoryCreateResponsePayload {   story_create(<input>) {    client_mutation_id  }}";
    public static final String VIEWER_CREATE_TEMPORARY_PHOTO_MUTATION = "mutation($data: ViewerCreateTemporaryPhotoData!) {   viewer_create_temporary_photo(data: $data) {     photo {      id    }  }}";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Methods mMethods;

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$10  reason: invalid class name */
    public class AnonymousClass10 extends Retryable<FacebookShareResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$description;
        public final /* synthetic */ FacebookShareRequest.FacebookSharePrivacy val$privacy;
        public final /* synthetic */ String val$uploadSessionId;
    }

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$11  reason: invalid class name */
    public class AnonymousClass11 extends Retryable<FacebookShareResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$description;
        public final /* synthetic */ String val$gameId;
        public final /* synthetic */ FacebookShareRequest.FacebookSharePrivacy val$privacy;
        public final /* synthetic */ String val$uploadSessionId;
    }

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$13  reason: invalid class name */
    public class AnonymousClass13 extends Retryable<FacebookUploadPhotoResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$caption;
        public final /* synthetic */ FacebookShareRequest.FacebookSharePrivacy val$privacy;
        public final /* synthetic */ TypedFile val$typedScreenshotFile;
    }

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$14  reason: invalid class name */
    public class AnonymousClass14 extends Retryable<FacebookUploadPhotoResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$caption;
        public final /* synthetic */ TypedFile val$typedScreenshotFile;
    }

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$17  reason: invalid class name */
    public class AnonymousClass17 extends Retryable<FacebookUploadPhotoResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$caption;
        public final /* synthetic */ String val$groupId;
        public final /* synthetic */ TypedFile val$typedScreenshotFile;
    }

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$18  reason: invalid class name */
    public class AnonymousClass18 extends Retryable<FacebookShareResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$description;
        public final /* synthetic */ String val$groupId;
        public final /* synthetic */ String val$uploadSessionId;
    }

    /* renamed from: com.oculus.mediaupload.api.FacebookShareMethods$19  reason: invalid class name */
    public class AnonymousClass19 extends Retryable<FacebookShareResponse> {
        public final /* synthetic */ FacebookShareMethods this$0;
        public final /* synthetic */ String val$accessToken;
        public final /* synthetic */ String val$description;
        public final /* synthetic */ String val$gameId;
        public final /* synthetic */ String val$groupId;
        public final /* synthetic */ String val$uploadSessionId;
    }

    public interface Methods {
        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FacebookGetUserIdResponse getUserId(@Header("Authorization") String str, @Query("doc") String str2, @Body String str3);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FacebookShareToMessengerResponse messageSend(@Header("Authorization") String str, @Query("doc") String str2, @Query("variables") String str3, @Body String str4);

        @POST("/{group_id}/videos")
        @FormUrlEncoded
        FacebookShareResponse postVideoToFacebookGroupAfterUpload(@Field("access_token") String str, @Field("description") String str2, @Field("upload_phase") String str3, @Field("upload_session_id") String str4, @Path("group_id") String str5, @Nullable @Field("game_id") String str6);

        @POST("/me/videos")
        @FormUrlEncoded
        FacebookShareResponse postVideoToFacebookTimelineAfterUpload(@Field("access_token") String str, @Field("privacy") String str2, @Field("description") String str3, @Field("upload_phase") String str4, @Field("upload_session_id") String str5, @Nullable @Field("game_id") String str6);

        @POST("/{thread_id}/conversations")
        @FormUrlEncoded
        MessengerSendMessageResponse sendPhotoToMessenger1To1Thread(@Field("access_token") String str, @Nullable @Field("message") String str2, @Field("media") String str3, @Path("thread_id") String str4);

        @POST("/gt_{thread_id}/messages")
        @FormUrlEncoded
        MessengerSendMessageResponse sendPhotoToMessengerGroupThread(@Field("access_token") String str, @Nullable @Field("message") String str2, @Field("media") String str3, @Path("thread_id") String str4);

        @POST("/{group_id}/photos")
        @Multipart
        FacebookUploadPhotoResponse startPhotoUploadToGroup(@Part("caption") String str, @Part("file") TypedFile typedFile, @Part("access_token") String str2, @Path("group_id") String str3, @Part("published") boolean z);

        @POST("/me/photos")
        @Multipart
        FacebookUploadPhotoResponse startPhotoUploadToStories(@Part("caption") String str, @Part("file") TypedFile typedFile, @Part("access_token") String str2, @Part("published") boolean z);

        @POST("/me/photos")
        @Multipart
        FacebookUploadPhotoResponse startPhotoUploadToTimeline(@Part("caption") String str, @Part("file") TypedFile typedFile, @Part("access_token") String str2, @Part("privacy") String str3, @Part("published") boolean z);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FacebookShareGraphQLResponse storyCreate(@Header("Authorization") String str, @Query("q") String str2, @Query("query_params") String str3, @Body String str4);

        @POST("/me/photos")
        @Multipart
        FacebookUploadPhotoResponse syncPhotoToFacebook(@Part("file") TypedFile typedFile, @Part("access_token") String str, @Part("privacy") String str2);

        @POST("/me/videos")
        @FormUrlEncoded
        FacebookShareResponse syncVideoToFacebookAfterUpload(@Field("access_token") String str, @Field("privacy") String str2, @Field("upload_phase") String str3, @Field("upload_session_id") String str4, @Nullable @Field("game_id") String str5);

        @POST("/me/message_images")
        @Multipart
        FacebookUploadPhotoResponse uploadPhotoToMessenger(@Part("file") TypedFile typedFile, @Part("access_token") String str);

        @POST("/{object}/videos")
        @FormUrlEncoded
        FacebookVideoUploadStartResponse videoUploadStart(@Path("object") String str, @Field("access_token") String str2, @Field("upload_phase") String str3, @Field("file_size") long j);

        @POST("/{object}/videos")
        @Multipart
        FacebookVideoUploadTransferResponse videoUploadTransfer(@Path("object") String str, @Part("access_token") String str2, @Part("upload_phase") String str3, @Part("upload_session_id") String str4, @Part("start_offset") long j, @Part("video_file_chunk") TypedByteArrayWithFilename typedByteArrayWithFilename);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Multipart
        FacebookViewerCreateTemporaryPhotoResponse viewerCreateTemporaryPhoto(@Header("Authorization") String str, @Query("doc") String str2, @Query("variables") String str3, @Part("photo") TypedFile typedFile);
    }

    public enum UploadPhase {
        START("start"),
        TRANSFER("transfer"),
        FINISH("finish");
        
        public final String label;

        /* access modifiers changed from: public */
        UploadPhase(String str) {
            this.label = str;
        }
    }

    public final FacebookGetUserIdResponse A00(final String str) throws UploaderException {
        ((ThreadUtils) AnonymousClass0J2.A03(0, 583, this._UL_mInjectionContext)).A05();
        return (FacebookGetUserIdResponse) new Retryable<FacebookGetUserIdResponse>() {
            /* class com.oculus.mediaupload.api.FacebookShareMethods.AnonymousClass9 */
        }.A00();
    }

    public final FacebookViewerCreateTemporaryPhotoResponse A01(File file, final String str, final String str2) throws UploaderException {
        ((ThreadUtils) AnonymousClass0J2.A03(0, 583, this._UL_mInjectionContext)).A05();
        final TypedFile typedFile = new TypedFile("image/jpeg", file);
        return (FacebookViewerCreateTemporaryPhotoResponse) new Retryable<FacebookViewerCreateTemporaryPhotoResponse>() {
            /* class com.oculus.mediaupload.api.FacebookShareMethods.AnonymousClass2 */
        }.A00();
    }

    @Inject
    public FacebookShareMethods(AbstractC06640p5 r3, @FacebookGraphRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
