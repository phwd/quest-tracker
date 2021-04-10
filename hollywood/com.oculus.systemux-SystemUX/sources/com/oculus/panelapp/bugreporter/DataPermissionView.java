package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.panelapp.bugreporter.databinding.BugReportDataPermissionViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;

public class DataPermissionView extends BaseView {
    private static final String TAG = LoggingUtil.tag(DataPermissionView.class);
    private BugReportDataPermissionViewBinding mBinding;
    private BugReporterUtil mBugReporterUtil;
    private BugReporterViewModel mBugReporterViewModel;
    private BugReporterPanelApp mPanelApp;

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void onHide() {
    }

    public DataPermissionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding) {
        Log.d(TAG, "Initializing data permission View");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
        this.mBugReporterViewModel = bugReporterUtil.getBugReporterViewModel();
        this.mBinding = (BugReportDataPermissionViewBinding) viewDataBinding;
        this.mBinding.setViewModel(this.mBugReporterUtil.getDataPermissionViewModel());
        initializeCheckBox();
        initializeBackButton();
        initializeCancelButton();
        initializeSubmitButton();
        Log.d(TAG, "Initialized data permission view");
    }

    private void initializeCheckBox() {
        Log.d(TAG, "Initializing system information checkbox");
        this.mBinding.checkbox.setEventHandler(this.mPanelApp);
        this.mBinding.checkbox.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DataPermissionView$ZsKzwnaO5nKLeT_Spqpx6za1As */

            public final void onClick(View view) {
                DataPermissionView.this.lambda$initializeCheckBox$6$DataPermissionView(view);
            }
        });
        Log.d(TAG, "Initialized system information checkbox");
    }

    public /* synthetic */ void lambda$initializeCheckBox$6$DataPermissionView(View view) {
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DATA_STEP_INCLUDE_LOGS, new String[0]);
    }

    private void initializeBackButton() {
        Log.d(TAG, "Initializing back button");
        this.mBinding.backButton.setEventHandler(this.mPanelApp);
        this.mBinding.backButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DataPermissionView$S0TNCNmoUK2Q6Cz7zFFawqh20qA */

            public final void onClick(View view) {
                DataPermissionView.this.lambda$initializeBackButton$7$DataPermissionView(view);
            }
        });
        Log.d(TAG, "Initialized back button");
    }

    public /* synthetic */ void lambda$initializeBackButton$7$DataPermissionView(View view) {
        this.mBugReporterViewModel.previousStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DATA_STEP_BACK, new String[0]);
    }

    private void initializeCancelButton() {
        Log.d(TAG, "Initializing cancel button");
        this.mBinding.cancelButton.setEventHandler(this.mPanelApp);
        this.mBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DataPermissionView$NL5NVW_5SHWQ3Xj03t0Lkuy7gw */

            public final void onClick(View view) {
                DataPermissionView.this.lambda$initializeCancelButton$8$DataPermissionView(view);
            }
        });
        Log.d(TAG, "Initialized cancel button");
    }

    public /* synthetic */ void lambda$initializeCancelButton$8$DataPermissionView(View view) {
        this.mBugReporterViewModel.cancel();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DATA_STEP_CANCEL, new String[0]);
    }

    private void initializeSubmitButton() {
        Log.d(TAG, "Initializing submit button");
        this.mBinding.submitButton.setEventHandler(this.mPanelApp);
        this.mBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DataPermissionView$N1hpJ2hehnlJK9Oeg8K8FxZlBc8 */

            public final void onClick(View view) {
                DataPermissionView.this.lambda$initializeSubmitButton$9$DataPermissionView(view);
            }
        });
        Log.d(TAG, "Initialized submit button");
    }

    public /* synthetic */ void lambda$initializeSubmitButton$9$DataPermissionView(View view) {
        this.mBugReporterViewModel.nextStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DATA_STEP_SUBMIT, new String[0]);
        this.mBugReporterUtil.submitReport();
    }
}
