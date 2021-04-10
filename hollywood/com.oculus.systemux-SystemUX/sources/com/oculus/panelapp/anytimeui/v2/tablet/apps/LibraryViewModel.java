package com.oculus.panelapp.anytimeui.v2.tablet.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.apps.models.AppScoreboardsInfo;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.SupportedPlatform;
import com.oculus.panelapp.anytimeui.utils.DeviceUtils;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.abtest.StoreOptionalityExperiment;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryPlatform;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryCacheHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFakeAppUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFetcher;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryLogger;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryStateHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel;
import com.oculus.tablet.utils.backtotop.ScrollCallback;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.userserver.api.sharing.SharingManager;
import com.oculus.vrshell.util.DeviceProperties;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class LibraryViewModel extends BaseObservable implements AppViewModel, ViewModelLifecycle {
    private static final String TAG = LoggingUtil.tag(LibraryViewModel.class);
    private Map<String, App> mApps = new ConcurrentHashMap();
    private Map<String, Boolean> mAppsWithAchievements = new HashMap();
    private Map<String, Boolean> mAppsWithLeaderboards = new HashMap();
    private Context mContext;
    private LibraryFilter mCurrentFilter;
    private LibraryPlatform mCurrentPlatform;
    private LibrarySorter mCurrentSorter;
    private int mDisableScrollRequestSemaphore;
    private List<App> mFakeApps;
    @Nullable
    private AsyncQueryHandle mFetchAppsScoreboardsAsyncQueryHandle;
    private List<LibraryFilter> mFilterOptions;
    private boolean mHighlightHome;
    private long mInitializationStartTimeMs;
    private boolean mIsAppMigrationDialogBlocked;
    private boolean mIsInternetConnected;
    private boolean mIsLoadingInitialApps;
    private boolean mIsLoadingInitialUnknownSources;
    private boolean mIsScrollEnabled;
    private List<LibraryItem> mItems = new ArrayList();
    private LibraryCacheHelper mLibraryCacheHelper;
    private LibraryFetcher mLibraryFetcher;
    private LibraryLogger mLibraryLogger;
    private LibraryStateHelper mLibraryStateHelper;
    private BroadcastReceiver mPackageChangeBroadcastReceiver;
    private final AnytimeUIPanelAppBase mPanelApp;
    private List<LibraryPlatform> mPlatformOptions;
    private String mPrototypeOrganizationId;
    private ScrollCallback mScrollCallback;
    private SharingManager mSharingManager;
    private boolean mShouldSkipSavingScrollPositionOnDestroy;
    private List<LibrarySorter> mSorterOptions;
    private StoreOptionalityExperiment mStoreOptionalityExperiment;
    private List<UnknownSource> mUnknownSources = new ArrayList();
    private BroadcastReceiver mWifiBroadcastReceiver;

    public LibraryViewModel(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        boolean z = false;
        this.mIsInternetConnected = false;
        this.mIsScrollEnabled = true;
        this.mPrototypeOrganizationId = null;
        this.mHighlightHome = false;
        this.mShouldSkipSavingScrollPositionOnDestroy = false;
        this.mInitializationStartTimeMs = System.currentTimeMillis();
        this.mContext = context;
        this.mPanelApp = anytimeUIPanelAppBase;
        initializeWifiBroadcastReceiver();
        this.mLibraryCacheHelper = new LibraryCacheHelper(this.mContext);
        this.mLibraryFetcher = new LibraryFetcher(this.mContext, this);
        this.mLibraryFetcher.fetchUnknownSourcesAsync(true);
        this.mLibraryStateHelper = new LibraryStateHelper(this.mContext);
        this.mStoreOptionalityExperiment = anytimeUIPanelAppBase.getStoreOptionalityExperiment();
        this.mSharingManager = new SharingManager(this.mContext);
        initializePackageChangeBroadcastReceiver();
        initializePrototypesOrganizationId();
        maybeCreateAppNullStateOrdering();
        this.mIsAppMigrationDialogBlocked = this.mLibraryStateHelper.isAppMigrationDialogBlocked();
        Context context2 = this.mContext;
        LibraryStateHelper libraryStateHelper = this.mLibraryStateHelper;
        boolean isSystemAppsAsTilesEnabled = this.mStoreOptionalityExperiment.isSystemAppsAsTilesEnabled();
        if (!this.mStoreOptionalityExperiment.isStoreAlwaysFirstAppTileEnabled() && this.mStoreOptionalityExperiment.isStorePresentInAppTilesEnabled()) {
            z = true;
        }
        this.mFakeApps = LibraryFakeAppUtils.getFakeApps(context2, libraryStateHelper, isSystemAppsAsTilesEnabled, z);
    }

    private void initializePackageChangeBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_FULLY_REMOVED");
        intentFilter.addDataScheme("package");
        this.mPackageChangeBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_FULLY_REMOVED".equals(action)) {
                    LibraryViewModel.this.mLibraryFetcher.fetchUnknownSourcesAsync(false);
                }
            }
        };
        this.mContext.registerReceiver(this.mPackageChangeBroadcastReceiver, intentFilter);
    }

    private void initializeWifiBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mWifiBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                    NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                    LibraryViewModel.this.mIsInternetConnected = networkInfo != null && networkInfo.isConnected();
                    LibraryViewModel.this.notifyPropertyChanged(BR.items);
                    if (LibraryViewModel.this.mIsInternetConnected) {
                        LibraryViewModel.this.maybeDownloadMigrationAppsStoredForLaterDownload();
                    }
                }
            }
        };
        this.mContext.registerReceiver(this.mWifiBroadcastReceiver, intentFilter);
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mContext.unregisterReceiver(this.mPackageChangeBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mWifiBroadcastReceiver);
        LibraryFetcher libraryFetcher = this.mLibraryFetcher;
        if (libraryFetcher != null) {
            libraryFetcher.destroy();
        }
        clearFetchAppsScoreboardsAsyncQueryHandle();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchAppsScoreboardsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchAppsScoreboardsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchAppsScoreboardsAsyncQueryHandle = null;
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void executeInitialActionsAfterLoad() {
        Log.d(TAG, "Executing Actions after initial apps load.");
        LibraryLogger libraryLogger = this.mLibraryLogger;
        if (libraryLogger != null) {
            libraryLogger.logLibraryInitialApps(this.mApps.size(), LibraryUtils.filterMigratableApps(new ArrayList(this.mApps.values()), this.mPrototypeOrganizationId).size());
        }
        maybeShowAppMigration();
    }

    @Bindable
    public List<LibraryPlatform> getPlatformOptions() {
        return new ArrayList(this.mPlatformOptions);
    }

    public void setPlatformOptions(List<LibraryPlatform> list) {
        if (!Objects.equals(this.mPlatformOptions, list)) {
            Log.d(TAG, "Setting platform options.");
            this.mPlatformOptions = list;
            notifyPropertyChanged(BR.platformOptions);
        }
    }

    @Bindable
    public LibraryPlatform getCurrentPlatform() {
        return this.mCurrentPlatform;
    }

    public void setCurrentPlatform(LibraryPlatform libraryPlatform) {
        if (this.mCurrentPlatform != libraryPlatform) {
            Log.d(TAG, String.format("Setting current platform to be: %s.", libraryPlatform.getLocalizedString(this.mContext)));
            this.mCurrentPlatform = libraryPlatform;
            onDropdownChange();
            notifyPropertyChanged(BR.currentPlatform);
            notifyPropertyChanged(BR.items);
            setFilterOptions(generateFilterOptions());
            notifyPropertyChanged(BR.filterCounts);
        }
    }

    @Bindable
    public List<LibrarySorter> getSorterOptions() {
        return new ArrayList(this.mSorterOptions);
    }

    public void setSorterOptions(List<LibrarySorter> list) {
        if (!Objects.equals(this.mSorterOptions, list)) {
            Log.d(TAG, "Setting sorter options.");
            this.mSorterOptions = list;
            notifyPropertyChanged(BR.sorterOptions);
        }
    }

    @Bindable
    public LibrarySorter getCurrentSorter() {
        return this.mCurrentSorter;
    }

    public void setCurrentSorter(LibrarySorter librarySorter) {
        if (this.mCurrentSorter != librarySorter) {
            Log.d(TAG, String.format("Setting current sorter to be: %s.", librarySorter.getLocalizedString(this.mContext)));
            this.mCurrentSorter = librarySorter;
            onDropdownChange();
            notifyPropertyChanged(BR.currentSorter);
            notifyPropertyChanged(BR.items);
        }
    }

    @Bindable
    public List<LibraryFilter> getFilterOptions() {
        return new ArrayList(this.mFilterOptions);
    }

    public void setFilterOptions(List<LibraryFilter> list) {
        if (!Objects.equals(this.mFilterOptions, list)) {
            Log.d(TAG, "Setting filter options.");
            this.mFilterOptions = list;
            notifyPropertyChanged(BR.filterOptions);
            if (!this.mFilterOptions.contains(this.mCurrentFilter)) {
                setCurrentFilter(this.mFilterOptions.get(0));
            }
        }
    }

    @Bindable
    public LibraryFilter getCurrentFilter() {
        return this.mCurrentFilter;
    }

    public void setCurrentFilter(LibraryFilter libraryFilter) {
        if (this.mCurrentFilter != libraryFilter) {
            Log.d(TAG, String.format("Setting current filter to be: %s.", libraryFilter.getLocalizedString(this.mContext)));
            this.mCurrentFilter = libraryFilter;
            onDropdownChange();
            notifyPropertyChanged(BR.currentFilter);
            notifyPropertyChanged(BR.items);
        }
    }

    public void resetState() {
        resetDropdowns();
        resetScrollPosition();
    }

    public void resetDropdowns() {
        setCurrentPlatform(LibraryPlatform.getDefault());
        setCurrentSorter(LibrarySorter.getDefault());
        setCurrentFilter(LibraryFilter.getDefault());
    }

    public void resetScrollPosition() {
        ScrollCallback scrollCallback = this.mScrollCallback;
        if (scrollCallback != null) {
            scrollCallback.addPendingScrollToPosition(0);
            notifyPropertyChanged(BR.items);
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void setApps(List<App> list) {
        this.mApps.clear();
        maybeFetchAndUpdateAppsScoreboardsInfo(list);
        for (App app : list) {
            this.mApps.put(app.packageName, LibraryAppUtils.adjustApp(this.mContext, app, this.mStoreOptionalityExperiment.isSystemAppsAsTilesEnabled()));
        }
        Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, "Updated Library data source for all apps");
        notifyPropertyChanged(BR.items);
        notifyPropertyChanged(BR.filterCounts);
        if (!has3DofApps(list)) {
            setCurrentPlatform(LibraryPlatform.ANDROID_6DOF);
        }
        setFilterOptions(generateFilterOptions());
        notifyPropertyChanged(BR.platformDropdownEnabled);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void flagInitialAppsLoadingState(boolean z) {
        boolean z2 = this.mIsLoadingInitialApps;
        this.mIsLoadingInitialApps = z;
        notifyPropertyChanged(BR.items);
        if (!z) {
            long currentTimeMillis = System.currentTimeMillis() - this.mInitializationStartTimeMs;
            Log.d(LibraryUtils.LIBRARY_TTI_TAG, String.format("Initial OCMS apps fetch finished, %dms after initialization started.", Long.valueOf(currentTimeMillis)));
        }
        if (z2 && !z && this.mCurrentFilter != LibraryFilter.UNKNOWN_SOURCES) {
            restoreScrollPosition();
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void flagInitialUnknownSourcesLoadingState(boolean z) {
        boolean z2 = this.mIsLoadingInitialUnknownSources;
        this.mIsLoadingInitialUnknownSources = z;
        notifyPropertyChanged(BR.items);
        if (!z) {
            long currentTimeMillis = System.currentTimeMillis() - this.mInitializationStartTimeMs;
            Log.d(LibraryUtils.LIBRARY_TTI_TAG, String.format("Initial unknown sources fetch finished, %dms after initialization started.", Long.valueOf(currentTimeMillis)));
        }
        if (z2 && !z && this.mCurrentFilter == LibraryFilter.UNKNOWN_SOURCES) {
            restoreScrollPosition();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void restoreScrollPosition() {
        this.mScrollCallback.addPendingScrollToPosition(this.mLibraryStateHelper.loadScrollPositionState());
        notifyPropertyChanged(BR.items);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void setApp(App app) {
        String str;
        App adjustApp = LibraryAppUtils.adjustApp(this.mContext, app, this.mStoreOptionalityExperiment.isSystemAppsAsTilesEnabled());
        this.mApps.put(adjustApp.packageName, adjustApp);
        String str2 = LibraryUtils.LIBRARY_SYNCING_TAG;
        Object[] objArr = new Object[3];
        objArr[0] = adjustApp.packageName;
        objArr[1] = adjustApp.status.toString();
        if (adjustApp.status == AppStatus.DOWNLOADING) {
            str = ", " + adjustApp.downloadedSizeBytes + "/" + adjustApp.downloadSizeBytes;
        } else {
            str = "";
        }
        objArr[2] = str;
        Log.d(str2, String.format("Updated Library data source for app: %s, current state: %s%s", objArr));
        notifyPropertyChanged(BR.items);
        setFilterOptions(generateFilterOptions());
        notifyPropertyChanged(BR.filterCounts);
        maybeFetchAndUpdateAppsScoreboardsInfo(Collections.singletonList(adjustApp));
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel
    public void setUnknownSources(List<UnknownSource> list) {
        this.mUnknownSources.clear();
        this.mUnknownSources.addAll(list);
        notifyPropertyChanged(BR.items);
        notifyPropertyChanged(BR.filterCounts);
    }

    @Bindable
    public List<LibraryItem> getItems() {
        this.mItems.clear();
        if (this.mCurrentFilter == LibraryFilter.UNKNOWN_SOURCES) {
            if (this.mIsLoadingInitialUnknownSources) {
                return this.mItems;
            }
            this.mItems.add(new LibraryItem(LibraryItem.ItemType.HEADER_UNKNOWN_SOURCES));
            ArrayList<UnknownSource> arrayList = new ArrayList(this.mUnknownSources);
            if (arrayList.isEmpty()) {
                this.mItems.add(new LibraryItem(this.mCurrentFilter));
            } else {
                LibraryUtils.sortUnknownSourcesBySorter(arrayList, this.mCurrentSorter);
                for (UnknownSource unknownSource : arrayList) {
                    this.mItems.add(new LibraryItem(unknownSource));
                }
            }
            return this.mItems;
        } else if (this.mIsLoadingInitialApps) {
            return this.mItems;
        } else {
            List<App> filterAppsByFilter = LibraryUtils.filterAppsByFilter(getPreFilteredApps(), this.mCurrentFilter, this.mPrototypeOrganizationId);
            LibraryUtils.sortAppsBySorter(filterAppsByFilter, this.mCurrentSorter);
            LibraryUtils.promoteAvailableApps(filterAppsByFilter, this.mIsInternetConnected, !this.mPanelApp.acquireSettingsViewModel().getIsTrackingIn3DOFMode());
            this.mPanelApp.releaseSettingsViewModel();
            boolean z = false;
            if (this.mStoreOptionalityExperiment.isStoreAlwaysFirstAppTileEnabled() && (this.mCurrentFilter == LibraryFilter.ALL || this.mCurrentFilter == LibraryFilter.OCULUS_APPS || this.mCurrentFilter == LibraryFilter.INSTALLED)) {
                filterAppsByFilter.add(0, LibraryFakeAppUtils.getStoreSystemApp(this.mContext, this.mLibraryStateHelper));
            }
            if (this.mStoreOptionalityExperiment.isLibraryHeaderEnabled()) {
                this.mItems.add(new LibraryItem(this.mApps.getOrDefault("com.oculus.browser", null), this.mApps.getOrDefault("com.oculus.tv", null)));
            }
            if (this.mCurrentPlatform == LibraryPlatform.ANDROID_3DOF && !this.mLibraryStateHelper.loadGoGearVrBannerDismissedState()) {
                this.mItems.add(new LibraryItem(LibraryItem.ItemType.GO_GEAR_VR_BANNER));
            }
            if (!filterAppsByFilter.isEmpty()) {
                for (App app : filterAppsByFilter) {
                    this.mItems.add(new LibraryItem(app));
                }
            }
            if (this.mStoreOptionalityExperiment.isGetMoreAppsButtonEnabled()) {
                this.mItems.add(new LibraryItem(LibraryItem.ItemType.GET_MORE_APPS));
            }
            if (filterAppsByFilter.isEmpty()) {
                if (this.mCurrentFilter != LibraryFilter.UPDATES) {
                    this.mItems.add(new LibraryItem(this.mCurrentFilter));
                } else {
                    Iterator<String> it = LibraryUtils.SYSTEM_APP_PACKAGE_NAMES.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        App orDefault = this.mApps.getOrDefault(it.next(), null);
                        if (orDefault != null && orDefault.status == AppStatus.INSTALLED && orDefault.versionCode < orDefault.latestVersionCode) {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        this.mItems.add(new LibraryItem(this.mCurrentFilter));
                    }
                }
            }
            return this.mItems;
        }
    }

    @Bindable
    public Map<LibraryFilter, Integer> getFilterCounts() {
        HashMap hashMap = new HashMap();
        List<App> preFilteredApps = getPreFilteredApps();
        if (this.mStoreOptionalityExperiment.isStoreAlwaysFirstAppTileEnabled()) {
            preFilteredApps.add(LibraryFakeAppUtils.getStoreSystemApp(this.mContext, this.mLibraryStateHelper));
        }
        for (LibraryFilter libraryFilter : this.mFilterOptions) {
            if (libraryFilter == LibraryFilter.UNKNOWN_SOURCES) {
                hashMap.put(LibraryFilter.UNKNOWN_SOURCES, Integer.valueOf(this.mUnknownSources.size()));
            } else {
                List<App> filterAppsByFilter = LibraryUtils.filterAppsByFilter(preFilteredApps, libraryFilter, this.mPrototypeOrganizationId);
                if (libraryFilter == LibraryFilter.UPDATES && !this.mStoreOptionalityExperiment.isSystemAppsAsTilesEnabled()) {
                    maybeAddSystemAppToFilteredList(this.mApps.getOrDefault("com.oculus.browser", null), filterAppsByFilter);
                    maybeAddSystemAppToFilteredList(this.mApps.getOrDefault("com.oculus.tv", null), filterAppsByFilter);
                }
                hashMap.put(libraryFilter, Integer.valueOf(filterAppsByFilter.size()));
            }
        }
        return hashMap;
    }

    private List<App> getPreFilteredApps() {
        boolean isSystemAppsAsTilesEnabled = this.mStoreOptionalityExperiment.isSystemAppsAsTilesEnabled();
        ArrayList arrayList = new ArrayList(this.mApps.values());
        arrayList.addAll(this.mFakeApps);
        List<App> excludeHiddenApps = LibraryUtils.excludeHiddenApps(arrayList);
        if (!isSystemAppsAsTilesEnabled) {
            excludeHiddenApps = LibraryUtils.excludeSystemApps(excludeHiddenApps);
        }
        return LibraryUtils.filterAppsByPlatform(LibraryUtils.excludeIncompatibleApps(excludeHiddenApps), this.mCurrentPlatform, isSystemAppsAsTilesEnabled);
    }

    private void maybeAddSystemAppToFilteredList(App app, List<App> list) {
        if (LibraryUtils.appHasPendingUpdate(app)) {
            list.add(app);
        }
    }

    @Bindable
    public boolean getPlatformDropdownEnabled() {
        return DeviceProperties.supports3DoFAppsLibrary() && has3DofApps(new ArrayList(this.mApps.values()));
    }

    private boolean has3DofApps(List<App> list) {
        for (App app : this.mApps.values()) {
            if (app.platform == SupportedPlatform.ANDROID) {
                return true;
            }
        }
        return false;
    }

    public LibraryFetcher getLibraryFetcher() {
        return this.mLibraryFetcher;
    }

    public void setScrollCallback(ScrollCallback scrollCallback) {
        this.mScrollCallback = scrollCallback;
    }

    public LibraryStateHelper getLibraryStateHelper() {
        return this.mLibraryStateHelper;
    }

    private List<LibraryFilter> generateFilterOptions() {
        ArrayList arrayList;
        if (this.mCurrentPlatform == LibraryPlatform.ANDROID_6DOF) {
            arrayList = new ArrayList(Arrays.asList(LibraryFilter.ALL, LibraryFilter.OCULUS_APPS, LibraryFilter.INSTALLED, LibraryFilter.UPDATES, LibraryFilter.NOT_INSTALLED, LibraryFilter.DEMOS, LibraryFilter.TUTORIALS));
            if (DeviceUtils.isADBEnabled(this.mContext)) {
                arrayList.add(LibraryFilter.UNKNOWN_SOURCES);
            }
        } else {
            arrayList = new ArrayList(Arrays.asList(LibraryFilter.ALL, LibraryFilter.INSTALLED, LibraryFilter.UPDATES, LibraryFilter.NOT_INSTALLED));
        }
        if (this.mPanelApp.isGKEnabled(Gatekeeper.TRUSTED_USER) && LibraryUtils.appsContainPrototypes(this.mApps.values(), this.mCurrentPlatform, this.mPrototypeOrganizationId)) {
            arrayList.add(LibraryFilter.PROTOTYPES);
        }
        if (!isCurrentUserDeviceAdmin() && isLibrarySharingEnabled()) {
            arrayList.add(LibraryFilter.SHARED);
        }
        return arrayList;
    }

    public void updateFakeApp(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mLibraryStateHelper.saveFakeAppState(str, false, currentTimeMillis);
        int i = 0;
        while (true) {
            if (i >= this.mFakeApps.size()) {
                break;
            } else if (str.equals(this.mFakeApps.get(i).packageName)) {
                List<App> list = this.mFakeApps;
                list.set(i, new App.Builder(list.get(i)).withUnseen(false).withRecentActivityMs(currentTimeMillis).build());
                break;
            } else {
                i++;
            }
        }
        notifyPropertyChanged(BR.items);
    }

    public void updateTracking() {
        notifyPropertyChanged(BR.items);
    }

    public boolean getIsInternetConnected() {
        return this.mIsInternetConnected;
    }

    public boolean getIsScrollEnabled() {
        return this.mIsScrollEnabled;
    }

    public void setScrollEnabled(boolean z) {
        this.mDisableScrollRequestSemaphore += z ? -1 : 1;
        boolean z2 = false;
        if (this.mDisableScrollRequestSemaphore < 0) {
            this.mDisableScrollRequestSemaphore = 0;
        }
        if (this.mDisableScrollRequestSemaphore == 0) {
            z2 = true;
        }
        this.mIsScrollEnabled = z2;
    }

    public void resetScrollEnabled() {
        this.mDisableScrollRequestSemaphore = 0;
        this.mIsScrollEnabled = true;
    }

    public void dismissGoGearVrBanner() {
        this.mLibraryStateHelper.saveGoGearVrBannerDismissedState(true);
        notifyPropertyChanged(BR.items);
    }

    private void onDropdownChange() {
        this.mLibraryStateHelper.saveDropdownsState(this.mCurrentPlatform, this.mCurrentSorter, this.mCurrentFilter);
        ScrollCallback scrollCallback = this.mScrollCallback;
        if (scrollCallback != null) {
            scrollCallback.addPendingScrollToPosition(0);
        }
    }

    public void loadAppsFromCacheAsync() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$LibraryViewModel$sb5fjrasJpnG592vs2GBT87Q */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LibraryViewModel.this.lambda$loadAppsFromCacheAsync$74$LibraryViewModel();
            }
        }, new ThreadExecutor.Listener<List<App>>() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel.AnonymousClass3 */

            @Override // com.oculus.vrshell.util.ThreadExecutor.Listener
            public void onFailure(Throwable th) {
                Log.e(LibraryViewModel.TAG, "Exception while loading cached apps: %s", th);
            }

            public void onSuccess(List<App> list) {
                long currentTimeMillis = System.currentTimeMillis() - LibraryViewModel.this.mInitializationStartTimeMs;
                if (list == null || list.size() == 0) {
                    Log.d(LibraryUtils.LIBRARY_TTI_TAG, String.format("Apps cache is invalid or empty, %dms after initialization started.", Long.valueOf(currentTimeMillis)));
                } else if (!LibraryViewModel.this.mIsLoadingInitialApps) {
                    Log.d(LibraryUtils.LIBRARY_TTI_TAG, String.format("Apps cache fetched but abandoned, %dms after initialization started, because the initial OCMS fetch is already finished.", Long.valueOf(currentTimeMillis)));
                } else {
                    LibraryViewModel.this.mIsLoadingInitialApps = false;
                    LibraryViewModel.this.setApps(list);
                    Log.d(LibraryUtils.LIBRARY_TTI_TAG, String.format("Apps cache fetched and populated successfully, %dms after initialization started.", Long.valueOf(currentTimeMillis)));
                    if (LibraryViewModel.this.mCurrentFilter != LibraryFilter.UNKNOWN_SOURCES) {
                        LibraryViewModel.this.restoreScrollPosition();
                    }
                }
            }
        });
    }

    public /* synthetic */ List lambda$loadAppsFromCacheAsync$74$LibraryViewModel() throws Exception {
        return this.mLibraryCacheHelper.loadApps();
    }

    public void saveAppsToCache() {
        this.mLibraryCacheHelper.saveApps(new ArrayList(this.mApps.values()));
    }

    public Map<String, Boolean> getAppsWithAchievements() {
        return this.mAppsWithAchievements;
    }

    public Map<String, Boolean> getAppsWithLeaderboards() {
        return this.mAppsWithLeaderboards;
    }

    private void maybeFetchAndUpdateAppsScoreboardsInfo(List<App> list) {
        HashMap hashMap = new HashMap();
        for (App app : list) {
            if (!this.mApps.containsKey(app.packageName)) {
                hashMap.put(app.id, app);
            }
        }
        if (hashMap.size() != 0) {
            clearFetchAppsScoreboardsAsyncQueryHandle();
            new Handler(this.mContext.getMainLooper()).post(new Runnable(hashMap) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$LibraryViewModel$XRi1Uj3h8Pz_HIQKiJnLEWVMff4 */
                private final /* synthetic */ Map f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    LibraryViewModel.this.lambda$maybeFetchAndUpdateAppsScoreboardsInfo$75$LibraryViewModel(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$maybeFetchAndUpdateAppsScoreboardsInfo$75$LibraryViewModel(final Map map) {
        this.mFetchAppsScoreboardsAsyncQueryHandle = HorizonContentProviderHelper.fetchAppsScoreboardsInfo(this.mContext, (String[]) map.keySet().toArray(new String[0]), new HorizonContentProviderHelper.FetchAppsScoreboardsInfoCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchAppsScoreboardsInfoCallback
            public void onSuccess(List<AppScoreboardsInfo> list) {
                for (AppScoreboardsInfo appScoreboardsInfo : list) {
                    App app = (App) map.get(appScoreboardsInfo.getAppId());
                    if (app != null) {
                        LibraryViewModel.this.mAppsWithAchievements.put(app.packageName, Boolean.valueOf(appScoreboardsInfo.getHasAchievements()));
                        LibraryViewModel.this.mAppsWithLeaderboards.put(app.packageName, Boolean.valueOf(appScoreboardsInfo.getHasLeaderboards()));
                    }
                }
                LibraryViewModel.this.clearFetchAppsScoreboardsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(LibraryViewModel.TAG, "Error when fetching scoreboards info");
                LibraryViewModel.this.clearFetchAppsScoreboardsAsyncQueryHandle();
            }
        });
    }

    private boolean isCurrentUserDeviceAdmin() {
        UserManager userManager = (UserManager) this.mContext.getSystemService("user");
        return userManager != null && userManager.isSystemUser();
    }

    private boolean isLibrarySharingEnabled() {
        SharingManager sharingManager = this.mSharingManager;
        if (sharingManager != null) {
            try {
                return sharingManager.getLibrarySharingEnabled();
            } catch (SecurityException unused) {
            }
        }
        return false;
    }

    private void initializePrototypesOrganizationId() {
        this.mPrototypeOrganizationId = DeviceConfigHelper.getString(this.mContext, Gatekeeper.LIBRARY_PROTOTYPE_ORGANIZATION_ID);
        Log.d(TAG, String.format("Organization Id fetched with Device Config: %s", this.mPrototypeOrganizationId));
    }

    public String getPrototypeOrganizationId() {
        return this.mPrototypeOrganizationId;
    }

    private void maybeCreateAppNullStateOrdering() {
        if (this.mStoreOptionalityExperiment.isSystemAppsAsTilesEnabled()) {
            LibraryUtils.createNullStateOrdering(this.mContext, this.mLibraryStateHelper);
        }
    }

    public void maybeShowAppMigration() {
        if (!LibraryAppMigration.shouldShowAppMigrationDialog(this.mLibraryStateHelper, this.mPanelApp.getSystemUXConfig().isEnterpriseMode, this.mPanelApp.isOCShellAutomationEnabled(), this.mIsAppMigrationDialogBlocked, this.mPanelApp.isUserDeviceOwner())) {
            Log.d(TAG, "Not showing App Migration dialog, already been shown once");
            return;
        }
        Log.d(TAG, "Showing App Migration dialog");
        LibraryAppMigration libraryAppMigration = new LibraryAppMigration(this.mPanelApp, this.mLibraryStateHelper);
        libraryAppMigration.filterAndSortApps(new ArrayList(this.mApps.values()), this.mPrototypeOrganizationId);
        libraryAppMigration.maybeLaunchAppMigrationDialog();
    }

    public void maybeDownloadMigrationAppsStoredForLaterDownload() {
        if (!LibraryAppMigration.shouldDownloadMigrationAppsStoredForLaterDownload(this.mLibraryStateHelper)) {
            Log.d(TAG, "No apps marked for later download from App Migration Dialog");
            return;
        }
        Log.d(TAG, "Installing Migration Apps that were stored for later install with internet connection");
        new LibraryAppMigration(this.mPanelApp, this.mLibraryStateHelper).fetchAndDownloadAppsStoredForLater();
    }

    @Bindable
    public boolean getHeaderColoredSystemAppsEnabled() {
        return this.mStoreOptionalityExperiment.isLibraryHeaderEnabledWithColoredTiles();
    }

    @Bindable
    public boolean getEventsEntryEnabled() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_EVENTS_PANEL_ENTRY);
    }

    public void setHighlightHome(boolean z) {
        this.mHighlightHome = z;
        notifyPropertyChanged(BR.highlightHome);
    }

    @Bindable
    public boolean getHighlightHome() {
        return this.mHighlightHome;
    }

    public void setLibraryLogger(LibraryLogger libraryLogger) {
        this.mLibraryLogger = libraryLogger;
    }

    public void onFinishedOnboardingTutorial() {
        resetScrollEnabled();
    }

    public boolean getShouldSkipSavingScrollPositionOnDestroy() {
        return this.mShouldSkipSavingScrollPositionOnDestroy;
    }

    public void setShouldSkipSavingScrollPositionOnDestroy(boolean z) {
        this.mShouldSkipSavingScrollPositionOnDestroy = z;
    }
}
