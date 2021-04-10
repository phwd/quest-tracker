package com.oculus.horizoncontent.apps.models;

public final class AppScoreboardsInfo {
    private final String mAppId;
    private final boolean mHasAchievements;
    private final boolean mHasLeaderboards;

    public AppScoreboardsInfo(String str, boolean z, boolean z2) {
        this.mAppId = str;
        this.mHasAchievements = z;
        this.mHasLeaderboards = z2;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public boolean getHasAchievements() {
        return this.mHasAchievements;
    }

    public boolean getHasLeaderboards() {
        return this.mHasLeaderboards;
    }
}
