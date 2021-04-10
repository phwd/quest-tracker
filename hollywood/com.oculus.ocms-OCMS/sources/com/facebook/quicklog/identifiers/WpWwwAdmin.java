package com.facebook.quicklog.identifiers;

public class WpWwwAdmin {
    public static final int CONTENT_MODERATOR_ACTION_APPROVE_CONTENT = 52887569;
    public static final int CONTENT_MODERATOR_ACTION_DEACTIVATE_USER = 52887571;
    public static final int CONTENT_MODERATOR_ACTION_DELETE_CONTENT = 52887570;
    public static final int CONTENT_MODERATOR_ACTION_QUARANTINE_CONTENT = 52887572;
    public static final int CONTENT_MODERATOR_ACTION_UNQUARANTINE_CONTENT = 52887573;
    public static final int DOWNGRADE_COWORKER_TO_LIMITED_ACCOUNT_SINGLE = 52898380;
    public static final short MODULE_ID = 807;

    public static String getMarkerName(int i) {
        if (i == 10828) {
            return "WP_WWW_ADMIN_DOWNGRADE_COWORKER_TO_LIMITED_ACCOUNT_SINGLE";
        }
        switch (i) {
            case 17:
                return "WP_WWW_ADMIN_CONTENT_MODERATOR_ACTION_APPROVE_CONTENT";
            case 18:
                return "WP_WWW_ADMIN_CONTENT_MODERATOR_ACTION_DELETE_CONTENT";
            case 19:
                return "WP_WWW_ADMIN_CONTENT_MODERATOR_ACTION_DEACTIVATE_USER";
            case 20:
                return "WP_WWW_ADMIN_CONTENT_MODERATOR_ACTION_QUARANTINE_CONTENT";
            case 21:
                return "WP_WWW_ADMIN_CONTENT_MODERATOR_ACTION_UNQUARANTINE_CONTENT";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
