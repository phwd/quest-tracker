package com.oculus.horizon.api.common.notification;

public class Notification {
    public String id;
    public boolean is_read;
    public String notification_type;
    public RenderedNotification rendered_notification;

    public String getExtraData() {
        return this.rendered_notification.extra_data;
    }

    public class RenderedNotification {
        public String extra_data;

        public RenderedNotification() {
        }
    }
}
