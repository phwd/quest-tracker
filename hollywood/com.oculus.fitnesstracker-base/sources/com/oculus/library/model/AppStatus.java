package com.oculus.library.model;

public enum AppStatus {
    UNKNOWN(-1),
    ENTITLED(0),
    INCOMPATIBLE(1),
    DOWNLOAD_QUEUED(2),
    DOWNLOADING(3),
    INSTALLING(4),
    INSTALLED(5),
    UNINSTALLING(6),
    INSTALL_AVAILABLE(7),
    PAUSED(8),
    STUCK(9);
    
    public final int value;

    private AppStatus(int i) {
        this.value = i;
    }
}
