package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise;

import android.content.Context;
import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.LauncherAction;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFetcher;
import com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.SystemUXRoute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManagedLauncherViewModel extends BaseObservable implements AppViewModel, ViewModelLifecycle {
    private static final String TAG = LoggingUtil.tag(ManagedLauncherViewModel.class);
    private final Context mContext;
    private final List<App> mIngestedApps = new ArrayList();
    private boolean mIsHandTrackingEnabled;
    private final List<LauncherAction> mLauncherActions = new ArrayList();
    private final LibraryFetcher mLibraryFetcher;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private boolean mShowUnknownSources;
    private final List<UnknownSource> mUnknownSources = new ArrayList();

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void executeInitialActionsAfterLoad() {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void flagInitialAppsLoadingState(boolean z) {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void flagInitialUnknownSourcesLoadingState(boolean z) {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void setApp(App app) {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void setApps(List<App> list) {
    }

    public ManagedLauncherViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mLibraryFetcher = new LibraryFetcher(this.mContext, this);
        this.mLibraryFetcher.fetchUnknownSourcesAsync(true);
    }

    @Bindable
    public boolean getIsHandTrackingEnabled() {
        return this.mIsHandTrackingEnabled;
    }

    public void setIsHandTrackingEnabled(boolean z) {
        this.mIsHandTrackingEnabled = z;
        notifyPropertyChanged(BR.isHandTrackingEnabled);
    }

    @Bindable
    public boolean getShowUnknownSources() {
        return this.mShowUnknownSources;
    }

    public void setShowUnknownSources(boolean z) {
        this.mShowUnknownSources = z;
        notifyPropertyChanged(BR.showUnknownSources);
    }

    @Bindable({"isHandTrackingEnabled", "launcherActions", "ingestedApps", "unknownSources"})
    public List<ManagedLauncherItem> getItems() {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        if (getIsHandTrackingEnabled()) {
            arrayList.add(ManagedLauncherItem.createSystemUxRouteApp(this.mContext.getResources().getString(R.string.anytime_tablet_library_fake_app_display_name_hands), SystemUXRoute.HAND_TRACKING_NUX, R.drawable.hands));
        }
        for (LauncherAction launcherAction : getLauncherActions()) {
            ManagedLauncherItem createApp = ManagedLauncherItem.createApp(launcherAction.getAppName(), launcherAction.getPackageName());
            if (hashSet.add(createApp.getPackageName())) {
                arrayList.add(createApp);
            } else {
                Log.d(TAG, String.format("Skip adding launcher action because an item already exists for package: %s", createApp.getPackageName()));
            }
        }
        for (App app : getIngestedApps()) {
            ManagedLauncherItem createIngestedApp = ManagedLauncherItem.createIngestedApp(app);
            if (hashSet.add(createIngestedApp.getPackageName())) {
                arrayList.add(createIngestedApp);
            } else {
                Log.d(TAG, String.format("Skip adding ingested app because an item already exists for package: %s", createIngestedApp.getPackageName()));
            }
        }
        for (UnknownSource unknownSource : getUnknownSources()) {
            ManagedLauncherItem createUnknownSource = ManagedLauncherItem.createUnknownSource(unknownSource);
            if (hashSet.add(createUnknownSource.getPackageName())) {
                arrayList.add(createUnknownSource);
            } else {
                Log.d(TAG, String.format("Skip adding unknown source because an item already exists for package: %s", createUnknownSource.getPackageName()));
            }
        }
        return arrayList;
    }

    @Bindable
    public List<LauncherAction> getLauncherActions() {
        return this.mLauncherActions;
    }

    public void setLauncherActions(List<LauncherAction> list) {
        this.mLauncherActions.clear();
        this.mLauncherActions.addAll(list);
        notifyPropertyChanged(BR.launcherActions);
    }

    @Bindable
    public List<App> getIngestedApps() {
        return this.mIngestedApps;
    }

    public void setIngestedApps(List<App> list) {
        this.mIngestedApps.clear();
        this.mIngestedApps.addAll(list);
        notifyPropertyChanged(BR.ingestedApps);
    }

    @Bindable({"showUnknownSources"})
    public List<UnknownSource> getUnknownSources() {
        return this.mShowUnknownSources ? this.mUnknownSources : new ArrayList();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void setUnknownSources(List<UnknownSource> list) {
        if (!this.mPanelApp.getSystemUXConfig().isDefaultApplicationSet) {
            this.mUnknownSources.clear();
            this.mUnknownSources.addAll(list);
            notifyPropertyChanged(BR.unknownSources);
        }
    }
}
