package com.oculus.deviceconfigservice;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import X.AnonymousClass0Rj;
import X.AnonymousClass1BP;
import X.AnonymousClass1ZX;
import X.AnonymousClass1aT;
import X.AnonymousClass1aW;
import X.AnonymousClass1am;
import X.AnonymousClass1ao;
import X.AnonymousClass1b5;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.ApplicationScoped;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.ultralight.Dependencies;
import com.oculus.deviceconfigclient.shared.Constants;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfigservice_mobileconfigaccessor_MobileConfigAccessor_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigAppAwareAccessorDecorator extends AnonymousClass1aT {
    public static final int CONFIGS_UPDATE_RETRY_COUNT = 3;
    public static final int CONFIGS_UPDATE_RETRY_INTERVAL_N_SECONDS = 10;
    public static final long MS_IN_ONE_MINUTE = 60000;
    public static final long MS_IN_ONE_SECOND = 1000;
    public static final String PARAM_PATH_KEY = "com.oculus.deviceconfig.PARAMS_MAP_PATH";
    public static final String PERMISSION_PUSH_TO_CLIENT = "com.oculus.permission.DEVICE_CONFIG_PUSH_TO_CLIENT";
    public static final long SYNC_JOB_EVERY_N_MINUTES = 1;
    public static final String TAG = "MobileConfigAppAwareAccessorDecorator";
    public static final long UPDATE_MOBILE_CONFIGS_EVERY_N_MINUTES = 60;
    public static final long UPDATE_MOBILE_CONFIGS_EVERY_N_MS = 3600000;
    public static volatile MobileConfigAppAwareAccessorDecorator _UL__ULSEP_com_oculus_deviceconfigservice_MobileConfigAppAwareAccessorDecorator_ULSEP_INSTANCE = null;
    public static final boolean mDebugLog = true;
    public static final boolean mVerboseLog = false;
    public static boolean sAddScheduledTasks = true;
    public static final Set<String> sAllowedAppPrefixes = new HashSet<String>() {
        /* class com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator.AnonymousClass1 */

        {
            add("com.oculus.");
            add("com.facebook.");
            add("oculus.");
        }
    };
    public static ScheduledExecutorService sExecutor;
    public static volatile long sLastMobileConfigUpdate;
    public static volatile int sLastSequenceNumber;
    public final AnonymousClass1ao mAccessor;
    public final Map<String, AppInfo> mCachedAppInfosByName = new HashMap();
    public final Map<Integer, AppInfo> mCachedAppInfosByPid = new HashMap();
    public final Context mContext;
    public final Map<String, ValueInfo<Boolean>> mCurrentBooleanValues = new HashMap();
    public final Map<String, ValueInfo<Double>> mCurrentDoubleValues = new HashMap();
    public final Map<String, ValueInfo<Long>> mCurrentLongValues = new HashMap();
    public final Map<String, ValueInfo<String>> mCurrentStringValues = new HashMap();
    @Nullable
    public ConfigsUpdateJob mRequestConfigsUpdateJob;

    public class ConfigsUpdateJob implements Runnable {
        public final int mRetryCount;
        public volatile boolean mStopJob;

        public ConfigsUpdateJob(int i) {
            this.mRetryCount = i;
        }

        public final void run() {
            if (!this.mStopJob) {
                try {
                    MobileConfigAppAwareAccessorDecorator.A03(MobileConfigAppAwareAccessorDecorator.this, this.mRetryCount);
                } catch (Exception e) {
                    AnonymousClass0NO.A0H(MobileConfigAppAwareAccessorDecorator.TAG, e, "Exception while trying to request configs update");
                }
            }
        }
    }

    public class GlobalChangeListener implements MobileConfigCxxChangeListener {
        public GlobalChangeListener() {
        }

        @Override // com.facebook.mobileconfig.MobileConfigCxxChangeListener
        public final synchronized void onConfigChanged(String[] strArr) {
            HashSet<String> hashSet;
            String str;
            MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator = MobileConfigAppAwareAccessorDecorator.this;
            synchronized (mobileConfigAppAwareAccessorDecorator) {
                HashSet hashSet2 = new HashSet();
                for (AppInfo appInfo : mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByPid.values()) {
                    hashSet2.add(appInfo.PackageName);
                }
                AnonymousClass1ao r0 = mobileConfigAppAwareAccessorDecorator.mAccessor;
                synchronized (r0.A0A) {
                    hashSet = new HashSet(r0.A0B);
                }
                hashSet.removeAll(hashSet2);
            }
            HashSet hashSet3 = new HashSet(Arrays.asList(strArr));
            for (String str2 : hashSet) {
                AnonymousClass1ao r2 = mobileConfigAppAwareAccessorDecorator.mAccessor;
                File filesDir = r2.A03.getApplicationContext().getFilesDir();
                if (AnonymousClass1aW.A09(filesDir, str2)) {
                    try {
                        String A04 = AnonymousClass1aW.A04(AnonymousClass1aW.A02(filesDir, str2));
                        AnonymousClass1ao.A00(r2, str2);
                        if (A04 != null) {
                            List<AnonymousClass1am> A01 = AnonymousClass1am.A01(A04, false);
                            boolean z = false;
                            for (AnonymousClass1am r02 : A01) {
                                z |= hashSet3.contains(r02.A06);
                            }
                            if (z) {
                                String A00 = AnonymousClass1am.A00(A04);
                                A01.size();
                                AnonymousClass1ZX r13 = new AnonymousClass1ZX(str2, 0);
                                String[] strArr2 = new String[A01.size()];
                                int i = 0;
                                for (AnonymousClass1am r03 : A01) {
                                    strArr2[i] = AnonymousClass006.A07(r03.A06, ":", r03.A07);
                                    i++;
                                }
                                List<String[]> A09 = mobileConfigAppAwareAccessorDecorator.A09(r13, strArr2);
                                StorageInternalRepresentation storageInternalRepresentation = new StorageInternalRepresentation();
                                storageInternalRepresentation.ParamsMapVersion = A00;
                                int ordinal = AnonymousClass1b5.CONFIG_PARAM_NAME.ordinal();
                                int ordinal2 = AnonymousClass1b5.TYPE.ordinal();
                                int ordinal3 = AnonymousClass1b5.VALUE.ordinal();
                                int ordinal4 = AnonymousClass1b5.LOGGING_ID.ordinal();
                                int ordinal5 = AnonymousClass1b5.IS_SESSIONLESS.ordinal();
                                for (String[] strArr3 : A09) {
                                    String str3 = strArr3[ordinal];
                                    String str4 = strArr3[ordinal2];
                                    String str5 = strArr3[ordinal3];
                                    String str6 = strArr3[ordinal4];
                                    boolean parseBoolean = Boolean.parseBoolean(strArr3[ordinal5]);
                                    try {
                                        int parseInt = Integer.parseInt(str4);
                                        if (parseInt == 1) {
                                            str = Constants.BOOLEAN_TYPE_JSON_VALUE;
                                        } else if (parseInt == 2) {
                                            str = "long";
                                        } else if (parseInt == 3) {
                                            str = "string";
                                        } else if (parseInt != 4) {
                                            AnonymousClass0NO.A0E(MobileConfigAppAwareAccessorDecorator.TAG, "Unrecognized type '%s' for config param '%s'.", str4, str3);
                                        } else {
                                            str = "double";
                                        }
                                        storageInternalRepresentation.Params.add(new StorageParam(str3, str, str5, str6, parseBoolean));
                                    } catch (NumberFormatException unused) {
                                        AnonymousClass0NO.A0E(MobileConfigAppAwareAccessorDecorator.TAG, "Unrecognized type '%s' for config param '%s'.", str4, str3);
                                    }
                                }
                                Intent intent = new Intent();
                                intent.setAction(Constants.DC_SET_CLIENT_VALUES_ACTION);
                                intent.putExtra(Constants.INTENT_EXTRA_INTERNAL_REPRESENTATION, storageInternalRepresentation);
                                intent.setPackage(str2);
                                mobileConfigAppAwareAccessorDecorator.mContext.sendBroadcast(intent, MobileConfigAppAwareAccessorDecorator.PERMISSION_PUSH_TO_CLIENT);
                            }
                        }
                    } catch (IOException e) {
                        AnonymousClass0NO.A0K("MobileConfigAccessor", e, "Error reading params map for package '%s'", str2);
                    }
                }
            }
        }
    }

    public static class ValueInfo<Type> {
        public Set<Integer> AssociatedPids = new HashSet();
        @Nullable
        public Type Value;
    }

    public static void A04(MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator, String str, String str2, int i) {
        AppInfo appInfo;
        synchronized (mobileConfigAppAwareAccessorDecorator) {
            appInfo = mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByName.get(str2);
        }
        if (appInfo != null) {
            int i2 = appInfo.Pid;
            if (i2 != i) {
                for (String str3 : appInfo.AssociatedConfigs) {
                    if (!A06(i2, mobileConfigAppAwareAccessorDecorator.mCurrentBooleanValues, str3) && !A06(i2, mobileConfigAppAwareAccessorDecorator.mCurrentDoubleValues, str3) && !A06(i2, mobileConfigAppAwareAccessorDecorator.mCurrentLongValues, str3) && !A06(i2, mobileConfigAppAwareAccessorDecorator.mCurrentStringValues, str3)) {
                        AnonymousClass0NO.A0E(TAG, "Could not remove config param %s previously used by %s (pid %s)", str3, appInfo.ProcessName, Integer.valueOf(i2));
                    }
                }
                synchronized (mobileConfigAppAwareAccessorDecorator) {
                    mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByName.remove(appInfo.ProcessName);
                    mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByPid.remove(Integer.valueOf(appInfo.Pid));
                }
            } else {
                return;
            }
        }
        AppInfo appInfo2 = new AppInfo(str, str2, i);
        synchronized (mobileConfigAppAwareAccessorDecorator) {
            mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByName.put(appInfo2.ProcessName, appInfo2);
            mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByPid.put(Integer.valueOf(appInfo2.Pid), appInfo2);
        }
    }

    /* renamed from: com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$mobileconfig$factory$MobileConfigValueSource;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                X.0Rj[] r0 = X.AnonymousClass0Rj.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator.AnonymousClass4.$SwitchMap$com$facebook$mobileconfig$factory$MobileConfigValueSource = r2
                X.0Rj r0 = X.AnonymousClass0Rj.DEFAULT__INVALID_CONFIG_PARAM_NAME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                X.0Rj r0 = X.AnonymousClass0Rj.DEFAULT__NO_DATA_ON_DISK     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                X.0Rj r0 = X.AnonymousClass0Rj.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator.AnonymousClass4.<clinit>():void");
        }
    }

    public static class AppInfo {
        public Set<String> AssociatedConfigs = new HashSet();
        public final String PackageName;
        public final int Pid;
        public final String ProcessName;

        public AppInfo(String str, String str2, int i) {
            this.PackageName = str;
            this.ProcessName = str2;
            this.Pid = i;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.util.Map<java.lang.String, java.lang.String> */
    /* JADX DEBUG: Multi-variable search result rejected for r2v4, resolved type: java.util.Map<java.lang.String, java.lang.Long> */
    /* JADX DEBUG: Multi-variable search result rejected for r2v8, resolved type: java.util.Map<java.lang.String, java.lang.Double> */
    /* JADX DEBUG: Multi-variable search result rejected for r2v12, resolved type: java.util.Map<java.lang.String, java.lang.Boolean> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c2, code lost:
        r5 = r18.mContext;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c4, code lost:
        monitor-enter(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r12.mOverriddenBooleanValues.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cc, code lost:
        monitor-enter(com.oculus.deviceconfigservice.DeviceConfigOverrideUtil.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r10 = com.oculus.deviceconfigservice.DeviceConfigSharedPreferencesNames.OVERRIDDEN_VALUE;
        r0 = r5.getSharedPreferences(r10.toString(), 0).getString(com.oculus.deviceconfigservice.DeviceConfigOverrideUtil.PREF_BOOLEAN_VALUES, "{}");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e0, code lost:
        if (r0 != null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e2, code lost:
        r0 = "{}";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e3, code lost:
        r8 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r3 = new org.json.JSONObject(r0);
        r2 = r3.keys();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f5, code lost:
        if (r2.hasNext() == false) goto L_0x0119;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f7, code lost:
        r1 = r2.next();
        r8.put(r1, java.lang.Boolean.valueOf(r3.getBoolean(r1)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0109, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010a, code lost:
        X.AnonymousClass0NO.A0E(com.oculus.deviceconfigservice.DeviceConfigOverrideUtil.TAG, "Error when parsing device config overrides map for shared preferences key: %s", com.oculus.deviceconfigservice.DeviceConfigOverrideUtil.PREF_BOOLEAN_VALUES, r6);
     */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0274 A[LOOP:7: B:108:0x026e->B:110:0x0274, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0198 A[LOOP:3: B:72:0x0192->B:74:0x0198, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0208 A[LOOP:5: B:90:0x0202->B:92:0x0208, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0222  */
    @com.facebook.ultralight.Inject
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MobileConfigAppAwareAccessorDecorator(X.AnonymousClass1ao r19, @com.facebook.inject.ForAppContext android.content.Context r20) {
        /*
        // Method dump skipped, instructions count: 668
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator.<init>(X.1ao, android.content.Context):void");
    }

    /* JADX WARN: Incorrect args count in method signature: <Type:Ljava/lang/Object;>(LX/1ZX;Ljava/util/Map<Ljava/lang/String;Lcom/oculus/deviceconfigservice/MobileConfigAppAwareAccessorDecorator$ValueInfo<TType;>;>;TType;Ljava/lang/String;)TType; */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005f, code lost:
        if (r0 != false) goto L_0x0061;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object A00(com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator r4, X.AnonymousClass1ZX r5, @javax.annotation.Nullable java.util.Map r6, java.lang.Object r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator.A00(com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator, X.1ZX, java.util.Map, java.lang.Object, java.lang.String):java.lang.Object");
    }

    public static final String A01(MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator, AnonymousClass1ZX r3) {
        AppInfo appInfo;
        int i = r3.A00;
        synchronized (mobileConfigAppAwareAccessorDecorator) {
            appInfo = mobileConfigAppAwareAccessorDecorator.mCachedAppInfosByPid.get(Integer.valueOf(i));
        }
        if (appInfo != null) {
            return appInfo.ProcessName;
        }
        return r3.A01;
    }

    public static <Type> void A02(AnonymousClass1ZX r2, Map<String, ValueInfo<Type>> map, String str, Type type) {
        int i = r2.A00;
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(i));
        ValueInfo<Type> valueInfo = map.get(str);
        if (valueInfo == null) {
            valueInfo = new ValueInfo<>();
            map.put(str, valueInfo);
        } else {
            type.equals(valueInfo.Value);
        }
        valueInfo.Value = type;
        valueInfo.AssociatedPids = hashSet;
    }

    public static boolean A07(MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator) {
        boolean z;
        Bundle bundle;
        String string;
        String str;
        InputStream inputStream;
        Context context = mobileConfigAppAwareAccessorDecorator.mContext;
        AnonymousClass1ao r8 = mobileConfigAppAwareAccessorDecorator.mAccessor;
        Set<String> set = sAllowedAppPrefixes;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            AnonymousClass0NO.A08("MobileConfigAccessorDecoratorUtil", "No PackageManager found.");
            return false;
        }
        boolean z2 = false;
        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE)) {
            String str2 = applicationInfo.packageName;
            if (str2 == null) {
                AnonymousClass0NO.A0E("MobileConfigAccessorDecoratorUtil", "Could not get the package name for the application %s", applicationInfo);
            } else {
                Iterator<String> it = set.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        continue;
                        break;
                    } else if (str2.startsWith(it.next())) {
                        try {
                            String A07 = AnonymousClass006.A07(str2, "#", packageManager.getPackageInfo(str2, 0).versionName);
                            Set<String> set2 = AnonymousClass1BP.A00;
                            synchronized (set2) {
                                z = set2.add(A07);
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            AnonymousClass0NO.A0K("MobileConfigAccessorDecoratorUtil", e, "Could not get version for %s", str2);
                            Set<String> set3 = AnonymousClass1BP.A00;
                            synchronized (set3) {
                                z = set3.add(str2);
                            }
                        }
                        if (z && (bundle = applicationInfo.metaData) != null && (string = bundle.getString(PARAM_PATH_KEY)) != null && (str = applicationInfo.sourceDir) != null) {
                            try {
                                JarFile jarFile = new JarFile(str);
                                JarEntry jarEntry = jarFile.getJarEntry(string);
                                if (jarEntry == null || (inputStream = jarFile.getInputStream(jarEntry)) == null) {
                                    AnonymousClass0NO.A0E("MobileConfigAccessorDecoratorUtil", "Could not find file %s for package %s", string, str2);
                                } else {
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        byteArrayOutputStream.write(bArr, 0, read);
                                    }
                                    r8.A01(str2, byteArrayOutputStream.toString("UTF-8"));
                                    z2 = true;
                                }
                            } catch (IOException e2) {
                                AnonymousClass0NO.A0K("MobileConfigAccessorDecoratorUtil", e2, "Could not read content for package %s", str2);
                            }
                        }
                    }
                }
            }
        }
        if (z2) {
            return z2;
        }
        AnonymousClass0NO.A09("MobileConfigAccessorDecoratorUtil", "Could not find new app to prefetch");
        return z2;
    }

    public static boolean A08(MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator) {
        boolean z;
        AnonymousClass1ao r5 = mobileConfigAppAwareAccessorDecorator.mAccessor;
        synchronized (r5) {
            if (r5.A0C.get()) {
                AnonymousClass0NO.A09("MobileConfigAccessor", "Service currently upgrading");
                z = false;
            } else {
                if (!r5.A06.updateConfigsSynchronouslyWithDefaultUpdater(5000)) {
                    AnonymousClass0NO.A0F("MobileConfigAccessor", "Failed to update sessionbased configs within timeout %d ms", 5000);
                    z = false;
                } else {
                    z = true;
                }
                if (!r5.A07.updateConfigsSynchronouslyWithDefaultUpdater(5000)) {
                    AnonymousClass0NO.A0F("MobileConfigAccessor", "Failed to update sessionless configs within timeout %d ms", 5000);
                    z = false;
                }
            }
        }
        synchronized (mobileConfigAppAwareAccessorDecorator) {
            ConfigsUpdateJob configsUpdateJob = mobileConfigAppAwareAccessorDecorator.mRequestConfigsUpdateJob;
            if (configsUpdateJob != null) {
                configsUpdateJob.mStopJob = true;
                mobileConfigAppAwareAccessorDecorator.mRequestConfigsUpdateJob = null;
            }
        }
        if (z) {
            sLastMobileConfigUpdate = System.currentTimeMillis();
            return z;
        }
        AnonymousClass0NO.A09(TAG, "Failed to update configs in mobile config service");
        return z;
    }

    public static void A03(MobileConfigAppAwareAccessorDecorator mobileConfigAppAwareAccessorDecorator, int i) {
        if (!A08(mobileConfigAppAwareAccessorDecorator) && i > 0) {
            AnonymousClass0NO.A0F(TAG, "Failed to update configs. Will retry for %d more times", Integer.valueOf(i));
            int i2 = i - 1;
            synchronized (mobileConfigAppAwareAccessorDecorator) {
                if (mobileConfigAppAwareAccessorDecorator.mRequestConfigsUpdateJob == null) {
                    ConfigsUpdateJob configsUpdateJob = new ConfigsUpdateJob(i2);
                    mobileConfigAppAwareAccessorDecorator.mRequestConfigsUpdateJob = configsUpdateJob;
                    sExecutor.schedule(configsUpdateJob, 10, TimeUnit.SECONDS);
                }
            }
        }
    }

    public static void A05(String str, String str2, AnonymousClass0Rj r7) {
        Object[] objArr;
        String str3;
        switch (r7.ordinal()) {
            case 0:
                objArr = new Object[]{str2, str};
                str3 = "Config name %s has an unknown source in package %s";
                break;
            case 5:
                AnonymousClass0NO.A0F(TAG, "A config in the package %s did not exist on disk. Try to update configs.", str);
                return;
            case 8:
                objArr = new Object[]{str2, str};
                str3 = "Config name %s is not recognized in package %s";
                break;
            default:
                return;
        }
        AnonymousClass0NO.A0E(TAG, str3, objArr);
    }

    public static <Type> boolean A06(int i, Map<String, ValueInfo<Type>> map, String str) {
        ValueInfo<Type> valueInfo = map.get(str);
        if (valueInfo == null) {
            return false;
        }
        valueInfo.AssociatedPids.remove(Integer.valueOf(i));
        if (!valueInfo.AssociatedPids.isEmpty()) {
            return true;
        }
        map.remove(str);
        return true;
    }
}
