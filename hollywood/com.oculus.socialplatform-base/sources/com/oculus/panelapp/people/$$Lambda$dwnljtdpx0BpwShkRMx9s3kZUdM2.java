package com.oculus.panelapp.people;

import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.people.-$$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2 implements Predicate {
    public static final /* synthetic */ $$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2 INSTANCE = new $$Lambda$dwnljtdpx0BpwShkRMx9s3kZUdM2();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((PeopleUserAction) obj).isRelevant();
    }
}
