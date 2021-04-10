package com.oculus.nux.ota;

public class StateMachineInternalState {
    private boolean mHighPriAppsDownloadComplete = false;
    private boolean mInEnterpriseMode = false;
    private boolean mIsHighPriAppsDownloadStalled = false;
    private boolean mIsOtaDownloadStalled = false;
    private boolean mNoOta = false;
    private boolean mOkayToReboot = false;
    private boolean mOtaReady = false;

    public StateMachineInternalState(boolean z, boolean z2) {
        this.mInEnterpriseMode = z;
        this.mHighPriAppsDownloadComplete = z2;
    }

    public boolean stayInNewDeviceState() {
        return !this.mOkayToReboot && !this.mOtaReady && !this.mNoOta;
    }

    public boolean stayInNoOtaOrOtaReadyState() {
        return !this.mOkayToReboot;
    }

    public boolean stayInOkayToRebootState() {
        return !this.mIsOtaDownloadStalled && !this.mOtaReady && !this.mNoOta;
    }

    public boolean stayInWaitingForHighPriAppsDownloadState() {
        return !this.mHighPriAppsDownloadComplete && !this.mIsHighPriAppsDownloadStalled;
    }

    public void setInEnterpriseMode(boolean z) {
        this.mInEnterpriseMode = z;
    }

    public boolean getInEnterpriseMode() {
        return this.mInEnterpriseMode;
    }

    public void setHighPriAppsDownloadComplete(boolean z) {
        this.mHighPriAppsDownloadComplete = z;
    }

    public boolean getHighPriAppsDownloadComplete() {
        return this.mHighPriAppsDownloadComplete;
    }

    public void setIsHighPriAppsDownloadStalled(boolean z) {
        this.mIsHighPriAppsDownloadStalled = z;
    }

    public boolean getIsHighPriAppsDownloadStalled() {
        return this.mIsHighPriAppsDownloadStalled;
    }

    public void setIsOtaDownloadStalled(boolean z) {
        this.mIsOtaDownloadStalled = z;
    }

    public boolean getIsOtaDownloadStalled() {
        return this.mIsOtaDownloadStalled;
    }

    public void setNoOta(boolean z) {
        this.mNoOta = z;
    }

    public boolean getNoOta() {
        return this.mNoOta;
    }

    public void setOtaReady(boolean z) {
        this.mOtaReady = z;
    }

    public boolean getOtaReady() {
        return this.mOtaReady;
    }

    public void setOkayToReboot(boolean z) {
        this.mOkayToReboot = z;
    }

    public boolean getOkayToReboot() {
        return this.mOkayToReboot;
    }
}
