package com.oculus.mediaupload.model;

import java.util.Locale;
import javax.annotation.Nullable;

public class NotificationRequest {
    public final int progress;
    public final Type type;

    public enum Type {
        FACEBOOK_VIDEO_UPLOAD_STARTED,
        FACEBOOK_VIDEO_UPLOAD_PROGRESS,
        FACEBOOK_VIDEO_UPLOAD_SUCCESS,
        FACEBOOK_VIDEO_UPLOAD_FAILURE,
        FACEBOOK_SCREENSHOT_UPLOAD_STARTED,
        FACEBOOK_SCREENSHOT_UPLOAD_SUCCESS,
        FACEBOOK_SCREENSHOT_UPLOAD_FAILURE,
        MESSENGER_VIDEO_UPLOAD_FAILURE,
        MESSENGER_SCREENSHOT_UPLOAD_FAILURE,
        OCULUS_VIDEO_UPLOAD_STARTED,
        OCULUS_VIDEO_UPLOAD_PROGRESS,
        OCULUS_VIDEO_UPLOAD_SUCCESS,
        OCULUS_VIDEO_UPLOAD_FAILURE,
        OCULUS_SCREENSHOT_UPLOAD_SUCCESS,
        OCULUS_SCREENSHOT_UPLOAD_FAILURE,
        REQUEST_FACEBOOK_GAMING_LOGIN
    }

    public final String toString() {
        return String.format(Locale.US, "%s[%s, progress = %s]", "NotificationRequest", this.type.name(), Integer.valueOf(this.progress));
    }

    public NotificationRequest(Type type2, @Nullable int i) {
        this.type = type2;
        this.progress = i;
    }
}
