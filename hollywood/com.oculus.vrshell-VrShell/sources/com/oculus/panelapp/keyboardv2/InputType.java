package com.oculus.panelapp.keyboardv2;

import android.util.Log;

public enum InputType {
    UNKNOWN,
    TEXT_SIMPLE,
    TEXT_DEFAULT,
    TEXT_URI,
    EMAIL_DEFAULT,
    NUMBER_DEFAULT,
    NUMBER_COMPACT,
    PHONE_DEFAULT,
    TEXT_PHYSICAL,
    PASSWORD;
    
    private static final String TAG = "InputType";

    public static InputType getInputType(String str) {
        if (str.equalsIgnoreCase("TEXT_SIMPLE")) {
            return TEXT_SIMPLE;
        }
        if (str.equalsIgnoreCase("TEXT_DEFAULT") || str.isEmpty()) {
            return TEXT_DEFAULT;
        }
        if (str.equalsIgnoreCase("TEXT_EMAIL")) {
            return TEXT_DEFAULT;
        }
        if (str.equalsIgnoreCase("TEXT_URI")) {
            return TEXT_URI;
        }
        if (str.equalsIgnoreCase("EMAIL_DEFAULT")) {
            return EMAIL_DEFAULT;
        }
        if (str.equalsIgnoreCase("NUMBER_DEFAULT")) {
            return NUMBER_DEFAULT;
        }
        if (str.equalsIgnoreCase("NUMBER_COMPACT")) {
            return NUMBER_COMPACT;
        }
        if (str.equalsIgnoreCase("PHONE_DEFAULT")) {
            return PHONE_DEFAULT;
        }
        if (str.equalsIgnoreCase("TEXT_PHYSICAL")) {
            return TEXT_PHYSICAL;
        }
        if (str.equalsIgnoreCase("PASSWORD")) {
            return PASSWORD;
        }
        Log.d(TAG, String.format("Unknown InputType: %s", str));
        return TEXT_DEFAULT;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static String toString(InputType inputType) {
        switch (inputType) {
            case TEXT_SIMPLE:
                return "text_simple";
            case TEXT_DEFAULT:
            case TEXT_URI:
                return "text_default";
            case EMAIL_DEFAULT:
                return "email_default";
            case NUMBER_DEFAULT:
                return "number_default";
            case NUMBER_COMPACT:
                return "number_compact";
            case PHONE_DEFAULT:
                return "phone_default";
            case TEXT_PHYSICAL:
                return "text_physical";
            case PASSWORD:
                break;
            default:
                Log.d(TAG, String.format("Unknown type: %i", inputType.toString()));
                break;
        }
        return "text_default";
    }
}
