package com.oculus.mediaupload.model;

import com.oculus.mediaupload.model.MediaUploaderResult;
import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderServiceManagerResult {
    @Nullable
    public final MediaUploaderServiceManagerRequest next;
    @Nullable
    public final NotificationRequest notification;
    public final MediaUploaderResult result;
    @Nullable
    public final SaveUploadedFileRequest uploadedFile;

    public static MediaUploaderServiceManagerResult A00(MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
        return new MediaUploaderServiceManagerResult(new MediaUploaderResult(MediaUploaderResult.MediaUploaderResultType.FAILURE, null, mediaUploaderRequestError.name(), 1.0f), null, null, null);
    }

    public static MediaUploaderServiceManagerResult A01(MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, NotificationRequest notificationRequest) {
        return new MediaUploaderServiceManagerResult(new MediaUploaderResult(MediaUploaderResult.MediaUploaderResultType.FAILURE, null, mediaUploaderRequestError.name(), 1.0f), null, null, notificationRequest);
    }

    public final String toString() {
        return String.format(Locale.US, "%s[result = %s, uploaded_file_name = %s, next = %s, notification = %s]", "MediaUploaderServiceManagerResult", this.result, this.uploadedFile, this.next, this.notification);
    }

    public MediaUploaderServiceManagerResult(MediaUploaderResult mediaUploaderResult, @Nullable SaveUploadedFileRequest saveUploadedFileRequest, @Nullable MediaUploaderServiceManagerRequest mediaUploaderServiceManagerRequest, @Nullable NotificationRequest notificationRequest) {
        this.result = mediaUploaderResult;
        this.uploadedFile = saveUploadedFileRequest;
        this.next = mediaUploaderServiceManagerRequest;
        this.notification = notificationRequest;
    }
}
