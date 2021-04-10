package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public class EventBus {
    private static final LoadingCache<Class<?>, Set<Class<?>>> flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, Set<Class<?>>>() {
        /* class com.google.common.eventbus.EventBus.AnonymousClass1 */

        public Set<Class<?>> load(Class<?> concreteClass) {
            return TypeToken.of((Class) concreteClass).getTypes().rawTypes();
        }
    });
    private final ThreadLocal<Queue<EventWithSubscriber>> eventsToDispatch;
    private final SubscriberFindingStrategy finder;
    private final ThreadLocal<Boolean> isDispatching;
    private SubscriberExceptionHandler subscriberExceptionHandler;
    private final SetMultimap<Class<?>, EventSubscriber> subscribersByType;
    private final ReadWriteLock subscribersByTypeLock;

    public EventBus() {
        this("default");
    }

    public EventBus(String identifier) {
        this(new LoggingSubscriberExceptionHandler(identifier));
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler2) {
        this.subscribersByType = HashMultimap.create();
        this.subscribersByTypeLock = new ReentrantReadWriteLock();
        this.finder = new AnnotatedSubscriberFinder();
        this.eventsToDispatch = new ThreadLocal<Queue<EventWithSubscriber>>() {
            /* class com.google.common.eventbus.EventBus.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Queue<EventWithSubscriber> initialValue() {
                return new LinkedList();
            }
        };
        this.isDispatching = new ThreadLocal<Boolean>() {
            /* class com.google.common.eventbus.EventBus.AnonymousClass3 */

            /* access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Boolean initialValue() {
                return false;
            }
        };
        this.subscriberExceptionHandler = (SubscriberExceptionHandler) Preconditions.checkNotNull(subscriberExceptionHandler2);
    }

    public void register(Object object) {
        Multimap<Class<?>, EventSubscriber> methodsInListener = this.finder.findAllSubscribers(object);
        this.subscribersByTypeLock.writeLock().lock();
        try {
            this.subscribersByType.putAll(methodsInListener);
        } finally {
            this.subscribersByTypeLock.writeLock().unlock();
        }
    }

    public void unregister(Object object) {
        for (Map.Entry<Class<?>, Collection<EventSubscriber>> entry : this.finder.findAllSubscribers(object).asMap().entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<EventSubscriber> eventMethodsInListener = entry.getValue();
            this.subscribersByTypeLock.writeLock().lock();
            try {
                Set<EventSubscriber> currentSubscribers = this.subscribersByType.get(eventType);
                if (currentSubscribers.containsAll(eventMethodsInListener)) {
                    currentSubscribers.removeAll(eventMethodsInListener);
                } else {
                    throw new IllegalArgumentException("missing event subscriber for an annotated method. Is " + object + " registered?");
                }
            } finally {
                this.subscribersByTypeLock.writeLock().unlock();
            }
        }
    }

    public void post(Object event) {
        boolean dispatched = false;
        for (Class<?> eventType : flattenHierarchy(event.getClass())) {
            this.subscribersByTypeLock.readLock().lock();
            try {
                Set<EventSubscriber> wrappers = this.subscribersByType.get(eventType);
                if (!wrappers.isEmpty()) {
                    dispatched = true;
                    for (EventSubscriber wrapper : wrappers) {
                        enqueueEvent(event, wrapper);
                    }
                }
            } finally {
                this.subscribersByTypeLock.readLock().unlock();
            }
        }
        if (!dispatched && !(event instanceof DeadEvent)) {
            post(new DeadEvent(this, event));
        }
        dispatchQueuedEvents();
    }

    /* access modifiers changed from: package-private */
    public void enqueueEvent(Object event, EventSubscriber subscriber) {
        this.eventsToDispatch.get().offer(new EventWithSubscriber(event, subscriber));
    }

    /* access modifiers changed from: package-private */
    public void dispatchQueuedEvents() {
        if (!this.isDispatching.get().booleanValue()) {
            this.isDispatching.set(true);
            try {
                Queue<EventWithSubscriber> events = this.eventsToDispatch.get();
                while (true) {
                    EventWithSubscriber eventWithSubscriber = events.poll();
                    if (eventWithSubscriber != null) {
                        dispatch(eventWithSubscriber.event, eventWithSubscriber.subscriber);
                    } else {
                        return;
                    }
                }
            } finally {
                this.isDispatching.remove();
                this.eventsToDispatch.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatch(Object event, EventSubscriber wrapper) {
        try {
            wrapper.handleEvent(event);
        } catch (InvocationTargetException e) {
            this.subscriberExceptionHandler.handleException(e.getCause(), new SubscriberExceptionContext(this, event, wrapper.getSubscriber(), wrapper.getMethod()));
        } catch (Throwable t) {
            Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, String.format("Exception %s thrown while handling exception: %s", t, e.getCause()), t);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Set<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        try {
            return flattenHierarchyCache.getUnchecked(concreteClass);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    private static final class LoggingSubscriberExceptionHandler implements SubscriberExceptionHandler {
        private final Logger logger;

        public LoggingSubscriberExceptionHandler(String identifier) {
            this.logger = Logger.getLogger(EventBus.class.getName() + "." + ((String) Preconditions.checkNotNull(identifier)));
        }

        @Override // com.google.common.eventbus.SubscriberExceptionHandler
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            Logger logger2 = this.logger;
            Level level = Level.SEVERE;
            logger2.log(level, "Could not dispatch event: " + context.getSubscriber() + " to " + context.getSubscriberMethod(), exception.getCause());
        }
    }

    /* access modifiers changed from: package-private */
    public static class EventWithSubscriber {
        final Object event;
        final EventSubscriber subscriber;

        public EventWithSubscriber(Object event2, EventSubscriber subscriber2) {
            this.event = Preconditions.checkNotNull(event2);
            this.subscriber = (EventSubscriber) Preconditions.checkNotNull(subscriber2);
        }
    }
}
