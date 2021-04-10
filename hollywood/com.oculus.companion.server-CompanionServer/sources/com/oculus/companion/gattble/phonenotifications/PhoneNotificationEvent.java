package com.oculus.companion.gattble.phonenotifications;

public enum PhoneNotificationEvent {
    PHONE_NOTIFICATIONS_DISABLED(0, "The Phone Notifications Setting is disabled"),
    APPLICATION_DOES_NOT_PASS_FILTER(1, "The incoming notification's source application does not pass the app filters");
    
    private final int code;
    private final String description;

    private PhoneNotificationEvent(int i, String str) {
        this.code = i;
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return "(" + this.code + "): " + this.description;
    }
}
