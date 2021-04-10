package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.FacebookShareRequest;

public class ScreenshotUploadFBStartedEvent extends UploadEvent {
    public boolean mCapturedWithShortcut;
    public long mFileSize;
    public FacebookShareRequest.FacebookShareType mShareType;

    public ScreenshotUploadFBStartedEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, boolean z) {
        super(eventManager);
        this.mFileSize = j;
        this.mShareType = facebookShareType;
        this.mCapturedWithShortcut = z;
    }
}
