package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import javax.annotation.Nullable;

public class ScreenshotUploadOculusSuccessEvent extends UploadEvent {
    @Nullable
    public String mAppId = null;
    public long mFileSize;

    public ScreenshotUploadOculusSuccessEvent(EventManager eventManager, long j) {
        super(eventManager);
        this.mFileSize = j;
    }
}
