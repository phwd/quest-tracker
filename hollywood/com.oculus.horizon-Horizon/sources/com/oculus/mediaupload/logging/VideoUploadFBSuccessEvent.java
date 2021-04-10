package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.FacebookShareRequest;
import javax.annotation.Nullable;

public class VideoUploadFBSuccessEvent extends UploadEvent {
    public FacebookShareRequest.FacebookShareType mFacebookShareType;
    public long mFileSize;
    @Nullable
    public String mGameId;
    public boolean mIsInstantReplay;

    public VideoUploadFBSuccessEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, String str, boolean z) {
        super(eventManager);
        this.mFileSize = j;
        this.mFacebookShareType = facebookShareType;
        this.mGameId = str;
        this.mIsInstantReplay = z;
    }

    public VideoUploadFBSuccessEvent(EventManager eventManager, long j, FacebookShareRequest.FacebookShareType facebookShareType, boolean z) {
        super(eventManager);
        this.mFileSize = j;
        this.mFacebookShareType = facebookShareType;
        this.mGameId = null;
        this.mIsInstantReplay = z;
    }
}
