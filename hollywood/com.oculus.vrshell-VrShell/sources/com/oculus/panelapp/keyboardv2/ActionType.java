package com.oculus.panelapp.keyboardv2;

import android.util.Log;

public enum ActionType {
    UNKNOWN,
    GO,
    NEWLINE,
    SEARCH,
    NEXT,
    SEND,
    DONE;
    
    private static final String TAG = "ActionType";

    public static ActionType getActionType(String str) {
        if (str.equalsIgnoreCase("GO") || str.isEmpty()) {
            return GO;
        }
        if (str.equalsIgnoreCase("NEWLINE")) {
            return NEWLINE;
        }
        if (str.equalsIgnoreCase("SEARCH")) {
            return SEARCH;
        }
        if (str.equalsIgnoreCase("NEXT")) {
            return NEXT;
        }
        if (str.equalsIgnoreCase("SEND")) {
            return SEND;
        }
        if (str.equalsIgnoreCase("DONE")) {
            return DONE;
        }
        Log.d(TAG, String.format("Unknown ActionType: %s", str));
        return GO;
    }

    public String toString() {
        return name().toLowerCase();
    }
}
