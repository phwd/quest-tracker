package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$Method implements Internal.EnumLite {
    UNKNOWN(99999),
    PING(0),
    HELLO(1),
    AUTHENTICATE(2),
    WIFI_SCAN(1001),
    WIFI_CONNECT(1002),
    WIFI_STATUS(1003),
    WIFI_FORGET(1004),
    WIFI_ENABLE(1005),
    WIFI_DISABLE(1006),
    WIFI_RECONNECT(1007),
    OCULUS_LOGIN_DEPRECATED(2001),
    OCULUS_LOGOUT(2002),
    OCULUS_SET_USER_SECRET(2003),
    OCULUS_SET_ACCESS_TOKEN(2004),
    OCULUS_INSERT_LINKED_ACCOUNT(2101),
    CONTROLLER_SCAN(3001),
    CONTROLLER_PAIR(3002),
    CONTROLLER_STATUS(3003),
    CONTROLLER_UNPAIR(3004),
    CONTROLLER_SET_HANDEDNESS(3005),
    CONTROLLER_SCAN_AND_PAIR(3006),
    CONTROLLER_VERIFY_CONNECTABLE(3007),
    VERIFY_MULTIPLE_CONTROLLERS_CONNECTABLE(3008),
    PIN_SET(4001),
    PIN_STATUS(4002),
    PIN_LOCK(4003),
    PIN_UNLOCK(4004),
    PIN_VERIFY(4005),
    PIN_RESET(4006),
    WIPE_DATA(5001),
    OTA_CHECK_AVAILABILITY(5002),
    OTA_MANUAL_UPDATE(5003),
    DEV_MODE_SET(6001),
    DEV_MODE_STATUS(6002),
    MTP_MODE_SET(6003),
    MTP_MODE_STATUS(6004),
    ADB_MODE_SET(6005),
    ADB_MODE_STATUS(6006),
    OTA_ENABLED_SET(6007),
    OTA_ENABLED_STATUS(6008),
    CRASH_REPORTS_ENABLED_SET(6009),
    CRASH_REPORTS_ENABLED_STATUS(6010),
    AUTOWAKE_SET(6011),
    AUTOWAKE_STATUS(6012),
    AUTOSLEEP_TIME_SET(6013),
    AUTOSLEEP_TIME_STATUS(6014),
    NAME_SET(6015),
    LINE_FREQUENCY_SET(6016),
    LINE_FREQUENCY_STATUS(6017),
    APP_LAUNCH(7001),
    HMD_STATUS(8001),
    HMD_VERSION(8002),
    HMD_CAPABILITIES(8003),
    HMD_EXTERNAL_BATTERY_STATUS(8004),
    LOCALE_SET(9001),
    TIME_SET(9002),
    HEALTH_AND_SAFETY_WARNING_SET(9101),
    NUX_COMPLETED(9201),
    MIRROR_REQUEST(10001),
    MANAGED_MODE_SET(11001),
    MANAGED_MODE_STATUS(11002),
    TEXT_SEND(12001),
    DISCOVER_CASTING_DEVICES(13001),
    START_CASTING(13002),
    STOP_CASTING(13003),
    CASTING_STATUS(13004),
    SKIP_HIGH_PRI_APPS_DOWNLOAD(14001),
    RESET_GUARDIAN(15001),
    RESET_HEADSET_VIEW(15002),
    DEVELOPMENT_LICENSE_DETAILS(16001),
    DEVELOPMENT_LICENSE_REFRESH(16002),
    PHONE_NOTIFICATION_SET(17001),
    PHONE_NOTIFICATION_STATUS(17002),
    INPUT_EVENT_SEND(19001),
    TOGGLE_PASSTHROUGH(20001),
    RENDER_KEYBOARD_START(21001),
    RENDER_KEYBOARD_STOP(21002);
    
    private static final Internal.EnumLiteMap<Protocol$Method> internalValueMap = new Internal.EnumLiteMap<Protocol$Method>() {
        /* class com.oculus.companion.server.Protocol$Method.AnonymousClass1 */
    };
    private final int value;

    public static Protocol$Method forNumber(int i) {
        if (i == 0) {
            return PING;
        }
        if (i == 1) {
            return HELLO;
        }
        if (i == 2) {
            return AUTHENTICATE;
        }
        if (i == 9001) {
            return LOCALE_SET;
        }
        if (i == 9002) {
            return TIME_SET;
        }
        if (i == 11001) {
            return MANAGED_MODE_SET;
        }
        if (i == 11002) {
            return MANAGED_MODE_STATUS;
        }
        switch (i) {
            case 1001:
                return WIFI_SCAN;
            case 1002:
                return WIFI_CONNECT;
            case 1003:
                return WIFI_STATUS;
            case 1004:
                return WIFI_FORGET;
            case 1005:
                return WIFI_ENABLE;
            case 1006:
                return WIFI_DISABLE;
            case 1007:
                return WIFI_RECONNECT;
            default:
                switch (i) {
                    case 2001:
                        return OCULUS_LOGIN_DEPRECATED;
                    case 2002:
                        return OCULUS_LOGOUT;
                    case 2003:
                        return OCULUS_SET_USER_SECRET;
                    case 2004:
                        return OCULUS_SET_ACCESS_TOKEN;
                    default:
                        switch (i) {
                            case 2101:
                                return OCULUS_INSERT_LINKED_ACCOUNT;
                            case 6001:
                                return DEV_MODE_SET;
                            case 6002:
                                return DEV_MODE_STATUS;
                            case 6003:
                                return MTP_MODE_SET;
                            case 6004:
                                return MTP_MODE_STATUS;
                            case 6005:
                                return ADB_MODE_SET;
                            case 6006:
                                return ADB_MODE_STATUS;
                            case 6007:
                                return OTA_ENABLED_SET;
                            case 6008:
                                return OTA_ENABLED_STATUS;
                            case 6009:
                                return CRASH_REPORTS_ENABLED_SET;
                            case 6010:
                                return CRASH_REPORTS_ENABLED_STATUS;
                            case 6011:
                                return AUTOWAKE_SET;
                            case 6012:
                                return AUTOWAKE_STATUS;
                            case 6013:
                                return AUTOSLEEP_TIME_SET;
                            case 6014:
                                return AUTOSLEEP_TIME_STATUS;
                            case 6015:
                                return NAME_SET;
                            case 6016:
                                return LINE_FREQUENCY_SET;
                            case 6017:
                                return LINE_FREQUENCY_STATUS;
                            case 7001:
                                return APP_LAUNCH;
                            case 9101:
                                return HEALTH_AND_SAFETY_WARNING_SET;
                            case 9201:
                                return NUX_COMPLETED;
                            case 10001:
                                return MIRROR_REQUEST;
                            case 12001:
                                return TEXT_SEND;
                            case 13001:
                                return DISCOVER_CASTING_DEVICES;
                            case 13002:
                                return START_CASTING;
                            case 13003:
                                return STOP_CASTING;
                            case 13004:
                                return CASTING_STATUS;
                            case 14001:
                                return SKIP_HIGH_PRI_APPS_DOWNLOAD;
                            case 15001:
                                return RESET_GUARDIAN;
                            case 15002:
                                return RESET_HEADSET_VIEW;
                            case 16001:
                                return DEVELOPMENT_LICENSE_DETAILS;
                            case 16002:
                                return DEVELOPMENT_LICENSE_REFRESH;
                            case 17001:
                                return PHONE_NOTIFICATION_SET;
                            case 17002:
                                return PHONE_NOTIFICATION_STATUS;
                            case 19001:
                                return INPUT_EVENT_SEND;
                            case 20001:
                                return TOGGLE_PASSTHROUGH;
                            case 21001:
                                return RENDER_KEYBOARD_START;
                            case 21002:
                                return RENDER_KEYBOARD_STOP;
                            case 99999:
                                return UNKNOWN;
                            default:
                                switch (i) {
                                    case 3001:
                                        return CONTROLLER_SCAN;
                                    case 3002:
                                        return CONTROLLER_PAIR;
                                    case 3003:
                                        return CONTROLLER_STATUS;
                                    case 3004:
                                        return CONTROLLER_UNPAIR;
                                    case 3005:
                                        return CONTROLLER_SET_HANDEDNESS;
                                    case 3006:
                                        return CONTROLLER_SCAN_AND_PAIR;
                                    case 3007:
                                        return CONTROLLER_VERIFY_CONNECTABLE;
                                    case 3008:
                                        return VERIFY_MULTIPLE_CONTROLLERS_CONNECTABLE;
                                    default:
                                        switch (i) {
                                            case 4001:
                                                return PIN_SET;
                                            case 4002:
                                                return PIN_STATUS;
                                            case 4003:
                                                return PIN_LOCK;
                                            case 4004:
                                                return PIN_UNLOCK;
                                            case 4005:
                                                return PIN_VERIFY;
                                            case 4006:
                                                return PIN_RESET;
                                            default:
                                                switch (i) {
                                                    case 5001:
                                                        return WIPE_DATA;
                                                    case 5002:
                                                        return OTA_CHECK_AVAILABILITY;
                                                    case 5003:
                                                        return OTA_MANUAL_UPDATE;
                                                    default:
                                                        switch (i) {
                                                            case 8001:
                                                                return HMD_STATUS;
                                                            case 8002:
                                                                return HMD_VERSION;
                                                            case 8003:
                                                                return HMD_CAPABILITIES;
                                                            case 8004:
                                                                return HMD_EXTERNAL_BATTERY_STATUS;
                                                            default:
                                                                return null;
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    private Protocol$Method(int i) {
        this.value = i;
    }
}
