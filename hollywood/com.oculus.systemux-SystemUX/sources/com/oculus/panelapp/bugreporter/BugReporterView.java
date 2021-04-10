package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.ReportStep;
import com.oculus.panelapp.bugreporter.databinding.BugReportConfirmationViewBinding;
import com.oculus.panelapp.bugreporter.databinding.BugReportDataPermissionViewBinding;
import com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBinding;
import com.oculus.panelapp.bugreporter.databinding.BugReportMediaViewBinding;
import com.oculus.panelapp.bugreporter.databinding.BugReportOsUpdateViewBinding;
import com.oculus.panelapp.bugreporter.databinding.BugReporterCancelViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import com.oculus.panelapp.bugreporter.util.OSUpdaterUtil;

public class BugReporterView extends LinearLayout implements BugReportStepHandler {
    private static final String TAG = LoggingUtil.tag(BugReporterView.class);
    private BugReporterUtil mBugReportUtil;
    private BugReporterViewModel mBugReporterViewModel;
    private BugReporterPanelApp mPanelApp;
    private BaseView mView;

    public BugReporterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "BugReporterView created");
    }

    public void initialize(BugReporterPanelApp bugReporterPanelApp) {
        Log.i(TAG, "Initializing BugReporterView");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReportUtil = new BugReporterUtil(bugReporterPanelApp.getContext(), bugReporterPanelApp);
        this.mView = null;
        this.mBugReporterViewModel = this.mBugReportUtil.getBugReporterViewModel();
        this.mBugReporterViewModel.registerStepHandler(this);
        Context context = bugReporterPanelApp.getContext();
        BugReporterViewModel bugReporterViewModel = this.mBugReporterViewModel;
        bugReporterViewModel.getClass();
        OSUpdaterUtil.checkOSVersion(context, new OSUpdaterUtil.OSUpdateAvailabilityCallback() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$v33uYbxnXgMFrYbpIiitlFRE3AU */

            @Override // com.oculus.panelapp.bugreporter.util.OSUpdaterUtil.OSUpdateAvailabilityCallback
            public final void run(boolean z) {
                BugReporterViewModel.this.initializeFlow(z);
            }
        });
    }

    @Override // com.oculus.panelapp.bugreporter.BugReportStepHandler
    @VisibleForTesting
    public void showStep(ReportStep reportStep) {
        BaseView baseView = this.mView;
        if (baseView != null) {
            baseView.onHide();
            removeAllViews();
        }
        switch (reportStep) {
            case OS_UPDATE:
                BugReportOsUpdateViewBinding inflate = BugReportOsUpdateViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                this.mView = (OSUpdateView) inflate.getRoot();
                this.mView.initialize(this.mPanelApp, this.mBugReportUtil, inflate);
                return;
            case DESCRIPTION:
                BugReportDescriptionViewBinding inflate2 = BugReportDescriptionViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                this.mView = (DescriptionView) inflate2.getRoot();
                this.mView.initialize(this.mPanelApp, this.mBugReportUtil, inflate2);
                return;
            case MEDIA:
                BugReportMediaViewBinding inflate3 = BugReportMediaViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                this.mView = (MediaView) inflate3.getRoot();
                this.mView.initialize(this.mPanelApp, this.mBugReportUtil, inflate3);
                return;
            case DATA_PERMISSION:
                BugReportDataPermissionViewBinding inflate4 = BugReportDataPermissionViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                this.mView = (DataPermissionView) inflate4.getRoot();
                this.mView.initialize(this.mPanelApp, this.mBugReportUtil, inflate4);
                return;
            case CONFIRMATION:
                BugReportConfirmationViewBinding inflate5 = BugReportConfirmationViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                this.mView = (ConfirmationView) inflate5.getRoot();
                this.mView.initialize(this.mPanelApp, this.mBugReportUtil, inflate5);
                return;
            case CANCEL:
                BugReporterCancelViewBinding inflate6 = BugReporterCancelViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                this.mView = (CancelView) inflate6.getRoot();
                this.mView.initialize(this.mPanelApp, this.mBugReportUtil, inflate6);
                return;
            default:
                return;
        }
    }

    public boolean onControllerBackButton() {
        return this.mBugReporterViewModel.onControllerBackButton();
    }
}
