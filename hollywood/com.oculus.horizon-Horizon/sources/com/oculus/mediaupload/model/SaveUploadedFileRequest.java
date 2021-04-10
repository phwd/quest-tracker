package com.oculus.mediaupload.model;

import com.oculus.mediaupload.io.MediaUploaderDB;
import java.util.Locale;

public class SaveUploadedFileRequest {
    public final MediaUploaderDB.Type type;
    public final String uploadedFileName;

    public final String toString() {
        return String.format(Locale.US, "%s[%s, uploadedFileName = %s]", "NotificationRequest", this.type.name(), this.uploadedFileName);
    }

    public SaveUploadedFileRequest(MediaUploaderDB.Type type2, String str) {
        this.type = type2;
        this.uploadedFileName = str;
    }
}
