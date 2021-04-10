package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.text.TextUtils;
import com.oculus.library.model.App;
import java.util.function.Predicate;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$dkTR5_hwmt2tC4eKgX3VEhoQVKw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$dkTR5_hwmt2tC4eKgX3VEhoQVKw implements Predicate {
    public static final /* synthetic */ $$Lambda$LibraryUtils$dkTR5_hwmt2tC4eKgX3VEhoQVKw INSTANCE = new $$Lambda$LibraryUtils$dkTR5_hwmt2tC4eKgX3VEhoQVKw();

    private /* synthetic */ $$Lambda$LibraryUtils$dkTR5_hwmt2tC4eKgX3VEhoQVKw() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return TextUtils.isEmpty(((App) obj).isDemoOf);
    }
}
