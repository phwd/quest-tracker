package com.oculus.horizon.api.common.notification;

public class Notification {
    public String id;
    public boolean is_read;
    public String notification_type;
    public final RenderedNotification rendered_notification;

    public class RenderedNotification {
        public final String extra_data;

        public RenderedNotification() {
        }
    }

    public String getExtraData() {
        return this.rendered_notification.extra_data;
    }
}
