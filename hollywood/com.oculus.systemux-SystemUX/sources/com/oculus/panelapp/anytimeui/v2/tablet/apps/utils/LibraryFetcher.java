package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import com.oculus.panelapp.anytimeui.v2.tablet.common.AppViewModel;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.PackageUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class LibraryFetcher {
    private static final String FETCH_ALL_APPS_KEY = "all_apps";
    private static final String INTENT_CATEGORY_VR = "com.oculus.intent.category.VR";
    private static final String METADATA_KEY_VR_MODE = "com.samsung.android.vr.application.mode";
    private static final String METADATA_VR_MODE_DUAL = "dual";
    private static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    private static final String TAG = LoggingUtil.tag(LibraryFetcher.class);
    private Context mContext;
    private boolean mHasPendingUnknownSourcesFetch;
    private boolean mIsFetchingUnknownSources;
    private Map<String, LibraryAppFetch> mLibraryAppFetches = new HashMap();
    private AppViewModel mViewModel;

    public LibraryFetcher(Context context, AppViewModel appViewModel) {
        this.mContext = context;
        this.mViewModel = appViewModel;
    }

    public synchronized void destroy() {
        for (LibraryAppFetch libraryAppFetch : this.mLibraryAppFetches.values()) {
            libraryAppFetch.stopFetchIfRunning();
        }
    }

    public void fetchHorizonAppsAsync(boolean z) {
        if (z) {
            setInitialAppsLoadingState(true);
        }
        fetchHorizonAppAsync(FETCH_ALL_APPS_KEY, z);
    }

    public void fetchHorizonAppAsync(String str) {
        fetchHorizonAppAsync(str, false);
    }

    public void fetchUnknownSourcesAsync(boolean z) {
        if (z) {
            setInitialUnknownSourcesLoadingState(true);
        }
        if (this.mIsFetchingUnknownSources) {
            this.mHasPendingUnknownSourcesFetch = true;
            return;
        }
        this.mIsFetchingUnknownSources = true;
        executeFetchUnknownSources(z);
    }

    public void executeFetchUnknownSources(final boolean z) {
        this.mIsFetchingUnknownSources = true;
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryFetcher$vuYoUej0VJZ5V5WEmQJ9adaQimU */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LibraryFetcher.this.lambda$executeFetchUnknownSources$98$LibraryFetcher();
            }
        }, new ThreadExecutor.Listener<List<UnknownSource>>() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFetcher.AnonymousClass1 */

            @Override // com.oculus.vrshell.util.ThreadExecutor.Listener
            public void onFailure(Throwable th) {
                Log.e(LibraryFetcher.TAG, "Exception while getting unknown sources", th);
                LibraryFetcher.this.mViewModel.setUnknownSources(new ArrayList());
                LibraryFetcher.this.mIsFetchingUnknownSources = false;
                if (z) {
                    LibraryFetcher.this.setInitialUnknownSourcesLoadingState(false);
                }
                verifyPendingFetches();
            }

            public void onSuccess(List<UnknownSource> list) {
                Log.d(LibraryFetcher.TAG, "Successfully fetched unknown sources");
                LibraryFetcher.this.mViewModel.setUnknownSources(list);
                if (z) {
                    LibraryFetcher.this.setInitialUnknownSourcesLoadingState(false);
                }
                verifyPendingFetches();
            }

            private void verifyPendingFetches() {
                if (LibraryFetcher.this.mHasPendingUnknownSourcesFetch) {
                    LibraryFetcher.this.mHasPendingUnknownSourcesFetch = false;
                    Log.d(LibraryFetcher.TAG, "Executing pending fetch for unknown sources");
                    LibraryFetcher.this.executeFetchUnknownSources(false);
                    return;
                }
                LibraryFetcher.this.mIsFetchingUnknownSources = false;
            }
        });
    }

    public /* synthetic */ List lambda$executeFetchUnknownSources$98$LibraryFetcher() throws Exception {
        PackageManager packageManager = this.mContext.getPackageManager();
        ArrayList arrayList = new ArrayList();
        HashSet<String> hashSet = new HashSet<>();
        for (App app : HorizonUtil.getApplications(this.mContext)) {
            hashSet.add(app.packageName);
        }
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent(PackageUtil.INTENT_ACTION_PANEL), 128)) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (isUnknownSource(serviceInfo.applicationInfo, hashSet)) {
                arrayList.add(new UnknownSource(serviceInfo.applicationInfo.packageName + "/" + serviceInfo.name, serviceInfo.name, (double) getApkSize(serviceInfo.applicationInfo.sourceDir), getLastModifiedDate(serviceInfo.applicationInfo.sourceDir)));
            }
        }
        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(128)) {
            if (isUnknownSource(applicationInfo, hashSet)) {
                arrayList.add(new UnknownSource(applicationInfo.packageName, packageManager.getApplicationLabel(applicationInfo).toString(), (double) getApkSize(applicationInfo.sourceDir), getLastModifiedDate(applicationInfo.sourceDir)));
            }
        }
        return arrayList;
    }

    public boolean isFetchingUnknownSources() {
        return this.mIsFetchingUnknownSources;
    }

    private synchronized void fetchHorizonAppAsync(String str, boolean z) {
        Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Checking if there is currently an app fetch in progress for: %s", str));
        if ((!this.mLibraryAppFetches.containsKey(str) || this.mLibraryAppFetches.get(str).isAppFetchTimedOut()) && (!this.mLibraryAppFetches.containsKey(FETCH_ALL_APPS_KEY) || this.mLibraryAppFetches.get(FETCH_ALL_APPS_KEY).isAppFetchTimedOut())) {
            Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("No existing app fetch in progress, we are clear to execute the app fetch for: %s", str));
            executeFetchHorizonApps(str, z);
            return;
        }
        if (!this.mLibraryAppFetches.containsKey(str)) {
            this.mLibraryAppFetches.put(str, new LibraryAppFetch(str));
        }
        this.mLibraryAppFetches.get(str).addPendingFetch();
        Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Existing app fetch for %s or for All apps is currently in progress, adding a pending app fetch", str));
    }

    private synchronized void executeFetchHorizonApps(String str, boolean z) {
        ThreadExecutor.ConcurrentListenableScheduledFuture concurrentListenableScheduledFuture;
        if (!this.mLibraryAppFetches.containsKey(str)) {
            this.mLibraryAppFetches.put(str, new LibraryAppFetch(str));
        }
        LibraryAppFetch libraryAppFetch = this.mLibraryAppFetches.get(str);
        if (FETCH_ALL_APPS_KEY.equals(str)) {
            concurrentListenableScheduledFuture = getThreadExecutorForAllAppsLoad(z);
        } else {
            concurrentListenableScheduledFuture = getThreadExecutorForAppLoad(str);
        }
        libraryAppFetch.startFetch(concurrentListenableScheduledFuture);
    }

    private ThreadExecutor.ConcurrentListenableScheduledFuture getThreadExecutorForAllAppsLoad(final boolean z) {
        Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Starting fetch for all apps. Initial fetch: %b", Boolean.valueOf(z)));
        return ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryFetcher$No4TAovtTtLC7f5qft6Gir36SE */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LibraryFetcher.this.lambda$getThreadExecutorForAllAppsLoad$99$LibraryFetcher();
            }
        }, new ThreadExecutor.Listener<List<App>>() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFetcher.AnonymousClass2 */

            @Override // com.oculus.vrshell.util.ThreadExecutor.Listener
            public void onFailure(Throwable th) {
                Log.e(LibraryUtils.LIBRARY_SYNCING_TAG, "Exception while fetching all apps", th);
                verifyPendingFetches();
                if (z) {
                    LibraryFetcher.this.setInitialAppsLoadingState(false);
                }
            }

            public void onSuccess(List<App> list) {
                Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, "Successfully fetched all apps");
                if (LibraryFetcher.this.mViewModel != null) {
                    LibraryFetcher.this.mViewModel.setApps(list);
                    Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, "Fetched data for all apps from OCMS");
                    if (z) {
                        LibraryFetcher.this.setInitialAppsLoadingState(false);
                        LibraryFetcher.this.mViewModel.executeInitialActionsAfterLoad();
                    }
                }
                verifyPendingFetches();
            }

            private void verifyPendingFetches() {
                if (!LibraryFetcher.this.verifyPendingFetches(LibraryFetcher.FETCH_ALL_APPS_KEY)) {
                    for (LibraryAppFetch libraryAppFetch : LibraryFetcher.this.mLibraryAppFetches.values()) {
                        if (!LibraryFetcher.FETCH_ALL_APPS_KEY.equals(libraryAppFetch.getPackageName())) {
                            LibraryFetcher.this.verifyPendingFetches(libraryAppFetch.getPackageName());
                        }
                    }
                }
            }
        });
    }

    public /* synthetic */ List lambda$getThreadExecutorForAllAppsLoad$99$LibraryFetcher() throws Exception {
        return HorizonUtil.getApplications(this.mContext);
    }

    private ThreadExecutor.ConcurrentListenableScheduledFuture getThreadExecutorForAppLoad(final String str) {
        return ThreadExecutor.getInstance().execute(new Callable(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryFetcher$g4alZ6rMHcCdpfmewAidVdkOueA */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LibraryFetcher.this.lambda$getThreadExecutorForAppLoad$100$LibraryFetcher(this.f$1);
            }
        }, new ThreadExecutor.Listener<App>() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFetcher.AnonymousClass3 */

            @Override // com.oculus.vrshell.util.ThreadExecutor.Listener
            public void onFailure(Throwable th) {
                Log.e(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Exception while fetching app: %s", str), th);
                verifyPendingFetches();
            }

            public void onSuccess(App app) {
                Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Successfully fetched app: %s", str));
                if (LibraryFetcher.this.mViewModel != null) {
                    LibraryFetcher.this.mViewModel.setApp(app);
                }
                verifyPendingFetches();
            }

            private void verifyPendingFetches() {
                LibraryFetcher.this.verifyPendingFetches(str);
            }
        });
    }

    public /* synthetic */ App lambda$getThreadExecutorForAppLoad$100$LibraryFetcher(String str) throws Exception {
        return HorizonUtil.getApplication(this.mContext, str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInitialAppsLoadingState(boolean z) {
        AppViewModel appViewModel = this.mViewModel;
        if (appViewModel != null) {
            appViewModel.flagInitialAppsLoadingState(z);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInitialUnknownSourcesLoadingState(boolean z) {
        AppViewModel appViewModel = this.mViewModel;
        if (appViewModel != null) {
            appViewModel.flagInitialUnknownSourcesLoadingState(z);
        }
    }

    private boolean isUnknownSource(ApplicationInfo applicationInfo, HashSet<String> hashSet) {
        String installerPackageName = this.mContext.getPackageManager().getInstallerPackageName(applicationInfo.packageName);
        return applicationInfo != null && !hashSet.contains(applicationInfo.packageName) && (applicationInfo.flags & 1) == 0 && !(installerPackageName != null && installerPackageName.equals("com.oculus.ocms"));
    }

    private boolean isVrApp(ApplicationInfo applicationInfo) {
        if (applicationInfo == null) {
            return false;
        }
        if (hasLegacyVrAppMetadata(applicationInfo) || hasOpenXRAppCategories(applicationInfo.packageName)) {
            return true;
        }
        return false;
    }

    private boolean hasLegacyVrAppMetadata(ApplicationInfo applicationInfo) {
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return false;
        }
        String string = applicationInfo.metaData.getString(METADATA_KEY_VR_MODE);
        if (METADATA_VR_MODE_DUAL.equals(string) || METADATA_VR_MODE_VR_ONLY.equals(string)) {
            return true;
        }
        return false;
    }

    private boolean hasOpenXRAppCategories(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !this.mContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory(INTENT_CATEGORY_VR).setPackage(str), 0).isEmpty();
    }

    private long getApkSize(String str) {
        try {
            return new File(str).length();
        } catch (Exception unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to read file size of " + str);
            return 0;
        }
    }

    private long getLastModifiedDate(String str) {
        try {
            return new File(str).lastModified();
        } catch (Exception unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to read file last modification date of " + str);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean verifyPendingFetches(String str) {
        synchronized (this) {
            if (!this.mLibraryAppFetches.containsKey(str) || !this.mLibraryAppFetches.get(str).hasPendingFetch()) {
                this.mLibraryAppFetches.remove(str);
                return false;
            }
            this.mLibraryAppFetches.get(str).resetPendingFetches();
            Log.d(LibraryUtils.LIBRARY_SYNCING_TAG, String.format("Executing previously added pending fetch for app: %s", str));
            executeFetchHorizonApps(str, false);
            return true;
        }
    }
}
