package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;

public class VideoUploadOculusStartedEvent extends UploadEvent {
    public long mFileSize;

    public VideoUploadOculusStartedEvent(EventManager eventManager, long j) {
        super(eventManager);
        this.mFileSize = j;
    }
}
