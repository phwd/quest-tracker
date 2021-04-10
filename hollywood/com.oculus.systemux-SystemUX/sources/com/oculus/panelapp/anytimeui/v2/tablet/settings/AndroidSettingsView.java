package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsViewBinding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.util.SettingsSectionUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.List;

public class AndroidSettingsView extends BaseView {
    private static final int POST_HIGHLIGHT_DELAY = 5000;
    private static final int PRE_HIGHLIGHT_DELAY = 1000;
    private SettingsContentAdapter mAdapter;
    private AnytimeTabletAndroidSettingsViewBinding mBinding;
    private Context mContext;
    private RecyclerView.OnScrollListener mOnScrollListener;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private SettingsSection mSection;
    private SettingsLogger mSettingsLogger;
    private AndroidSettingsViewModel mViewModel;

    public AndroidSettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletAndroidSettingsViewBinding) viewDataBinding;
        this.mViewModel = anytimeUIAndroidPanelAppV2.acquireAndroidSettingsViewModel();
        this.mSettingsLogger = this.mViewModel.getSettingsLogger();
        this.mBinding.setViewModel(this.mViewModel);
        initializeSettingsRecyclerView();
        initializeBackButton();
        initializeToolbarButton();
    }

    private void initializeBackButton() {
        this.mBinding.backButton.setEventHandler(this.mPanelApp);
        this.mBinding.backButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$AndroidSettingsView$f8yk9XiMRCvvw1ItYo9_sIs3qY */

            public final void onClick(View view) {
                AndroidSettingsView.this.lambda$initializeBackButton$247$AndroidSettingsView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeBackButton$247$AndroidSettingsView(View view) {
        String parentSectionRoute = this.mSection.getParentSectionRoute();
        if (!TextUtils.isEmpty(parentSectionRoute)) {
            this.mPanelApp.actionNavigate(SystemUXRoute.SETTINGS, parentSectionRoute);
        }
    }

    private void initializeToolbarButton() {
        this.mBinding.toolbarButton.setEventHandler(this.mPanelApp);
        this.mBinding.toolbarButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$AndroidSettingsView$bMVIRZyueWYUjQeCO0TgvJg7k1c */

            public final void onClick(View view) {
                AndroidSettingsView.this.lambda$initializeToolbarButton$248$AndroidSettingsView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeToolbarButton$248$AndroidSettingsView(View view) {
        this.mSection.clickToolbarButton();
    }

    private void initializeSettingsRecyclerView() {
        OCRecyclerView oCRecyclerView = this.mBinding.settingsContent;
        Context context = this.mContext;
        AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = this.mPanelApp;
        anytimeUIAndroidPanelAppV2.getClass();
        this.mAdapter = new SettingsContentAdapter(context, anytimeUIAndroidPanelAppV2, new OCLink.OCLinkHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$Og4eAm1osmJac_gariDC7t9yO_U */

            @Override // com.oculus.ocui.OCLink.OCLinkHandler
            public final void open(String str, String str2) {
                AnytimeUIAndroidPanelAppV2.this.actionNavigate(str, str2);
            }
        }, this.mSettingsLogger);
        oCRecyclerView.setAdapter(this.mAdapter);
        oCRecyclerView.setHasFixedSize(true);
        this.mOnScrollListener = new RecyclerView.OnScrollListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsView.AnonymousClass1 */

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (gridLayoutManager != null) {
                    if (gridLayoutManager.findLastVisibleItemPosition() == gridLayoutManager.getItemCount() - 1) {
                        AndroidSettingsView.this.mSection.onScrolledToBottom();
                    }
                }
            }
        };
        oCRecyclerView.addOnScrollListener(this.mOnScrollListener);
        oCRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 1) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsView.AnonymousClass2 */

            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager, androidx.recyclerview.widget.LinearLayoutManager
            public boolean canScrollVertically() {
                return AndroidSettingsView.this.mViewModel.getIsScrollEnabled() && super.canScrollVertically();
            }
        });
    }

    private void updateSettingsSection(SettingsSection settingsSection) {
        SettingsSection settingsSection2 = this.mSection;
        if (settingsSection2 != null) {
            settingsSection2.onHide();
            this.mSection.destroy();
        }
        this.mSection = settingsSection;
        this.mBinding.setSection(this.mSection);
        updateContent();
        this.mSection.onShow();
    }

    /* access modifiers changed from: private */
    public void updateContent() {
        this.mBinding.title.setText(this.mSection.getTitle());
        this.mAdapter.setSettingsItems(this.mSection.getFilteredSettingsItems());
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mBinding.backButton.setEventHandler(null);
        this.mBinding.settingsContent.removeOnScrollListener(this.mOnScrollListener);
        SettingsSection settingsSection = this.mSection;
        if (settingsSection != null) {
            settingsSection.destroy();
        }
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        this.mViewModel.dismissDropdown();
        SettingsSection settingsSection = this.mSection;
        if (settingsSection != null) {
            settingsSection.onHide();
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        String str2;
        this.mSettingsLogger.setSection(SettingsSectionUtil.grabSectionFromUri(str));
        SettingsSection sectionFromDeepLink = SettingsSectionUtil.getSectionFromDeepLink(this.mContext, this.mPanelApp, str, new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$AndroidSettingsView$yOqRNGDkQeb54nHvWLtfvPPlg */

            public final void run() {
                AndroidSettingsView.this.updateContent();
            }
        });
        if (sectionFromDeepLink != null) {
            str2 = SettingsSectionUtil.grabSettingFromUri(str);
            updateSettingsSection(sectionFromDeepLink);
        } else {
            str2 = "";
        }
        this.mViewModel.dismissDropdown();
        this.mViewModel.resetScrollEnabled();
        resetScrollIfNecessary(str);
        goToSetting(str2);
    }

    private void goToSetting(String str) {
        if (!TextUtils.isEmpty(str)) {
            List<BaseSettingsItem> filteredSettingsItems = this.mSection.getFilteredSettingsItems();
            for (int i = 0; i < filteredSettingsItems.size(); i++) {
                SettingsItem settingsItem = (SettingsItem) filteredSettingsItems.get(i);
                if (!TextUtils.isEmpty(settingsItem.getName()) && settingsItem.getName().equals(str)) {
                    this.mBinding.settingsContent.scrollToPosition(i);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable(i) {
                        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$AndroidSettingsView$JXgmn1dgXW10z8JzFladVVKu7eY */
                        private final /* synthetic */ int f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            AndroidSettingsView.this.lambda$goToSetting$249$AndroidSettingsView(this.f$1);
                        }
                    }, 1000);
                    handler.postDelayed(new Runnable(i) {
                        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$AndroidSettingsView$9GGBKGJo_j5Wsfonnd2GlWhe8Ls */
                        private final /* synthetic */ int f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            AndroidSettingsView.this.lambda$goToSetting$250$AndroidSettingsView(this.f$1);
                        }
                    }, 5000);
                }
            }
            return;
        }
        this.mBinding.settingsContent.scrollToPosition(0);
    }

    public /* synthetic */ void lambda$goToSetting$249$AndroidSettingsView(int i) {
        this.mAdapter.setSelectedPosition(i);
    }

    public /* synthetic */ void lambda$goToSetting$250$AndroidSettingsView(int i) {
        this.mAdapter.resetSelectedPosition(i);
    }

    private void resetScrollIfNecessary(String str) {
        if (!str.equals(this.mViewModel.getCurrentUri())) {
            this.mViewModel.setCurrentUri(str);
            this.mBinding.settingsContent.scrollToPosition(0);
        }
    }
}
