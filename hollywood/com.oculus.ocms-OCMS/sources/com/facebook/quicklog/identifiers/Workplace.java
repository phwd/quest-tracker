package com.facebook.quicklog.identifiers;

public class Workplace {
    public static final int APP_CONTENT_LOAD_TTI = 12451844;
    public static final int AT_WORK_ACCOUNTS_CLAIM = 12451862;
    public static final int AT_WORK_ACCOUNTS_CLAIM_INFO = 12451867;
    public static final int AT_WORK_ACCOUNTS_CLAIM_SSO = 12451874;
    public static final int AT_WORK_ACCOUNTS_INFO = 12451863;
    public static final int AT_WORK_ACCOUNTS_SELF_INVITE = 12451861;
    public static final int AT_WORK_ACCOUNTS_SEND_NOTIFICATION = 12451864;
    public static final int AT_WORK_CLAIM_ACCOUNT = 12451857;
    public static final int AT_WORK_COMPANY_CREATION = 12451860;
    public static final int AT_WORK_COMPANY_CREATION_WITH_INVITE = 12451866;
    public static final int AT_WORK_INVITE_CHECK = 12451858;
    public static final int AT_WORK_INVITE_COMPANY_INFO = 12451865;
    public static final int AT_WORK_INVITE_USER_MOBILE = 12459797;
    public static final int AT_WORK_PRE_LOGIN_INFO = 12451855;
    public static final int AT_WORK_SELF_INVITE = 12451856;
    public static final int AT_WORK_SELF_INVITE_SSO = 12451875;
    public static final int COMPOSER_DESTINATION_SCREEN_LOAD = 12451870;
    public static final short MODULE_ID = 190;
    public static final int NAVIGATE_TO_PHONE_NUMBER_SIGNUP_FRAGMENT = 12451876;
    public static final int RICH_TEXT_RENDER_ANDROID = 12451846;

    public static String getMarkerName(int i) {
        if (i == 4) {
            return "WORKPLACE_APP_CONTENT_LOAD_TTI";
        }
        if (i == 6) {
            return "WORKPLACE_RICH_TEXT_RENDER_ANDROID";
        }
        if (i == 30) {
            return "WORKPLACE_COMPOSER_DESTINATION_SCREEN_LOAD";
        }
        if (i == 7957) {
            return "WORKPLACE_AT_WORK_INVITE_USER_MOBILE";
        }
        switch (i) {
            case 15:
                return "WORKPLACE_AT_WORK_PRE_LOGIN_INFO";
            case 16:
                return "WORKPLACE_AT_WORK_SELF_INVITE";
            case 17:
                return "WORKPLACE_AT_WORK_CLAIM_ACCOUNT";
            case 18:
                return "WORKPLACE_AT_WORK_INVITE_CHECK";
            default:
                switch (i) {
                    case 20:
                        return "WORKPLACE_AT_WORK_COMPANY_CREATION";
                    case 21:
                        return "WORKPLACE_AT_WORK_ACCOUNTS_SELF_INVITE";
                    case 22:
                        return "WORKPLACE_AT_WORK_ACCOUNTS_CLAIM";
                    case 23:
                        return "WORKPLACE_AT_WORK_ACCOUNTS_INFO";
                    case 24:
                        return "WORKPLACE_AT_WORK_ACCOUNTS_SEND_NOTIFICATION";
                    case 25:
                        return "WORKPLACE_AT_WORK_INVITE_COMPANY_INFO";
                    case 26:
                        return "WORKPLACE_AT_WORK_COMPANY_CREATION_WITH_INVITE";
                    case 27:
                        return "WORKPLACE_AT_WORK_ACCOUNTS_CLAIM_INFO";
                    default:
                        switch (i) {
                            case 34:
                                return "WORKPLACE_AT_WORK_ACCOUNTS_CLAIM_SSO";
                            case 35:
                                return "WORKPLACE_AT_WORK_SELF_INVITE_SSO";
                            case 36:
                                return "WORKPLACE_NAVIGATE_TO_PHONE_NUMBER_SIGNUP_FRAGMENT";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
