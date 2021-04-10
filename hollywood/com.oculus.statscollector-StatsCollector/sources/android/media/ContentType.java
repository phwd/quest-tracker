package android.media;

import com.google.protobuf.Internal;

public enum ContentType implements Internal.EnumLite {
    CONTENT_TYPE_UNKNOWN(0),
    SPEECH(1),
    MUSIC(2),
    MOVIE(3),
    SONIFICATION(4);
    
    public static final int CONTENT_TYPE_UNKNOWN_VALUE = 0;
    public static final int MOVIE_VALUE = 3;
    public static final int MUSIC_VALUE = 2;
    public static final int SONIFICATION_VALUE = 4;
    public static final int SPEECH_VALUE = 1;
    private static final Internal.EnumLiteMap<ContentType> internalValueMap = new Internal.EnumLiteMap<ContentType>() {
        /* class android.media.ContentType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ContentType findValueByNumber(int number) {
            return ContentType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ContentType valueOf(int value2) {
        return forNumber(value2);
    }

    public static ContentType forNumber(int value2) {
        if (value2 == 0) {
            return CONTENT_TYPE_UNKNOWN;
        }
        if (value2 == 1) {
            return SPEECH;
        }
        if (value2 == 2) {
            return MUSIC;
        }
        if (value2 == 3) {
            return MOVIE;
        }
        if (value2 != 4) {
            return null;
        }
        return SONIFICATION;
    }

    public static Internal.EnumLiteMap<ContentType> internalGetValueMap() {
        return internalValueMap;
    }

    private ContentType(int value2) {
        this.value = value2;
    }
}
