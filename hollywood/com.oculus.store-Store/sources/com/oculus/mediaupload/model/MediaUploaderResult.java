package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderResult implements Parcelable {
    public static final Parcelable.Creator<MediaUploaderResult> CREATOR = new Parcelable.Creator<MediaUploaderResult>() {
        /* class com.oculus.mediaupload.model.MediaUploaderResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaUploaderResult createFromParcel(Parcel in) {
            return new MediaUploaderResult(in);
        }

        @Override // android.os.Parcelable.Creator
        public MediaUploaderResult[] newArray(int size) {
            return new MediaUploaderResult[size];
        }
    };
    @Nullable
    public final String error;
    @Nullable
    public final String objectId;
    public final float progress;
    public final MediaUploaderResultType type;

    public enum MediaUploaderRequestError {
        UNKNOWN_TYPE,
        UNKNOWN_SHARE_TYPE,
        UNAUTHORIZED_REQUEST,
        MEDIA_UPLOADER_REQUEST_MISSING,
        MEDIA_UPLOADER_RESULT_RECEIVER_MISSING,
        TYPE_MISSING,
        MEDIA_NAME_MISSING,
        MEDIA_URL_MISSING,
        MEDIA_FILE_DOES_NOT_EXIST,
        FACEBOOK_MESSENGER_IMAGE_ID_MISSING,
        FACEBOOK_SHARE_MISSING,
        FACEBOOK_SYNC_SHARE_ACCESS_TOKEN_MISSING,
        FACEBOOK_SHARE_ACCESS_TOKEN_MISSING,
        FACEBOOK_SHARE_TYPE_MISSING,
        FACEBOOK_SHARE_PRIVACY_MISSING,
        FACEBOOK_SHARE_DESCRIPTION_MISSING,
        FACEBOOK_SHARE_MEDIA_URL_MISSING,
        FACEBOOK_SHARE_GROUP_ID_MISSING,
        MESSENGER_THREAD_ID_MISSING,
        UPLOAD_PHASE_MISSING,
        UPLOAD_SESSION_ID_MISSING,
        CHUNK_UPLOAD_FAILED,
        CHUNK_READ_FAILED,
        START_POST_TO_FB_FAILED,
        FINISH_POST_TO_FB_FAILED,
        FINISH_POST_TO_MESSENGER_FAILED,
        POST_TO_FB_FAILED,
        POST_TO_MESSENGER_FAILED,
        VIDEO_ID_MISSING,
        POST_TO_OCULUS_FAILED,
        CREATE_POST_TO_OCULUS_FAILED,
        START_POST_TO_OCULUS_FAILED
    }

    public enum MediaUploaderResultType {
        CREATION_SUCCESS,
        UPLOAD_PROGRESS,
        UPLOAD_SUCCESS,
        UPLOAD_TO_FACEBOOK_SUCCESS,
        FAILURE
    }

    public static MediaUploaderResult forCreationSuccess(String objectId2) {
        return new MediaUploaderResult(MediaUploaderResultType.CREATION_SUCCESS, objectId2, null, 0.0f);
    }

    public static MediaUploaderResult forUploadProgress(String objectId2, float progress2) {
        return new MediaUploaderResult(MediaUploaderResultType.UPLOAD_PROGRESS, objectId2, null, progress2);
    }

    public static MediaUploaderResult forUploadSuccess(String objectId2) {
        return new MediaUploaderResult(MediaUploaderResultType.UPLOAD_SUCCESS, objectId2, null, 1.0f);
    }

    public static MediaUploaderResult forUploadToFacebookSuccess(String objectId2) {
        return new MediaUploaderResult(MediaUploaderResultType.UPLOAD_TO_FACEBOOK_SUCCESS, objectId2, null, 1.0f);
    }

    public static MediaUploaderResult forFailure(String error2) {
        return new MediaUploaderResult(MediaUploaderResultType.FAILURE, null, error2, 1.0f);
    }

    public static MediaUploaderResult forFailure(MediaUploaderRequestError error2) {
        return new MediaUploaderResult(MediaUploaderResultType.FAILURE, null, error2.name(), 1.0f);
    }

    private MediaUploaderResult(MediaUploaderResultType type2, @Nullable String objectId2, @Nullable String error2, float progress2) {
        this.type = type2;
        this.objectId = objectId2;
        this.error = error2;
        this.progress = progress2;
    }

    public MediaUploaderResult(Parcel in) {
        this.type = MediaUploaderResultType.valueOf(in.readString());
        this.objectId = in.readString();
        this.error = in.readString();
        this.progress = in.readFloat();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.type.name());
        out.writeString(this.objectId);
        out.writeString(this.error);
        out.writeFloat(this.progress);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "%s[%s, progress = %f, error = %s, objectId = %s]", MediaUploaderResult.class.getSimpleName(), this.type.name(), Float.valueOf(this.progress), this.error, this.objectId);
    }
}
