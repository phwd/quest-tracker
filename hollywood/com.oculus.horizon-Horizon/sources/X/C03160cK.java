package X;

import dalvik.system.BaseDexClassLoader;
import java.io.IOException;
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
/* renamed from: X.0cK  reason: invalid class name and case insensitive filesystem */
public final class C03160cK {
    @GuardedBy("sSoSourcesLock")
    public static int A00;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    public static C04540hw A01;
    @Nullable
    public static C04300hL A02;
    public static boolean A03;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    public static AbstractC03170cL[] A04;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    public static AnonymousClass0HU[] A05;
    public static final ReentrantReadWriteLock A06 = new ReentrantReadWriteLock();
    @GuardedBy("SoLoader.class")
    public static final HashSet<String> A07 = new HashSet<>();
    @GuardedBy("SoLoader.class")
    public static final Map<String, Object> A08 = new HashMap();
    public static final Set<String> A09 = Collections.newSetFromMap(new ConcurrentHashMap());
    @GuardedBy("sSoSourcesLock")
    public static volatile int A0A;

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(android.content.Context r21, int r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 456
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03160cK.A03(android.content.Context, int):void");
    }

    public static int A00() {
        ReentrantReadWriteLock reentrantReadWriteLock = A06;
        reentrantReadWriteLock.writeLock().lock();
        try {
            int i = 0;
            if ((A00 & 2) != 0) {
                i = 1;
            }
            return i;
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public static String A01() {
        ClassLoader classLoader = C03160cK.class.getClassLoader();
        if (classLoader == null || (classLoader instanceof BaseDexClassLoader)) {
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("ClassLoader ", classLoader.getClass().getName(), " should be of type BaseDexClassLoader"));
        }
    }

    public static void A02() {
        ReentrantReadWriteLock reentrantReadWriteLock = A06;
        reentrantReadWriteLock.readLock().lock();
        try {
            boolean z = false;
            if (A04 != null) {
                z = true;
            }
            if (!z) {
                throw new IllegalStateException("SoLoader.init() not yet called");
            }
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public static void A04(AbstractC03170cL r6) throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = A06;
        reentrantReadWriteLock.writeLock().lock();
        try {
            A02();
            r6.A04(A00());
            AbstractC03170cL[] r4 = A04;
            int length = r4.length;
            AbstractC03170cL[] r1 = new AbstractC03170cL[(length + 1)];
            r1[0] = r6;
            System.arraycopy(r4, 0, r1, 1, length);
            A04 = r1;
            A0A++;
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011d A[LOOP:0: B:58:0x00ba->B:75:0x011d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0127 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A05(java.lang.String r14, int r15) throws java.lang.UnsatisfiedLinkError {
        /*
        // Method dump skipped, instructions count: 408
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03160cK.A05(java.lang.String, int):boolean");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x010e, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("couldn't find DSO to load: ");
        r2.append(r26);
        r19.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0120, code lost:
        r1 = X.C03160cK.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0123, code lost:
        if (r7 >= r1.length) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0125, code lost:
        r2.append("\n\tSoSource ");
        r2.append(r7);
        r2.append(": ");
        r2.append(r1[r7].toString());
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x013e, code lost:
        r0 = X.C03160cK.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0140, code lost:
        if (r0 == null) goto L_0x0170;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r1 = r0.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x014d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0153, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0154, code lost:
        r1 = new java.io.File(r1.createPackageContext(r1.getPackageName(), 0).getApplicationInfo().nativeLibraryDir);
        r2.append("\n\tNative lib dir: ");
        r2.append(r1.getAbsolutePath());
        r2.append("\n");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0170, code lost:
        r19.readLock().unlock();
        r2.append(" result: ");
        r2.append(r5);
        r1 = r2.toString();
        android.util.Log.e("SoLoader", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x018b, code lost:
        throw new java.lang.UnsatisfiedLinkError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x018c, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x018d, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x018f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0190, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0198, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0199, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x019a, code lost:
        X.C03060c0.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x019d, code lost:
        if (r16 != false) goto L_0x019f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x019f, code lost:
        android.os.StrictMode.setThreadPolicy(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01a7, code lost:
        monitor-enter(X.C03160cK.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r11.add(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01ad, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01b1, code lost:
        r3 = new java.lang.StringBuilder();
        r3.append("couldn't find DSO to load: ");
        r3.append(r26);
        r1 = r2.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01c0, code lost:
        if (r1 == null) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01c2, code lost:
        r1 = r2.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01c6, code lost:
        r3.append(" caused by: ");
        r3.append(r1);
        r2.printStackTrace();
        r3.append(" result: ");
        r3.append(r7);
        r1 = r3.toString();
        android.util.Log.e("SoLoader", r1);
        r0 = new java.lang.UnsatisfiedLinkError(r1);
        r0.initCause(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x01e8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x01e9, code lost:
        android.util.Log.e("SoLoader", X.AnonymousClass006.A07("Could not load: ", r26, " because no SO source exists"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x01fd, code lost:
        throw new java.lang.UnsatisfiedLinkError(X.AnonymousClass006.A05("couldn't find DSO to load: ", r26));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x01fe, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01ff, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0206, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0207, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0208, code lost:
        r2 = r3.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0223, code lost:
        throw new X.C03150cJ(r3, r2.substring(r2.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0224, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x022a, code lost:
        if ((r29 & 16) == 0) goto L_0x022c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x023a, code lost:
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x023f, code lost:
        X.C03060c0.A01("MergedSoMapping.invokeJniOnload[", r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0248, code lost:
        switch(r27.hashCode()) {
            case -1924924588: goto L_0x0366;
            case -1548718714: goto L_0x0359;
            case -1347925350: goto L_0x034c;
            case -1281161254: goto L_0x033f;
            case -902468257: goto L_0x0332;
            case -897020359: goto L_0x0325;
            case -835404702: goto L_0x0318;
            case -381653348: goto L_0x030b;
            case -253106228: goto L_0x02fe;
            case -143235895: goto L_0x02f1;
            case -61423793: goto L_0x02e3;
            case 3260: goto L_0x02d5;
            case 101517: goto L_0x02c7;
            case 97224041: goto L_0x02b9;
            case 451661819: goto L_0x02ab;
            case 597752563: goto L_0x029d;
            case 599642181: goto L_0x028f;
            case 656562322: goto L_0x0281;
            case 1047472087: goto L_0x0273;
            case 1540136281: goto L_0x0265;
            case 2056114364: goto L_0x0257;
            default: goto L_0x024b;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0256, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass006.A05("Unknown library: ", r27));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x025d, code lost:
        if (r27.equals("mobileconfig-jni") != false) goto L_0x025f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x025f, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libmobileconfig_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x026b, code lost:
        if (r27.equals("xanalyticsadapter-jni") != false) goto L_0x026d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x026d, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libxanalyticsadapter_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0279, code lost:
        if (r27.equals("cryptox") != false) goto L_0x027b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x027b, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libcryptox_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0287, code lost:
        if (r27.equals("double-conversion") != false) goto L_0x0289;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0289, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libdouble_conversion_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0295, code lost:
        if (r27.equals("native-filters") != false) goto L_0x0297;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0297, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libnative_filters_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x02a3, code lost:
        if (r27.equals("fb_jpegturbo") != false) goto L_0x02a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x02a5, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfb_jpegturbo_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x02b1, code lost:
        if (r27.equals("jniexecutors") != false) goto L_0x02b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x02b3, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libjniexecutors_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x02bf, code lost:
        if (r27.equals("fbjni") != false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x02c1, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbjni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x02cd, code lost:
        if (r27.equals("fmt") != false) goto L_0x02cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x02cf, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfmt_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x02db, code lost:
        if (r27.equals("fb") != false) goto L_0x02dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x02dd, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfb_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x02e9, code lost:
        if (r27.equals("asyncexecutor") != false) goto L_0x02eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x02eb, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libasyncexecutor_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x02f7, code lost:
        if (r27.equals("fbgloginit") != false) goto L_0x02f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x02f9, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbgloginit_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0304, code lost:
        if (r27.equals("profiloextapi") != false) goto L_0x0306;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0306, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libprofiloextapi_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0311, code lost:
        if (r27.equals("fbacore-jni") != false) goto L_0x0313;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0313, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbacore_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        r19 = X.C03160cK.A06;
        r19.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x031e, code lost:
        if (r27.equals("native-imagetranscoder") != false) goto L_0x0320;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0320, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libnative_imagetranscoder_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x032b, code lost:
        if (r27.equals("sodium") != false) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x032d, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libsodium_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0338, code lost:
        if (r27.equals("sigmux") != false) goto L_0x033a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x033a, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libsigmux_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0345, code lost:
        if (r27.equals("fbexit") != false) goto L_0x0347;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0347, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbexit_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0352, code lost:
        if (r27.equals("flatbuffers") != false) goto L_0x0354;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0354, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libflatbuffers_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x035f, code lost:
        if (r27.equals("mediapipeline-filterlib") != false) goto L_0x0361;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0361, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libmediapipeline_filterlib_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x036c, code lost:
        if (r27.equals("fbsystrace") != false) goto L_0x036e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x036e, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbsystrace_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0372, code lost:
        if (r0 == 0) goto L_0x0374;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0374, code lost:
        X.C03160cK.A09.add(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0381, code lost:
        throw new java.lang.UnsatisfiedLinkError("Failed to invoke native library JNI_OnLoad");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0382, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0392, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass006.A09("Failed to call JNI_OnLoad from '", r27, "', which has been merged into '", r26, "'.  See comment for details."), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0393, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0394, code lost:
        X.C03060c0.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0397, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0398, code lost:
        X.C03060c0.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x039b, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x039c, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x03a5, code lost:
        return !r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x03a9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        if (r20 != false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x03aa, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x03b1, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        monitor-enter(X.C03160cK.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        if (r11.contains(r26) == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0056, code lost:
        if (r28 != null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
        monitor-exit(X.C03160cK.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0059, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0061, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0062, code lost:
        r20 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
        monitor-exit(X.C03160cK.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0065, code lost:
        if (r20 != false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r19.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0076, code lost:
        if (X.C03160cK.A04 == null) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0078, code lost:
        r19.readLock().unlock();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0082, code lost:
        if (r30 != null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0085, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0088, code lost:
        r15 = android.os.StrictMode.allowThreadDiskReads();
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x008e, code lost:
        X.C03060c0.A01("SoLoader.loadLibrary[", r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r19.readLock().lock();
        r5 = 0;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r1 = X.C03160cK.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a0, code lost:
        if (r2 >= r1.length) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a2, code lost:
        r5 = r1[r2].A05(r26, r29, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00aa, code lost:
        if (r5 != 3) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ac, code lost:
        r4 = X.C03160cK.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ae, code lost:
        if (r4 == null) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b0, code lost:
        r0 = r4.length;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00b5, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b7, code lost:
        if (r5 != 0) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00bc, code lost:
        if (r3 >= r0) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00be, code lost:
        r2 = r4[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c0, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r1 = r2.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00c3, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r0 = r1.get(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00c8, code lost:
        if (r0 != null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00ca, code lost:
        r0 = new java.lang.Object();
        r1.put(r26, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00d2, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00d3, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r2.A00 = r26;
        r2.A04(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00da, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00db, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00ea, code lost:
        if (r2.A05(r26, r29, r15) != 1) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00ed, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00f0, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0102, code lost:
        X.C03060c0.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0105, code lost:
        if (r16 == false) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0107, code lost:
        android.os.StrictMode.setThreadPolicy(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x010a, code lost:
        if (r5 == 0) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x010c, code lost:
        if (r5 != 3) goto L_0x01a7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0374 A[Catch:{ UnsatisfiedLinkError -> 0x0382, all -> 0x0393 }] */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x037a A[Catch:{ UnsatisfiedLinkError -> 0x0382, all -> 0x0393 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A06(java.lang.String r26, @javax.annotation.Nullable java.lang.String r27, @javax.annotation.Nullable java.lang.String r28, int r29, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r30) {
        /*
        // Method dump skipped, instructions count: 1036
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03160cK.A06(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }
}
