package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import com.google.common.util.concurrent.FutureCallback;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.library.model.InstallerCallback;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.modules.codegen.LibraryModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.Qpl;
import com.oculus.panellib.ThreadExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;

public final class LibraryModuleImpl extends LibraryModule {
    private static final String INTENT_CATEGORY_VR = "com.oculus.intent.category.VR";
    private static final int MAX_LIBRARY_FETCHES_TO_QPL_LOG = 5;
    private static final String METADATA_KEY_VR_MODE = "com.samsung.android.vr.application.mode";
    private static final String METADATA_VR_MODE_DUAL = "dual";
    private static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    private static final String REQUESTED_FEATURE_HAND_TRACKING = "oculus.software.handtracking";
    private static final String TAG = LibraryModuleImpl.class.getSimpleName();
    private static LibraryModuleImpl mInstance = null;
    private static final ReentrantReadWriteLock mInstanceLock = new ReentrantReadWriteLock(true);
    private static List<LibraryModule.Entitlement> mLibrary = null;
    private static final List<String> mLibraryChangeEvents = new ArrayList();
    private static final ReentrantLock mLibraryChangeEventsLock = new ReentrantLock(true);
    private static final AtomicInteger mLibraryFetchesCount = new AtomicInteger();
    private static final ReentrantLock mLibraryLock = new ReentrantLock(true);
    private static Resolver<List<LibraryModule.Entitlement>> mLibraryResolver = null;
    private static OVRLibrary sOVRLibraryInstance;
    private final Context mContext;

    public LibraryModuleImpl(Context context) {
        this.mContext = context;
        mInstanceLock.writeLock().lock();
        try {
            mInstance = this;
        } finally {
            mInstanceLock.writeLock().unlock();
        }
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void shutdownModule() {
        mInstanceLock.writeLock().lock();
        try {
            mInstance = null;
        } finally {
            mInstanceLock.writeLock().unlock();
        }
    }

    private static LibraryModule.AppImage convertImageToAppImage(Map<Image.ImageName, Image> images, Image.ImageName name) {
        Image image = images.get(name);
        if (image == null) {
            return null;
        }
        LibraryModule.AppImage appImage = new LibraryModule.AppImage();
        appImage.uri = image.uri;
        appImage.width = (double) image.width;
        appImage.height = (double) image.height;
        return appImage;
    }

    /* access modifiers changed from: private */
    public static LibraryModule.Entitlement convertAppToEntitlement(App app) {
        LibraryModule.Entitlement entitlement = new LibraryModule.Entitlement();
        entitlement.id = app.id;
        entitlement.packageName = app.packageName;
        entitlement.status = LibraryModule.Status.valueOf(app.status.name());
        entitlement.displayName = app.displayName;
        entitlement.category = LibraryModule.Category.valueOf(app.category.name());
        entitlement.downloadedSizeBytes = (double) app.downloadedSizeBytes;
        entitlement.downloadSizeBytes = (double) app.downloadSizeBytes;
        entitlement.comfortRating = LibraryModule.ComfortRating.valueOf(app.comfortRating.name());
        entitlement.versionCode = (double) app.versionCode;
        entitlement.latestVersionCode = (double) app.latestVersionCode;
        entitlement.latestTargetSdkVersion = (double) app.latestTargetSdkVersion;
        entitlement.microphoneUsage = LibraryModule.MicrophoneUsage.valueOf(app.microphoneUsage.name());
        entitlement.minRecommendedVersionCode = (double) app.minRecommendedVersionCode;
        entitlement.minRequiredVersionCode = (double) app.minRequiredVersionCode;
        entitlement.isDemoOf = app.isDemoOf;
        entitlement.platform = LibraryModule.Platform.valueOf(app.platform.name());
        entitlement.headTracking = LibraryModule.HeadTrackingMode.valueOf(app.headTracking.name());
        entitlement.grantTimeMs = (double) app.grantTimeMs;
        entitlement.grantReason = LibraryModule.EntitlementGrantReason.valueOf(app.grantReason.name());
        entitlement.trustedBinaryStatus = app.trustedBinaryStatus;
        entitlement.cloudStorageStatus = LibraryModule.CloudStorageStatus.valueOf(app.cloudStorageStatus.name());
        entitlement.isUnseen = app.isUnseen;
        entitlement.isTest = app.isTest;
        entitlement.recentActivityMs = (double) app.recentActivityMs;
        entitlement.apkFullSizeBytes = (double) app.apkFullSizeBytes;
        entitlement.isConcept = app.isConcept;
        entitlement.applicationGroupingId = app.applicationGroupingId;
        LibraryModule.EntitlementImages images = new LibraryModule.EntitlementImages();
        images.SOURCE_MAIN = convertImageToAppImage(app.images, Image.ImageName.SOURCE_MAIN);
        images.SOURCE_SQUARE = convertImageToAppImage(app.images, Image.ImageName.SOURCE_SQUARE);
        images.SOURCE_TINY = convertImageToAppImage(app.images, Image.ImageName.SOURCE_TINY);
        images.LANDSCAPE_SMALL = convertImageToAppImage(app.images, Image.ImageName.LANDSCAPE_SMALL);
        images.HERO = convertImageToAppImage(app.images, Image.ImageName.HERO);
        entitlement.images = images;
        List<String> latestPermissions = new ArrayList<>();
        for (String permission : app.latestPermissions) {
            latestPermissions.add(permission);
        }
        entitlement.latestPermissions = latestPermissions;
        LibraryModule.EnvironmentSource environmentSource = new LibraryModule.EnvironmentSource();
        environmentSource.id = app.environmentSourceId;
        environmentSource.displayName = app.environmentSourceDisplayName;
        entitlement.environmentSource = environmentSource;
        return entitlement;
    }

    public static void fetchInitialLibraryAsync(final Context context) {
        final String qplFetchPointName;
        int fetchId = mLibraryFetchesCount.getAndIncrement();
        if (fetchId >= 5) {
            qplFetchPointName = null;
        } else if (fetchId > 0) {
            qplFetchPointName = String.format("fetch_library_%d", Integer.valueOf(fetchId));
        } else {
            qplFetchPointName = "fetch_library";
        }
        if (qplFetchPointName != null) {
            Qpl.markerPointStart(Qpl.QPL_MARKER_INIT, qplFetchPointName);
        }
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass1 */

            /* JADX INFO: finally extract failed */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x0094, code lost:
                r6 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
                r6 = r5;
                r5 = r6;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b6, code lost:
                r5 = th;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                // Method dump skipped, instructions count: 184
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.AnonymousClass1.call():java.lang.Void");
            }
        }, "LibraryModuleImpl::fetchInitialLibraryAsync");
    }

    public static void registerLibraryChangeListener(Context context) {
        getOVRLibraryInstance(context).registerAppsChangeListener(new OVRLibrary.OnChangeListener() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass2 */

            @Override // com.oculus.libraryapi.OVRLibrary.OnChangeListener
            public void onChange(String packageName) {
                LibraryModuleImpl.mInstanceLock.readLock().lock();
                try {
                    if (LibraryModuleImpl.mInstance != null) {
                        LibraryModuleImpl.mInstance.emitLibraryUpdate(packageName);
                    } else {
                        LibraryModuleImpl.mLibraryChangeEventsLock.lock();
                        try {
                            LibraryModuleImpl.mLibraryChangeEvents.add(packageName);
                        } finally {
                            LibraryModuleImpl.mLibraryChangeEventsLock.unlock();
                        }
                    }
                } finally {
                    LibraryModuleImpl.mInstanceLock.readLock().unlock();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static OVRLibrary getOVRLibraryInstance(Context context) {
        if (sOVRLibraryInstance == null) {
            sOVRLibraryInstance = new OVRLibrary(context);
        }
        return sOVRLibraryInstance;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005a, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.LibraryModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getInitialLibraryImpl(com.oculus.modules.codegen.Resolver<java.util.List<com.oculus.modules.codegen.LibraryModule.Entitlement>> r7) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.getInitialLibraryImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void emitLibraryUpdate(String packageName) {
        LibraryModule.LibraryUpdate update = new LibraryModule.LibraryUpdate();
        update.packageName = packageName;
        emitOnLibraryUpdated(update);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchUnknownSourcesAsyncImpl(final boolean displaySideloadedAppsEnabled, final boolean includeDisabledApps, final Resolver<List<LibraryModule.UnknownSource>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    PackageManager pm = LibraryModuleImpl.this.mContext.getPackageManager();
                    ArrayList arrayList = new ArrayList();
                    HashSet<String> libraryPackageNames = LibraryModuleImpl.this.getLibraryPackageNames();
                    for (ResolveInfo info : pm.queryIntentServices(new Intent("com.oculus.vrshell.SHELL_MAIN"), 128)) {
                        ServiceInfo service = info.serviceInfo;
                        if (LibraryModuleImpl.this.isUnknownSource(service.applicationInfo, libraryPackageNames, includeDisabledApps)) {
                            LibraryModule.UnknownSource app = new LibraryModule.UnknownSource();
                            app.packageName = service.applicationInfo.packageName + "/" + service.name;
                            app.applicationName = service.name;
                            app.apkFullSizeBytes = (double) LibraryModuleImpl.this.getApkSize(service.applicationInfo.sourceDir);
                            arrayList.add(app);
                        }
                    }
                    if (displaySideloadedAppsEnabled) {
                        LibraryModuleImpl.this.fetchSideloadedApps(arrayList);
                    } else {
                        for (ApplicationInfo application : pm.getInstalledApplications(128)) {
                            if (LibraryModuleImpl.this.isUnknownSource(application, libraryPackageNames, includeDisabledApps)) {
                                arrayList.add(LibraryModuleImpl.this.createUnknownSourcePackageForApp(pm, application));
                            }
                        }
                    }
                    resolver.resolve(arrayList);
                    return null;
                } catch (Exception e) {
                    Log.e(LibraryModuleImpl.TAG, "Exception while getting unknown sources", e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "LibraryModuleImpl::fetchUnknownSourcesAsyncImpl");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchDisabledAppsAsyncImpl(final Resolver<List<LibraryModule.UnknownSource>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass4 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    PackageManager pm = LibraryModuleImpl.this.mContext.getPackageManager();
                    ArrayList arrayList = new ArrayList();
                    HashSet<String> libraryPackageNames = LibraryModuleImpl.this.getLibraryPackageNames();
                    for (ApplicationInfo application : pm.getInstalledApplications(128)) {
                        if (LibraryModuleImpl.this.isDisabledApp(application, libraryPackageNames)) {
                            arrayList.add(LibraryModuleImpl.this.createUnknownSourcePackageForApp(pm, application));
                        }
                    }
                    resolver.resolve(arrayList);
                    return null;
                } catch (Exception e) {
                    Log.e(LibraryModuleImpl.TAG, "Exception while getting unknown sources", e);
                    resolver.reject(e);
                    return null;
                }
            }
        }, "LibraryModuleImpl::fetchDisabledAppsAsyncImpl");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private LibraryModule.UnknownSource createUnknownSourcePackageForApp(PackageManager pm, ApplicationInfo application) {
        LibraryModule.UnknownSource app = new LibraryModule.UnknownSource();
        app.packageName = application.packageName;
        app.applicationName = pm.getApplicationLabel(application).toString();
        app.apkFullSizeBytes = (double) getApkSize(application.sourceDir);
        return app;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private HashSet<String> getLibraryPackageNames() {
        HashSet<String> libraryPackageNames = new HashSet<>();
        for (App app : getOVRLibraryInstance(this.mContext).getAllApps()) {
            libraryPackageNames.add(app.packageName);
        }
        return libraryPackageNames;
    }

    private boolean isLibraryInstalled(ApplicationInfo application) {
        String applicationInstaller = this.mContext.getPackageManager().getInstallerPackageName(application.packageName);
        return applicationInstaller != null && applicationInstaller.equals("com.oculus.ocms");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isDisabledApp(ApplicationInfo application, HashSet<String> hashSet) {
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isUnknownSource(ApplicationInfo application, HashSet<String> libraryPackageNames, boolean includeDisabledApps) {
        return isUnknownSourceOrDisabledApp(application, libraryPackageNames);
    }

    private boolean isUnknownSourceOrDisabledApp(ApplicationInfo applicationInfo, HashSet<String> libraryPackageNames) {
        if (applicationInfo != null && !libraryPackageNames.contains(applicationInfo.packageName) && (applicationInfo.flags & 1) == 0 && !isLibraryInstalled(applicationInfo)) {
            return true;
        }
        return false;
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
        String vrMode = applicationInfo.metaData.getString(METADATA_KEY_VR_MODE);
        if (METADATA_VR_MODE_DUAL.equals(vrMode) || METADATA_VR_MODE_VR_ONLY.equals(vrMode)) {
            return true;
        }
        return false;
    }

    private boolean hasOpenXRAppCategories(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        if (!this.mContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory(INTENT_CATEGORY_VR).setPackage(packageName), 0).isEmpty()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cf, code lost:
        r17 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d0, code lost:
        r17 = r16;
        r16 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e7, code lost:
        r16 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fetchSideloadedApps(java.util.List<com.oculus.modules.codegen.LibraryModule.UnknownSource> r22) {
        /*
        // Method dump skipped, instructions count: 233
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.fetchSideloadedApps(java.util.List):void");
    }

    private int getDeclaredIntField(Class c, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return ((Integer) c.getDeclaredField(fieldName).get(null)).intValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getApkSize(String applicationDirectory) {
        try {
            return new File(applicationDirectory).length();
        } catch (Exception e) {
            Log.e(TAG, "Unable to read file size of " + applicationDirectory);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchLibraryAsyncImpl(final Resolver<List<LibraryModule.Entitlement>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass5 */

            /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
                r5 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0058, code lost:
                r5 = r4;
                r4 = r5;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x006c, code lost:
                r4 = th;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                // Method dump skipped, instructions count: 110
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.AnonymousClass5.call():java.lang.Void");
            }
        }, "LibraryModuleImpl::fetchLibraryAsyncImpl");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchLibraryItemAsyncImpl(final String packageName, final Resolver<LibraryModule.Entitlement> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass6 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                App app = LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).getApp(packageName);
                if (app == null) {
                    String error = String.format("Received null app from OVRLibrary for package name %s", packageName);
                    Log.e(LibraryModuleImpl.TAG, error);
                    resolver.reject(new Exception(error));
                } else {
                    resolver.resolve(LibraryModuleImpl.convertAppToEntitlement(app));
                }
                return null;
            }
        }, "LibraryModuleImpl::fetchLibraryItemAsyncImpl");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private RequestOrigin getRequestOriginFromString(String requestOrigin) {
        try {
            return RequestOrigin.valueOf(requestOrigin);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Illegal request origin " + requestOrigin);
            return RequestOrigin.UNKNOWN;
        }
    }

    public void downloadAndInstall(final String packageName, final RequestOrigin requestOrigin, final InstallerCallback callback) {
        final OVRLibrary ovrLibrary = getOVRLibraryInstance(this.mContext);
        Log.i(TAG, "requesting install via OVRLibrary for " + packageName);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass7 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                ovrLibrary.downloadAndInstall(packageName, requestOrigin, callback);
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void downloadAndInstallAsyncImpl(final String packageName, final LibraryModule.LibraryRequestOrigin requestOrigin, final Resolver<Void> resolver) {
        Log.i(TAG, "requesting install via OVRLibrary for " + packageName);
        final OVRLibrary ovrLibrary = getOVRLibraryInstance(this.mContext);
        final InstallerCallback callback = new InstallerCallback() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass8 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult result) {
                if (result.isSuccess()) {
                    resolver.resolve(null);
                } else {
                    resolver.reject(new Exception(result.error.toString()));
                }
            }
        };
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass9 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                ovrLibrary.downloadAndInstall(packageName, LibraryModuleImpl.this.getRequestOriginFromString(requestOrigin.name()), callback);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void cancelDownloadImpl(final String packageName) {
        Log.i(TAG, "requesting cancel via OVRLibrary for " + packageName);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass10 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).cancelDownload(packageName, null);
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void uninstallImpl(final String packageName, final LibraryModule.LibraryRequestOrigin requestOrigin, final Resolver<Void> resolver) {
        Log.i(TAG, "requesting uninstall via OVRLibrary for " + packageName);
        final OVRLibrary ovrLibrary = getOVRLibraryInstance(this.mContext);
        final InstallerCallback callback = new InstallerCallback() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass11 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult result) {
                if (result.error == null) {
                    resolver.resolve(null);
                } else {
                    resolver.reject(new Exception(result.error.toString()));
                }
            }
        };
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass12 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                ovrLibrary.uninstall(packageName, LibraryModuleImpl.this.getRequestOriginFromString(requestOrigin.name()), callback);
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void updateRecentActivityImpl(final String packageName) {
        final OVRLibrary ovrLibrary = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass13 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                ovrLibrary.save(new App.Editor(packageName).withRecentActivityMs(System.currentTimeMillis()));
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void toggleOffUnseenAsyncImpl(final String packageName, final Resolver<Void> resolver) {
        final OVRLibrary ovrLibrary = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Boolean>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass14 */

            @Override // java.util.concurrent.Callable
            public Boolean call() {
                boolean z = true;
                if (ovrLibrary.save(new App.Editor(packageName).withIsUnseen(false)) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }, new FutureCallback<Boolean>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass15 */

            public void onSuccess(Boolean updated) {
                resolver.resolve(null);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable throwable) {
                Log.e(LibraryModuleImpl.TAG, "Exception while toggling off unseen on app", throwable);
                resolver.reject(throwable);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void networkRefreshAsyncImpl(final Resolver<Void> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass16 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).refetch(new ResultReceiver(null) {
                    /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass16.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    public void onReceiveResult(int resultCode, Bundle resultData) {
                        if (!(resultCode == 0)) {
                            Log.e(LibraryModuleImpl.TAG, "Unable to refresh OVRLibrary");
                            resolver.reject(new Exception("Unable to refresh OVRLibrary"));
                            return;
                        }
                        resolver.resolve(null);
                    }
                });
                return null;
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void getInstalledAppPermissionsImpl(final String packageName, final Resolver<List<JSONObject>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<ArrayList<JSONObject>>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass17 */

            @Override // java.util.concurrent.Callable
            public ArrayList<JSONObject> call() throws JSONException {
                try {
                    String[] permissionInfos = LibraryModuleImpl.this.mContext.getPackageManager().getPackageInfo(packageName, 4096).requestedPermissions;
                    ArrayList<JSONObject> permissionInfoResponses = new ArrayList<>(permissionInfos.length);
                    for (String str : permissionInfos) {
                        try {
                            permissionInfoResponses.add(LibraryModuleImpl.getPermissionInfoResponse(LibraryModuleImpl.this.mContext, str));
                        } catch (JSONException e) {
                            Log.e(LibraryModuleImpl.TAG, "Exception while parsing permissions");
                            throw e;
                        }
                    }
                    return permissionInfoResponses;
                } catch (PackageManager.NameNotFoundException e2) {
                    return null;
                }
            }
        }, new FutureCallback<ArrayList<JSONObject>>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass18 */

            public void onSuccess(ArrayList<JSONObject> jsonResponses) {
                if (jsonResponses == null) {
                    resolver.reject(new Exception("Received null permission response"));
                } else {
                    resolver.resolve(jsonResponses);
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable throwable) {
                Log.e(LibraryModuleImpl.TAG, "Exception while fetching install app permissions", throwable);
                resolver.reject(throwable);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void getPermissionInfoAsyncImpl(final String permissionName, final Resolver<JSONObject> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<JSONObject>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass19 */

            @Override // java.util.concurrent.Callable
            public JSONObject call() throws JSONException {
                return LibraryModuleImpl.getPermissionInfoResponse(LibraryModuleImpl.this.mContext, permissionName);
            }
        }, new FutureCallback<JSONObject>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass20 */

            public void onSuccess(JSONObject permissionInfoJson) {
                if (permissionInfoJson == null) {
                    resolver.reject(new Exception("Received null permission response"));
                } else {
                    resolver.resolve(permissionInfoJson);
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable throwable) {
                Log.e(LibraryModuleImpl.TAG, "Exception while fetching permission info", throwable);
                resolver.reject(throwable);
            }
        });
    }

    public static JSONObject getPermissionInfoResponse(Context context, String permissionName) throws JSONException {
        PackageManager pm = context.getPackageManager();
        com.oculus.os.pm.PackageManager.getInstance();
        JSONObject response = new JSONObject();
        response.put("name", permissionName);
        try {
            PermissionInfo permissionInfo = pm.getPermissionInfo(permissionName, 0);
            String permissionGroup = com.oculus.os.pm.PackageManager.getGroupOfPermission(permissionInfo);
            response.put("groupName", permissionGroup);
            response.put("packageName", permissionInfo.packageName);
            response.put("label", permissionInfo.loadLabel(pm));
            response.put("description", permissionInfo.loadDescription(pm));
            response.put("protectionLevel", permissionInfo.protectionLevel);
            try {
                PermissionGroupInfo groupInfo = pm.getPermissionGroupInfo(permissionGroup, 0);
                response.put("groupPriority", groupInfo.priority);
                response.put("groupLabel", groupInfo.loadLabel(pm));
                response.put("groupDescription", groupInfo.loadDescription(pm));
            } catch (PackageManager.NameNotFoundException e) {
            }
        } catch (PackageManager.NameNotFoundException e2) {
        }
        return response;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void isInstalledAsyncImpl(final String packageName, final Resolver<Boolean> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass21 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    LibraryModuleImpl.this.mContext.getPackageManager().getPackageInfo(packageName, 0);
                    resolver.resolve(true);
                    return null;
                } catch (PackageManager.NameNotFoundException e) {
                    resolver.resolve(false);
                    return null;
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.LibraryModule
    public void genControllerSupportForPackageImpl(String packageName, Resolver<List<LibraryModule.SupportedController>> resolver) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            Set<LibraryModule.SupportedController> supportedControllers = new HashSet<>();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 16384);
            supportedControllers.add(LibraryModule.SupportedController.TOUCH);
            if (!isPackageAPanelApp(packageName, packageManager)) {
                FeatureInfo[] featureInfoArr = packageInfo.reqFeatures;
                int length = featureInfoArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    FeatureInfo reqFeature = featureInfoArr[i];
                    if (REQUESTED_FEATURE_HAND_TRACKING.equals(reqFeature.name)) {
                        if ((reqFeature.flags & 1) != 0) {
                            supportedControllers.remove(LibraryModule.SupportedController.TOUCH);
                        }
                        supportedControllers.add(LibraryModule.SupportedController.HANDS);
                    } else {
                        i++;
                    }
                }
            } else {
                supportedControllers.add(LibraryModule.SupportedController.HANDS);
            }
            resolver.resolve(new ArrayList(supportedControllers));
        } catch (PackageManager.NameNotFoundException e) {
            resolver.reject(e);
        }
    }

    private boolean isPackageAPanelApp(String packageName, PackageManager packageManager) {
        Bundle metaData;
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.oculus.vrshell.SHELL_MAIN").setPackage(packageName), 128)) {
            if (!(resolveInfo.serviceInfo == null || (metaData = resolveInfo.serviceInfo.metaData) == null || !metaData.containsKey("com.oculus.vrshell.is_default_service"))) {
                return true;
            }
        }
        return false;
    }
}
