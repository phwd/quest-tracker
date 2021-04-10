package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.oculus.os.Version;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SoLoader {
    static final boolean SYSTRACE_LIBRARY_LOADING;
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

    static {
        boolean shouldSystrace = false;
        try {
            shouldSystrace = Build.VERSION.SDK_INT >= 18;
        } catch (NoClassDefFoundError | UnsatisfiedLinkError e) {
        }
        SYSTRACE_LIBRARY_LOADING = shouldSystrace;
    }

    public static void init(Context context, int flags) throws IOException {
        init(context, flags, null);
    }

    public static void init(Context context, int flags, @Nullable SoFileLoader soFileLoader) throws IOException {
        StrictMode.ThreadPolicy oldPolicy = StrictMode.allowThreadDiskWrites();
        try {
            isSystemApp = checkIfSystemApp(context, flags);
            initSoLoader(soFileLoader);
            initSoSources(context, flags, soFileLoader);
            if (!NativeLoader.isInitialized()) {
                NativeLoader.init(new NativeLoaderToSoLoaderDelegate());
            }
        } finally {
            StrictMode.setThreadPolicy(oldPolicy);
        }
    }

    private static void initSoSources(Context context, int flags, @Nullable SoFileLoader soFileLoader) throws IOException {
        int apkSoSourceFlags;
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources == null) {
                Log.d("SoLoader", "init start");
                sFlags = flags;
                ArrayList<SoSource> soSources = new ArrayList<>();
                String LD_LIBRARY_PATH = System.getenv("LD_LIBRARY_PATH");
                if (LD_LIBRARY_PATH == null) {
                    LD_LIBRARY_PATH = SysUtil.is64Bit() ? "/vendor/lib64:/system/lib64" : "/vendor/lib:/system/lib";
                }
                String[] split = LD_LIBRARY_PATH.split(":");
                int length = split.length;
                for (int i = 0; i < length; i++) {
                    String systemLibraryDirectory = split[i];
                    Log.d("SoLoader", "adding system library source: " + systemLibraryDirectory);
                    soSources.add(new DirectorySoSource(new File(systemLibraryDirectory), 2));
                }
                if (context != null) {
                    if ((flags & 1) != 0) {
                        sBackupSoSources = null;
                        Log.d("SoLoader", "adding exo package source: lib-main");
                        soSources.add(0, new ExoSoSource(context, "lib-main"));
                    } else {
                        if (isSystemApp) {
                            apkSoSourceFlags = 0;
                        } else {
                            apkSoSourceFlags = 1;
                            int ourSoSourceFlags = 0;
                            if (Build.VERSION.SDK_INT <= 17) {
                                ourSoSourceFlags = 0 | 1;
                            }
                            sApplicationSoSource = new ApplicationSoSource(context, ourSoSourceFlags);
                            Log.d("SoLoader", "adding application source: " + sApplicationSoSource.toString());
                            soSources.add(0, sApplicationSoSource);
                        }
                        if ((sFlags & 8) != 0) {
                            sBackupSoSources = null;
                        } else {
                            File mainApkDir = new File(context.getApplicationInfo().sourceDir);
                            ArrayList<UnpackingSoSource> backupSources = new ArrayList<>();
                            ApkSoSource mainApkSource = new ApkSoSource(context, mainApkDir, "lib-main", apkSoSourceFlags);
                            backupSources.add(mainApkSource);
                            Log.d("SoLoader", "adding backup source from : " + mainApkSource.toString());
                            if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
                                Log.d("SoLoader", "adding backup sources from split apks");
                                int splitIndex = 0;
                                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                int length2 = strArr.length;
                                for (int i2 = 0; i2 < length2; i2++) {
                                    splitIndex++;
                                    ApkSoSource splitApkSource = new ApkSoSource(context, new File(strArr[i2]), "lib-" + splitIndex, apkSoSourceFlags);
                                    Log.d("SoLoader", "adding backup source: " + splitApkSource.toString());
                                    backupSources.add(splitApkSource);
                                }
                            }
                            sBackupSoSources = (UnpackingSoSource[]) backupSources.toArray(new UnpackingSoSource[backupSources.size()]);
                            soSources.addAll(0, backupSources);
                        }
                    }
                }
                SoSource[] finalSoSources = (SoSource[]) soSources.toArray(new SoSource[soSources.size()]);
                int prepareFlags = makePrepareFlags();
                int i3 = finalSoSources.length;
                while (true) {
                    int i4 = i3 - 1;
                    if (i3 <= 0) {
                        break;
                    }
                    Log.d("SoLoader", "Preparing SO source: " + finalSoSources[i4]);
                    finalSoSources[i4].prepare(prepareFlags);
                    i3 = i4;
                }
                sSoSources = finalSoSources;
                sSoSourcesVersion++;
                Log.d("SoLoader", "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            Log.d("SoLoader", "init exiting");
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static int makePrepareFlags() {
        int prepareFlags = 0;
        sSoSourcesLock.writeLock().lock();
        try {
            if ((sFlags & 2) != 0) {
                prepareFlags = 0 | 1;
            }
            return prepareFlags;
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
            } else {
                final Runtime runtime = Runtime.getRuntime();
                final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
                final boolean hasNativeLoadMethod = nativeLoadRuntimeMethod != null;
                final String localLdLibraryPath = hasNativeLoadMethod ? Api14Utils.getClassLoaderLdLoadLibrary() : null;
                final String localLdLibraryPathNoZips = makeNonZipPath(localLdLibraryPath);
                sSoFileLoader = new SoFileLoader() {
                    /* class com.facebook.soloader.SoLoader.AnonymousClass1 */

                    @Override // com.facebook.soloader.SoFileLoader
                    public void load(String pathToSoFile, int loadFlags) {
                        Exception e;
                        String error;
                        boolean inZip = true;
                        if (hasNativeLoadMethod) {
                            if ((loadFlags & 4) != 4) {
                                inZip = false;
                            }
                            String path = inZip ? localLdLibraryPath : localLdLibraryPathNoZips;
                            try {
                                synchronized (runtime) {
                                    error = (String) nativeLoadRuntimeMethod.invoke(runtime, pathToSoFile, SoLoader.class.getClassLoader(), path);
                                    if (error != null) {
                                        throw new UnsatisfiedLinkError(error);
                                    }
                                }
                                if (error != null) {
                                    Log.e("SoLoader", "Error when loading lib: " + error + " lib hash: " + getLibHash(pathToSoFile) + " search path is " + path);
                                }
                            } catch (IllegalAccessException e2) {
                                e = e2;
                                try {
                                    throw new RuntimeException("Error: Cannot load " + pathToSoFile, e);
                                } catch (Throwable th) {
                                    if (0 != 0) {
                                        Log.e("SoLoader", "Error when loading lib: " + ((String) null) + " lib hash: " + getLibHash(pathToSoFile) + " search path is " + path);
                                    }
                                    throw th;
                                }
                            } catch (IllegalArgumentException e3) {
                                e = e3;
                                throw new RuntimeException("Error: Cannot load " + pathToSoFile, e);
                            } catch (InvocationTargetException e4) {
                                e = e4;
                                throw new RuntimeException("Error: Cannot load " + pathToSoFile, e);
                            }
                        } else {
                            System.load(pathToSoFile);
                        }
                    }

                    private String getLibHash(String libPath) {
                        try {
                            File libFile = new File(libPath);
                            MessageDigest digest = MessageDigest.getInstance("MD5");
                            InputStream libInStream = new FileInputStream(libFile);
                            try {
                                byte[] buffer = new byte[4096];
                                while (true) {
                                    int bytesRead = libInStream.read(buffer);
                                    if (bytesRead > 0) {
                                        digest.update(buffer, 0, bytesRead);
                                    } else {
                                        String digestStr = String.format("%32x", new BigInteger(1, digest.digest()));
                                        libInStream.close();
                                        return digestStr;
                                    }
                                }
                            } catch (Throwable th) {
                                th.addSuppressed(th);
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
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method method = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException | SecurityException e) {
            Log.w("SoLoader", "Cannot get nativeLoad method", e);
            return null;
        }
    }

    private static boolean checkIfSystemApp(Context context, int flags) {
        if ((flags & 32) != 0 || context == null || (context.getApplicationInfo().flags & 129) == 0) {
            return false;
        }
        return true;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable cause, String machine) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + machine);
            initCause(cause);
        }
    }

    public static boolean loadLibrary(String shortName) {
        return loadLibrary(shortName, 0);
    }

    /* JADX INFO: finally extract failed */
    public static boolean loadLibrary(String shortName, int loadFlags) throws UnsatisfiedLinkError {
        String soName;
        boolean needsLoad = true;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        if (sLoadedLibraries.contains(shortName)) {
                            needsLoad = false;
                        }
                        if (needsLoad) {
                            if (sSystemLoadLibraryWrapper != null) {
                                sSystemLoadLibraryWrapper.loadLibrary(shortName);
                            } else {
                                System.loadLibrary(shortName);
                            }
                        }
                    }
                    sSoSourcesLock.readLock().unlock();
                    return needsLoad;
                }
            }
            sSoSourcesLock.readLock().unlock();
            if (!isSystemApp || sSystemLoadLibraryWrapper == null) {
                String mergedLibName = MergedSoMapping.mapLibName(shortName);
                if (mergedLibName != null) {
                    soName = mergedLibName;
                } else {
                    soName = shortName;
                }
                return loadLibraryBySoName(System.mapLibraryName(soName), shortName, mergedLibName, loadFlags, null);
            }
            sSystemLoadLibraryWrapper.loadLibrary(shortName);
            return true;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    static void loadLibraryBySoName(String soName, int loadFlags, StrictMode.ThreadPolicy oldPolicy) {
        loadLibraryBySoNameImpl(soName, null, null, loadFlags, oldPolicy);
    }

    private static boolean loadLibraryBySoName(String soName, @Nullable String shortName, @Nullable String mergedLibName, int loadFlags, @Nullable StrictMode.ThreadPolicy oldPolicy) {
        boolean retry;
        boolean ret = false;
        do {
            retry = false;
            try {
                ret = loadLibraryBySoNameImpl(soName, shortName, mergedLibName, loadFlags, oldPolicy);
                continue;
            } catch (UnsatisfiedLinkError e) {
                int currentVersion = sSoSourcesVersion;
                sSoSourcesLock.writeLock().lock();
                try {
                    if (sApplicationSoSource != null && sApplicationSoSource.checkAndMaybeUpdate()) {
                        Log.w("SoLoader", "sApplicationSoSource updated during load: " + soName + ", attempting load again.");
                        sSoSourcesVersion++;
                        retry = true;
                    }
                    sSoSourcesLock.writeLock().unlock();
                    if (sSoSourcesVersion == currentVersion) {
                        throw e;
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (Throwable th) {
                    sSoSourcesLock.writeLock().unlock();
                    throw th;
                }
            }
        } while (retry);
        return ret;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x016d, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0170, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0171, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        com.facebook.soloader.SoLoader.sSoSourcesLock.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        if (r3 != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0049, code lost:
        if (com.facebook.soloader.SoLoader.sLoadedLibraries.contains(r11) == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004b, code lost:
        if (r13 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004e, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004f, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0065, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0066, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0067, code lost:
        if (r3 != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        android.util.Log.d("SoLoader", "About to load: " + r11);
        doLoadLibraryBySoName(r11, r14, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0086, code lost:
        monitor-enter(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        android.util.Log.d("SoLoader", "Loaded: " + r11);
        com.facebook.soloader.SoLoader.sLoadedLibraries.add(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a4, code lost:
        monitor-exit(com.facebook.soloader.SoLoader.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a7, code lost:
        if ((r14 & 16) != 0) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ad, code lost:
        if (android.text.TextUtils.isEmpty(r12) != false) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b5, code lost:
        if (com.facebook.soloader.SoLoader.sLoadedAndMergedLibraries.contains(r12) == false) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b7, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b8, code lost:
        if (r13 == null) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ba, code lost:
        if (r2 != false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00be, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING == false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c0, code lost:
        com.facebook.soloader.Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", r12, "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        android.util.Log.d("SoLoader", "About to merge: " + r12 + " / " + r11);
        com.facebook.soloader.MergedSoMapping.invokeJniOnload(r12);
        com.facebook.soloader.SoLoader.sLoadedAndMergedLibraries.add(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f3, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00f5, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00f8, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00f9, code lost:
        com.facebook.soloader.SoLoader.sSoSourcesLock.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0102, code lost:
        if (r3 != false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0104, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x010d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x010e, code lost:
        com.facebook.soloader.SoLoader.sSoSourcesLock.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0117, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0118, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0119, code lost:
        r6 = r1.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x011d, code lost:
        if (r6 == null) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0136, code lost:
        throw new com.facebook.soloader.SoLoader.WrongAbiError(r1, r6.substring(r6.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0137, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x013b, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x013e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0167, code lost:
        throw new java.lang.RuntimeException("Failed to call JNI_OnLoad from '" + r12 + "', which has been merged into '" + r11 + "'.  See comment for details.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0168, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x016b, code lost:
        if (com.facebook.soloader.SoLoader.SYSTRACE_LIBRARY_LOADING != false) goto L_0x016d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryBySoNameImpl(java.lang.String r11, @javax.annotation.Nullable java.lang.String r12, @javax.annotation.Nullable java.lang.String r13, int r14, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r15) {
        /*
        // Method dump skipped, instructions count: 372
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryBySoNameImpl(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    /* JADX INFO: finally extract failed */
    private static void doLoadLibraryBySoName(String soName, int loadFlags, @Nullable StrictMode.ThreadPolicy oldPolicy) throws UnsatisfiedLinkError {
        int result = 0;
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources == null) {
                Log.e("SoLoader", "Could not load: " + soName + " because no SO source exists");
                throw new UnsatisfiedLinkError("couldn't find DSO to load: " + soName);
            }
            sSoSourcesLock.readLock().unlock();
            boolean restoreOldPolicy = false;
            if (oldPolicy == null) {
                oldPolicy = StrictMode.allowThreadDiskReads();
                restoreOldPolicy = true;
            }
            if (SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", soName, "]");
            }
            Throwable error = null;
            try {
                sSoSourcesLock.readLock().lock();
                int i = 0;
                while (true) {
                    if (result != 0) {
                        break;
                    }
                    try {
                        if (i >= sSoSources.length) {
                            break;
                        }
                        result = sSoSources[i].loadLibrary(soName, loadFlags, oldPolicy);
                        if (result == 3 && sBackupSoSources != null) {
                            Log.d("SoLoader", "Trying backup SoSource for " + soName);
                            UnpackingSoSource[] unpackingSoSourceArr = sBackupSoSources;
                            int length = unpackingSoSourceArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length) {
                                    break;
                                }
                                UnpackingSoSource backupSoSource = unpackingSoSourceArr[i2];
                                backupSoSource.prepare(soName);
                                int resultFromBackup = backupSoSource.loadLibrary(soName, loadFlags, oldPolicy);
                                if (resultFromBackup == 1) {
                                    result = resultFromBackup;
                                    break;
                                }
                                i2++;
                            }
                        } else {
                            i++;
                        }
                    } catch (Throwable th) {
                        sSoSourcesLock.readLock().unlock();
                        throw th;
                    }
                }
                sSoSourcesLock.readLock().unlock();
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.endSection();
                }
                if (restoreOldPolicy) {
                    StrictMode.setThreadPolicy(oldPolicy);
                }
                if (result == 0 || result == 3) {
                    StringBuilder sb = new StringBuilder().append("couldn't find DSO to load: ").append(soName);
                    if (0 != 0) {
                        String cause = error.getMessage();
                        if (cause == null) {
                            cause = error.toString();
                        }
                        sb.append(" caused by: ").append(cause);
                        error.printStackTrace();
                    } else {
                        sSoSourcesLock.readLock().lock();
                        for (int i3 = 0; i3 < sSoSources.length; i3++) {
                            sb.append("\n\tSoSource ").append(i3).append(": ").append(sSoSources[i3].toString());
                        }
                        if (sApplicationSoSource != null) {
                            sb.append("\n\tNative lib dir: ").append(ApplicationSoSource.getNativeLibDirFromContext(sApplicationSoSource.getUpdatedContext()).getAbsolutePath()).append("\n");
                        }
                        sSoSourcesLock.readLock().unlock();
                    }
                    sb.append(" result: ").append(result);
                    String message = sb.toString();
                    Log.e("SoLoader", message);
                    UnsatisfiedLinkError err = new UnsatisfiedLinkError(message);
                    if (0 != 0) {
                        err.initCause(null);
                    }
                    throw err;
                }
            } catch (Throwable th2) {
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.endSection();
                }
                if (restoreOldPolicy) {
                    StrictMode.setThreadPolicy(oldPolicy);
                }
                if (0 == 0 || 0 == 3) {
                    StringBuilder sb2 = new StringBuilder().append("couldn't find DSO to load: ").append(soName);
                    if (0 != 0) {
                        String cause2 = error.getMessage();
                        if (cause2 == null) {
                            cause2 = error.toString();
                        }
                        sb2.append(" caused by: ").append(cause2);
                        error.printStackTrace();
                    } else {
                        sSoSourcesLock.readLock().lock();
                        for (int i4 = 0; i4 < sSoSources.length; i4++) {
                            sb2.append("\n\tSoSource ").append(i4).append(": ").append(sSoSources[i4].toString());
                        }
                        if (sApplicationSoSource != null) {
                            sb2.append("\n\tNative lib dir: ").append(ApplicationSoSource.getNativeLibDirFromContext(sApplicationSoSource.getUpdatedContext()).getAbsolutePath()).append("\n");
                        }
                        sSoSourcesLock.readLock().unlock();
                    }
                    sb2.append(" result: ").append(0);
                    String message2 = sb2.toString();
                    Log.e("SoLoader", message2);
                    UnsatisfiedLinkError err2 = new UnsatisfiedLinkError(message2);
                    if (0 != 0) {
                        err2.initCause(null);
                    }
                    throw err2;
                }
                throw th2;
            }
        } catch (Throwable th3) {
            sSoSourcesLock.readLock().unlock();
            throw th3;
        }
    }

    @Nullable
    public static String makeNonZipPath(String localLdLibraryPath) {
        if (localLdLibraryPath == null) {
            return null;
        }
        String[] paths = localLdLibraryPath.split(":");
        ArrayList<String> pathsWithoutZip = new ArrayList<>(paths.length);
        for (String path : paths) {
            if (!path.contains("!")) {
                pathsWithoutZip.add(path);
            }
        }
        return TextUtils.join(":", pathsWithoutZip);
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("SoLoader.init() not yet called");
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

    /* access modifiers changed from: private */
    @TargetApi(Version.VERSION_14)
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
}
