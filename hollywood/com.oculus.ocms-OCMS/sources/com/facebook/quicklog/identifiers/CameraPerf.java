package com.facebook.quicklog.identifiers;

public class CameraPerf {
    public static final int AUDIOPIPELINE_INIT = 11272236;
    public static final int AUTO_FACE_FOCUS_TRACKER_FACE_DETECTION = 11272231;
    public static final int AUTO_FACE_FOCUS_TRACKER_WARMUP = 11272230;
    public static final int CAMERA_EFFECT_FETCH_CACHE = 11272215;
    public static final int CAMERA_EFFECT_FETCH_NETWORK = 11272216;
    public static final int CANCEL_RECORDING_VIDEO = 11272193;
    public static final int CAPTURE_IMAGE = 11272194;
    public static final int CAPTURE_PHOTO = 11272208;
    public static final int CAPTURE_PHOTO_NATIVE = 11272228;
    public static final int CAPTURE_PHOTO_OS = 11272214;
    public static final int CAPTURE_PHOTO_PREVIEW = 11272227;
    public static final int CAPTURE_POST_PHOTO = 11272224;
    public static final int EFFECT_TIME_TO_INTERACT = 11272226;
    public static final int FIRST_HARDWARE_FRAME_TO_FIRST_USER_FRAME = 11272223;
    public static final int HW_START_CAMERA_SESSION = 11272217;
    public static final int HW_TIME_TO_FIRST_FRAME_AFTER_CAMERA_OPEN = 11272219;
    public static final short MODULE_ID = 172;
    public static final int NATIVE_AUDIO_PIPELINE_INIT = 11285187;
    public static final int NATIVE_AUDIO_POST_PROCESSOR_INIT = 11286624;
    public static final int NATIVE_CREATE_AUDIO_GRAPH = 11284795;
    public static final int NATIVE_RTC_AUDIO_PIPELINE_INIT = 11281828;
    public static final int PREPARE_CAMERA_SESSION = 11272221;
    public static final int PROCESSING_INPUT_FRAME_TIME_AVG = 11272225;
    public static final int RECORDING = 11272235;
    public static final int SAVE_PHOTO = 11272212;
    public static final int START_AUDIO_AND_CAMERA_SESSION = 11272205;
    public static final int START_AUDIO_SESSION = 11272195;
    public static final int START_CAMERA_SESSION = 11272196;
    public static final int START_CAMERA_SESSION_TO_FIRST_HARDWARE_FRAME = 11272222;
    public static final int START_MEDIA_PIPELINE_SESSION = 11272210;
    public static final int START_RECORDING_VIDEO = 11272197;
    public static final int STOP_AUDIO_AND_CAMERA_SESSION = 11272206;
    public static final int STOP_AUDIO_SESSION = 11272203;
    public static final int STOP_CAMERA_SESSION = 11272204;
    public static final int STOP_MEDIA_PIPELINE_SESSION = 11272211;
    public static final int STOP_RECORDING_VIDEO = 11272198;
    public static final int TIME_TO_FIRST_FRAME = 11272207;
    public static final int TIME_TO_FIRST_FRAME_AFTER_CAMERA_OPEN = 11272213;
    public static final int UPDATE_CAPTURE_DEVICE_POSITION = 11272199;
    public static final int UPDATE_EXPOSURE_POINT = 11272200;
    public static final int UPDATE_TORCH_MODE = 11272202;

    public static String getMarkerName(int i) {
        if (i == 27) {
            return "CAMERA_PERF_HW_TIME_TO_FIRST_FRAME_AFTER_CAMERA_OPEN";
        }
        if (i == 9636) {
            return "CAMERA_PERF_NATIVE_RTC_AUDIO_PIPELINE_INIT";
        }
        if (i == 12603) {
            return "CAMERA_PERF_NATIVE_CREATE_AUDIO_GRAPH";
        }
        if (i == 12995) {
            return "CAMERA_PERF_NATIVE_AUDIO_PIPELINE_INIT";
        }
        if (i == 14432) {
            return "CAMERA_PERF_NATIVE_AUDIO_POST_PROCESSOR_INIT";
        }
        if (i == 38) {
            return "CAMERA_PERF_AUTO_FACE_FOCUS_TRACKER_WARMUP";
        }
        if (i == 39) {
            return "CAMERA_PERF_AUTO_FACE_FOCUS_TRACKER_FACE_DETECTION";
        }
        if (i == 43) {
            return "CAMERA_PERF_RECORDING";
        }
        if (i == 44) {
            return "CAMERA_PERF_AUDIOPIPELINE_INIT";
        }
        switch (i) {
            case 1:
                return "CAMERA_PERF_CANCEL_RECORDING_VIDEO";
            case 2:
                return "CAMERA_PERF_CAPTURE_IMAGE";
            case 3:
                return "CAMERA_PERF_START_AUDIO_SESSION";
            case 4:
                return "CAMERA_PERF_START_CAMERA_SESSION";
            case 5:
                return "CAMERA_PERF_START_RECORDING_VIDEO";
            case 6:
                return "CAMERA_PERF_STOP_RECORDING_VIDEO";
            case 7:
                return "CAMERA_PERF_UPDATE_CAPTURE_DEVICE_POSITION";
            case 8:
                return "CAMERA_PERF_UPDATE_EXPOSURE_POINT";
            default:
                switch (i) {
                    case 10:
                        return "CAMERA_PERF_UPDATE_TORCH_MODE";
                    case 11:
                        return "CAMERA_PERF_STOP_AUDIO_SESSION";
                    case 12:
                        return "CAMERA_PERF_STOP_CAMERA_SESSION";
                    case 13:
                        return "CAMERA_PERF_START_AUDIO_AND_CAMERA_SESSION";
                    case 14:
                        return "CAMERA_PERF_STOP_AUDIO_AND_CAMERA_SESSION";
                    case 15:
                        return "CAMERA_PERF_TIME_TO_FIRST_FRAME";
                    case 16:
                        return "CAMERA_PERF_CAPTURE_PHOTO";
                    default:
                        switch (i) {
                            case 18:
                                return "CAMERA_PERF_START_MEDIA_PIPELINE_SESSION";
                            case 19:
                                return "CAMERA_PERF_STOP_MEDIA_PIPELINE_SESSION";
                            case 20:
                                return "CAMERA_PERF_SAVE_PHOTO";
                            case 21:
                                return "CAMERA_PERF_TIME_TO_FIRST_FRAME_AFTER_CAMERA_OPEN";
                            case 22:
                                return "CAMERA_PERF_CAPTURE_PHOTO_OS";
                            case 23:
                                return "CAMERA_PERF_CAMERA_EFFECT_FETCH_CACHE";
                            case 24:
                                return "CAMERA_PERF_CAMERA_EFFECT_FETCH_NETWORK";
                            case 25:
                                return "CAMERA_PERF_HW_START_CAMERA_SESSION";
                            default:
                                switch (i) {
                                    case 29:
                                        return "CAMERA_PERF_PREPARE_CAMERA_SESSION";
                                    case 30:
                                        return "CAMERA_PERF_START_CAMERA_SESSION_TO_FIRST_HARDWARE_FRAME";
                                    case 31:
                                        return "CAMERA_PERF_FIRST_HARDWARE_FRAME_TO_FIRST_USER_FRAME";
                                    case 32:
                                        return "CAMERA_PERF_CAPTURE_POST_PHOTO";
                                    case 33:
                                        return "CAMERA_PERF_PROCESSING_INPUT_FRAME_TIME_AVG";
                                    case 34:
                                        return "CAMERA_PERF_EFFECT_TIME_TO_INTERACT";
                                    case 35:
                                        return "CAMERA_PERF_CAPTURE_PHOTO_PREVIEW";
                                    case 36:
                                        return "CAMERA_PERF_CAPTURE_PHOTO_NATIVE";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
