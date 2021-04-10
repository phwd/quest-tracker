package com.oculus.panelapp.social;

import com.oculus.panelapp.social.actions.SocialUserAction;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.social.-$$Lambda$UserCardViewHolder$jhfs2b9LmQ2OwUhRw47p-QOqv5Q  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$UserCardViewHolder$jhfs2b9LmQ2OwUhRw47pQOqv5Q implements Predicate {
    public static final /* synthetic */ $$Lambda$UserCardViewHolder$jhfs2b9LmQ2OwUhRw47pQOqv5Q INSTANCE = new $$Lambda$UserCardViewHolder$jhfs2b9LmQ2OwUhRw47pQOqv5Q();

    private /* synthetic */ $$Lambda$UserCardViewHolder$jhfs2b9LmQ2OwUhRw47pQOqv5Q() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((SocialUserAction) obj).isRelevant();
    }
}
