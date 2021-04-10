package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.mediaupload.model.MediaUploaderResult;
import javax.annotation.Nullable;

public class VideoUploadFBFailedEvent extends UploadEvent {
    public MediaUploaderResult.MediaUploaderRequestError mError;
    @Nullable
    public Exception mException;
    @Nullable
    public FacebookShareRequest.FacebookShareType mFacebookShareType;
    public long mFileSize;

    public VideoUploadFBFailedEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
        super(eventManager);
        this.mFileSize = j;
        this.mFacebookShareType = facebookShareType;
        this.mError = mediaUploaderRequestError;
        this.mException = null;
    }

    public VideoUploadFBFailedEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, Exception exc) {
        super(eventManager);
        this.mFileSize = j;
        this.mFacebookShareType = facebookShareType;
        this.mError = mediaUploaderRequestError;
        this.mException = exc;
    }

    public VideoUploadFBFailedEvent(EventManager eventManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
        super(eventManager);
        this.mFileSize = 0;
        this.mFacebookShareType = null;
        this.mError = mediaUploaderRequestError;
        this.mException = null;
    }
}
