package com.oculus.panelapp.anytimeui.v2.tablet.apps.models;

import java.util.Objects;

public class UnknownSource {
    private double mApkSize;
    private String mApplicationName;
    private long mLastModified;
    private String mPackageName;

    public UnknownSource(String str, String str2, double d, long j) {
        this.mPackageName = str;
        this.mApplicationName = str2;
        this.mApkSize = d;
        this.mLastModified = j;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getApplicationName() {
        return this.mApplicationName;
    }

    public double getApkSize() {
        return this.mApkSize;
    }

    public long getLastModified() {
        return this.mLastModified;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnknownSource unknownSource = (UnknownSource) obj;
        return this.mLastModified == unknownSource.mLastModified && Double.compare(unknownSource.mApkSize, this.mApkSize) == 0 && Objects.equals(this.mApplicationName, unknownSource.mApplicationName) && Objects.equals(this.mPackageName, unknownSource.mPackageName);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mLastModified), Double.valueOf(this.mApkSize), this.mApplicationName, this.mPackageName);
    }
}
