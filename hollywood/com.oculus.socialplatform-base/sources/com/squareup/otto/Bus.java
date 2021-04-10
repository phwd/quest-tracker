package com.squareup.otto;

import X.AnonymousClass006;
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
    public final ThreadEnforcer enforcer;
    public final ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>> eventsToDispatch;
    public final Map<Class<?>, Set<Class<?>>> flattenHierarchyCache;
    public final HandlerFinder handlerFinder;
    public final ConcurrentMap<Class<?>, Set<EventHandler>> handlersByType;
    public final String identifier;
    public final ThreadLocal<Boolean> isDispatching;
    public final ConcurrentMap<Class<?>, EventProducer> producersByType;

    public static class EventWithHandler {
        public final Object event;
        public final EventHandler handler;

        public EventWithHandler(Object obj, EventHandler eventHandler) {
            this.event = obj;
            this.handler = eventHandler;
        }
    }

    private Set<Class<?>> getClassesFor(Class<?> cls) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        while (true) {
            linkedList.add(cls);
            while (true) {
                if (linkedList.isEmpty()) {
                    return hashSet;
                }
                Class cls2 = (Class) linkedList.remove(0);
                hashSet.add(cls2);
                cls = cls2.getSuperclass();
                if (cls != null) {
                }
            }
        }
    }

    public void dispatchQueuedEvents() {
        if (!this.isDispatching.get().booleanValue()) {
            this.isDispatching.set(true);
            while (true) {
                try {
                    EventWithHandler poll = this.eventsToDispatch.get().poll();
                    if (poll != null) {
                        EventHandler eventHandler = poll.handler;
                        if (eventHandler.valid) {
                            dispatch(poll.event, eventHandler);
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

    public void enqueueEvent(Object obj, EventHandler eventHandler) {
        this.eventsToDispatch.get().offer(new EventWithHandler(obj, eventHandler));
    }

    public Set<Class<?>> flattenHierarchy(Class<?> cls) {
        Set<Class<?>> set = this.flattenHierarchyCache.get(cls);
        if (set != null) {
            return set;
        }
        Set<Class<?>> classesFor = getClassesFor(cls);
        this.flattenHierarchyCache.put(cls, classesFor);
        return classesFor;
    }

    public Set<EventHandler> getHandlersForEventType(Class<?> cls) {
        return this.handlersByType.get(cls);
    }

    public EventProducer getProducerForEventType(Class<?> cls) {
        return this.producersByType.get(cls);
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
                    StringBuilder sb = new StringBuilder("Producer method for type ");
                    sb.append(cls);
                    sb.append(" found on type ");
                    sb.append(eventProducer.target.getClass());
                    sb.append(", but already registered by type ");
                    sb.append(putIfAbsent2.target.getClass());
                    sb.append(".");
                    throw new IllegalArgumentException(sb.toString());
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
                if (eventProducer2 != null && eventProducer2.valid) {
                    for (EventHandler eventHandler2 : entry.getValue()) {
                        if (!eventProducer2.valid) {
                            break;
                        } else if (eventHandler2.valid) {
                            dispatchProducerResultToHandler(eventHandler2, eventProducer2);
                        }
                    }
                }
            }
            return;
        }
        throw new NullPointerException("Object to register must not be null.");
    }

    public String toString() {
        return AnonymousClass006.A09("[Bus \"", this.identifier, "\"]");
    }

    public void unregister(Object obj) {
        if (obj != null) {
            this.enforcer.enforce(this);
            for (Map.Entry<Class<?>, EventProducer> entry : this.handlerFinder.findAllProducers(obj).entrySet()) {
                Class<?> key = entry.getKey();
                EventProducer producerForEventType = getProducerForEventType(key);
                EventProducer value = entry.getValue();
                if (value == null || !value.equals(producerForEventType)) {
                    StringBuilder sb = new StringBuilder("Missing event producer for an annotated method. Is ");
                    sb.append(obj.getClass());
                    sb.append(" registered?");
                    throw new IllegalArgumentException(sb.toString());
                }
                this.producersByType.remove(key).valid = false;
            }
            for (Map.Entry<Class<?>, Set<EventHandler>> entry2 : this.handlerFinder.findAllSubscribers(obj).entrySet()) {
                Set<EventHandler> handlersForEventType = getHandlersForEventType(entry2.getKey());
                Set<EventHandler> value2 = entry2.getValue();
                if (handlersForEventType == null || !handlersForEventType.containsAll(value2)) {
                    StringBuilder sb2 = new StringBuilder("Missing event handler for an annotated method. Is ");
                    sb2.append(obj.getClass());
                    sb2.append(" registered?");
                    throw new IllegalArgumentException(sb2.toString());
                }
                for (EventHandler eventHandler : handlersForEventType) {
                    if (value2.contains(eventHandler)) {
                        eventHandler.valid = false;
                    }
                }
                handlersForEventType.removeAll(value2);
            }
            return;
        }
        throw new NullPointerException("Object to unregister must not be null.");
    }

    private void dispatchProducerResultToHandler(EventHandler eventHandler, EventProducer eventProducer) {
        try {
            Object produceEvent = eventProducer.produceEvent();
            if (produceEvent != null) {
                dispatch(produceEvent, eventHandler);
            }
        } catch (InvocationTargetException e) {
            StringBuilder sb = new StringBuilder("Producer ");
            sb.append(eventProducer);
            sb.append(" threw an exception.");
            throwRuntimeException(sb.toString(), e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static void throwRuntimeException(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(AnonymousClass006.A09(str, ": ", cause.getMessage()), cause);
        }
        throw new RuntimeException(AnonymousClass006.A09(str, ": ", invocationTargetException.getMessage()), invocationTargetException);
    }

    public void dispatch(Object obj, EventHandler eventHandler) {
        try {
            eventHandler.handleEvent(obj);
        } catch (InvocationTargetException e) {
            StringBuilder sb = new StringBuilder("Could not dispatch event: ");
            sb.append(obj.getClass());
            sb.append(" to handler ");
            sb.append(eventHandler);
            throwRuntimeException(sb.toString(), e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Bus() {
        this("default");
    }

    public Bus(ThreadEnforcer threadEnforcer) {
        this(threadEnforcer, "default");
    }

    public Bus(ThreadEnforcer threadEnforcer, String str) {
        this(threadEnforcer, str, HandlerFinder.ANNOTATED);
    }

    public Bus(ThreadEnforcer threadEnforcer, String str, HandlerFinder handlerFinder2) {
        this.handlersByType = new ConcurrentHashMap();
        this.producersByType = new ConcurrentHashMap();
        this.eventsToDispatch = new ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>>() {
            /* class com.squareup.otto.Bus.AnonymousClass1 */

            @Override // java.lang.ThreadLocal
            public ConcurrentLinkedQueue<EventWithHandler> initialValue() {
                return new ConcurrentLinkedQueue<>();
            }
        };
        this.isDispatching = new ThreadLocal<Boolean>() {
            /* class com.squareup.otto.Bus.AnonymousClass2 */

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

    public Bus(String str) {
        this(ThreadEnforcer.MAIN, str);
    }
}
