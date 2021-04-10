package com.oculus.horizon.abuse_prevention;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import com.facebook.ultralight.Dependencies;
import com.oculus.horizon.logging.LoggingEvents;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.util.thread.ThreadUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID"})
public class VideoUploaderMethods {
    public static final String ABUSE_REPORT_VIDEO = "ABUSE_REPORT_VIDEO";
    public static final int CHUNK_SIZE = 2000000;
    public static final int MAX_RETRIES = 5;
    public static final String TAG = "VideoUploaderMethods";
    public final Methods mMethods;
    public final ThreadUtils mThreadUtils;

    public interface Methods {
        @POST("/video_upload_start")
        @FormUrlEncoded
        VideoUploadStartResponse videoUploadStart(@Field("file_name") String str, @Field("file_size") long j, @Field("mime_type") String str2, @Field("chunk_size") int i);

        @POST("/video_upload_submit")
        @FormUrlEncoded
        VideoUploadSubmitResponse videoUploadSubmit(@Field("target_object_id") String str, @Field("video_type") String str2, @Field("upload_session_id") String str3);

        @POST("/video_upload_transfer")
        @Multipart
        VideoUploadTransferResponse videoUploadTransfer(@Part("upload_session_id") String str, @Part("start_offset") long j, @Part("chunk") TypedByteArrayWithFilename typedByteArrayWithFilename);
    }

    public static abstract class Retryable<TResult> {
        public final TResult A00(OculusLogger.VideoUploaderLogger videoUploaderLogger) throws VideoUploaderException {
            String str;
            String str2;
            RetrofitError e = null;
            long j = 1000;
            int i = 0;
            while (i < 5) {
                try {
                    if (this instanceof AnonymousClass3) {
                        AnonymousClass3 r7 = (AnonymousClass3) this;
                        return (TResult) VideoUploaderMethods.this.mMethods.videoUploadSubmit(r7.val$targetObjectId, r7.val$videoType, r7.val$uploadSessionId);
                    } else if (!(this instanceof AnonymousClass2)) {
                        AnonymousClass1 r72 = (AnonymousClass1) this;
                        return (TResult) VideoUploaderMethods.this.mMethods.videoUploadStart(r72.val$fileName, r72.val$fileSize, r72.val$mimeType, r72.val$chunkSize);
                    } else {
                        AnonymousClass2 r73 = (AnonymousClass2) this;
                        return (TResult) VideoUploaderMethods.this.mMethods.videoUploadTransfer(r73.val$uploadSessionId, r73.val$startOffset, r73.val$chunk);
                    }
                } catch (RetrofitError e2) {
                    e = e2;
                    i++;
                    AnonymousClass0NO.A0K(VideoUploaderMethods.TAG, e, "Got error, retrying (retry %s of %s) after sleeping for %s ms.", Integer.valueOf(i), 5, Long.valueOf(j));
                    if (this instanceof AnonymousClass3) {
                        str2 = "/video_upload_submit";
                    } else if (!(this instanceof AnonymousClass2)) {
                        str2 = "/video_upload_start";
                    } else {
                        str2 = "/video_upload_transfer";
                    }
                    StringWriter stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    e.printStackTrace(printWriter);
                    printWriter.flush();
                    String obj = stringWriter.toString();
                    Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, OculusLogger.this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_VIDEO_UPLOAD_RETRY);
                    A22.A15("reason", obj);
                    A22.A13(LoggingKeys.RETRY_NUMBER, i);
                    A22.A15("method", str2);
                    OculusLogger.VideoUploaderLogger.A00(videoUploaderLogger, A22);
                    try {
                        Thread.sleep(j);
                    } catch (InterruptedException e3) {
                        AnonymousClass0NO.A0B(VideoUploaderMethods.TAG, "InterruptedException", e3);
                    }
                    j *= 2;
                }
            }
            Object[] objArr = new Object[1];
            if (this instanceof AnonymousClass3) {
                str = "/video_upload_submit";
            } else if (!(this instanceof AnonymousClass2)) {
                str = "/video_upload_start";
            } else {
                str = "/video_upload_transfer";
            }
            objArr[0] = str;
            throw new VideoUploaderException(e, "Ran out of retries for %s", objArr);
        }
    }

    @Inject
    public VideoUploaderMethods(@OculusRestAdapter RestAdapter restAdapter, ThreadUtils threadUtils) {
        this.mMethods = (Methods) restAdapter.create(Methods.class);
        this.mThreadUtils = threadUtils;
    }
}
