package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.panelapp.bugreporter.databinding.BugReportConfirmationViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;

public class ConfirmationView extends BaseView {
    private static final String TAG = LoggingUtil.tag(ConfirmationView.class);
    private BugReportConfirmationViewBinding mBinding;
    private BugReporterUtil mBugReporterUtil;
    private BugReporterPanelApp mPanelApp;

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void onHide() {
    }

    public ConfirmationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding) {
        Log.d(TAG, "Initializing confirmation view");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
        this.mBinding = (BugReportConfirmationViewBinding) viewDataBinding;
        initializeCloseButton();
        Log.d(TAG, "Initialized confirmation view");
    }

    private void initializeCloseButton() {
        this.mBinding.closeButton.setEventHandler(this.mPanelApp);
        this.mBinding.closeButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$ConfirmationView$jHfIgJwhYc1ZgI46qXEuvnBqM4g */

            public final void onClick(View view) {
                ConfirmationView.this.lambda$initializeCloseButton$5$ConfirmationView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCloseButton$5$ConfirmationView(View view) {
        this.mPanelApp.actionQuitAndHide();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.CONFIRMATION_STEP_CLOSE, new String[0]);
    }
}
