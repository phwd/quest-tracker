package com.oculus.panelapp.keyboardv2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum InputMethodEditorAction {
    NONE,
    TEXT,
    COMPOSE,
    COMMAND;
    
    private static final Map<String, InputMethodEditorAction> stringToEnum;

    static {
        HashMap hashMap = new HashMap();
        InputMethodEditorAction[] values = values();
        for (InputMethodEditorAction inputMethodEditorAction : values) {
            hashMap.put(inputMethodEditorAction.name(), inputMethodEditorAction);
        }
        stringToEnum = Collections.unmodifiableMap(hashMap);
    }

    public static InputMethodEditorAction get(String str) {
        InputMethodEditorAction inputMethodEditorAction = stringToEnum.get(str);
        return inputMethodEditorAction != null ? inputMethodEditorAction : NONE;
    }
}
