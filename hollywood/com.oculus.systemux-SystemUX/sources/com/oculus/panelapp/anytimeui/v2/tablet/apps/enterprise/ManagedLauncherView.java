package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.LauncherAction;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.utils.backtotop.BackToTopButtonManager;
import com.oculus.tablet.utils.backtotop.BackToTopButtonProvider;
import com.oculus.tablet.utils.backtotop.PendingScrollUpdate;
import com.oculus.tablet.utils.backtotop.ScrollCallback;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ManagedLauncherView extends BaseView {
    private static final List<String> ACTIONS = Arrays.asList(APP_INSTALLED_ACTION, APP_UNINSTALLED_ACTION, CONFIG_CHANGED_ACTION);
    private static final String APP_INSTALLED_ACTION = "com.oculus.alpenglow.config.APP_INSTALLED";
    private static final String APP_UNINSTALLED_ACTION = "com.oculus.alpenglow.config.APP_UNINSTALLED";
    private static final String CONFIG_CHANGED_ACTION = "com.oculus.alpenglow.config.CONFIG_CHANGED";
    private static final int NUM_TILES_PER_ROW = 4;
    private static final String TAG = LoggingUtil.tag(ManagedLauncherView.class);
    private BackToTopButtonManager mBackToTopButtonManager;
    private AnytimeTabletManagedLauncherViewV2Binding mBinding;
    private Config mConfig;
    private ManagedLauncherContentAdapter mContentAdapter;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private PendingScrollUpdate mPendingScrollUpdate = new PendingScrollUpdate();
    private ManagedLauncherBroadcastReceiver mReceiver;
    private ScrollCallback mScrollCallback;
    private ManagedLauncherViewModel mViewModel;

    public ManagedLauncherView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing ManagedLauncherView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing ManagedLauncherView");
        this.mBinding = (AnytimeTabletManagedLauncherViewV2Binding) viewDataBinding;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mConfig = Config.readEnterprise(getContext());
        this.mBackToTopButtonManager = new BackToTopButtonManager(getContext());
        this.mViewModel = anytimeUIAndroidPanelAppV2.acquireManagedLauncherViewModel();
        initializeScrollCallback();
        initializeLauncherContent();
        initializeBackToTopButton();
        this.mBinding.setViewModel(this.mViewModel);
        this.mContentAdapter = new ManagedLauncherContentAdapter(getContext(), this.mPanelApp, this.mScrollCallback);
        this.mBinding.managedLauncherContent.setHasFixedSize(true);
        this.mBinding.managedLauncherContent.setAdapter(this.mContentAdapter);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        this.mBinding.managedLauncherContent.setLayoutManager(gridLayoutManager);
        this.mBinding.managedLauncherContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherView.AnonymousClass1 */

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                int findFirstVisibleItemPosition;
                super.onScrolled(recyclerView, i, i2);
                if (i2 != 0 && ManagedLauncherView.this.mBackToTopButtonManager != null && (findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()) >= 0) {
                    ManagedLauncherView.this.mBackToTopButtonManager.doActionBasedOnPosition(findFirstVisibleItemPosition);
                }
            }
        });
        this.mReceiver = new ManagedLauncherBroadcastReceiver();
        for (String str : ACTIONS) {
            getContext().registerReceiver(this.mReceiver, new IntentFilter(str));
        }
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.APPS_TABLET);
    }

    private void initializeScrollCallback() {
        this.mScrollCallback = new ScrollCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherView.AnonymousClass2 */

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void onUpdate() {
                int scrollToPosition;
                if (ManagedLauncherView.this.mPendingScrollUpdate != null && ManagedLauncherView.this.mBinding != null && ManagedLauncherView.this.mBinding.managedLauncherContent != null && ManagedLauncherView.this.mPendingScrollUpdate.hasPendingScrollAction()) {
                    Log.d(ManagedLauncherView.TAG, String.format("Scroll to position: %d", Integer.valueOf(ManagedLauncherView.this.mPendingScrollUpdate.getScrollToPosition())));
                    ManagedLauncherView.this.mBinding.managedLauncherContent.scrollToPosition(ManagedLauncherView.this.mPendingScrollUpdate.getScrollToPosition());
                    if (ManagedLauncherView.this.mBackToTopButtonManager != null && (scrollToPosition = ManagedLauncherView.this.mPendingScrollUpdate.getScrollToPosition()) >= 0) {
                        ManagedLauncherView.this.mBackToTopButtonManager.doActionBasedOnPosition(scrollToPosition);
                    }
                    ManagedLauncherView.this.mPendingScrollUpdate.resetPendingScrollAction();
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void addPendingScrollToPosition(int i) {
                if (ManagedLauncherView.this.mPendingScrollUpdate != null) {
                    ManagedLauncherView.this.mPendingScrollUpdate.addPendingScrollToPosition(i);
                    Log.d(ManagedLauncherView.TAG, String.format("Setting pending scroll position: %d", Integer.valueOf(ManagedLauncherView.this.mPendingScrollUpdate.getScrollToPosition())));
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void smoothScrollToPosition(int i) {
                if (ManagedLauncherView.this.mBinding != null && ManagedLauncherView.this.mBinding.managedLauncherContent != null) {
                    ManagedLauncherView.this.mBinding.managedLauncherContent.smoothScrollToPosition(i);
                }
            }
        };
    }

    private void initializeBackToTopButton() {
        this.mBinding.backToTopButton.setEventHandler(this.mPanelApp);
        this.mBackToTopButtonManager.initialize(new BackToTopButtonProvider(this.mBinding.backToTopButtonHeightLayout, this.mBinding.backToTopButton), this.mScrollCallback, null);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying ManagedLauncherView");
        getContext().unregisterReceiver(this.mReceiver);
        this.mReceiver = null;
        this.mContentAdapter.destroy();
        this.mPanelApp.releaseManagedLauncherViewModel();
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing ManagedLauncherView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding ManagedLauncherView");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initializeLauncherContent() {
        this.mViewModel.setIsHandTrackingEnabled(this.mPanelApp.getSystemUXConfig().isEnterpriseHandTrackingEnabled);
        this.mViewModel.setShowUnknownSources(this.mConfig.showUnknownSources);
        if (this.mPanelApp.getSystemUXConfig().isDefaultApplicationSet) {
            this.mViewModel.setLauncherActions(new ArrayList());
            this.mViewModel.setIngestedApps(new ArrayList());
            return;
        }
        this.mViewModel.setLauncherActions(getLauncherActions());
        this.mViewModel.setIngestedApps(getIngestedApps());
    }

    private ArrayList<LauncherAction> getLauncherActions() {
        HashMap hashMap = new HashMap();
        for (LauncherAction launcherAction : this.mConfig.launcherActions) {
            try {
                this.mPanelApp.getContext().createPackageContext(launcherAction.getPackageName(), 2).getPackageManager().getApplicationInfo(launcherAction.getPackageName(), 128);
                if (!hashMap.containsKey(launcherAction.getPackageName())) {
                    hashMap.put(launcherAction.getPackageName(), launcherAction);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        ArrayList<LauncherAction> arrayList = new ArrayList<>();
        arrayList.addAll(hashMap.values());
        return arrayList;
    }

    private List<App> getIngestedApps() {
        Log.d(TAG, "getting the applications from ovr library");
        List<App> applications = HorizonUtil.getApplications(getContext());
        String str = TAG;
        Log.d(str, "successfully retrieved the ovr library: " + applications.size());
        List<App> excludeHiddenApps = LibraryUtils.excludeHiddenApps(applications);
        ArrayList arrayList = new ArrayList();
        for (App app : excludeHiddenApps) {
            if (app.status == AppStatus.INSTALLED) {
                arrayList.add(app);
            }
        }
        return arrayList;
    }

    private class ManagedLauncherBroadcastReceiver extends BroadcastReceiver {
        private ManagedLauncherBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String str = ManagedLauncherView.TAG;
            Log.d(str, "Managed launcher received an intent: " + action);
            if (!ManagedLauncherView.ACTIONS.contains(action)) {
                String str2 = ManagedLauncherView.TAG;
                Log.w(str2, "Managed launcher received an unknown intent: " + action);
                return;
            }
            Config readEnterprise = Config.readEnterprise(ManagedLauncherView.this.getContext());
            if (!action.equals(ManagedLauncherView.CONFIG_CHANGED_ACTION) || ManagedLauncherView.this.mConfig.showUnknownSources != readEnterprise.showUnknownSources) {
                String str3 = ManagedLauncherView.TAG;
                Log.d(str3, "Reloading apps due to: " + action);
                ManagedLauncherView.this.mConfig = readEnterprise;
                ManagedLauncherView.this.initializeLauncherContent();
                return;
            }
            Log.d(ManagedLauncherView.TAG, "Skip reloading apps since unknown sources didn't change for CONFIG_CHANGED event.");
        }
    }
}
