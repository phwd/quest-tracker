package android.bluetooth.a2dp;

import com.google.protobuf.Internal;

public enum PlaybackStateEnum implements Internal.EnumLite {
    PLAYBACK_STATE_UNKNOWN(0),
    PLAYBACK_STATE_PLAYING(10),
    PLAYBACK_STATE_NOT_PLAYING(11);
    
    public static final int PLAYBACK_STATE_NOT_PLAYING_VALUE = 11;
    public static final int PLAYBACK_STATE_PLAYING_VALUE = 10;
    public static final int PLAYBACK_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<PlaybackStateEnum> internalValueMap = new Internal.EnumLiteMap<PlaybackStateEnum>() {
        /* class android.bluetooth.a2dp.PlaybackStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public PlaybackStateEnum findValueByNumber(int number) {
            return PlaybackStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static PlaybackStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static PlaybackStateEnum forNumber(int value2) {
        if (value2 == 0) {
            return PLAYBACK_STATE_UNKNOWN;
        }
        if (value2 == 10) {
            return PLAYBACK_STATE_PLAYING;
        }
        if (value2 != 11) {
            return null;
        }
        return PLAYBACK_STATE_NOT_PLAYING;
    }

    public static Internal.EnumLiteMap<PlaybackStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private PlaybackStateEnum(int value2) {
        this.value = value2;
    }
}
