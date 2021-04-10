package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.MediaUploaderResult;
import javax.annotation.Nullable;

public class VideoUploadOculusFailedEvent extends UploadEvent {
    public MediaUploaderResult.MediaUploaderRequestError mError;
    @Nullable
    public Exception mException;
    public long mFileSize;

    public VideoUploadOculusFailedEvent(EventManager eventManager, long j, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, Exception exc) {
        super(eventManager);
        this.mFileSize = j;
        this.mError = mediaUploaderRequestError;
        this.mException = exc;
    }

    public VideoUploadOculusFailedEvent(EventManager eventManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
        super(eventManager);
        this.mFileSize = 0;
        this.mError = mediaUploaderRequestError;
        this.mException = null;
    }
}
