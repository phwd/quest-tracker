package com.oculus.notifications;

public enum NotificationsDisplayDuration {
    DEFAULT(5),
    SHORT(3),
    LONG(7);
    
    public final int length;

    private NotificationsDisplayDuration(int i) {
        this.length = i;
    }

    public static NotificationsDisplayDuration fromName(String str) {
        NotificationsDisplayDuration[] values = values();
        for (NotificationsDisplayDuration notificationsDisplayDuration : values) {
            if (notificationsDisplayDuration.name().equalsIgnoreCase(str)) {
                return notificationsDisplayDuration;
            }
        }
        return DEFAULT;
    }
}
