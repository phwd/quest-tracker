package android.stats.intelligence;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;

public final class IntelligenceStatsEnums {
    private IntelligenceStatsEnums() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum Status implements Internal.EnumLite {
        STATUS_UNKNOWN(0),
        STATUS_SUCCEEDED(1),
        STATUS_FAILED(2);
        
        public static final int STATUS_FAILED_VALUE = 2;
        public static final int STATUS_SUCCEEDED_VALUE = 1;
        public static final int STATUS_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() {
            /* class android.stats.intelligence.IntelligenceStatsEnums.Status.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Status findValueByNumber(int number) {
                return Status.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Status valueOf(int value2) {
            return forNumber(value2);
        }

        public static Status forNumber(int value2) {
            if (value2 == 0) {
                return STATUS_UNKNOWN;
            }
            if (value2 == 1) {
                return STATUS_SUCCEEDED;
            }
            if (value2 != 2) {
                return null;
            }
            return STATUS_FAILED;
        }

        public static Internal.EnumLiteMap<Status> internalGetValueMap() {
            return internalValueMap;
        }

        private Status(int value2) {
            this.value = value2;
        }
    }

    public enum EventType implements Internal.EnumLite {
        EVENT_UNKNOWN(0),
        EVENT_CONTENT_SUGGESTIONS_CLASSIFY_CONTENT_CALL(1),
        EVENT_CONTENT_SUGGESTIONS_SUGGEST_CONTENT_CALL(2);
        
        public static final int EVENT_CONTENT_SUGGESTIONS_CLASSIFY_CONTENT_CALL_VALUE = 1;
        public static final int EVENT_CONTENT_SUGGESTIONS_SUGGEST_CONTENT_CALL_VALUE = 2;
        public static final int EVENT_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<EventType> internalValueMap = new Internal.EnumLiteMap<EventType>() {
            /* class android.stats.intelligence.IntelligenceStatsEnums.EventType.AnonymousClass1 */

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
            if (value2 == 0) {
                return EVENT_UNKNOWN;
            }
            if (value2 == 1) {
                return EVENT_CONTENT_SUGGESTIONS_CLASSIFY_CONTENT_CALL;
            }
            if (value2 != 2) {
                return null;
            }
            return EVENT_CONTENT_SUGGESTIONS_SUGGEST_CONTENT_CALL;
        }

        public static Internal.EnumLiteMap<EventType> internalGetValueMap() {
            return internalValueMap;
        }

        private EventType(int value2) {
            this.value = value2;
        }
    }
}
