package com.oculus.companion.server;

import android.support.coordinatorlayout.R$styleable;
import com.google.protobuf.Internal;

public enum Protocol$ErrorCode implements Internal.EnumLite {
    UNKNOWN_ERROR(0),
    BAD_REQUEST(1),
    UNSUPPORTED_METHOD(2),
    BATTERY_TOO_LOW(3),
    BAD_ARGUEMENT(4),
    AUTHENTICATION_FAILURE(5),
    TIMED_OUT(6),
    ALREADY_IN_PROGRESS(7),
    USER_PIN_REQUIRED(8),
    INTERNAL_ERROR(9),
    WIFI_NO_NETWORK(11),
    WIFI_INVALID_AUTH(12),
    BAD_ACCESS_TOKEN(13),
    DEVICE_WIFI_ERROR(14),
    DEVICE_BLE_ERROR(15),
    WIFI_NO_INTERNET(16),
    WIFI_AUTH_TIMEOUT(17),
    WIFI_IP_CONFIG_FAIL(18),
    OPERATION_NOT_ALLOWED(19),
    BAD_PERIPHERAL_DEVICE(30),
    BAD_PERIPHERAL_ADDRESS(31),
    BLE_PAIRING_KEY_REQUIRED(32),
    BAD_LOCK_PIN(401),
    PIN_LOCK_NOT_SET(402),
    TOO_MANY_PIN_TRIES(403),
    APP_LAUNCH_ERROR(501),
    APP_NOT_INSTALLED(502),
    CONTROLLER_PAIR_FAILED(601),
    CONTROLLER_PAIR_REQUIRED(602),
    CONTROLLER_INTERNAL_ERROR(603),
    CONTROLLER_BLOCKED_BY_UPDATE(604),
    DEVELOPMENT_LICENSE_DOES_NOT_EXIST(701),
    GK_CHECK_FAILED(801),
    WRONG_USER_LOGGED_IN(901);
    
    private static final Internal.EnumLiteMap<Protocol$ErrorCode> internalValueMap = new Internal.EnumLiteMap<Protocol$ErrorCode>() {
        /* class com.oculus.companion.server.Protocol$ErrorCode.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$ErrorCode forNumber(int i) {
        if (i == 501) {
            return APP_LAUNCH_ERROR;
        }
        if (i == 502) {
            return APP_NOT_INSTALLED;
        }
        if (i == 701) {
            return DEVELOPMENT_LICENSE_DOES_NOT_EXIST;
        }
        if (i == 801) {
            return GK_CHECK_FAILED;
        }
        if (i == 901) {
            return WRONG_USER_LOGGED_IN;
        }
        switch (i) {
            case 0:
                return UNKNOWN_ERROR;
            case 1:
                return BAD_REQUEST;
            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                return UNSUPPORTED_METHOD;
            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                return BATTERY_TOO_LOW;
            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                return BAD_ARGUEMENT;
            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                return AUTHENTICATION_FAILURE;
            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                return TIMED_OUT;
            case 7:
                return ALREADY_IN_PROGRESS;
            case 8:
                return USER_PIN_REQUIRED;
            case 9:
                return INTERNAL_ERROR;
            default:
                switch (i) {
                    case 11:
                        return WIFI_NO_NETWORK;
                    case 12:
                        return WIFI_INVALID_AUTH;
                    case 13:
                        return BAD_ACCESS_TOKEN;
                    case 14:
                        return DEVICE_WIFI_ERROR;
                    case 15:
                        return DEVICE_BLE_ERROR;
                    case 16:
                        return WIFI_NO_INTERNET;
                    case 17:
                        return WIFI_AUTH_TIMEOUT;
                    case 18:
                        return WIFI_IP_CONFIG_FAIL;
                    case 19:
                        return OPERATION_NOT_ALLOWED;
                    default:
                        switch (i) {
                            case 30:
                                return BAD_PERIPHERAL_DEVICE;
                            case 31:
                                return BAD_PERIPHERAL_ADDRESS;
                            case 32:
                                return BLE_PAIRING_KEY_REQUIRED;
                            default:
                                switch (i) {
                                    case 401:
                                        return BAD_LOCK_PIN;
                                    case 402:
                                        return PIN_LOCK_NOT_SET;
                                    case 403:
                                        return TOO_MANY_PIN_TRIES;
                                    default:
                                        switch (i) {
                                            case 601:
                                                return CONTROLLER_PAIR_FAILED;
                                            case 602:
                                                return CONTROLLER_PAIR_REQUIRED;
                                            case 603:
                                                return CONTROLLER_INTERNAL_ERROR;
                                            case 604:
                                                return CONTROLLER_BLOCKED_BY_UPDATE;
                                            default:
                                                return null;
                                        }
                                }
                        }
                }
        }
    }

    private Protocol$ErrorCode(int i) {
        this.value = i;
    }
}
