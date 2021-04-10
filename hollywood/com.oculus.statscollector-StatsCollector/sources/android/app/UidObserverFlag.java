package android.app;

import com.google.protobuf.Internal;

public enum UidObserverFlag implements Internal.EnumLite {
    UID_OBSERVER_FLAG_PROCSTATE(1),
    UID_OBSERVER_FLAG_GONE(2),
    UID_OBSERVER_FLAG_IDLE(3),
    UID_OBSERVER_FLAG_ACTIVE(4),
    UID_OBSERVER_FLAG_CACHED(5);
    
    public static final int UID_OBSERVER_FLAG_ACTIVE_VALUE = 4;
    public static final int UID_OBSERVER_FLAG_CACHED_VALUE = 5;
    public static final int UID_OBSERVER_FLAG_GONE_VALUE = 2;
    public static final int UID_OBSERVER_FLAG_IDLE_VALUE = 3;
    public static final int UID_OBSERVER_FLAG_PROCSTATE_VALUE = 1;
    private static final Internal.EnumLiteMap<UidObserverFlag> internalValueMap = new Internal.EnumLiteMap<UidObserverFlag>() {
        /* class android.app.UidObserverFlag.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public UidObserverFlag findValueByNumber(int number) {
            return UidObserverFlag.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static UidObserverFlag valueOf(int value2) {
        return forNumber(value2);
    }

    public static UidObserverFlag forNumber(int value2) {
        if (value2 == 1) {
            return UID_OBSERVER_FLAG_PROCSTATE;
        }
        if (value2 == 2) {
            return UID_OBSERVER_FLAG_GONE;
        }
        if (value2 == 3) {
            return UID_OBSERVER_FLAG_IDLE;
        }
        if (value2 == 4) {
            return UID_OBSERVER_FLAG_ACTIVE;
        }
        if (value2 != 5) {
            return null;
        }
        return UID_OBSERVER_FLAG_CACHED;
    }

    public static Internal.EnumLiteMap<UidObserverFlag> internalGetValueMap() {
        return internalValueMap;
    }

    private UidObserverFlag(int value2) {
        this.value = value2;
    }
}
