package android.stats;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;

public final class StatsEnums {
    private StatsEnums() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum EventType implements Internal.EnumLite {
        TYPE_UNKNOWN(0);
        
        public static final int TYPE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<EventType> internalValueMap = new Internal.EnumLiteMap<EventType>() {
            /* class android.stats.StatsEnums.EventType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventType findValueByNumber(int number) {
                return EventType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static EventType valueOf(int value2) {
            return forNumber(value2);
        }

        public static EventType forNumber(int value2) {
            if (value2 != 0) {
                return null;
            }
            return TYPE_UNKNOWN;
        }

        public static Internal.EnumLiteMap<EventType> internalGetValueMap() {
            return internalValueMap;
        }

        private EventType(int value2) {
            this.value = value2;
        }
    }
}
