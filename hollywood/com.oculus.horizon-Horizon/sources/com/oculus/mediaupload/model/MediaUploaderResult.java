package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderResult implements Parcelable {
    public static final Parcelable.Creator<MediaUploaderResult> CREATOR = new Parcelable.Creator<MediaUploaderResult>() {
        /* class com.oculus.mediaupload.model.MediaUploaderResult.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final MediaUploaderResult createFromParcel(Parcel parcel) {
            return new MediaUploaderResult(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final MediaUploaderResult[] newArray(int i) {
            return new MediaUploaderResult[i];
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
        MEDIA_ID_MISSING,
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

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format(Locale.US, "%s[%s, progress = %f, error = %s, objectId = %s]", "MediaUploaderResult", this.type.name(), Float.valueOf(this.progress), this.error, this.objectId);
    }

    public final void writeToParcel(Parcel parcel, int i) {
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
}
