package X;

import com.facebook.messenger.assistant.thrift.AssistantAction;
import java.util.HashMap;
import java.util.Map;

public enum FO {
    UNKNOWN_ACTION_TYPE(-1),
    DISPLAY_RESPONSE_ACTION(2),
    CALL_FRIEND_RESPONSE_ACTION(4),
    CAMERA_CONTROL_RESPONSE_ACTION(5),
    NATIVE_TEMPLATE_RESPONSE_ACTION(9),
    GENERIC_ERROR_RESPONSE_ACTION(10),
    MEDIA_CONTROL_RESPONSE_ACTION(11),
    MEDIA_STATION_PLAY_RESPONSE_ACTION(12),
    VOLUME_CONTROL_RESPONSE_ACTION(13),
    ALARM_CONTROL_RESPONSE_ACTION(16),
    DEVICE_CONTROL_RESPONSE_ACTION(17),
    APP_CONTROL_RESPONSE_ACTION(18),
    INCOMING_CALL_RESPONSE_ACTION(19),
    EFFECT_CONTROL_RESPONSE_ACTION(25),
    BRIGHTNESS_CONTROL_RESPONSE_ACTION(24),
    SEND_MESSAGE_RESPONSE_ACTION(38),
    VIDEO_MESSAGE_RESPONSE_ACTION(41),
    TIMER_CONTROL_V2_RESPONSE_ACTION(26),
    APPLICATION_VOICE_COMMAND_RESPONSE_ACTION(28),
    SHOW_PHOTOS_RESPONSE_ACTION(29),
    MULTI_RESPONSE_ACTION(32),
    IN_CALL_VIDEO_CONTROL_RESPONSE_ACTION(30),
    EXTERNAL_DISPLAY_CONTROL_RESPONSE_ACTION(31),
    DISPLAY_TOAST_MSG_RESPONSE_ACTION(39),
    PLAY_SPOTIFY_LANGUAGE_RESPONSE_ACTION(42),
    FORCE_SPOTIFY_PREVIOUS_TRACK_RESPONSE_ACTION(43),
    END_CONVERSATION_RESPONSE_ACTION(34),
    SHOW_AVAILABLE_FRIENDS_RESPONSE_ACTION(36),
    DEVICE_SLEEP_RESPONSE_ACTION(35),
    GO_HOME_RESPONSE_ACTION(44),
    DEFAULT_MUSIC_PROVIDER_CONTROL_RESPONSE_ACTION(48),
    CALL_USER_RESPONSE_ACTION(47),
    REMINDER_CONTROL_RESPONSE_ACTION(46),
    ALARM_CONTROL_V2_RESPONSE_ACTION(49);
    
    public static Map sAllValuesMap = new HashMap();
    public int actionType;

    /* access modifiers changed from: public */
    static {
        FO[] values = values();
        for (FO fo : values) {
            CT.A00(!sAllValuesMap.containsKey(Integer.valueOf(fo.get())));
            sAllValuesMap.put(Integer.valueOf(fo.get()), fo);
        }
    }

    public static FO fromAssistantAction(AssistantAction assistantAction) {
        try {
            if (sAllValuesMap.containsKey(Integer.valueOf(assistantAction.A03()))) {
                return (FO) sAllValuesMap.get(Integer.valueOf(assistantAction.A03()));
            }
        } catch (IllegalStateException unused) {
        }
        return UNKNOWN_ACTION_TYPE;
    }

    /* access modifiers changed from: public */
    FO(int i) {
        this.actionType = i;
    }

    public int get() {
        return this.actionType;
    }
}
