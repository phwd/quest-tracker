package com.oculus.panelapp.social;

import java.util.Comparator;
import java.util.Locale;

/* renamed from: com.oculus.panelapp.social.-$$Lambda$SocialView$OlQT0MWCm9aZCQFAWElP0zCKjqg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SocialView$OlQT0MWCm9aZCQFAWElP0zCKjqg implements Comparator {
    public static final /* synthetic */ $$Lambda$SocialView$OlQT0MWCm9aZCQFAWElP0zCKjqg INSTANCE = new $$Lambda$SocialView$OlQT0MWCm9aZCQFAWElP0zCKjqg();

    private /* synthetic */ $$Lambda$SocialView$OlQT0MWCm9aZCQFAWElP0zCKjqg() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((SocialUserAdapterItem) ((SocialAdapterItem) obj)).getUser().getAlias().toLowerCase(Locale.getDefault()).compareTo(((SocialUserAdapterItem) ((SocialAdapterItem) obj2)).getUser().getAlias().toLowerCase(Locale.getDefault()));
    }
}
