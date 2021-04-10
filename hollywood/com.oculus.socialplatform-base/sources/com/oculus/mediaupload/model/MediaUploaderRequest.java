package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderRequest implements Parcelable {
    public static final Parcelable.Creator<MediaUploaderRequest> CREATOR = new Parcelable.Creator<MediaUploaderRequest>() {
        /* class com.oculus.mediaupload.model.MediaUploaderRequest.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaUploaderRequest createFromParcel(Parcel parcel) {
            return new MediaUploaderRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MediaUploaderRequest[] newArray(int i) {
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

    public int describeContents() {
        return 0;
    }

    public static MediaUploaderRequest forMediaUploaderRequestWithAccessToken(MediaUploaderRequest mediaUploaderRequest, String str) {
        return new MediaUploaderRequest(mediaUploaderRequest.type, mediaUploaderRequest.mediaName, mediaUploaderRequest.facebookShareRequest, mediaUploaderRequest.mediaUrl, str, mediaUploaderRequest.description, mediaUploaderRequest.uploadFailedErrorMessage);
    }

    public static MediaUploaderRequest forShareScreenshotToFacebook(String str, String str2, FacebookShareRequest facebookShareRequest2, String str3) {
        return new MediaUploaderRequest(Type.SHARE_SCREENSHOT_TO_FACEBOOK, null, facebookShareRequest2, str3, str, str2, null);
    }

    public static MediaUploaderRequest forShareVideoshotToFacebook(String str, String str2, FacebookShareRequest facebookShareRequest2, String str3) {
        return new MediaUploaderRequest(Type.SHARE_VIDEOSHOT_TO_FACEBOOK, null, facebookShareRequest2, str3, str, str2, null);
    }

    public static MediaUploaderRequest forSyncScreenshotToFacebook(String str) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_FACEBOOK, str, FacebookShareRequest.forSync(), null, null, null, null);
    }

    public static MediaUploaderRequest forSyncVideoshotToFacebook(String str) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK, str, FacebookShareRequest.forSync(), null, null, null, null);
    }

    public static MediaUploaderRequest forUploadScreenshotToOculus(String str) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_OCULUS, str, null, null, null, null, null);
    }

    public static MediaUploaderRequest forUploadVideoshotToOculus(String str) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_OCULUS, str, null, null, null, null, null);
    }

    public String toString() {
        int length;
        Locale locale = Locale.US;
        int i = 0;
        String name = this.type.name();
        String str = this.mediaName;
        FacebookShareRequest facebookShareRequest2 = this.facebookShareRequest;
        if (facebookShareRequest2 == null) {
            facebookShareRequest2 = null;
        }
        String str2 = this.mediaUrl;
        String str3 = this.description;
        if (str3 == null) {
            length = 0;
        } else {
            length = str3.length();
        }
        Integer valueOf = Integer.valueOf(length);
        String str4 = this.uploadFailedErrorMessage;
        if (str4 != null) {
            i = str4.length();
        }
        return String.format(locale, "%s[%s, mediaName = %s, shareRequest = %s, mediaUrl = %s, description = %d chars, uploadFailedErrorMessage = %d chars]", "MediaUploaderRequest", name, str, facebookShareRequest2, str2, valueOf, Integer.valueOf(i));
    }

    public void writeToParcel(Parcel parcel, int i) {
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

    public MediaUploaderRequest(Type type2, @Nullable String str, @Nullable FacebookShareRequest facebookShareRequest2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.type = type2;
        this.mediaName = str;
        this.facebookShareRequest = facebookShareRequest2;
        this.mediaUrl = str2;
        this.accessToken = str3;
        this.description = str4;
        this.uploadFailedErrorMessage = str5;
    }

    public static MediaUploaderRequest forUploadScreenshotToFacebook(String str, FacebookShareRequest facebookShareRequest2, String str2, String str3) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_FACEBOOK, str, facebookShareRequest2, null, str2, str3, null);
    }

    public static MediaUploaderRequest forUploadScreenshotToFacebook(String str, FacebookShareRequest facebookShareRequest2, String str2, String str3, String str4) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_FACEBOOK, str, facebookShareRequest2, null, str2, str3, str4);
    }

    public static MediaUploaderRequest forUploadVideoshotToFacebook(String str, FacebookShareRequest facebookShareRequest2, String str2, String str3) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK, str, facebookShareRequest2, null, str2, str3, null);
    }

    public static MediaUploaderRequest forUploadVideoshotToFacebook(String str, FacebookShareRequest facebookShareRequest2, String str2, String str3, String str4) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK, str, facebookShareRequest2, null, str2, str3, str4);
    }
}
