package com.oculus.panelapp.social;

import com.oculus.panelapp.social.actions.SocialUserAction;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.social.-$$Lambda$UserCardViewHolder$_p17d8J4HtpoFDlBg5RoiGCBwj02  reason: invalid class name */
public final /* synthetic */ class $$Lambda$UserCardViewHolder$_p17d8J4HtpoFDlBg5RoiGCBwj02 implements Predicate {
    public static final /* synthetic */ $$Lambda$UserCardViewHolder$_p17d8J4HtpoFDlBg5RoiGCBwj02 INSTANCE = new $$Lambda$UserCardViewHolder$_p17d8J4HtpoFDlBg5RoiGCBwj02();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((SocialUserAction) obj).isRelevant();
    }
}
