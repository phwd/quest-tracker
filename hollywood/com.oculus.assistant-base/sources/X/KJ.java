package X;

import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class KJ {
    public static int A00;
    public static C0964pg A01;
    public static C0973pp A02;
    public static boolean A03;
    public static KK[] A04;
    public static VS[] A05;
    public static final ReentrantReadWriteLock A06 = new ReentrantReadWriteLock();
    public static final HashSet A07 = new HashSet();
    public static final Map A08 = new HashMap();
    public static final Set A09 = Collections.newSetFromMap(new ConcurrentHashMap());
    public static volatile int A0A;

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(android.content.Context r22, int r23) {
        /*
        // Method dump skipped, instructions count: 534
        */
        throw new UnsupportedOperationException("Method not decompiled: X.KJ.A03(android.content.Context, int):void");
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
        ClassLoader classLoader = KJ.class.getClassLoader();
        if (classLoader == null || (classLoader instanceof BaseDexClassLoader)) {
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e);
            }
        } else {
            throw new IllegalStateException(AnonymousClass08.A05("ClassLoader ", classLoader.getClass().getName(), " should be of type BaseDexClassLoader"));
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

    public static void A04(KK kk) {
        ReentrantReadWriteLock reentrantReadWriteLock = A06;
        reentrantReadWriteLock.writeLock().lock();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Prepending to SO sources: ");
            sb.append(kk);
            Log.d("SoLoader", sb.toString());
            A02();
            kk.A06(A00());
            KK[] kkArr = A04;
            int length = kkArr.length;
            KK[] kkArr2 = new KK[(length + 1)];
            kkArr2[0] = kk;
            System.arraycopy(kkArr, 0, kkArr2, 1, length);
            A04 = kkArr2;
            A0A++;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Prepended to SO sources: ");
            sb2.append(kk);
            Log.d("SoLoader", sb2.toString());
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01b7 A[LOOP:0: B:96:0x013b->B:113:0x01b7, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x01c1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0136  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A05(java.lang.String r14, int r15) {
        /*
        // Method dump skipped, instructions count: 682
        */
        throw new UnsupportedOperationException("Method not decompiled: X.KJ.A05(java.lang.String, int):boolean");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0122, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("couldn't find DSO to load: ");
        r2.append(r26);
        r19.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0134, code lost:
        r1 = X.KJ.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0137, code lost:
        if (r7 >= r1.length) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0139, code lost:
        r2.append("\n\tSoSource ");
        r2.append(r7);
        r2.append(": ");
        r2.append(r1[r7].toString());
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0152, code lost:
        r0 = X.KJ.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0154, code lost:
        if (r0 == null) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r1 = r0.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0161, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0167, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0168, code lost:
        r1 = new java.io.File(r1.createPackageContext(r1.getPackageName(), 0).getApplicationInfo().nativeLibraryDir);
        r2.append("\n\tNative lib dir: ");
        r2.append(r1.getAbsolutePath());
        r2.append("\n");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0184, code lost:
        r19.readLock().unlock();
        r2.append(" result: ");
        r2.append(r5);
        r1 = r2.toString();
        android.util.Log.e("SoLoader", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x019f, code lost:
        throw new java.lang.UnsatisfiedLinkError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01a0, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01a1, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01a3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01a4, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01ac, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01ad, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01ae, code lost:
        X.KD.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01b1, code lost:
        if (r16 != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01b3, code lost:
        android.os.StrictMode.setThreadPolicy(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01bb, code lost:
        monitor-enter(X.KJ.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        android.util.Log.d("SoLoader", X.AnonymousClass08.A04("Loaded: ", r26));
        r11.add(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01cd, code lost:
        r3 = new java.lang.StringBuilder();
        r3.append("couldn't find DSO to load: ");
        r3.append(r26);
        r1 = r2.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01dc, code lost:
        if (r1 == null) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01de, code lost:
        r1 = r2.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x01e2, code lost:
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
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0204, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0205, code lost:
        android.util.Log.e("SoLoader", X.AnonymousClass08.A05("Could not load: ", r26, " because no SO source exists"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0219, code lost:
        throw new java.lang.UnsatisfiedLinkError(X.AnonymousClass08.A04("couldn't find DSO to load: ", r26));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x021a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x021b, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0222, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0223, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0224, code lost:
        r2 = r3.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0228, code lost:
        if (r2 == null) goto L_0x0240;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x023f, code lost:
        throw new X.KI(r3, r2.substring(r2.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0240, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0246, code lost:
        if ((r29 & 16) == 0) goto L_0x0248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0256, code lost:
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x025b, code lost:
        X.KD.A01("MergedSoMapping.invokeJniOnload[", r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:?, code lost:
        android.util.Log.d("SoLoader", X.AnonymousClass08.A06("About to merge: ", r27, " / ", r26));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0271, code lost:
        switch(r27.hashCode()) {
            case -2077915623: goto L_0x0533;
            case -1940491414: goto L_0x0526;
            case -1924924588: goto L_0x0519;
            case -1776425186: goto L_0x050c;
            case -1670517886: goto L_0x04ff;
            case -1458421135: goto L_0x04f2;
            case -1347925350: goto L_0x04e5;
            case -1333485256: goto L_0x04d8;
            case -1306635078: goto L_0x04cb;
            case -1281161254: goto L_0x04be;
            case -1115955040: goto L_0x04b0;
            case -995487190: goto L_0x04a2;
            case -902468257: goto L_0x0494;
            case -897020359: goto L_0x0486;
            case -819479231: goto L_0x0478;
            case -579981385: goto L_0x046a;
            case -381653348: goto L_0x045c;
            case -281578811: goto L_0x044e;
            case -281578780: goto L_0x0440;
            case -281577850: goto L_0x0432;
            case -253106228: goto L_0x0424;
            case -178664270: goto L_0x0416;
            case -143235895: goto L_0x0408;
            case -61423793: goto L_0x03fa;
            case 3260: goto L_0x03ec;
            case 3143491: goto L_0x03de;
            case 3482174: goto L_0x03d0;
            case 3539948: goto L_0x03c2;
            case 97224041: goto L_0x03b4;
            case 102970551: goto L_0x03a6;
            case 221123238: goto L_0x0398;
            case 288953734: goto L_0x038a;
            case 396371224: goto L_0x037c;
            case 412051925: goto L_0x036e;
            case 451661819: goto L_0x0360;
            case 611840033: goto L_0x0352;
            case 656562322: goto L_0x0344;
            case 785965449: goto L_0x0336;
            case 827223410: goto L_0x0328;
            case 853620883: goto L_0x031a;
            case 1047472087: goto L_0x030c;
            case 1135004579: goto L_0x02fe;
            case 1339119906: goto L_0x02f0;
            case 1428957523: goto L_0x02e2;
            case 1540136281: goto L_0x02d4;
            case 1697392675: goto L_0x02c6;
            case 1766595053: goto L_0x02b8;
            case 1923953501: goto L_0x02aa;
            case 1932431899: goto L_0x029c;
            case 2055829129: goto L_0x028e;
            case 2056114364: goto L_0x0280;
            default: goto L_0x0274;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x027f, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A04("Unknown library: ", r27));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0286, code lost:
        if (r27.equals("mobileconfig-jni") != false) goto L_0x0288;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0288, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libmobileconfig_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0294, code lost:
        if (r27.equals("tigonapi") != false) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0296, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libtigonapi_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x02a2, code lost:
        if (r27.equals("papaya-fb-transport") != false) goto L_0x02a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02a4, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libpapaya_fb_transport_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x02b0, code lost:
        if (r27.equals("xplat_caffe2_android_pytorch_jni_common") != false) goto L_0x02b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x02b2, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libxplat_caffe2_android_pytorch_jni_common_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x02be, code lost:
        if (r27.equals("liger-native") != false) goto L_0x02c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x02c0, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libliger_native_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x02cc, code lost:
        if (r27.equals("papaya-assistant-smart-keyboard-executor") != false) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x02ce, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libpapaya_assistant_smart_keyboard_executor_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x02da, code lost:
        if (r27.equals("xanalyticsadapter-jni") != false) goto L_0x02dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x02dc, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libxanalyticsadapter_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x02e8, code lost:
        if (r27.equals("proxygen-http") != false) goto L_0x02ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x02ea, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libproxygen_http_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x02f6, code lost:
        if (r27.equals("models-core") != false) goto L_0x02f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x02f8, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libmodels_core_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0304, code lost:
        if (r27.equals("android_aware_dlopen") != false) goto L_0x0306;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0306, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libandroid_aware_dlopen_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0312, code lost:
        if (r27.equals("cryptox") != false) goto L_0x0314;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0314, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libcryptox_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0320, code lost:
        if (r27.equals("classid") != false) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0322, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libclassid_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x032e, code lost:
        if (r27.equals("fbandroid_java_com_facebook_assistant_oacr_jni_logging_logging") != false) goto L_0x0330;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0330, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbandroid_java_com_facebook_assistant_oacr_jni_logging_logging_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        r19 = X.KJ.A06;
        r19.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x033c, code lost:
        if (r27.equals("proxygen_lib_utils_crypt") != false) goto L_0x033e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x033e, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libproxygen_lib_utils_crypt_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x034a, code lost:
        if (r27.equals("double-conversion") != false) goto L_0x034c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x034c, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libdouble_conversion_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0358, code lost:
        if (r27.equals("speechopus") != false) goto L_0x035a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x035a, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libspeechopus_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0366, code lost:
        if (r27.equals("jniexecutors") != false) goto L_0x0368;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0368, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libjniexecutors_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0374, code lost:
        if (r27.equals("appnetsessionid") != false) goto L_0x0376;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0376, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libappnetsessionid_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0382, code lost:
        if (r27.equals("fb_sqlite_omnistore") != false) goto L_0x0384;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0384, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfb_sqlite_omnistore_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0390, code lost:
        if (r27.equals("distract") != false) goto L_0x0392;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0392, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libdistract_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x039e, code lost:
        if (r27.equals("force_dlopen") != false) goto L_0x03a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x03a0, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libforce_dlopen_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x03ac, code lost:
        if (r27.equals("liger") != false) goto L_0x03ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x03ae, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libliger_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x03ba, code lost:
        if (r27.equals("fbjni") != false) goto L_0x03bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x03bc, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbjni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x03c8, code lost:
        if (r27.equals("sslx") != false) goto L_0x03ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x03ca, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libsslx_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x03d6, code lost:
        if (r27.equals("quic") != false) goto L_0x03d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x03d8, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libquic_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x03e4, code lost:
        if (r27.equals("fizz") != false) goto L_0x03e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x03e6, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfizz_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x03f2, code lost:
        if (r27.equals("fb") != false) goto L_0x03f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        if (r20 != false) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x03f4, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfb_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0400, code lost:
        if (r27.equals("asyncexecutor") != false) goto L_0x0402;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0402, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libasyncexecutor_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x040e, code lost:
        if (r27.equals("fbgloginit") != false) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0410, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbgloginit_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x041c, code lost:
        if (r27.equals("oacr_pass_through_trim_jni") != false) goto L_0x041e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x041e, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.liboacr_pass_through_trim_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x042a, code lost:
        if (r27.equals("profiloextapi") != false) goto L_0x042c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x042c, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libprofiloextapi_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x0438, code lost:
        if (r27.equals("classid900") != false) goto L_0x043a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x043a, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libclassid900_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0446, code lost:
        if (r27.equals("classid810") != false) goto L_0x0448;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0448, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libclassid810_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        monitor-enter(X.KJ.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0454, code lost:
        if (r27.equals("classid800") != false) goto L_0x0456;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0456, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libclassid800_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x0462, code lost:
        if (r27.equals("fbacore-jni") != false) goto L_0x0464;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x0464, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbacore_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0470, code lost:
        if (r27.equals("nightwatch") != false) goto L_0x0472;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x0472, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libnightwatch_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x047e, code lost:
        if (r27.equals("proxygen_lib_utils_compression") != false) goto L_0x0480;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x0480, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libproxygen_lib_utils_compression_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x048c, code lost:
        if (r27.equals("sodium") != false) goto L_0x048e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x048e, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libsodium_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x049a, code lost:
        if (r27.equals("sigmux") != false) goto L_0x049c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x049c, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libsigmux_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x04a8, code lost:
        if (r27.equals("papaya") != false) goto L_0x04aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x04aa, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libpapaya_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x04b6, code lost:
        if (r27.equals("appstatelogger") != false) goto L_0x04b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x04b8, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libappstatelogger_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x04c4, code lost:
        if (r27.equals("fbexit") != false) goto L_0x04c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x04c6, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbexit_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x04d1, code lost:
        if (r27.equals("proxygen_lib_utils_logging") != false) goto L_0x04d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x04d3, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libproxygen_lib_utils_logging_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        if (r11.contains(r26) == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x04de, code lost:
        if (r27.equals("opus1_3_1") != false) goto L_0x04e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x04e0, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libopus1_3_1_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x04eb, code lost:
        if (r27.equals("flatbuffers") != false) goto L_0x04ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x04ed, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libflatbuffers_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x04f8, code lost:
        if (r27.equals("proxygen_lib_utils_conn_quality") != false) goto L_0x04fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x04fa, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libproxygen_lib_utils_conn_quality_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0505, code lost:
        if (r27.equals("oacr_api_jni") != false) goto L_0x0507;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0056, code lost:
        if (r28 != null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x0507, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.liboacr_api_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x0512, code lost:
        if (r27.equals("jniperflogger") != false) goto L_0x0514;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x0514, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libjniperflogger_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x051f, code lost:
        if (r27.equals("fbsystrace") != false) goto L_0x0521;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x0521, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.libfbsystrace_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x052c, code lost:
        if (r27.equals("oacr_runtime_sanitizer_jni") != false) goto L_0x052e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x052e, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.liboacr_runtime_sanitizer_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
        monitor-exit(X.KJ.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:321:0x0539, code lost:
        if (r27.equals("oacr_utils_jni") != false) goto L_0x053b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x053b, code lost:
        r0 = com.facebook.soloader.MergedSoMapping$Invoke_JNI_OnLoad.liboacr_utils_jni_so();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x053f, code lost:
        if (r0 == 0) goto L_0x0541;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x0541, code lost:
        X.KJ.A09.add(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x054e, code lost:
        throw new java.lang.UnsatisfiedLinkError("Failed to invoke native library JNI_OnLoad");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x054f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0059, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x055f, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass08.A07("Failed to call JNI_OnLoad from '", r27, "', which has been merged into '", r26, "'.  See comment for details."), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x0560, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x0561, code lost:
        X.KD.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0564, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x0565, code lost:
        X.KD.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x0568, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x0569, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x0572, code lost:
        return !r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0576, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0577, code lost:
        r19.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x057e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0061, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0062, code lost:
        r20 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
        monitor-exit(X.KJ.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0065, code lost:
        if (r20 != false) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        android.util.Log.d("SoLoader", X.AnonymousClass08.A04("About to load: ", r26));
        r19.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007f, code lost:
        if (X.KJ.A04 == null) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0081, code lost:
        r19.readLock().unlock();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008b, code lost:
        if (r30 != null) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008e, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0091, code lost:
        r15 = android.os.StrictMode.allowThreadDiskReads();
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0097, code lost:
        X.KD.A01("SoLoader.loadLibrary[", r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r19.readLock().lock();
        r5 = 0;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r1 = X.KJ.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a9, code lost:
        if (r2 >= r1.length) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ab, code lost:
        r5 = r1[r2].A04(r26, r29, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b3, code lost:
        if (r5 != 3) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b7, code lost:
        if (X.KJ.A05 == null) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b9, code lost:
        android.util.Log.d("SoLoader", X.AnonymousClass08.A04("Trying backup SoSource for ", r26));
        r4 = X.KJ.A05;
        r0 = r4.length;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c9, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cb, code lost:
        if (r5 != 0) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d0, code lost:
        if (r3 >= r0) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00d2, code lost:
        r2 = r4[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00d4, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r1 = r2.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00d7, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r0 = r1.get(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00dc, code lost:
        if (r0 != null) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00de, code lost:
        r0 = new java.lang.Object();
        r1.put(r26, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00e6, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00e7, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r2.A00 = r26;
        r2.A06(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00ee, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00ef, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00fe, code lost:
        if (r2.A04(r26, r29, r15) != 1) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0101, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0116, code lost:
        X.KD.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0119, code lost:
        if (r16 == false) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x011b, code lost:
        android.os.StrictMode.setThreadPolicy(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x011e, code lost:
        if (r5 == 0) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0120, code lost:
        if (r5 != 3) goto L_0x01bb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0541 A[Catch:{ UnsatisfiedLinkError -> 0x054f, all -> 0x0560 }] */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0547 A[Catch:{ UnsatisfiedLinkError -> 0x054f, all -> 0x0560 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A06(java.lang.String r26, java.lang.String r27, java.lang.String r28, int r29, android.os.StrictMode.ThreadPolicy r30) {
        /*
        // Method dump skipped, instructions count: 1616
        */
        throw new UnsupportedOperationException("Method not decompiled: X.KJ.A06(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }
}
