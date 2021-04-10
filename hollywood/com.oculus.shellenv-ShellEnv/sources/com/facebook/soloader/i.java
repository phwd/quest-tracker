package com.facebook.soloader;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
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

public class i {
    static final boolean a;
    static h b;
    private static final ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    private static k[] d = null;
    private static volatile int e = 0;
    private static m[] f;
    private static b g;
    private static final HashSet h = new HashSet();
    private static final Map i = new HashMap();
    private static final Set j = Collections.newSetFromMap(new ConcurrentHashMap());
    private static l k = null;
    private static int l;
    private static boolean m;

    /* access modifiers changed from: package-private */
    public static class a {
        public static String a() {
            ClassLoader classLoader = i.class.getClassLoader();
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

    public static final class b extends UnsatisfiedLinkError {
        b(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.a()) + " error: " + str);
            initCause(th);
        }
    }

    static {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                z = true;
            }
        } catch (NoClassDefFoundError | UnsatisfiedLinkError unused) {
        }
        a = z;
    }

    private static int a() {
        c.writeLock().lock();
        try {
            return (l & 2) != 0 ? 1 : 0;
        } finally {
            c.writeLock().unlock();
        }
    }

    public static void a(Context context, int i2) {
        boolean z;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        if (context != null) {
            try {
                if ((context.getApplicationInfo().flags & 129) != 0) {
                    z = true;
                    m = z;
                    a(null);
                    b(context, 2);
                    com.facebook.soloader.a.a.a(new g());
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                }
            } catch (Throwable th) {
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                throw th;
            }
        }
        z = false;
        m = z;
        a(null);
        b(context, 2);
        com.facebook.soloader.a.a.a(new g());
        StrictMode.setThreadPolicy(allowThreadDiskWrites);
    }

    private static synchronized void a(h hVar) {
        String str;
        synchronized (i.class) {
            if (hVar != null) {
                b = hVar;
                return;
            }
            Runtime runtime = Runtime.getRuntime();
            Method b2 = b();
            boolean z = b2 != null;
            String a2 = z ? a.a() : null;
            if (a2 == null) {
                str = null;
            } else {
                String[] split = a2.split(":");
                ArrayList arrayList = new ArrayList(split.length);
                for (String str2 : split) {
                    if (!str2.contains("!")) {
                        arrayList.add(str2);
                    }
                }
                str = TextUtils.join(":", arrayList);
            }
            b = new j(z, a2, str, runtime, b2);
        }
    }

    static void a(String str, int i2, StrictMode.ThreadPolicy threadPolicy) {
        b(str, null, null, i2, threadPolicy);
    }

    /* JADX INFO: finally extract failed */
    public static boolean a(String str, int i2) {
        boolean z;
        c.readLock().lock();
        try {
            if (d == null) {
                if (!"http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    synchronized (i.class) {
                        z = !h.contains(str);
                        if (z) {
                            System.loadLibrary(str);
                        }
                    }
                    c.readLock().unlock();
                    return z;
                } else if (!c()) {
                    throw new IllegalStateException("SoLoader.init() not yet called");
                }
            }
            c.readLock().unlock();
            return a(System.mapLibraryName(str), str, null, i2, null);
        } catch (Throwable th) {
            c.readLock().unlock();
            throw th;
        }
    }

    private static boolean a(String str, String str2, String str3, int i2, StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean z2 = false;
        do {
            try {
                z2 = b(str, str2, str3, i2, null);
                z = false;
                continue;
            } catch (UnsatisfiedLinkError e2) {
                int i3 = e;
                c.writeLock().lock();
                try {
                    z = true;
                    if (g == null || !g.a()) {
                        z = false;
                    } else {
                        Log.w("SoLoader", "sApplicationSoSource updated during load: " + str + ", attempting load again.");
                        e = e + 1;
                    }
                    c.writeLock().unlock();
                    if (e == i3) {
                        throw e2;
                    }
                } catch (IOException e3) {
                    throw new RuntimeException(e3);
                } catch (Throwable th) {
                    c.writeLock().unlock();
                    throw th;
                }
            }
        } while (z);
        return z2;
    }

    private static Method b() {
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT <= 27) {
            try {
                Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException | SecurityException e2) {
                Log.w("SoLoader", "Cannot get nativeLoad method", e2);
            }
        }
        return null;
    }

    private static void b(Context context, int i2) {
        int i3;
        c.writeLock().lock();
        try {
            if (d == null) {
                l = i2;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = SysUtil.b() ? "/vendor/lib64:/system/lib64" : "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                for (String str2 : split) {
                    new StringBuilder("adding system library source: ").append(str2);
                    arrayList.add(new c(new File(str2), 2));
                }
                if (context != null) {
                    if ((i2 & 1) != 0) {
                        f = null;
                        arrayList.add(0, new d(context, "lib-main"));
                    } else {
                        if (m) {
                            i3 = 0;
                        } else {
                            g = new b(context, Build.VERSION.SDK_INT <= 17 ? 1 : 0);
                            new StringBuilder("adding application source: ").append(g.toString());
                            arrayList.add(0, g);
                            i3 = 1;
                        }
                        if ((l & 8) != 0) {
                            f = null;
                        } else {
                            File file = new File(context.getApplicationInfo().sourceDir);
                            ArrayList arrayList2 = new ArrayList();
                            a aVar = new a(context, file, "lib-main", i3);
                            arrayList2.add(aVar);
                            new StringBuilder("adding backup source from : ").append(aVar.toString());
                            if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
                                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                int length = strArr.length;
                                int i4 = 0;
                                int i5 = 0;
                                while (i4 < length) {
                                    a aVar2 = new a(context, new File(strArr[i4]), "lib-" + i5, i3);
                                    new StringBuilder("adding backup source: ").append(aVar2.toString());
                                    arrayList2.add(aVar2);
                                    i4++;
                                    i5++;
                                }
                            }
                            f = (m[]) arrayList2.toArray(new m[arrayList2.size()]);
                            arrayList.addAll(0, arrayList2);
                        }
                    }
                }
                k[] kVarArr = (k[]) arrayList.toArray(new k[arrayList.size()]);
                int a2 = a();
                int length2 = kVarArr.length;
                while (true) {
                    int i6 = length2 - 1;
                    if (length2 <= 0) {
                        break;
                    }
                    new StringBuilder("Preparing SO source: ").append(kVarArr[i6]);
                    kVarArr[i6].a(a2);
                    length2 = i6;
                }
                d = kVarArr;
                e++;
                StringBuilder sb = new StringBuilder("init finish: ");
                sb.append(d.length);
                sb.append(" SO sources prepared");
            }
        } finally {
            c.writeLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(java.lang.String r12, int r13, android.os.StrictMode.ThreadPolicy r14) {
        /*
        // Method dump skipped, instructions count: 387
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.i.b(java.lang.String, int, android.os.StrictMode$ThreadPolicy):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        com.facebook.soloader.i.c.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r1 != false) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        monitor-enter(com.facebook.soloader.i.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0050, code lost:
        if (com.facebook.soloader.i.h.contains(r7) == false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0052, code lost:
        if (r9 != null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        monitor-exit(com.facebook.soloader.i.class);
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
        monitor-exit(com.facebook.soloader.i.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0062, code lost:
        if (r1 != false) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        new java.lang.StringBuilder("About to load: ").append(r7);
        b(r7, r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0071, code lost:
        monitor-enter(com.facebook.soloader.i.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        new java.lang.StringBuilder("Loaded: ").append(r7);
        com.facebook.soloader.i.h.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0081, code lost:
        monitor-exit(com.facebook.soloader.i.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0086, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0087, code lost:
        r8 = r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008b, code lost:
        if (r8 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a4, code lost:
        throw new com.facebook.soloader.i.b(r7, r8.substring(r8.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a5, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ab, code lost:
        if ((r10 & 16) != 0) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b1, code lost:
        if (android.text.TextUtils.isEmpty(r8) != false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b9, code lost:
        if (com.facebook.soloader.i.j.contains(r8) == false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00bb, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00bd, code lost:
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00be, code lost:
        if (r9 == null) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c0, code lost:
        if (r10 != false) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00c4, code lost:
        if (com.facebook.soloader.i.a == false) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c6, code lost:
        com.facebook.soloader.g.a("MergedSoMapping.invokeJniOnload[", r8, "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r9 = new java.lang.StringBuilder("About to merge: ");
        r9.append(r8);
        r9.append(" / ");
        r9.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f2, code lost:
        throw new java.lang.IllegalArgumentException("Unknown library: " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00f3, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00f5, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0116, code lost:
        throw new java.lang.RuntimeException("Failed to call JNI_OnLoad from '" + r8 + "', which has been merged into '" + r7 + "'.  See comment for details.", r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0119, code lost:
        if (com.facebook.soloader.i.a != false) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x011b, code lost:
        android.os.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x011e, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x011f, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0120, code lost:
        com.facebook.soloader.i.c.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0129, code lost:
        if (r1 != false) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x012b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x012c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0130, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0131, code lost:
        com.facebook.soloader.i.c.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x013a, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10, android.os.StrictMode.ThreadPolicy r11) {
        /*
        // Method dump skipped, instructions count: 318
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.i.b(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    private static boolean c() {
        c.readLock().lock();
        try {
            return d != null;
        } finally {
            c.readLock().unlock();
        }
    }
}
