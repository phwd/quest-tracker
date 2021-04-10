package X;

import com.facebook.messenger.assistant.thrift.CuResponseHeader;
import java.util.HashMap;
import java.util.Map;

public enum FR {
    UNKNOWN(0),
    AVAILABILITY_SET(1),
    CALL_FRIEND(2),
    ADD_TO_CALL(3),
    SMART_CAMERA_CONTROL(4),
    END_CONVERSATION(5),
    DEVICE_SLEEP(6),
    GENERIC_ERROR(7),
    MEDIA_CONTROL(8),
    MEDIA_SIGNIN(9),
    OPT_IN_OUT(10),
    PANDORA_PLAY_MEDIA(11),
    SPOTIFY_PLAY_MEDIA(12),
    VOLUME_SET(14),
    WEATHER_FORECAST(15),
    SHOW_FRIENDS_AVAILABILITY(16),
    GO_HOME(19),
    SHOW_MESSAGE(20),
    END_DIALOG(21),
    ALARM_CONTROL(24),
    SHOW_ASSISTANT_CAPABILITY(25),
    PLAY_MEDIA(27),
    TIMER_CONTROL(28),
    INCOMING_CALL(29),
    TTS_STREAMING(26),
    APP_CONTROL(23),
    CORE_NAVIGATION(31),
    DEBUG_SETTING_SET(33),
    BRIGHTNESS_CONTROL(34),
    EFFECT_CONTROL(35),
    TIMER_CONTROL_V2(36),
    DO_NOT_DISTURB_CONTROL(37),
    SHOW_PHOTOS(40),
    IN_CALL_VIDEO_CONTROL(41),
    EXTERNAL_DISPLAY_CONTROL(42),
    SEND_MESSAGE(45),
    VIDEO_MESSAGE_CONTROL(48),
    DISPLAY_TOAST_MSG(46),
    SPOTIFY_PREVIOUS_TRACK(49),
    APPLICATION_VOICE_COMMAND(38),
    DEFAULT_MUSIC_PROVIDER_CONTROL(50);
    
    public static Map sAllValuesMap = new HashMap();
    public int responseType;

    /* access modifiers changed from: public */
    static {
        FR[] values = values();
        for (FR fr : values) {
            CT.A00(!sAllValuesMap.containsKey(Integer.valueOf(fr.get())));
            sAllValuesMap.put(Integer.valueOf(fr.get()), fr);
        }
    }

    public static FR fromCuResponseHeader(CuResponseHeader cuResponseHeader) {
        if (sAllValuesMap.containsKey(cuResponseHeader.A00(1))) {
            return (FR) sAllValuesMap.get(cuResponseHeader.A00(1));
        }
        return UNKNOWN;
    }

    /* access modifiers changed from: public */
    FR(int i) {
        this.responseType = i;
    }

    public int get() {
        return this.responseType;
    }
}
