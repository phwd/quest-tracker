package com.oculus.panelapp.anytimeui.v2.tablet.apps.models;

import android.content.Context;
import android.util.SparseArray;
import com.oculus.panelapp.anytimeui.R;
import java.util.HashMap;
import java.util.Map;

public enum LibraryPlatform {
    ANDROID_6DOF(0, R.string.anytime_tablet_library_platform_android_6dof, R.drawable.quest_headset),
    ANDROID_3DOF(1, R.string.anytime_tablet_library_platform_android_3dof, R.drawable.go_headset);
    
    private static final SparseArray<LibraryPlatform> LIBRARY_PLATFORM = new SparseArray<>();
    private static final Map<LibraryPlatform, Integer> LIBRARY_PLATFORM_ICON_MAP = new HashMap();
    private static final Map<LibraryPlatform, Integer> LIBRARY_PLATFORM_LABEL_MAP = new HashMap();
    private final int mIconId;
    private final int mId;
    private final int mStringId;

    static {
        LibraryPlatform[] values = values();
        for (LibraryPlatform libraryPlatform : values) {
            LIBRARY_PLATFORM_LABEL_MAP.put(libraryPlatform, Integer.valueOf(libraryPlatform.getStringId()));
            LIBRARY_PLATFORM_ICON_MAP.put(libraryPlatform, Integer.valueOf(libraryPlatform.getIconId()));
            LIBRARY_PLATFORM.put(libraryPlatform.getId(), libraryPlatform);
        }
    }

    private LibraryPlatform(int i, int i2, int i3) {
        this.mId = i;
        this.mStringId = i2;
        this.mIconId = i3;
    }

    public int getId() {
        return this.mId;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public String getLocalizedString(Context context) {
        return context.getString(this.mStringId);
    }

    public static Map<LibraryPlatform, Integer> getLabelMap() {
        return LIBRARY_PLATFORM_LABEL_MAP;
    }

    public static Map<LibraryPlatform, Integer> getIconMap() {
        return LIBRARY_PLATFORM_ICON_MAP;
    }

    public static LibraryPlatform getDefault() {
        return ANDROID_6DOF;
    }

    public static LibraryPlatform valueOf(int i) {
        return LIBRARY_PLATFORM.get(i);
    }
}
