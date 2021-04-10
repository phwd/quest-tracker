package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.notification.Notification;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class NotificationResponse {
    public SearchResults notifications;

    public static class SearchResults {
        public long count;
        public List<Notification> nodes;
    }
}
