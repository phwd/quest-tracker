package com.oculus.panelapp.people;

import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.people.-$$Lambda$blQ6FfbpdgNN_WDHngkoRtE0x3A2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$blQ6FfbpdgNN_WDHngkoRtE0x3A2 implements Predicate {
    public static final /* synthetic */ $$Lambda$blQ6FfbpdgNN_WDHngkoRtE0x3A2 INSTANCE = new $$Lambda$blQ6FfbpdgNN_WDHngkoRtE0x3A2();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((PeopleUserAction) obj).isRelevant();
    }
}
