package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import java.util.Comparator;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$GvheKIp0vySKhcJabvVu8tNJ4hg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$GvheKIp0vySKhcJabvVu8tNJ4hg implements Comparator {
    public static final /* synthetic */ $$Lambda$LibraryUtils$GvheKIp0vySKhcJabvVu8tNJ4hg INSTANCE = new $$Lambda$LibraryUtils$GvheKIp0vySKhcJabvVu8tNJ4hg();

    private /* synthetic */ $$Lambda$LibraryUtils$GvheKIp0vySKhcJabvVu8tNJ4hg() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((UnknownSource) obj).getApplicationName().compareTo(((UnknownSource) obj2).getApplicationName());
    }
}
