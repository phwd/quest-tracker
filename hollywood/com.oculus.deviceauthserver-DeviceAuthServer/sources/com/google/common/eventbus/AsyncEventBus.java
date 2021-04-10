package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

@Beta
public class AsyncEventBus extends EventBus {
    private final ConcurrentLinkedQueue<EventBus.EventWithSubscriber> eventsToDispatch = new ConcurrentLinkedQueue<>();
    private final Executor executor;

    public AsyncEventBus(String identifier, Executor executor2) {
        super(identifier);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    public AsyncEventBus(Executor executor2, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(subscriberExceptionHandler);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    public AsyncEventBus(Executor executor2) {
        super("default");
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.eventbus.EventBus
    public void enqueueEvent(Object event, EventSubscriber subscriber) {
        this.eventsToDispatch.offer(new EventBus.EventWithSubscriber(event, subscriber));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.eventbus.EventBus
    public void dispatchQueuedEvents() {
        while (true) {
            EventBus.EventWithSubscriber eventWithSubscriber = this.eventsToDispatch.poll();
            if (eventWithSubscriber != null) {
                dispatch(eventWithSubscriber.event, eventWithSubscriber.subscriber);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.eventbus.EventBus
    public void dispatch(final Object event, final EventSubscriber subscriber) {
        Preconditions.checkNotNull(event);
        Preconditions.checkNotNull(subscriber);
        this.executor.execute(new Runnable() {
            /* class com.google.common.eventbus.AsyncEventBus.AnonymousClass1 */

            public void run() {
                AsyncEventBus.super.dispatch(event, subscriber);
            }
        });
    }
}
