package com.facebook.quicklog.identifiers;

public class MediaUploadPipeline {
    public static final short MODULE_ID = 354;
    public static final int VIDEO_TRANSCODING = 23199745;
    public static final int VIDEO_UPLOADING = 23199746;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "MEDIA_UPLOAD_PIPELINE_VIDEO_UPLOADING" : "MEDIA_UPLOAD_PIPELINE_VIDEO_TRANSCODING";
    }
}
