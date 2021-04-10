package com.oculus.panelapp.keyboardv2;

import android.util.Log;

public enum KeyAction {
    UNKNOWN,
    TEXT,
    COMMAND;
    
    private static final String TAG = "KeyAction";

    public static KeyAction getKeyAction(String str) {
        if (str.equalsIgnoreCase("TEXT")) {
            return TEXT;
        }
        if (str.equalsIgnoreCase("COMMAND")) {
            return COMMAND;
        }
        Log.d(TAG, String.format("Unknown KeyAction: %s", str));
        return UNKNOWN;
    }
}
