package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models;

import java.util.Objects;

public class LauncherAction {
    public final String mAppName;
    public final String mLaunchCommand;
    public final String mPackageName;

    public LauncherAction(String str, String str2, String str3) {
        this.mAppName = str;
        this.mPackageName = str2;
        this.mLaunchCommand = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LauncherAction launcherAction = (LauncherAction) obj;
        return this.mAppName.equals(launcherAction.mAppName) && this.mLaunchCommand.equals(launcherAction.mLaunchCommand) && this.mPackageName.equals(launcherAction.mPackageName);
    }

    public int hashCode() {
        return Objects.hash(this.mAppName, this.mLaunchCommand, this.mPackageName);
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }
}
