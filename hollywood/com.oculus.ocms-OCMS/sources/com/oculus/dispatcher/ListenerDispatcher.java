package com.oculus.dispatcher;

import com.google.common.collect.MapMaker;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListenerDispatcher<LISTENER> {
    private final Map<LISTENER, Boolean> mDynamicListeners = new MapMaker().weakKeys().makeMap();
    private final Set<LISTENER> mStaticListeners;
    private final Set<LISTENER> mStrongListeners = new HashSet();

    public ListenerDispatcher(Set<LISTENER> set) {
        this.mStaticListeners = set;
    }

    public void addListener(LISTENER listener) {
        this.mDynamicListeners.put(listener, Boolean.TRUE);
    }

    public void addStrongListener(LISTENER listener) {
        this.mStrongListeners.add(listener);
    }

    public void removeListener(LISTENER listener) {
        this.mDynamicListeners.remove(listener);
        this.mStrongListeners.remove(listener);
    }

    public Set<LISTENER> getListeners() {
        return Sets.union(this.mStaticListeners, Sets.union(this.mDynamicListeners.keySet(), this.mStrongListeners));
    }
}
