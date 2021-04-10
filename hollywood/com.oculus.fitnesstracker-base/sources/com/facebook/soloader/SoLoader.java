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
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SoLoader {
    static final boolean SYSTRACE_LIBRARY_LOADING;
    private static boolean isSystemApp;
    private static ApplicationSoSource sApplicationSoSource;
    private static UnpackingSoSource[] sBackupSoSources;
    private static int sFlags;
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    static SoFileLoader sSoFileLoader;
    private static SoSource[] sSoSources = null;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    private static volatile int sSoSourcesVersion = 0;
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;

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

    private static void initSoSources$230dc011(Context context, int i) throws IOException {
        int i2;
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources == null) {
                Log.d("SoLoader", "init start");
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = SysUtil.is64Bit() ? "/vendor/lib64:/system/lib64" : "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (String str2 : split) {
                    Log.d("SoLoader", "adding system library source: " + str2);
                    arrayList.add(new DirectorySoSource(new File(str2), 2));
                }
                if (context != null) {
                    if ((i & 1) != 0) {
                        sBackupSoSources = null;
                        Log.d("SoLoader", "adding exo package source: lib-main");
                        arrayList.add(0, new ExoSoSource(context, "lib-main"));
                    } else {
                        if (isSystemApp) {
                            i2 = 0;
                        } else {
                            sApplicationSoSource = new ApplicationSoSource(context, Build.VERSION.SDK_INT <= 17 ? 1 : 0);
                            Log.d("SoLoader", "adding application source: " + sApplicationSoSource.toString());
                            arrayList.add(0, sApplicationSoSource);
                            i2 = 1;
                        }
                        if ((sFlags & 8) != 0) {
                            sBackupSoSources = null;
                        } else {
                            File file = new File(context.getApplicationInfo().sourceDir);
                            ArrayList arrayList2 = new ArrayList();
                            ApkSoSource apkSoSource = new ApkSoSource(context, file, "lib-main", i2);
                            arrayList2.add(apkSoSource);
                            Log.d("SoLoader", "adding backup source from : " + apkSoSource.toString());
                            if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
                                Log.d("SoLoader", "adding backup sources from split apks");
                                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                int length = strArr.length;
                                int i3 = 0;
                                int i4 = 0;
                                while (i3 < length) {
                                    File file2 = new File(strArr[i3]);
                                    StringBuilder sb = new StringBuilder("lib-");
                                    sb.append(i4);
                                    ApkSoSource apkSoSource2 = new ApkSoSource(context, file2, sb.toString(), i2);
                                    Log.d("SoLoader", "adding backup source: " + apkSoSource2.toString());
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
                    Log.d("SoLoader", "Preparing SO source: " + soSourceArr[i5]);
                    soSourceArr[i5].prepare(makePrepareFlags);
                    length2 = i5;
                }
                sSoSources = soSourceArr;
                sSoSourcesVersion++;
                Log.d("SoLoader", "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            Log.d("SoLoader", "init exiting");
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

    private static synchronized void initSoLoader(SoFileLoader soFileLoader) {
        final String str;
        synchronized (SoLoader.class) {
            if (soFileLoader == null) {
                if (sSoFileLoader != null) {
                    return;
                }
            }
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
                return;
            }
            final Runtime runtime = Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
            final boolean z = nativeLoadRuntimeMethod != null;
            final String classLoaderLdLoadLibrary = z ? Api14Utils.getClassLoaderLdLoadLibrary() : null;
            if (classLoaderLdLoadLibrary == null) {
                str = null;
            } else {
                String[] split = classLoaderLdLoadLibrary.split(":");
                ArrayList arrayList = new ArrayList(split.length);
                for (String str2 : split) {
                    if (!str2.contains("!")) {
                        arrayList.add(str2);
                    }
                }
                str = TextUtils.join(":", arrayList);
            }
            sSoFileLoader = new SoFileLoader() {
                /* class com.facebook.soloader.SoLoader.AnonymousClass1 */

                @Override // com.facebook.soloader.SoFileLoader
                public final void loadBytes$32a67ea() {
                    throw new UnsupportedOperationException();
                }

                /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
                    if (r1 == null) goto L_0x005f;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
                    android.util.Log.e("SoLoader", "Error when loading lib: " + r1 + " lib hash: " + getLibHash(r9) + " search path is " + r10);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                    return;
                 */
                @Override // com.facebook.soloader.SoFileLoader
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void load(java.lang.String r9, int r10) {
                    /*
                    // Method dump skipped, instructions count: 183
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.load(java.lang.String, int):void");
                }

                private static String getLibHash(String str) {
                    try {
                        File file = new File(str);
                        MessageDigest instance = MessageDigest.getInstance("MD5");
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read > 0) {
                                    instance.update(bArr, 0, read);
                                } else {
                                    String format = String.format("%32x", new BigInteger(1, instance.digest()));
                                    fileInputStream.close();
                                    return format;
                                }
                            }
                        } catch (Throwable unused) {
                        }
                        throw th;
                    } catch (IOException e) {
                        return e.toString();
                    } catch (SecurityException e2) {
                        return e2.toString();
                    } catch (NoSuchAlgorithmException e3) {
                        return e3.toString();
                    }
                }
            };
        }
    }

    private static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT <= 27) {
            try {
                Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException | SecurityException e) {
                Log.w("SoLoader", "Cannot get nativeLoad method", e);
            }
        }
        return null;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    public static boolean loadLibrary(String str, int i) throws UnsatisfiedLinkError {
        Boolean loadLibraryOnNonAndroid = loadLibraryOnNonAndroid(str);
        if (loadLibraryOnNonAndroid != null) {
            return loadLibraryOnNonAndroid.booleanValue();
        }
        if (!isSystemApp || sSystemLoadLibraryWrapper == null) {
            return loadLibraryBySoName(System.mapLibraryName(str), str, null, i, null);
        }
        return true;
    }

    private static Boolean loadLibraryOnNonAndroid(String str) {
        Boolean valueOf;
        if (sSoSources != null) {
            return null;
        }
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if (!"http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    synchronized (SoLoader.class) {
                        boolean z = !sLoadedLibraries.contains(str);
                        if (z && sSystemLoadLibraryWrapper == null) {
                            System.loadLibrary(str);
                        }
                        valueOf = Boolean.valueOf(z);
                    }
                    sSoSourcesLock.readLock().unlock();
                    return valueOf;
                } else if (!isInitialized()) {
                    throw new IllegalStateException("SoLoader.init() not yet called");
                }
            }
            return null;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    static void loadLibraryBySoName(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        loadLibraryBySoNameImpl(str, null, null, i, threadPolicy);
    }

    private static boolean loadLibraryBySoName(String str, String str2, String str3, int i, StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean z2 = false;
        do {
            try {
                z2 = loadLibraryBySoNameImpl(str, str2, str3, i, null);
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
                        Log.w("SoLoader", "sApplicationSoSource updated during load: " + str + ", attempting load again.");
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

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        com.facebook.soloader.SoLoader.sSoSourcesLock.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r1 != false) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0050, code lost:
        if (com.facebook.soloader.SoLoader.sLoadedLibraries.contains(r8) == false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0052, code lost:
        if (r10 != null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0055, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0060, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0061, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0062, code lost:
        if (r1 != false) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        android.util.Log.d("SoLoader", "About to load: " + r8);
        doLoadLibraryBySoName(r8, r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007a, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        android.util.Log.d("SoLoader", "Loaded: " + r8);
        com.facebook.soloader.SoLoader.sLoadedLibraries.add(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0093, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0098, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0099, code lost:
        r9 = r8.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009d, code lost:
        if (r9 == null) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b6, code lost:
        throw new com.facebook.soloader.SoLoader.WrongAbiError(r8, r9.substring(r9.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b7, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bd, code lost:
        if ((r11 & 16) != 0) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c3, code lost:
        if (android.text.TextUtils.isEmpty(r9) != false) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00cb, code lost:
        if (com.facebook.soloader.SoLoader.sLoadedAndMergedLibraries.contains(r9) == false) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00cd, code lost:
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cf, code lost:
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00d0, code lost:
        if (r10 == null) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00d2, code lost:
        if (r11 != false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00d6, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING == false) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00d8, code lost:
        com.facebook.soloader.Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", r9, "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        android.util.Log.d("SoLoader", "About to merge: " + r9 + " / " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x010d, code lost:
        throw new java.lang.IllegalArgumentException("Unknown library: " + r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x010e, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0110, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0131, code lost:
        throw new java.lang.RuntimeException("Failed to call JNI_OnLoad from '" + r9 + "', which has been merged into '" + r8 + "'.  See comment for details.", r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0134, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING != false) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0136, code lost:
        android.os.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0139, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x013a, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x013b, code lost:
        com.facebook.soloader.SoLoader.sSoSourcesLock.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0144, code lost:
        if (r1 != false) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0146, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0147, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x014b, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x014c, code lost:
        com.facebook.soloader.SoLoader.sSoSourcesLock.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0155, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryBySoNameImpl(java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11, android.os.StrictMode.ThreadPolicy r12) {
        /*
        // Method dump skipped, instructions count: 345
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryBySoNameImpl(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0130  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void doLoadLibraryBySoName(java.lang.String r12, int r13, android.os.StrictMode.ThreadPolicy r14) throws java.lang.UnsatisfiedLinkError {
        /*
        // Method dump skipped, instructions count: 394
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.doLoadLibraryBySoName(java.lang.String, int, android.os.StrictMode$ThreadPolicy):void");
    }

    private static boolean isInitialized() {
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

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public static class Api14Utils {
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

    public static void init(Context context, int i) throws IOException {
        boolean z;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        if (context != null) {
            try {
                if ((context.getApplicationInfo().flags & 129) != 0) {
                    z = true;
                    isSystemApp = z;
                    initSoLoader(null);
                    initSoSources$230dc011(context, 2);
                    NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                }
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        }
        z = false;
        isSystemApp = z;
        initSoLoader(null);
        initSoSources$230dc011(context, 2);
        NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
        StrictMode.setThreadPolicy(allowThreadDiskWrites);
    }
}
