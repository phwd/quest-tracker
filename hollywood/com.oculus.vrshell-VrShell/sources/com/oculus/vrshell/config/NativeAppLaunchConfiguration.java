package com.oculus.vrshell.config;

public class NativeAppLaunchConfiguration {
    private boolean isTvApplication = false;
    private boolean isVrApplication = false;
    private String launchComponent = null;

    public String getLaunchComponent() {
        return this.launchComponent;
    }

    public void setLaunchComponent(String str) {
        this.launchComponent = str;
    }

    public boolean isVrApplication() {
        return this.isVrApplication;
    }

    public void setIsVrApplication(boolean z) {
        this.isVrApplication = z;
    }

    public boolean isTvApplication() {
        return this.isTvApplication;
    }

    public void setIsTvApplication(boolean z) {
        this.isTvApplication = z;
    }
}
