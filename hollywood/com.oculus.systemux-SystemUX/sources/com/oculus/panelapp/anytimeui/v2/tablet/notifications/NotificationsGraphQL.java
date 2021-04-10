package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import java.util.Locale;

public class NotificationsGraphQL {
    public static String notificationUpdateSeenState(long j, String str) {
        return String.format(Locale.US, "mutation {\n  notification_update_seen_state(input_data: {notification: %d, client_mutation_id: 1, seen_state: %s}) {\n    notification {\n      id \n      xnotif_type \n      seen_state \n    }\n  }\n}", Long.valueOf(j), str);
    }
}
