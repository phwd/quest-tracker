package com.facebook.quicklog.identifiers;

public class AssistantWearable {
    public static final int APP_START = 152181506;
    public static final int COLD_START = 152182764;
    public static final int COMMS_SPEECH_RECOGNITION = 152185158;
    public static final int DICTATION = 152181621;
    public static final int DICTATION_AUDIO_FROM_WEARABLE = 152190351;
    public static final int DICTATION_AUDIO_TO_COMPANION = 152190460;
    public static final int MESSENGER_REPLY = 152187262;
    public static final short MODULE_ID = 2322;
    public static final int SPEECH_TO_TEXT = 152178372;
    public static final int TTS = 152189941;
    public static final int TTS_CLIENT_INIT = 152183673;
    public static final int TTS_CLIENT_SPEAK = 152179358;
    public static final int TTS_SERVICE_SYNTHESIZE_TEXT = 152183782;

    public static String getMarkerName(int i) {
        switch (i) {
            case 3780:
                return "ASSISTANT_WEARABLE_SPEECH_TO_TEXT";
            case 4766:
                return "ASSISTANT_WEARABLE_TTS_CLIENT_SPEAK";
            case 6914:
                return "ASSISTANT_WEARABLE_APP_START";
            case 7029:
                return "ASSISTANT_WEARABLE_DICTATION";
            case 8172:
                return "ASSISTANT_WEARABLE_COLD_START";
            case 9081:
                return "ASSISTANT_WEARABLE_TTS_CLIENT_INIT";
            case 9190:
                return "ASSISTANT_WEARABLE_TTS_SERVICE_SYNTHESIZE_TEXT";
            case 10566:
                return "ASSISTANT_WEARABLE_COMMS_SPEECH_RECOGNITION";
            case 12670:
                return "ASSISTANT_WEARABLE_MESSENGER_REPLY";
            case 15349:
                return "ASSISTANT_WEARABLE_TTS";
            case 15759:
                return "ASSISTANT_WEARABLE_DICTATION_AUDIO_FROM_WEARABLE";
            case 15868:
                return "ASSISTANT_WEARABLE_DICTATION_AUDIO_TO_COMPANION";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
