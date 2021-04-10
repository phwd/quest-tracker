package com.oculus.panelapp.keyboardv2;

public enum DictationModeAutoTriggerState {
    OFF("OFF"),
    ON("ON"),
    ON_WITH_AUTO_SUBMIT("ON_WITH_AUTO_SUBMIT");
    
    private static final String TAG = "DictationModeAutoTriggerState";
    private final String stateName;

    private DictationModeAutoTriggerState(String str) {
        this.stateName = str;
    }

    public String getStateName() {
        return this.stateName;
    }

    public static DictationModeAutoTriggerState getStateFromString(String str) {
        if (str == null) {
            return OFF;
        }
        DictationModeAutoTriggerState[] values = values();
        for (DictationModeAutoTriggerState dictationModeAutoTriggerState : values) {
            if (str.equalsIgnoreCase(dictationModeAutoTriggerState.stateName)) {
                return dictationModeAutoTriggerState;
            }
        }
        return OFF;
    }
}
