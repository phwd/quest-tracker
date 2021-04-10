package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.soloader.nativeloader.NativeLoader;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_DISABLE_BACKUP_SOSOURCE = 8;
    public static final int SOLOADER_DONT_TREAT_AS_SYSTEMAPP = 32;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    public static final int SOLOADER_SKIP_MERGED_JNI_ONLOAD = 16;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    private static final String SO_STORE_NAME_SPLIT = "lib-";
    static final boolean SYSTRACE_LIBRARY_LOADING;
    static final String TAG = "SoLoader";
    private static boolean isSystemApp;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static ApplicationSoSource sApplicationSoSource;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static UnpackingSoSource[] sBackupSoSources;
    @GuardedBy("sSoSourcesLock")
    private static int sFlags;
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    @GuardedBy("SoLoader.class")
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();
    @GuardedBy("SoLoader.class")
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    @Nullable
    static SoFileLoader sSoFileLoader;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static SoSource[] sSoSources = null;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    @GuardedBy("sSoSourcesLock")
    private static volatile int sSoSourcesVersion = 0;
    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;

    static /* synthetic */ int access$208() {
        int i = sSoSourcesVersion;
        sSoSourcesVersion = i + 1;
        return i;
    }

    static {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                z = true;
            }
        } catch (NoClassDefFoundError | UnsatisfiedLinkError unused) {
        }
        SYSTRACE_LIBRARY_LOADING = z;
    }

    public static void init(Context context, int i) throws IOException {
        init(context, i, null);
    }

    public static void init(Context context, int i, @Nullable SoFileLoader soFileLoader) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            isSystemApp = checkIfSystemApp(context, i);
            initSoLoader(soFileLoader);
            initSoSources(context, i, soFileLoader);
            if (!NativeLoader.isInitialized()) {
                NativeLoader.init(new NativeLoaderToSoLoaderDelegate());
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, z ? 1 : 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initSoSources(Context context, int i, @Nullable SoFileLoader soFileLoader) throws IOException {
        int i2;
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources == null) {
                Log.d(TAG, "init start");
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = SysUtil.is64Bit() ? "/vendor/lib64:/system/lib64" : "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (String str2 : split) {
                    Log.d(TAG, "adding system library source: " + str2);
                    arrayList.add(new DirectorySoSource(new File(str2), 2));
                }
                if (context != null) {
                    if ((i & 1) != 0) {
                        sBackupSoSources = null;
                        Log.d(TAG, "adding exo package source: lib-main");
                        arrayList.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                    } else {
                        if (isSystemApp) {
                            i2 = 0;
                        } else {
                            sApplicationSoSource = new ApplicationSoSource(context, Build.VERSION.SDK_INT <= 17 ? 1 : 0);
                            Log.d(TAG, "adding application source: " + sApplicationSoSource.toString());
                            arrayList.add(0, sApplicationSoSource);
                            i2 = 1;
                        }
                        if ((sFlags & 8) != 0) {
                            sBackupSoSources = null;
                        } else {
                            File file = new File(context.getApplicationInfo().sourceDir);
                            ArrayList arrayList2 = new ArrayList();
                            ApkSoSource apkSoSource = new ApkSoSource(context, file, SO_STORE_NAME_MAIN, i2);
                            arrayList2.add(apkSoSource);
                            Log.d(TAG, "adding backup source from : " + apkSoSource.toString());
                            if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
                                Log.d(TAG, "adding backup sources from split apks");
                                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                int length = strArr.length;
                                int i3 = 0;
                                int i4 = 0;
                                while (i3 < length) {
                                    File file2 = new File(strArr[i3]);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(SO_STORE_NAME_SPLIT);
                                    sb.append(i4);
                                    ApkSoSource apkSoSource2 = new ApkSoSource(context, file2, sb.toString(), i2);
                                    Log.d(TAG, "adding backup source: " + apkSoSource2.toString());
                                    arrayList2.add(apkSoSource2);
                                    i3++;
                                    i4++;
                                }
                            }
                            sBackupSoSources = (UnpackingSoSource[]) arrayList2.toArray(new UnpackingSoSource[arrayList2.size()]);
                            arrayList.addAll(0, arrayList2);
                        }
                    }
                }
                SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                int makePrepareFlags = makePrepareFlags();
                int length2 = soSourceArr.length;
                while (true) {
                    int i5 = length2 - 1;
                    if (length2 <= 0) {
                        break;
                    }
                    Log.d(TAG, "Preparing SO source: " + soSourceArr[i5]);
                    soSourceArr[i5].prepare(makePrepareFlags);
                    length2 = i5;
                }
                sSoSources = soSourceArr;
                sSoSourcesVersion++;
                Log.d(TAG, "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            Log.d(TAG, "init exiting");
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static int makePrepareFlags() {
        sSoSourcesLock.writeLock().lock();
        try {
            return (sFlags & 2) != 0 ? 1 : 0;
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
                return;
            }
            final Runtime runtime = Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
            final boolean z = nativeLoadRuntimeMethod != null;
            final String classLoaderLdLoadLibrary = z ? Api14Utils.getClassLoaderLdLoadLibrary() : null;
            final String makeNonZipPath = makeNonZipPath(classLoaderLdLoadLibrary);
            sSoFileLoader = new SoFileLoader() {
                /* class com.facebook.soloader.SoLoader.AnonymousClass1 */

                /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
                    if (r1 == null) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
                    android.util.Log.e(com.facebook.soloader.SoLoader.TAG, "Error when loading lib: " + r1 + " lib hash: " + getLibHash(r9) + " search path is " + r10);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                    return;
                 */
                @Override // com.facebook.soloader.SoFileLoader
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void load(java.lang.String r9, int r10) {
                    /*
                    // Method dump skipped, instructions count: 191
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.load(java.lang.String, int):void");
                }

                /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
                    r8 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
                    if (r8 != null) goto L_0x003f;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
                    r1.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
                    r1 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
                    r8.addSuppressed(r1);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
                    r1.close();
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                private java.lang.String getLibHash(java.lang.String r8) {
                    /*
                        r7 = this;
                        java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        r0.<init>(r8)     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        java.lang.String r8 = "MD5"
                        java.security.MessageDigest r8 = java.security.MessageDigest.getInstance(r8)     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        r1.<init>(r0)     // Catch:{ IOException -> 0x0058, SecurityException -> 0x0052, NoSuchAlgorithmException -> 0x004c }
                        r0 = 0
                        r2 = 4096(0x1000, float:5.74E-42)
                        byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x003a }
                    L_0x0015:
                        int r3 = r1.read(r2)     // Catch:{ Throwable -> 0x003a }
                        r4 = 0
                        if (r3 <= 0) goto L_0x0020
                        r8.update(r2, r4, r3)     // Catch:{ Throwable -> 0x003a }
                        goto L_0x0015
                    L_0x0020:
                        java.lang.String r2 = "%32x"
                        r3 = 1
                        java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x003a }
                        java.math.BigInteger r6 = new java.math.BigInteger     // Catch:{ Throwable -> 0x003a }
                        byte[] r8 = r8.digest()     // Catch:{ Throwable -> 0x003a }
                        r6.<init>(r3, r8)     // Catch:{ Throwable -> 0x003a }
                        r5[r4] = r6     // Catch:{ Throwable -> 0x003a }
                        java.lang.String r8 = java.lang.String.format(r2, r5)     // Catch:{ Throwable -> 0x003a }
                        r1.close()
                        goto L_0x005d
                    L_0x0038:
                        r8 = move-exception
                        goto L_0x003d
                    L_0x003a:
                        r8 = move-exception
                        r0 = r8
                        throw r0     // Catch:{ all -> 0x0038 }
                    L_0x003d:
                        if (r0 == 0) goto L_0x0048
                        r1.close()     // Catch:{ Throwable -> 0x0043 }
                        goto L_0x004b
                    L_0x0043:
                        r1 = move-exception
                        r0.addSuppressed(r1)
                        goto L_0x004b
                    L_0x0048:
                        r1.close()
                    L_0x004b:
                        throw r8
                    L_0x004c:
                        r8 = move-exception
                        java.lang.String r8 = r8.toString()
                        goto L_0x005d
                    L_0x0052:
                        r8 = move-exception
                        java.lang.String r8 = r8.toString()
                        goto L_0x005d
                    L_0x0058:
                        r8 = move-exception
                        java.lang.String r8 = r8.toString()
                    L_0x005d:
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.getLibHash(java.lang.String):java.lang.String");
                }
            };
        }
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT <= 27) {
            try {
                Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException | SecurityException e) {
                Log.w(TAG, "Cannot get nativeLoad method", e);
            }
        }
        return null;
    }

    private static boolean checkIfSystemApp(Context context, int i) {
        return ((i & 32) != 0 || context == null || (context.getApplicationInfo().flags & 129) == 0) ? false : true;
    }

    public static void setInTestMode() {
        TestOnlyUtils.setSoSources(new SoSource[]{new NoopSoSource()});
    }

    public static void deinitForTest() {
        TestOnlyUtils.setSoSources(null);
    }

    @NotThreadSafe
    static class TestOnlyUtils {
        TestOnlyUtils() {
        }

        static void setSoSources(SoSource[] soSourceArr) {
            SoLoader.sSoSourcesLock.writeLock().lock();
            try {
                SoSource[] unused = SoLoader.sSoSources = soSourceArr;
                SoLoader.access$208();
            } finally {
                SoLoader.sSoSourcesLock.writeLock().unlock();
            }
        }

        static void setSoFileLoader(SoFileLoader soFileLoader) {
            SoLoader.sSoFileLoader = soFileLoader;
        }

        static void resetStatus() {
            synchronized (SoLoader.class) {
                SoLoader.sLoadedLibraries.clear();
                SoLoader.sLoadingLibraries.clear();
                SoLoader.sSoFileLoader = null;
            }
            setSoSources(null);
        }
    }

    public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper systemLoadLibraryWrapper) {
        sSystemLoadLibraryWrapper = systemLoadLibraryWrapper;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    @Nullable
    public static String getLibraryPath(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String str2 = null;
            if (sSoSources != null) {
                int i = 0;
                while (str2 == null && i < sSoSources.length) {
                    str2 = sSoSources[i].getLibraryPath(str);
                    i++;
                }
            }
            return str2;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String[] getLibraryDependencies(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String[] strArr = null;
            if (sSoSources != null) {
                int i = 0;
                while (strArr == null && i < sSoSources.length) {
                    strArr = sSoSources[i].getLibraryDependencies(str);
                    i++;
                }
            }
            return strArr;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    /* JADX INFO: finally extract failed */
    public static boolean loadLibrary(String str, int i) throws UnsatisfiedLinkError {
        SystemLoadLibraryWrapper systemLoadLibraryWrapper;
        boolean z;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        z = !sLoadedLibraries.contains(str);
                        if (z) {
                            if (sSystemLoadLibraryWrapper != null) {
                                sSystemLoadLibraryWrapper.loadLibrary(str);
                            } else {
                                System.loadLibrary(str);
                            }
                        }
                    }
                    sSoSourcesLock.readLock().unlock();
                    return z;
                }
            }
            sSoSourcesLock.readLock().unlock();
            if (!isSystemApp || (systemLoadLibraryWrapper = sSystemLoadLibraryWrapper) == null) {
                String mapLibName = MergedSoMapping.mapLibName(str);
                return loadLibraryBySoName(System.mapLibraryName(mapLibName != null ? mapLibName : str), str, mapLibName, i, null);
            }
            systemLoadLibraryWrapper.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    static void loadLibraryBySoName(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        loadLibraryBySoNameImpl(str, null, null, i, threadPolicy);
    }

    private static boolean loadLibraryBySoName(String str, @Nullable String str2, @Nullable String str3, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean z2 = false;
        do {
            try {
                z2 = loadLibraryBySoNameImpl(str, str2, str3, i, threadPolicy);
                z = false;
                continue;
            } catch (UnsatisfiedLinkError e) {
                int i2 = sSoSourcesVersion;
                sSoSourcesLock.writeLock().lock();
                try {
                    z = true;
                    if (sApplicationSoSource == null || !sApplicationSoSource.checkAndMaybeUpdate()) {
                        z = false;
                    } else {
                        Log.w(TAG, "sApplicationSoSource updated during load: " + str + ", attempting load again.");
                        sSoSourcesVersion = sSoSourcesVersion + 1;
                    }
                    sSoSourcesLock.writeLock().unlock();
                    if (sSoSourcesVersion == i2) {
                        throw e;
                    }
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                } catch (Throwable th) {
                    sSoSourcesLock.writeLock().unlock();
                    throw th;
                }
            }
        } while (z);
        return z2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        if (r2 != false) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        if (com.facebook.soloader.SoLoader.sLoadedLibraries.contains(r7) == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004b, code lost:
        if (r9 != null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004e, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0050, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0052, code lost:
        if (r2 != false) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        android.util.Log.d(com.facebook.soloader.SoLoader.TAG, "About to load: " + r7);
        doLoadLibraryBySoName(r7, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        android.util.Log.d(com.facebook.soloader.SoLoader.TAG, "Loaded: " + r7);
        com.facebook.soloader.SoLoader.sLoadedLibraries.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008b, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0090, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0091, code lost:
        r8 = r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0095, code lost:
        if (r8 == null) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ae, code lost:
        throw new com.facebook.soloader.SoLoader.WrongAbiError(r7, r8.substring(r8.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00af, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b8, code lost:
        if ((r10 & 16) != 0) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00be, code lost:
        if (android.text.TextUtils.isEmpty(r8) != false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c6, code lost:
        if (com.facebook.soloader.SoLoader.sLoadedAndMergedLibraries.contains(r8) == false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c8, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00c9, code lost:
        if (r9 == null) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00cb, code lost:
        if (r1 != false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00cf, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING == false) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00d1, code lost:
        com.facebook.soloader.Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", r8, "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        android.util.Log.d(com.facebook.soloader.SoLoader.TAG, "About to merge: " + r8 + " / " + r7);
        com.facebook.soloader.MergedSoMapping.invokeJniOnload(r8);
        com.facebook.soloader.SoLoader.sLoadedAndMergedLibraries.add(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0100, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING == false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0102, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0106, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0108, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x012c, code lost:
        throw new java.lang.RuntimeException("Failed to call JNI_OnLoad from '" + r8 + "', which has been merged into '" + r7 + "'.  See comment for details.", r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x012f, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING != false) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0131, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0134, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0135, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0138, code lost:
        return !r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryBySoNameImpl(java.lang.String r7, @javax.annotation.Nullable java.lang.String r8, @javax.annotation.Nullable java.lang.String r9, int r10, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r11) {
        /*
        // Method dump skipped, instructions count: 318
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryBySoNameImpl(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0154 A[LOOP:3: B:69:0x014f->B:71:0x0154, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doLoadLibraryBySoName(java.lang.String r16, int r17, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r18) throws java.lang.UnsatisfiedLinkError {
        /*
        // Method dump skipped, instructions count: 545
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.doLoadLibraryBySoName(java.lang.String, int, android.os.StrictMode$ThreadPolicy):void");
    }

    @Nullable
    public static String makeNonZipPath(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(":", arrayList);
    }

    static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            for (SoSource soSource : sSoSources) {
                File unpackLibrary = soSource.unpackLibrary(str);
                if (unpackLibrary != null) {
                    return unpackLibrary;
                }
            }
            sSoSourcesLock.readLock().unlock();
            throw new FileNotFoundException(str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new RuntimeException("SoLoader.init() not yet called");
        }
    }

    public static boolean isInitialized() {
        sSoSourcesLock.readLock().lock();
        try {
            return sSoSources != null;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion;
    }

    public static void prependSoSource(SoSource soSource) throws IOException {
        sSoSourcesLock.writeLock().lock();
        try {
            Log.d(TAG, "Prepending to SO sources: " + soSource);
            assertInitialized();
            soSource.prepare(makePrepareFlags());
            SoSource[] soSourceArr = new SoSource[(sSoSources.length + 1)];
            soSourceArr[0] = soSource;
            System.arraycopy(sSoSources, 0, soSourceArr, 1, sSoSources.length);
            sSoSources = soSourceArr;
            sSoSourcesVersion++;
            Log.d(TAG, "Prepended to SO sources: " + soSource);
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static String makeLdLibraryPath() {
        sSoSourcesLock.readLock().lock();
        try {
            assertInitialized();
            Log.d(TAG, "makeLdLibraryPath");
            ArrayList arrayList = new ArrayList();
            SoSource[] soSourceArr = sSoSources;
            if (soSourceArr != null) {
                for (SoSource soSource : soSourceArr) {
                    soSource.addToLdLibraryPath(arrayList);
                }
            }
            String join = TextUtils.join(":", arrayList);
            Log.d(TAG, "makeLdLibraryPath final path: " + join);
            return join;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean areSoSourcesAbisSupported() {
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                String[] supportedAbis = SysUtil.getSupportedAbis();
                for (SoSource soSource : sSoSources) {
                    String[] soSourceAbis = soSource.getSoSourceAbis();
                    for (String str : soSourceAbis) {
                        boolean z = false;
                        for (int i = 0; i < supportedAbis.length && !z; i++) {
                            z = str.equals(supportedAbis[i]);
                        }
                        if (!z) {
                            Log.e(TAG, "abi not supported: " + str);
                        }
                    }
                }
                sSoSourcesLock.readLock().unlock();
                return true;
            }
            return false;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    /* access modifiers changed from: private */
    @DoNotOptimize
    @TargetApi(14)
    public static class Api14Utils {
        private Api14Utils() {
        }

        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader == null || (classLoader instanceof BaseDexClassLoader)) {
                try {
                    return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot call getLdLibraryPath", e);
                }
            } else {
                throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
            }
        }
    }
}
