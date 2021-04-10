package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.bumptech.glide.Glide;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCDropdownVisibilityCallback;
import com.oculus.ocui.OCLink;
import com.oculus.panelapp.bugreporter.common.BaseView;
import com.oculus.panelapp.bugreporter.common.BugCategory;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import java.util.function.Function;

public class DescriptionView extends BaseView {
    private static final String TAG = LoggingUtil.tag(DescriptionView.class);
    private BugReportDescriptionViewBinding mBinding;
    private BugReporterUtil mBugReporterUtil;
    private BugReporterViewModel mBugReporterViewModel;
    private BugReporterPanelApp mPanelApp;

    public DescriptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing DescriptionView");
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding) {
        Log.d(TAG, "Initializing DescriptionView");
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReporterUtil = bugReporterUtil;
        this.mBugReporterViewModel = bugReporterUtil.getBugReporterViewModel();
        this.mBinding = (BugReportDescriptionViewBinding) viewDataBinding;
        this.mBinding.setViewModel(this.mBugReporterUtil.getDescriptionViewModel());
        initializeCategorySelector();
        initializeCancelButton();
        initializeContinueButton();
        initializeScreenshot();
        initializeScreenshotCheckbox();
        initializePreselectedPhoto();
        initializeSupportText();
        Log.d(TAG, "Initialized DescriptionView");
    }

    @Override // com.oculus.panelapp.bugreporter.common.BaseView
    public void onHide() {
        this.mBinding.categorySelector.dismissDropdown();
    }

    private void initializeCategorySelector() {
        Log.d(TAG, "Initializing category selector");
        DescriptionViewModel descriptionViewModel = this.mBugReporterUtil.getDescriptionViewModel();
        this.mBinding.categorySelector.setItems(descriptionViewModel.getBugCategoryDropdownItems());
        this.mBinding.categorySelector.setTitleMap(descriptionViewModel.getBugCategoryDropdownLabelMap());
        this.mBinding.categorySelector.setOnItemClick(new Function(descriptionViewModel) {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DescriptionView$eHFLD3qAPqh5OySWoErmfIsGjQY */
            private final /* synthetic */ DescriptionViewModel f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DescriptionView.this.lambda$initializeCategorySelector$10$DescriptionView(this.f$1, obj);
            }
        });
        this.mBinding.categorySelector.setSelectedItem(descriptionViewModel.getBugCategory());
        this.mBinding.categorySelector.setVisibilityCallback(new OCDropdownVisibilityCallback() {
            /* class com.oculus.panelapp.bugreporter.DescriptionView.AnonymousClass1 */

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onShow() {
                DescriptionView.this.mBinding.scrollview.disableScroll();
            }

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onHide() {
                DescriptionView.this.mBinding.scrollview.enableScroll();
            }
        });
        this.mBinding.categorySelector.setEventHandler(this.mPanelApp);
        Log.d(TAG, "Initialized category selector");
    }

    public /* synthetic */ Object lambda$initializeCategorySelector$10$DescriptionView(DescriptionViewModel descriptionViewModel, Object obj) {
        this.mBinding.categorySelector.setSelectedItem(obj);
        BugCategory bugCategory = (BugCategory) obj;
        descriptionViewModel.setBugCategory(bugCategory);
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DESCRIPTION_STEP_CATEGORY, "category", bugCategory.name());
        return null;
    }

    private void initializeCancelButton() {
        Log.d(TAG, "Initializing cancel button");
        this.mBinding.cancelButton.setEventHandler(this.mPanelApp);
        this.mBinding.cancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DescriptionView$0MVk7F2lWPqzi6dihxkLitNNmdA */

            public final void onClick(View view) {
                DescriptionView.this.lambda$initializeCancelButton$11$DescriptionView(view);
            }
        });
        Log.d(TAG, "Initialized cancel button");
    }

    public /* synthetic */ void lambda$initializeCancelButton$11$DescriptionView(View view) {
        this.mBugReporterViewModel.cancel();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DESCRIPTION_STEP_CANCEL, new String[0]);
    }

    private void initializeContinueButton() {
        Log.d(TAG, "Initializing continue button");
        this.mBinding.continueButton.setEventHandler(this.mPanelApp);
        this.mBinding.continueButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DescriptionView$91oqZgtL4dwdgv6YE1nFJUMa2xw */

            public final void onClick(View view) {
                DescriptionView.this.lambda$initializeContinueButton$12$DescriptionView(view);
            }
        });
        Log.d(TAG, "Initialized continue button");
    }

    public /* synthetic */ void lambda$initializeContinueButton$12$DescriptionView(View view) {
        this.mBugReporterViewModel.nextStep();
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DESCRIPTION_STEP_CONTINUE, new String[0]);
        if (this.mBugReporterUtil.getDescriptionViewModel().shouldSkipToSubmit()) {
            this.mBugReporterUtil.submitReport();
        }
    }

    private void initializeScreenshot() {
        Log.d(TAG, "Initializing screenshot");
        this.mBinding.screenshot.setClipToOutline(true);
        Log.d(TAG, "Initialized screenshot");
    }

    private void initializeScreenshotCheckbox() {
        Log.d(TAG, "Initializing screenshot checkbox");
        this.mBinding.checkbox.setEventHandler(this.mPanelApp);
        this.mBinding.checkbox.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$DescriptionView$Ylz7xV8KbZheaGmU3QF7LkI7vY */

            public final void onClick(View view) {
                DescriptionView.this.lambda$initializeScreenshotCheckbox$13$DescriptionView(view);
            }
        });
        Log.d(TAG, "Initialized screenshot checkbox");
    }

    public /* synthetic */ void lambda$initializeScreenshotCheckbox$13$DescriptionView(View view) {
        this.mBugReporterUtil.logButtonClick(ClickEventButtonId.DESCRIPTION_STEP_INCLUDE_SCREENSHOT, new String[0]);
    }

    private void initializePreselectedPhoto() {
        DescriptionViewModel descriptionViewModel = this.mBugReporterUtil.getDescriptionViewModel();
        if (descriptionViewModel.getHasPreselectedPhoto()) {
            Log.d(TAG, "Initializing preselected photo");
            Glide.with(this.mPanelApp.getContext()).asBitmap().load(descriptionViewModel.getPreselectedPhoto()).into(this.mBinding.preselectedPhoto);
            this.mBinding.preselectedPhoto.setClipToOutline(true);
            Log.d(TAG, "Initialized preselected photo");
        }
    }

    private void initializeSupportText() {
        this.mBinding.supportText.setEventHandler(this.mPanelApp);
        OCLink oCLink = this.mBinding.supportText;
        BugReporterPanelApp bugReporterPanelApp = this.mPanelApp;
        bugReporterPanelApp.getClass();
        oCLink.setLinkHandler(new OCLink.OCLinkHandler() {
            /* class com.oculus.panelapp.bugreporter.$$Lambda$t1jUMaaImRqG8knFQ2A0MiuNgs4 */

            @Override // com.oculus.ocui.OCLink.OCLinkHandler
            public final void open(String str, String str2) {
                BugReporterPanelApp.this.actionNavigate(str, str2);
            }
        });
    }
}
