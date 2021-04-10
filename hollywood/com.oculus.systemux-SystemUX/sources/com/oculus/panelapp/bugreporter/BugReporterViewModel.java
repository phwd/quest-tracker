package com.oculus.panelapp.bugreporter;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.common.ReportStep;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;

public class BugReporterViewModel {
    private static final String TAG = LoggingUtil.tag(BugReporterViewModel.class);
    private final BugReporterUtil mBugReporterUtil;
    private ReportStep mCancelledStep;
    private ReportStep mCurrentStep;
    private final BugReporterPanelApp mPanelApp;
    private BugReportStepHandler mStepHandler;

    public BugReporterViewModel(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil) {
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
    }

    public void initializeFlow(boolean z) {
        showStep(z ? ReportStep.OS_UPDATE : ReportStep.DESCRIPTION);
    }

    public void nextStep() {
        ReportStep nextStep = getNextStep(this.mCurrentStep, shouldShowMediaStep(), this.mBugReporterUtil.isPublicUser());
        if (nextStep != null) {
            showStep(nextStep);
        }
    }

    public void previousStep() {
        ReportStep previousStep = getPreviousStep(this.mCurrentStep, this.mCancelledStep, shouldShowMediaStep());
        if (previousStep != null) {
            showStep(previousStep);
        }
    }

    private boolean shouldShowMediaStep() {
        return this.mBugReporterUtil.getMediaViewModel().hasMediaFiles() && !this.mBugReporterUtil.getDescriptionViewModel().getHasPreselectedPhoto();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static ReportStep getNextStep(ReportStep reportStep, boolean z, boolean z2) {
        switch (reportStep) {
            case OS_UPDATE:
                return ReportStep.DESCRIPTION;
            case DESCRIPTION:
                if (z) {
                    return ReportStep.MEDIA;
                }
                break;
            case MEDIA:
                break;
            case DATA_PERMISSION:
                return ReportStep.CONFIRMATION;
            case CONFIRMATION:
            case CANCEL:
                Log.w(TAG, "Cannot go forward");
                return null;
            default:
                return null;
        }
        if (z2) {
            return ReportStep.CONFIRMATION;
        }
        return ReportStep.DATA_PERMISSION;
    }

    public static ReportStep getPreviousStep(ReportStep reportStep, ReportStep reportStep2, boolean z) {
        switch (reportStep) {
            case OS_UPDATE:
            case DESCRIPTION:
            case CONFIRMATION:
                Log.w(TAG, "Cannot go back");
                return null;
            case MEDIA:
                return ReportStep.DESCRIPTION;
            case DATA_PERMISSION:
                return z ? ReportStep.MEDIA : ReportStep.DESCRIPTION;
            case CANCEL:
                return reportStep2;
            default:
                return null;
        }
    }

    public boolean onControllerBackButton() {
        if (this.mCurrentStep == null) {
            return false;
        }
        switch (this.mCurrentStep) {
            case OS_UPDATE:
            case CONFIRMATION:
            case CANCEL:
                this.mPanelApp.actionQuitAndHide();
                return true;
            case DESCRIPTION:
            case MEDIA:
            case DATA_PERMISSION:
                cancel();
                return true;
            default:
                return true;
        }
    }

    public void cancel() {
        this.mCancelledStep = this.mCurrentStep;
        showStep(ReportStep.CANCEL);
    }

    @VisibleForTesting
    public void showStep(ReportStep reportStep) {
        if (this.mCurrentStep != reportStep) {
            this.mCurrentStep = reportStep;
            BugReportStepHandler bugReportStepHandler = this.mStepHandler;
            if (bugReportStepHandler != null) {
                bugReportStepHandler.showStep(this.mCurrentStep);
            }
        }
    }

    public void registerStepHandler(BugReportStepHandler bugReportStepHandler) {
        this.mStepHandler = bugReportStepHandler;
    }
}
