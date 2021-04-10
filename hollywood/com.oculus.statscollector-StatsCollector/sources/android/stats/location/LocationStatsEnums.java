package android.stats.location;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;

public final class LocationStatsEnums {
    private LocationStatsEnums() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum LocationManagerServiceApi implements Internal.EnumLite {
        API_UNKNOWN(0),
        API_REQUEST_LOCATION_UPDATES(1),
        API_ADD_GNSS_MEASUREMENTS_LISTENER(2),
        API_REGISTER_GNSS_STATUS_CALLBACK(3),
        API_REQUEST_GEOFENCE(4),
        API_SEND_EXTRA_COMMAND(5);
        
        public static final int API_ADD_GNSS_MEASUREMENTS_LISTENER_VALUE = 2;
        public static final int API_REGISTER_GNSS_STATUS_CALLBACK_VALUE = 3;
        public static final int API_REQUEST_GEOFENCE_VALUE = 4;
        public static final int API_REQUEST_LOCATION_UPDATES_VALUE = 1;
        public static final int API_SEND_EXTRA_COMMAND_VALUE = 5;
        public static final int API_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<LocationManagerServiceApi> internalValueMap = new Internal.EnumLiteMap<LocationManagerServiceApi>() {
            /* class android.stats.location.LocationStatsEnums.LocationManagerServiceApi.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LocationManagerServiceApi findValueByNumber(int number) {
                return LocationManagerServiceApi.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LocationManagerServiceApi valueOf(int value2) {
            return forNumber(value2);
        }

        public static LocationManagerServiceApi forNumber(int value2) {
            if (value2 == 0) {
                return API_UNKNOWN;
            }
            if (value2 == 1) {
                return API_REQUEST_LOCATION_UPDATES;
            }
            if (value2 == 2) {
                return API_ADD_GNSS_MEASUREMENTS_LISTENER;
            }
            if (value2 == 3) {
                return API_REGISTER_GNSS_STATUS_CALLBACK;
            }
            if (value2 == 4) {
                return API_REQUEST_GEOFENCE;
            }
            if (value2 != 5) {
                return null;
            }
            return API_SEND_EXTRA_COMMAND;
        }

        public static Internal.EnumLiteMap<LocationManagerServiceApi> internalGetValueMap() {
            return internalValueMap;
        }

        private LocationManagerServiceApi(int value2) {
            this.value = value2;
        }
    }

    public enum UsageState implements Internal.EnumLite {
        USAGE_STARTED(0),
        USAGE_ENDED(1);
        
        public static final int USAGE_ENDED_VALUE = 1;
        public static final int USAGE_STARTED_VALUE = 0;
        private static final Internal.EnumLiteMap<UsageState> internalValueMap = new Internal.EnumLiteMap<UsageState>() {
            /* class android.stats.location.LocationStatsEnums.UsageState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UsageState findValueByNumber(int number) {
                return UsageState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UsageState valueOf(int value2) {
            return forNumber(value2);
        }

        public static UsageState forNumber(int value2) {
            if (value2 == 0) {
                return USAGE_STARTED;
            }
            if (value2 != 1) {
                return null;
            }
            return USAGE_ENDED;
        }

        public static Internal.EnumLiteMap<UsageState> internalGetValueMap() {
            return internalValueMap;
        }

        private UsageState(int value2) {
            this.value = value2;
        }
    }

    public enum ProviderType implements Internal.EnumLite {
        PROVIDER_UNKNOWN(0),
        PROVIDER_NETWORK(1),
        PROVIDER_GPS(2),
        PROVIDER_PASSIVE(3),
        PROVIDER_FUSED(4);
        
        public static final int PROVIDER_FUSED_VALUE = 4;
        public static final int PROVIDER_GPS_VALUE = 2;
        public static final int PROVIDER_NETWORK_VALUE = 1;
        public static final int PROVIDER_PASSIVE_VALUE = 3;
        public static final int PROVIDER_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<ProviderType> internalValueMap = new Internal.EnumLiteMap<ProviderType>() {
            /* class android.stats.location.LocationStatsEnums.ProviderType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ProviderType findValueByNumber(int number) {
                return ProviderType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ProviderType valueOf(int value2) {
            return forNumber(value2);
        }

        public static ProviderType forNumber(int value2) {
            if (value2 == 0) {
                return PROVIDER_UNKNOWN;
            }
            if (value2 == 1) {
                return PROVIDER_NETWORK;
            }
            if (value2 == 2) {
                return PROVIDER_GPS;
            }
            if (value2 == 3) {
                return PROVIDER_PASSIVE;
            }
            if (value2 != 4) {
                return null;
            }
            return PROVIDER_FUSED;
        }

        public static Internal.EnumLiteMap<ProviderType> internalGetValueMap() {
            return internalValueMap;
        }

        private ProviderType(int value2) {
            this.value = value2;
        }
    }

    public enum CallbackType implements Internal.EnumLite {
        CALLBACK_UNKNOWN(0),
        CALLBACK_NOT_APPLICABLE(1),
        CALLBACK_LISTENER(2),
        CALLBACK_PENDING_INTENT(3);
        
        public static final int CALLBACK_LISTENER_VALUE = 2;
        public static final int CALLBACK_NOT_APPLICABLE_VALUE = 1;
        public static final int CALLBACK_PENDING_INTENT_VALUE = 3;
        public static final int CALLBACK_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<CallbackType> internalValueMap = new Internal.EnumLiteMap<CallbackType>() {
            /* class android.stats.location.LocationStatsEnums.CallbackType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CallbackType findValueByNumber(int number) {
                return CallbackType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CallbackType valueOf(int value2) {
            return forNumber(value2);
        }

        public static CallbackType forNumber(int value2) {
            if (value2 == 0) {
                return CALLBACK_UNKNOWN;
            }
            if (value2 == 1) {
                return CALLBACK_NOT_APPLICABLE;
            }
            if (value2 == 2) {
                return CALLBACK_LISTENER;
            }
            if (value2 != 3) {
                return null;
            }
            return CALLBACK_PENDING_INTENT;
        }

        public static Internal.EnumLiteMap<CallbackType> internalGetValueMap() {
            return internalValueMap;
        }

        private CallbackType(int value2) {
            this.value = value2;
        }
    }

    public enum LocationRequestQuality implements Internal.EnumLite {
        QUALITY_UNKNOWN(0),
        ACCURACY_FINE(100),
        ACCURACY_BLOCK(102),
        ACCURACY_CITY(104),
        POWER_NONE(200),
        POWER_LOW(201),
        POWER_HIGH(203);
        
        public static final int ACCURACY_BLOCK_VALUE = 102;
        public static final int ACCURACY_CITY_VALUE = 104;
        public static final int ACCURACY_FINE_VALUE = 100;
        public static final int POWER_HIGH_VALUE = 203;
        public static final int POWER_LOW_VALUE = 201;
        public static final int POWER_NONE_VALUE = 200;
        public static final int QUALITY_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<LocationRequestQuality> internalValueMap = new Internal.EnumLiteMap<LocationRequestQuality>() {
            /* class android.stats.location.LocationStatsEnums.LocationRequestQuality.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LocationRequestQuality findValueByNumber(int number) {
                return LocationRequestQuality.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LocationRequestQuality valueOf(int value2) {
            return forNumber(value2);
        }

        public static LocationRequestQuality forNumber(int value2) {
            if (value2 == 0) {
                return QUALITY_UNKNOWN;
            }
            if (value2 == 100) {
                return ACCURACY_FINE;
            }
            if (value2 == 102) {
                return ACCURACY_BLOCK;
            }
            if (value2 == 104) {
                return ACCURACY_CITY;
            }
            if (value2 == 203) {
                return POWER_HIGH;
            }
            if (value2 == 200) {
                return POWER_NONE;
            }
            if (value2 != 201) {
                return null;
            }
            return POWER_LOW;
        }

        public static Internal.EnumLiteMap<LocationRequestQuality> internalGetValueMap() {
            return internalValueMap;
        }

        private LocationRequestQuality(int value2) {
            this.value = value2;
        }
    }

    public enum LocationRequestIntervalBucket implements Internal.EnumLite {
        INTERVAL_UNKNOWN(0),
        INTERVAL_BETWEEN_0_SEC_AND_1_SEC(1),
        INTERVAL_BETWEEN_1_SEC_AND_5_SEC(2),
        INTERVAL_BETWEEN_5_SEC_AND_1_MIN(3),
        INTERVAL_BETWEEN_1_MIN_AND_10_MIN(4),
        INTERVAL_BETWEEN_10_MIN_AND_1_HOUR(5),
        INTERVAL_LARGER_THAN_1_HOUR(6);
        
        public static final int INTERVAL_BETWEEN_0_SEC_AND_1_SEC_VALUE = 1;
        public static final int INTERVAL_BETWEEN_10_MIN_AND_1_HOUR_VALUE = 5;
        public static final int INTERVAL_BETWEEN_1_MIN_AND_10_MIN_VALUE = 4;
        public static final int INTERVAL_BETWEEN_1_SEC_AND_5_SEC_VALUE = 2;
        public static final int INTERVAL_BETWEEN_5_SEC_AND_1_MIN_VALUE = 3;
        public static final int INTERVAL_LARGER_THAN_1_HOUR_VALUE = 6;
        public static final int INTERVAL_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<LocationRequestIntervalBucket> internalValueMap = new Internal.EnumLiteMap<LocationRequestIntervalBucket>() {
            /* class android.stats.location.LocationStatsEnums.LocationRequestIntervalBucket.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LocationRequestIntervalBucket findValueByNumber(int number) {
                return LocationRequestIntervalBucket.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static LocationRequestIntervalBucket valueOf(int value2) {
            return forNumber(value2);
        }

        public static LocationRequestIntervalBucket forNumber(int value2) {
            switch (value2) {
                case 0:
                    return INTERVAL_UNKNOWN;
                case 1:
                    return INTERVAL_BETWEEN_0_SEC_AND_1_SEC;
                case 2:
                    return INTERVAL_BETWEEN_1_SEC_AND_5_SEC;
                case 3:
                    return INTERVAL_BETWEEN_5_SEC_AND_1_MIN;
                case 4:
                    return INTERVAL_BETWEEN_1_MIN_AND_10_MIN;
                case 5:
                    return INTERVAL_BETWEEN_10_MIN_AND_1_HOUR;
                case 6:
                    return INTERVAL_LARGER_THAN_1_HOUR;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<LocationRequestIntervalBucket> internalGetValueMap() {
            return internalValueMap;
        }

        private LocationRequestIntervalBucket(int value2) {
            this.value = value2;
        }
    }

    public enum SmallestDisplacementBucket implements Internal.EnumLite {
        DISTANCE_UNKNOWN(0),
        DISTANCE_ZERO(1),
        DISTANCE_BETWEEN_0_AND_100(2),
        DISTANCE_LARGER_THAN_100(3);
        
        public static final int DISTANCE_BETWEEN_0_AND_100_VALUE = 2;
        public static final int DISTANCE_LARGER_THAN_100_VALUE = 3;
        public static final int DISTANCE_UNKNOWN_VALUE = 0;
        public static final int DISTANCE_ZERO_VALUE = 1;
        private static final Internal.EnumLiteMap<SmallestDisplacementBucket> internalValueMap = new Internal.EnumLiteMap<SmallestDisplacementBucket>() {
            /* class android.stats.location.LocationStatsEnums.SmallestDisplacementBucket.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SmallestDisplacementBucket findValueByNumber(int number) {
                return SmallestDisplacementBucket.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SmallestDisplacementBucket valueOf(int value2) {
            return forNumber(value2);
        }

        public static SmallestDisplacementBucket forNumber(int value2) {
            if (value2 == 0) {
                return DISTANCE_UNKNOWN;
            }
            if (value2 == 1) {
                return DISTANCE_ZERO;
            }
            if (value2 == 2) {
                return DISTANCE_BETWEEN_0_AND_100;
            }
            if (value2 != 3) {
                return null;
            }
            return DISTANCE_LARGER_THAN_100;
        }

        public static Internal.EnumLiteMap<SmallestDisplacementBucket> internalGetValueMap() {
            return internalValueMap;
        }

        private SmallestDisplacementBucket(int value2) {
            this.value = value2;
        }
    }

    public enum ExpirationBucket implements Internal.EnumLite {
        EXPIRATION_UNKNOWN(0),
        EXPIRATION_BETWEEN_0_AND_20_SEC(1),
        EXPIRATION_BETWEEN_20_SEC_AND_1_MIN(2),
        EXPIRATION_BETWEEN_1_MIN_AND_10_MIN(3),
        EXPIRATION_BETWEEN_10_MIN_AND_1_HOUR(4),
        EXPIRATION_LARGER_THAN_1_HOUR(5),
        EXPIRATION_NO_EXPIRY(6);
        
        public static final int EXPIRATION_BETWEEN_0_AND_20_SEC_VALUE = 1;
        public static final int EXPIRATION_BETWEEN_10_MIN_AND_1_HOUR_VALUE = 4;
        public static final int EXPIRATION_BETWEEN_1_MIN_AND_10_MIN_VALUE = 3;
        public static final int EXPIRATION_BETWEEN_20_SEC_AND_1_MIN_VALUE = 2;
        public static final int EXPIRATION_LARGER_THAN_1_HOUR_VALUE = 5;
        public static final int EXPIRATION_NO_EXPIRY_VALUE = 6;
        public static final int EXPIRATION_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<ExpirationBucket> internalValueMap = new Internal.EnumLiteMap<ExpirationBucket>() {
            /* class android.stats.location.LocationStatsEnums.ExpirationBucket.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ExpirationBucket findValueByNumber(int number) {
                return ExpirationBucket.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ExpirationBucket valueOf(int value2) {
            return forNumber(value2);
        }

        public static ExpirationBucket forNumber(int value2) {
            switch (value2) {
                case 0:
                    return EXPIRATION_UNKNOWN;
                case 1:
                    return EXPIRATION_BETWEEN_0_AND_20_SEC;
                case 2:
                    return EXPIRATION_BETWEEN_20_SEC_AND_1_MIN;
                case 3:
                    return EXPIRATION_BETWEEN_1_MIN_AND_10_MIN;
                case 4:
                    return EXPIRATION_BETWEEN_10_MIN_AND_1_HOUR;
                case 5:
                    return EXPIRATION_LARGER_THAN_1_HOUR;
                case 6:
                    return EXPIRATION_NO_EXPIRY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ExpirationBucket> internalGetValueMap() {
            return internalValueMap;
        }

        private ExpirationBucket(int value2) {
            this.value = value2;
        }
    }

    public enum GeofenceRadiusBucket implements Internal.EnumLite {
        RADIUS_UNKNOWN(0),
        RADIUS_BETWEEN_0_AND_100(1),
        RADIUS_BETWEEN_100_AND_200(2),
        RADIUS_BETWEEN_200_AND_300(3),
        RADIUS_BETWEEN_300_AND_1000(4),
        RADIUS_BETWEEN_1000_AND_10000(5),
        RADIUS_LARGER_THAN_100000(6),
        RADIUS_NEGATIVE(7);
        
        public static final int RADIUS_BETWEEN_0_AND_100_VALUE = 1;
        public static final int RADIUS_BETWEEN_1000_AND_10000_VALUE = 5;
        public static final int RADIUS_BETWEEN_100_AND_200_VALUE = 2;
        public static final int RADIUS_BETWEEN_200_AND_300_VALUE = 3;
        public static final int RADIUS_BETWEEN_300_AND_1000_VALUE = 4;
        public static final int RADIUS_LARGER_THAN_100000_VALUE = 6;
        public static final int RADIUS_NEGATIVE_VALUE = 7;
        public static final int RADIUS_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<GeofenceRadiusBucket> internalValueMap = new Internal.EnumLiteMap<GeofenceRadiusBucket>() {
            /* class android.stats.location.LocationStatsEnums.GeofenceRadiusBucket.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public GeofenceRadiusBucket findValueByNumber(int number) {
                return GeofenceRadiusBucket.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static GeofenceRadiusBucket valueOf(int value2) {
            return forNumber(value2);
        }

        public static GeofenceRadiusBucket forNumber(int value2) {
            switch (value2) {
                case 0:
                    return RADIUS_UNKNOWN;
                case 1:
                    return RADIUS_BETWEEN_0_AND_100;
                case 2:
                    return RADIUS_BETWEEN_100_AND_200;
                case 3:
                    return RADIUS_BETWEEN_200_AND_300;
                case 4:
                    return RADIUS_BETWEEN_300_AND_1000;
                case 5:
                    return RADIUS_BETWEEN_1000_AND_10000;
                case 6:
                    return RADIUS_LARGER_THAN_100000;
                case 7:
                    return RADIUS_NEGATIVE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<GeofenceRadiusBucket> internalGetValueMap() {
            return internalValueMap;
        }

        private GeofenceRadiusBucket(int value2) {
            this.value = value2;
        }
    }

    public enum ActivityImportance implements Internal.EnumLite {
        IMPORTANCE_UNKNOWN(0),
        IMPORTANCE_TOP(1),
        IMPORTANCE_FORGROUND_SERVICE(2),
        IMPORTANCE_BACKGROUND(3);
        
        public static final int IMPORTANCE_BACKGROUND_VALUE = 3;
        public static final int IMPORTANCE_FORGROUND_SERVICE_VALUE = 2;
        public static final int IMPORTANCE_TOP_VALUE = 1;
        public static final int IMPORTANCE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<ActivityImportance> internalValueMap = new Internal.EnumLiteMap<ActivityImportance>() {
            /* class android.stats.location.LocationStatsEnums.ActivityImportance.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ActivityImportance findValueByNumber(int number) {
                return ActivityImportance.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ActivityImportance valueOf(int value2) {
            return forNumber(value2);
        }

        public static ActivityImportance forNumber(int value2) {
            if (value2 == 0) {
                return IMPORTANCE_UNKNOWN;
            }
            if (value2 == 1) {
                return IMPORTANCE_TOP;
            }
            if (value2 == 2) {
                return IMPORTANCE_FORGROUND_SERVICE;
            }
            if (value2 != 3) {
                return null;
            }
            return IMPORTANCE_BACKGROUND;
        }

        public static Internal.EnumLiteMap<ActivityImportance> internalGetValueMap() {
            return internalValueMap;
        }

        private ActivityImportance(int value2) {
            this.value = value2;
        }
    }
}
