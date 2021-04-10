package com.oculus.panelapp.anytimeui.v2.tablet.apps.models;

import android.content.Context;
import android.util.SparseArray;
import com.oculus.panelapp.anytimeui.R;
import java.util.HashMap;
import java.util.Map;

public enum LibrarySorter {
    MOST_RECENT(0, R.string.anytime_tablet_library_sorter_most_recent),
    LEAST_RECENT(1, R.string.anytime_tablet_library_sorter_least_recent),
    A_TO_Z(2, R.string.anytime_tablet_library_sorter_a_to_z),
    Z_TO_A(3, R.string.anytime_tablet_library_sorter_z_to_a);
    
    private static final SparseArray<LibrarySorter> LIBRARY_SORTER = new SparseArray<>();
    private static final Map<LibrarySorter, Integer> LIBRARY_SORTER_MAP = new HashMap();
    private final int mId;
    private final int mStringId;

    static {
        LibrarySorter[] values = values();
        for (LibrarySorter librarySorter : values) {
            LIBRARY_SORTER_MAP.put(librarySorter, Integer.valueOf(librarySorter.getStringId()));
            LIBRARY_SORTER.put(librarySorter.getId(), librarySorter);
        }
    }

    private LibrarySorter(int i, int i2) {
        this.mId = i;
        this.mStringId = i2;
    }

    public int getId() {
        return this.mId;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public String getLocalizedString(Context context) {
        return context.getString(this.mStringId);
    }

    public static Map<LibrarySorter, Integer> getLabelMap() {
        return LIBRARY_SORTER_MAP;
    }

    public static LibrarySorter getDefault() {
        return MOST_RECENT;
    }

    public static LibrarySorter valueOf(int i) {
        return LIBRARY_SORTER.get(i);
    }
}
