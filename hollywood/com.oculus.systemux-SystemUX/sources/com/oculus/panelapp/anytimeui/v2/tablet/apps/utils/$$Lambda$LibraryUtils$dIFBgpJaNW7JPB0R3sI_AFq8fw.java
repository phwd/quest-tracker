package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import java.util.Comparator;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$dIFBgpJaNW7JPB0R3sI_AFq-8fw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$dIFBgpJaNW7JPB0R3sI_AFq8fw implements Comparator {
    public static final /* synthetic */ $$Lambda$LibraryUtils$dIFBgpJaNW7JPB0R3sI_AFq8fw INSTANCE = new $$Lambda$LibraryUtils$dIFBgpJaNW7JPB0R3sI_AFq8fw();

    private /* synthetic */ $$Lambda$LibraryUtils$dIFBgpJaNW7JPB0R3sI_AFq8fw() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Long.compare(((UnknownSource) obj2).getLastModified(), ((UnknownSource) obj).getLastModified());
    }
}
