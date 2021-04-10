package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;

public class SubscriberExceptionContext {
    private final Object event;
    private final EventBus eventBus;
    private final Object subscriber;
    private final Method subscriberMethod;

    SubscriberExceptionContext(EventBus eventBus2, Object event2, Object subscriber2, Method subscriberMethod2) {
        this.eventBus = (EventBus) Preconditions.checkNotNull(eventBus2);
        this.event = Preconditions.checkNotNull(event2);
        this.subscriber = Preconditions.checkNotNull(subscriber2);
        this.subscriberMethod = (Method) Preconditions.checkNotNull(subscriberMethod2);
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public Object getEvent() {
        return this.event;
    }

    public Object getSubscriber() {
        return this.subscriber;
    }

    public Method getSubscriberMethod() {
        return this.subscriberMethod;
    }
}
