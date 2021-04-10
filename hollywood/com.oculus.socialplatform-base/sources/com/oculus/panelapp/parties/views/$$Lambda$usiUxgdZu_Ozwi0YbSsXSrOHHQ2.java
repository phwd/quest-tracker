package com.oculus.panelapp.parties.views;

import com.oculus.panelapp.parties.views.actions.PartyAction;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.parties.views.-$$Lambda$usiUxgdZu-_Ozwi0YbSsXSrOHHQ2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$usiUxgdZu_Ozwi0YbSsXSrOHHQ2 implements Predicate {
    public static final /* synthetic */ $$Lambda$usiUxgdZu_Ozwi0YbSsXSrOHHQ2 INSTANCE = new $$Lambda$usiUxgdZu_Ozwi0YbSsXSrOHHQ2();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((PartyAction) obj).isRelevant();
    }
}
