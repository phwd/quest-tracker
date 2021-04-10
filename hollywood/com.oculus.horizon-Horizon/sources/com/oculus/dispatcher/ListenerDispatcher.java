package com.oculus.dispatcher;

import X.AnonymousClass0dB;
import X.C06980qi;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListenerDispatcher<LISTENER> {
    public final Map<LISTENER, Boolean> mDynamicListeners;
    public final Set<LISTENER> mStaticListeners;
    public final Set<LISTENER> mStrongListeners = new HashSet();

    public final Set<LISTENER> A02() {
        Set<LISTENER> set = this.mStaticListeners;
        Set<LISTENER> keySet = this.mDynamicListeners.keySet();
        Set<LISTENER> set2 = this.mStrongListeners;
        Preconditions.checkNotNull(keySet, "set1");
        Preconditions.checkNotNull(set2, "set2");
        AnonymousClass0dB r1 = new AnonymousClass0dB(keySet, set2);
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(r1, "set2");
        return new AnonymousClass0dB(set, r1);
    }

    public ListenerDispatcher(Set<LISTENER> set) {
        this.mStaticListeners = set;
        C06980qi r1 = new C06980qi();
        r1.A01(MapMakerInternalMap.Strength.WEAK);
        this.mDynamicListeners = r1.A00();
    }
}
