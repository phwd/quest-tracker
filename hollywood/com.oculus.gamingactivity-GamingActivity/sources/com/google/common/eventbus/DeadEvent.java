package com.google.common.eventbus;

import androidx.core.app.NotificationCompat;
import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

@Beta
public class DeadEvent {
    private final Object event;
    private final Object source;

    public DeadEvent(Object source2, Object event2) {
        this.source = Preconditions.checkNotNull(source2);
        this.event = Preconditions.checkNotNull(event2);
    }

    public Object getSource() {
        return this.source;
    }

    public Object getEvent() {
        return this.event;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("source", this.source).add(NotificationCompat.CATEGORY_EVENT, this.event).toString();
    }
}
