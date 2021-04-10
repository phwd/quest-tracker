package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderRequest implements Parcelable {
    public static final Parcelable.Creator<MediaUploaderRequest> CREATOR = new Parcelable.Creator<MediaUploaderRequest>() {
        /* class com.oculus.mediaupload.model.MediaUploaderRequest.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final MediaUploaderRequest createFromParcel(Parcel parcel) {
            return new MediaUploaderRequest(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final MediaUploaderRequest[] newArray(int i) {
            return new MediaUploaderRequest[i];
        }
    };
    @Nullable
    public final String accessToken;
    @Nullable
    public final String description;
    @Nullable
    public final FacebookShareRequest facebookShareRequest;
    @Nullable
    public final String mediaName;
    @Nullable
    public final String mediaUrl;
    public final Type type;
    @Nullable
    public final String uploadFailedErrorMessage;

    public enum Type {
        UPLOAD_SCREENSHOT_TO_OCULUS,
        UPLOAD_VIDEOSHOT_TO_OCULUS,
        UPLOAD_SCREENSHOT_TO_FACEBOOK,
        UPLOAD_VIDEOSHOT_TO_FACEBOOK,
        SHARE_SCREENSHOT_TO_FACEBOOK,
        SHARE_VIDEOSHOT_TO_FACEBOOK
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        int length;
        Locale locale = Locale.US;
        Object[] objArr = new Object[7];
        int i = 0;
        objArr[0] = "MediaUploaderRequest";
        objArr[1] = this.type.name();
        objArr[2] = this.mediaName;
        FacebookShareRequest facebookShareRequest2 = this.facebookShareRequest;
        if (facebookShareRequest2 == null) {
            facebookShareRequest2 = null;
        }
        objArr[3] = facebookShareRequest2;
        objArr[4] = this.mediaUrl;
        String str = this.description;
        if (str == null) {
            length = 0;
        } else {
            length = str.length();
        }
        objArr[5] = Integer.valueOf(length);
        String str2 = this.uploadFailedErrorMessage;
        if (str2 != null) {
            i = str2.length();
        }
        objArr[6] = Integer.valueOf(i);
        return String.format(locale, "%s[%s, mediaName = %s, shareRequest = %s, mediaUrl = %s, description = %d chars, uploadFailedErrorMessage = %d chars]", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type.name());
        parcel.writeString(this.mediaName);
        parcel.writeParcelable(this.facebookShareRequest, 0);
        parcel.writeString(this.mediaUrl);
        parcel.writeString(this.accessToken);
        parcel.writeString(this.description);
        parcel.writeString(this.uploadFailedErrorMessage);
    }

    public MediaUploaderRequest(Parcel parcel) {
        this.type = Type.valueOf(parcel.readString());
        this.mediaName = parcel.readString();
        this.facebookShareRequest = (FacebookShareRequest) parcel.readParcelable(FacebookShareRequest.class.getClassLoader());
        this.mediaUrl = parcel.readString();
        this.accessToken = parcel.readString();
        this.description = parcel.readString();
        this.uploadFailedErrorMessage = parcel.readString();
    }

    public MediaUploaderRequest(Type type2, @Nullable String str, @Nullable FacebookShareRequest facebookShareRequest2) {
        this.type = type2;
        this.mediaName = str;
        this.facebookShareRequest = facebookShareRequest2;
        this.mediaUrl = null;
        this.accessToken = null;
        this.description = null;
        this.uploadFailedErrorMessage = null;
    }
}
