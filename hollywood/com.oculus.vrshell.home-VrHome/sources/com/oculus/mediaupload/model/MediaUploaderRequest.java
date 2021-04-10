package com.oculus.mediaupload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderRequest implements Parcelable {
    public static final Parcelable.Creator<MediaUploaderRequest> CREATOR = new Parcelable.Creator<MediaUploaderRequest>() {
        /* class com.oculus.mediaupload.model.MediaUploaderRequest.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaUploaderRequest createFromParcel(Parcel in) {
            return new MediaUploaderRequest(in);
        }

        @Override // android.os.Parcelable.Creator
        public MediaUploaderRequest[] newArray(int size) {
            return new MediaUploaderRequest[size];
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

    public static MediaUploaderRequest forUploadScreenshotToOculus(String screenshotName) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_OCULUS, screenshotName, null, null, null, null, null);
    }

    public static MediaUploaderRequest forUploadVideoshotToOculus(String videoshotName) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_OCULUS, videoshotName, null, null, null, null, null);
    }

    public static MediaUploaderRequest forUploadScreenshotToFacebook(String photoName, FacebookShareRequest facebookShareRequest2, String accessToken2, String description2) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_FACEBOOK, photoName, facebookShareRequest2, null, accessToken2, description2, null);
    }

    public static MediaUploaderRequest forUploadVideoshotToFacebook(String videoshotName, FacebookShareRequest facebookShareRequest2, String accessToken2, String description2) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK, videoshotName, facebookShareRequest2, null, accessToken2, description2, null);
    }

    public static MediaUploaderRequest forUploadScreenshotToFacebook(String photoName, FacebookShareRequest facebookShareRequest2, String accessToken2, String description2, String uploadFailedErrorMessage2) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_FACEBOOK, photoName, facebookShareRequest2, null, accessToken2, description2, uploadFailedErrorMessage2);
    }

    public static MediaUploaderRequest forUploadVideoshotToFacebook(String videoshotName, FacebookShareRequest facebookShareRequest2, String accessToken2, String description2, String uploadFailedErrorMessage2) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK, videoshotName, facebookShareRequest2, null, accessToken2, description2, uploadFailedErrorMessage2);
    }

    public static MediaUploaderRequest forShareScreenshotToFacebook(String accessToken2, String description2, FacebookShareRequest facebookShareRequest2, String mediaUrl2) {
        return new MediaUploaderRequest(Type.SHARE_SCREENSHOT_TO_FACEBOOK, null, facebookShareRequest2, mediaUrl2, accessToken2, description2, null);
    }

    public static MediaUploaderRequest forShareVideoshotToFacebook(String accessToken2, String description2, FacebookShareRequest facebookShareRequest2, String mediaUrl2) {
        return new MediaUploaderRequest(Type.SHARE_VIDEOSHOT_TO_FACEBOOK, null, facebookShareRequest2, mediaUrl2, accessToken2, description2, null);
    }

    public static MediaUploaderRequest forSyncVideoshotToFacebook(String videoshotName) {
        return new MediaUploaderRequest(Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK, videoshotName, FacebookShareRequest.forSync(), null, null, null, null);
    }

    public static MediaUploaderRequest forSyncScreenshotToFacebook(String screenshotName) {
        return new MediaUploaderRequest(Type.UPLOAD_SCREENSHOT_TO_FACEBOOK, screenshotName, FacebookShareRequest.forSync(), null, null, null, null);
    }

    public static MediaUploaderRequest forMediaUploaderRequestWithAccessToken(MediaUploaderRequest request, String accessToken2) {
        return new MediaUploaderRequest(request.type, request.mediaName, request.facebookShareRequest, request.mediaUrl, accessToken2, request.description, request.uploadFailedErrorMessage);
    }

    private MediaUploaderRequest(Type type2, @Nullable String mediaName2, @Nullable FacebookShareRequest facebookShareRequest2, @Nullable String mediaUrl2, @Nullable String accessToken2, @Nullable String description2, @Nullable String uploadFailedErrorMessage2) {
        this.type = type2;
        this.mediaName = mediaName2;
        this.facebookShareRequest = facebookShareRequest2;
        this.mediaUrl = mediaUrl2;
        this.accessToken = accessToken2;
        this.description = description2;
        this.uploadFailedErrorMessage = uploadFailedErrorMessage2;
    }

    public MediaUploaderRequest(Parcel in) {
        this.type = Type.valueOf(in.readString());
        this.mediaName = in.readString();
        this.facebookShareRequest = (FacebookShareRequest) in.readParcelable(FacebookShareRequest.class.getClassLoader());
        this.mediaUrl = in.readString();
        this.accessToken = in.readString();
        this.description = in.readString();
        this.uploadFailedErrorMessage = in.readString();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.type.name());
        out.writeString(this.mediaName);
        out.writeParcelable(this.facebookShareRequest, 0);
        out.writeString(this.mediaUrl);
        out.writeString(this.accessToken);
        out.writeString(this.description);
        out.writeString(this.uploadFailedErrorMessage);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        int length;
        int i = 0;
        Locale locale = Locale.US;
        Object[] objArr = new Object[7];
        objArr[0] = MediaUploaderRequest.class.getSimpleName();
        objArr[1] = this.type.name();
        objArr[2] = this.mediaName;
        objArr[3] = this.facebookShareRequest != null ? this.facebookShareRequest : null;
        objArr[4] = this.mediaUrl;
        if (this.description == null) {
            length = 0;
        } else {
            length = this.description.length();
        }
        objArr[5] = Integer.valueOf(length);
        if (this.uploadFailedErrorMessage != null) {
            i = this.uploadFailedErrorMessage.length();
        }
        objArr[6] = Integer.valueOf(i);
        return String.format(locale, "%s[%s, mediaName = %s, shareRequest = %s, mediaUrl = %s, description = %d chars, uploadFailedErrorMessage = %d chars]", objArr);
    }
}
