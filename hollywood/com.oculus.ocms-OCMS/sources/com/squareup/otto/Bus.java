package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Bus {
    public static final String DEFAULT_IDENTIFIER = "default";
    private final ThreadEnforcer enforcer;
    private final ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>> eventsToDispatch;
    private final Map<Class<?>, Set<Class<?>>> flattenHierarchyCache;
    private final HandlerFinder handlerFinder;
    private final ConcurrentMap<Class<?>, Set<EventHandler>> handlersByType;
    private final String identifier;
    private final ThreadLocal<Boolean> isDispatching;
    private final ConcurrentMap<Class<?>, EventProducer> producersByType;

    public Bus() {
        this(DEFAULT_IDENTIFIER);
    }

    public Bus(String str) {
        this(ThreadEnforcer.MAIN, str);
    }

    public Bus(ThreadEnforcer threadEnforcer) {
        this(threadEnforcer, DEFAULT_IDENTIFIER);
    }

    public Bus(ThreadEnforcer threadEnforcer, String str) {
        this(threadEnforcer, str, HandlerFinder.ANNOTATED);
    }

    Bus(ThreadEnforcer threadEnforcer, String str, HandlerFinder handlerFinder2) {
        this.handlersByType = new ConcurrentHashMap();
        this.producersByType = new ConcurrentHashMap();
        this.eventsToDispatch = new ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>>() {
            /* class com.squareup.otto.Bus.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public ConcurrentLinkedQueue<EventWithHandler> initialValue() {
                return new ConcurrentLinkedQueue<>();
            }
        };
        this.isDispatching = new ThreadLocal<Boolean>() {
            /* class com.squareup.otto.Bus.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Boolean initialValue() {
                return false;
            }
        };
        this.flattenHierarchyCache = new HashMap();
        this.enforcer = threadEnforcer;
        this.identifier = str;
        this.handlerFinder = handlerFinder2;
    }

    public String toString() {
        return "[Bus \"" + this.identifier + "\"]";
    }

    public void register(Object obj) {
        Set<EventHandler> putIfAbsent;
        if (obj != null) {
            this.enforcer.enforce(this);
            Map<Class<?>, EventProducer> findAllProducers = this.handlerFinder.findAllProducers(obj);
            for (Class<?> cls : findAllProducers.keySet()) {
                EventProducer eventProducer = findAllProducers.get(cls);
                EventProducer putIfAbsent2 = this.producersByType.putIfAbsent(cls, eventProducer);
                if (putIfAbsent2 == null) {
                    Set<EventHandler> set = this.handlersByType.get(cls);
                    if (set != null && !set.isEmpty()) {
                        for (EventHandler eventHandler : set) {
                            dispatchProducerResultToHandler(eventHandler, eventProducer);
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Producer method for type " + cls + " found on type " + eventProducer.target.getClass() + ", but already registered by type " + putIfAbsent2.target.getClass() + ".");
                }
            }
            Map<Class<?>, Set<EventHandler>> findAllSubscribers = this.handlerFinder.findAllSubscribers(obj);
            for (Class<?> cls2 : findAllSubscribers.keySet()) {
                Set<EventHandler> set2 = this.handlersByType.get(cls2);
                if (set2 == null && (putIfAbsent = this.handlersByType.putIfAbsent(cls2, (set2 = new CopyOnWriteArraySet<>()))) != null) {
                    set2 = putIfAbsent;
                }
                set2.addAll(findAllSubscribers.get(cls2));
            }
            for (Map.Entry<Class<?>, Set<EventHandler>> entry : findAllSubscribers.entrySet()) {
                EventProducer eventProducer2 = this.producersByType.get(entry.getKey());
                if (eventProducer2 != null && eventProducer2.isValid()) {
                    for (EventHandler eventHandler2 : entry.getValue()) {
                        if (!eventProducer2.isValid()) {
                            break;
                        } else if (eventHandler2.isValid()) {
                            dispatchProducerResultToHandler(eventHandler2, eventProducer2);
                        }
                    }
                }
            }
            return;
        }
        throw new NullPointerException("Object to register must not be null.");
    }

    private void dispatchProducerResultToHandler(EventHandler eventHandler, EventProducer eventProducer) {
        Object obj;
        try {
            obj = eventProducer.produceEvent();
        } catch (InvocationTargetException e) {
            throwRuntimeException("Producer " + eventProducer + " threw an exception.", e);
            obj = null;
        }
        if (obj != null) {
            dispatch(obj, eventHandler);
        }
    }

    public void unregister(Object obj) {
        if (obj != null) {
            this.enforcer.enforce(this);
            for (Map.Entry<Class<?>, EventProducer> entry : this.handlerFinder.findAllProducers(obj).entrySet()) {
                Class<?> key = entry.getKey();
                EventProducer producerForEventType = getProducerForEventType(key);
                EventProducer value = entry.getValue();
                if (value == null || !value.equals(producerForEventType)) {
                    throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + obj.getClass() + " registered?");
                }
                this.producersByType.remove(key).invalidate();
            }
            for (Map.Entry<Class<?>, Set<EventHandler>> entry2 : this.handlerFinder.findAllSubscribers(obj).entrySet()) {
                Set<EventHandler> handlersForEventType = getHandlersForEventType(entry2.getKey());
                Set<EventHandler> value2 = entry2.getValue();
                if (handlersForEventType == null || !handlersForEventType.containsAll(value2)) {
                    throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + obj.getClass() + " registered?");
                }
                for (EventHandler eventHandler : handlersForEventType) {
                    if (value2.contains(eventHandler)) {
                        eventHandler.invalidate();
                    }
                }
                handlersForEventType.removeAll(value2);
            }
            return;
        }
        throw new NullPointerException("Object to unregister must not be null.");
    }

    public void post(Object obj) {
        if (obj != null) {
            this.enforcer.enforce(this);
            boolean z = false;
            for (Class<?> cls : flattenHierarchy(obj.getClass())) {
                Set<EventHandler> handlersForEventType = getHandlersForEventType(cls);
                if (handlersForEventType != null && !handlersForEventType.isEmpty()) {
                    z = true;
                    for (EventHandler eventHandler : handlersForEventType) {
                        enqueueEvent(obj, eventHandler);
                    }
                }
            }
            if (!z && !(obj instanceof DeadEvent)) {
                post(new DeadEvent(this, obj));
            }
            dispatchQueuedEvents();
            return;
        }
        throw new NullPointerException("Event to post must not be null.");
    }

    /* access modifiers changed from: protected */
    public void enqueueEvent(Object obj, EventHandler eventHandler) {
        this.eventsToDispatch.get().offer(new EventWithHandler(obj, eventHandler));
    }

    /* access modifiers changed from: protected */
    public void dispatchQueuedEvents() {
        if (!this.isDispatching.get().booleanValue()) {
            this.isDispatching.set(true);
            while (true) {
                try {
                    EventWithHandler poll = this.eventsToDispatch.get().poll();
                    if (poll != null) {
                        if (poll.handler.isValid()) {
                            dispatch(poll.event, poll.handler);
                        }
                    } else {
                        return;
                    }
                } finally {
                    this.isDispatching.set(false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatch(Object obj, EventHandler eventHandler) {
        try {
            eventHandler.handleEvent(obj);
        } catch (InvocationTargetException e) {
            throwRuntimeException("Could not dispatch event: " + obj.getClass() + " to handler " + eventHandler, e);
        }
    }

    /* access modifiers changed from: package-private */
    public EventProducer getProducerForEventType(Class<?> cls) {
        return this.producersByType.get(cls);
    }

    /* access modifiers changed from: package-private */
    public Set<EventHandler> getHandlersForEventType(Class<?> cls) {
        return this.handlersByType.get(cls);
    }

    /* access modifiers changed from: package-private */
    public Set<Class<?>> flattenHierarchy(Class<?> cls) {
        Set<Class<?>> set = this.flattenHierarchyCache.get(cls);
        if (set != null) {
            return set;
        }
        Set<Class<?>> classesFor = getClassesFor(cls);
        this.flattenHierarchyCache.put(cls, classesFor);
        return classesFor;
    }

    private Set<Class<?>> getClassesFor(Class<?> cls) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        linkedList.add(cls);
        while (!linkedList.isEmpty()) {
            Class cls2 = (Class) linkedList.remove(0);
            hashSet.add(cls2);
            Class superclass = cls2.getSuperclass();
            if (superclass != null) {
                linkedList.add(superclass);
            }
        }
        return hashSet;
    }

    private static void throwRuntimeException(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(str + ": " + cause.getMessage(), cause);
        }
        throw new RuntimeException(str + ": " + invocationTargetException.getMessage(), invocationTargetException);
    }

    /* access modifiers changed from: package-private */
    public static class EventWithHandler {
        final Object event;
        final EventHandler handler;

        public EventWithHandler(Object obj, EventHandler eventHandler) {
            this.event = obj;
            this.handler = eventHandler;
        }
    }
}
