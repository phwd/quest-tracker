package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.library.model.App;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$CzqYOlBvi-9qKY3QC2f6RQ_AhnE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$CzqYOlBvi9qKY3QC2f6RQ_AhnE implements Predicate {
    public static final /* synthetic */ $$Lambda$LibraryUtils$CzqYOlBvi9qKY3QC2f6RQ_AhnE INSTANCE = new $$Lambda$LibraryUtils$CzqYOlBvi9qKY3QC2f6RQ_AhnE();

    private /* synthetic */ $$Lambda$LibraryUtils$CzqYOlBvi9qKY3QC2f6RQ_AhnE() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return LibraryUtils.OCULUS_APP_PACKAGE_NAMES.contains(((App) obj).packageName);
    }
}
