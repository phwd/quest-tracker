package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.mediaupload.model.MediaUploaderResult;
import javax.annotation.Nullable;

public class ScreenshotUploadFBFailedEvent extends UploadEvent {
    public final MediaUploaderResult.MediaUploaderRequestError mError;
    @Nullable
    public final FacebookShareRequest.FacebookShareType mShareType;

    public ScreenshotUploadFBFailedEvent(EventManager eventManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, @Nullable FacebookShareRequest.FacebookShareType facebookShareType) {
        super(eventManager);
        this.mError = mediaUploaderRequestError;
        this.mShareType = facebookShareType;
    }
}
