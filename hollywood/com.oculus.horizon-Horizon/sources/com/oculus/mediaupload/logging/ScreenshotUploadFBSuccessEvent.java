package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.FacebookShareRequest;
import javax.annotation.Nullable;

public class ScreenshotUploadFBSuccessEvent extends UploadEvent {
    public boolean mCapturedWithShortcut;
    public long mFileSize;
    @Nullable
    public String mGameId;
    public FacebookShareRequest.FacebookShareType mShareType;

    public ScreenshotUploadFBSuccessEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, String str, boolean z) {
        super(eventManager);
        this.mFileSize = j;
        this.mShareType = facebookShareType;
        this.mGameId = str;
        this.mCapturedWithShortcut = z;
    }

    public ScreenshotUploadFBSuccessEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, boolean z) {
        super(eventManager);
        this.mFileSize = j;
        this.mShareType = facebookShareType;
        this.mCapturedWithShortcut = z;
    }
}
