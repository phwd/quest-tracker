package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderResult implements Parcelable {
    public static final Parcelable.Creator<MediaUploaderResult> CREATOR = new Parcelable.Creator<MediaUploaderResult>() {
        /* class com.oculus.mediaupload.model.MediaUploaderResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaUploaderResult createFromParcel(Parcel parcel) {
            return new MediaUploaderResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MediaUploaderResult[] newArray(int i) {
            return new MediaUploaderResult[i];
        }
    };
    @Nullable
    public final String error;
    @Nullable
    public final String objectId;
    public final float progress;
    public final MediaUploaderResultType type;

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class MediaUploaderRequestError extends Enum<MediaUploaderRequestError> {
        public static final /* synthetic */ MediaUploaderRequestError[] $VALUES;
        public static final MediaUploaderRequestError CHUNK_READ_FAILED;
        public static final MediaUploaderRequestError CHUNK_UPLOAD_FAILED;
        public static final MediaUploaderRequestError CREATE_POST_TO_OCULUS_FAILED;
        public static final MediaUploaderRequestError FACEBOOK_MESSENGER_IMAGE_ID_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_ACCESS_TOKEN_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_DESCRIPTION_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_GROUP_ID_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_MEDIA_URL_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_PRIVACY_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SHARE_TYPE_MISSING;
        public static final MediaUploaderRequestError FACEBOOK_SYNC_SHARE_ACCESS_TOKEN_MISSING;
        public static final MediaUploaderRequestError FINISH_POST_TO_FB_FAILED;
        public static final MediaUploaderRequestError FINISH_POST_TO_MESSENGER_FAILED;
        public static final MediaUploaderRequestError MEDIA_FILE_DOES_NOT_EXIST;
        public static final MediaUploaderRequestError MEDIA_NAME_MISSING;
        public static final MediaUploaderRequestError MEDIA_UPLOADER_REQUEST_MISSING;
        public static final MediaUploaderRequestError MEDIA_UPLOADER_RESULT_RECEIVER_MISSING;
        public static final MediaUploaderRequestError MEDIA_URL_MISSING;
        public static final MediaUploaderRequestError MESSENGER_THREAD_ID_MISSING;
        public static final MediaUploaderRequestError POST_TO_FB_FAILED;
        public static final MediaUploaderRequestError POST_TO_MESSENGER_FAILED;
        public static final MediaUploaderRequestError POST_TO_OCULUS_FAILED;
        public static final MediaUploaderRequestError START_POST_TO_FB_FAILED;
        public static final MediaUploaderRequestError START_POST_TO_OCULUS_FAILED;
        public static final MediaUploaderRequestError TYPE_MISSING;
        public static final MediaUploaderRequestError UNAUTHORIZED_REQUEST;
        public static final MediaUploaderRequestError UNKNOWN_SHARE_TYPE;
        public static final MediaUploaderRequestError UNKNOWN_TYPE;
        public static final MediaUploaderRequestError UPLOAD_PHASE_MISSING;
        public static final MediaUploaderRequestError UPLOAD_SESSION_ID_MISSING;
        public static final MediaUploaderRequestError VIDEO_ID_MISSING;

        static {
            MediaUploaderRequestError mediaUploaderRequestError = new MediaUploaderRequestError("UNKNOWN_TYPE", 0);
            UNKNOWN_TYPE = mediaUploaderRequestError;
            MediaUploaderRequestError mediaUploaderRequestError2 = new MediaUploaderRequestError("UNKNOWN_SHARE_TYPE", 1);
            UNKNOWN_SHARE_TYPE = mediaUploaderRequestError2;
            MediaUploaderRequestError mediaUploaderRequestError3 = new MediaUploaderRequestError("UNAUTHORIZED_REQUEST", 2);
            UNAUTHORIZED_REQUEST = mediaUploaderRequestError3;
            MediaUploaderRequestError mediaUploaderRequestError4 = new MediaUploaderRequestError("MEDIA_UPLOADER_REQUEST_MISSING", 3);
            MEDIA_UPLOADER_REQUEST_MISSING = mediaUploaderRequestError4;
            MediaUploaderRequestError mediaUploaderRequestError5 = new MediaUploaderRequestError("MEDIA_UPLOADER_RESULT_RECEIVER_MISSING", 4);
            MEDIA_UPLOADER_RESULT_RECEIVER_MISSING = mediaUploaderRequestError5;
            MediaUploaderRequestError mediaUploaderRequestError6 = new MediaUploaderRequestError("TYPE_MISSING", 5);
            TYPE_MISSING = mediaUploaderRequestError6;
            MediaUploaderRequestError mediaUploaderRequestError7 = new MediaUploaderRequestError("MEDIA_NAME_MISSING", 6);
            MEDIA_NAME_MISSING = mediaUploaderRequestError7;
            MediaUploaderRequestError mediaUploaderRequestError8 = new MediaUploaderRequestError("MEDIA_URL_MISSING", 7);
            MEDIA_URL_MISSING = mediaUploaderRequestError8;
            MediaUploaderRequestError mediaUploaderRequestError9 = new MediaUploaderRequestError("MEDIA_FILE_DOES_NOT_EXIST", 8);
            MEDIA_FILE_DOES_NOT_EXIST = mediaUploaderRequestError9;
            MediaUploaderRequestError mediaUploaderRequestError10 = new MediaUploaderRequestError("FACEBOOK_MESSENGER_IMAGE_ID_MISSING", 9);
            FACEBOOK_MESSENGER_IMAGE_ID_MISSING = mediaUploaderRequestError10;
            MediaUploaderRequestError mediaUploaderRequestError11 = new MediaUploaderRequestError("FACEBOOK_SHARE_MISSING", 10);
            FACEBOOK_SHARE_MISSING = mediaUploaderRequestError11;
            MediaUploaderRequestError mediaUploaderRequestError12 = new MediaUploaderRequestError("FACEBOOK_SYNC_SHARE_ACCESS_TOKEN_MISSING", 11);
            FACEBOOK_SYNC_SHARE_ACCESS_TOKEN_MISSING = mediaUploaderRequestError12;
            MediaUploaderRequestError mediaUploaderRequestError13 = new MediaUploaderRequestError("FACEBOOK_SHARE_ACCESS_TOKEN_MISSING", 12);
            FACEBOOK_SHARE_ACCESS_TOKEN_MISSING = mediaUploaderRequestError13;
            MediaUploaderRequestError mediaUploaderRequestError14 = new MediaUploaderRequestError("FACEBOOK_SHARE_TYPE_MISSING", 13);
            FACEBOOK_SHARE_TYPE_MISSING = mediaUploaderRequestError14;
            MediaUploaderRequestError mediaUploaderRequestError15 = new MediaUploaderRequestError("FACEBOOK_SHARE_PRIVACY_MISSING", 14);
            FACEBOOK_SHARE_PRIVACY_MISSING = mediaUploaderRequestError15;
            MediaUploaderRequestError mediaUploaderRequestError16 = new MediaUploaderRequestError("FACEBOOK_SHARE_DESCRIPTION_MISSING", 15);
            FACEBOOK_SHARE_DESCRIPTION_MISSING = mediaUploaderRequestError16;
            MediaUploaderRequestError mediaUploaderRequestError17 = new MediaUploaderRequestError("FACEBOOK_SHARE_MEDIA_URL_MISSING", 16);
            FACEBOOK_SHARE_MEDIA_URL_MISSING = mediaUploaderRequestError17;
            MediaUploaderRequestError mediaUploaderRequestError18 = new MediaUploaderRequestError("FACEBOOK_SHARE_GROUP_ID_MISSING", 17);
            FACEBOOK_SHARE_GROUP_ID_MISSING = mediaUploaderRequestError18;
            MediaUploaderRequestError mediaUploaderRequestError19 = new MediaUploaderRequestError("MESSENGER_THREAD_ID_MISSING", 18);
            MESSENGER_THREAD_ID_MISSING = mediaUploaderRequestError19;
            MediaUploaderRequestError mediaUploaderRequestError20 = new MediaUploaderRequestError("UPLOAD_PHASE_MISSING", 19);
            UPLOAD_PHASE_MISSING = mediaUploaderRequestError20;
            MediaUploaderRequestError mediaUploaderRequestError21 = new MediaUploaderRequestError("UPLOAD_SESSION_ID_MISSING", 20);
            UPLOAD_SESSION_ID_MISSING = mediaUploaderRequestError21;
            MediaUploaderRequestError mediaUploaderRequestError22 = new MediaUploaderRequestError("CHUNK_UPLOAD_FAILED", 21);
            CHUNK_UPLOAD_FAILED = mediaUploaderRequestError22;
            MediaUploaderRequestError mediaUploaderRequestError23 = new MediaUploaderRequestError("CHUNK_READ_FAILED", 22);
            CHUNK_READ_FAILED = mediaUploaderRequestError23;
            MediaUploaderRequestError mediaUploaderRequestError24 = new MediaUploaderRequestError("START_POST_TO_FB_FAILED", 23);
            START_POST_TO_FB_FAILED = mediaUploaderRequestError24;
            MediaUploaderRequestError mediaUploaderRequestError25 = new MediaUploaderRequestError("FINISH_POST_TO_FB_FAILED", 24);
            FINISH_POST_TO_FB_FAILED = mediaUploaderRequestError25;
            MediaUploaderRequestError mediaUploaderRequestError26 = new MediaUploaderRequestError("FINISH_POST_TO_MESSENGER_FAILED", 25);
            FINISH_POST_TO_MESSENGER_FAILED = mediaUploaderRequestError26;
            MediaUploaderRequestError mediaUploaderRequestError27 = new MediaUploaderRequestError("POST_TO_FB_FAILED", 26);
            POST_TO_FB_FAILED = mediaUploaderRequestError27;
            MediaUploaderRequestError mediaUploaderRequestError28 = new MediaUploaderRequestError("POST_TO_MESSENGER_FAILED", 27);
            POST_TO_MESSENGER_FAILED = mediaUploaderRequestError28;
            MediaUploaderRequestError mediaUploaderRequestError29 = new MediaUploaderRequestError("VIDEO_ID_MISSING", 28);
            VIDEO_ID_MISSING = mediaUploaderRequestError29;
            MediaUploaderRequestError mediaUploaderRequestError30 = new MediaUploaderRequestError("POST_TO_OCULUS_FAILED", 29);
            POST_TO_OCULUS_FAILED = mediaUploaderRequestError30;
            MediaUploaderRequestError mediaUploaderRequestError31 = new MediaUploaderRequestError("CREATE_POST_TO_OCULUS_FAILED", 30);
            CREATE_POST_TO_OCULUS_FAILED = mediaUploaderRequestError31;
            MediaUploaderRequestError mediaUploaderRequestError32 = new MediaUploaderRequestError("START_POST_TO_OCULUS_FAILED", 31);
            START_POST_TO_OCULUS_FAILED = mediaUploaderRequestError32;
            MediaUploaderRequestError[] mediaUploaderRequestErrorArr = new MediaUploaderRequestError[32];
            System.arraycopy(new MediaUploaderRequestError[]{mediaUploaderRequestError, mediaUploaderRequestError2, mediaUploaderRequestError3, mediaUploaderRequestError4, mediaUploaderRequestError5, mediaUploaderRequestError6, mediaUploaderRequestError7, mediaUploaderRequestError8, mediaUploaderRequestError9, mediaUploaderRequestError10, mediaUploaderRequestError11, mediaUploaderRequestError12, mediaUploaderRequestError13, mediaUploaderRequestError14, mediaUploaderRequestError15, mediaUploaderRequestError16, mediaUploaderRequestError17, mediaUploaderRequestError18, mediaUploaderRequestError19, mediaUploaderRequestError20, mediaUploaderRequestError21, mediaUploaderRequestError22, mediaUploaderRequestError23, mediaUploaderRequestError24, mediaUploaderRequestError25, mediaUploaderRequestError26, mediaUploaderRequestError27}, 0, mediaUploaderRequestErrorArr, 0, 27);
            System.arraycopy(new MediaUploaderRequestError[]{mediaUploaderRequestError28, mediaUploaderRequestError29, mediaUploaderRequestError30, mediaUploaderRequestError31, mediaUploaderRequestError32}, 0, mediaUploaderRequestErrorArr, 27, 5);
            $VALUES = mediaUploaderRequestErrorArr;
        }

        public static MediaUploaderRequestError valueOf(String str) {
            return (MediaUploaderRequestError) Enum.valueOf(MediaUploaderRequestError.class, str);
        }

        public static MediaUploaderRequestError[] values() {
            return (MediaUploaderRequestError[]) $VALUES.clone();
        }

        public MediaUploaderRequestError(String str, int i) {
        }
    }

    public enum MediaUploaderResultType {
        CREATION_SUCCESS,
        UPLOAD_PROGRESS,
        UPLOAD_SUCCESS,
        UPLOAD_TO_FACEBOOK_SUCCESS,
        FAILURE
    }

    public int describeContents() {
        return 0;
    }

    public static MediaUploaderResult forCreationSuccess(String str) {
        return new MediaUploaderResult(MediaUploaderResultType.CREATION_SUCCESS, str, null, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
    }

    public static MediaUploaderResult forUploadProgress(String str, float f) {
        return new MediaUploaderResult(MediaUploaderResultType.UPLOAD_PROGRESS, str, null, f);
    }

    public static MediaUploaderResult forUploadSuccess(String str) {
        return new MediaUploaderResult(MediaUploaderResultType.UPLOAD_SUCCESS, str, null, 1.0f);
    }

    public static MediaUploaderResult forUploadToFacebookSuccess(String str) {
        return new MediaUploaderResult(MediaUploaderResultType.UPLOAD_TO_FACEBOOK_SUCCESS, str, null, 1.0f);
    }

    public String toString() {
        return String.format(Locale.US, "%s[%s, progress = %f, error = %s, objectId = %s]", "MediaUploaderResult", this.type.name(), Float.valueOf(this.progress), this.error, this.objectId);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type.name());
        parcel.writeString(this.objectId);
        parcel.writeString(this.error);
        parcel.writeFloat(this.progress);
    }

    public MediaUploaderResult(Parcel parcel) {
        this.type = MediaUploaderResultType.valueOf(parcel.readString());
        this.objectId = parcel.readString();
        this.error = parcel.readString();
        this.progress = parcel.readFloat();
    }

    public MediaUploaderResult(MediaUploaderResultType mediaUploaderResultType, @Nullable String str, @Nullable String str2, float f) {
        this.type = mediaUploaderResultType;
        this.objectId = str;
        this.error = str2;
        this.progress = f;
    }

    public static MediaUploaderResult forFailure(MediaUploaderRequestError mediaUploaderRequestError) {
        return new MediaUploaderResult(MediaUploaderResultType.FAILURE, null, mediaUploaderRequestError.name(), 1.0f);
    }

    public static MediaUploaderResult forFailure(String str) {
        return new MediaUploaderResult(MediaUploaderResultType.FAILURE, null, str, 1.0f);
    }
}
