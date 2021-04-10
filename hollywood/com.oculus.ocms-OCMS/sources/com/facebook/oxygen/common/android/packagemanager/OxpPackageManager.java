package com.facebook.oxygen.common.android.packagemanager;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressLint({"NewApi"})
public class OxpPackageManager extends PackageManager {
    private static final ThreadLocal<Integer> SUPPRESSED = new ThreadLocal<Integer>() {
        /* class com.facebook.oxygen.common.android.packagemanager.OxpPackageManager.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Integer initialValue() {
            return 0;
        }
    };
    @Nonnull
    private final PackageManager mAndroidPackageManager;
    @Nonnull
    private final Counters mCounters;

    public interface Counters {
        void bumpPackageQueried(String str);
    }

    public static final class ScopeGuard implements AutoCloseable {
        public ScopeGuard() {
            OxpPackageManager.SUPPRESSED.set(Integer.valueOf(((Integer) OxpPackageManager.SUPPRESSED.get()).intValue() + 1));
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            Integer num = (Integer) OxpPackageManager.SUPPRESSED.get();
            if (num != null) {
                OxpPackageManager.SUPPRESSED.set(Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    OxpPackageManager(@Nonnull PackageManager packageManager, @Nonnull Counters counters) {
        this.mAndroidPackageManager = packageManager;
        this.mCounters = counters;
    }

    public static ScopeGuard suppress() {
        return new ScopeGuard();
    }

    private static boolean isSuppressed() {
        return SUPPRESSED.get().intValue() != 0;
    }

    private void bumpPackageQueried(String str) {
        if (!isSuppressed()) {
            this.mCounters.bumpPackageQueried(str);
        }
    }

    @Override // android.content.pm.PackageManager
    public PackageInfo getPackageInfo(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getPackageInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    public PackageInfo getPackageInfo(@Nonnull VersionedPackage versionedPackage, int i) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(versionedPackage.getPackageName());
        return this.mAndroidPackageManager.getPackageInfo(versionedPackage, i);
    }

    public String[] currentToCanonicalPackageNames(@Nonnull String[] strArr) {
        for (String str : strArr) {
            bumpPackageQueried(str);
        }
        return this.mAndroidPackageManager.currentToCanonicalPackageNames(strArr);
    }

    public String[] canonicalToCurrentPackageNames(@Nonnull String[] strArr) {
        for (String str : strArr) {
            bumpPackageQueried(str);
        }
        return this.mAndroidPackageManager.canonicalToCurrentPackageNames(strArr);
    }

    @Nullable
    public Intent getLaunchIntentForPackage(@Nonnull String str) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getLaunchIntentForPackage(str);
    }

    @Nullable
    public Intent getLeanbackLaunchIntentForPackage(@Nonnull String str) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getLeanbackLaunchIntentForPackage(str);
    }

    @Override // android.content.pm.PackageManager
    public int[] getPackageGids(@Nonnull String str) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getPackageGids(str);
    }

    @Override // android.content.pm.PackageManager
    public int[] getPackageGids(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getPackageGids(str, i);
    }

    @Override // android.content.pm.PackageManager
    public int getPackageUid(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getPackageUid(str, i);
    }

    @Override // android.content.pm.PackageManager
    public PermissionInfo getPermissionInfo(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getPermissionInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<PermissionInfo> queryPermissionsByGroup(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.queryPermissionsByGroup(str, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public PermissionGroupInfo getPermissionGroupInfo(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getPermissionGroupInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
        return this.mAndroidPackageManager.getAllPermissionGroups(i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public ApplicationInfo getApplicationInfo(@Nonnull String str, int i) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getApplicationInfo(str, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public ActivityInfo getActivityInfo(@Nonnull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public ActivityInfo getReceiverInfo(@Nonnull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getReceiverInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public ServiceInfo getServiceInfo(@Nonnull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getServiceInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public ProviderInfo getProviderInfo(@Nonnull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getProviderInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<PackageInfo> getInstalledPackages(int i) {
        return this.mAndroidPackageManager.getInstalledPackages(i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<PackageInfo> getPackagesHoldingPermissions(@Nonnull String[] strArr, int i) {
        return this.mAndroidPackageManager.getPackagesHoldingPermissions(strArr, i);
    }

    public int checkPermission(@Nonnull String str, @Nonnull String str2) {
        bumpPackageQueried(str2);
        return this.mAndroidPackageManager.checkPermission(str, str2);
    }

    public boolean isPermissionRevokedByPolicy(@Nonnull String str, @Nonnull String str2) {
        bumpPackageQueried(str2);
        return this.mAndroidPackageManager.isPermissionRevokedByPolicy(str, str2);
    }

    public boolean addPermission(@Nonnull PermissionInfo permissionInfo) {
        return this.mAndroidPackageManager.addPermission(permissionInfo);
    }

    public boolean addPermissionAsync(@Nonnull PermissionInfo permissionInfo) {
        return this.mAndroidPackageManager.addPermissionAsync(permissionInfo);
    }

    public void removePermission(@Nonnull String str) {
        this.mAndroidPackageManager.removePermission(str);
    }

    @Override // android.content.pm.PackageManager
    public int checkSignatures(@Nonnull String str, @Nonnull String str2) {
        bumpPackageQueried(str);
        bumpPackageQueried(str2);
        return this.mAndroidPackageManager.checkSignatures(str, str2);
    }

    @Override // android.content.pm.PackageManager
    public int checkSignatures(int i, int i2) {
        return this.mAndroidPackageManager.checkSignatures(i, i2);
    }

    @Nullable
    public String[] getPackagesForUid(int i) {
        return this.mAndroidPackageManager.getPackagesForUid(i);
    }

    @Nullable
    public String getNameForUid(int i) {
        return this.mAndroidPackageManager.getNameForUid(i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ApplicationInfo> getInstalledApplications(int i) {
        return this.mAndroidPackageManager.getInstalledApplications(i);
    }

    public boolean isInstantApp() {
        return this.mAndroidPackageManager.isInstantApp();
    }

    public boolean isInstantApp(@Nonnull String str) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.isInstantApp(str);
    }

    public int getInstantAppCookieMaxBytes() {
        return this.mAndroidPackageManager.getInstantAppCookieMaxBytes();
    }

    @Nonnull
    public byte[] getInstantAppCookie() {
        return this.mAndroidPackageManager.getInstantAppCookie();
    }

    public void clearInstantAppCookie() {
        this.mAndroidPackageManager.clearInstantAppCookie();
    }

    public void updateInstantAppCookie(@Nullable byte[] bArr) {
        this.mAndroidPackageManager.updateInstantAppCookie(bArr);
    }

    @Nullable
    public String[] getSystemSharedLibraryNames() {
        return this.mAndroidPackageManager.getSystemSharedLibraryNames();
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<SharedLibraryInfo> getSharedLibraries(int i) {
        return this.mAndroidPackageManager.getSharedLibraries(i);
    }

    @Nullable
    public ChangedPackages getChangedPackages(int i) {
        return this.mAndroidPackageManager.getChangedPackages(i);
    }

    @Nonnull
    public FeatureInfo[] getSystemAvailableFeatures() {
        return this.mAndroidPackageManager.getSystemAvailableFeatures();
    }

    public boolean hasSystemFeature(@Nonnull String str) {
        return this.mAndroidPackageManager.hasSystemFeature(str);
    }

    public boolean hasSystemFeature(@Nonnull String str, int i) {
        return this.mAndroidPackageManager.hasSystemFeature(str, i);
    }

    @Nullable
    public ResolveInfo resolveActivity(@Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.resolveActivity(intent, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ResolveInfo> queryIntentActivities(@Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.queryIntentActivities(intent, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ResolveInfo> queryIntentActivityOptions(@Nullable ComponentName componentName, @Nullable Intent[] intentArr, @Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.queryIntentActivityOptions(componentName, intentArr, intent, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ResolveInfo> queryBroadcastReceivers(@Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.queryBroadcastReceivers(intent, i);
    }

    @Nullable
    public ResolveInfo resolveService(@Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.resolveService(intent, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ResolveInfo> queryIntentServices(@Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.queryIntentServices(intent, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ResolveInfo> queryIntentContentProviders(@Nonnull Intent intent, int i) {
        return this.mAndroidPackageManager.queryIntentContentProviders(intent, i);
    }

    @Nullable
    public ProviderInfo resolveContentProvider(@Nonnull String str, int i) {
        return this.mAndroidPackageManager.resolveContentProvider(str, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<ProviderInfo> queryContentProviders(@Nullable String str, int i, int i2) {
        return this.mAndroidPackageManager.queryContentProviders(str, i, i2);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public InstrumentationInfo getInstrumentationInfo(@Nonnull ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getInstrumentationInfo(componentName, i);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<InstrumentationInfo> queryInstrumentation(@Nonnull String str, int i) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.queryInstrumentation(str, i);
    }

    @Nullable
    public Drawable getDrawable(@Nonnull String str, int i, @Nullable ApplicationInfo applicationInfo) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getDrawable(str, i, applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Drawable getActivityIcon(@Nonnull ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityIcon(componentName);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Drawable getActivityIcon(@Nonnull Intent intent) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityIcon(intent);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getActivityBanner(@Nonnull ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityBanner(componentName);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getActivityBanner(@Nonnull Intent intent) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityBanner(intent);
    }

    @Nonnull
    public Drawable getDefaultActivityIcon() {
        return this.mAndroidPackageManager.getDefaultActivityIcon();
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Drawable getApplicationIcon(@Nonnull ApplicationInfo applicationInfo) {
        return this.mAndroidPackageManager.getApplicationIcon(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Drawable getApplicationIcon(@Nonnull String str) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getApplicationIcon(str);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getApplicationBanner(@Nonnull ApplicationInfo applicationInfo) {
        return this.mAndroidPackageManager.getApplicationBanner(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getApplicationBanner(@Nonnull String str) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getApplicationBanner(str);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getActivityLogo(@Nonnull ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityLogo(componentName);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getActivityLogo(@Nonnull Intent intent) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getActivityLogo(intent);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getApplicationLogo(@Nonnull ApplicationInfo applicationInfo) {
        return this.mAndroidPackageManager.getApplicationLogo(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    @Nullable
    public Drawable getApplicationLogo(@Nonnull String str) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getApplicationLogo(str);
    }

    @Nonnull
    public Drawable getUserBadgedIcon(@Nonnull Drawable drawable, @Nonnull UserHandle userHandle) {
        return this.mAndroidPackageManager.getUserBadgedIcon(drawable, userHandle);
    }

    @Nonnull
    public Drawable getUserBadgedDrawableForDensity(@Nonnull Drawable drawable, @Nonnull UserHandle userHandle, @Nullable Rect rect, int i) {
        return this.mAndroidPackageManager.getUserBadgedDrawableForDensity(drawable, userHandle, rect, i);
    }

    @Nonnull
    public CharSequence getUserBadgedLabel(@Nonnull CharSequence charSequence, @Nonnull UserHandle userHandle) {
        return this.mAndroidPackageManager.getUserBadgedLabel(charSequence, userHandle);
    }

    @Nullable
    public CharSequence getText(@Nonnull String str, int i, @Nullable ApplicationInfo applicationInfo) {
        return this.mAndroidPackageManager.getText(str, i, applicationInfo);
    }

    @Nullable
    public XmlResourceParser getXml(@Nonnull String str, int i, @Nullable ApplicationInfo applicationInfo) {
        return this.mAndroidPackageManager.getXml(str, i, applicationInfo);
    }

    @Nonnull
    public CharSequence getApplicationLabel(@Nonnull ApplicationInfo applicationInfo) {
        return this.mAndroidPackageManager.getApplicationLabel(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Resources getResourcesForActivity(@Nonnull ComponentName componentName) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getResourcesForActivity(componentName);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Resources getResourcesForApplication(@Nonnull ApplicationInfo applicationInfo) throws PackageManager.NameNotFoundException {
        return this.mAndroidPackageManager.getResourcesForApplication(applicationInfo);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public Resources getResourcesForApplication(@Nonnull String str) throws PackageManager.NameNotFoundException {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getResourcesForApplication(str);
    }

    public void verifyPendingInstall(int i, int i2) {
        this.mAndroidPackageManager.verifyPendingInstall(i, i2);
    }

    public void extendVerificationTimeout(int i, int i2, long j) {
        this.mAndroidPackageManager.extendVerificationTimeout(i, i2, j);
    }

    public void setInstallerPackageName(@Nonnull String str, @Nullable String str2) {
        bumpPackageQueried(str);
        bumpPackageQueried(str2);
        this.mAndroidPackageManager.setInstallerPackageName(str, str2);
    }

    @Nullable
    public String getInstallerPackageName(@Nonnull String str) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getInstallerPackageName(str);
    }

    public void addPackageToPreferred(@Nonnull String str) {
        bumpPackageQueried(str);
        this.mAndroidPackageManager.addPackageToPreferred(str);
    }

    public void removePackageFromPreferred(@Nonnull String str) {
        bumpPackageQueried(str);
        this.mAndroidPackageManager.removePackageFromPreferred(str);
    }

    @Override // android.content.pm.PackageManager
    @Nonnull
    public List<PackageInfo> getPreferredPackages(int i) {
        return this.mAndroidPackageManager.getPreferredPackages(i);
    }

    public void addPreferredActivity(@Nonnull IntentFilter intentFilter, int i, @Nullable ComponentName[] componentNameArr, @Nonnull ComponentName componentName) {
        this.mAndroidPackageManager.addPreferredActivity(intentFilter, i, componentNameArr, componentName);
    }

    public void clearPackagePreferredActivities(@Nonnull String str) {
        bumpPackageQueried(str);
        this.mAndroidPackageManager.clearPackagePreferredActivities(str);
    }

    @Override // android.content.pm.PackageManager
    public int getPreferredActivities(@Nonnull List<IntentFilter> list, @Nonnull List<ComponentName> list2, @Nullable String str) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getPreferredActivities(list, list2, str);
    }

    public void setComponentEnabledSetting(@Nonnull ComponentName componentName, int i, int i2) {
        this.mAndroidPackageManager.setComponentEnabledSetting(componentName, i, i2);
    }

    public int getComponentEnabledSetting(@Nonnull ComponentName componentName) {
        return this.mAndroidPackageManager.getComponentEnabledSetting(componentName);
    }

    public void setApplicationEnabledSetting(@Nonnull String str, int i, int i2) {
        bumpPackageQueried(str);
        this.mAndroidPackageManager.setApplicationEnabledSetting(str, i, i2);
    }

    public int getApplicationEnabledSetting(@Nonnull String str) {
        bumpPackageQueried(str);
        return this.mAndroidPackageManager.getApplicationEnabledSetting(str);
    }

    public boolean isSafeMode() {
        return this.mAndroidPackageManager.isSafeMode();
    }

    public void setApplicationCategoryHint(@Nonnull String str, int i) {
        bumpPackageQueried(str);
        this.mAndroidPackageManager.setApplicationCategoryHint(str, i);
    }

    @Nonnull
    public PackageInstaller getPackageInstaller() {
        return this.mAndroidPackageManager.getPackageInstaller();
    }

    public boolean canRequestPackageInstalls() {
        return this.mAndroidPackageManager.canRequestPackageInstalls();
    }
}
