package android.stats.storage;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;

public final class StorageEnums {
    private StorageEnums() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum ExternalStorageType implements Internal.EnumLite {
        UNKNOWN(0),
        SD_CARD(1),
        USB(2),
        OTHER(3);
        
        public static final int OTHER_VALUE = 3;
        public static final int SD_CARD_VALUE = 1;
        public static final int UNKNOWN_VALUE = 0;
        public static final int USB_VALUE = 2;
        private static final Internal.EnumLiteMap<ExternalStorageType> internalValueMap = new Internal.EnumLiteMap<ExternalStorageType>() {
            /* class android.stats.storage.StorageEnums.ExternalStorageType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ExternalStorageType findValueByNumber(int number) {
                return ExternalStorageType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ExternalStorageType valueOf(int value2) {
            return forNumber(value2);
        }

        public static ExternalStorageType forNumber(int value2) {
            if (value2 == 0) {
                return UNKNOWN;
            }
            if (value2 == 1) {
                return SD_CARD;
            }
            if (value2 == 2) {
                return USB;
            }
            if (value2 != 3) {
                return null;
            }
            return OTHER;
        }

        public static Internal.EnumLiteMap<ExternalStorageType> internalGetValueMap() {
            return internalValueMap;
        }

        private ExternalStorageType(int value2) {
            this.value = value2;
        }
    }
}
