package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import com.oculus.panelapp.anytimeui.R;
import java.util.HashMap;
import java.util.Map;

public enum NotificationsMoreButtonItem {
    REMOVE(R.string.anytime_tablet_notifications_remove);
    
    private static final Map<NotificationsMoreButtonItem, Integer> NOTIFICATIONS_MORE_BUTTON_ITEM_MAP = new HashMap();
    private final int mStringId;

    static {
        NotificationsMoreButtonItem[] values = values();
        for (NotificationsMoreButtonItem notificationsMoreButtonItem : values) {
            NOTIFICATIONS_MORE_BUTTON_ITEM_MAP.put(notificationsMoreButtonItem, Integer.valueOf(notificationsMoreButtonItem.getStringId()));
        }
    }

    private NotificationsMoreButtonItem(int i) {
        this.mStringId = i;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public static Map<NotificationsMoreButtonItem, Integer> getMap() {
        return NOTIFICATIONS_MORE_BUTTON_ITEM_MAP;
    }
}
