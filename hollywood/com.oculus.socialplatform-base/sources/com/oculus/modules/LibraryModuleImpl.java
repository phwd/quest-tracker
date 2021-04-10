package com.oculus.modules;

import X.AbstractC057411o;
import X.AnonymousClass006;
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
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.library.model.InstallerCallback;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.modules.codegen.LibraryModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.vrshell.util.PackageUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public final class LibraryModuleImpl extends LibraryModule {
    public static final String INTENT_CATEGORY_VR = "com.oculus.intent.category.VR";
    public static final int MAX_LIBRARY_FETCHES_TO_QPL_LOG = 5;
    public static final String METADATA_KEY_VR_MODE = "com.samsung.android.vr.application.mode";
    public static final String METADATA_VR_MODE_DUAL = "dual";
    public static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    public static final String REQUESTED_FEATURE_HAND_TRACKING = "oculus.software.handtracking";
    public static final String TAG = "LibraryModuleImpl";
    public static LibraryModuleImpl mInstance;
    public static final ReentrantReadWriteLock mInstanceLock = new ReentrantReadWriteLock(true);
    public static List<LibraryModule.Entitlement> mLibrary;
    public static final List<String> mLibraryChangeEvents = new ArrayList();
    public static final ReentrantLock mLibraryChangeEventsLock = new ReentrantLock(true);
    public static final AtomicInteger mLibraryFetchesCount = new AtomicInteger();
    public static final ReentrantLock mLibraryLock = new ReentrantLock(true);
    public static Resolver<List<LibraryModule.Entitlement>> mLibraryResolver;
    public static OVRLibrary sOVRLibraryInstance;
    public final Context mContext;

    private boolean hasLegacyVrAppMetadata(ApplicationInfo applicationInfo) {
        Bundle bundle;
        if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
            return false;
        }
        String string = bundle.getString("com.samsung.android.vr.application.mode");
        return "dual".equals(string) || "vr_only".equals(string);
    }

    private boolean isDisabledApp(ApplicationInfo applicationInfo, HashSet<String> hashSet) {
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isUnknownSourceOrDisabledApp(ApplicationInfo applicationInfo, HashSet<String> hashSet) {
        return applicationInfo != null && !hashSet.contains(applicationInfo.packageName) && (applicationInfo.flags & 1) == 0 && !isLibraryInstalled(applicationInfo);
    }

    private boolean isVrApp(ApplicationInfo applicationInfo) {
        if (applicationInfo != null) {
            return hasLegacyVrAppMetadata(applicationInfo) || hasOpenXRAppCategories(applicationInfo.packageName);
        }
        return false;
    }

    public void downloadAndInstall(final String str, final RequestOrigin requestOrigin, final InstallerCallback installerCallback) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass7 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                oVRLibraryInstance.downloadAndInstall(str, requestOrigin, installerCallback);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void downloadAndInstallAsyncImpl(final String str, final LibraryModule.LibraryRequestOrigin libraryRequestOrigin, final Resolver<Void> resolver) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        final AnonymousClass8 r6 = new InstallerCallback() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass8 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                if (installerResult.isSuccess()) {
                    resolver.resolve(null);
                } else {
                    resolver.reject(new Exception(installerResult.error.toString()));
                }
            }
        };
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass9 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                oVRLibraryInstance.downloadAndInstall(str, LibraryModuleImpl.this.getRequestOriginFromString(libraryRequestOrigin.name()), r6);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void uninstallImpl(final String str, final LibraryModule.LibraryRequestOrigin libraryRequestOrigin, final Resolver<Void> resolver) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        final AnonymousClass11 r6 = new InstallerCallback() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass11 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                InstallerResultError installerResultError = installerResult.error;
                if (installerResultError == null) {
                    resolver.resolve(null);
                } else {
                    resolver.reject(new Exception(installerResultError.toString()));
                }
            }
        };
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass12 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                oVRLibraryInstance.uninstall(str, LibraryModuleImpl.this.getRequestOriginFromString(libraryRequestOrigin.name()), r6);
                return null;
            }
        });
    }

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
    private void emitLibraryUpdate(String str) {
        LibraryModule.LibraryUpdate libraryUpdate = new LibraryModule.LibraryUpdate();
        libraryUpdate.packageName = str;
        emitOnLibraryUpdated(libraryUpdate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0019, code lost:
        if (r3 != null) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void fetchInitialLibraryAsync(final android.content.Context r4) {
        /*
            java.util.concurrent.atomic.AtomicInteger r0 = com.oculus.modules.LibraryModuleImpl.mLibraryFetchesCount
            int r1 = r0.getAndIncrement()
            r0 = 5
            if (r1 >= r0) goto L_0x0034
            if (r1 <= 0) goto L_0x0031
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r0}
            java.lang.String r0 = "fetch_library_%d"
            java.lang.String r3 = java.lang.String.format(r0, r1)
            if (r3 == 0) goto L_0x0022
        L_0x001b:
            r1 = 51707905(0x3150001, float:4.378717E-37)
            r0 = 0
            com.oculus.panellib.Qpl.markerPointStart(r1, r3, r0)
        L_0x0022:
            com.oculus.panellib.ThreadExecutor r2 = com.oculus.panellib.ThreadExecutor.getInstance()
            com.oculus.modules.LibraryModuleImpl$1 r1 = new com.oculus.modules.LibraryModuleImpl$1
            r1.<init>(r4, r3)
            java.lang.String r0 = "LibraryModuleImpl::fetchInitialLibraryAsync"
            r2.execute(r1, r0)
            return
        L_0x0031:
            java.lang.String r3 = "fetch_library"
            goto L_0x001b
        L_0x0034:
            r3 = 0
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.fetchInitialLibraryAsync(android.content.Context):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0081, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fetchSideloadedApps(java.util.List<com.oculus.modules.codegen.LibraryModule.UnknownSource> r13) {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.fetchSideloadedApps(java.util.List):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getApkSize(String str) {
        try {
            return new File(str).length();
        } catch (Exception unused) {
            Log.e(TAG, AnonymousClass006.A07("Unable to read file size of ", str));
            return 0;
        }
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

    public static OVRLibrary getOVRLibraryInstance(Context context) {
        OVRLibrary oVRLibrary = sOVRLibraryInstance;
        if (oVRLibrary != null) {
            return oVRLibrary;
        }
        OVRLibrary oVRLibrary2 = new OVRLibrary(context);
        sOVRLibraryInstance = oVRLibrary2;
        return oVRLibrary2;
    }

    private boolean isLibraryInstalled(ApplicationInfo applicationInfo) {
        String installerPackageName = this.mContext.getPackageManager().getInstallerPackageName(applicationInfo.packageName);
        if (installerPackageName == null || !installerPackageName.equals("com.oculus.ocms")) {
            return false;
        }
        return true;
    }

    private boolean isPackageAPanelApp(String str, PackageManager packageManager) {
        Bundle bundle;
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent(PackageUtil.INTENT_ACTION_PANEL).setPackage(str), 128)) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (!(serviceInfo == null || (bundle = serviceInfo.metaData) == null || !bundle.containsKey("com.oculus.vrshell.is_default_service"))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void genControllerSupportForPackageImpl(String str, Resolver<List<LibraryModule.SupportedController>> resolver) {
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

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        throw r0;
     */
    @Override // com.oculus.modules.codegen.LibraryModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getInitialLibraryImpl(com.oculus.modules.codegen.Resolver<java.util.List<com.oculus.modules.codegen.LibraryModule.Entitlement>> r4) {
        /*
            r3 = this;
            java.lang.String r1 = "LibraryModuleImpl"
            java.lang.String r0 = "getInitialLibraryImpl"
            com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock
            r2.<init>(r1, r0)
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.LibraryModuleImpl.mLibraryLock     // Catch:{ all -> 0x0057 }
            r0.lock()     // Catch:{ all -> 0x0057 }
            java.util.List<com.oculus.modules.codegen.LibraryModule$Entitlement> r0 = com.oculus.modules.LibraryModuleImpl.mLibrary     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0019
            r4.resolve(r0)     // Catch:{ all -> 0x0050 }
            r0 = 0
            com.oculus.modules.LibraryModuleImpl.mLibrary = r0     // Catch:{ all -> 0x0050 }
            goto L_0x001b
        L_0x0019:
            com.oculus.modules.LibraryModuleImpl.mLibraryResolver = r4     // Catch:{ all -> 0x0050 }
        L_0x001b:
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.LibraryModuleImpl.mLibraryLock
            r0.unlock()
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.LibraryModuleImpl.mLibraryChangeEventsLock
            r0.lock()
            java.util.List<java.lang.String> r0 = com.oculus.modules.LibraryModuleImpl.mLibraryChangeEvents     // Catch:{ all -> 0x0049 }
            java.util.Iterator r1 = r0.iterator()     // Catch:{ all -> 0x0049 }
        L_0x002b:
            boolean r0 = r1.hasNext()     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x003b
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0049 }
            r3.emitLibraryUpdate(r0)     // Catch:{ all -> 0x0049 }
            goto L_0x002b
        L_0x003b:
            java.util.List<java.lang.String> r0 = com.oculus.modules.LibraryModuleImpl.mLibraryChangeEvents     // Catch:{ all -> 0x0049 }
            r0.clear()     // Catch:{ all -> 0x0049 }
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.LibraryModuleImpl.mLibraryChangeEventsLock
            r0.unlock()
            r2.close()
            return
        L_0x0049:
            r1 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.LibraryModuleImpl.mLibraryChangeEventsLock
            r0.unlock()
            throw r1
        L_0x0050:
            r1 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.LibraryModuleImpl.mLibraryLock
            r0.unlock()
            throw r1
        L_0x0057:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x005d }
        L_0x005d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.getInitialLibraryImpl(com.oculus.modules.codegen.Resolver):void");
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

    @Override // com.oculus.modules.codegen.LibraryModule
    public void toggleOffUnseenAsyncImpl(final String str, final Resolver<Void> resolver) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Boolean>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass14 */

            @Override // java.util.concurrent.Callable
            public Boolean call() {
                OVRLibrary oVRLibrary = oVRLibraryInstance;
                App.Editor editor = new App.Editor(str);
                editor.withIsUnseen(false);
                boolean z = true;
                if (oVRLibrary.save(editor) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }, new AbstractC057411o<Boolean>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass15 */

            @Override // X.AbstractC057411o
            public void onFailure(@Nonnull Throwable th) {
                Log.e(LibraryModuleImpl.TAG, "Exception while toggling off unseen on app", th);
                resolver.reject(th);
            }

            public void onSuccess(@Nullable Boolean bool) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void updateRecentActivityImpl(final String str) {
        final OVRLibrary oVRLibraryInstance = getOVRLibraryInstance(this.mContext);
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass13 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                OVRLibrary oVRLibrary = oVRLibraryInstance;
                App.Editor editor = new App.Editor(str);
                editor.withRecentActivityMs(System.currentTimeMillis());
                oVRLibrary.save(editor);
                return null;
            }
        });
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

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public static LibraryModule.AppImage convertImageToAppImage(Map<Image.ImageName, Image> map, Image.ImageName imageName) {
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

    private int getDeclaredIntField(Class cls, String str) throws NoSuchFieldException, IllegalAccessException {
        return ((Number) cls.getDeclaredField(str).get(null)).intValue();
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
            jSONObject.put("packageName", permissionInfo.packageName);
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private RequestOrigin getRequestOriginFromString(String str) {
        try {
            return RequestOrigin.valueOf(str);
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, AnonymousClass006.A07("Illegal request origin ", str));
            return RequestOrigin.UNKNOWN;
        }
    }

    private boolean hasOpenXRAppCategories(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !this.mContext.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory(INTENT_CATEGORY_VR).setPackage(str), 0).isEmpty();
    }

    private boolean isUnknownSource(ApplicationInfo applicationInfo, HashSet<String> hashSet, boolean z) {
        return isUnknownSourceOrDisabledApp(applicationInfo, hashSet);
    }

    public static void registerLibraryChangeListener(Context context) {
        getOVRLibraryInstance(context).registerAppsChangeListener(new OVRLibrary.OnChangeListener() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass2 */

            @Override // com.oculus.libraryapi.OVRLibrary.OnChangeListener
            public void onChange(String str) {
                LibraryModuleImpl.mInstanceLock.readLock().lock();
                try {
                    LibraryModuleImpl libraryModuleImpl = LibraryModuleImpl.mInstance;
                    if (libraryModuleImpl != null) {
                        libraryModuleImpl.emitLibraryUpdate(str);
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

    @Override // com.oculus.modules.codegen.LibraryModule
    public void cancelDownloadImpl(final String str) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass10 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).cancelDownload(str, null);
                return null;
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchDisabledAppsAsyncImpl(final Resolver<List<LibraryModule.UnknownSource>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass4 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    PackageManager packageManager = LibraryModuleImpl.this.mContext.getPackageManager();
                    ArrayList arrayList = new ArrayList();
                    LibraryModuleImpl.this.getLibraryPackageNames();
                    Iterator<ApplicationInfo> it = packageManager.getInstalledApplications(128).iterator();
                    while (it.hasNext()) {
                        it.next();
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

    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchLibraryAsyncImpl(final Resolver<List<LibraryModule.Entitlement>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass5 */

            /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
                r2.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
                throw r0;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                    r7 = this;
                    com.oculus.modules.LibraryModuleImpl r0 = com.oculus.modules.LibraryModuleImpl.this
                    android.content.Context r0 = r0.mContext
                    com.oculus.libraryapi.OVRLibrary r0 = com.oculus.modules.LibraryModuleImpl.access$000(r0)
                    com.oculus.library.model.App[] r6 = r0.getAllApps()
                    r5 = 0
                    if (r6 != 0) goto L_0x001c
                    com.oculus.modules.codegen.Resolver r2 = r4
                    java.lang.String r1 = "Received null apps from OVRLibrary"
                    java.lang.Exception r0 = new java.lang.Exception
                    r0.<init>(r1)
                    r2.reject(r0)
                    return r5
                L_0x001c:
                    int r4 = r6.length
                    java.util.ArrayList r3 = new java.util.ArrayList
                    r3.<init>(r4)
                    java.lang.String r1 = "LibraryModuleImpl"
                    java.lang.String r0 = "convertAppToEntitlement"
                    com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock
                    r2.<init>(r1, r0)
                    r1 = 0
                L_0x002c:
                    if (r1 >= r4) goto L_0x0041
                    r0 = r6[r1]     // Catch:{ all -> 0x003a }
                    com.oculus.modules.codegen.LibraryModule$Entitlement r0 = com.oculus.modules.LibraryModuleImpl.access$300(r0)     // Catch:{ all -> 0x003a }
                    r3.add(r0)     // Catch:{ all -> 0x003a }
                    int r1 = r1 + 1
                    goto L_0x002c
                L_0x003a:
                    r0 = move-exception
                    throw r0     // Catch:{ all -> 0x003c }
                L_0x003c:
                    r0 = move-exception
                    r2.close()     // Catch:{ all -> 0x0040 }
                L_0x0040:
                    throw r0
                L_0x0041:
                    r2.close()
                    com.oculus.modules.codegen.Resolver r0 = r4
                    r0.resolve(r3)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.LibraryModuleImpl.AnonymousClass5.call():java.lang.Void");
            }
        }, "LibraryModuleImpl::fetchLibraryAsyncImpl");
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchLibraryItemAsyncImpl(final String str, final Resolver<LibraryModule.Entitlement> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass6 */

            @Override // java.util.concurrent.Callable
            public Void call() {
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

    @Override // com.oculus.modules.codegen.LibraryModule
    public void fetchUnknownSourcesAsyncImpl(final boolean z, final boolean z2, final Resolver<List<LibraryModule.UnknownSource>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    PackageManager packageManager = LibraryModuleImpl.this.mContext.getPackageManager();
                    ArrayList arrayList = new ArrayList();
                    HashSet libraryPackageNames = LibraryModuleImpl.this.getLibraryPackageNames();
                    for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent(PackageUtil.INTENT_ACTION_PANEL), 128)) {
                        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                        LibraryModuleImpl libraryModuleImpl = LibraryModuleImpl.this;
                        ApplicationInfo applicationInfo = serviceInfo.applicationInfo;
                        boolean z = z2;
                        if (libraryModuleImpl.isUnknownSourceOrDisabledApp(applicationInfo, libraryPackageNames)) {
                            LibraryModule.UnknownSource unknownSource = new LibraryModule.UnknownSource();
                            ApplicationInfo applicationInfo2 = serviceInfo.applicationInfo;
                            String str = applicationInfo2.packageName;
                            String str2 = serviceInfo.name;
                            unknownSource.packageName = AnonymousClass006.A09(str, "/", str2);
                            unknownSource.applicationName = str2;
                            unknownSource.apkFullSizeBytes = (double) LibraryModuleImpl.this.getApkSize(applicationInfo2.sourceDir);
                            arrayList.add(unknownSource);
                        }
                    }
                    if (z) {
                        LibraryModuleImpl.this.fetchSideloadedApps(arrayList);
                    } else {
                        for (ApplicationInfo applicationInfo3 : packageManager.getInstalledApplications(128)) {
                            LibraryModuleImpl libraryModuleImpl2 = LibraryModuleImpl.this;
                            boolean z2 = z2;
                            if (libraryModuleImpl2.isUnknownSourceOrDisabledApp(applicationInfo3, libraryPackageNames)) {
                                arrayList.add(LibraryModuleImpl.this.createUnknownSourcePackageForApp(packageManager, applicationInfo3));
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
    public void getInstalledAppPermissionsImpl(final String str, final Resolver<List<JSONObject>> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<ArrayList<JSONObject>>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass17 */

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
        }, new AbstractC057411o<ArrayList<JSONObject>>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass18 */

            @Override // X.AbstractC057411o
            public void onFailure(@Nonnull Throwable th) {
                Log.e(LibraryModuleImpl.TAG, "Exception while fetching install app permissions", th);
                resolver.reject(th);
            }

            public void onSuccess(@Nullable ArrayList<JSONObject> arrayList) {
                if (arrayList == null) {
                    resolver.reject(new Exception("Received null permission response"));
                } else {
                    resolver.resolve(arrayList);
                }
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void getPermissionInfoAsyncImpl(final String str, final Resolver<JSONObject> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<JSONObject>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass19 */

            @Override // java.util.concurrent.Callable
            public JSONObject call() throws JSONException {
                return LibraryModuleImpl.getPermissionInfoResponse(LibraryModuleImpl.this.mContext, str);
            }
        }, new AbstractC057411o<JSONObject>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass20 */

            @Override // X.AbstractC057411o
            public void onFailure(@Nonnull Throwable th) {
                Log.e(LibraryModuleImpl.TAG, "Exception while fetching permission info", th);
                resolver.reject(th);
            }

            public void onSuccess(@Nullable JSONObject jSONObject) {
                if (jSONObject == null) {
                    resolver.reject(new Exception("Received null permission response"));
                } else {
                    resolver.resolve(jSONObject);
                }
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void isInstalledAsyncImpl(final String str, final Resolver<Boolean> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass21 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    LibraryModuleImpl.this.mContext.getPackageManager().getPackageInfo(str, 0);
                    resolver.resolve(true);
                    return null;
                } catch (PackageManager.NameNotFoundException unused) {
                    resolver.resolve(false);
                    return null;
                }
            }
        });
    }

    @Override // com.oculus.modules.codegen.LibraryModule
    public void networkRefreshAsyncImpl(final Resolver<Void> resolver) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass16 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                LibraryModuleImpl.getOVRLibraryInstance(LibraryModuleImpl.this.mContext).refetch(new ResultReceiver(null) {
                    /* class com.oculus.modules.LibraryModuleImpl.AnonymousClass16.AnonymousClass1 */

                    public void onReceiveResult(int i, Bundle bundle) {
                        if (i == 0) {
                            resolver.resolve(null);
                            return;
                        }
                        Log.e(LibraryModuleImpl.TAG, "Unable to refresh OVRLibrary");
                        resolver.reject(new Exception("Unable to refresh OVRLibrary"));
                    }
                });
                return null;
            }
        });
    }

    public static /* synthetic */ boolean access$1700(LibraryModuleImpl libraryModuleImpl, ApplicationInfo applicationInfo, HashSet hashSet) {
        return false;
    }
}
