package android.stats.docsui;

import com.google.protobuf.Internal;

public enum MimeType implements Internal.EnumLite {
    MIME_UNKNOWN(0),
    MIME_NONE(1),
    MIME_ANY(2),
    MIME_APPLICATION(3),
    MIME_AUDIO(4),
    MIME_IMAGE(5),
    MIME_MESSAGE(6),
    MIME_MULTIPART(7),
    MIME_TEXT(8),
    MIME_VIDEO(9),
    MIME_OTHER(10);
    
    public static final int MIME_ANY_VALUE = 2;
    public static final int MIME_APPLICATION_VALUE = 3;
    public static final int MIME_AUDIO_VALUE = 4;
    public static final int MIME_IMAGE_VALUE = 5;
    public static final int MIME_MESSAGE_VALUE = 6;
    public static final int MIME_MULTIPART_VALUE = 7;
    public static final int MIME_NONE_VALUE = 1;
    public static final int MIME_OTHER_VALUE = 10;
    public static final int MIME_TEXT_VALUE = 8;
    public static final int MIME_UNKNOWN_VALUE = 0;
    public static final int MIME_VIDEO_VALUE = 9;
    private static final Internal.EnumLiteMap<MimeType> internalValueMap = new Internal.EnumLiteMap<MimeType>() {
        /* class android.stats.docsui.MimeType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public MimeType findValueByNumber(int number) {
            return MimeType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static MimeType valueOf(int value2) {
        return forNumber(value2);
    }

    public static MimeType forNumber(int value2) {
        switch (value2) {
            case 0:
                return MIME_UNKNOWN;
            case 1:
                return MIME_NONE;
            case 2:
                return MIME_ANY;
            case 3:
                return MIME_APPLICATION;
            case 4:
                return MIME_AUDIO;
            case 5:
                return MIME_IMAGE;
            case 6:
                return MIME_MESSAGE;
            case 7:
                return MIME_MULTIPART;
            case 8:
                return MIME_TEXT;
            case 9:
                return MIME_VIDEO;
            case 10:
                return MIME_OTHER;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<MimeType> internalGetValueMap() {
        return internalValueMap;
    }

    private MimeType(int value2) {
        this.value = value2;
    }
}
