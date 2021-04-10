package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class PowerMetrics {
    public static final int ACRA_ACTIVE_RADIO_TIME_S = 7209067;
    public static final int ACRA_RADIO_WAKEUP_COUNT = 7209069;
    public static final int ACRA_TAIL_RADIO_TIME_S = 7209068;
    public static final int ACRA_TX_BYTES = 7209070;
    public static final int APP_WAKEUP_ATTRIBUTION_DATA = 7209051;
    public static final int AVG_BRIGHTNESS = 7209022;
    public static final int BATTERY_DISCHARGE_TRACING = 7209071;
    public static final int BLE_OPPORTUNISTIC_SCAN_COUNT = 7209065;
    public static final int BLE_OPPORTUNISTIC_SCAN_TOTAL_DURATION_MS = 7209066;
    public static final int BLE_SCAN_COUNT = 7209043;
    public static final int BLE_SCAN_TOTAL_DURATION_MS = 7209044;
    public static final int CAMERA_OPEN_TIME = 7209009;
    public static final int CAMERA_PREVIEW_TIME = 7209010;
    public static final int CHILD_CPU_SYSTEM_TIME = 7208987;
    public static final int CHILD_CPU_TOTAL_TIME = 7208988;
    public static final int CHILD_CPU_USER_TIME = 7208986;
    public static final int CONTACTS_DOWNLOAD_COUNT = 7209046;
    public static final int CPU_ATTRIBUTION_DATA = 7208989;
    public static final int CPU_SPIN_BLACKBOX_TRACING = 7209072;
    public static final int CPU_SPIN_TRACING = 7209008;
    public static final int CPU_TIME_IN_STATE = 7209021;
    public static final int DELTA_CONTACTS_SYNC = 7209048;
    public static final int DISPLAY_ENERGY = 7209023;
    public static final int DISPLAY_ENERGY_100_TIME_PERCENT_ON_BATTERY = 7209024;
    public static final int FULL_CONTACTS_SYNC = 7209047;
    public static final int LIGER_ATTRIBUTION_DATA = 7208972;
    public static final int LIGER_DOWN_BYTES = 7208974;
    public static final int LIGER_FULL_POWER_TIME = 7208971;
    public static final int LIGER_LOW_POWER_TIME = 7208970;
    public static final int LIGER_REQUEST_COUNT = 7208975;
    public static final int LIGER_TRANSFER_DATA_SIZE = 7208969;
    public static final int LIGER_UP_BYTES = 7208973;
    public static final int LIGER_WAKEUP_COUNT = 7208976;
    public static final int LOCATION_ATTRIBUTION_DATA = 7208982;
    public static final int LOCATION_COARSE_TIME_MS = 7208983;
    public static final int LOCATION_FINE_TIME_MS = 7208985;
    public static final int LOCATION_MEDIUM_TIME_MS = 7208984;
    public static final int MOBILE_RX_BYTES = 7209033;
    public static final int MOBILE_RX_PACKETS = 7209037;
    public static final int MOBILE_TX_BYTES = 7209034;
    public static final int MOBILE_TX_PACKETS = 7209035;
    public static final short MODULE_ID = 110;
    public static final int MQTT_ATTRIBUTION_DATA = 7209006;
    public static final int MQTT_FULL_POWER_TIME = 7209015;
    public static final int MQTT_LOW_POWER_TIME = 7209016;
    public static final int MQTT_NUM_RADIO_WAKEUPS = 7208965;
    public static final int MQTT_RECEIVED_BYTES = 7208968;
    public static final int MQTT_SENT_BYTES = 7208967;
    public static final int MQTT_TRAFFIC_COUNT = 7208966;
    public static final int PROC_CPU_SYSTEM_TIME = 7208962;
    public static final int PROC_CPU_TOTAL_TIME = 7208963;
    public static final int PROC_CPU_USER_TIME = 7208961;
    public static final int RADIO_MOBILE_HIGH_POWER_ACTIVE_S = 7209061;
    public static final int RADIO_MOBILE_LOW_POWER_ACTIVE_S = 7209060;
    public static final int RADIO_MOBILE_WAKEUP_COUNT = 7209062;
    public static final int RADIO_WIFI_ACTIVE_S = 7209063;
    public static final int RADIO_WIFI_WAKEUP_COUNT = 7209064;
    public static final int RANDOM_LOOM_TRACING = 7209049;
    public static final int RANDOM_LOOM_TRACING_BG = 7209074;
    public static final int RANDOM_LOOM_TRACING_FG = 7209073;
    public static final int REPORT_METRICS = 7211220;
    public static final int SESSION_BATTERY_DRAIN = 7208964;
    public static final int SESSION_BATTERY_WAS_CHARGING = 7209042;
    public static final int SESSION_END_BATTERY_LEVEL = 7209041;
    public static final int SESSION_POWER_DRAIN = 7208990;
    public static final int THREAD_CPU_ATTRIBUTION_DATA = 7209045;
    public static final int WAKELOCKS_ACQUIRED_NUM = 7208979;
    public static final int WAKELOCKS_ATTRIBUTION_DATA = 7208980;
    public static final int WAKELOCKS_DISPOSED_TIME = 7208981;
    public static final int WAKELOCKS_HELD_TIME = 7208978;
    public static final int WIFI_RX_BYTES = 7209040;
    public static final int WIFI_RX_PACKETS = 7209036;
    public static final int WIFI_SCAN_COUNT = 7209050;
    public static final int WIFI_TX_BYTES = 7209039;
    public static final int WIFI_TX_PACKETS = 7209038;

    public static String getMarkerName(int i) {
        if (i == 46) {
            return "POWER_METRICS_MQTT_ATTRIBUTION_DATA";
        }
        if (i == 2260) {
            return "POWER_METRICS_REPORT_METRICS";
        }
        if (i == 55) {
            return "POWER_METRICS_MQTT_FULL_POWER_TIME";
        }
        if (i == 56) {
            return "POWER_METRICS_MQTT_LOW_POWER_TIME";
        }
        switch (i) {
            case 1:
                return "POWER_METRICS_PROC_CPU_USER_TIME";
            case 2:
                return "POWER_METRICS_PROC_CPU_SYSTEM_TIME";
            case 3:
                return "POWER_METRICS_PROC_CPU_TOTAL_TIME";
            case 4:
                return "POWER_METRICS_SESSION_BATTERY_DRAIN";
            case 5:
                return "POWER_METRICS_MQTT_NUM_RADIO_WAKEUPS";
            case 6:
                return "POWER_METRICS_MQTT_TRAFFIC_COUNT";
            case 7:
                return "POWER_METRICS_MQTT_SENT_BYTES";
            case 8:
                return "POWER_METRICS_MQTT_RECEIVED_BYTES";
            case 9:
                return "POWER_METRICS_LIGER_TRANSFER_DATA_SIZE";
            case 10:
                return "POWER_METRICS_LIGER_LOW_POWER_TIME";
            case 11:
                return "POWER_METRICS_LIGER_FULL_POWER_TIME";
            case 12:
                return "POWER_METRICS_LIGER_ATTRIBUTION_DATA";
            case 13:
                return "POWER_METRICS_LIGER_UP_BYTES";
            case 14:
                return "POWER_METRICS_LIGER_DOWN_BYTES";
            case 15:
                return "POWER_METRICS_LIGER_REQUEST_COUNT";
            case 16:
                return "POWER_METRICS_LIGER_WAKEUP_COUNT";
            default:
                switch (i) {
                    case 18:
                        return "POWER_METRICS_WAKELOCKS_HELD_TIME";
                    case 19:
                        return "POWER_METRICS_WAKELOCKS_ACQUIRED_NUM";
                    case 20:
                        return "POWER_METRICS_WAKELOCKS_ATTRIBUTION_DATA";
                    case 21:
                        return "POWER_METRICS_WAKELOCKS_DISPOSED_TIME";
                    case 22:
                        return "POWER_METRICS_LOCATION_ATTRIBUTION_DATA";
                    case 23:
                        return "POWER_METRICS_LOCATION_COARSE_TIME_MS";
                    case 24:
                        return "POWER_METRICS_LOCATION_MEDIUM_TIME_MS";
                    case 25:
                        return "POWER_METRICS_LOCATION_FINE_TIME_MS";
                    case 26:
                        return "POWER_METRICS_CHILD_CPU_USER_TIME";
                    case 27:
                        return "POWER_METRICS_CHILD_CPU_SYSTEM_TIME";
                    case 28:
                        return "POWER_METRICS_CHILD_CPU_TOTAL_TIME";
                    case 29:
                        return "POWER_METRICS_CPU_ATTRIBUTION_DATA";
                    case 30:
                        return "POWER_METRICS_SESSION_POWER_DRAIN";
                    default:
                        switch (i) {
                            case 48:
                                return "POWER_METRICS_CPU_SPIN_TRACING";
                            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                return "POWER_METRICS_CAMERA_OPEN_TIME";
                            case 50:
                                return "POWER_METRICS_CAMERA_PREVIEW_TIME";
                            default:
                                switch (i) {
                                    case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                                        return "POWER_METRICS_CPU_TIME_IN_STATE";
                                    case 62:
                                        return "POWER_METRICS_AVG_BRIGHTNESS";
                                    case 63:
                                        return "POWER_METRICS_DISPLAY_ENERGY";
                                    case 64:
                                        return "POWER_METRICS_DISPLAY_ENERGY_100_TIME_PERCENT_ON_BATTERY";
                                    default:
                                        switch (i) {
                                            case 73:
                                                return "POWER_METRICS_MOBILE_RX_BYTES";
                                            case 74:
                                                return "POWER_METRICS_MOBILE_TX_BYTES";
                                            case 75:
                                                return "POWER_METRICS_MOBILE_TX_PACKETS";
                                            case 76:
                                                return "POWER_METRICS_WIFI_RX_PACKETS";
                                            case 77:
                                                return "POWER_METRICS_MOBILE_RX_PACKETS";
                                            case 78:
                                                return "POWER_METRICS_WIFI_TX_PACKETS";
                                            case 79:
                                                return "POWER_METRICS_WIFI_TX_BYTES";
                                            case 80:
                                                return "POWER_METRICS_WIFI_RX_BYTES";
                                            case 81:
                                                return "POWER_METRICS_SESSION_END_BATTERY_LEVEL";
                                            case 82:
                                                return "POWER_METRICS_SESSION_BATTERY_WAS_CHARGING";
                                            case 83:
                                                return "POWER_METRICS_BLE_SCAN_COUNT";
                                            case 84:
                                                return "POWER_METRICS_BLE_SCAN_TOTAL_DURATION_MS";
                                            case 85:
                                                return "POWER_METRICS_THREAD_CPU_ATTRIBUTION_DATA";
                                            case 86:
                                                return "POWER_METRICS_CONTACTS_DOWNLOAD_COUNT";
                                            case 87:
                                                return "POWER_METRICS_FULL_CONTACTS_SYNC";
                                            case 88:
                                                return "POWER_METRICS_DELTA_CONTACTS_SYNC";
                                            case 89:
                                                return "POWER_METRICS_RANDOM_LOOM_TRACING";
                                            case 90:
                                                return "POWER_METRICS_WIFI_SCAN_COUNT";
                                            case 91:
                                                return "POWER_METRICS_APP_WAKEUP_ATTRIBUTION_DATA";
                                            default:
                                                switch (i) {
                                                    case 100:
                                                        return "POWER_METRICS_RADIO_MOBILE_LOW_POWER_ACTIVE_S";
                                                    case 101:
                                                        return "POWER_METRICS_RADIO_MOBILE_HIGH_POWER_ACTIVE_S";
                                                    case 102:
                                                        return "POWER_METRICS_RADIO_MOBILE_WAKEUP_COUNT";
                                                    case 103:
                                                        return "POWER_METRICS_RADIO_WIFI_ACTIVE_S";
                                                    case 104:
                                                        return "POWER_METRICS_RADIO_WIFI_WAKEUP_COUNT";
                                                    case 105:
                                                        return "POWER_METRICS_BLE_OPPORTUNISTIC_SCAN_COUNT";
                                                    case 106:
                                                        return "POWER_METRICS_BLE_OPPORTUNISTIC_SCAN_TOTAL_DURATION_MS";
                                                    case 107:
                                                        return "POWER_METRICS_ACRA_ACTIVE_RADIO_TIME_S";
                                                    case 108:
                                                        return "POWER_METRICS_ACRA_TAIL_RADIO_TIME_S";
                                                    case 109:
                                                        return "POWER_METRICS_ACRA_RADIO_WAKEUP_COUNT";
                                                    case 110:
                                                        return "POWER_METRICS_ACRA_TX_BYTES";
                                                    case 111:
                                                        return "POWER_METRICS_BATTERY_DISCHARGE_TRACING";
                                                    case 112:
                                                        return "POWER_METRICS_CPU_SPIN_BLACKBOX_TRACING";
                                                    case 113:
                                                        return "POWER_METRICS_RANDOM_LOOM_TRACING_FG";
                                                    case 114:
                                                        return "POWER_METRICS_RANDOM_LOOM_TRACING_BG";
                                                    default:
                                                        return "UNDEFINED_QPL_EVENT";
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
