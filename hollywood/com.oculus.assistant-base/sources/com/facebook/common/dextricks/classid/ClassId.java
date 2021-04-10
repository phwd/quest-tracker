package com.facebook.common.dextricks.classid;

import X.KJ;
import android.os.Build;
import com.android.dex.Dex;
import com.oculus.aidl.OVRServiceInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ConcurrentHashMap;

public class ClassId {
    public static Field comAndroidDexDex_data;
    public static Field javaLangClass_dexCache;
    public static Field javaLangClass_dexClassDefIndex;
    public static Method javaLangClass_getDex;
    public static Method javaLangClass_getDexClassDefIndex;
    public static Field javaLangDexCache_dexFile;
    public static Method javaLangDexCache_getDex;
    public static final ConcurrentHashMap sDexKeyToDexSignature = new ConcurrentHashMap(0, 0.9f);
    public static final boolean sInitialized;

    static {
        boolean z;
        boolean z2 = false;
        try {
            KJ.A05("classid", 0);
            z = true;
        } catch (UnsatisfiedLinkError unused) {
            z = false;
        }
        if (z) {
            synchronized (ClassId.class) {
                if (Build.VERSION.SDK_INT < 26) {
                    try {
                        Field declaredField = Class.forName("com.android.dex.Dex").getDeclaredField("data");
                        declaredField.setAccessible(true);
                        comAndroidDexDex_data = declaredField;
                    } catch (Exception unused2) {
                    }
                }
                try {
                    Field declaredField2 = Class.class.getDeclaredField("dexClassDefIndex");
                    Field declaredField3 = Class.class.getDeclaredField("dexCache");
                    Class<?> cls = Class.forName("java.lang.DexCache");
                    declaredField2.setAccessible(true);
                    declaredField3.setAccessible(true);
                    javaLangClass_dexClassDefIndex = declaredField2;
                    javaLangClass_dexCache = declaredField3;
                    if (Build.VERSION.SDK_INT < 26) {
                        Method declaredMethod = cls.getDeclaredMethod("getDex", new Class[0]);
                        declaredMethod.setAccessible(true);
                        javaLangDexCache_getDex = declaredMethod;
                    } else {
                        Field declaredField4 = cls.getDeclaredField("dexFile");
                        declaredField4.setAccessible(true);
                        javaLangDexCache_dexFile = declaredField4;
                    }
                    verifyInitialize();
                } catch (Exception unused3) {
                    if (Build.VERSION.SDK_INT < 26) {
                        try {
                            Method declaredMethod2 = Class.class.getDeclaredMethod("getDexClassDefIndex", new Class[0]);
                            Method declaredMethod3 = Class.class.getDeclaredMethod("getDex", new Class[0]);
                            declaredMethod2.setAccessible(true);
                            declaredMethod3.setAccessible(true);
                            javaLangClass_getDexClassDefIndex = declaredMethod2;
                            javaLangClass_getDex = declaredMethod3;
                            verifyInitialize();
                        } catch (Exception unused4) {
                            sInitialized = z2;
                        }
                    }
                }
            }
            z2 = true;
            sInitialized = z2;
        }
    }

    public static native int getSignatureFromDexFile_8_0_0(long j);

    public static native int getSignatureFromDexFile_8_1_0(long j);

    public static native int getSignatureFromDexFile_9_0_0(long j);

    public static int calculateViaDexCacheDexFileSignature(Class cls) {
        int signatureFromDexFile_8_0_0;
        Object obj = javaLangClass_dexCache.get(ClassId.class);
        if (obj == null) {
            return 0;
        }
        Number number = (Number) sDexKeyToDexSignature.get(obj);
        if (number == null) {
            long j = javaLangDexCache_dexFile.getLong(obj);
            switch (Build.VERSION.SDK_INT) {
                case OVRServiceInterface.Stub.TRANSACTION_sharedMicrophoneDisableNoiseSuppressor /*{ENCODED_INT: 26}*/:
                    signatureFromDexFile_8_0_0 = getSignatureFromDexFile_8_0_0(j);
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_sharedMicrophoneEnableNoiseSuppressor /*{ENCODED_INT: 27}*/:
                    signatureFromDexFile_8_0_0 = getSignatureFromDexFile_8_1_0(j);
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_updatePlatformContext /*{ENCODED_INT: 28}*/:
                case OVRServiceInterface.Stub.TRANSACTION_assetFileDownloadById /*{ENCODED_INT: 29}*/:
                case OVRServiceInterface.Stub.TRANSACTION_assetFileDownloadByName /*{ENCODED_INT: 30}*/:
                    signatureFromDexFile_8_0_0 = getSignatureFromDexFile_9_0_0(j);
                    break;
                default:
                    signatureFromDexFile_8_0_0 = 0;
                    break;
            }
            number = Integer.valueOf(signatureFromDexFile_8_0_0);
            sDexKeyToDexSignature.put(obj, number);
        }
        return number.intValue();
    }

    public static int getSignatureForDex(Dex dex) {
        ByteBuffer byteBuffer;
        Field field = comAndroidDexDex_data;
        if (!(field == null || (byteBuffer = (ByteBuffer) field.get(dex)) == null)) {
            ByteBuffer duplicate = byteBuffer.duplicate();
            if (duplicate.limit() >= 16) {
                duplicate.order(ByteOrder.LITTLE_ENDIAN);
                duplicate.position(12);
                int i = duplicate.getInt();
                if (i != 0) {
                    return i;
                }
            }
        }
        return dex.open(12).readInt();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a2, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009c A[ExcHandler: IOException | IllegalAccessException | InvocationTargetException (r1v2 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:38:0x0096] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void verifyInitialize() {
        /*
        // Method dump skipped, instructions count: 176
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.dextricks.classid.ClassId.verifyInitialize():void");
    }
}
