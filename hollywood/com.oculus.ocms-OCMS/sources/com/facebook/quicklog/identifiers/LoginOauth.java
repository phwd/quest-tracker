package com.facebook.quicklog.identifiers;

import androidx.core.view.PointerIconCompat;
import com.facebook.ultralight.UL;

public class LoginOauth {
    public static final int DIALOG_ACCEPT = 987104250;
    public static final int DIALOG_DENY = 987113824;
    public static final int DIALOG_SHOWN = 987110118;
    public static final int FAIL_OTHER = 987115291;
    public static final int FAIL_TIMEOUT = 987112165;
    public static final int HAVE_EXACTLY_ONE_GMAIL = 987115605;
    public static final int HAVE_ONE_GMAIL_MATCHED_CP = 987105778;
    public static final short MODULE_ID = 15062;
    public static final int NO_EMAIL_FETCHED = 987109656;
    public static final int NO_GMAIL_MATCHED = 987111426;
    public static final int NUMBER_OF_GMAILS = 987107107;
    public static final int PERMISSION_DIALOG_SHOWN = 987105325;
    public static final int SUCCESS = 987107859;
    public static final int TOKEN_FAILURE = 987107036;
    public static final int TOKEN_FETCHED = 987112390;

    public static String getMarkerName(int i) {
        switch (i) {
            case PointerIconCompat.TYPE_ZOOM_IN:
                return "LOGIN_OAUTH_DIALOG_ACCEPT";
            case UL.id._UL__ULSEP_java_lang_Boolean_ULSEP_com_facebook_common_build_IsInternalBuild_ULSEP_BINDING_ID /*{ENCODED_INT: 2093}*/:
                return "LOGIN_OAUTH_PERMISSION_DIALOG_SHOWN";
            case 2546:
                return "LOGIN_OAUTH_HAVE_ONE_GMAIL_MATCHED_CP";
            case 3804:
                return "LOGIN_OAUTH_TOKEN_FAILURE";
            case 3875:
                return "LOGIN_OAUTH_NUMBER_OF_GMAILS";
            case 4627:
                return "LOGIN_OAUTH_SUCCESS";
            case 6424:
                return "LOGIN_OAUTH_NO_EMAIL_FETCHED";
            case 6886:
                return "LOGIN_OAUTH_DIALOG_SHOWN";
            case 8194:
                return "LOGIN_OAUTH_NO_GMAIL_MATCHED";
            case 8933:
                return "LOGIN_OAUTH_FAIL_TIMEOUT";
            case 9158:
                return "LOGIN_OAUTH_TOKEN_FETCHED";
            case 10592:
                return "LOGIN_OAUTH_DIALOG_DENY";
            case 12059:
                return "LOGIN_OAUTH_FAIL_OTHER";
            case 12373:
                return "LOGIN_OAUTH_HAVE_EXACTLY_ONE_GMAIL";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
