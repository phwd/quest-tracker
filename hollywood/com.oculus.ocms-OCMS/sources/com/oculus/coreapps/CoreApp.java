package com.oculus.coreapps;

import com.facebook.common.internal.Objects;
import com.oculus.horizon.api.common.MinimumAppVersion;

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

    public CoreApp(MinimumAppVersion.MinimumAppVersionEntry minimumAppVersionEntry) {
        this.entry = minimumAppVersionEntry;
        this.status = Status.UNKNOWN;
    }

    public CoreApp(CoreApp coreApp, Status status2) {
        this.entry = coreApp.entry;
        this.status = status2;
    }

    public String getStatusString() {
        return this.status.toString();
    }

    public boolean isStarted() {
        return this.status == Status.IN_PROGRESS || isDone();
    }

    public boolean isDone() {
        return this.status == Status.UP_TO_DATE || this.status == Status.DOWNLOAD_FAILED || this.status == Status.INSTALL_FAILED || this.status == Status.INSTALL_SUCCESS;
    }

    public CoreApp onUpToDate() {
        return new CoreApp(this, Status.UP_TO_DATE);
    }

    public CoreApp onNeedsInstall() {
        return new CoreApp(this, Status.NEEDS_INSTALL);
    }

    public CoreApp onDownloadStart() {
        return new CoreApp(this, Status.IN_PROGRESS);
    }

    public CoreApp onDownloadSuccess() {
        return new CoreApp(this, this.status);
    }

    public CoreApp onDownloadFail() {
        return new CoreApp(this, Status.DOWNLOAD_FAILED);
    }

    public CoreApp onInstallSuccess() {
        return new CoreApp(this, Status.INSTALL_SUCCESS);
    }

    public CoreApp onInstallFail() {
        return new CoreApp(this, Status.INSTALL_FAILED);
    }

    public int hashCode() {
        return Objects.hashCode(this.status, this.entry);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CoreApp coreApp = (CoreApp) obj;
        if (this.status != coreApp.status || !this.entry.equals(coreApp.entry)) {
            return false;
        }
        return true;
    }

    public int compareTo(CoreApp coreApp) {
        return this.entry.display_name.compareTo(coreApp.entry.display_name);
    }
}
