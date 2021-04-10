package android.stats.docsui;

import com.google.protobuf.Internal;

public enum Root implements Internal.EnumLite {
    ROOT_UNKNOWN(0),
    ROOT_NONE(1),
    ROOT_OTHER_DOCS_PROVIDER(2),
    ROOT_AUDIO(3),
    ROOT_DEVICE_STORAGE(4),
    ROOT_DOWNLOADS(5),
    ROOT_HOME(6),
    ROOT_IMAGES(7),
    ROOT_RECENTS(8),
    ROOT_VIDEOS(9),
    ROOT_MTP(10),
    ROOT_THIRD_PARTY_APP(11);
    
    public static final int ROOT_AUDIO_VALUE = 3;
    public static final int ROOT_DEVICE_STORAGE_VALUE = 4;
    public static final int ROOT_DOWNLOADS_VALUE = 5;
    public static final int ROOT_HOME_VALUE = 6;
    public static final int ROOT_IMAGES_VALUE = 7;
    public static final int ROOT_MTP_VALUE = 10;
    public static final int ROOT_NONE_VALUE = 1;
    public static final int ROOT_OTHER_DOCS_PROVIDER_VALUE = 2;
    public static final int ROOT_RECENTS_VALUE = 8;
    public static final int ROOT_THIRD_PARTY_APP_VALUE = 11;
    public static final int ROOT_UNKNOWN_VALUE = 0;
    public static final int ROOT_VIDEOS_VALUE = 9;
    private static final Internal.EnumLiteMap<Root> internalValueMap = new Internal.EnumLiteMap<Root>() {
        /* class android.stats.docsui.Root.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public Root findValueByNumber(int number) {
            return Root.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Root valueOf(int value2) {
        return forNumber(value2);
    }

    public static Root forNumber(int value2) {
        switch (value2) {
            case 0:
                return ROOT_UNKNOWN;
            case 1:
                return ROOT_NONE;
            case 2:
                return ROOT_OTHER_DOCS_PROVIDER;
            case 3:
                return ROOT_AUDIO;
            case 4:
                return ROOT_DEVICE_STORAGE;
            case 5:
                return ROOT_DOWNLOADS;
            case 6:
                return ROOT_HOME;
            case 7:
                return ROOT_IMAGES;
            case 8:
                return ROOT_RECENTS;
            case 9:
                return ROOT_VIDEOS;
            case 10:
                return ROOT_MTP;
            case 11:
                return ROOT_THIRD_PARTY_APP;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<Root> internalGetValueMap() {
        return internalValueMap;
    }

    private Root(int value2) {
        this.value = value2;
    }
}
