package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.FacebookShareRequest;

public class VideoUploadFBStartedEvent extends UploadEvent {
    public FacebookShareRequest.FacebookShareType mFacebookShareType;
    public long mFileSize;

    public VideoUploadFBStartedEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType) {
        super(eventManager);
        this.mFileSize = j;
        this.mFacebookShareType = facebookShareType;
    }
}
