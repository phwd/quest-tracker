package android.stats.devicepolicy;

import com.google.protobuf.Internal;

public enum EventId implements Internal.EnumLite {
    SET_PASSWORD_QUALITY(1),
    SET_PASSWORD_MINIMUM_LENGTH(2),
    SET_PASSWORD_MINIMUM_NUMERIC(3),
    SET_PASSWORD_MINIMUM_NON_LETTER(4),
    SET_PASSWORD_MINIMUM_LETTERS(5),
    SET_PASSWORD_MINIMUM_LOWER_CASE(6),
    SET_PASSWORD_MINIMUM_UPPER_CASE(7),
    SET_PASSWORD_MINIMUM_SYMBOLS(8),
    SET_KEYGUARD_DISABLED_FEATURES(9),
    LOCK_NOW(10),
    WIPE_DATA_WITH_REASON(11),
    ADD_USER_RESTRICTION(12),
    REMOVE_USER_RESTRICTION(13),
    SET_SECURE_SETTING(14),
    SET_SECURITY_LOGGING_ENABLED(15),
    RETRIEVE_SECURITY_LOGS(16),
    RETRIEVE_PRE_REBOOT_SECURITY_LOGS(17),
    SET_PERMISSION_POLICY(18),
    SET_PERMISSION_GRANT_STATE(19),
    INSTALL_KEY_PAIR(20),
    INSTALL_CA_CERT(21),
    CHOOSE_PRIVATE_KEY_ALIAS(22),
    REMOVE_KEY_PAIR(23),
    UNINSTALL_CA_CERTS(24),
    SET_CERT_INSTALLER_PACKAGE(25),
    SET_ALWAYS_ON_VPN_PACKAGE(26),
    SET_PERMITTED_INPUT_METHODS(27),
    SET_PERMITTED_ACCESSIBILITY_SERVICES(28),
    SET_SCREEN_CAPTURE_DISABLED(29),
    SET_CAMERA_DISABLED(30),
    QUERY_SUMMARY_FOR_USER(31),
    QUERY_SUMMARY(32),
    QUERY_DETAILS(33),
    REBOOT(34),
    SET_MASTER_VOLUME_MUTED(35),
    SET_AUTO_TIME_REQUIRED(36),
    SET_KEYGUARD_DISABLED(37),
    SET_STATUS_BAR_DISABLED(38),
    SET_ORGANIZATION_COLOR(39),
    SET_PROFILE_NAME(40),
    SET_USER_ICON(41),
    SET_DEVICE_OWNER_LOCK_SCREEN_INFO(42),
    SET_SHORT_SUPPORT_MESSAGE(43),
    SET_LONG_SUPPORT_MESSAGE(44),
    SET_CROSS_PROFILE_CONTACTS_SEARCH_DISABLED(45),
    SET_CROSS_PROFILE_CALLER_ID_DISABLED(46),
    SET_BLUETOOTH_CONTACT_SHARING_DISABLED(47),
    ADD_CROSS_PROFILE_INTENT_FILTER(48),
    ADD_CROSS_PROFILE_WIDGET_PROVIDER(49),
    SET_SYSTEM_UPDATE_POLICY(50),
    SET_LOCKTASK_MODE_ENABLED(51),
    ADD_PERSISTENT_PREFERRED_ACTIVITY(52),
    REQUEST_BUGREPORT(53),
    GET_WIFI_MAC_ADDRESS(54),
    REQUEST_QUIET_MODE_ENABLED(55),
    WORK_PROFILE_LOCATION_CHANGED(56),
    DO_USER_INFO_CLICKED(57),
    TRANSFER_OWNERSHIP(58),
    GENERATE_KEY_PAIR(59),
    SET_KEY_PAIR_CERTIFICATE(60),
    SET_KEEP_UNINSTALLED_PACKAGES(61),
    SET_APPLICATION_RESTRICTIONS(62),
    SET_APPLICATION_HIDDEN(63),
    ENABLE_SYSTEM_APP(64),
    ENABLE_SYSTEM_APP_WITH_INTENT(65),
    INSTALL_EXISTING_PACKAGE(66),
    SET_UNINSTALL_BLOCKED(67),
    SET_PACKAGES_SUSPENDED(68),
    ON_LOCK_TASK_MODE_ENTERING(69),
    SET_CROSS_PROFILE_CALENDAR_PACKAGES(70),
    GET_USER_PASSWORD_COMPLEXITY_LEVEL(72),
    INSTALL_SYSTEM_UPDATE(73),
    INSTALL_SYSTEM_UPDATE_ERROR(74),
    IS_MANAGED_KIOSK(75),
    IS_UNATTENDED_MANAGED_KIOSK(76),
    PROVISIONING_MANAGED_PROFILE_ON_FULLY_MANAGED_DEVICE(77),
    PROVISIONING_PERSISTENT_DEVICE_OWNER(78),
    PROVISIONING_ENTRY_POINT_NFC(79),
    PROVISIONING_ENTRY_POINT_QR_CODE(80),
    PROVISIONING_ENTRY_POINT_CLOUD_ENROLLMENT(81),
    PROVISIONING_ENTRY_POINT_ADB(82),
    PROVISIONING_ENTRY_POINT_TRUSTED_SOURCE(83),
    PROVISIONING_DPC_PACKAGE_NAME(84),
    PROVISIONING_DPC_INSTALLED_BY_PACKAGE(85),
    PROVISIONING_PROVISIONING_ACTIVITY_TIME_MS(86),
    PROVISIONING_PREPROVISIONING_ACTIVITY_TIME_MS(87),
    PROVISIONING_ENCRYPT_DEVICE_ACTIVITY_TIME_MS(88),
    PROVISIONING_WEB_ACTIVITY_TIME_MS(89),
    PROVISIONING_TRAMPOLINE_ACTIVITY_TIME_MS(90),
    PROVISIONING_POST_ENCRYPTION_ACTIVITY_TIME_MS(91),
    PROVISIONING_FINALIZATION_ACTIVITY_TIME_MS(92),
    PROVISIONING_NETWORK_TYPE(93),
    PROVISIONING_ACTION(94),
    PROVISIONING_EXTRAS(95),
    PROVISIONING_COPY_ACCOUNT_TASK_MS(96),
    PROVISIONING_CREATE_PROFILE_TASK_MS(97),
    PROVISIONING_START_PROFILE_TASK_MS(98),
    PROVISIONING_DOWNLOAD_PACKAGE_TASK_MS(99),
    PROVISIONING_INSTALL_PACKAGE_TASK_MS(100),
    PROVISIONING_CANCELLED(101),
    PROVISIONING_ERROR(102),
    PROVISIONING_COPY_ACCOUNT_STATUS(103),
    PROVISIONING_TOTAL_TASK_TIME_MS(104),
    PROVISIONING_SESSION_STARTED(105),
    PROVISIONING_SESSION_COMPLETED(106),
    PROVISIONING_TERMS_ACTIVITY_TIME_MS(107),
    PROVISIONING_TERMS_COUNT(108),
    PROVISIONING_TERMS_READ(109),
    SEPARATE_PROFILE_CHALLENGE_CHANGED(110),
    SET_GLOBAL_SETTING(111),
    INSTALL_PACKAGE(112),
    UNINSTALL_PACKAGE(113),
    WIFI_SERVICE_ADD_NETWORK_SUGGESTIONS(114),
    WIFI_SERVICE_ADD_OR_UPDATE_NETWORK(115),
    QUERY_SUMMARY_FOR_DEVICE(116),
    REMOVE_CROSS_PROFILE_WIDGET_PROVIDER(117),
    ESTABLISH_VPN(118),
    SET_NETWORK_LOGGING_ENABLED(119),
    RETRIEVE_NETWORK_LOGS(120),
    PROVISIONING_PREPARE_TOTAL_TIME_MS(121),
    PROVISIONING_PREPARE_STARTED(122),
    PROVISIONING_PREPARE_COMPLETED(123),
    PROVISIONING_FLOW_TYPE(124),
    CROSS_PROFILE_APPS_GET_TARGET_USER_PROFILES(125),
    CROSS_PROFILE_APPS_START_ACTIVITY_AS_USER(126);
    
    public static final int ADD_CROSS_PROFILE_INTENT_FILTER_VALUE = 48;
    public static final int ADD_CROSS_PROFILE_WIDGET_PROVIDER_VALUE = 49;
    public static final int ADD_PERSISTENT_PREFERRED_ACTIVITY_VALUE = 52;
    public static final int ADD_USER_RESTRICTION_VALUE = 12;
    public static final int CHOOSE_PRIVATE_KEY_ALIAS_VALUE = 22;
    public static final int CROSS_PROFILE_APPS_GET_TARGET_USER_PROFILES_VALUE = 125;
    public static final int CROSS_PROFILE_APPS_START_ACTIVITY_AS_USER_VALUE = 126;
    public static final int DO_USER_INFO_CLICKED_VALUE = 57;
    public static final int ENABLE_SYSTEM_APP_VALUE = 64;
    public static final int ENABLE_SYSTEM_APP_WITH_INTENT_VALUE = 65;
    public static final int ESTABLISH_VPN_VALUE = 118;
    public static final int GENERATE_KEY_PAIR_VALUE = 59;
    public static final int GET_USER_PASSWORD_COMPLEXITY_LEVEL_VALUE = 72;
    public static final int GET_WIFI_MAC_ADDRESS_VALUE = 54;
    public static final int INSTALL_CA_CERT_VALUE = 21;
    public static final int INSTALL_EXISTING_PACKAGE_VALUE = 66;
    public static final int INSTALL_KEY_PAIR_VALUE = 20;
    public static final int INSTALL_PACKAGE_VALUE = 112;
    public static final int INSTALL_SYSTEM_UPDATE_ERROR_VALUE = 74;
    public static final int INSTALL_SYSTEM_UPDATE_VALUE = 73;
    public static final int IS_MANAGED_KIOSK_VALUE = 75;
    public static final int IS_UNATTENDED_MANAGED_KIOSK_VALUE = 76;
    public static final int LOCK_NOW_VALUE = 10;
    public static final int ON_LOCK_TASK_MODE_ENTERING_VALUE = 69;
    public static final int PROVISIONING_ACTION_VALUE = 94;
    public static final int PROVISIONING_CANCELLED_VALUE = 101;
    public static final int PROVISIONING_COPY_ACCOUNT_STATUS_VALUE = 103;
    public static final int PROVISIONING_COPY_ACCOUNT_TASK_MS_VALUE = 96;
    public static final int PROVISIONING_CREATE_PROFILE_TASK_MS_VALUE = 97;
    public static final int PROVISIONING_DOWNLOAD_PACKAGE_TASK_MS_VALUE = 99;
    public static final int PROVISIONING_DPC_INSTALLED_BY_PACKAGE_VALUE = 85;
    public static final int PROVISIONING_DPC_PACKAGE_NAME_VALUE = 84;
    public static final int PROVISIONING_ENCRYPT_DEVICE_ACTIVITY_TIME_MS_VALUE = 88;
    public static final int PROVISIONING_ENTRY_POINT_ADB_VALUE = 82;
    public static final int PROVISIONING_ENTRY_POINT_CLOUD_ENROLLMENT_VALUE = 81;
    public static final int PROVISIONING_ENTRY_POINT_NFC_VALUE = 79;
    public static final int PROVISIONING_ENTRY_POINT_QR_CODE_VALUE = 80;
    public static final int PROVISIONING_ENTRY_POINT_TRUSTED_SOURCE_VALUE = 83;
    public static final int PROVISIONING_ERROR_VALUE = 102;
    public static final int PROVISIONING_EXTRAS_VALUE = 95;
    public static final int PROVISIONING_FINALIZATION_ACTIVITY_TIME_MS_VALUE = 92;
    public static final int PROVISIONING_FLOW_TYPE_VALUE = 124;
    public static final int PROVISIONING_INSTALL_PACKAGE_TASK_MS_VALUE = 100;
    public static final int PROVISIONING_MANAGED_PROFILE_ON_FULLY_MANAGED_DEVICE_VALUE = 77;
    public static final int PROVISIONING_NETWORK_TYPE_VALUE = 93;
    public static final int PROVISIONING_PERSISTENT_DEVICE_OWNER_VALUE = 78;
    public static final int PROVISIONING_POST_ENCRYPTION_ACTIVITY_TIME_MS_VALUE = 91;
    public static final int PROVISIONING_PREPARE_COMPLETED_VALUE = 123;
    public static final int PROVISIONING_PREPARE_STARTED_VALUE = 122;
    public static final int PROVISIONING_PREPARE_TOTAL_TIME_MS_VALUE = 121;
    public static final int PROVISIONING_PREPROVISIONING_ACTIVITY_TIME_MS_VALUE = 87;
    public static final int PROVISIONING_PROVISIONING_ACTIVITY_TIME_MS_VALUE = 86;
    public static final int PROVISIONING_SESSION_COMPLETED_VALUE = 106;
    public static final int PROVISIONING_SESSION_STARTED_VALUE = 105;
    public static final int PROVISIONING_START_PROFILE_TASK_MS_VALUE = 98;
    public static final int PROVISIONING_TERMS_ACTIVITY_TIME_MS_VALUE = 107;
    public static final int PROVISIONING_TERMS_COUNT_VALUE = 108;
    public static final int PROVISIONING_TERMS_READ_VALUE = 109;
    public static final int PROVISIONING_TOTAL_TASK_TIME_MS_VALUE = 104;
    public static final int PROVISIONING_TRAMPOLINE_ACTIVITY_TIME_MS_VALUE = 90;
    public static final int PROVISIONING_WEB_ACTIVITY_TIME_MS_VALUE = 89;
    public static final int QUERY_DETAILS_VALUE = 33;
    public static final int QUERY_SUMMARY_FOR_DEVICE_VALUE = 116;
    public static final int QUERY_SUMMARY_FOR_USER_VALUE = 31;
    public static final int QUERY_SUMMARY_VALUE = 32;
    public static final int REBOOT_VALUE = 34;
    public static final int REMOVE_CROSS_PROFILE_WIDGET_PROVIDER_VALUE = 117;
    public static final int REMOVE_KEY_PAIR_VALUE = 23;
    public static final int REMOVE_USER_RESTRICTION_VALUE = 13;
    public static final int REQUEST_BUGREPORT_VALUE = 53;
    public static final int REQUEST_QUIET_MODE_ENABLED_VALUE = 55;
    public static final int RETRIEVE_NETWORK_LOGS_VALUE = 120;
    public static final int RETRIEVE_PRE_REBOOT_SECURITY_LOGS_VALUE = 17;
    public static final int RETRIEVE_SECURITY_LOGS_VALUE = 16;
    public static final int SEPARATE_PROFILE_CHALLENGE_CHANGED_VALUE = 110;
    public static final int SET_ALWAYS_ON_VPN_PACKAGE_VALUE = 26;
    public static final int SET_APPLICATION_HIDDEN_VALUE = 63;
    public static final int SET_APPLICATION_RESTRICTIONS_VALUE = 62;
    public static final int SET_AUTO_TIME_REQUIRED_VALUE = 36;
    public static final int SET_BLUETOOTH_CONTACT_SHARING_DISABLED_VALUE = 47;
    public static final int SET_CAMERA_DISABLED_VALUE = 30;
    public static final int SET_CERT_INSTALLER_PACKAGE_VALUE = 25;
    public static final int SET_CROSS_PROFILE_CALENDAR_PACKAGES_VALUE = 70;
    public static final int SET_CROSS_PROFILE_CALLER_ID_DISABLED_VALUE = 46;
    public static final int SET_CROSS_PROFILE_CONTACTS_SEARCH_DISABLED_VALUE = 45;
    public static final int SET_DEVICE_OWNER_LOCK_SCREEN_INFO_VALUE = 42;
    public static final int SET_GLOBAL_SETTING_VALUE = 111;
    public static final int SET_KEEP_UNINSTALLED_PACKAGES_VALUE = 61;
    public static final int SET_KEYGUARD_DISABLED_FEATURES_VALUE = 9;
    public static final int SET_KEYGUARD_DISABLED_VALUE = 37;
    public static final int SET_KEY_PAIR_CERTIFICATE_VALUE = 60;
    public static final int SET_LOCKTASK_MODE_ENABLED_VALUE = 51;
    public static final int SET_LONG_SUPPORT_MESSAGE_VALUE = 44;
    public static final int SET_MASTER_VOLUME_MUTED_VALUE = 35;
    public static final int SET_NETWORK_LOGGING_ENABLED_VALUE = 119;
    public static final int SET_ORGANIZATION_COLOR_VALUE = 39;
    public static final int SET_PACKAGES_SUSPENDED_VALUE = 68;
    public static final int SET_PASSWORD_MINIMUM_LENGTH_VALUE = 2;
    public static final int SET_PASSWORD_MINIMUM_LETTERS_VALUE = 5;
    public static final int SET_PASSWORD_MINIMUM_LOWER_CASE_VALUE = 6;
    public static final int SET_PASSWORD_MINIMUM_NON_LETTER_VALUE = 4;
    public static final int SET_PASSWORD_MINIMUM_NUMERIC_VALUE = 3;
    public static final int SET_PASSWORD_MINIMUM_SYMBOLS_VALUE = 8;
    public static final int SET_PASSWORD_MINIMUM_UPPER_CASE_VALUE = 7;
    public static final int SET_PASSWORD_QUALITY_VALUE = 1;
    public static final int SET_PERMISSION_GRANT_STATE_VALUE = 19;
    public static final int SET_PERMISSION_POLICY_VALUE = 18;
    public static final int SET_PERMITTED_ACCESSIBILITY_SERVICES_VALUE = 28;
    public static final int SET_PERMITTED_INPUT_METHODS_VALUE = 27;
    public static final int SET_PROFILE_NAME_VALUE = 40;
    public static final int SET_SCREEN_CAPTURE_DISABLED_VALUE = 29;
    public static final int SET_SECURE_SETTING_VALUE = 14;
    public static final int SET_SECURITY_LOGGING_ENABLED_VALUE = 15;
    public static final int SET_SHORT_SUPPORT_MESSAGE_VALUE = 43;
    public static final int SET_STATUS_BAR_DISABLED_VALUE = 38;
    public static final int SET_SYSTEM_UPDATE_POLICY_VALUE = 50;
    public static final int SET_UNINSTALL_BLOCKED_VALUE = 67;
    public static final int SET_USER_ICON_VALUE = 41;
    public static final int TRANSFER_OWNERSHIP_VALUE = 58;
    public static final int UNINSTALL_CA_CERTS_VALUE = 24;
    public static final int UNINSTALL_PACKAGE_VALUE = 113;
    public static final int WIFI_SERVICE_ADD_NETWORK_SUGGESTIONS_VALUE = 114;
    public static final int WIFI_SERVICE_ADD_OR_UPDATE_NETWORK_VALUE = 115;
    public static final int WIPE_DATA_WITH_REASON_VALUE = 11;
    public static final int WORK_PROFILE_LOCATION_CHANGED_VALUE = 56;
    private static final Internal.EnumLiteMap<EventId> internalValueMap = new Internal.EnumLiteMap<EventId>() {
        /* class android.stats.devicepolicy.EventId.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public EventId findValueByNumber(int number) {
            return EventId.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static EventId valueOf(int value2) {
        return forNumber(value2);
    }

    public static EventId forNumber(int value2) {
        switch (value2) {
            case 1:
                return SET_PASSWORD_QUALITY;
            case 2:
                return SET_PASSWORD_MINIMUM_LENGTH;
            case 3:
                return SET_PASSWORD_MINIMUM_NUMERIC;
            case 4:
                return SET_PASSWORD_MINIMUM_NON_LETTER;
            case 5:
                return SET_PASSWORD_MINIMUM_LETTERS;
            case 6:
                return SET_PASSWORD_MINIMUM_LOWER_CASE;
            case 7:
                return SET_PASSWORD_MINIMUM_UPPER_CASE;
            case 8:
                return SET_PASSWORD_MINIMUM_SYMBOLS;
            case 9:
                return SET_KEYGUARD_DISABLED_FEATURES;
            case 10:
                return LOCK_NOW;
            case 11:
                return WIPE_DATA_WITH_REASON;
            case 12:
                return ADD_USER_RESTRICTION;
            case 13:
                return REMOVE_USER_RESTRICTION;
            case 14:
                return SET_SECURE_SETTING;
            case 15:
                return SET_SECURITY_LOGGING_ENABLED;
            case 16:
                return RETRIEVE_SECURITY_LOGS;
            case 17:
                return RETRIEVE_PRE_REBOOT_SECURITY_LOGS;
            case 18:
                return SET_PERMISSION_POLICY;
            case 19:
                return SET_PERMISSION_GRANT_STATE;
            case 20:
                return INSTALL_KEY_PAIR;
            case 21:
                return INSTALL_CA_CERT;
            case 22:
                return CHOOSE_PRIVATE_KEY_ALIAS;
            case 23:
                return REMOVE_KEY_PAIR;
            case 24:
                return UNINSTALL_CA_CERTS;
            case 25:
                return SET_CERT_INSTALLER_PACKAGE;
            case 26:
                return SET_ALWAYS_ON_VPN_PACKAGE;
            case 27:
                return SET_PERMITTED_INPUT_METHODS;
            case 28:
                return SET_PERMITTED_ACCESSIBILITY_SERVICES;
            case 29:
                return SET_SCREEN_CAPTURE_DISABLED;
            case 30:
                return SET_CAMERA_DISABLED;
            case 31:
                return QUERY_SUMMARY_FOR_USER;
            case 32:
                return QUERY_SUMMARY;
            case 33:
                return QUERY_DETAILS;
            case 34:
                return REBOOT;
            case 35:
                return SET_MASTER_VOLUME_MUTED;
            case 36:
                return SET_AUTO_TIME_REQUIRED;
            case 37:
                return SET_KEYGUARD_DISABLED;
            case 38:
                return SET_STATUS_BAR_DISABLED;
            case 39:
                return SET_ORGANIZATION_COLOR;
            case 40:
                return SET_PROFILE_NAME;
            case 41:
                return SET_USER_ICON;
            case 42:
                return SET_DEVICE_OWNER_LOCK_SCREEN_INFO;
            case 43:
                return SET_SHORT_SUPPORT_MESSAGE;
            case 44:
                return SET_LONG_SUPPORT_MESSAGE;
            case 45:
                return SET_CROSS_PROFILE_CONTACTS_SEARCH_DISABLED;
            case 46:
                return SET_CROSS_PROFILE_CALLER_ID_DISABLED;
            case 47:
                return SET_BLUETOOTH_CONTACT_SHARING_DISABLED;
            case 48:
                return ADD_CROSS_PROFILE_INTENT_FILTER;
            case 49:
                return ADD_CROSS_PROFILE_WIDGET_PROVIDER;
            case 50:
                return SET_SYSTEM_UPDATE_POLICY;
            case 51:
                return SET_LOCKTASK_MODE_ENABLED;
            case 52:
                return ADD_PERSISTENT_PREFERRED_ACTIVITY;
            case 53:
                return REQUEST_BUGREPORT;
            case 54:
                return GET_WIFI_MAC_ADDRESS;
            case 55:
                return REQUEST_QUIET_MODE_ENABLED;
            case 56:
                return WORK_PROFILE_LOCATION_CHANGED;
            case 57:
                return DO_USER_INFO_CLICKED;
            case 58:
                return TRANSFER_OWNERSHIP;
            case 59:
                return GENERATE_KEY_PAIR;
            case 60:
                return SET_KEY_PAIR_CERTIFICATE;
            case 61:
                return SET_KEEP_UNINSTALLED_PACKAGES;
            case 62:
                return SET_APPLICATION_RESTRICTIONS;
            case 63:
                return SET_APPLICATION_HIDDEN;
            case 64:
                return ENABLE_SYSTEM_APP;
            case 65:
                return ENABLE_SYSTEM_APP_WITH_INTENT;
            case 66:
                return INSTALL_EXISTING_PACKAGE;
            case 67:
                return SET_UNINSTALL_BLOCKED;
            case 68:
                return SET_PACKAGES_SUSPENDED;
            case 69:
                return ON_LOCK_TASK_MODE_ENTERING;
            case 70:
                return SET_CROSS_PROFILE_CALENDAR_PACKAGES;
            case 71:
            default:
                return null;
            case 72:
                return GET_USER_PASSWORD_COMPLEXITY_LEVEL;
            case 73:
                return INSTALL_SYSTEM_UPDATE;
            case 74:
                return INSTALL_SYSTEM_UPDATE_ERROR;
            case 75:
                return IS_MANAGED_KIOSK;
            case 76:
                return IS_UNATTENDED_MANAGED_KIOSK;
            case 77:
                return PROVISIONING_MANAGED_PROFILE_ON_FULLY_MANAGED_DEVICE;
            case 78:
                return PROVISIONING_PERSISTENT_DEVICE_OWNER;
            case 79:
                return PROVISIONING_ENTRY_POINT_NFC;
            case 80:
                return PROVISIONING_ENTRY_POINT_QR_CODE;
            case 81:
                return PROVISIONING_ENTRY_POINT_CLOUD_ENROLLMENT;
            case 82:
                return PROVISIONING_ENTRY_POINT_ADB;
            case 83:
                return PROVISIONING_ENTRY_POINT_TRUSTED_SOURCE;
            case 84:
                return PROVISIONING_DPC_PACKAGE_NAME;
            case 85:
                return PROVISIONING_DPC_INSTALLED_BY_PACKAGE;
            case 86:
                return PROVISIONING_PROVISIONING_ACTIVITY_TIME_MS;
            case 87:
                return PROVISIONING_PREPROVISIONING_ACTIVITY_TIME_MS;
            case 88:
                return PROVISIONING_ENCRYPT_DEVICE_ACTIVITY_TIME_MS;
            case 89:
                return PROVISIONING_WEB_ACTIVITY_TIME_MS;
            case 90:
                return PROVISIONING_TRAMPOLINE_ACTIVITY_TIME_MS;
            case 91:
                return PROVISIONING_POST_ENCRYPTION_ACTIVITY_TIME_MS;
            case 92:
                return PROVISIONING_FINALIZATION_ACTIVITY_TIME_MS;
            case 93:
                return PROVISIONING_NETWORK_TYPE;
            case 94:
                return PROVISIONING_ACTION;
            case 95:
                return PROVISIONING_EXTRAS;
            case 96:
                return PROVISIONING_COPY_ACCOUNT_TASK_MS;
            case 97:
                return PROVISIONING_CREATE_PROFILE_TASK_MS;
            case 98:
                return PROVISIONING_START_PROFILE_TASK_MS;
            case 99:
                return PROVISIONING_DOWNLOAD_PACKAGE_TASK_MS;
            case 100:
                return PROVISIONING_INSTALL_PACKAGE_TASK_MS;
            case 101:
                return PROVISIONING_CANCELLED;
            case 102:
                return PROVISIONING_ERROR;
            case 103:
                return PROVISIONING_COPY_ACCOUNT_STATUS;
            case 104:
                return PROVISIONING_TOTAL_TASK_TIME_MS;
            case 105:
                return PROVISIONING_SESSION_STARTED;
            case 106:
                return PROVISIONING_SESSION_COMPLETED;
            case 107:
                return PROVISIONING_TERMS_ACTIVITY_TIME_MS;
            case 108:
                return PROVISIONING_TERMS_COUNT;
            case 109:
                return PROVISIONING_TERMS_READ;
            case 110:
                return SEPARATE_PROFILE_CHALLENGE_CHANGED;
            case 111:
                return SET_GLOBAL_SETTING;
            case 112:
                return INSTALL_PACKAGE;
            case 113:
                return UNINSTALL_PACKAGE;
            case 114:
                return WIFI_SERVICE_ADD_NETWORK_SUGGESTIONS;
            case 115:
                return WIFI_SERVICE_ADD_OR_UPDATE_NETWORK;
            case 116:
                return QUERY_SUMMARY_FOR_DEVICE;
            case 117:
                return REMOVE_CROSS_PROFILE_WIDGET_PROVIDER;
            case 118:
                return ESTABLISH_VPN;
            case 119:
                return SET_NETWORK_LOGGING_ENABLED;
            case 120:
                return RETRIEVE_NETWORK_LOGS;
            case 121:
                return PROVISIONING_PREPARE_TOTAL_TIME_MS;
            case 122:
                return PROVISIONING_PREPARE_STARTED;
            case 123:
                return PROVISIONING_PREPARE_COMPLETED;
            case 124:
                return PROVISIONING_FLOW_TYPE;
            case 125:
                return CROSS_PROFILE_APPS_GET_TARGET_USER_PROFILES;
            case 126:
                return CROSS_PROFILE_APPS_START_ACTIVITY_AS_USER;
        }
    }

    public static Internal.EnumLiteMap<EventId> internalGetValueMap() {
        return internalValueMap;
    }

    private EventId(int value2) {
        this.value = value2;
    }
}
