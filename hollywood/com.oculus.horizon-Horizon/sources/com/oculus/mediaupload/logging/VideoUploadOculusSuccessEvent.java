package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import javax.annotation.Nullable;

public class VideoUploadOculusSuccessEvent extends UploadEvent {
    @Nullable
    public String mAppId = null;
    public long mFileSize;

    public VideoUploadOculusSuccessEvent(EventManager eventManager, long j) {
        super(eventManager);
        this.mFileSize = j;
    }
}
