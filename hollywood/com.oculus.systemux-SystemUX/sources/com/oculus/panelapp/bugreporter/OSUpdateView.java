package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.panelapp.bugreporter.databinding.BugReportOsUpdateViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;

public class OSUpdateView extends BaseView {
    private static final String TAG = LoggingUtil.tag(OSUpdateView.class);
    private BugReportOsUpdateViewBinding mBinding;
    private BugReporterUtil mBugReporterUtil;
    private BugReporterPanelApp mPanelApp;

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void onHide() {
    }

    public OSUpdateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding) {
        Log.d(TAG, "Initializing OS Update View");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
        this.mBinding = (BugReportOsUpdateViewBinding) viewDataBinding;
        initializeUpdateButton();
        initializeReportButton();
        initializeCancelButton();
        Log.d(TAG, "Initialized OS Update View");
    }

    private void initializeUpdateButton() {
        this.mBinding.updateButton.setEventHandler(this.mPanelApp);
        this.mBinding.updateButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$OSUpdateView$dG0Uz1IAedCkFSWjOWiLvejsumw */

            public final void onClick(View view) {
                OSUpdateView.this.lambda$initializeUpdateButton$19$OSUpdateView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeUpdateButton$19$OSUpdateView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_ABOUT_URI);
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.OS_STEP_UPDATE, new String[0]);
    }

    private void initializeReportButton() {
        this.mBinding.reportButton.setEventHandler(this.mPanelApp);
        this.mBinding.reportButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$OSUpdateView$5T6G2lxSymktkq3yH1439d_DZbQ */

            public final void onClick(View view) {
                OSUpdateView.this.lambda$initializeReportButton$20$OSUpdateView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeReportButton$20$OSUpdateView(View view) {
        this.mBugReporterUtil.getBugReporterViewModel().nextStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.OS_STEP_CONTINUE, new String[0]);
    }

    private void initializeCancelButton() {
        this.mBinding.cancelButton.setEventHandler(this.mPanelApp);
        this.mBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$OSUpdateView$HAnHvc6i2CdwxJPeK1JZL3TAITw */

            public final void onClick(View view) {
                OSUpdateView.this.lambda$initializeCancelButton$21$OSUpdateView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCancelButton$21$OSUpdateView(View view) {
        this.mPanelApp.actionQuitAndHide();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.OS_STEP_CANCEL, new String[0]);
    }
}
