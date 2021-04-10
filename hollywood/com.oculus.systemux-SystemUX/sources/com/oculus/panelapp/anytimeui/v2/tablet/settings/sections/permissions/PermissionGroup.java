package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions;

import java.util.Objects;

public class PermissionGroup {
    private final String mDisplayName;
    private final String mGroup;

    public PermissionGroup(String str, String str2) {
        this.mGroup = str;
        this.mDisplayName = str2;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mGroup.equals(((PermissionGroup) obj).mGroup);
    }

    public int hashCode() {
        return Objects.hash(this.mGroup);
    }
}
