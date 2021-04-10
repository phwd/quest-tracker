package com.oculus.panelapp.bugreporter;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;

public class DataPermissionViewModel extends BaseObservable {
    private boolean mAttachLogs = true;
    private final BugReporterUtil mBugReportUtil;

    public DataPermissionViewModel(BugReporterUtil bugReporterUtil) {
        this.mBugReportUtil = bugReporterUtil;
    }

    @Bindable
    public boolean getAttachLogs() {
        return !this.mBugReportUtil.isPublicUser() && this.mAttachLogs;
    }

    public void setAttachLogs(boolean z) {
        if (this.mAttachLogs != z) {
            this.mAttachLogs = z;
            notifyPropertyChanged(BR.attachLogs);
        }
    }
}
