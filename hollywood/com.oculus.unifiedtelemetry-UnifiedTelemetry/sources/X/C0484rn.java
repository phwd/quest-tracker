package X;

import com.oculus.unifiedtelemetry.UnifiedTelemetryApplication;
import dalvik.system.BaseDexClassLoader;
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
/* renamed from: X.rn  reason: case insensitive filesystem */
public final class C0484rn {
    @GuardedBy("sSoSourcesLock")
    public static int A00;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    public static C0492sB A01;
    @Nullable
    public static C0491sA A02;
    @Nullable
    public static UnifiedTelemetryApplication.AnonymousClass1 A03;
    public static boolean A04;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    public static AbstractC0507sb[] A05;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    public static AbstractC0486rs[] A06;
    public static final ReentrantReadWriteLock A07 = new ReentrantReadWriteLock();
    @GuardedBy("SoLoader.class")
    public static final HashSet<String> A08 = new HashSet<>();
    @GuardedBy("SoLoader.class")
    public static final Map<String, Object> A09 = new HashMap();
    public static final Set<String> A0A = Collections.newSetFromMap(new ConcurrentHashMap());
    @GuardedBy("sSoSourcesLock")
    public static volatile int A0B;

    public static String A00() {
        ClassLoader classLoader = C0484rn.class.getClassLoader();
        if (classLoader == null || (classLoader instanceof BaseDexClassLoader)) {
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass06.A05("ClassLoader ", classLoader.getClass().getName(), " should be of type BaseDexClassLoader"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00db A[LOOP:0: B:35:0x0078->B:52:0x00db, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A02(java.lang.String r13, int r14) throws java.lang.UnsatisfiedLinkError {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0484rn.A02(java.lang.String, int):boolean");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0145, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0146, code lost:
        r1 = new java.io.File(r4.createPackageContext(r4.getPackageName(), 0).getApplicationInfo().nativeLibraryDir);
        r2.append("\n\tNative lib dir: ");
        r2.append(r1.getAbsolutePath());
        r2.append("\n");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0162, code lost:
        r18.readLock().unlock();
        r2.append(" result: ");
        r2.append(r12);
        r1 = r2.toString();
        android.util.Log.e("SoLoader", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x017b, code lost:
        throw new java.lang.UnsatisfiedLinkError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x017c, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x017d, code lost:
        r6 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x017f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0180, code lost:
        r18.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0188, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0189, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x018a, code lost:
        X.C0509se.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x018d, code lost:
        if (r16 != false) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x018f, code lost:
        android.os.StrictMode.setThreadPolicy(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0197, code lost:
        monitor-enter(X.C0484rn.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        r11.add(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01a6, code lost:
        X.C0484rn.A0A.contains(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01ad, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01ae, code lost:
        r18.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01b5, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01b6, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01b9, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("couldn't find DSO to load: ");
        r2.append(r23);
        r1 = r4.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01c8, code lost:
        if (r1 == null) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x01ca, code lost:
        r1 = r4.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01ce, code lost:
        r2.append(" caused by: ");
        r2.append(r1);
        r4.printStackTrace();
        r2.append(" result: ");
        r2.append(r6);
        r1 = r2.toString();
        android.util.Log.e("SoLoader", r1);
        r0 = new java.lang.UnsatisfiedLinkError(r1);
        r0.initCause(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01ee, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01ef, code lost:
        android.util.Log.e("SoLoader", X.AnonymousClass06.A05("Could not load: ", r23, " because no SO source exists"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0203, code lost:
        throw new java.lang.UnsatisfiedLinkError(X.AnonymousClass06.A04("couldn't find DSO to load: ", r23));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0204, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0205, code lost:
        r18.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x020c, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x020d, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x020e, code lost:
        r2 = r4.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0229, code lost:
        throw new X.C0499sR(r4, r2.substring(r2.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x022a, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x022c, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0230, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0231, code lost:
        r18.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0238, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r18 = X.C0484rn.A07;
        r18.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        monitor-enter(X.C0484rn.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r0 = r11.contains(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        monitor-exit(X.C0484rn.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        if (r0 == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0056, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r18.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0066, code lost:
        if (X.C0484rn.A05 == null) goto L_0x01ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0068, code lost:
        r18.readLock().unlock();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0072, code lost:
        if (r10 != null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0075, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
        r10 = android.os.StrictMode.allowThreadDiskReads();
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007e, code lost:
        X.C0509se.A01("SoLoader.loadLibrary[", r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r18.readLock().lock();
        r12 = 0;
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r4 = X.C0484rn.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0092, code lost:
        if (r13 >= r4.length) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0094, code lost:
        r12 = r4[r13].A05(r23, r25, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
        if (r12 != 3) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009e, code lost:
        r13 = X.C0484rn.A06;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a0, code lost:
        if (r13 == null) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a2, code lost:
        r0 = r13.length;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a7, code lost:
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a9, code lost:
        if (r12 != 0) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ae, code lost:
        if (r14 >= r0) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b0, code lost:
        r4 = r13[r14];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b2, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r15 = r4.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00b5, code lost:
        monitor-enter(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r0 = r15.get(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ba, code lost:
        if (r0 != null) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00bc, code lost:
        r0 = new java.lang.Object();
        r15.put(r23, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c4, code lost:
        monitor-exit(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c5, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r4.A00 = r23;
        r4.A07(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00cc, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00cd, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00dc, code lost:
        if (r4.A05(r23, r25, r10) != 1) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00df, code lost:
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00e2, code lost:
        r12 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00f4, code lost:
        X.C0509se.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00f7, code lost:
        if (r16 == false) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00f9, code lost:
        android.os.StrictMode.setThreadPolicy(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00fc, code lost:
        if (r12 == 0) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x00fe, code lost:
        if (r12 != 3) goto L_0x0197;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0100, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("couldn't find DSO to load: ");
        r2.append(r23);
        r18.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0112, code lost:
        r4 = X.C0484rn.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0115, code lost:
        if (r6 >= r4.length) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0117, code lost:
        r2.append("\n\tSoSource ");
        r2.append(r6);
        r2.append(": ");
        r2.append(r4[r6].toString());
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0130, code lost:
        r0 = X.C0484rn.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0132, code lost:
        if (r0 == null) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
        r4 = r0.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x013f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A03(java.lang.String r23, @javax.annotation.Nullable java.lang.String r24, @javax.annotation.Nullable int r25, android.os.StrictMode.ThreadPolicy r26) {
        /*
        // Method dump skipped, instructions count: 572
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0484rn.A03(java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b0, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(android.content.Context r21) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0484rn.A01(android.content.Context):void");
    }
}
