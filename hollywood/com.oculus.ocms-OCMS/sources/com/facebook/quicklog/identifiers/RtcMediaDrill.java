package com.facebook.quicklog.identifiers;

public class RtcMediaDrill {
    public static final int H264_CODEC_DRILL = 12386309;
    public static final int MEDIA_DRILL = 12386305;
    public static final short MODULE_ID = 189;
    public static final int OPUS_CODEC_DRILL = 12386307;
    public static final int RESAMPLING_DRILL = 12386306;
    public static final int VP8_CODEC_DRILL = 12386308;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "RTC_MEDIA_DRILL_H264_CODEC_DRILL" : "RTC_MEDIA_DRILL_VP8_CODEC_DRILL" : "RTC_MEDIA_DRILL_OPUS_CODEC_DRILL" : "RTC_MEDIA_DRILL_RESAMPLING_DRILL" : "RTC_MEDIA_DRILL_MEDIA_DRILL";
    }
}
