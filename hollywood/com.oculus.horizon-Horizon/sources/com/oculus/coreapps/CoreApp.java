package com.oculus.coreapps;

import com.oculus.horizon.api.common.MinimumAppVersion;
import java.util.Arrays;

public class CoreApp implements Comparable<CoreApp> {
    public final MinimumAppVersion.MinimumAppVersionEntry entry;
    public final Status status;

    public enum Status {
        UNKNOWN,
        UP_TO_DATE,
        NEEDS_INSTALL,
        IN_PROGRESS,
        DOWNLOAD_FAILED,
        INSTALL_FAILED,
        INSTALL_SUCCESS
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CoreApp coreApp = (CoreApp) obj;
        return this.status == coreApp.status && this.entry.equals(coreApp.entry);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.status, this.entry});
    }

    public final boolean A00() {
        Status status2 = this.status;
        if (status2 == Status.UP_TO_DATE || status2 == Status.DOWNLOAD_FAILED || status2 == Status.INSTALL_FAILED || status2 == Status.INSTALL_SUCCESS) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(CoreApp coreApp) {
        return this.entry.display_name.compareTo(coreApp.entry.display_name);
    }

    public CoreApp(CoreApp coreApp, Status status2) {
        this.entry = coreApp.entry;
        this.status = status2;
    }

    public CoreApp(MinimumAppVersion.MinimumAppVersionEntry minimumAppVersionEntry) {
        this.entry = minimumAppVersionEntry;
        this.status = Status.UNKNOWN;
    }
}
