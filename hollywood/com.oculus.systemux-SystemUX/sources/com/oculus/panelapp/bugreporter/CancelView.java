package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.panelapp.bugreporter.databinding.BugReporterCancelViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;

public class CancelView extends BaseView {
    private static final String TAG = LoggingUtil.tag(CancelView.class);
    private BugReporterCancelViewBinding mBinding;
    private BugReporterUtil mBugReporterUtil;
    private BugReporterPanelApp mPanelApp;

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void onHide() {
    }

    public CancelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding) {
        Log.d(TAG, "Initializing Cancel View");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
        this.mBinding = (BugReporterCancelViewBinding) viewDataBinding;
        initializeContinueReportButton();
        initializeCancelReportButton();
        Log.d(TAG, "Initialized Cancel View");
    }

    private void initializeContinueReportButton() {
        this.mBinding.continueReportButton.setEventHandler(this.mPanelApp);
        this.mBinding.continueReportButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$CancelView$UeCFqHyIVSFZHehdrO16H_MB1C4 */

            public final void onClick(View view) {
                CancelView.this.lambda$initializeContinueReportButton$3$CancelView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeContinueReportButton$3$CancelView(View view) {
        this.mBugReporterUtil.getBugReporterViewModel().previousStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.CANCEL_STEP_CANCEL, new String[0]);
    }

    private void initializeCancelReportButton() {
        this.mBinding.cancelReportButton.setEventHandler(this.mPanelApp);
        this.mBinding.cancelReportButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$CancelView$O7PGV7WQg_zyOKfdcK04a_2y6hs */

            public final void onClick(View view) {
                CancelView.this.lambda$initializeCancelReportButton$4$CancelView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCancelReportButton$4$CancelView(View view) {
        this.mBugReporterUtil.cancelReport();
        this.mPanelApp.actionQuitAndHide();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.CANCEL_STEP_CONFIRM, new String[0]);
    }
}
