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
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.library.model.InstallerCallback;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.modules.codegen.LibraryModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.panellib.Qpl;
import com.oculus.panellib.SystraceBlock;
import com.oculus.panellib.ThreadExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    private static final String TAG = "LibraryModuleImpl";
    private static LibraryModuleImpl mInstance;
    private static final ReentrantReadWriteLock mInstanceLock = new ReentrantReadWriteLock(true);
    private static List<LibraryModule.Entitlement> mLibrary = null;
    private static final List<String> mLibraryChangeEvents = new ArrayList();
    private static final ReentrantLock mLibraryChangeEventsLock = new ReentrantLock(true);
    private static final AtomicInteger mLibraryFetchesCount = new AtomicInteger();
    private static final ReentrantLock mLibraryLock = new ReentrantLock(true);
    private static Resolver<List<LibraryModule.Entitlement>> mLibraryResolver = null;
    private static OVRLibrary sOVRLibraryInstance;
    private final Context mContext;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isDisabledApp(ApplicationInfo applicationInfo, HashSet<String> hashSet) {
        return false;
    }

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
    public final void shutdownModule() {
        mInstanceLock.writeLock().lock();
        try {
            mInstance = null;
        } finally {
            mInstanceLock.writeLock().unlock();
        }
    }

    private static LibraryModule.AppImage convertImageToAppImage(Map<Image.ImageName, Image> map, Image.ImageName imageName) {
        Image image = map.get(imageName);
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
        LibraryModule.EntitlementImages entitlementImages = new LibraryModule.EntitlementImages();
        entitlementImages.SOURCE_MAIN = convertImageToAppImage(app.images, Image.ImageName.SOURCE_MAIN);
        entitlementImages.SOURCE_SQUARE = convertImageToAppImage(app.images, Image.ImageName.SOURCE_SQUARE);
        entitlementImages.SOURCE_TINY = convertImageToAppImage(app.images, Image.ImageName.SOURCE_TINY);
        entitlementImages.LANDSCAPE_SMALL = convertImageToAppImage(app.images, Image.ImageName.LANDSCAPE_SMALL);
        entitlementImages.HERO = convertImageToAppImage(app.images, Image.ImageName.HERO);
        entitlement.images = entitlementImages;
        ArrayList arrayList = new ArrayList();
        for (String str : app.latestPermissions) {
            arrayList.add(str);
        }
        entitlement.latestPermissions = arrayList;
        LibraryModule.EnvironmentSource environmentSource = new LibraryModule.EnvironmentSource();
        environmentSource.id = app.environmentSourceId;
        environmentSource.displayName = app.environmentSourceDisplayName;
        entitlement.environmentSource = environmentSource;
        return entitlement;
    }

    public static void fetchInitialLibraryAsync(final Context context) {
        final String str;
        int andIncrement = mLibraryFetchesCount.getAndIncrement();
        if (andIncrement >= 5) {
            str = null;
        } else if (andIncrement > 0) {
            str = String.format("fetch_library_%d", Integer.valueOf(andIncrement));
        } else {
            str = "fetch_library";
        }
        if (str != null) {
            Qpl.markerPointStart(Qpl.QPL_MARKER_INIT, str);
        }
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass1 */

            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                String str;
                App[] allApps = LibraryModuleImpl.getOVRLibraryInstance(context).getAllApps();
                if (allApps == null) {
                    Log.e(LibraryModuleImpl.TAG, "Received null apps from OVRLibrary");
                    if (LibraryModuleImpl.mLibraryResolver != null) {
                        LibraryModuleImpl.mLibraryResolver.reject(new Exception("Received null apps from OVRLibrary"));
                    }
                    return null;
                }
                try {
                    ArrayList arrayList = new ArrayList(allApps.length);
                    SystraceBlock systraceBlock = new SystraceBlock(LibraryModuleImpl.TAG, "convertAppToEntitlement");
                    try {
                        for (App app : allApps) {
                            arrayList.add(LibraryModuleImpl.convertAppToEntitlement(app));
                        }
                        systraceBlock.close();
                        LibraryModuleImpl.mLibraryLock.lock();
                        try {
                            if (LibraryModuleImpl.mLibraryResolver != null) {
                                LibraryModuleImpl.mLibraryResolver.resolve(arrayList);
                            } else {
                                List unused = LibraryModuleImpl.mLibrary = arrayList;
                            }
                            LibraryModuleImpl.mLibraryLock.unlock();
                            return null;
                        } catch (Throwable th) {
                            LibraryModuleImpl.mLibraryLock.unlock();
                            throw th;
                        }
                    } catch (Throwable unused2) {
                    }
                } finally {
                    str = str;
                    if (str != null) {
                        Qpl.markerPointEnd(Qpl.QPL_MARKER_INIT, str);
                    }
                }
                throw th;
            }
        }, "LibraryModuleImpl::fetchInitialLibraryAsync");
    }

    public static void registerLibraryChangeListener(Context context) {
        getOVRLibraryInstance(context).registerObserverForAppChangeUri(OCMSLibraryContract.uriForAllPackages(), new OVRLibrary.OnChangeListener() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass2 */

            @Override // com.oculus.libraryapi.OVRLibrary.OnChangeListener
            public final void onChange(String str) {
                LibraryModuleImpl.mInstanceLock.readLock().lock();
                try {
                    if (LibraryModuleImpl.mInstance != null) {
                        LibraryModuleImpl.mInstance.emitLibraryUpdate(str);
                    } else {
                        LibraryModuleImpl.mLibraryChangeEventsLock.lock();
                        try {
                            LibraryModuleImpl.mLibraryChangeEvents.add(str);
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
    @Override // com.oculus.modules.codegen.LibraryModule
    public final void getInitialLibraryImpl(Resolver<List<LibraryModule.Entitlement>> resolver) {
        SystraceBlock systraceBlock = new SystraceBlock(TAG, "getInitialLibraryImpl");
        try {
            mLibraryLock.lock();
            try {
                if (mLibrary != null) {
                    resolver.resolve(mLibrary);
                    mLibrary = null;
                } else {
                    mLibraryResolver = resolver;
                }
                mLibraryLock.unlock();
                mLibraryChangeEventsLock.lock();
                try {
                    for (String str : mLibraryChangeEvents) {
                        emitLibraryUpdate(str);
                    }
                    mLibraryChangeEvents.clear();
                    mLibraryChangeEventsLock.unlock();
                    systraceBlock.close();
                    return;
                } catch (Throwable th) {
                    mLibraryChangeEventsLock.unlock();
                    throw th;
                }
            } catch (Throwable th2) {
                mLibraryLock.unlock();
                throw th2;
            }
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void emitLibraryUpdate(String str) {
        LibraryModule.LibraryUpdate libraryUpdate = new LibraryModule.LibraryUpdate();
        libraryUpdate.packageName = str;
        emitOnLibraryUpdated(libraryUpdate);
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void fetchUnknownSourcesAsyncImpl(final boolean z, final boolean z2, final Resolver<List<LibraryModule.UnknownSource>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass3 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    PackageManager packageManager = LibraryModuleImpl.this.mContext.getPackageManager();
                    ArrayList arrayList = new ArrayList();
                    HashSet libraryPackageNames = LibraryModuleImpl.this.getLibraryPackageNames();
                    for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.oculus.vrshell.SHELL_MAIN"), 128)) {
                        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                        if (LibraryModuleImpl.this.isUnknownSource(serviceInfo.applicationInfo, libraryPackageNames, z2)) {
                            LibraryModule.UnknownSource unknownSource = new LibraryModule.UnknownSource();
                            unknownSource.packageName = serviceInfo.applicationInfo.packageName + "/" + serviceInfo.name;
                            unknownSource.applicationName = serviceInfo.name;
                            unknownSource.apkFullSizeBytes = (double) LibraryModuleImpl.this.getApkSize(serviceInfo.applicationInfo.sourceDir);
                            arrayList.add(unknownSource);
                        }
                    }
                    if (z) {
                        LibraryModuleImpl.this.fetchSideloadedApps(arrayList);
                    } else {
                        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(128)) {
                            if (LibraryModuleImpl.this.isUnknownSource(applicationInfo, libraryPackageNames, z2)) {
                                arrayList.add(LibraryModuleImpl.this.createUnknownSourcePackageForApp(packageManager, applicationInfo));
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

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void fetchDisabledAppsAsyncImpl(final Resolver<List<LibraryModule.UnknownSource>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass4 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    PackageManager packageManager = LibraryModuleImpl.this.mContext.getPackageManager();
                    ArrayList arrayList = new ArrayList();
                    HashSet libraryPackageNames = LibraryModuleImpl.this.getLibraryPackageNames();
                    for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(128)) {
                        if (LibraryModuleImpl.this.isDisabledApp(applicationInfo, libraryPackageNames)) {
                            arrayList.add(LibraryModuleImpl.this.createUnknownSourcePackageForApp(packageManager, applicationInfo));
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
    private LibraryModule.UnknownSource createUnknownSourcePackageForApp(PackageManager packageManager, ApplicationInfo applicationInfo) {
        LibraryModule.UnknownSource unknownSource = new LibraryModule.UnknownSource();
        unknownSource.packageName = applicationInfo.packageName;
        unknownSource.applicationName = packageManager.getApplicationLabel(applicationInfo).toString();
        unknownSource.apkFullSizeBytes = (double) getApkSize(applicationInfo.sourceDir);
        return unknownSource;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private HashSet<String> getLibraryPackageNames() {
        HashSet<String> hashSet = new HashSet<>();
        for (App app : getOVRLibraryInstance(this.mContext).getAllApps()) {
            hashSet.add(app.packageName);
        }
        return hashSet;
    }

    private boolean isLibraryInstalled(ApplicationInfo applicationInfo) {
        String installerPackageName = this.mContext.getPackageManager().getInstallerPackageName(applicationInfo.packageName);
        return installerPackageName != null && installerPackageName.equals("com.oculus.ocms");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isUnknownSource(ApplicationInfo applicationInfo, HashSet<String> hashSet, boolean z) {
        return isUnknownSourceOrDisabledApp(applicationInfo, hashSet);
    }

    private boolean isUnknownSourceOrDisabledApp(ApplicationInfo applicationInfo, HashSet<String> hashSet) {
        return applicationInfo != null && !hashSet.contains(applicationInfo.packageName) && (applicationInfo.flags & 1) == 0 && !isLibraryInstalled(applicationInfo);
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
        if (!this.mContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory(INTENT_CATEGORY_VR).setPackage(str), 0).isEmpty()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0071 A[Catch:{ Exception -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fetchSideloadedApps(java.util.List<com.oculus.modules.codegen.LibraryModule.UnknownSource> r13) {
        /*
        // Method dump skipped, instructions count: 140
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.fetchSideloadedApps(java.util.List):void");
    }

    private int getDeclaredIntField(Class cls, String str) throws NoSuchFieldException, IllegalAccessException {
        return ((Integer) cls.getDeclaredField(str).get(null)).intValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getApkSize(String str) {
        try {
            return new File(str).length();
        } catch (Exception unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to read file size of " + str);
            return 0;
        }
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void fetchLibraryAsyncImpl(final Resolver<List<LibraryModule.Entitlement>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass5 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                App[] allApps = LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).getAllApps();
                if (allApps == null) {
                    resolver.reject(new Exception("Received null apps from OVRLibrary"));
                    return null;
                }
                ArrayList arrayList = new ArrayList(allApps.length);
                SystraceBlock systraceBlock = new SystraceBlock(LibraryModuleImpl.TAG, "convertAppToEntitlement");
                try {
                    for (App app : allApps) {
                        arrayList.add(LibraryModuleImpl.convertAppToEntitlement(app));
                    }
                    systraceBlock.close();
                    resolver.resolve(arrayList);
                    return null;
                } catch (Throwable unused) {
                }
                throw th;
            }
        }, "LibraryModuleImpl::fetchLibraryAsyncImpl");
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void fetchLibraryItemAsyncImpl(final String str, final Resolver<LibraryModule.Entitlement> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass6 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                App app = LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).getApp(str);
                if (app == null) {
                    String format = String.format("Received null app from OVRLibrary for package name %s", str);
                    Log.e(LibraryModuleImpl.TAG, format);
                    resolver.reject(new Exception(format));
                    return null;
                }
                resolver.resolve(LibraryModuleImpl.convertAppToEntitlement(app));
                return null;
            }
        }, "LibraryModuleImpl::fetchLibraryItemAsyncImpl");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private RequestOrigin getRequestOriginFromString(String str) {
        try {
            return RequestOrigin.valueOf(str);
        } catch (IllegalArgumentException unused) {
            String str2 = TAG;
            Log.e(str2, "Illegal request origin " + str);
            return RequestOrigin.UNKNOWN;
        }
    }

    public final void downloadAndInstall(final String str, final RequestOrigin requestOrigin, final InstallerCallback installerCallback) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        String str2 = TAG;
        Log.i(str2, "requesting install via OVRLibrary for " + str);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass7 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                oVRLibraryInstance.downloadAndInstall(str, requestOrigin, installerCallback);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void downloadAndInstallAsyncImpl(final String str, final LibraryModule.LibraryRequestOrigin libraryRequestOrigin, final Resolver<Void> resolver) {
        String str2 = TAG;
        Log.i(str2, "requesting install via OVRLibrary for " + str);
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        final AnonymousClass8 r6 = new InstallerCallback() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass8 */

            @Override // com.oculus.library.model.InstallerCallback
            public final void onInstallerResult(InstallerResult installerResult) {
                if (installerResult.error == null) {
                    resolver.resolve(null);
                } else {
                    resolver.reject(new Exception(installerResult.error.toString()));
                }
            }
        };
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass9 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                oVRLibraryInstance.downloadAndInstall(str, LibraryModuleImpl.this.getRequestOriginFromString(libraryRequestOrigin.name()), r6);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void cancelDownloadImpl(final String str) {
        String str2 = TAG;
        Log.i(str2, "requesting cancel via OVRLibrary for " + str);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass10 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).cancelDownload(str, null);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void uninstallImpl(final String str, final LibraryModule.LibraryRequestOrigin libraryRequestOrigin, final Resolver<Void> resolver) {
        String str2 = TAG;
        Log.i(str2, "requesting uninstall via OVRLibrary for " + str);
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        final AnonymousClass11 r6 = new InstallerCallback() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass11 */

            @Override // com.oculus.library.model.InstallerCallback
            public final void onInstallerResult(InstallerResult installerResult) {
                if (installerResult.error == null) {
                    resolver.resolve(null);
                } else {
                    resolver.reject(new Exception(installerResult.error.toString()));
                }
            }
        };
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass12 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                oVRLibraryInstance.uninstall(str, LibraryModuleImpl.this.getRequestOriginFromString(libraryRequestOrigin.name()), r6);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void updateRecentActivityImpl(final String str) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass13 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                oVRLibraryInstance.save(new App.Editor(str).withRecentActivityMs(System.currentTimeMillis()));
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void toggleOffUnseenAsyncImpl(final String str, final Resolver<Void> resolver) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Boolean>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass14 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Boolean call() throws Exception {
                boolean z = true;
                if (oVRLibraryInstance.save(new App.Editor(str).withIsUnseen(false)) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }, new FutureCallback<Boolean>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass15 */

            @Override // com.google.common.util.concurrent.FutureCallback
            public final void onFailure(Throwable th) {
                Log.e(LibraryModuleImpl.TAG, "Exception while toggling off unseen on app", th);
                resolver.reject(th);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.google.common.util.concurrent.FutureCallback
            public final /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void networkRefreshAsyncImpl(final Resolver<Void> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass16 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ Void call() throws Exception {
                LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).refetch(new ResultReceiver(null) {
                    /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass16.AnonymousClass1 */

                    /* access modifiers changed from: protected */
                    public final void onReceiveResult(int i, Bundle bundle) {
                        if (!(i == 0)) {
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

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void getInstalledAppPermissionsImpl(final String str, final Resolver<List<JSONObject>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<ArrayList<JSONObject>>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass17 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public ArrayList<JSONObject> call() throws JSONException {
                try {
                    String[] strArr = LibraryModuleImpl.this.mContext.getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
                    ArrayList<JSONObject> arrayList = new ArrayList<>(strArr.length);
                    for (String str : strArr) {
                        try {
                            arrayList.add(LibraryModuleImpl.getPermissionInfoResponse(LibraryModuleImpl.this.mContext, str));
                        } catch (JSONException e) {
                            Log.e(LibraryModuleImpl.TAG, "Exception while parsing permissions");
                            throw e;
                        }
                    }
                    return arrayList;
                } catch (PackageManager.NameNotFoundException unused) {
                    return null;
                }
            }
        }, new FutureCallback<ArrayList<JSONObject>>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass18 */

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.google.common.util.concurrent.FutureCallback
            public final /* bridge */ /* synthetic */ void onSuccess(ArrayList<JSONObject> arrayList) {
                ArrayList<JSONObject> arrayList2 = arrayList;
                if (arrayList2 == null) {
                    resolver.reject(new Exception("Received null permission response"));
                } else {
                    resolver.resolve(arrayList2);
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public final void onFailure(Throwable th) {
                Log.e(LibraryModuleImpl.TAG, "Exception while fetching install app permissions", th);
                resolver.reject(th);
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void getPermissionInfoAsyncImpl(final String str, final Resolver<JSONObject> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<JSONObject>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass19 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final /* bridge */ /* synthetic */ JSONObject call() throws Exception {
                return LibraryModuleImpl.getPermissionInfoResponse(LibraryModuleImpl.this.mContext, str);
            }
        }, new FutureCallback<JSONObject>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass20 */

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.google.common.util.concurrent.FutureCallback
            public final /* bridge */ /* synthetic */ void onSuccess(JSONObject jSONObject) {
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 == null) {
                    resolver.reject(new Exception("Received null permission response"));
                } else {
                    resolver.resolve(jSONObject2);
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public final void onFailure(Throwable th) {
                Log.e(LibraryModuleImpl.TAG, "Exception while fetching permission info", th);
                resolver.reject(th);
            }
        });
    }

    public static JSONObject getPermissionInfoResponse(Context context, String str) throws JSONException {
        PackageManager packageManager = context.getPackageManager();
        com.oculus.os.pm.PackageManager.getInstance();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        try {
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(str, 0);
            String groupOfPermission = com.oculus.os.pm.PackageManager.getGroupOfPermission(permissionInfo);
            jSONObject.put("groupName", groupOfPermission);
            jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, permissionInfo.packageName);
            jSONObject.put("label", permissionInfo.loadLabel(packageManager));
            jSONObject.put("description", permissionInfo.loadDescription(packageManager));
            jSONObject.put("protectionLevel", permissionInfo.protectionLevel);
            PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(groupOfPermission, 0);
            jSONObject.put("groupPriority", permissionGroupInfo.priority);
            jSONObject.put("groupLabel", permissionGroupInfo.loadLabel(packageManager));
            jSONObject.put("groupDescription", permissionGroupInfo.loadDescription(packageManager));
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return jSONObject;
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void isInstalledAsyncImpl(final String str, final Resolver<Boolean> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass21 */

            /* access modifiers changed from: private */
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    LibraryModuleImpl.this.mContext.getPackageManager().getPackageInfo(str, 0);
                    resolver.resolve(Boolean.TRUE);
                    return null;
                } catch (PackageManager.NameNotFoundException unused) {
                    resolver.resolve(Boolean.FALSE);
                    return null;
                }
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public final void genControllerSupportForPackageImpl(String str, Resolver<List<LibraryModule.SupportedController>> resolver) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            HashSet hashSet = new HashSet();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 16384);
            hashSet.add(LibraryModule.SupportedController.TOUCH);
            if (!isPackageAPanelApp(str, packageManager)) {
                FeatureInfo[] featureInfoArr = packageInfo.reqFeatures;
                int length = featureInfoArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    FeatureInfo featureInfo = featureInfoArr[i];
                    if (REQUESTED_FEATURE_HAND_TRACKING.equals(featureInfo.name)) {
                        if ((featureInfo.flags & 1) != 0) {
                            hashSet.remove(LibraryModule.SupportedController.TOUCH);
                        }
                        hashSet.add(LibraryModule.SupportedController.HANDS);
                    } else {
                        i++;
                    }
                }
            } else {
                hashSet.add(LibraryModule.SupportedController.HANDS);
            }
            resolver.resolve(new ArrayList(hashSet));
        } catch (PackageManager.NameNotFoundException e) {
            resolver.reject(e);
        }
    }

    private boolean isPackageAPanelApp(String str, PackageManager packageManager) {
        Bundle bundle;
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.oculus.vrshell.SHELL_MAIN").setPackage(str), 128)) {
            if (!(resolveInfo.serviceInfo == null || (bundle = resolveInfo.serviceInfo.metaData) == null || !bundle.containsKey("com.oculus.vrshell.is_default_service"))) {
                return true;
            }
        }
        return false;
    }
}
