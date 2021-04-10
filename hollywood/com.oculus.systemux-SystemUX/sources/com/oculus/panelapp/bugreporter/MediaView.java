package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.panelapp.bugreporter.databinding.BugReportMediaViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.List;
import java.util.concurrent.Callable;

public class MediaView extends BaseView {
    private static final String TAG = LoggingUtil.tag(MediaView.class);
    private BugReportMediaViewBinding mBinding;
    private BugReporterUtil mBugReporterUtil;
    private BugReporterViewModel mBugReporterViewModel;
    private CameraRollAdapter mCameraRollAdapter;
    private BugReporterPanelApp mPanelApp;

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void onHide() {
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding) {
        Log.d(TAG, "Initializing media view");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
        this.mBugReporterViewModel = bugReporterUtil.getBugReporterViewModel();
        this.mBinding = (BugReportMediaViewBinding) viewDataBinding;
        this.mBinding.setViewModel(this.mBugReporterUtil.getMediaViewModel());
        initializeBackButton();
        initializeContinueButton();
        initializeCancelButton();
        initializeCameraRoll();
        Log.d(TAG, "Initialized media view");
    }

    private void initializeBackButton() {
        Log.d(TAG, "Initializing back button");
        this.mBinding.backButton.setEventHandler(this.mPanelApp);
        this.mBinding.backButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$MediaView$dpHkqGykJ4t1KXj_UUnLUlpX9mg */

            public final void onClick(View view) {
                MediaView.this.lambda$initializeBackButton$14$MediaView(view);
            }
        });
        Log.d(TAG, "Initialized back button");
    }

    public /* synthetic */ void lambda$initializeBackButton$14$MediaView(View view) {
        this.mBugReporterViewModel.previousStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.MEDIA_STEP_BACK, new String[0]);
    }

    private void initializeContinueButton() {
        Log.d(TAG, "Initializing continue button");
        this.mBinding.continueButton.setEventHandler(this.mPanelApp);
        this.mBinding.continueButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$MediaView$bvMWqEvHczLGzxaYJe0MzgvSB5k */

            public final void onClick(View view) {
                MediaView.this.lambda$initializeContinueButton$15$MediaView(view);
            }
        });
        Log.d(TAG, "Initialized continue button");
    }

    public /* synthetic */ void lambda$initializeContinueButton$15$MediaView(View view) {
        this.mBugReporterViewModel.nextStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.MEDIA_STEP_CONTINUE, new String[0]);
        if (this.mBugReporterUtil.isPublicUser()) {
            this.mBugReporterUtil.submitReport();
        }
    }

    private void initializeCancelButton() {
        Log.d(TAG, "Initializing cancel button");
        this.mBinding.cancelButton.setEventHandler(this.mPanelApp);
        this.mBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$MediaView$rAYO1cdTJSMbn2DEtGzo508Vds */

            public final void onClick(View view) {
                MediaView.this.lambda$initializeCancelButton$16$MediaView(view);
            }
        });
        Log.d(TAG, "Initialized cancel button");
    }

    public /* synthetic */ void lambda$initializeCancelButton$16$MediaView(View view) {
        this.mBugReporterViewModel.cancel();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.MEDIA_STEP_CANCEL, new String[0]);
    }

    private void initializeCameraRoll() {
        OCRecyclerView oCRecyclerView = this.mBinding.cameraRoll;
        this.mCameraRollAdapter = new CameraRollAdapter(getContext(), this.mPanelApp, this.mBugReporterUtil);
        oCRecyclerView.setAdapter(this.mCameraRollAdapter);
        oCRecyclerView.setHasFixedSize(true);
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$MediaView$D9NJK86BiuX4tsYM_4qcyOonk */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaView.this.lambda$initializeCameraRoll$18$MediaView();
            }
        });
        oCRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    public /* synthetic */ Object lambda$initializeCameraRoll$18$MediaView() throws Exception {
        UiThreadExecutor.getInstance().execute(new Runnable(this.mBugReporterUtil.getMediaViewModel().getMediaFiles()) {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$MediaView$1htPPRwVvdhgXxHdcHKyw2YTV3A */
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MediaView.this.lambda$null$17$MediaView(this.f$1);
            }
        });
        return null;
    }

    public /* synthetic */ void lambda$null$17$MediaView(List list) {
        this.mCameraRollAdapter.initializeCameraRoll(list);
    }
}
