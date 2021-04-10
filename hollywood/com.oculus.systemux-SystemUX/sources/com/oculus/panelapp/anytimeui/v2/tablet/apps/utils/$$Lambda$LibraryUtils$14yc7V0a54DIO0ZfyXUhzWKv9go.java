package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.library.model.App;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$14yc7V0a54DIO0ZfyXUhzWKv9go  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$14yc7V0a54DIO0ZfyXUhzWKv9go implements Predicate {
    public static final /* synthetic */ $$Lambda$LibraryUtils$14yc7V0a54DIO0ZfyXUhzWKv9go INSTANCE = new $$Lambda$LibraryUtils$14yc7V0a54DIO0ZfyXUhzWKv9go();

    private /* synthetic */ $$Lambda$LibraryUtils$14yc7V0a54DIO0ZfyXUhzWKv9go() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return LibraryUtils.lambda$excludeSystemApps$152((App) obj);
    }
}
