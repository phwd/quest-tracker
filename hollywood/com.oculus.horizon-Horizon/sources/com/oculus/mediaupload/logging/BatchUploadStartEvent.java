package com.oculus.mediaupload.logging;

import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.model.MediaUploaderIntentValidator;

public class BatchUploadStartEvent extends UploadEvent {
    public int mNumItems;
    public MediaUploaderIntentValidator.Type mType;

    public BatchUploadStartEvent(EventManager eventManager, MediaUploaderIntentValidator.Type type, int i) {
        super(eventManager);
        this.mType = type;
        this.mNumItems = i;
    }
}
