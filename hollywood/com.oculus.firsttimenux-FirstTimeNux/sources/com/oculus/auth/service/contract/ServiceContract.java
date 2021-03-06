package com.oculus.auth.service.contract;

public final class ServiceContract {
    public static final String ACTION_AUTH_WITH_OCULUS_EMAIL_AND_PASSWORD_FOR_ACCOUNT_LINKING = "com.oculus.auth.ACTION_AUTH_WITH_OCULUS_EMAIL_AND_PASSWORD_FOR_ACCOUNT_LINKING";
    public static final String ACTION_ENSURE_DEVICE_OWNERSHIP = "com.oculus.auth.ACTION_ENSURE_DEVICE_OWNERSHIP";
    public static final String ACTION_FB_CHECK_MACHINE_APPROVAL = "com.oculus.auth.ACTION_FB_CHECK_MACHINE_APPROVAL";
    public static final String ACTION_FB_LOGIN = "com.oculus.auth.ACTION_FB_LOGIN";
    public static final String ACTION_FB_MACHINE_APPROVAL_LOGIN = "com.oculus.auth.ACTION_FB_MACHINE_APPROVAL_LOGIN";
    public static final String ACTION_FB_RESEND_SMS = "com.oculus.auth.ACTION_FB_RESEND_SMS";
    public static final String ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE = "com.oculus.auth.ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE";
    public static final String ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS = "com.oculus.auth.ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS";
    public static final String ACTION_FETCH_FB_INFO_FOR_ACCOUNT_LINKING = "com.oculus.auth.ACTION_FETCH_FB_INFO_FOR_ACCOUNT_LINKING";
    public static final String ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS = "com.oculus.auth.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS";
    public static final String ACTION_LINK_FB_ACCOUNT = "com.oculus.auth.ACTION_LINK_FB_ACCOUNT";
    public static final String ACTION_LOGIN = "com.oculus.auth.ACTION_LOGIN";
    public static final String ACTION_LOGIN_API = "com.oculus.auth.ACTION_LOGIN_API";
    public static final String ACTION_LOGIN_WITH_FB_AUTH = "com.oculus.auth.ACTION_LOGIN_WITH_FB_AUTH";
    public static final String ACTION_LOGOUT = "com.oculus.auth.ACTION_LOGOUT";
    public static final String ACTION_RESEND_LOGIN_APPROVAL_CODE = "com.oculus.auth.ACTION_RESEND_LOGIN_APPROVAL_CODE";
    public static final String ACTION_SEND_TWO_FACTOR_CODE = "com.oculus.auth.ACTION_SEND_TWO_FACTOR_CODE";
    public static final String ACTION_STATUS = "com.oculus.auth.ACTION_STATUS";
    public static final String ACTION_VERIFY_LOGIN = "com.oculus.auth.ACTION_VERIFY_LOGIN";
    public static final String ACTION_VERIFY_LOGIN_FOR_ACCOUNT_LINKING = "com.oculus.auth.ACTION_VERIFY_LOGIN_FOR_ACCOUNT_LINKING";
    public static final String ACTION_VERIFY_PIN = "com.oculus.auth.ACTION_VERIFY_PIN";
    public static final String BOUND_SERVICE = "com.oculus.auth.service.AuthService2";
    public static final String BROADCAST_LOGIN = "com.oculus.auth.BROADCAST_LOGIN";
    public static final String BROADCAST_LOGOUT = "com.oculus.auth.BROADCAST_LOGOUT";
    public static final int ERROR_CODE_ACCOUNT_ALREADY_ON_DEVICE = -17;
    public static final int ERROR_CODE_ACCOUNT_CONFLICT = -18;
    public static final int ERROR_CODE_ANOMALOUS_LOGIN = -2;
    public static final int ERROR_CODE_CANNOT_MERGE_ACCOUNTS_BECAUSE_ALREADY_LINKED = -19;
    public static final int ERROR_CODE_DEVICE_ALREADY_CLAIMED = -14;
    public static final int ERROR_CODE_HTTP_ERROR = -6;
    public static final int ERROR_CODE_INVALID_CREDS = -1;
    public static final int ERROR_CODE_INVALID_PARAMS = -12;
    public static final int ERROR_CODE_LIMITED_LOGIN = -3;
    public static final int ERROR_CODE_LOGIN_APPROVALS_INVALID_CODE = -10;
    public static final int ERROR_CODE_LOGIN_APPROVALS_NOT_IN_CHECKPOINT = -9;
    public static final int ERROR_CODE_LOGIN_APPROVALS_RATE_LIMITED = -11;
    public static final int ERROR_CODE_NETWORK_ERROR = -4;
    public static final int ERROR_CODE_NO_DEVICE_IDENTITY = -16;
    public static final int ERROR_CODE_NO_LINKED_ACCOUNT = -13;
    public static final int ERROR_CODE_PASSWORD_DECRYPTION_ERROR = -15;
    public static final int ERROR_CODE_RATE_LIMITED = -5;
    public static final int ERROR_CODE_TWO_FACTOR_LOGIN_REQUIRED = -8;
    public static final int ERROR_CODE_UNKNOWN_ERROR = -7;
    @Deprecated
    public static final int ERROR_CODE_USER_ALREADY_ON_DEVICE = -17;
    public static final String EXTRA_ACCESS_TOKEN = "access_token";
    public static final String EXTRA_ALL_EMAILS = "all_emails";
    public static final String EXTRA_AUTH_TOKEN = "auth_token";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_ENCRYPTION_KEY_PKG = "pwd_enc_key_pkg";
    public static final String EXTRA_ENCRYPTION_KEY_PKG_KEY_ID = "key_id";
    public static final String EXTRA_ENCRYPTION_KEY_PKG_PUBLIC_KEY = "public_key";
    public static final String EXTRA_ENCRYPTION_KEY_PKG_SECONDS_TO_LIVE = "seconds_to_live";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_ERROR_MESSAGE = "error_message";
    public static final String EXTRA_ERROR_SUBCODE = "error_subcode";
    public static final String EXTRA_ERROR_TITLE = "error_title";
    public static final String EXTRA_ERROR_USER_MESSAGE = "error_data";
    public static final String EXTRA_EXISTING_EMAIL_CONFLICT = "existing_email_conflict";
    public static final String EXTRA_FB_ACCESS_TOKEN = "fb_access_token";
    public static final String EXTRA_FB_EMAIL = "facebook_email";
    public static final String EXTRA_FB_LINK_LOGGING_JSON = "fb_link_logging_json";
    public static final String EXTRA_FB_LINK_TOS_VERSION = "fb_link_tos_version";
    public static final String EXTRA_FB_USER_ID = "fb_user_id";
    public static final String EXTRA_FIRST_FACTOR = "first_factor";
    public static final String EXTRA_FOLLOW_UP = "follow_up";
    public static final String EXTRA_IDENTIFIER = "identifier";
    public static final String EXTRA_IS_RELOGIN = "is_relogin";
    public static final String EXTRA_IS_SECURED_ACTION = "secured_action";
    public static final String EXTRA_LOGIN_FIRST_FACTOR = "login_first_factor";
    public static final String EXTRA_LOGOUT_WITHOUT_SERVER = "logout_no_server";
    public static final String EXTRA_MACHINE_APPROVED = "machine_approved";
    public static final String EXTRA_MACHINE_ID = "machine_id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_NONCE = "nonce";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_PIN = "pin";
    public static final String EXTRA_PROFILE_PIC_URI = "profile_pic_uri";
    public static final String EXTRA_RECEIVER = "receiver";
    public static final String EXTRA_RESULT = "result";
    public static final String EXTRA_SESSION_KEY = "sessionKey";
    public static final String EXTRA_SKIP_CLAIMING = "skip_claiming";
    public static final String EXTRA_SUPPORT_URI = "support_uri";
    public static final String EXTRA_TWO_FACTOR_METHODS = "two_factor_methods";
    public static final String EXTRA_TWO_FACTOR_METHOD_ID = "two_factor_method_id";
    public static final String EXTRA_UID = "uid";
    public static final String EXTRA_USER = "user";
    public static final String EXTRA_USERNAME_SUGGESTIONS = "username_suggestions";
    public static final String EXTRA_USER_ID = "user_id";
    public static final String FOLLOW_UP_LINK = "link";
    public static final String PERMISSION_BROADCAST = "com.oculus.horizon.auth";
    public static final int RESULT_CANCELED = 0;
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_OK = -1;
    public static final int SUCCESS_CODE = 1;
}
