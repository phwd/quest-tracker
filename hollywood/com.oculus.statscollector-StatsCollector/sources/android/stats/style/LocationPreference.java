package android.stats.style;

import com.google.protobuf.Internal;

public enum LocationPreference implements Internal.EnumLite {
    LOCATION_PREFERENCE_UNSPECIFIED(0),
    LOCATION_UNAVAILABLE(1),
    LOCATION_CURRENT(2),
    LOCATION_MANUAL(3);
    
    public static final int LOCATION_CURRENT_VALUE = 2;
    public static final int LOCATION_MANUAL_VALUE = 3;
    public static final int LOCATION_PREFERENCE_UNSPECIFIED_VALUE = 0;
    public static final int LOCATION_UNAVAILABLE_VALUE = 1;
    private static final Internal.EnumLiteMap<LocationPreference> internalValueMap = new Internal.EnumLiteMap<LocationPreference>() {
        /* class android.stats.style.LocationPreference.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LocationPreference findValueByNumber(int number) {
            return LocationPreference.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LocationPreference valueOf(int value2) {
        return forNumber(value2);
    }

    public static LocationPreference forNumber(int value2) {
        if (value2 == 0) {
            return LOCATION_PREFERENCE_UNSPECIFIED;
        }
        if (value2 == 1) {
            return LOCATION_UNAVAILABLE;
        }
        if (value2 == 2) {
            return LOCATION_CURRENT;
        }
        if (value2 != 3) {
            return null;
        }
        return LOCATION_MANUAL;
    }

    public static Internal.EnumLiteMap<LocationPreference> internalGetValueMap() {
        return internalValueMap;
    }

    private LocationPreference(int value2) {
        this.value = value2;
    }
}
