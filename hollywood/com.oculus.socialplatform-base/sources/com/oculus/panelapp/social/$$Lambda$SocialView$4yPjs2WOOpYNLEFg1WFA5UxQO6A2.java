package com.oculus.panelapp.social;

import java.util.Comparator;
import java.util.Locale;

/* renamed from: com.oculus.panelapp.social.-$$Lambda$SocialView$4yPjs2WOOpYNLEFg1WFA5UxQO6A2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SocialView$4yPjs2WOOpYNLEFg1WFA5UxQO6A2 implements Comparator {
    public static final /* synthetic */ $$Lambda$SocialView$4yPjs2WOOpYNLEFg1WFA5UxQO6A2 INSTANCE = new $$Lambda$SocialView$4yPjs2WOOpYNLEFg1WFA5UxQO6A2();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((SocialUserAdapterItem) ((SocialAdapterItem) obj)).mUser.mAlias.toLowerCase(Locale.getDefault()).compareTo(((SocialUserAdapterItem) ((SocialAdapterItem) obj2)).mUser.mAlias.toLowerCase(Locale.getDefault()));
    }
}
