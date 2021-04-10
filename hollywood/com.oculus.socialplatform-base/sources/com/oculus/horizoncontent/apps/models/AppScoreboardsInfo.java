package com.oculus.horizoncontent.apps.models;

public final class AppScoreboardsInfo {
    public final String mAppId;
    public final boolean mHasAchievements;
    public final boolean mHasLeaderboards;

    public String getAppId() {
        return this.mAppId;
    }

    public boolean getHasAchievements() {
        return this.mHasAchievements;
    }

    public boolean getHasLeaderboards() {
        return this.mHasLeaderboards;
    }

    public AppScoreboardsInfo(String str, boolean z, boolean z2) {
        this.mAppId = str;
        this.mHasAchievements = z;
        this.mHasLeaderboards = z2;
    }
}
