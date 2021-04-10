package com.oculus.panelapp.anytimeui.v2.tablet.apps.models;

import android.content.Context;
import android.util.SparseArray;
import com.oculus.panelapp.anytimeui.R;
import java.util.HashMap;
import java.util.Map;

public enum LibraryFilter {
    ALL(0, R.string.anytime_tablet_library_filter_all, false),
    OCULUS_APPS(1, R.string.anytime_tablet_library_filter_oculus_apps, false),
    INSTALLED(2, R.string.anytime_tablet_library_filter_installed, false),
    UPDATES(3, R.string.anytime_tablet_library_filter_updates, false),
    NOT_INSTALLED(4, R.string.anytime_tablet_library_filter_not_installed, false),
    DEMOS(5, R.string.anytime_tablet_library_filter_demos, false),
    TUTORIALS(6, R.string.anytime_tablet_library_filter_tutorials, false),
    SHARED(7, R.string.anytime_tablet_library_filter_shared, false),
    UNKNOWN_SOURCES(8, R.string.anytime_tablet_library_filter_unknown_sources, true),
    PROTOTYPES(9, R.string.anytime_tablet_library_filter_prototype, false);
    
    private static final SparseArray<LibraryFilter> LIBRARY_FILTER = new SparseArray<>();
    private static final Map<LibraryFilter, Boolean> LIBRARY_FILTER_ALERT_MAP = new HashMap();
    private final int mId;
    private final boolean mShouldAlert;
    private final int mStringId;

    static {
        LibraryFilter[] values = values();
        for (LibraryFilter libraryFilter : values) {
            LIBRARY_FILTER_ALERT_MAP.put(libraryFilter, Boolean.valueOf(libraryFilter.getShouldAlert()));
            LIBRARY_FILTER.put(libraryFilter.getId(), libraryFilter);
        }
    }

    private LibraryFilter(int i, int i2, boolean z) {
        this.mId = i;
        this.mStringId = i2;
        this.mShouldAlert = z;
    }

    public int getId() {
        return this.mId;
    }

    public boolean getShouldAlert() {
        return this.mShouldAlert;
    }

    public String getLocalizedString(Context context) {
        return context.getString(this.mStringId);
    }

    public static Map<LibraryFilter, Boolean> getAlertMap() {
        return LIBRARY_FILTER_ALERT_MAP;
    }

    public static LibraryFilter getDefault() {
        return ALL;
    }

    public static LibraryFilter valueOf(int i) {
        return LIBRARY_FILTER.get(i);
    }
}
