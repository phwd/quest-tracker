package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.library.model.App;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$JP9fwH88Hp_vzZz-WZC1pD_7pjI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$JP9fwH88Hp_vzZzWZC1pD_7pjI implements Predicate {
    public static final /* synthetic */ $$Lambda$LibraryUtils$JP9fwH88Hp_vzZzWZC1pD_7pjI INSTANCE = new $$Lambda$LibraryUtils$JP9fwH88Hp_vzZzWZC1pD_7pjI();

    private /* synthetic */ $$Lambda$LibraryUtils$JP9fwH88Hp_vzZzWZC1pD_7pjI() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return LibraryUtils.TUTORIAL_PACKAGE_NAMES.contains(((App) obj).packageName);
    }
}
